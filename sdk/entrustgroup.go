package sdk

import (
	"encoding/json"
	"strconv"
	"strings"
	"time"

	"github.com/astaxie/beego/httplib"
)

// Entrust group related constants
const (
	DefaultPageSize = 50   // Default page size
	MaxPageSize     = 1000 // Maximum page size
	DefaultOrder    = 1    // Default order (descending)
	OrderAsc        = 0    // Ascending order
	OrderDesc       = 1    // Descending order
)

// Helper function for pointer creation
func IntPtr(i int) *int {
	return &i
}

// CreateEntrustGroupModel Create entrust group request model
type CreateEntrustGroupModel struct {
	GroupId         string   `json:"groupId"`         // Group ID
	Name            string   `json:"name"`            // Group name
	Owner           string   `json:"owner"`           // Group owner ID
	UserIds         []string `json:"userIds"`         // Member ID list
	GroupProfile    string   `json:"groupProfile"`    // Group basic information
	GroupExtProfile string   `json:"groupExtProfile"` // Group extended information
	Permissions     string   `json:"permissions"`     // Permission settings
}

// EntrustGroupModel Entrust group update model
type EntrustGroupModel struct {
	GroupId         string `json:"groupId"`         // Group ID
	Name            string `json:"name"`            // Group name
	GroupProfile    string `json:"groupProfile"`    // Group basic information
	GroupExtProfile string `json:"groupExtProfile"` // Group extended information
	Permissions     string `json:"permissions"`     // Permission settings
}

// QuitEntrustGroupModel Quit entrust group request model
type QuitEntrustGroupModel struct {
	GroupId       string   `json:"groupId"`       // Group ID
	UserIds       []string `json:"userIds"`       // User ID list
	IsDelBan      *int     `json:"isDelBan"`      // Whether to delete ban (nil=not set, 0=keep, 1=delete)
	IsDelWhite    *int     `json:"isDelWhite"`    // Whether to delete whitelist (nil=not set, 0=keep, 1=delete)
	IsDelFollowed *int     `json:"isDelFollowed"` // Whether to delete follow (nil=not set, 0=keep, 1=delete)
}

// KickOutEntrustGroupModel Kick out group member request model
type KickOutEntrustGroupModel struct {
	GroupId       string   `json:"groupId"`       // Group ID
	UserIds       []string `json:"userIds"`       // User ID list
	IsDelBan      *int     `json:"isDelBan"`      // Whether to delete ban (nil=not set, 0=keep, 1=delete)
	IsDelWhite    *int     `json:"isDelWhite"`    // Whether to delete whitelist (nil=not set, 0=keep, 1=delete)
	IsDelFollowed *int     `json:"isDelFollowed"` // Whether to delete follow (nil=not set, 0=keep, 1=delete)
}

// TransferOwnerModel Transfer group owner request model
type TransferOwnerModel struct {
	GroupId       string `json:"groupId"`       // Group ID
	NewOwner      string `json:"newOwner"`      // New group owner ID
	IsDelBan      *int   `json:"isDelBan"`      // Whether to delete ban (nil=not set, 0=keep, 1=delete)
	IsDelWhite    *int   `json:"isDelWhite"`    // Whether to delete whitelist (nil=not set, 0=keep, 1=delete)
	IsDelFollowed *int   `json:"isDelFollowed"` // Whether to delete follow (nil=not set, 0=keep, 1=delete)
	IsQuit        *int   `json:"isQuit"`        // Whether to quit group (nil=not set, 0=not quit, 1=quit)
}

// ImportEntrustGroupModel Import entrust group request model
type ImportEntrustGroupModel struct {
	GroupId         string `json:"groupId"`         // Group ID
	Name            string `json:"name"`            // Group name
	Owner           string `json:"owner"`           // Group owner ID
	GroupProfile    string `json:"groupProfile"`    // Group basic information
	GroupExtProfile string `json:"groupExtProfile"` // Group extended information
	Permissions     string `json:"permissions"`     // Permission settings
}

// PagingQueryMembersModel Paging query members request model
type PagingQueryMembersModel struct {
	GroupId   string `json:"groupId"`   // Group ID
	Type      int    `json:"type"`      // Query type
	PageToken string `json:"pageToken"` // Pagination token
	Size      int    `json:"size"`      // Page size
	Order     int    `json:"order"`     // Sort order
}

// MemberInfoModel Member information model
type MemberInfoModel struct {
	GroupId  string `json:"groupId"`  // Group ID
	UserId   string `json:"userId"`   // User ID
	Nickname string `json:"nickname"` // Nickname
	Extra    string `json:"extra"`    // Extra information
}

// GroupRemarkNameModel Group remark name model
type GroupRemarkNameModel struct {
	UserId     string `json:"userId"`     // User ID
	GroupId    string `json:"groupId"`    // Group ID
	RemarkName string `json:"remarkName"` // Remark name
}

// PageModel Pagination model
type PageModel struct {
	PageToken string `json:"pageToken"` // Pagination token
	Size      int    `json:"size"`      // Page size
	Order     int    `json:"order"`     // Sort order
}

// QueryJoinedGroupsModel Query joined groups request model
type QueryJoinedGroupsModel struct {
	UserId    string `json:"userId"`    // User ID
	Role      int    `json:"role"`      // Role
	PageToken string `json:"pageToken"` // Pagination token
	Size      int    `json:"size"`      // Page size
	Order     *int   `json:"order"`     // Sort order
}

// Response result structures
type ResponseResult struct {
}

type QueryGroupProfilesResult struct {
	Profiles []EntrustGroupProfile `json:"profiles"` // Group profile list
}

type EntrustGroupProfile struct {
	GroupId         string `json:"groupId"`         // Group ID
	GroupName       string `json:"groupName"`       // Group name
	GroupProfile    string `json:"groupProfile"`    // Group basic information
	GroupExtProfile string `json:"groupExtProfile"` // Group extended information
	Permissions     string `json:"permissions"`     // Permission settings
	CreateTime      int64  `json:"createTime"`      // Create time
	MemberCount     int    `json:"memberCount"`     // Member count
	Owner           string `json:"owner"`           // Group owner ID
}

type JoinGroupResult struct {
	GroupId         string `json:"groupId"`         // Group ID
	Name            string `json:"name"`            // Group name
	Owner           string `json:"owner"`           // Group owner ID
	GroupProfile    string `json:"groupProfile"`    // Group basic information
	GroupExtProfile string `json:"groupExtProfile"` // Group extended information
	Permissions     string `json:"permissions"`     // Permission settings
	RemarkName      string `json:"remarkName"`      // Remark name
	CreateTime      int64  `json:"createTime"`      // Create time
	MemberCount     int    `json:"memberCount"`     // Member count
}

type SetManagersResult struct {
	UserIds      []string `json:"userIds"`      // User ID array that failed to add due to not being group members
	ManagerCount int      `json:"managerCount"` // Manager count
}

type PagingQueryMembersResult struct {
	PageToken string        `json:"pageToken"` // Pagination token
	Members   []GroupMember `json:"members"`   // Member list
}

type GroupMember struct {
	UserId   string `json:"userId"`   // User ID
	Nickname string `json:"nickname"` // Nickname
	Role     int    `json:"role"`     // Role
	Time     int64  `json:"time"`     // Join time
	Extra    string `json:"extra"`    // Extra information
}

type QueryMembersResult struct {
	Members []GroupMember `json:"members"` // Member list
}

type RemarkNameResult struct {
	RemarkName string `json:"remarkName"` // Remark name
}

type FollowedMemberResult struct {
	UserId  string           `json:"userId"`  // User ID
	GroupId string           `json:"groupId"` // Group ID
	Members []FollowedMember `json:"members"` // Followed user ID list
}

type FollowedMember struct {
	UserId    string `json:"userId"`    // User ID
	Timestamp int64  `json:"timestamp"` // Follow time
}

type PagingQueryGroupsResult struct {
	PageToken string             `json:"pageToken"` // Pagination token
	Groups    []EntrustGroupInfo `json:"groups"`    // Group list
}

type EntrustGroupInfo struct {
	GroupId     string `json:"groupId"`     // Group ID
	Owner       string `json:"owner"`       // Group owner ID
	Creator     string `json:"creator"`     // Creator ID
	Name        string `json:"name"`        // Group name
	PortraitUrl string `json:"portraitUrl"` // Portrait URL
	Time        int64  `json:"time"`        // Create time
}

type PagingQueryJoinedGroupsResult struct {
	TotalCount int                      `json:"totalCount"` // Total count
	PageToken  string                   `json:"pageToken"`  // Pagination token
	Groups     []EntrustGroupDetailInfo `json:"groups"`     // Group list
}

type EntrustGroupDetailInfo struct {
	GroupId         string `json:"groupId"`         // Group ID
	Name            string `json:"name"`            // Group name
	RemarkName      string `json:"remarkName"`      // Remark name
	GroupProfile    string `json:"groupProfile"`    // Group basic information
	CreateTime      int64  `json:"createTime"`      // Create time
	Permissions     string `json:"permissions"`     // Permission settings
	GroupExtProfile string `json:"groupExtProfile"` // Group extended information
	JoinTime        int64  `json:"joinTime"`        // Join time
	Role            int    `json:"role"`            // Role
	Count           int    `json:"count"`           // Member count
}

// Helper function: validate group ID
func (rc *RongCloud) validateGroupId(groupId string) error {
	if groupId == "" {
		return RCErrorNew(1002, "Parameter 'groupId' is required")
	}
	return nil
}

// Helper function: validate user ID
func (rc *RongCloud) validateUserId(userId string) error {
	if userId == "" {
		return RCErrorNew(1002, "Parameter 'userId' is required")
	}
	return nil
}

// Helper function: validate user ID list
func (rc *RongCloud) validateUserIds(userIds []string) error {
	if len(userIds) == 0 {
		return RCErrorNew(1002, "Parameter 'userIds' is required")
	}
	return nil
}

// Helper function: remove duplicates
func removeDuplicates(slice []string) []string {
	keys := make(map[string]bool)
	result := []string{}
	for _, item := range slice {
		if !keys[item] {
			keys[item] = true
			result = append(result, item)
		}
	}
	return result
}

// Create entrust group
func (rc *RongCloud) EntrustGroupCreate(group CreateEntrustGroupModel) (ResponseResult, error) {
	result := ResponseResult{}

	// Validate required parameters
	if err := rc.validateGroupId(group.GroupId); err != nil {
		return result, err
	}
	if group.Name == "" {
		return result, RCErrorNew(1002, "Parameter 'name' is required")
	}
	if group.Owner == "" {
		return result, RCErrorNew(1002, "Parameter 'owner' is required")
	}
	if err := rc.validateUserIds(group.UserIds); err != nil {
		return result, err
	}

	req := httplib.Post(rc.rongCloudURI + "/entrust/group/create.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("groupId", group.GroupId)
	req.Param("name", group.Name)
	req.Param("owner", group.Owner)
	req.Param("userIds", strings.Join(removeDuplicates(group.UserIds), ","))
	if group.GroupProfile != "" {
		req.Param("groupProfile", group.GroupProfile)
	}
	if group.GroupExtProfile != "" {
		req.Param("groupExtProfile", group.GroupExtProfile)
	}
	if group.Permissions != "" {
		req.Param("permissions", group.Permissions)
	}

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}

	return result, nil
}

// Update entrust group profile
func (rc *RongCloud) EntrustGroupUpdateProfile(group EntrustGroupModel) (ResponseResult, error) {
	result := ResponseResult{}

	// Validate required parameters
	if err := rc.validateGroupId(group.GroupId); err != nil {
		return result, err
	}

	req := httplib.Post(rc.rongCloudURI + "/entrust/group/profile/update.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("groupId", group.GroupId)
	if group.Name != "" {
		req.Param("name", group.Name)
	}
	if group.GroupProfile != "" {
		req.Param("groupProfile", group.GroupProfile)
	}
	if group.GroupExtProfile != "" {
		req.Param("groupExtProfile", group.GroupExtProfile)
	}
	if group.Permissions != "" {
		req.Param("permissions", group.Permissions)
	}

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}

	return result, nil
}

// Query entrust group profiles
func (rc *RongCloud) EntrustGroupQueryProfiles(groupIds ...string) (QueryGroupProfilesResult, error) {
	result := QueryGroupProfilesResult{}

	// Validate required parameters
	if len(groupIds) == 0 {
		return result, RCErrorNew(1002, "Parameter 'groupIds' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/entrust/group/profile/query.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("groupIds", strings.Join(removeDuplicates(groupIds), ","))

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}

	return result, nil
}

// Quit entrust group
func (rc *RongCloud) EntrustGroupQuit(params QuitEntrustGroupModel) (ResponseResult, error) {
	result := ResponseResult{}

	// Validate required parameters
	if err := rc.validateGroupId(params.GroupId); err != nil {
		return result, err
	}
	if err := rc.validateUserIds(params.UserIds); err != nil {
		return result, err
	}

	req := httplib.Post(rc.rongCloudURI + "/entrust/group/quit.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("groupId", params.GroupId)
	req.Param("userIds", strings.Join(removeDuplicates(params.UserIds), ","))
	if params.IsDelBan != nil {
		req.Param("isDelBan", strconv.Itoa(*params.IsDelBan))
	}
	if params.IsDelWhite != nil {
		req.Param("isDelWhite", strconv.Itoa(*params.IsDelWhite))
	}
	if params.IsDelFollowed != nil {
		req.Param("isDelFollowed", strconv.Itoa(*params.IsDelFollowed))
	}

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}

	return result, nil
}

// Kick out group members
func (rc *RongCloud) EntrustGroupKickOut(params KickOutEntrustGroupModel) (ResponseResult, error) {
	result := ResponseResult{}

	// Validate required parameters
	if err := rc.validateGroupId(params.GroupId); err != nil {
		return result, err
	}
	if err := rc.validateUserIds(params.UserIds); err != nil {
		return result, err
	}

	req := httplib.Post(rc.rongCloudURI + "/entrust/group/member/kick.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("groupId", params.GroupId)
	req.Param("userIds", strings.Join(removeDuplicates(params.UserIds), ","))
	if params.IsDelBan != nil {
		req.Param("isDelBan", strconv.Itoa(*params.IsDelBan))
	}
	if params.IsDelWhite != nil {
		req.Param("isDelWhite", strconv.Itoa(*params.IsDelWhite))
	}
	if params.IsDelFollowed != nil {
		req.Param("isDelFollowed", strconv.Itoa(*params.IsDelFollowed))
	}

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}

	return result, nil
}

// Kick out user from all groups
func (rc *RongCloud) EntrustGroupKickOutAllGroups(userId string) (ResponseResult, error) {
	result := ResponseResult{}

	// Validate required parameters
	if err := rc.validateUserId(userId); err != nil {
		return result, err
	}

	req := httplib.Post(rc.rongCloudURI + "/entrust/group/member/kick/all.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("userId", userId)

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}

	return result, nil
}

// Dismiss group
func (rc *RongCloud) EntrustGroupDismiss(groupId string) (ResponseResult, error) {
	result := ResponseResult{}

	// Validate required parameters
	if err := rc.validateGroupId(groupId); err != nil {
		return result, err
	}

	req := httplib.Post(rc.rongCloudURI + "/entrust/group/dismiss.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("groupId", groupId)

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}

	return result, nil
}

// Join group
func (rc *RongCloud) EntrustGroupJoin(groupId string, userIds ...string) (JoinGroupResult, error) {
	result := JoinGroupResult{}

	// Validate required parameters
	if err := rc.validateGroupId(groupId); err != nil {
		return result, err
	}
	if len(userIds) == 0 {
		return result, RCErrorNew(1002, "Parameter 'userIds' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/entrust/group/join.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("groupId", groupId)
	req.Param("userIds", strings.Join(removeDuplicates(userIds), ","))

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}

	return result, nil
}

// Transfer group owner
func (rc *RongCloud) EntrustGroupTransferOwner(params TransferOwnerModel) (ResponseResult, error) {
	result := ResponseResult{}

	// Validate required parameters
	if err := rc.validateGroupId(params.GroupId); err != nil {
		return result, err
	}
	if err := rc.validateUserId(params.NewOwner); err != nil {
		return result, err
	}

	req := httplib.Post(rc.rongCloudURI + "/entrust/group/transfer/owner.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("groupId", params.GroupId)
	req.Param("newOwner", params.NewOwner)
	if params.IsDelBan != nil {
		req.Param("isDelBan", strconv.Itoa(*params.IsDelBan))
	}
	if params.IsDelWhite != nil {
		req.Param("isDelWhite", strconv.Itoa(*params.IsDelWhite))
	}
	if params.IsDelFollowed != nil {
		req.Param("isDelFollowed", strconv.Itoa(*params.IsDelFollowed))
	}
	if params.IsQuit != nil {
		req.Param("isQuit", strconv.Itoa(*params.IsQuit))
	}

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}

	return result, nil
}

// Import group
func (rc *RongCloud) EntrustGroupImportGroup(group ImportEntrustGroupModel) (ResponseResult, error) {
	result := ResponseResult{}

	// Validate required parameters
	if err := rc.validateGroupId(group.GroupId); err != nil {
		return result, err
	}
	if group.Name == "" {
		return result, RCErrorNew(1002, "Parameter 'name' is required")
	}
	if group.Owner == "" {
		return result, RCErrorNew(1002, "Parameter 'owner' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/entrust/group/import.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("groupId", group.GroupId)
	req.Param("name", group.Name)
	req.Param("owner", group.Owner)
	if group.GroupProfile != "" {
		req.Param("groupProfile", group.GroupProfile)
	}
	if group.GroupExtProfile != "" {
		req.Param("groupExtProfile", group.GroupExtProfile)
	}
	if group.Permissions != "" {
		req.Param("permissions", group.Permissions)
	}

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}

	return result, nil
}

// Add managers
func (rc *RongCloud) EntrustGroupAddManagers(groupId string, userIds ...string) (SetManagersResult, error) {
	result := SetManagersResult{}

	// Validate required parameters
	if err := rc.validateGroupId(groupId); err != nil {
		return result, err
	}
	if len(userIds) == 0 {
		return result, RCErrorNew(1002, "Parameter 'userIds' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/entrust/group/manager/add.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("groupId", groupId)
	req.Param("userIds", strings.Join(removeDuplicates(userIds), ","))

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}

	return result, nil
}

// Remove managers
func (rc *RongCloud) EntrustGroupRemoveManagers(groupId string, userIds ...string) (ResponseResult, error) {
	result := ResponseResult{}

	// Validate required parameters
	if err := rc.validateGroupId(groupId); err != nil {
		return result, err
	}
	if len(userIds) == 0 {
		return result, RCErrorNew(1002, "Parameter 'userIds' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/entrust/group/manager/remove.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("groupId", groupId)
	req.Param("userIds", strings.Join(removeDuplicates(userIds), ","))

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}

	return result, nil
}

// Paging query group members
func (rc *RongCloud) EntrustGroupPagingQueryMembers(pageQuery PagingQueryMembersModel) (PagingQueryMembersResult, error) {
	result := PagingQueryMembersResult{}

	// Validate required parameters
	if err := rc.validateGroupId(pageQuery.GroupId); err != nil {
		return result, err
	}

	req := httplib.Post(rc.rongCloudURI + "/entrust/group/member/query.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("groupId", pageQuery.GroupId)
	if pageQuery.Type != 0 {
		req.Param("type", strconv.Itoa(pageQuery.Type))
	}
	if pageQuery.PageToken != "" {
		req.Param("pageToken", pageQuery.PageToken)
	}
	if pageQuery.Size > 0 {
		req.Param("size", strconv.Itoa(pageQuery.Size))
	}
	if pageQuery.Order >= 0 {
		req.Param("order", strconv.Itoa(pageQuery.Order))
	}

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}

	return result, nil
}

// Query specific members by user IDs
func (rc *RongCloud) EntrustGroupQueryMembersByUserIds(groupId string, userIds ...string) (QueryMembersResult, error) {
	result := QueryMembersResult{}

	// Validate required parameters
	if err := rc.validateGroupId(groupId); err != nil {
		return result, err
	}
	if len(userIds) == 0 {
		return result, RCErrorNew(1002, "Parameter 'userIds' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/entrust/group/member/specific/query.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("groupId", groupId)
	req.Param("userIds", strings.Join(removeDuplicates(userIds), ","))

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}

	return result, nil
}

// Set member information
func (rc *RongCloud) EntrustGroupSetMemberInfo(memberInfo MemberInfoModel) (ResponseResult, error) {
	result := ResponseResult{}

	// Validate required parameters
	if err := rc.validateGroupId(memberInfo.GroupId); err != nil {
		return result, err
	}
	if err := rc.validateUserId(memberInfo.UserId); err != nil {
		return result, err
	}

	req := httplib.Post(rc.rongCloudURI + "/entrust/group/member/set.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("groupId", memberInfo.GroupId)
	req.Param("userId", memberInfo.UserId)
	if memberInfo.Nickname != "" {
		req.Param("nickname", memberInfo.Nickname)
	}
	if memberInfo.Extra != "" {
		req.Param("extra", memberInfo.Extra)
	}

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}

	return result, nil
}

// Set group remark name
func (rc *RongCloud) EntrustGroupSetRemarkName(remarkName GroupRemarkNameModel) (ResponseResult, error) {
	result := ResponseResult{}

	// Validate required parameters
	if err := rc.validateUserId(remarkName.UserId); err != nil {
		return result, err
	}
	if err := rc.validateGroupId(remarkName.GroupId); err != nil {
		return result, err
	}

	req := httplib.Post(rc.rongCloudURI + "/entrust/group/remarkname/set.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("userId", remarkName.UserId)
	req.Param("groupId", remarkName.GroupId)
	req.Param("remarkName", remarkName.RemarkName)

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}

	return result, nil
}

// Delete group remark name
func (rc *RongCloud) EntrustGroupDelRemarkName(groupId, userId string) (ResponseResult, error) {
	result := ResponseResult{}

	// Validate required parameters
	if err := rc.validateGroupId(groupId); err != nil {
		return result, err
	}
	if err := rc.validateUserId(userId); err != nil {
		return result, err
	}

	req := httplib.Post(rc.rongCloudURI + "/entrust/group/remarkname/delete.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("userId", userId)
	req.Param("groupId", groupId)

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}

	return result, nil
}

// Query group remark name
func (rc *RongCloud) EntrustGroupQueryRemarkName(groupId, userId string) (RemarkNameResult, error) {
	result := RemarkNameResult{}

	// Validate required parameters
	if err := rc.validateGroupId(groupId); err != nil {
		return result, err
	}
	if err := rc.validateUserId(userId); err != nil {
		return result, err
	}

	req := httplib.Post(rc.rongCloudURI + "/entrust/group/remarkname/query.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("userId", userId)
	req.Param("groupId", groupId)

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}

	return result, nil
}

// Follow group members
func (rc *RongCloud) EntrustGroupFollowMember(groupId, userId string, followUserIds ...string) (ResponseResult, error) {
	result := ResponseResult{}

	// Validate required parameters
	if err := rc.validateGroupId(groupId); err != nil {
		return result, err
	}
	if err := rc.validateUserId(userId); err != nil {
		return result, err
	}
	if len(followUserIds) == 0 {
		return result, RCErrorNew(1002, "Parameter 'followUserIds' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/entrust/group/member/follow.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("userId", userId)
	req.Param("groupId", groupId)
	req.Param("followUserIds", strings.Join(removeDuplicates(followUserIds), ","))

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}

	return result, nil
}

// Unfollow group members
func (rc *RongCloud) EntrustGroupUnfollowMember(groupId, userId string, followUserIds ...string) (ResponseResult, error) {
	result := ResponseResult{}

	// Validate required parameters
	if err := rc.validateGroupId(groupId); err != nil {
		return result, err
	}
	if err := rc.validateUserId(userId); err != nil {
		return result, err
	}
	if len(followUserIds) == 0 {
		return result, RCErrorNew(1002, "Parameter 'followUserIds' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/entrust/group/member/unfollow.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("userId", userId)
	req.Param("groupId", groupId)
	req.Param("followUserIds", strings.Join(removeDuplicates(followUserIds), ","))

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}

	return result, nil
}

// Get followed group members
func (rc *RongCloud) EntrustGroupGetFollowedMember(groupId, userId string) (FollowedMemberResult, error) {
	result := FollowedMemberResult{}

	// Validate required parameters
	if err := rc.validateGroupId(groupId); err != nil {
		return result, err
	}
	if err := rc.validateUserId(userId); err != nil {
		return result, err
	}

	req := httplib.Post(rc.rongCloudURI + "/entrust/group/member/followed/get.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("userId", userId)
	req.Param("groupId", groupId)

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}

	return result, nil
}

// Paging query groups
func (rc *RongCloud) EntrustGroupPagingQueryGroups(pageModel PageModel) (PagingQueryGroupsResult, error) {
	result := PagingQueryGroupsResult{}

	req := httplib.Post(rc.rongCloudURI + "/entrust/group/query.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	if pageModel.PageToken != "" {
		req.Param("pageToken", pageModel.PageToken)
	}
	if pageModel.Size > 0 {
		req.Param("size", strconv.Itoa(pageModel.Size))
	}
	if pageModel.Order >= 0 {
		req.Param("order", strconv.Itoa(pageModel.Order))
	}

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}

	return result, nil
}

// Paging query joined groups
func (rc *RongCloud) EntrustGroupPagingQueryJoinedGroups(pageModel QueryJoinedGroupsModel) (PagingQueryJoinedGroupsResult, error) {
	result := PagingQueryJoinedGroupsResult{}

	// Validate required parameters
	if err := rc.validateUserId(pageModel.UserId); err != nil {
		return result, err
	}

	req := httplib.Post(rc.rongCloudURI + "/entrust/joined/group/query.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("userId", pageModel.UserId)
	if pageModel.Role != 0 {
		req.Param("role", strconv.Itoa(pageModel.Role))
	}
	if pageModel.PageToken != "" {
		req.Param("pageToken", pageModel.PageToken)
	}
	if pageModel.Size > 0 {
		req.Param("size", strconv.Itoa(pageModel.Size))
	}
	if pageModel.Order != nil {
		req.Param("order", strconv.Itoa(*pageModel.Order))
	}

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}

	return result, nil
}
