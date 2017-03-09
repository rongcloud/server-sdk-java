/**
 * 融云 Server API java 客户端
 * create by kitName
 * create datetime : 2016-10-20
 * <p>
 * v2.0.1
 */
package io.rong;

import io.rong.methods.*;
import io.rong.util.HttpUtil;

import java.net.Proxy;
import java.util.concurrent.ConcurrentHashMap;

public class RongCloud {

    private static ConcurrentHashMap<String, RongCloud> rongCloud = new ConcurrentHashMap<String, RongCloud>();

    public User user;
    public Message message;
    public Wordfilter wordfilter;
    public Group group;
    public Chatroom chatroom;
    public Push push;
    public SMS sms;

    private RongCloud(String appKey, String appSecret) {
        user = new User(appKey, appSecret);
        message = new Message(appKey, appSecret);
        wordfilter = new Wordfilter(appKey, appSecret);
        group = new Group(appKey, appSecret);
        chatroom = new Chatroom(appKey, appSecret);
        push = new Push(appKey, appSecret);
        sms = new SMS(appKey, appSecret);

    }

    private RongCloud(String appKey, String appSecret, Proxy proxy) {
        user = new User(appKey, appSecret, proxy);
        message = new Message(appKey, appSecret, proxy);
        wordfilter = new Wordfilter(appKey, appSecret, proxy);
        group = new Group(appKey, appSecret, proxy);
        chatroom = new Chatroom(appKey, appSecret, proxy);
        push = new Push(appKey, appSecret, proxy);
        sms = new SMS(appKey, appSecret, proxy);

    }

    public static RongCloud getInstance(String appKey, String appSecret) {
        if (null == rongCloud.get(appKey)) {
            rongCloud.putIfAbsent(appKey, new RongCloud(appKey, appSecret));
        }
        return rongCloud.get(appKey);
    }

    public static RongCloud getInstance(String appKey, String appSecret, String proxyHost, int proxyPort) {
        if (null == rongCloud.get(appKey)) {
            rongCloud.putIfAbsent(appKey, new RongCloud(appKey, appSecret, HttpUtil.getProxy(proxyHost,proxyPort)));
        }
        return rongCloud.get(appKey);
    }

}