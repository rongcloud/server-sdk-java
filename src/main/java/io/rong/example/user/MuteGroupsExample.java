package io.rong.example.user;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.user.mute.MuteGroups;
import io.rong.models.Result;
import io.rong.models.group.GroupMember;
import io.rong.models.group.GroupModel;
import io.rong.models.response.GroupMuteMembersListResult;
/**
 *
 * Example of muting all group members
 * @author RongCloud
 *
 * @version 3.0.0
 */
public class MuteGroupsExample {
    /**
     * Replace with your App Key
     * */
    private static final String appKey = "appKey";
    /**
     * Replace with your App Secret
     * */
    private static final String appSecret = "appSecret";

    /**
     * Local test
     *
     *
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        //Custom API endpoint
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, CenterEnum.BJ);

        MuteGroups muteGroups = rongCloud.user.muteGroups;
        /**
         * API documentation: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * Prevent users from sending messages in all joined groups
         */

        GroupMember[] members = {new GroupMember().setId("ghJiu7H1"),new GroupMember().setId("ghJiu7H2")};

        GroupModel groupModel = new GroupModel()
                .setMembers(members)
                .setMinute(5);
        Result result = muteGroups.add(groupModel);
        System.out.println("group.ban.add:  " + result.toString());

        /**
         * 
         * Retrieve the list of muted users in all groups
         */
        groupModel = new GroupModel()
                .setMembers(members);
        GroupMuteMembersListResult GroupBanResult = (GroupMuteMembersListResult) muteGroups.getList();
        System.out.println("group.ban.getList:  " + GroupBanResult.toString());

        /**
         * 
         * Remove the mute settings for a user in all groups
         */
        groupModel = new GroupModel()
                .setMembers(members);
        Result groupRollBackGagUserResult =  muteGroups.remove(groupModel);
        System.out.println("group.ban.remove:  " + groupRollBackGagUserResult.toString());


    }
}
