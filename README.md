server-sdk-java
=================

RongCloud IM Server SDK in Java.

## 集成

   * 中央仓库获取[JAR] (http://search.maven.org/remotecontent?filepath=cn/rongcloud/im/server-sdk-java/3.0.1/server-sdk-java-3.0.1.jar)
   * Maven
```
    <dependency>
        <groupId>cn.rongcloud.im</groupId>
        <artifactId>server-sdk-java</artifactId>
        <version>3.0.1</version>
    </dependency>
```
   * Gradle
```
    compile group: 'cn.rongcloud.im', name: 'server-sdk-java', version: '3.0.1'
   
```
   * 基于源码 Meavn 打包构建
```
   1、下载或克隆 `server-sdk-java`
   
   2、进入项目 `server-sdk-java` 目录
   
   3、安装依赖 `mvn install`
   
   4、打包 `mvn clean package`
   
```
   * 运行环境
   
    Java版本  7+

# 使用

请前往 [开发者后台](https://developer.rongcloud.cn/) 创建应用 -> 获取 Appkey、Secret

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