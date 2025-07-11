package sdk

import (
	"encoding/json"
	"strconv"
	"strings"
	"time"

	"github.com/astaxie/beego/httplib"
)

// FriendModel Add friend request model
type FriendModel struct {
	UserId   string `json:"userId"`   // User ID
	TargetId string `json:"targetId"` // Target user ID
	OptType  *int   `json:"optType"`  // Add type: 1: Add friend according to the target user's add friend verification level (default); 2: Add friend directly: no need to go through the target user's approval
	Extra    string `json:"extra"`    // Extra information
}

// FriendProfileModel Friend profile model
type FriendProfileModel struct {
	UserId           string `json:"userId"`           // User ID
	TargetId         string `json:"targetId"`         // Target user ID
	RemarkName       string `json:"remarkName"`       // Remark name
	FriendExtProfile string `json:"friendExtProfile"` // Friend extended profile
}

// PagingGetFriendsModel Paging get friends request model
type PagingGetFriendsModel struct {
	UserId    string `json:"userId"`    // User ID
	PageToken string `json:"pageToken"` // Pagination token
	Size      int    `json:"size"`      // Page size
	Order     *int   `json:"order"`     // Sort order (nil=not set, 0=asc, 1=desc)
}

// Response result structures for friend API
type QueryFriendsResult struct {
	TotalCount int             `json:"totalCount"` // Total number of friends
	PageToken  string          `json:"pageToken"`  // Pagination token
	Friends    []FriendProfile `json:"friendList"` // Friend list
}

type FriendProfile struct {
	UserId           string `json:"userId"`           // User ID
	Name             string `json:"name"`             // Username
	RemarkName       string `json:"remarkName"`       // Friend alias
	FriendExtProfile string `json:"friendExtProfile"` // Custom extended attributes
	Time             int64  `json:"time"`             // Timestamp when the friend was added
}

type CheckFriendsResult struct {
	Results []CheckFriendResult `json:"results"` // Friend relation list
}

type CheckFriendResult struct {
	UserId string `json:"userId"` // User ID
	Result int    `json:"result"` // Unidirectional check result: 1: Not in my friend list; 2: In my friend list; Bidirectional check result: 1: In both users' friend lists; 2: Not in both users' friend lists; 3: Only in the current user's friend list; 4: Only in the target user's friend list
}

type GetPermissionResult struct {
	PermissionSettings []PermissionSetting `json:"permissionSettings"` // User permission list
}

type PermissionSetting struct {
	UserId string `json:"userId"` // User ID
	Type   int    `json:"type"`   // Permission type
}

// Helper function: validate user ID for friend operations
func (rc *RongCloud) validateFriendUserId(userId string) error {
	if userId == "" {
		return RCErrorNew(1002, "Parameter 'userId' is required")
	}
	return nil
}

// Helper function: validate target IDs
func (rc *RongCloud) validateTargetIds(targetIds []string) error {
	if len(targetIds) == 0 {
		return RCErrorNew(1002, "Parameter 'targetIds' is required")
	}
	return nil
}

// Add friend
func (rc *RongCloud) FriendAdd(friend FriendModel) (ResponseResult, error) {
	result := ResponseResult{}

	// Validate required parameters
	if err := rc.validateFriendUserId(friend.UserId); err != nil {
		return result, err
	}
	if friend.TargetId == "" {
		return result, RCErrorNew(1002, "Parameter 'targetId' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/friend/add.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("userId", friend.UserId)
	req.Param("targetId", friend.TargetId)
	if friend.OptType != nil {
		req.Param("optType", strconv.Itoa(*friend.OptType))
	}
	if friend.Extra != "" {
		req.Param("extra", friend.Extra)
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

// Delete friend
func (rc *RongCloud) FriendDelete(userId string, targetIds ...string) (ResponseResult, error) {
	result := ResponseResult{}

	// Validate required parameters
	if err := rc.validateFriendUserId(userId); err != nil {
		return result, err
	}
	if err := rc.validateTargetIds(targetIds); err != nil {
		return result, err
	}

	req := httplib.Post(rc.rongCloudURI + "/friend/delete.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("userId", userId)
	req.Param("targetIds", strings.Join(removeDuplicates(targetIds), ","))

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

// Clean all friends
func (rc *RongCloud) FriendClean(userId string) (ResponseResult, error) {
	result := ResponseResult{}

	// Validate required parameters
	if err := rc.validateFriendUserId(userId); err != nil {
		return result, err
	}

	req := httplib.Post(rc.rongCloudURI + "/friend/clean.json")
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

// Set friend profile
func (rc *RongCloud) FriendSetProfile(profileModel FriendProfileModel) (ResponseResult, error) {
	result := ResponseResult{}

	// Validate required parameters
	if err := rc.validateFriendUserId(profileModel.UserId); err != nil {
		return result, err
	}
	if profileModel.TargetId == "" {
		return result, RCErrorNew(1002, "Parameter 'targetId' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/friend/profile/set.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("userId", profileModel.UserId)
	req.Param("targetId", profileModel.TargetId)
	if profileModel.RemarkName != "" {
		req.Param("remarkName", profileModel.RemarkName)
	}
	if profileModel.FriendExtProfile != "" {
		req.Param("friendExtProfile", profileModel.FriendExtProfile)
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

// Paginate to get friend information
func (rc *RongCloud) PagingGetFriends(getFriendsModel PagingGetFriendsModel) (QueryFriendsResult, error) {
	result := QueryFriendsResult{}

	// Validate required parameters
	if err := rc.validateFriendUserId(getFriendsModel.UserId); err != nil {
		return result, err
	}

	req := httplib.Post(rc.rongCloudURI + "/friend/get.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("userId", getFriendsModel.UserId)
	if getFriendsModel.PageToken != "" {
		req.Param("pageToken", getFriendsModel.PageToken)
	}
	if getFriendsModel.Size > 0 {
		req.Param("size", strconv.Itoa(getFriendsModel.Size))
	}
	if getFriendsModel.Order != nil {
		req.Param("order", strconv.Itoa(*getFriendsModel.Order))
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

// Check friend relationships
func (rc *RongCloud) FriendCheckFriends(userId string, targetIds ...string) (CheckFriendsResult, error) {
	result := CheckFriendsResult{}

	// Validate required parameters
	if err := rc.validateFriendUserId(userId); err != nil {
		return result, err
	}
	if err := rc.validateTargetIds(targetIds); err != nil {
		return result, err
	}

	req := httplib.Post(rc.rongCloudURI + "/friend/check.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("userId", userId)
	req.Param("targetIds", strings.Join(removeDuplicates(targetIds), ","))

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

// Set permission for adding friends
func (rc *RongCloud) FriendSetPermission(permissionType int, userIds ...string) (ResponseResult, error) {
	result := ResponseResult{}

	// Validate required parameters
	if len(userIds) == 0 {
		return result, RCErrorNew(1002, "Parameter 'userIds' is required")
	}
	if permissionType == 0 {
		return result, RCErrorNew(1002, "Parameter 'permissionType' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/friend/permission/set.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("userIds", strings.Join(removeDuplicates(userIds), ","))
	req.Param("permissionType", strconv.Itoa(permissionType))

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

// Get permission for adding friends
func (rc *RongCloud) FriendGetPermission(userIds ...string) (GetPermissionResult, error) {
	result := GetPermissionResult{}

	// Validate required parameters
	if len(userIds) == 0 {
		return result, RCErrorNew(1002, "Parameter 'userIds' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/friend/permission/get.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

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
