package io.rong.models.message;

import com.alibaba.fastjson.JSONObject;

import io.rong.messages.BaseMessage;

/**
 * @author RongCloud
 */
public class MentionMessageContent {
    private BaseMessage content;

    public MentionMessageContent(BaseMessage content, MentionedInfo mentionedInfo) {
        this.content = content;
        this.content.setMentionedInfo(mentionedInfo);
    }

    public BaseMessage getContent() {
        return this.content;
    }

    public void setContent(BaseMessage content) {
        this.content = content;
    }

    @Override
    public String toString(){
        JSONObject jsonObject = new JSONObject();
        if (content != null) {
            jsonObject = JSONObject.parseObject(content.toString());
            return jsonObject.toJSONString();
		}
        return jsonObject.toJSONString();
    }
}
