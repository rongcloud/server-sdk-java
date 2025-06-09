/**
 * RongCloud Server API Java Client
 * Created by kitName
 * Created datetime: 2017-06-05
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
import org.apache.commons.lang3.StringUtils;

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
        checkAppKeyAndAppSecret(appKey, appSecret);
        if (config == null) {
            throw new IllegalArgumentException("'config' can not be null");
        }
        if (StringUtils.isBlank(config.getDomain())) {
            throw new IllegalArgumentException("'config' must set domains");
        }
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
        checkAppKeyAndAppSecret(appKey, appSecret);
        if (centerEnum == null) {
            throw new IllegalArgumentException("'centerEnum' can not be null");
        }
        return getInstance(appKey, appSecret,
                new RongCloudConfig(centerEnum.getPrimaryUrl(), centerEnum.getBackupUrl()));
    }

    /**
     * Custom access instance
     *
     * @param appKey
     * @param appSecret
     * @param config
     * @return
     */
    public static RongCloud getInstance(String appKey, String appSecret, RongCloudConfig config) {
        checkAppKeyAndAppSecret(appKey, appSecret);
        if (null == rongCloud.get(appKey + "_" + appSecret)) {
            RongCloud rc = new RongCloud(appKey, appSecret, config);
            rongCloud.putIfAbsent(appKey + "_" + appSecret, rc);
            return rc;
        } else {
            return rongCloud.get(appKey + "_" + appSecret);
        }
    }

    /**
     * Check appKey and appSecret
     */
    private static void checkAppKeyAndAppSecret(String appKey, String appSecret) {
        if (StringUtils.isBlank(appKey) || StringUtils.isBlank(appSecret)) {
            throw new IllegalArgumentException("'appKey' and 'appSecret' can not be null");
        }
    }

    /**
     * Custom API endpoint
     */
    public static RongCloud getInstance(String appKey, String appSecret, String apiHost, String... apiHosts) {
        checkAppKeyAndAppSecret(appKey, appSecret);
        if (StringUtils.isBlank(apiHost)) {
            throw new IllegalArgumentException("'apiHost' can not be null");
        }
        return getInstance(appKey, appSecret, new RongCloudConfig(apiHost, apiHosts));
    }

}