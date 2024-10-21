package io.rong.example.profile;

import io.rong.RongCloud;
import io.rong.RongCloudConfig;
import io.rong.methods.profile.EntrustGroup;
import io.rong.models.profile.*;
import io.rong.models.response.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 群托管服务
 * <p>
 * API 文档: https://docs.rongcloud.cn/platform-chat-api/im-server-api-list-v1
 *
 * @author RongCloud
 * @version 3.6.0
 */
public class EntrustGroupExample {

    /**
     * 此处替换成您的appKey
     */
    private static final String APP_KEY = "appKey";
    /**
     * 此处替换成您的appSecret
     */
    private static final String APP_SECRET = "appSecret";

    /**
     * 初始化
     */
    private static EntrustGroup entrustGroup = RongCloud.getInstance(APP_KEY, APP_SECRET, RongCloudConfig.DefaultConfig).entrustGroup;


    /**
     * 本地调用测试
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        // 创建托管群组
        createExample();

        // 托管群组资料设置
        updateExample();

         //托管群组资料查询
        queryProfilesExample();

        // 退出群组
        quitExample();

        // 踢出群组
        kickOutExample();

        // 将用户踢出所有群组
        kickOutAllGroupExample();

        // 解散群组
        dismissExample();


        // 加入群组
        joinExample();

        // 群主转移
        transferOwnerExample();

        // 导入托管群组
        importGroupExample();

        // 设置群管理员
        setManagersExample();
        // 移除群管理员
        removeManagersExample();

        // 分页查询群成员
        pagingQueryMembersExample();

        // 查询指定群成员
        queryMembersByUserIdsExample();


        // 设置群成员信息
        setMemberInfoExample();

        //设置群备注名
        setGroupRemarkName();

        //删除群备注名
        delGroupRemarkName();

        //设置群备注名
        queryGroupRemarkName();

        // 关注群成员
        followMemberExample();


        // 取消关注群成员
        unfollowMemberExample();

        // 查询关注群成员
        getFollowedMemberExample();


        //分页查询群组信息
        pagingQueryGroupsExample();

        //分页查询加入群组资料
        pagingQueryJoinedGroupsExample();
    }


    /**
     * 分页查询加入群组资料
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
     * 分页查询群组信息
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
     * 关注群成员
     */
    private static void followMemberExample() throws Exception {
        ResponseResult result = entrustGroup.followMember("gid", "uid1", "uid2", "uid3");
        System.out.println(result);
    }


    /**
     * 取消关注群成员
     */
    private static void unfollowMemberExample() throws Exception {
        ResponseResult result = entrustGroup.unfollowMember("gid1", "uid1", "uid2", "uid3");
        System.out.println(result);
    }

    /**
     * 获取关注群成员
     */
    private static void getFollowedMemberExample() throws Exception {
        FollowedMemberResult result = entrustGroup.getFollowedMember("gid", "uid1");
        System.out.println(result);
    }


    /**
     * 查询群备注名
     */
    private static void queryGroupRemarkName() throws Exception {
        RemarkNameResult result = entrustGroup.queryRemarkName("gid", "uid1");
        System.out.println(result);
    }

    /**
     * 删除群备注名
     */
    private static void delGroupRemarkName() throws Exception {
        ResponseResult result = entrustGroup.delRemarkName("gid", "uid1");
        System.out.println(result);
    }

    /**
     * 设置群备注名
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
     * 设置群成员信息
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
     * 查询群管理员
     */
    private static void queryMembersByUserIdsExample() throws Exception {
        QueryMembersResult result = entrustGroup.queryMembersByUserIds("gid", "uid1", "uid2");
        System.out.println(result);
    }


    /**
     * 分页查询群成员
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
     * 设置群管理员
     */
    private static void setManagersExample() throws Exception {
        SetManagersResult result = entrustGroup.addManagers("gid1", "uid1", "uid2");
        System.out.println(result);
    }


    /**
     * 移除群管理员
     */
    private static void removeManagersExample() throws Exception {
        ResponseResult result = entrustGroup.removeManagers("gid1", "uid1", "uid2", "uid2", "uid0");
        System.out.println(result);
    }

    /**
     * 导入托管群组
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
     * 转让群组
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
     * 加入群组
     */
    private static void joinExample() throws Exception {
        JoinGroupResult result = entrustGroup.join("gid1", "uid1", "uid2");
        System.out.println(result);
    }


    /**
     * 解散群组
     */
    private static void dismissExample() throws Exception {
        ResponseResult result = entrustGroup.dismiss("gid1");
        System.out.println(result);
    }

    /**
     * 指定用户踢出所有群组
     */
    private static void kickOutAllGroupExample() throws Exception {
        ResponseResult result = entrustGroup.kickOutAllGroups("uid1");
        System.out.println(result);
    }

    /**
     * 踢出群组
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
     * 退出群组
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
     * 托管群组资料查询
     */
    private static void queryProfilesExample() throws Exception {
        QueryGroupProfilesResult result = entrustGroup.queryProfiles("group1", "group2");
        System.out.println(result);
    }

    /**
     * 创建托管群组
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
     * 托管群组资料设置
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