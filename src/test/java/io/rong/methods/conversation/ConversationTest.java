package io.rong.methods.conversation;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import io.rong.RongCloud;
import io.rong.RongCloudConfig;
import io.rong.models.conversation.QueryConversationModel;
import io.rong.models.response.QueryConversationResult;
import io.rong.models.response.ResponseResult;
import io.rong.util.GsonUtil;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.*;

public class ConversationTest {

    @Test
    public void querySendsFormPostAndParsesHttpResponse() throws Exception {
        final AtomicReference<String> request = new AtomicReference<>();
        final AtomicReference<String> contentType = new AtomicReference<>();
        final AtomicReference<String> requestId = new AtomicReference<>();
        HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/user/conversation/query.json", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                request.set(exchange.getRequestMethod() + " " + IOUtils.toString(exchange.getRequestBody(), "UTF-8"));
                contentType.set(exchange.getRequestHeaders().getFirst("Content-Type"));
                requestId.set(exchange.getRequestHeaders().getFirst("X-Request-ID"));
                byte[] response = "{\"code\":200,\"conversations\":[],\"requestId\":\"http-id\"}".getBytes("UTF-8");
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, response.length);
                exchange.getResponseBody().write(response);
                exchange.close();
            }
        });
        server.start();
        try {
            RongCloud client = RongCloud.getInstance("test-app-key", "test-app-secret",
                    new RongCloudConfig("http://127.0.0.1:" + server.getAddress().getPort()));
            QueryConversationModel model = new QueryConversationModel();
            model.setUserId("user");

            QueryConversationResult result = client.conversation.query(model);

            assertEquals("POST userId=user", request.get());
            assertEquals("application/x-www-form-urlencoded", contentType.get());
            assertEquals(Integer.valueOf(200), result.getCode());
            assertNotNull(requestId.get());
            assertEquals(requestId.get(), result.getRequestId());
            assertEquals("userId=user", result.getReqBody());
            assertTrue(result.getConversations().isEmpty());
        } finally {
            server.stop(0);
        }
    }

    @Test
    public void queryEncodesFormParametersAndPreservesResponse() throws Exception {
        RecordingConversation conversation = new RecordingConversation();
        QueryConversationModel model = new QueryConversationModel();
        model.setUserId("用户 &+");
        model.setPageToken("cursor/+=");
        model.setSize(20);

        QueryConversationResult result = conversation.query(model);

        assertEquals("/user/conversation/query.json", conversation.uri);
        assertEquals("userId=%E7%94%A8%E6%88%B7+%26%2B&pageToken=cursor%2F%2B%3D&size=20", conversation.body);
        assertEquals(Integer.valueOf(200), result.getCode());
        assertEquals("next-token", result.getPageToken());
        assertEquals("request-id", result.getRequestId());
        assertEquals("target", result.getConversations().get(0).getTargetId());
        assertEquals("channel", result.getConversations().get(0).getBusChannel());
        assertEquals(Integer.valueOf(10), result.getConversations().get(0).getConversationType());
        assertEquals(Long.valueOf(1720000000000L), result.getConversations().get(0).getVersion());
        assertTrue(result.toString().contains("\"conversations\""));
        assertEquals("cursor/+=", model.getPageToken());
    }

    @Test
    public void queryOmitsOptionalParametersForFirstPage() throws Exception {
        RecordingConversation conversation = new RecordingConversation();
        QueryConversationModel model = new QueryConversationModel();
        model.setUserId("user");

        conversation.query(model);

        assertEquals("userId=user", conversation.body);
        assertNull(model.getSize());
        assertNull(model.getPageToken());
    }

    @Test
    public void queryLeavesSizeValidationToServer() throws Exception {
        RecordingConversation conversation = new RecordingConversation();
        QueryConversationModel model = new QueryConversationModel();
        model.setUserId("user");
        model.setSize(201);

        conversation.query(model);

        assertEquals("userId=user&size=201", conversation.body);
    }

    @Test
    public void queryRejectsMissingModelOrUserIdWithoutRequest() throws Exception {
        RecordingConversation conversation = new RecordingConversation();

        assertEquals(Integer.valueOf(1002), conversation.query(null).getCode());
        assertEquals(Integer.valueOf(1002), conversation.query(new QueryConversationModel()).getCode());
        QueryConversationModel model = new QueryConversationModel();
        model.setUserId("  ");
        assertEquals(Integer.valueOf(1002), conversation.query(model).getCode());
        assertNull(conversation.uri);
    }

    @Test
    public void queryDeserializesLastPageAndServerErrors() throws Exception {
        RecordingConversation conversation = new RecordingConversation();
        QueryConversationModel model = new QueryConversationModel();
        model.setUserId("user");
        conversation.response = "{\"code\":200,\"conversations\":[]}";

        QueryConversationResult result = conversation.query(model);

        assertTrue(result.getConversations().isEmpty());
        assertNull(result.getPageToken());

        conversation.response = "{\"code\":1008,\"errorMessage\":\"rate limited\",\"requestId\":\"error-id\"}";
        result = conversation.query(model);
        assertEquals(Integer.valueOf(1008), result.getCode());
        assertEquals("rate limited", result.getErrorMessage());
        assertEquals("error-id", result.getRequestId());
    }

    private static class RecordingConversation extends Conversation {
        private String uri;
        private String body;
        private String response = "{\"code\":200,\"requestId\":\"request-id\",\"pageToken\":\"next-token\","
                + "\"conversations\":[{\"targetId\":\"target\",\"busChannel\":\"channel\","
                + "\"conversationType\":10,\"version\":1720000000000}]}";

        private RecordingConversation() {
            super("appKey", "appSecret");
        }

        @Override
        protected <T extends ResponseResult> T doRequest(String uri, String body, String method,
                                                       Class<T> respClass) {
            this.uri = uri;
            this.body = body;
            return GsonUtil.fromJson(respClass, response);
        }
    }
}
