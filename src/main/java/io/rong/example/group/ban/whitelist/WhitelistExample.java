package io.rong.example.group.ban.whitelist;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.group.Group;
import io.rong.models.Result;
import io.rong.models.group.GroupMember;
import io.rong.models.group.GroupModel;
import io.rong.models.response.GroupBanWhitelistResult;

public class WhitelistExample {
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
         * API Documentation:
         * Add users to the mute exceptions list
         */

        GroupMember[] members = {new GroupMember().setId("ghJiu7H1"),new GroupMember().setId("ghJiu7H2")};

        GroupModel groupModel = new GroupModel()
                .setId("groupId")
                .setMembers(members);
        Result result = group.ban.whitelist.user.add(groupModel);
        System.out.println("group.ban.add:  " + result.toString());

        /**
         * API Documentation:
         * Query users in the mute exceptions list
         */
        groupModel = new GroupModel()
                .setId("12");
        GroupBanWhitelistResult GroupBanResult = (GroupBanWhitelistResult) group.ban.whitelist.user.getList("groupId");
        System.out.println("group.ban.getList:  " + GroupBanResult.toString());

        /**
         * API Documentation:
         * Method to remove a user from the Mute Exceptions list
         */
        groupModel = new GroupModel()
                .setMembers(members)
                .setId("groupId");
        Result groupRollBackGagUserResult =  group.ban.whitelist.user.remove(groupModel);
        System.out.println("group.ban.remove:  " + groupRollBackGagUserResult.toString());


    }
}
