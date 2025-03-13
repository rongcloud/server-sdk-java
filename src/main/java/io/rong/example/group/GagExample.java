package io.rong.example.group;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.group.gag.Gag;
import io.rong.models.Result;
import io.rong.models.group.GroupMember;
import io.rong.models.group.GroupModel;
import io.rong.models.response.ListGagGroupUserResult;
/**
 * Example for muting group members
 * @author RongCloud
 *
 * @version 3.0.0
 */
public class GagExample {
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

        Gag Gag = rongCloud.group.gag;
        /**
         * 
         * Method to mute group members
         */

        GroupMember[] members = {new GroupMember().setId("ghJiu7H1"),new GroupMember().setId("ghJiu7H2")};

        GroupModel group = new GroupModel()
                .setId("groupId")
                .setMembers(members)
                .setMinute(5);
        Result result = Gag.add(group);
        System.out.println("group.gag.add:  " + result.toString());

        /**
         * 
         * Query muted group members
         */
        ListGagGroupUserResult groupLisGagUserResult = Gag.getList("groupId");
        System.out.println("group.gag.getList:  " + groupLisGagUserResult.toString());

        /**
         * 
         * Remove muted group members
         */
        group = new GroupModel()
                .setId("groupId")
                .setMembers(members);

        Result groupRollBackGagUserResult = Gag.remove(group);
        System.out.println("group.gag.remove:  " + groupRollBackGagUserResult.toString());

    }
}
