/**
 * 融云 Server API java 客户端
 * create by kitName
 * create datetime : 2017-06-05
 * <p>
 * v2.0.1
 */
package io.rong;

import io.rong.methods.chatroom.Chatroom;
import io.rong.methods.conversation.Conversation;
import io.rong.methods.group.Group;
import io.rong.methods.message.Message;
import io.rong.methods.message.expansion.Expansion;
import io.rong.methods.profile.EntrustGroup;
import io.rong.methods.profile.EntrustUser;
import io.rong.methods.profile.Friend;
import io.rong.methods.push.Push;
import io.rong.methods.push.PushCustom;
import io.rong.methods.sensitive.SensitiveWord;
import io.rong.methods.sensitive.Wordfilter;
import io.rong.methods.ultragroup.UltraGroup;
import io.rong.methods.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class RongCloud {

    private static ConcurrentHashMap<String, RongCloud> rongCloud = new ConcurrentHashMap<String, RongCloud>();

    public User user;
    public Message message;
    public Expansion expansion;
    public Wordfilter wordfilter;
    public SensitiveWord sensitiveword;
    public Group group;
    public UltraGroup ultraGroup;
    public Chatroom chatroom;
    public Conversation conversation;
    public Push push;
    public PushCustom pushCustom;
    public EntrustGroup entrustGroup;
    public Friend friend;
    public EntrustUser entrustUser;
    public final RongCloudConfig config;

    public RongCloudConfig getConfig() {
        return this.config;
    }

    private RongCloud(String appKey, String appSecret, RongCloudConfig config) {
        user = new User(appKey, appSecret, this);
        message = new Message(appKey, appSecret);
        message.setRongCloud(this);
        expansion = new Expansion(appKey, appSecret);
        expansion.setRongCloud(this);
        wordfilter = new Wordfilter(appKey, appSecret);
        wordfilter.setRongCloud(this);
        sensitiveword = new SensitiveWord(appKey, appSecret);
        sensitiveword.setRongCloud(this);
        group = new Group(appKey, appSecret, this);
        ultraGroup = new UltraGroup(appKey, appSecret, this);
        chatroom = new Chatroom(appKey, appSecret, this);
        chatroom.setRongCloud(this);
        conversation = new Conversation(appKey, appSecret);
        conversation.setRongCloud(this);
        push = new Push(appKey, appSecret);
        push.setRongCloud(this);
        pushCustom = new PushCustom(appKey, appSecret, this);
        entrustGroup = new EntrustGroup(appKey, appSecret, this);
        friend = new Friend(appKey, appSecret, this);
        entrustUser = new EntrustUser(appKey, appSecret, this);
        this.config = config;
    }


    public static RongCloud getInstance(String appKey, String appSecret, CenterEnum centerEnum) {
        return getInstance(appKey, appSecret,
                new RongCloudConfig(centerEnum.getPrimaryUrl(), centerEnum.getBackupUrl()));
    }

    /**
     * 自定义访问实例
     *
     * @param appKey
     * @param appSecret
     * @param config
     * @return
     */
    public static RongCloud getInstance(String appKey, String appSecret, RongCloudConfig config) {
        if (null == rongCloud.get(appKey + "_" + appSecret)) {
            RongCloud rc = new RongCloud(appKey, appSecret, config);
            rongCloud.putIfAbsent(appKey + "_" + appSecret, rc);
            return rc;
        } else {
            return rongCloud.get(appKey + "_" + appSecret);
        }
    }

    /**
     * 自定义 API 地址
     */
    public static RongCloud getInstance(String appKey, String appSecret, String... apiHosts) {
        return getInstance(appKey, appSecret, new RongCloudConfig(apiHosts));
    }

    /**
     * 自定义 api 支持备用域名
     *
     * @param appKey
     * @param appSecret
     * @param api       主 API 地址
     * @param apiBackUp 备用 API 地址列表
     */
    @Deprecated
    public static RongCloud getInstance(String appKey, String appSecret, String api, List<String> apiBackUp) {
        List<String> apiList = new ArrayList<>();
        if (api != null) {
            apiList.add(api);
        }
        if (apiBackUp != null && apiBackUp.size() > 0) {
            for (String item : apiBackUp) {
                if (item != null)
                    apiList.add(item);
            }
        }
        return getInstance(appKey, appSecret, new RongCloudConfig(apiList));
    }


}