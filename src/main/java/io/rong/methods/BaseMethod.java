package io.rong.methods;

import io.rong.RongCloud;
import io.rong.models.response.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author RongCloud
 */
public abstract class BaseMethod {
    private static final String UTF8 = "UTF-8";
    protected static final String CONTENT_TYPE_JSON = "application/json";
    protected static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";
    protected String appKey;
    protected String appSecret;
    protected RongCloud rongCloud;
    protected String path;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }

    /**
     * Adds a parameter with URL encoding. Custom objects are uniformly processed by Gson.
     */
    protected void addFormParam(StringBuilder sb, String paramName, Object param) throws UnsupportedEncodingException {
        if (param == null) {
            return;
        }
        if (param instanceof String && StringUtils.isNotBlank((String) param)) {
            sb.append(paramName).append(URLEncoder.encode((String) param, UTF8));
        } else if (isBasicType(param)) {
            sb.append(paramName).append(URLEncoder.encode(String.valueOf(param), UTF8));
        } else {
            sb.append(paramName).append(URLEncoder.encode(GsonUtil.toJson(param), UTF8));
        }
    }

    /**
     * Get the JSON body
     */
    protected String getJsonBody(Object param) {
        if (param == null) {
            return "{}";
        }
        return GsonUtil.toJson(param);
    }


    /**
     * Initialize the interface validation file path
     */
    protected abstract void initPath();

    protected <T extends ResponseResult> T doRequest(String uri, String body, String method, Class<T> respClass) throws Exception {
        return doRequest(uri, body, method, respClass, CONTENT_TYPE_FORM);
    }

    protected <T extends ResponseResult> T doRequest(String uri, String body, String method, Class<T> respClass, String contentType) throws Exception {
        T result;
        String response = null;
        if (body.startsWith("&")) {
            body = body.substring(1);
        }
        try {
            HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, uri, contentType);
            HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());
            response = HttpUtil.returnResult(conn, rongCloud.getConfig());
            result = GsonUtil.fromJson(respClass, CommonUtil.getResponseByCode(path, method, response));
            if (result.getCode() == null) {
                throw new RuntimeException(response);
            }
        } catch (Exception e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = GsonUtil.fromJson(respClass, new ResponseResult(500, "uri=" + uri + "  response=" + response + "   errorInfo=" + e.getClass().getSimpleName() + ":" + e.getMessage()).toString());
        }
        result.setReqBody(body);
        return result;
    }


    protected <T extends ResponseResult> T doGetRequest(String uri, Class<T> respClass) {
        T result;
        String response = "";
        try {
            HttpURLConnection conn = HttpUtil.createGetHttpConnection(rongCloud.getConfig(), appKey, appSecret, uri);
            response = HttpUtil.returnResult(conn, rongCloud.getConfig());
            result = GsonUtil.fromJson(respClass, CommonUtil.getResponseByCode(path, "", response));
            if (result.getCode() == null) {
                throw new RuntimeException(response);
            }
        } catch (Exception e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = GsonUtil.fromJson(respClass, new ResponseResult(500, "uri=" + uri + "  response=" + response + "   errorInfo=" + e.getClass().getSimpleName() + ":" + e.getMessage()).toString());
        }
        return result;
    }


    /**
     * Use LinkedHashSet to maintain the insertion order of elements and automatically remove duplicates
     */
    protected String[] removeDuplicates(String[] params) {
        if (params == null || params.length == 0) {
            return new String[0];
        }

        Set<String> uniqueSet = new LinkedHashSet<>(Arrays.asList(params));
        return uniqueSet.toArray(new String[0]);
    }

    protected List<String> removeDuplicates(List<String> params) {
        if (params == null) {
            return Collections.emptyList();
        }
        Set<String> uniqueElements = new LinkedHashSet<>(params);
        return new ArrayList<>(uniqueElements);
    }

    protected <T extends ResponseResult> T checkFiled(Object model, String method, Class<T> respClass) {
        String message = CommonUtil.checkFiled(model, path, method, true);
        if (message != null) {
            return GsonUtil.fromJson(respClass, message);
        }
        return null;
    }

    protected <T extends ResponseResult> T checkParam(String paramName, Object value, String method, Class<T> respClass) {
        String message = CommonUtil.checkParam(paramName, value, path, method, true);
        if (message != null) {
            return GsonUtil.fromJson(respClass, message);
        }
        return null;
    }


    /**
     * Determines whether the given object is a primitive type or its wrapper class.
     *
     * @param obj The object to be checked
     * @return Returns true if the object is a primitive type or its wrapper class, otherwise returns false
     */
    public static boolean isBasicType(Object obj) {
        if (obj == null) {
            return false;
        }
        Class<?> clazz = obj.getClass();
        return clazz.isPrimitive() || Number.class.isAssignableFrom(clazz) ||
                clazz.equals(Boolean.class) || clazz.equals(Character.class) ||
                clazz.equals(Byte.class) || clazz.equals(Short.class) ||
                clazz.equals(Integer.class) || clazz.equals(Long.class) ||
                clazz.equals(Float.class) || clazz.equals(Double.class) ||
                clazz.equals(String.class);
    }

}
