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
 * Group Information Hosting Service
 * <p>
 *
 * @author RongCloud
 * @version 3.6.0
 */
public class FriendExample {

    /**
     * Replace with your App Key
     */
    private static final String APP_KEY = "appKey";
    /**
     * Replace with your App Secret
     */
    private static final String APP_SECRET = "secret";
    /**
     * Initialization
     */
    private static Friend friend = RongCloud.getInstance(APP_KEY, APP_SECRET, CenterEnum.BJ).friend;

    /**
     * Local test call
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        // Add a friend
        addFriendExample();

        // Delete a friend
        delFriendExample();


        // Clear the friend list for a specified user
        cleanFriendExample();

        // Set friend profile
        setProfileExample();

        // Get friend information list with pagination
        pagingGetFriendsExample();

        // Check friend relationship
        checkFriendsExample();

        // Set friend request permission
        setPermissionExample();

        // Query friend request permission
        getPermissionExample();

    }
    /**
     * Query friend request permission
     */
    private static void getPermissionExample() throws Exception {
        GetPermissionResult responseResult = friend.getPermission("uid", "uid2");
        System.out.println(responseResult);
    }


    /**
     * Set friend request permission
     */
    private static void setPermissionExample() throws Exception {
        ResponseResult responseResult = friend.setPermission(1, "uid", "uid2");
        System.out.println(responseResult);
    }




    /**
     * Check friend relationship
     */
    private static void checkFriendsExample() throws Exception {
        CheckFriendsResult responseResult = friend.checkFriends("uid", "targetId", "targetId2");
        System.out.println(responseResult);
    }


    /**
     * Paginate to get friend information
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
     * Add a friend
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
     * Delete a friend
     */
    private static void delFriendExample() throws Exception {
        ResponseResult responseResult = friend.delete("uid", "targetId", "targetId2");
        System.out.println(responseResult);
    }


    /**
     * Clear friend relationships for a specified user
     */
    private static void cleanFriendExample() throws Exception {
        ResponseResult responseResult = friend.clean("uid");
        System.out.println(responseResult);
    }


    /**
     * Set friend profile
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