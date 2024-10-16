package io.rong.methods.profile;

import io.rong.RongCloud;
import io.rong.methods.BaseMethod;
import io.rong.models.CheckMethod;
import io.rong.models.profile.FriendModel;
import io.rong.models.profile.FriendProfileModel;
import io.rong.models.profile.PagingGetFriendsModel;
import io.rong.models.response.CheckFriendsResult;
import io.rong.models.response.GetPermissionResult;
import io.rong.models.response.QueryFriendsResult;
import io.rong.models.response.ResponseResult;
import org.apache.commons.lang3.StringUtils;

/**
 * 托管群组服务
 * docs https://docs.rongcloud.cn/platform-chat-api/im-server-api-list-v1
 */
public class Friend extends BaseMethod {

    private static final String API_JSON_PATH = "profile/friend";

    public Friend(String appKey, String appSecret, RongCloud rongCloud) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.rongCloud = rongCloud;
        initPath();
    }

    @Override
    protected void initPath() {
        super.path = API_JSON_PATH;
    }

    /**
     * 添加好友
     **/
    public ResponseResult add(FriendModel friend) throws Exception {
        String method = CheckMethod.ADD_FRIEND;

        ResponseResult result = checkFiled(friend, method, ResponseResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "userId=", friend.getUserId());
        addFormParam(sb, "&targetId=", friend.getTargetId());
        addFormParam(sb, "&optType=", friend.getOptType());
        addFormParam(sb, "&extra=", friend.getExtra());
        String body = sb.toString();

        return doRequest("/friend/add.json", body, method, ResponseResult.class);
    }


    /**
     * 删除好友
     **/
    public ResponseResult delete(String userId, String... targetIds) throws Exception {
        String method = CheckMethod.DEL_FRIEND;

        ResponseResult result = checkParam("userId", userId, method, ResponseResult.class);
        if (result != null) {
            return result;
        }
        result = checkParam("targetIds", targetIds, method, ResponseResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "userId=", userId);
        addFormParam(sb, "&targetIds=", StringUtils.join(removeDuplicates(targetIds), ","));
        String body = sb.toString();

        return doRequest("/friend/delete.json", body, method, ResponseResult.class);
    }

    /**
     * 删除好友
     **/
    public ResponseResult clean(String userId) throws Exception {
        String method = CheckMethod.CLEAN_FRIEND;

        ResponseResult result = checkParam("userId", userId, method, ResponseResult.class);
        if (result != null) {
            return result;
        }
        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "userId=", userId);
        String body = sb.toString();

        return doRequest("/friend/clean.json", body, method, ResponseResult.class);
    }

    /**
     * 设置好友信息
     **/
    public ResponseResult setProfile(FriendProfileModel profileModel) throws Exception {
        String method = CheckMethod.SET_FRIEND_PROFILE;

        ResponseResult result = checkFiled(profileModel, method, ResponseResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "userId=", profileModel.getUserId());
        addFormParam(sb, "&targetId=", profileModel.getTargetId());
        addFormParam(sb, "&remarkName=", profileModel.getRemarkName());
        addFormParam(sb, "&friendExtProfile=", profileModel.getFriendExtProfile());
        String body = sb.toString();

        return doRequest("/friend/profile/set.json", body, method, ResponseResult.class);
    }


    /**
     * 分页获取好友信息
     **/
    public QueryFriendsResult pagingGetFriends(PagingGetFriendsModel getFriendsModel) throws Exception {
        String method = CheckMethod.PAGING_GET_FRIENDS;

        QueryFriendsResult result = checkFiled(getFriendsModel, method, QueryFriendsResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "userId=", getFriendsModel.getUserId());
        addFormParam(sb, "&pageToken=", getFriendsModel.getPageToken());
        addFormParam(sb, "&size=", getFriendsModel.getSize());
        addFormParam(sb, "&order=", getFriendsModel.getOrder());
        String body = sb.toString();

        return doRequest("/friend/get.json", body, method, QueryFriendsResult.class);
    }


    /**
     * 检查好友关系
     **/
    public CheckFriendsResult checkFriends(String userId, String... targetIds) throws Exception {
        String method = CheckMethod.CHECK_FRIENDS;

        CheckFriendsResult result = checkParam("userId", userId, method, CheckFriendsResult.class);
        if (result != null) {
            return result;
        }
        result = checkParam("targetIds", targetIds, method, CheckFriendsResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "userId=", userId);
        addFormParam(sb, "&targetIds=", StringUtils.join(removeDuplicates(targetIds), ","));
        String body = sb.toString();

        return doRequest("/friend/check.json", body, method, CheckFriendsResult.class);
    }


    /**
     * 设置添加好友权限
     **/
    public ResponseResult setPermission(Integer permissionType, String... userIds) throws Exception {
        String method = CheckMethod.SET_PERM;

        ResponseResult result = checkParam("userIds", userIds, method, ResponseResult.class);
        if (result != null) {
            return result;
        }
        result = checkParam("permissionType", permissionType, method, ResponseResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "userIds=", StringUtils.join(removeDuplicates(userIds), ","));
        addFormParam(sb, "&permissionType=", permissionType);
        String body = sb.toString();

        return doRequest("/friend/permission/set.json", body, method, ResponseResult.class);
    }


    /**
     * 获取加好友权限
     **/
    public GetPermissionResult getPermission(String... userIds) throws Exception {
        String method = CheckMethod.GET_PERM;

        GetPermissionResult result = checkParam("userIds", userIds, method, GetPermissionResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "userIds=", StringUtils.join(removeDuplicates(userIds), ","));
        String body = sb.toString();

        return doRequest("/friend/permission/get.json", body, method, GetPermissionResult.class);
    }


}