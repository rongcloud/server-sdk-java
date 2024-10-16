package io.rong.methods.profile;

import io.rong.RongCloud;
import io.rong.methods.BaseMethod;
import io.rong.models.CheckMethod;
import io.rong.models.profile.*;
import io.rong.models.response.*;
import org.apache.commons.lang3.StringUtils;

/**
 * 托管用户服务
 * docs https://docs.rongcloud.cn/platform-chat-api/im-server-api-list-v1
 */
public class EntrustUser extends BaseMethod {

    private static final String API_JSON_PATH = "profile/user";

    public EntrustUser(String appKey, String appSecret, RongCloud rongCloud) {
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
     * 设置用户托管信息
     **/
    public ResponseResult setProfile(UserProfileModel profileModel) throws Exception {
        String method = CheckMethod.SET_USER_PROFILE;

        ResponseResult result = checkFiled(profileModel, method, ResponseResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "userId=", profileModel.getUserId());
        addFormParam(sb, "&userProfile=", profileModel.getUserProfile());
        addFormParam(sb, "&userExtProfile=", profileModel.getUserExtProfile());
        String body = sb.toString();

        return doRequest("/user/profile/set.json", body, method, ResponseResult.class);
    }


    /**
     * 批量查询用户资料
     **/
    public QueryUserProfilesResp batchQueryUserProfiles(String... userId) throws Exception {
        String method = CheckMethod.BATCH_QUERY_USER_PROFILES;

        QueryUserProfilesResp result = checkParam("userId", userId, method, QueryUserProfilesResp.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "userId=", StringUtils.join(removeDuplicates(userId), ","));
        String body = sb.toString();

        return doRequest("/user/profile/batch/query.json", body, method, QueryUserProfilesResp.class);
    }


    /**
     * 用户托管信息清除
     **/
    public CheckFriendsResult cleanProfile(String... userId) throws Exception {
        String method = CheckMethod.CLEAN_PROFILE;

        CheckFriendsResult result = checkParam("userId", userId, method, CheckFriendsResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "userId=", StringUtils.join(removeDuplicates(userId), ","));
        String body = sb.toString();

        return doRequest("/user/profile/clean.json", body, method, CheckFriendsResult.class);
    }


    /**
     * 批量查询用户资料
     **/
    public QueryUserProfilesResp pagingQueryUserProfiles(PagingQueryUserProfilesModel input) throws Exception {
        String method = CheckMethod.PAGING_QUERY_USER_PROFILES;

        QueryUserProfilesResp result = checkFiled(input, method, QueryUserProfilesResp.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "page=", input.getPage());
        addFormParam(sb, "&size=", input.getSize());
        addFormParam(sb, "&order=", input.getOrder());
        String body = sb.toString();

        return doRequest("/user/profile/query.json", body, method, QueryUserProfilesResp.class);
    }






}