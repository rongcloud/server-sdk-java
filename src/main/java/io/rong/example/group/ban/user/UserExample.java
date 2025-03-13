package io.rong.example.group.ban.user;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.group.Group;
import io.rong.models.Result;
import io.rong.models.group.GroupMember;
import io.rong.models.group.GroupModel;
import io.rong.models.response.ListGagGroupUserResult;
/**
 * Example of muting all members in a group
 * @author RongCloud
 * @version 3.0.0
 */
public class UserExample {
    /**
     * Replace with your App Key
     */
    private static final String appKey = "appKey";
    /**
     * Replace with your App Secret
     */
    private static final String appSecret = "appSecret";

    /**
     * Local test execution
     *
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, CenterEnum.BJ);
        // Custom API endpoint
        // RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);

        Group group = rongCloud.group;
        /**
         * 
         * Mute a user in all groups they have joined
         */

        GroupMember[] members = {new GroupMember().setId("ghJiu7H1"),new GroupMember().setId("ghJiu7H2")};

        GroupModel groupModel = new GroupModel()
                .setMembers(members)
                .setMinute(5);
        Result result = group.ban.user.add(groupModel);
        System.out.println("group.ban.add:  " + result.toString());

        /**
        * 
        * Get the list of muted users in all groups
        */
        groupModel = new GroupModel()
                .setMembers(members);
        ListGagGroupUserResult GroupBanResult = (ListGagGroupUserResult) group.ban.user.getList();
        System.out.println("group.ban.getList:  " + GroupBanResult.toString());

        /**
         * 
         * Remove the mute settings for a user in all groups
         */
        groupModel = new GroupModel()
                .setMembers(members);
        //Result groupRollBackGagUserResult =  group.ban.user.remove(groupModel);
        //System.out.println("group.ban.remove:  " + groupRollBackGagUserResult.toString());


    }
}
