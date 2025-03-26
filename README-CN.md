server-sdk-java
=================

RongCloud IM Server SDK in Java.

## 集成

   * 中央仓库获取[JAR](https://search.maven.org/search?q=g:cn.rongcloud.im%20AND%20a:server-sdk-java&core=gav)
   * Maven
```
    <dependency>
        <groupId>cn.rongcloud.im</groupId>
        <artifactId>server-sdk-java</artifactId>
        <version>3.8.0</version>
    </dependency>
```
   * Gradle
```
    compile group: 'cn.rongcloud.im', name: 'server-sdk-java', version: '3.8.0'
   
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
  // 初始化,设置您使用的数据中心
  RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, CenterEnum.BJ);
  User user = rongCloud.user;

  /**
  *
  * 注册用户，生成用户在融云的唯一身份标识 Token
  */
  UserModel userModel = new UserModel()
            .setId("hHjap87")
            .setName("RongCloud")
            .setPortrait("http://www.rongcloud.cn/images/logo.png");
  TokenResult result = user.register(userModel);
  System.out.println("getToken:  " + result.toString());

```
```
# 打印结果
# getToken:  {"reqBody":"userId=userxxd2&name=username&portraitUri=http%3A%2F%2Fwww.rongcloud.cn%2Fimages%2Flogo.png","code":1002,"errorMessage":"Invalidate App-Key.","requestId":"0c1f127e9bc1401bb05eaad61b4502f7"}
```

特别说明: 接口每次调用都会返回唯一的 ***requestId*** ，此 Id 为请求融云的唯一标识。


## 示例

###### [用户模块示例](./src/main/java/io/rong/example/user)

###### [消息模块示例](./src/main/java/io/rong/example/message/MessageExample.java)

###### [群组模块示例](./src/main/java/io/rong/example/group)

###### [聊天室模块示例](./src/main/java/io/rong/example/chatroom/)

###### [会话模块示例](./src/main/java/io/rong/example/conversation/ConversationExample.java)

###### [敏感词模块示例](./src/main/java/io/rong/example/sensitive/SensitiveExample.java)

###### [超级群模块示例](./src/main/java/io/rong/example/ultragroup)


# 版本说明
为方便开发者的接入使用，更好的对融云 Server SDK 进行维护管理，现更新SDK 3.8.0，需强制设置服务器地址。
