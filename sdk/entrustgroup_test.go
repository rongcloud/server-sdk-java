package sdk

import (
	"encoding/json"
	"os"
	"testing"
)

// Helper function to format result as JSON
func formatJSON(data interface{}) string {
	jsonBytes, err := json.MarshalIndent(data, "", "  ")
	if err != nil {
		return "Failed to marshal JSON: " + err.Error()
	}
	return string(jsonBytes)
}

// Helper functions for pointer creation
func intPtr(i int) *int {
	return &i
}

// Test creating entrust group
func TestRongCloud_EntrustGroupCreate(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	group := CreateEntrustGroupModel{
		GroupId:         "entrust_group_002",
		Name:            "Test Entrust Group",
		Owner:           "user001",
		UserIds:         []string{"user001", "user002", "user003"},
		GroupProfile:    `{"portraitUrl":"http://test_url"}`,
		GroupExtProfile: `{"ext_00":"ext_00v"}`,
		Permissions:     `{"joinPerm":1}`,
	}

	result, err := rc.EntrustGroupCreate(group)
	t.Log("Create entrust group result:", formatJSON(result))
	t.Log("Error:", err)
}

// Test updating entrust group profile
func TestRongCloud_EntrustGroupUpdateProfile(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	group := EntrustGroupModel{
		GroupId:         "entrust_group_001",
		Name:            "Updated Test Entrust Group",
		GroupProfile:    `{"portraitUrl":"http://test_url_001"}`,
		GroupExtProfile: `{"ext_00":"ext_00v_001"}`,
		Permissions:     `{"joinPerm":1}`,
	}

	result, err := rc.EntrustGroupUpdateProfile(group)
	t.Log("Update entrust group profile result:", formatJSON(result))
	t.Log("Error:", err)
}

// Test querying entrust group profiles
func TestRongCloud_EntrustGroupQueryProfiles(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	result, err := rc.EntrustGroupQueryProfiles("entrust_group_001")
	t.Log("Query entrust group profiles result:", formatJSON(result))
	t.Log("Error:", err)
}

// Test quitting entrust group - simplified
func TestRongCloud_EntrustGroupQuit(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	params := QuitEntrustGroupModel{
		GroupId:       "entrust_group_001",
		UserIds:       []string{"user003"},
		IsDelBan:      IntPtr(1), // Delete ban
		IsDelWhite:    IntPtr(1), // Delete whitelist
		IsDelFollowed: IntPtr(1), // Delete follow
	}

	result, err := rc.EntrustGroupQuit(params)
	t.Log("Quit entrust group result:", formatJSON(result))
	t.Log("Error:", err)
}

// Test quitting entrust group with explicit 0 values - simplified
func TestRongCloud_EntrustGroupQuitWithZero(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	params := QuitEntrustGroupModel{
		GroupId:       "entrust_group_001",
		UserIds:       []string{"user003"},
		IsDelBan:      IntPtr(0), // Keep ban
		IsDelWhite:    IntPtr(0), // Keep whitelist
		IsDelFollowed: IntPtr(0), // Keep follow
	}

	result, err := rc.EntrustGroupQuit(params)
	t.Log("Quit entrust group with zero values result:", formatJSON(result))
	t.Log("Error:", err)
}

// Test quitting entrust group without setting optional parameters
func TestRongCloud_EntrustGroupQuitWithoutOptional(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	params := QuitEntrustGroupModel{
		GroupId: "entrust_group_001",
		UserIds: []string{"user003"},
		// IsDelBan, IsDelWhite, IsDelFollowed are nil (not set)
	}

	result, err := rc.EntrustGroupQuit(params)
	t.Log("Quit entrust group without optional params result:", formatJSON(result))
	t.Log("Error:", err)
}

// Test kicking out group members - simplified
func TestRongCloud_EntrustGroupKickOut(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	params := KickOutEntrustGroupModel{
		GroupId:       "entrust_group_001",
		UserIds:       []string{"user004"},
		IsDelBan:      IntPtr(1), // Delete ban
		IsDelWhite:    IntPtr(1), // Delete whitelist
		IsDelFollowed: IntPtr(1), // Delete follow
	}

	result, err := rc.EntrustGroupKickOut(params)
	t.Log("Kick out group members result:", formatJSON(result))
	t.Log("Error:", err)
}

// Test kicking out user from all groups
func TestRongCloud_EntrustGroupKickOutAllGroups(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	result, err := rc.EntrustGroupKickOutAllGroups("user005")
	t.Log("Kick out user from all groups result:", formatJSON(result))
	t.Log("Error:", err)
}

// Test dismissing group
func TestRongCloud_EntrustGroupDismiss(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	result, err := rc.EntrustGroupDismiss("entrust_group_001")
	t.Log("Dismiss group result:", formatJSON(result))
	t.Log("Error:", err)
}

// Test joining group
func TestRongCloud_EntrustGroupJoin(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	result, err := rc.EntrustGroupJoin("entrust_group_002", "user006", "user007")
	t.Log("Join group result:", formatJSON(result))
	t.Log("Error:", err)
}

// Test transferring group owner - simplified
func TestRongCloud_EntrustGroupTransferOwner(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	params := TransferOwnerModel{
		GroupId:       "entrust_group_002",
		NewOwner:      "user002",
		IsDelBan:      IntPtr(1), // Delete ban
		IsDelWhite:    IntPtr(1), // Delete whitelist
		IsDelFollowed: IntPtr(1), // Delete follow
		IsQuit:        IntPtr(0), // Keep original owner in group
	}

	result, err := rc.EntrustGroupTransferOwner(params)
	t.Log("Transfer group owner result:", formatJSON(result))
	t.Log("Error:", err)
}

// Test importing group
func TestRongCloud_EntrustGroupImportGroup(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	group := ImportEntrustGroupModel{
		GroupId:         "entrust_group_import_001",
		Name:            "Imported Entrust Group",
		Owner:           "user001",
		GroupProfile:    `{"portraitUrl":"http://test_url"}`,
		GroupExtProfile: `{"ext_00":"ext_00v"}`,
		Permissions:     `{"joinPerm":1}`,
	}

	result, err := rc.EntrustGroupImportGroup(group)
	t.Log("Import group result:", formatJSON(result))
	t.Log("Error:", err)
}

// Test adding managers
func TestRongCloud_EntrustGroupAddManagers(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	result, err := rc.EntrustGroupAddManagers("entrust_group_001", "user002", "user003")
	t.Log("Add managers result:", formatJSON(result))
	t.Log("Error:", err)
}

// Test removing managers
func TestRongCloud_EntrustGroupRemoveManagers(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	result, err := rc.EntrustGroupRemoveManagers("entrust_group_001", "user003")
	t.Log("Remove managers result:", formatJSON(result))
	t.Log("Error:", err)
}

// Test paging query group members
func TestRongCloud_EntrustGroupPagingQueryMembers(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	pageQuery := PagingQueryMembersModel{
		GroupId:   "entrust_group_001",
		Type:      0,
		PageToken: "",
		Size:      20,
		Order:     OrderDesc,
	}

	result, err := rc.EntrustGroupPagingQueryMembers(pageQuery)
	t.Log("Paging query group members result:", formatJSON(result))
	t.Log("Error:", err)
}

// Test querying specific members by user IDs
func TestRongCloud_EntrustGroupQueryMembersByUserIds(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	result, err := rc.EntrustGroupQueryMembersByUserIds("entrust_group_001", "user001", "user002")
	t.Log("Query specific members by user IDs result:", formatJSON(result))
	t.Log("Error:", err)
}

// Test setting member information
func TestRongCloud_EntrustGroupSetMemberInfo(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	memberInfo := MemberInfoModel{
		GroupId:  "entrust_group_002",
		UserId:   "user001",
		Nickname: "User Nickname",
		Extra:    "Additional info",
	}

	result, err := rc.EntrustGroupSetMemberInfo(memberInfo)
	t.Log("Set member information result:", formatJSON(result))
	t.Log("Error:", err)
}

// Test setting group remark name
func TestRongCloud_EntrustGroupSetRemarkName(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	remarkName := GroupRemarkNameModel{
		UserId:     "user001",
		GroupId:    "entrust_group_002",
		RemarkName: "My Group",
	}

	result, err := rc.EntrustGroupSetRemarkName(remarkName)
	t.Log("Set group remark name result:", formatJSON(result))
	t.Log("Error:", err)
}

// Test deleting group remark name
func TestRongCloud_EntrustGroupDelRemarkName(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	result, err := rc.EntrustGroupDelRemarkName("entrust_group_002", "user001")
	t.Log("Delete group remark name result:", formatJSON(result))
	t.Log("Error:", err)
}

// Test querying group remark name
func TestRongCloud_EntrustGroupQueryRemarkName(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	result, err := rc.EntrustGroupQueryRemarkName("entrust_group_002", "user001")
	t.Log("Query group remark name result:", formatJSON(result))
	t.Log("Error:", err)
}

// Test following group members
func TestRongCloud_EntrustGroupFollowMember(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	result, err := rc.EntrustGroupFollowMember("entrust_group_001", "user001", "user002", "user003")
	t.Log("Follow group members result:", formatJSON(result))
	t.Log("Error:", err)
}

// Test unfollowing group members
func TestRongCloud_EntrustGroupUnfollowMember(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	result, err := rc.EntrustGroupUnfollowMember("entrust_group_001", "user001", "user003")
	t.Log("Unfollow group members result:", formatJSON(result))
	t.Log("Error:", err)
}

// Test getting followed group members
func TestRongCloud_EntrustGroupGetFollowedMember(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	result, err := rc.EntrustGroupGetFollowedMember("entrust_group_001", "user001")
	t.Log("Get followed group members result:", formatJSON(result))
	t.Log("Error:", err)
}

// Test paging query groups
func TestRongCloud_EntrustGroupPagingQueryGroups(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	pageModel := PageModel{
		PageToken: "",
		Size:      20,
		Order:     OrderDesc,
	}

	result, err := rc.EntrustGroupPagingQueryGroups(pageModel)
	t.Log("Paging query groups result:", formatJSON(result))
	t.Log("Error:", err)
}

// Test paging query joined groups - simplified
func TestRongCloud_EntrustGroupPagingQueryJoinedGroups(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	pageModel := QueryJoinedGroupsModel{
		UserId:    "user001",
		Role:      0,
		PageToken: "",
		Size:      20,
		Order:     IntPtr(OrderDesc), // Use IntPtr directly
	}

	result, err := rc.EntrustGroupPagingQueryJoinedGroups(pageModel)
	t.Log("Paging query joined groups result:", formatJSON(result))
	t.Log("Error:", err)
}

// Test demonstrating simple usage patterns
func TestRongCloud_EntrustGroupSimpleUsage(t *testing.T) {
	t.Log("=== Simple Usage Examples ===")

	// Example 1: Use server defaults (don't set optional parameters)
	t.Log("1. Server defaults (nil values):")
	params1 := QuitEntrustGroupModel{
		GroupId: "group_001",
		UserIds: []string{"user001"},
		// All optional parameters are nil - server uses defaults
	}
	t.Log("Parameters:", formatJSON(params1))
	t.Log("URL: groupId=group_001&userIds=user001")

	// Example 2: Keep all records (explicit 0)
	t.Log("2. Keep all records (0=keep):")
	params2 := QuitEntrustGroupModel{
		GroupId:       "group_002",
		UserIds:       []string{"user002"},
		IsDelBan:      IntPtr(0), // Keep ban
		IsDelWhite:    IntPtr(0), // Keep whitelist
		IsDelFollowed: IntPtr(0), // Keep follow
	}
	t.Log("Parameters:", formatJSON(params2))
	t.Log("URL: groupId=group_002&userIds=user002&isDelBan=0&isDelWhite=0&isDelFollowed=0")

	// Example 3: Delete all records (explicit 1)
	t.Log("3. Delete all records (1=delete):")
	params3 := QuitEntrustGroupModel{
		GroupId:       "group_003",
		UserIds:       []string{"user003"},
		IsDelBan:      IntPtr(1), // Delete ban
		IsDelWhite:    IntPtr(1), // Delete whitelist
		IsDelFollowed: IntPtr(1), // Delete follow
	}
	t.Log("Parameters:", formatJSON(params3))
	t.Log("URL: groupId=group_003&userIds=user003&isDelBan=1&isDelWhite=1&isDelFollowed=1")

	// Example 4: Mixed settings
	t.Log("4. Mixed settings:")
	params4 := QuitEntrustGroupModel{
		GroupId:       "group_004",
		UserIds:       []string{"user004"},
		IsDelBan:      IntPtr(1), // Delete ban
		IsDelWhite:    IntPtr(0), // Keep whitelist
		IsDelFollowed: nil,       // Use server default
	}
	t.Log("Parameters:", formatJSON(params4))
	t.Log("URL: groupId=group_004&userIds=user004&isDelBan=1&isDelWhite=0")

	// Example 5: Pagination examples
	t.Log("5. Pagination examples:")

	// Ascending order
	pageAsc := QueryJoinedGroupsModel{
		UserId:    "user001",
		Role:      0,
		PageToken: "",
		Size:      20,
		Order:     IntPtr(OrderAsc), // 0=ascending
	}
	t.Log("Ascending order:", formatJSON(pageAsc))

	// Descending order
	pageDesc := QueryJoinedGroupsModel{
		UserId:    "user001",
		Role:      0,
		PageToken: "",
		Size:      20,
		Order:     IntPtr(OrderDesc), // 1=descending
	}
	t.Log("Descending order:", formatJSON(pageDesc))

	// No order specified (server default)
	pageDefault := QueryJoinedGroupsModel{
		UserId:    "user001",
		Role:      0,
		PageToken: "",
		Size:      20,
		Order:     nil, // Use server default
	}
	t.Log("Default order:", formatJSON(pageDefault))
}
