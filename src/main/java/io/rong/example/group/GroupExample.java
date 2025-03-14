package io.rong.example.group;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.group.Group;
import io.rong.models.Result;
import io.rong.models.group.GroupMember;
import io.rong.models.group.GroupModel;
import io.rong.models.group.UserGroup;
import io.rong.models.response.GroupUserQueryResult;

/**
 * Group service example
 * @author RongCloud
 *
 * @version 3.0.0
 */
public class GroupExample {
    /**
     * Replace with your App Key
     */
    private static final String appKey = "appKey";
    /**
     * Replace with your App Secret
     */
    private static final String appSecret = "appSecret";

    /**
     * Local test
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, CenterEnum.BJ);
        // Custom API endpoint
        // RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, api);

        Group Group = rongCloud.group;

        /**
         * 
         *
         * Create group method
         *
         */
        GroupMember[] members = {new GroupMember().setId("ghJiu7H1"), new GroupMember().setId("ghJiu7H2")};


        GroupModel group = new GroupModel()
                .setId("groupId")
                .setMembers(members)
                .setName("groupName");
        Result groupCreateResult = (Result) Group.create(group);
        System.out.println("group create result:  " + groupCreateResult.toString());

        /**
         * 
         *
         * Synchronize user's group list method
         */

        GroupModel group1 = new GroupModel()
                .setId("groupId1")
                .setName("groupName1");
        GroupModel group2 = new GroupModel()
                .setId("groupId2")
                .setName("groupName2");
        GroupModel[] groups = {group1, group2};
        UserGroup user = new UserGroup()
                .setId("jhkoi90jj")
                .setGroups(groups);

        Result syncResult = (Result) Group.sync(user);
        System.out.println("group sync:  " + syncResult.toString());


        /**
         *
         * 
         * Refresh group information method
         */
        //GroupMember[] members = {new GroupMember().setId("ghJiu7H1"),new GroupMember().setId("ghJiu7H2")};

        group = new GroupModel()
                .setId("groupId")
                .setName("groupName");
        Result refreshResult = (Result) Group.update(group);
        System.out.println("refresh:  " + refreshResult.toString());

        /**
         *
         *
         * Invite users to join a group
         *
         */
        group = new GroupModel()
                .setId("hdiuj87jj")
                .setMembers(members)
                .setName("groupName");
        Result groupInviteResult = (Result) rongCloud.group.invite(group);
        System.out.println("invite:  " + groupInviteResult.toString());

        /**
         * 
         *
         * User joins a specified group
         *
         */
        group = new GroupModel()
                .setId("groupId")
                .setMembers(members)
                .setName("groupName");
        Result groupJoinResult = (Result) Group.join(group);
        System.out.println("join:  " + groupJoinResult.toString());

        /**
         * 
         *
         * Query group members method
         *
         */
        group = new GroupModel().setId("groupId");
        GroupUserQueryResult getMemberesult = Group.get(group);
        System.out.println("group getMember:  " + getMemberesult.toString());

        /**
         * 
         *
         * Quit group
         *
         */
        group = new GroupModel()
                .setId("groupId")
                .setMembers(members)
                .setName("groupName");
        Result groupQuitResult = (Result) Group.quit(group);
        System.out.println("quit:  " + groupQuitResult.toString());

        /**
         *
         * 
         *
         * Dismiss group
         *
         */
        members = new GroupMember[]{new GroupMember().setId("ghJiu7H1")};

        group = new GroupModel()
                .setId("groupId")
                .setMembers(members);
        Result groupDismissResult = (Result) Group.dismiss(group);
        System.out.println("dismiss:  " + groupDismissResult.toString());

	 }

}