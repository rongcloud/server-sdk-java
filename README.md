server-sdk-java
=================

Rong Cloud Server SDK in Java.

# 使用教程

以注册用户为例

```
  String appKey = "appKey";
  String appSecret = "appSecret";
       
  RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
  User User = rongCloud.user;

  /**
  * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/user/user.html#register
  *
  * 注册用户，生成用户在融云的唯一身份标识 Token
  */
  UserModel user = new UserModel()
            .setId("hHjap87")
            .setName("RongCloud")
            .setPortrait("http://www.rongcloud.cn/images/logo.png");
  TokenResult result = User.register(user);
  System.out.println("getToken:  " + result.toString());

```
## [文档](http://rongcloud.github.io/server-sdk-nodejs/docs/v1/)

## 示例

###### [用户模块示例](./src/main/java/io/rong/example/user)

###### [消息模块示例](./src/main/java/io/rong/example/message/MessageExample.java)

###### [群组模块示例](./src/main/java/io/rong/example/group)

###### [聊天室模块示例](./src/main/java/io/rong/example/chatroom/)

###### [会话模块示例](./src/main/java/io/rong/example/conversation/ConversationExample.java)

###### [敏感词模块示例](./src/main/java/io/rong/example/sensitive/SensitiveExample.java)


# 版本说明
为方便开发者的接入使用，更好的对融云 Server SDK 进行维护管理，现更新SDK 3.0.0,老版本的 Server SDK仍可使用
但不兼容。