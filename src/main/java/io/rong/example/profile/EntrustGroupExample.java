package io.rong.example.profile;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.profile.EntrustGroup;
import io.rong.models.profile.*;
import io.rong.models.response.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Group Information Hosting Service
 * <p>
 * API Documentation: https://docs.rongcloud.cn/platform-chat-api/im-server-api-list-v1
 *
 * @author RongCloud
 * @version 3.6.0
 */
public class EntrustGroupExample {

    /**
     * Replace with your App Key
     */
    private static final String APP_KEY = "appKey";
    /**
     * Replace with your App Secret
     */
    private static final String APP_SECRET = "appSecret";

    /**
     * Initialization
     */
    private static EntrustGroup entrustGroup = RongCloud.getInstance(APP_KEY, APP_SECRET, CenterEnum.BJ).entrustGroup;


    /**
     * Local Test
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        // Create a Hosted Group
        createExample();

        // Update Hosted Group Information
        updateExample();


        // Query group profiles
        queryProfilesExample();

        // Exit a group
        quitExample();

        // Kick out a member from a group
        kickOutExample();

        // Kick out a user from all groups
        kickOutAllGroupExample();

        // Dismiss a group
        dismissExample();

        // Join a group
        joinExample();

        // Transfer group ownership
        transferOwnerExample();

        // Import hosted groups
        importGroupExample();

        // Set group administrators
        setManagersExample();

        // Remove group administrators
        removeManagersExample();

        // Paginate group members
        pagingQueryMembersExample();

        // Query specific group members
        queryMembersByUserIdsExample();

        // Set group member information
        setMemberInfoExample();

        // Set group alias
        setGroupRemarkName();

        // Delete group alias
        delGroupRemarkName();

        // Query group alias
        queryGroupRemarkName();

        // Follow group members
        followMemberExample();


// Unfollow a group member
        unfollowMemberExample();

// Query followed group members
        getFollowedMemberExample();


// Paginate query group information
        pagingQueryGroupsExample();

// Paginate query joined group information
        pagingQueryJoinedGroupsExample();
    }


    /**
     * Paginate query joined group information
     */
    private static void pagingQueryJoinedGroupsExample() throws Exception {
        QueryJoinedGroupsModel pageModel = new QueryJoinedGroupsModel();
        pageModel.setUserId("uid1");
        pageModel.setRole(2);
        pageModel.setOrder(1);
        pageModel.setSize(20);
        pageModel.setPageToken("");
        PagingQueryJoinedGroupsResult result = entrustGroup.pagingQueryJoinedGroups(pageModel);
        System.out.println(result);
    }


    /**
     * Paginate query group information
     */
    private static void pagingQueryGroupsExample() throws Exception {
        PageModel pageModel = new PageModel();
        pageModel.setOrder(1);
        pageModel.setSize(20);
        pageModel.setPageToken("");
        ResponseResult result = entrustGroup.pagingQueryGroups(pageModel);
        System.out.println(result);
    }

    /**
     * Follow a group member
     */
    private static void followMemberExample() throws Exception {
        ResponseResult result = entrustGroup.followMember("gid", "uid1", "uid2", "uid3");
        System.out.println(result);
    }


    /**
     * Unfollow group members
     */
    private static void unfollowMemberExample() throws Exception {
        ResponseResult result = entrustGroup.unfollowMember("gid1", "uid1", "uid2", "uid3");
        System.out.println(result);
    }

    /**
     * Get followed group members
     */
    private static void getFollowedMemberExample() throws Exception {
        FollowedMemberResult result = entrustGroup.getFollowedMember("gid", "uid1");
        System.out.println(result);
    }


    /**
     * Query group alias
     */
    private static void queryGroupRemarkName() throws Exception {
        RemarkNameResult result = entrustGroup.queryRemarkName("gid", "uid1");
        System.out.println(result);
    }

    /**
     * Delete group alias
     */
    private static void delGroupRemarkName() throws Exception {
        ResponseResult result = entrustGroup.delRemarkName("gid", "uid1");
        System.out.println(result);
    }

    /**
     * Set group alias
     */
    private static void setGroupRemarkName() throws Exception {
        GroupRemarkNameModel remarkNameModel = new GroupRemarkNameModel();
        remarkNameModel.setGroupId("gid");
        remarkNameModel.setUserId("uid1");
        remarkNameModel.setRemarkName("name1");
        ResponseResult result = entrustGroup.setRemarkName(remarkNameModel);
        System.out.println(result);
    }


    /**
     * Set group member information
     */
    private static void setMemberInfoExample() throws Exception {
        MemberInfoModel memberInfoModel = new MemberInfoModel();
        memberInfoModel.setGroupId("gid");
        memberInfoModel.setUserId("uid1");
        memberInfoModel.setNickname("nickname1");
        memberInfoModel.setExtra("ext");
        ResponseResult result = entrustGroup.setMemberInfo(memberInfoModel);
        System.out.println(result);
    }

    /**
     * Query group administrators
     */
    private static void queryMembersByUserIdsExample() throws Exception {
        QueryMembersResult result = entrustGroup.queryMembersByUserIds("gid", "uid1", "uid2");
        System.out.println(result);
    }


    /**
     * Paginate query group members
     */
    private static void pagingQueryMembersExample() throws Exception {
        PagingQueryMembersModel pagingQueryMembersModel = new PagingQueryMembersModel();
        pagingQueryMembersModel.setGroupId("gid");
        pagingQueryMembersModel.setType(1);
        pagingQueryMembersModel.setPageToken("");
        pagingQueryMembersModel.setSize(10);
        pagingQueryMembersModel.setOrder(1);
        PagingQueryMembersResult result = entrustGroup.pagingQueryMembers(pagingQueryMembersModel);
        System.out.println(result);
    }


    /**
     * Set group administrators
     */
    private static void setManagersExample() throws Exception {
        SetManagersResult result = entrustGroup.addManagers("gid1", "uid1", "uid2");
        System.out.println(result);
    }


    /**
     * Remove group administrators
     */
    private static void removeManagersExample() throws Exception {
        ResponseResult result = entrustGroup.removeManagers("gid1", "uid1", "uid2", "uid2", "uid0");
        System.out.println(result);
    }

    /**
     * Import a hosted group
     */
    private static void importGroupExample() throws Exception {
        ImportEntrustGroupModel groupModel = new ImportEntrustGroupModel();

        Map<String, String> groupProfile = new HashMap<>();
        groupProfile.put("portraitUrl", "http://test_url");

        Map<String, String> groupExtProfile = new HashMap<>();
        groupExtProfile.put("ext_00", "vvv1");
        groupExtProfile.put("ext_11", "vvv2");

        Map<String, Integer> permissions = new HashMap<>();
        permissions.put("joinPerm", 1);

        groupModel.setGroupId("group1")
                .setName("groupName")
                .setOwner("user1")
                .setGroupProfile(groupProfile)
                .setGroupExtProfile(groupExtProfile)
                .setPermissions(permissions);

        ResponseResult responseResult = entrustGroup.importGroup(groupModel);
        System.out.println(responseResult);
    }


    /**
     * Transfer group ownership
     */
    private static void transferOwnerExample() throws Exception {
        ResponseResult result = entrustGroup.transferOwner(new TransferOwnerModel()
                .setGroupId("gid")
                .setNewOwner("uid1")
                .setIsDelBan(1)
                .setIsDelFollowed(1)
                .setIsDelWhite(1)
                .setIsQuit(1)
        );
        System.out.println(result);
    }


    /**
     * Join a group
     */
    private static void joinExample() throws Exception {
        JoinGroupResult result = entrustGroup.join("gid1", "uid1", "uid2");
        System.out.println(result);
    }


    /**
     * Dismiss a group
     */
    private static void dismissExample() throws Exception {
        ResponseResult result = entrustGroup.dismiss("gid1");
        System.out.println(result);
    }

    /**
     * Kick a user out of all groups
     */
    private static void kickOutAllGroupExample() throws Exception {
        ResponseResult result = entrustGroup.kickOutAllGroups("uid1");
        System.out.println(result);
    }

    /**
     * Kick users out of a group
     */
    private static void kickOutExample() throws Exception {
        KickOutEntrustGroupModel params = new KickOutEntrustGroupModel();
        params.setGroupId("group1");
        params.setIsDelBan(1);
        params.setIsDelFollowed(1);
        params.setIsDelWhite(1);
        params.setUserIds(new String[]{"uid1", "uid2"});
        ResponseResult result = entrustGroup.kickOut(params);
        System.out.println(result);
    }

    /**
     * Quit a group
     */
    private static void quitExample() throws Exception {
        QuitEntrustGroupModel params = new QuitEntrustGroupModel()
                .setGroupId("group1")
                .setIsDelBan(2)
                .setIsDelFollowed(1)
                .setIsDelWhite(1)
                .setUserIds(new String[]{"uid1", "uid2"});
        ResponseResult result = entrustGroup.quit(params);
        System.out.println(result);
    }


    /**
     * Query Hosted Group Profiles
     */
    private static void queryProfilesExample() throws Exception {
        QueryGroupProfilesResult result = entrustGroup.queryProfiles("group1", "group2");
        System.out.println(result);
    }

    /**
     * Create a Hosted Group
     */
    private static void createExample() throws Exception {
        CreateEntrustGroupModel groupModel = new CreateEntrustGroupModel();

        Map<String, String> groupProfile = new HashMap<>();
        groupProfile.put("portraitUrl", "http://test_url");

        Map<String, String> groupExtProfile = new HashMap<>();
        groupExtProfile.put("ext_00", "vvv1");
        groupExtProfile.put("ext_11", "vvv2");

        Map<String, Integer> permissions = new HashMap<>();
        permissions.put("joinPerm", 1);

        groupModel.setGroupId("group1")
                .setName("groupName")
                .setOwner("user1")
                .setGroupProfile(groupProfile)
                .setGroupExtProfile(groupExtProfile)
                .setPermissions(permissions);
        groupModel.setUserIds(new String[]{"uid1", "uid2"});

        ResponseResult responseResult = entrustGroup.create(groupModel);
        System.out.println(responseResult);
    }

    /**
     * Update Hosted Group Profiles
     */
    private static void updateExample() throws Exception {
        EntrustGroupModel groupModel = new EntrustGroupModel();
        Map<String, String> groupProfile = new HashMap<>();
        groupProfile.put("portraitUrl", "http://test_url");
        Map<String, String> groupExtProfile = new HashMap<>();
        groupExtProfile.put("ext_01", "v1");
        groupExtProfile.put("ext_02", "v2");
        Map<String, Integer> permissions = new HashMap<>();
        permissions.put("joinPerm", 1);
        groupModel.setGroupId("group1")
                .setName("groupName")
                .setOwner("user1")
                .setGroupProfile(groupProfile)
                .setGroupExtProfile(groupExtProfile)
                .setPermissions(permissions);

        ResponseResult responseResult = entrustGroup.updateProfile(groupModel);
        System.out.println(responseResult);
    }


}