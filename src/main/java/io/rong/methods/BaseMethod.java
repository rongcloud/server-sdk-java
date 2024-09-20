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
     * 添加参数，url encode 处理，自定义对象 统一 Gson 处理
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
     * 获取 json Body
     */
    protected String getJsonBody(Object param) {
        if (param == null) {
            return "{}";
        }
        return GsonUtil.toJson(param);
    }


    /**
     * 初始化接口校验文件路径
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


    /**
     * 使用 LinkedHashSet 来保持元素的插入顺序，并自动去除重复项
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
     * 判断给定的对象是否为基本类型或其包装类。
     *
     * @param obj 要判断的对象
     * @return 如果是基本类型或其包装类返回 true，否则返回 false
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
