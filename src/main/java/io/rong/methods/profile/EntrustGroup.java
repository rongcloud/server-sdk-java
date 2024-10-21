package io.rong.methods.profile;

import io.rong.RongCloud;
import io.rong.methods.BaseMethod;
import io.rong.models.CheckMethod;
import io.rong.models.profile.*;
import io.rong.models.response.*;
import org.apache.commons.lang3.StringUtils;

/**
 * 托管群组服务
 * docs https://docs.rongcloud.cn/platform-chat-api/im-server-api-list-v1
 */
public class EntrustGroup extends BaseMethod {

    private static final String API_JSON_PATH = "profile/group";

    public EntrustGroup(String appKey, String appSecret, RongCloud rongCloud) {
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
     * 创建托管群组
     **/
    public ResponseResult create(CreateEntrustGroupModel group) throws Exception {
        String method = CheckMethod.CREATE;

        ResponseResult result = checkFiled(group, method, ResponseResult.class);
        if (result != null) {
            return result;
        }
        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "groupId=", group.getGroupId());
        addFormParam(sb, "&name=", group.getName());
        addFormParam(sb, "&owner=", group.getOwner());
        addFormParam(sb, "&userIds=", StringUtils.join(removeDuplicates(group.getUserIds()), ","));
        addFormParam(sb, "&groupProfile=", group.getGroupProfile());
        addFormParam(sb, "&groupExtProfile=", group.getGroupExtProfile());
        addFormParam(sb, "&permissions=", group.getPermissions());
        String body = sb.toString();

        return doRequest("/entrust/group/create.json", body, method, ResponseResult.class);
    }


    /**
     * 更新托管群组
     */
    public ResponseResult updateProfile(EntrustGroupModel group) throws Exception {
        String method = CheckMethod.UPDATE;

        ResponseResult result = checkFiled(group, method, ResponseResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "groupId=", group.getGroupId());
        addFormParam(sb, "&name=", group.getName());
        addFormParam(sb, "&groupProfile=", group.getGroupProfile());
        addFormParam(sb, "&groupExtProfile=", group.getGroupExtProfile());
        addFormParam(sb, "&permissions=", group.getPermissions());
        String body = sb.toString();

        return doRequest("/entrust/group/profile/update.json", body, method, ResponseResult.class);
    }


    /**
     * 获取托管群组资料
     */
    public QueryGroupProfilesResult queryProfiles(String... groupIds) throws Exception {
        String method = CheckMethod.QUERY_PROFILE;

        QueryGroupProfilesResult result = checkParam("groupIds", groupIds, method, QueryGroupProfilesResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "groupIds=", StringUtils.join(removeDuplicates(groupIds), ","));
        String body = sb.toString();

        return doRequest("/entrust/group/profile/query.json", body, method, QueryGroupProfilesResult.class);
    }

    public ResponseResult quit(QuitEntrustGroupModel params) throws Exception {
        String method = CheckMethod.QUIT;

        ResponseResult result = checkFiled(params, method, ResponseResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "groupId=", params.getGroupId());
        addFormParam(sb, "&userIds=", StringUtils.join(removeDuplicates(params.getUserIds()), ","));
        addFormParam(sb, "&isDelBan=", params.getIsDelBan());
        addFormParam(sb, "&isDelWhite=", params.getIsDelWhite());
        addFormParam(sb, "&isDelFollowed=", params.getIsDelFollowed());
        String body = sb.toString();

        return doRequest("/entrust/group/quit.json", body, method, ResponseResult.class);
    }

    /**
     * 踢出群组
     */
    public ResponseResult kickOut(KickOutEntrustGroupModel params) throws Exception {
        String method = CheckMethod.KICK;

        ResponseResult result = checkFiled(params, method, ResponseResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "groupId=", params.getGroupId());
        addFormParam(sb, "&userIds=", StringUtils.join(removeDuplicates(params.getUserIds()), ","));
        addFormParam(sb, "&isDelBan=", params.getIsDelBan());
        addFormParam(sb, "&isDelWhite=", params.getIsDelWhite());
        addFormParam(sb, "&isDelFollowed=", params.getIsDelFollowed());
        String body = sb.toString();

        return doRequest("/entrust/group/member/kick.json", body, method, ResponseResult.class);
    }

    /**
     * 指定用户踢出所有群组
     */
    public ResponseResult kickOutAllGroups(String userId) throws Exception {
        String method = CheckMethod.KICK_ALL;

        ResponseResult result = checkParam("userId", userId, method, ResponseResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "userId=", userId);
        String body = sb.toString();

        return doRequest("/entrust/group/member/kick/all.json", body, method, ResponseResult.class);
    }


    /**
     * 解散群组
     */
    public ResponseResult dismiss(String groupId) throws Exception {
        String method = CheckMethod.DISMISS;

        ResponseResult result = checkParam("groupId", groupId, method, ResponseResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "groupId=", groupId);
        String body = sb.toString();

        return doRequest("/entrust/group/dismiss.json", body, method, ResponseResult.class);
    }


    /**
     * 加入群组
     */
    public JoinGroupResult join(String groupId, String... userIds) throws Exception {
        String method = CheckMethod.JOIN;

        JoinGroupResult result = checkParam("groupId", groupId, method, JoinGroupResult.class);
        if (result != null) {
            return result;
        }

        result = checkParam("userIds", userIds, method, JoinGroupResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "groupId=", groupId);
        addFormParam(sb, "&userIds=", StringUtils.join(removeDuplicates(userIds), ","));
        String body = sb.toString();

        return doRequest("/entrust/group/join.json", body, method, JoinGroupResult.class);
    }


    /**
     * 转让群组
     */
    public ResponseResult transferOwner(TransferOwnerModel params) throws Exception {
        String method = CheckMethod.TRANS_OWNER;

        ResponseResult result = checkFiled(params, method, ResponseResult.class);
        if (result != null) {
            return result;
        }


        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "groupId=", params.getGroupId());
        addFormParam(sb, "&newOwner=", params.getNewOwner());
        addFormParam(sb, "&isDelBan=", params.getIsDelBan());
        addFormParam(sb, "&isDelWhite=", params.getIsDelWhite());
        addFormParam(sb, "&isDelFollowed=", params.getIsDelFollowed());
        addFormParam(sb, "&isQuit=", params.getIsQuit());
        String body = sb.toString();

        return doRequest("/entrust/group/transfer/owner.json", body, method, ResponseResult.class);
    }


    /**
     * 群组托管导入
     */
    public ResponseResult importGroup(ImportEntrustGroupModel group) throws Exception {
        String method = CheckMethod.IMPORT;

        ResponseResult result = checkFiled(group, method, ResponseResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "groupId=", group.getGroupId());
        addFormParam(sb, "&name=", group.getName());
        addFormParam(sb, "&owner=", group.getOwner());
        addFormParam(sb, "&groupProfile=", group.getGroupProfile());
        addFormParam(sb, "&groupExtProfile=", group.getGroupExtProfile());
        addFormParam(sb, "&permissions=", group.getPermissions());
        String body = sb.toString();

        return doRequest("/entrust/group/import.json", body, method, ResponseResult.class);
    }

    /**
     * 设置群管理员
     */
    public SetManagersResult addManagers(String groupId, String... userIds) throws Exception {
        String method = CheckMethod.GROUP_MANAGER;

        SetManagersResult result = checkParam("groupId", groupId, method, SetManagersResult.class);
        if (result != null) {
            return result;
        }

        result = checkParam("userIds", userIds, method, SetManagersResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "groupId=", groupId);
        addFormParam(sb, "&userIds=", StringUtils.join(removeDuplicates(userIds), ","));
        String body = sb.toString();

        return doRequest("/entrust/group/manager/add.json", body, method, SetManagersResult.class);
    }

    /**
     * 移除群管理员
     */
    public ResponseResult removeManagers(String groupId, String... userIds) throws Exception {
        String method = CheckMethod.GROUP_MANAGER;

        ResponseResult result = checkParam("groupId", groupId, method, ResponseResult.class);
        if (result != null) {
            return result;
        }

        result = checkParam("userIds", userIds, method, ResponseResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "groupId=", groupId);
        addFormParam(sb, "&userIds=", StringUtils.join(removeDuplicates(userIds), ","));
        String body = sb.toString();

        return doRequest("/entrust/group/manager/remove.json", body, method, ResponseResult.class);
    }



    /**
     * 分页获取群成员信息
     */
    public PagingQueryMembersResult pagingQueryMembers(PagingQueryMembersModel pageQuery) throws Exception {
        String method = CheckMethod.PAGING_QUERY_MEMBERS;

        PagingQueryMembersResult result = checkFiled(pageQuery, method, PagingQueryMembersResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "groupId=", pageQuery.getGroupId());
        addFormParam(sb, "&type=", pageQuery.getType());
        addFormParam(sb, "&pageToken=", pageQuery.getPageToken());
        addFormParam(sb, "&size=", pageQuery.getSize());
        addFormParam(sb, "&order=", pageQuery.getOrder());
        String body = sb.toString();

        return doRequest("/entrust/group/member/query.json", body, method, PagingQueryMembersResult.class);
    }


    /**
     * 获取指定群成员信息
     */
    public QueryMembersResult queryMembersByUserIds(String groupId, String... userIds) throws Exception {
        String method = CheckMethod.QUERY_MEMBERS;

        QueryMembersResult result = checkParam("groupId", groupId, method, QueryMembersResult.class);
        if (result != null) {
            return result;
        }

        result = checkParam("userIds", userIds, method, QueryMembersResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "groupId=", groupId);
        addFormParam(sb, "&userIds=", StringUtils.join(removeDuplicates(userIds), ","));
        String body = sb.toString();

        return doRequest("/entrust/group/member/specific/query.json", body, method, QueryMembersResult.class);
    }


    /**
     * 设置群成员资料
     */
    public ResponseResult setMemberInfo(MemberInfoModel memberInfoModel) throws Exception {
        String method = CheckMethod.SET_MEMBER;

        ResponseResult result = checkFiled(memberInfoModel, method, ResponseResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "groupId=", memberInfoModel.getGroupId());
        addFormParam(sb, "&userId=", memberInfoModel.getUserId());
        addFormParam(sb, "&nickname=", memberInfoModel.getNickname());
        addFormParam(sb, "&extra=", memberInfoModel.getExtra());
        String body = sb.toString();

        return doRequest("/entrust/group/member/set.json", body, method, ResponseResult.class);
    }


    /**
     * 设置群备注名
     */
    public ResponseResult setRemarkName(GroupRemarkNameModel goupRemarkNameModel) throws Exception {
        String method = CheckMethod.SET_REMARK_NAME;

        ResponseResult result = checkFiled(goupRemarkNameModel, method, ResponseResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "userId=", goupRemarkNameModel.getUserId());
        addFormParam(sb, "&groupId=", goupRemarkNameModel.getGroupId());
        addFormParam(sb, "&remarkName=", goupRemarkNameModel.getRemarkName());
        String body = sb.toString();

        return doRequest("/entrust/group/remarkname/set.json", body, method, ResponseResult.class);
    }


    /**
     * 删除群备注名
     */
    public ResponseResult delRemarkName(String groupId, String userId) throws Exception {
        String method = CheckMethod.DEL_REMARK_NAME;

        ResponseResult result = checkParam("groupId", groupId, method, ResponseResult.class);
        if (result != null) {
            return result;
        }
        result = checkParam("userId", userId, method, ResponseResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "userId=", userId);
        addFormParam(sb, "&groupId=", groupId);
        String body = sb.toString();

        return doRequest("/entrust/group/remarkname/delete.json", body, method, ResponseResult.class);
    }


    /**
     * 查询群备注名
     */
    public RemarkNameResult queryRemarkName(String groupId, String userId) throws Exception {
        String method = CheckMethod.QUERY_REMARK_NAME;

        RemarkNameResult result = checkParam("groupId", groupId, method, RemarkNameResult.class);
        if (result != null) {
            return result;
        }
        result = checkParam("userId", userId, method, RemarkNameResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "userId=", userId);
        addFormParam(sb, "&groupId=", groupId);
        String body = sb.toString();

        return doRequest("/entrust/group/remarkname/query.json", body, method, RemarkNameResult.class);
    }


    /**
     * 关注群成员
     */
    public ResponseResult followMember(String groupId, String userId, String... followUserIds) throws Exception {
        String method = CheckMethod.FOLLOW;

        ResponseResult result = checkParam("groupId", groupId, method, ResponseResult.class);
        if (result != null) {
            return result;
        }
        result = checkParam("userId", userId, method, ResponseResult.class);
        if (result != null) {
            return result;
        }

        result = checkParam("followUserIds", followUserIds, method, ResponseResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "userId=", userId);
        addFormParam(sb, "&groupId=", groupId);
        addFormParam(sb, "&followUserIds=", StringUtils.join(removeDuplicates(followUserIds), ","));
        String body = sb.toString();

        return doRequest("/entrust/group/member/follow.json", body, method, ResponseResult.class);
    }

    /**
     * 取消关注群成员
     */
    public ResponseResult unfollowMember(String groupId, String userId, String... followUserIds) throws Exception {
        String method = CheckMethod.UNFOLLOW;

        ResponseResult result = checkParam("groupId", groupId, method, ResponseResult.class);
        if (result != null) {
            return result;
        }
        result = checkParam("userId", userId, method, ResponseResult.class);
        if (result != null) {
            return result;
        }

        result = checkParam("followUserIds", followUserIds, method, ResponseResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "userId=", userId);
        addFormParam(sb, "&groupId=", groupId);
        addFormParam(sb, "&followUserIds=", StringUtils.join(removeDuplicates(followUserIds), ","));
        String body = sb.toString();

        return doRequest("/entrust/group/member/unfollow.json", body, method, ResponseResult.class);
    }

    /**
     * 获取关注群成员
     */
    public FollowedMemberResult getFollowedMember(String groupId, String userId) throws Exception {
        String method = CheckMethod.GET_FOLLOWED;

        FollowedMemberResult result = checkParam("groupId", groupId, method, FollowedMemberResult.class);
        if (result != null) {
            return result;
        }
        result = checkParam("userId", userId, method, FollowedMemberResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "userId=", userId);
        addFormParam(sb, "&groupId=", groupId);
        String body = sb.toString();

        return doRequest("/entrust/group/member/followed/get.json", body, method, FollowedMemberResult.class);
    }

    /**
     * 分页查询应用下群组信息
     */
    public PagingQueryGroupsResult pagingQueryGroups(PageModel pageModel) throws Exception {
        String method = CheckMethod.PAGING_QUERY_GROUPS;

        PagingQueryGroupsResult result = checkFiled(pageModel, method, PagingQueryGroupsResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "pageToken=", pageModel.getPageToken());
        addFormParam(sb, "&size=", pageModel.getSize());
        addFormParam(sb, "&order=", pageModel.getOrder());
        String body = sb.toString();

        return doRequest("/entrust/group/query.json", body, method, PagingQueryGroupsResult.class);

    }

    /**
     * 分页查询用户加入的群组
     */
    public PagingQueryJoinedGroupsResult pagingQueryJoinedGroups(QueryJoinedGroupsModel pageModel) throws Exception {
        String method = CheckMethod.PAGING_QUERY_JOINED_GROUPS;

        PagingQueryJoinedGroupsResult result = checkFiled(pageModel, method, PagingQueryJoinedGroupsResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "userId=", pageModel.getUserId());
        addFormParam(sb, "&role=", pageModel.getRole());
        addFormParam(sb, "&pageToken=", pageModel.getPageToken());
        addFormParam(sb, "&size=", pageModel.getSize());
        addFormParam(sb, "&order=", pageModel.getOrder());

        String body = sb.toString();

        return doRequest("/entrust/joined/group/query.json", body, method, PagingQueryJoinedGroupsResult.class);

    }


}