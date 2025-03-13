package io.rong.example.group.mute;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.group.mute.whitelist.MuteWhiteList;
import io.rong.models.Result;
import io.rong.models.group.GroupMember;
import io.rong.models.group.GroupModel;
import io.rong.models.response.GroupBanWhitelistResult;
/**
 * Example for Group Mute Allowlist
 * @author RongCloud
 *
 * @version 3.0
 */
public class MuteWhitelistExample {
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
        //RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        // Custom API endpoint configuration
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, CenterEnum.BJ);

        MuteWhiteList muteWhiteList = rongCloud.group.muteWhiteList;

        /**
         * API Documentation:
         * Method to add users to the mute allowlist
         */

        GroupMember[] members = {new GroupMember().setId("ghJiu7H1"),new GroupMember().setId("ghJiu7H2")};

        GroupModel groupModel = new GroupModel()
                .setId("groupId")
                .setMembers(members);
        Result result = muteWhiteList.user.add(groupModel);
        System.out.println("group.muteWhiteList.add:  " + result.toString());

        /**
         * API Documentation:
         * Method to query the mute allowlist users
         */
        groupModel = new GroupModel()
                .setId("12");
        GroupBanWhitelistResult GroupBanResult = (GroupBanWhitelistResult) muteWhiteList.user.getList("groupId");
        System.out.println("group.muteWhiteList.getList:  " + GroupBanResult.toString());

        /**
         * API Documentation:
         * Method to remove a user from the mute allowlist
         */
        groupModel = new GroupModel()
                .setMembers(members)
                .setId("groupId");
        Result groupRollBackGagUserResult =  muteWhiteList.user.remove(groupModel);
        System.out.println("group.muteWhiteList.remove:  " + groupRollBackGagUserResult.toString());


    }
}
