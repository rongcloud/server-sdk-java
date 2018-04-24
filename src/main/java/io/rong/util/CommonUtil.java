package io.rong.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.rong.models.User;
import io.rong.models.response.BlackListResult;
import io.rong.models.response.UserList;
import io.rong.models.response.WhiteListResult;
import io.rong.models.user.UserModel;
import org.apache.commons.lang3.StringUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 属性校验公共服务
 *
 * @author RongCloud
 */
public class CommonUtil {
    public static final String VERIFY_JSON_NAME = "/verify.json";
    public static final String API_JSON_NAME = "/api.json";
    public static boolean validateParams(Object params, int length){
        try {
            if(null == params){
                return false;
            }
            if(params instanceof String[]){
                String[] param = (String[]) params;
                int len = param.length;
                if(len <= length){
                    return true;
                }
            }else if(params instanceof String){
                String param = (String) params;
                int len = param.getBytes("UTF-8").length;
                if(len <= length){
                    return true;
                }
            }else if(params instanceof Integer){
                Integer param = (Integer) params;
                if(param <= length){
                    return true;
                }
            }
        } catch (UnsupportedEncodingException e) {
            System.out.println("长度校验错误"+e);
        }
        return false;
    }
    /**
     * 参数校验方法
     *
     * @param model  校验对象
     * @param path   路径
     * @param method 需要校验方法
     *
     * @return String
     **/
    public static String checkFiled(Object model,String path,String method){
        try {
            String code = "200";
            Integer max = 64;
            //api.json 的路径
            String apiPath = path;
            String type = "";
            if(path.contains("/")){
                path = path.substring(0,path.indexOf("/"));
            }
            String[] fileds = {};
            String checkObjectKey = "";
            //获取需要校验的参数
            Map<String,String[]> checkInfo = getCheckInfo(apiPath,method);
            for (Map.Entry<String,String[]> entry : checkInfo.entrySet()) {
                fileds = entry.getValue();
                checkObjectKey = entry.getKey();
            }
            //获取校验文件
            JSONObject verify =  JsonUtil.getJsonObject(path,VERIFY_JSON_NAME);
            //获取校验key
            Set<String> keys = verify.getJSONObject(checkObjectKey).keySet();
            //获取具体校验规则
            JSONObject entity = verify.getJSONObject(checkObjectKey);
            for(String name : fileds){
                for (String key : keys) {
                    if(name.equals(key)){
                        String nameTemp = name;
                        //将属性的首字符大写，方便构造get，set方法
                        name = name.substring(0,1).toUpperCase()+name.substring(1);
                        //获取属性的类型
                        //String type = field.getGenericType().toString();
                        Method m = model.getClass().getMethod("get"+name);
                        //获取字段的具体校验规则
                        JSONObject object =  entity.getJSONObject(nameTemp);
                        if(object.containsKey("require")){
                            Boolean must = (Boolean)object.getJSONObject("require").get("must");
                            if(m.invoke(model)  instanceof String){
                                String value = (String) m.invoke(model);
                                if(StringUtils.isBlank(value)){
                                    code = (String)object.getJSONObject("require").get("invalid");
                                }
                            }else{
                                Object value = (Object) m.invoke(model);
                                if(null == value){
                                    code = (String)object.getJSONObject("require").get("invalid");
                                }
                            }
                        }
                        if(object.containsKey("length")){
                            max = (Integer)object.getJSONObject("length").get("max");
                            //String value = (String) m.invoke(model);
                            if(m.invoke(model)  instanceof String){
                                String value = (String) m.invoke(model);
                                if("200".equals(code) && StringUtils.isBlank(value)){
                                    code = (String)object.getJSONObject("length").get("invalid");
                                }
                                if("200".equals(code) && value.getBytes("UTF-8").length > max){
                                    code = (String)object.getJSONObject("length").get("invalid");
                                }
                            }else if(m.invoke(model)  instanceof String[]){
                                String[] value = (String[]) m.invoke(model);
                                if("200".equals(code) && value.length > max){
                                    code = (String)object.getJSONObject("length").get("invalid");
                                }
                            }/*else{
                                Object value = (Object) m.invoke(model);
                                if(!"200".equals(code)){
                                    code = (String)object.getJSONObject("length").get("invalid");
                                }

                            }*/

                        }
                        if(object.containsKey("size")){
                            max = (Integer)object.getJSONObject("size").get("max");
                            type = (String)object.getJSONObject("typeof").get("type");
                            if(type.contains("array")){
                                Object[] value = (Object[]) m.invoke(model);
                                if("200".equals(code) && null == value){
                                    code = (String)object.getJSONObject("size").get("invalid");
                                }
                                if("200".equals(code) && value.length > max){
                                    code = (String)object.getJSONObject("size").get("invalid");
                                }

                            }else if(type.contains("int")){
                                Integer value = 0;
                                try{
                                    value = (Integer) m.invoke(model);
                                }catch (Exception e){
                                    code = (String)object.getJSONObject("typeof").get("invalid");
                                }

                                if("200".equals(code) && null == value){
                                    code = (String)object.getJSONObject("size").get("invalid");
                                }
                                if("200".equals(code) && value > max){
                                    code = (String)object.getJSONObject("size").get("invalid");
                                }

                            }
                        }
                        if(!"200".equals(code)){
                            //更具错误吗获取错误信息
                            String message = (String)CommonUtil.getErrorMessage(apiPath,method,code,name,String.valueOf(max),"1",type);
                            //对 errorMessage  替换
                            message = StringUtils.replace(message,"errorMessage","msg");
                            return message;
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 参数校验
     *
     * @param checkFiled 需要校验的字段
     * @param value  传入参数值
     * @param path   路径 （获取校验文件路径）
     * @param method 需要校验方法
     *
     * @return String
     **/
    public static String checkParam(String checkFiled,Object value,String path,String method){
        try {
            String code = "200";
            boolean flag = false;
            Integer max = 64;
            String type = "";
            String  apiPath = path;
            if(path.contains("/")){
                path = path.substring(0,path.indexOf("/"));
            }
            //String[] fileds = {};
            String checkObject = "";
            //获取需要校验的key
            Map<String,String[]> checkInfo = getCheckInfo(apiPath,method);
            for (Map.Entry<String,String[]> entry : checkInfo.entrySet()) {
                //fileds = entry.getValue();
                checkObject = entry.getKey();
            }
            JSONObject verify =  JsonUtil.getJsonObject(path,VERIFY_JSON_NAME);
            Set<String> keys = verify.getJSONObject(checkObject).keySet();
            JSONObject entity = verify.getJSONObject(checkObject);
            for (String key : keys) {
                if(checkFiled.equals(key)){
                    JSONObject object =  entity.getJSONObject(checkFiled);
                    if(object.containsKey("require")){
                        Boolean must = (Boolean)object.getJSONObject("require").get("must");
                        if(value  instanceof String){
                            if(StringUtils.isBlank(String.valueOf(value))){
                                code = (String)object.getJSONObject("require").get("invalid");
                            }

                        }else{
                            if(null == value){
                                code = (String)object.getJSONObject("require").get("invalid");
                            }
                        }
                    }
                    if(object.containsKey("length")){
                        max = (Integer)object.getJSONObject("length").get("max");
                        if(value  instanceof String){
                            if("200".equals(code) && StringUtils.isBlank(String.valueOf(value))){
                                code = (String)object.getJSONObject("length").get("invalid");
                            }
                            if("200".equals(code) && String.valueOf(value).getBytes("UTF-8").length > max){
                                code = (String)object.getJSONObject("length").get("invalid");
                            }
                        }else if(value  instanceof String[]){
                            String[] valueTemp = {};
                            try{
                                valueTemp = (String[]) value;
                            }catch (Exception e){
                                code = (String)object.getJSONObject("typeof").get("invalid");
                            }
                            if("200".equals(code) && valueTemp.length > max){
                                code = (String)object.getJSONObject("length").get("invalid");
                            }
                        }

                    }
                    if(object.containsKey("size")){
                        max = (Integer)object.getJSONObject("size").get("max");
                        type = (String)object.getJSONObject("typeof").get("type");
                        if(type.contains("array")){
                            String[] valueTemp = null;
                            if("200".equals(code) && null == value){
                                code = (String)object.getJSONObject("size").get("invalid");
                            }
                            try{
                                valueTemp = (String[]) value;
                            }catch (Exception e){
                                code = (String)object.getJSONObject("typeof").get("invalid");
                            }

                            if("200".equals(code) && valueTemp.length > max){
                                code = (String)object.getJSONObject("size").get("invalid");
                            }

                        }else if(type.contains("int")){
                            Integer valueTemp = 64;
                            try{
                                valueTemp = (Integer) value;
                            }catch (Exception e){
                                code = (String)object.getJSONObject("typeof").get("invalid");
                            }
                            if("200".equals(code) && null == value){
                                code = (String)object.getJSONObject("size").get("invalid");
                            }
                            if("200".equals(code) && valueTemp > max){
                                code = (String)object.getJSONObject("size").get("invalid");
                            }

                        }
                    }
                    String message = (String)CommonUtil.getErrorMessage(apiPath,method,code,checkFiled,String.valueOf(max),"1",type);
                    message = StringUtils.replace(message,"errorMessage","msg");
                    return message;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 获取错误信息
     *
     * @param path   路径 （获取校验文件路径）
     * @param method 校验方法（需要校验的方法）
     * @param errorCode 错误码
     * @param name  具体字段名
     * @param max   字段需要的最大值
     * @param min   字段的最小值
     * @param type  类型
     *
     * @return Map
     **/
    public static Object getErrorMessage(String path,String method,String errorCode,String name,String max ,String min,String type){
        JSONObject api = null;
        try {
            api = JsonUtil.getJsonObject(path,API_JSON_NAME);
            Set<Map.Entry<String,Object>> keys = api.getJSONObject(method).getJSONObject("response").getJSONObject("fail").entrySet();
            String[] serchList = {"{{name}}","{{max}}","{{name}}","{{min}}","{{currentType}}"};
            String[] replaceList = {name,max,name,min,type};
            for (Map.Entry<String,Object> entry : keys) {
                if(errorCode.equals(entry.getKey())){
                    String text = entry.getValue().toString();
                    //StringUtils.replaceEach(text,serchList,replaceList);
                    return StringUtils.replaceEach(text,serchList,replaceList);
                }
            }
        } catch (IOException e) {

        }
        return null;
    }
    /**
     * 获取校验信息
     *
     * @param path   路径 （获取校验文件路径）
     * @param method 校验方法（需要校验的方法）
     *
     * @return Map
     **/
    public static Map<String,String[]> getCheckInfo(String path,String method){
        JSONObject api = null;
        try {
            api = JsonUtil.getJsonObject(path,API_JSON_NAME);
            Set<String> keys = api.getJSONObject(method).getJSONObject("params").keySet();
            String key = keys.iterator().next();
            Set<String> subkeys;
            try{
                Object object = api.getJSONObject(method).getJSONObject("params").get(key);

                if(null != object && object instanceof JSONObject){
                    subkeys = api.getJSONObject(method).getJSONObject("params").getJSONObject(key).keySet();
                }else{
                    subkeys = keys;
                }
                Map<String,String[]> map = new HashMap<>();
                map.put(key,subkeys.toArray(new String[subkeys.size()]));
                return map;
            }catch(ClassCastException e){
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 获取response信息
     *
     * @param path   路径 （获取校验文件路径）
     * @param method 校验方法（需要校验的方法）
     * @param response 返回信息
     *
     * @return String
     **/
    public static String getResponseByCode(String path,String method,String response){
        JSONObject api = null;
        try {
            JSONObject object =  JSON.parseObject(response);
            String code = String.valueOf(object.get("code"));
            api = JsonUtil.getJsonObject(path,API_JSON_NAME);
            Set<Map.Entry<String,Object>> keys = api.getJSONObject(method).getJSONObject("response").getJSONObject("fail").entrySet();
            String text = response;
            if(code.equals("200")){
               if(path.contains("blacklist") && method.equals("getList")){

                   UserList userList = (UserList)GsonUtil.fromJson(response,UserList.class);
                   ArrayList<UserModel> users = new ArrayList<>();
                   for(String id : userList.getUsers()){
                       users.add(new UserModel().setId(id));
                   }
                   UserModel[] members = users.toArray(new UserModel[users.size()]);

                   BlackListResult blacklist = new BlackListResult(userList.getCode(),null,members);

                   text = blacklist.toString();

                }else if(path.contains("whitelist/user") && method.equals("getList")){

                   UserList userList = (UserList)GsonUtil.fromJson(response,UserList.class);
                   //User[] members = {};
                   ArrayList<UserModel> users = new ArrayList<>();
                   for(String id : userList.getUsers()){
                       users.add(new UserModel().setId(id));
                   }
                   UserModel[] members = users.toArray(new UserModel[users.size()]);
                   WhiteListResult whitelist = new WhiteListResult(userList.getCode(),null,members);

                   text = whitelist.toString();

                }else if(path.contains("chatroom")||path.contains("group")){
                    text = StringUtils.replace(response,"users","members");
                   if(text.contains("whitlistMsgType")){
                       text = StringUtils.replace(text,"whitlistMsgType","objectNames");
                   }
                   if(path.contains("gag")||path.contains("block")){
                       text = StringUtils.replace(text,"userId","id");
                   }
                }else if(path.contains("user")){
                   if(path.contains("block") || path.contains("blacklist")){
                       text = StringUtils.replace(response,"userId","id");
                   }
                }else if(path.contains("sensitiveword")){
                   text = StringUtils.replace(response,"word","keyword");
                   if(text.contains("keywords")){
                       text = StringUtils.replace(text,"keywords","words");
                   }
                   text = StringUtils.replace(text,"replaceWord","replace");

                }else{
                    text = response;
                }
                return text;
            }else{
                for (Map.Entry<String,Object> entry : keys) {
                    if(code.equals(entry.getKey())){
                        text = entry.getValue().toString();
                        //text = StringUtils.replace(text,"msg","errorMessage");
                        return text;
                    }
                }
                text = StringUtils.replace(response,"errorMessage","msg");
                if(path.contains("chatroom")){
                    text = StringUtils.replace(text,"users","members");
                    //对于 聊天室保活成功返回的code是0 更改统一返回200
                    if(path.contains("keepalive") && "0".equals(code)){
                        text = StringUtils.replace(text,"chatroomIds","chatrooms");
                        text = StringUtils.replace(text,"0","200");

                    }
                }
                return text;
            }
        } catch (Exception e) {
            System.out.println("-------------"+e);
        }
        return response;
    }

}
