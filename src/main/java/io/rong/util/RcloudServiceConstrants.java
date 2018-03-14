package io.rong.util;

public class RcloudServiceConstrants {
    public static final int CHATROOM= 1;
    public static final int GROUP =2;
    public static final int MESSAGE = 3;
    public static final int PUSH = 4;
    public static final int SENSITIVEWORD = 5;
    public static final int SMS = 6;
    public static final int USER= 7;
    public static final int WORDFILTER = 8;

    public static final int RCLOUD_SERVICE_ERROR= 1000;//1000=服务器端内部逻辑错误，请稍后重试
    public static final int RCLOUD_APP_NO_MATCH=1001;//1001=App Key与App Secret 不匹配
    public static final int RCLOUD_PARAM_ERROR = 1002;//1002=参数错误	参数错误，详细的描述信息会说明
    public static final int RCLOUD_NOT_POSTBODY = 1003;//1003=没有POST任何数据，请增加post请求参数
    public static final int RCLOUD_SINNATURE_ERROR = 1004;//1004=验证签名错误，查看签名验证http://www.rongcloud.cn/docs/server.html#signature
    public static final int RCLOUD_PARAM_LEN_OUT = 1005;//参数长度超限，参数长度超限，详细的描述信息会说明
    public static final int RCLOUD_APP_LOCKED = 1006;//App被锁定或删除，联系工作人员排查。
    public static final int RCLOUD_METHOD_LIMITED= 1007;//被限制调用	该方法被限制调用，详细的描述信息会说明
    public static final int RCLOUD_FREQUENCY_OUT= 1008;//=调用频率超限	调用频率超限，详细的描述信息会说明，广播消息未开通时也会返回此状态码
    public static final int RCLOUD_SERVICE_UNOPEN = 1009;//服务未开通，未开通该服务，请到开发者管理后台开通或提交工单申请
    public static final int RCLOUD_NOT_CHATROOMID_KEEPALIVE = 1015;//要删除的保活聊天室ID不存在，确认聊天室ID存在
    public static final int RCLOUD_CHATROOMID_KEEPALIVE_OUT = 1016;//设置保活聊天室个数超限，设置的保活聊天室个数超限。请联系上午协调更改
    public static final int RCLOUD_CONNECT_TIMEOUT= 1050;//内部服务超时，内部服务响应超时。联系融云，协助排查
    public static final int RCLOUD_TESTUSERS_OUT = 2007;//测试用户数量超限，测试用户数量超限。请提供蛋申请













//2007=测试用户数量超限，测试用户数量超限。请提供蛋申请

    //		chatRoom(1),group(2),message(3),push(4),sensitiveword(5),sms(6),user(7),worefilter(8);
}
