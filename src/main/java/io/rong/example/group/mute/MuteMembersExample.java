package io.rong.example.group.mute;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.group.mute.MuteMembers;
import io.rong.models.Result;
import io.rong.models.group.GroupMember;
import io.rong.models.group.GroupModel;
import io.rong.models.response.GroupMuteMembersListResult;
/**
 * Example of muting group members
 * @author RongCloud
 *
 * @version 3.0.0
 */
public class MuteMembersExample {
    /**
     * Replace with your App Key
     * */
    private static final String appKey = "appKey";
    /**
     * Replace with your App Secret
     * */
    private static final String appSecret = "appSecret";
    /**
     * Local test execution
     *
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // Custom API endpoint configuration
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, CenterEnum.BJ);

        MuteMembers muteMembers = rongCloud.group.muteMembers;
        /**
         * API Documentation: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * Method to mute group members
         */

        GroupMember[] members = {new GroupMember().setId("ghJiu7H1"),new GroupMember().setId("ghJiu7H2")};

        GroupModel group = new GroupModel()
                .setId("groupId")
                .setMembers(members)
                .setMinute(5);
        Result result = muteMembers.add(group);
        System.out.println("group.muteMembers.add:  " + result.toString());

        /**
         * API Documentation: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * Query muted group members
         */
        GroupMuteMembersListResult muteMembersResult = muteMembers.getList("groupId");
        System.out.println("group.muteMembers.getList:  " + muteMembersResult.toString());

        /**
         * API Documentation: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * Remove muted group members
         */
        group = new GroupModel()
                .setId("groupId")
                .setMembers(members);

        Result groupRollBackGagUserResult = muteMembers.remove(group);
        System.out.println("group.muteMembers.remove:  " + groupRollBackGagUserResult.toString());

    }
}
