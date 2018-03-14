package io.rong.example.group;

import io.rong.RongCloud;
import io.rong.methods.group.gag.Gag;
import io.rong.models.Result;
import io.rong.models.group.GroupMember;
import io.rong.models.group.GroupModel;
import io.rong.models.response.ListGagGroupUserResult;
/**
 *
 * 群组禁言例子
 * @author hc
 * @date 2017/12/30
 * @version
 */
public class GagExample {
    private static final String JSONFILE = GroupExample.class.getClassLoader().getResource("jsonsource").getPath()+"/";
    /**
     * 此处替换成您的appKey
     * */
    private static final String appKey = "appKey";
    /**
     * 此处替换成您的appSecret
     * */
    private static final String appSecret = "appSecret";

    /**
     * 本地调用测试
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        Gag Gag = rongCloud.group.gag;
        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/group/gag.html#add
         * 添加禁言群成员方法
         */
        String[] memberIds = {"userId1","userid2","userId3"};

        GroupMember[] members = {new GroupMember().setId("ghJiu7H1"),new GroupMember().setId("ghJiu7H2")};

        GroupModel group = new GroupModel()
                .setId("IXQhMs3ny")
                .setMembers(members)
                .setMunite(5);
        Result result = Gag.add(group);
        System.out.println("group.gag.add:  " + result.toString());

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/group/gag.html#getList
         * 查询被禁言群成员
         */
        ListGagGroupUserResult groupLisGagUserResult = Gag.getList("25");
        System.out.println("group.gag.getList:  " + groupLisGagUserResult.toString());

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/group/gag.html#remove
         * 移除禁言群成员
         */
        group = new GroupModel()
                .setId("IXQhMs3ny")
                .setMembers(members);

        Result groupRollBackGagUserResult = Gag.remove(group);
        System.out.println("group.gag.remove:  " + groupRollBackGagUserResult.toString());

    }
}
