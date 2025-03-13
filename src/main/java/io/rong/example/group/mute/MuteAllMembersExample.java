package io.rong.example.group.mute;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.group.mute.MuteAllMembers;
import io.rong.models.Result;
import io.rong.models.response.GroupMuteAllMembersCheckResult;
import io.rong.models.response.GroupMuteAllMembersListResult;

/**
 *
 * Example of Group Mute
 * @author RongCloud
 *
 * @version 3.0
 */
public class MuteAllMembersExample {
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
        // Custom API endpoint
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, CenterEnum.BJ);

        MuteAllMembers muteAllMembers = rongCloud.group.muteAllMembers;
        /**
         * API Documentation:
         * Add muted groups
         */
        String[] groupIds = {"ghJiu7H1","ghJiu7H2","ghJiu7H3","ghJiu7H4","ghJiu7H712","ghJiu7H6","ghJiu7H7","ghJiu7H8","ghJiu7H9","ghJiu7H10","ghJiu7H11","ghJiu7H12","ghJiu7H13","ghJiu7H14","ghJiu7H15","ghJiu7H16","ghJiu7H12","ghJiu7H18"};
        Result result = muteAllMembers.add(groupIds);
        System.out.println("group.muteAllMembers.add:  " + result.toString());

        /**
         * 
         * Query all muted groups
         */
        GroupMuteAllMembersListResult GroupMuteAllMembersResult = (GroupMuteAllMembersListResult)muteAllMembers.getList();
        System.out.println("group.muteAllMembers.getList:  " + GroupMuteAllMembersResult.toString());

        /**
         * 
         * Check method for muted groups
         */
        GroupMuteAllMembersCheckResult GroupBanCheckResult = (GroupMuteAllMembersCheckResult)muteAllMembers.check(groupIds);
        System.out.println("group.muteAllMembers.check:  " + GroupBanCheckResult.toString());

        /**
         * 
         * Remove method for muted groups
         */
        Result groupMuteAllMembersResult = muteAllMembers.remove(groupIds);
        System.out.println("group.muteAllMembers.remove:  " + groupMuteAllMembersResult.toString());


    }
}
