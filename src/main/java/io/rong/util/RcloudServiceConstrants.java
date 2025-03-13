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

    public static final int RCLOUD_SERVICE_ERROR = 1000; // 1000 = Internal server logic error, please try again later
    public static final int RCLOUD_APP_NO_MATCH = 1001; // 1001 = App Key and App Secret do not match
    public static final int RCLOUD_PARAM_ERROR = 1002; // 1002 = Parameter error, detailed description will be provided
    public static final int RCLOUD_NOT_POSTBODY = 1003; // 1003 = No POST data provided, please add post request parameters
    public static final int RCLOUD_SINNATURE_ERROR = 1004; // 1004 = Signature verification error
    public static final int RCLOUD_PARAM_LEN_OUT = 1005; // Parameter length exceeds the limit, detailed description will be provided
    public static final int RCLOUD_APP_LOCKED = 1006; // App is locked or deleted, contact support for assistance
    public static final int RCLOUD_METHOD_LIMITED = 1007; // Method call is restricted, detailed description will be provided
    public static final int RCLOUD_FREQUENCY_OUT = 1008; // Call frequency exceeds the limit, detailed description will be provided. This status code is also returned when broadcast message service is not enabled
    public static final int RCLOUD_SERVICE_UNOPEN = 1009; // Service is not enabled, please enable the service in the developer management console or submit a ticket
    public static final int RCLOUD_NOT_CHATROOMID_KEEPALIVE = 1015; // The chatroom ID to be kept alive does not exist, please confirm the chatroom ID exists
    public static final int RCLOUD_CHATROOMID_KEEPALIVE_OUT = 1016; // The number of chatrooms to be kept alive exceeds the limit, please contact business support for adjustment
    public static final int RCLOUD_CONNECT_TIMEOUT = 1050; // Internal service timeout, please contact RongCloud for assistance
    public static final int RCLOUD_TESTUSERS_OUT = 2007; // The number of test users exceeds the limit, please provide an application




    //		chatRoom(1),group(2),message(3),push(4),sensitiveword(5),sms(6),user(7),worefilter(8);
}
