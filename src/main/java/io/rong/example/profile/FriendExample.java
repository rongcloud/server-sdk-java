package io.rong.example.profile;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.profile.Friend;
import io.rong.models.profile.FriendModel;
import io.rong.models.profile.FriendProfileModel;
import io.rong.models.profile.PagingGetFriendsModel;
import io.rong.models.response.CheckFriendsResult;
import io.rong.models.response.GetPermissionResult;
import io.rong.models.response.QueryFriendsResult;
import io.rong.models.response.ResponseResult;

/**
 * 群托管服务
 * <p>
 * API 文档: https://docs.rongcloud.cn/platform-chat-api/im-server-api-list-v1
 *
 * @author RongCloud
 * @version 3.6.0
 */
public class FriendExample {

    /**
     * 此处替换成您的appKey
     */
    private static final String APP_KEY = "appKey";
    /**
     * 此处替换成您的appSecret
     */
    private static final String APP_SECRET = "secret";
    /**
     * 初始化
     */
    private static Friend friend = RongCloud.getInstance(APP_KEY, APP_SECRET, CenterEnum.BJ).friend;

    /**
     * 本地调用测试
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        //添加好友
        addFriendExample();

        //删除好友
        delFriendExample();

        //清除指定用户好友
        cleanFriendExample();

        // 设置好友资料
        setProfileExample();

        // 分页获取好友信息列表
        pagingGetFriendsExample();

        // 检查好友关系
        checkFriendsExample();


        //设置添加好友权限
        setPermissionExample();


        //查询添加好友权限
        getPermissionExample();

    }
    /**
     * 查询添加好友权限
     */
    private static void getPermissionExample() throws Exception {
        GetPermissionResult responseResult = friend.getPermission( "uid", "uid2");
        System.out.println(responseResult);
    }


    /**
     * 设置添加好友权限
     */
    private static void setPermissionExample() throws Exception {
        ResponseResult responseResult = friend.setPermission(1, "uid", "uid2");
        System.out.println(responseResult);
    }




    /**
     * 检查好友关系
     */
    private static void checkFriendsExample() throws Exception {
        CheckFriendsResult responseResult = friend.checkFriends("uid", "targetId", "targetId2");
        System.out.println(responseResult);
    }


    /**
     * 分页获取好友信息
     */
    private static void pagingGetFriendsExample() throws Exception {
        PagingGetFriendsModel getFriendsModel = new PagingGetFriendsModel();
        getFriendsModel.setUserId("userId");
        getFriendsModel.setOrder(1);
        getFriendsModel.setPageToken("");
        getFriendsModel.setSize(1);
        QueryFriendsResult responseResult = friend.pagingGetFriends(getFriendsModel);
        System.out.println(responseResult);
    }


    /**
     * 添加好友
     */
    private static void addFriendExample() throws Exception {
        FriendModel friendModel = new FriendModel();
        friendModel.setUserId("userId");
        friendModel.setTargetId("targetId");
        friendModel.setOptType(1);
        friendModel.setExtra("extra");
        friendModel.setDirectionType(1);
        ResponseResult responseResult = friend.add(friendModel);
        System.out.println(responseResult);
    }

    /**
     * 删除好友
     */
    private static void delFriendExample() throws Exception {
        ResponseResult responseResult = friend.delete("uid", "targetId", "targetId2");
        System.out.println(responseResult);
    }


    /**
     * 清除指定用户好友关系
     */
    private static void cleanFriendExample() throws Exception {
        ResponseResult responseResult = friend.clean("uid");
        System.out.println(responseResult);
    }


    /**
     * 设置好友资料
     */
    private static void setProfileExample() throws Exception {
        FriendProfileModel friendProfileModel = new FriendProfileModel();
        friendProfileModel.setUserId("userId");
        friendProfileModel.setTargetId("targetId");
        friendProfileModel.setRemarkName("remarkName");
        friendProfileModel.setFriendExtProfile("friendExtProfile");
        ResponseResult responseResult = friend.setProfile(friendProfileModel);
        System.out.println(responseResult);
    }


}