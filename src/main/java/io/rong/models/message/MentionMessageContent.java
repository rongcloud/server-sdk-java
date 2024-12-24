package io.rong.models.message;

import com.alibaba.fastjson.JSONObject;

import io.rong.messages.BaseMessage;

/**
 * @author RongCloud
 */
public class MentionMessageContent {
    private BaseMessage content;
    private MentionedInfo mentionedInfo;

    public MentionMessageContent(BaseMessage content, MentionedInfo mentionedInfo) {
        this.content = content;
        this.mentionedInfo = mentionedInfo;
    }

    public BaseMessage getContent() {
        return this.content;
    }

    public void setContent(BaseMessage content) {
        this.content = content;
    }

    public MentionedInfo getMentionedInfo() {
        return this.mentionedInfo;
    }

    public void setMentionedInfo(MentionedInfo mentionedInfo) {
        this.mentionedInfo = mentionedInfo;
    }

    @Override
    public String toString(){
    	JSONObject atMessage = new JSONObject();
    	
		if (content != null) {
			JSONObject baseMessage = new JSONObject();
			baseMessage = JSONObject.parseObject(content.toString());

			atMessage.put("content", baseMessage.get("content"));
			atMessage.put("extra", baseMessage.get("extra"));
            atMessage.put("user", baseMessage.get("user"));

			if (mentionedInfo != null) {
				JSONObject mention = new JSONObject();
				mention.put("type", mentionedInfo.getType());
				mention.put("userIdList", mentionedInfo.getUserIdList());
				mention.put("mentionedContent", mentionedInfo.getMentionedContent());
				
				atMessage.put("mentionedInfo", mention);
			}
		}
        return atMessage.toJSONString();
    }
}
