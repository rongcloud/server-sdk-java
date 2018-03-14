package io.rong.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
/**
 * Json公共服务
 * @date 2018-03-09
 * @author RongCloud
 */
public class JsonUtil {
    private static final String JSONFILE = JsonUtil.class.getClassLoader().getResource("jsonsource").getPath()+"/";
    /**
     * 获取JsonObject
     *
     * @param path 项目路径
     * @param jsonName json文件名字
     *
     * @return JsonObject
     **/
    public static <T> T getJsonObject(String path,String jsonName) throws IOException {
        BufferedReader reader = null ;
        try {
            String line="";
            StringBuffer arrs=new StringBuffer();
            reader =new BufferedReader( new InputStreamReader(new FileInputStream(JSONFILE+path+jsonName)));
            while ((line = reader.readLine()) != null){
                arrs.append(line);
            }
            JSONObject object =  JSON.parseObject(arrs.toString());
            return (T)object;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != reader){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    public static void main(String[] args){
        try {
           System.out.println((String)getJsonObject("group","/verify.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
