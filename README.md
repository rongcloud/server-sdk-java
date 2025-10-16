# server-sdk-java

RongCloud IM Server SDK in Java.

## Integration

* Get the [JAR](https://search.maven.org/search?q=g:cn.rongcloud.im%20AND%20a:server-sdk-java&core=gav) from the Maven repository
* Maven
```
    <dependency>
        <groupId>cn.rongcloud.im</groupId>
        <artifactId>server-sdk-java</artifactId>
        <version>4.0.8</version>
    </dependency>
```
* Gradle
```
    compile group: 'cn.rongcloud.im', name: 'server-sdk-java', version: '4.0.8'
```
* Build from source
```
   1. Download or clone `server-sdk-java`
   
   2. Navigate to the `server-sdk-java` directory
   
   3. Install dependencies with `mvn install`
   
   4. Package with `mvn clean package`
```
* Runtime environment

  Java version 7+

# Usage

Go to the [Developer Console](https://console.rongcloud.io/) to create an app and get your App Key and Secret.

Here’s how to register a user:

```
  String appKey = "appKey";
  String appSecret = "appSecret";
      
  // Initialize the RongCloud SDK, Set up the data center you use.
  RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret，CenterEnum.BJ);
  User user = rongCloud.user;

  /**
  *
  * Register a user and generate a unique Token in RongCloud
  */
  UserModel userModel = new UserModel()
            .setId("hHjap87")
            .setName("RongCloud")
            .setPortrait("http://www.rongcloud.cn/images/logo.png");
  TokenResult result = user.register(userModel);
  System.out.println("getToken: " + result.toString());

```
```
# Print result
# getToken: {"reqBody":"userId=userxxd2&name=username&portraitUri=http%3A%2F%2Fwww.rongcloud.cn%2Fimages%2Flogo.png","code":1002,"errorMessage":"Invalidate App-Key.","requestId":"0c1f127e9bc1401bb05eaad61b4502f7"}
```

Note: Each API call returns a unique ***request ID***, which identifies the request to RongCloud.

## Examples

###### [User module example](./src/main/java/io/rong/example/user)

###### [Message module example](./src/main/java/io/rong/example/message/MessageExample.java)

###### [Group module example](./src/main/java/io/rong/example/group)

###### [Chatroom module example](./src/main/java/io/rong/example/chatroom/)

###### [Conversation module example](./src/main/java/io/rong/example/conversation/ConversationExample.java)

###### [Sensitive word module example](./src/main/java/io/rong/example/sensitive/SensitiveExample.java)

###### [Ultra group module example](./src/main/java/io/rong/example/ultragroup)

# Version notes

We’ve updated to SDK version 3.8.0 for easier integration and better maintenance. Data center must be specified for new version initialization
