package sdk

import (
	"encoding/json"
	"os"
	"testing"
)

// Helper function to format result as JSON
func formatFriendJSON(data interface{}) string {
	jsonBytes, err := json.MarshalIndent(data, "", "  ")
	if err != nil {
		return "Failed to marshal JSON: " + err.Error()
	}
	return string(jsonBytes)
}

// Test adding a friend
func TestRongCloud_FriendAdd(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	friend := FriendModel{
		UserId:   "user001",
		TargetId: "user002",
		OptType:  intPtr(2),
		Extra:    `{"source":"test"}`,
	}

	result, err := rc.FriendAdd(friend)
	t.Log("Add friend result:", formatFriendJSON(result))
	t.Log("Error:", err)
}

// Test deleting friends
func TestRongCloud_FriendDelete(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	result, err := rc.FriendDelete("user001", "user002")
	t.Log("Delete friends result:", formatFriendJSON(result))
	t.Log("Error:", err)
}

// Test cleaning all friends
func TestRongCloud_FriendClean(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	result, err := rc.FriendClean("user001")
	t.Log("Clean all friends result:", formatFriendJSON(result))
	t.Log("Error:", err)
}

// Test setting friend profile
func TestRongCloud_FriendSetProfile(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	profileModel := FriendProfileModel{
		UserId:           "user001",
		TargetId:         "user002",
		RemarkName:       "Best Friend",
		FriendExtProfile: ``,
	}

	result, err := rc.FriendSetProfile(profileModel)
	t.Log("Set friend profile result:", formatFriendJSON(result))
	t.Log("Error:", err)
}

// Test paging get friends
func TestRongCloud_PagingGetFriends(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	getFriendsModel := PagingGetFriendsModel{
		UserId:    "user001",
		PageToken: "",
		Size:      20,
		Order:     IntPtr(OrderDesc),
	}

	result, err := rc.PagingGetFriends(getFriendsModel)
	t.Log("Paging get friends result:", formatFriendJSON(result))
	t.Log("Error:", err)
}

// Test checking friend relationships
func TestRongCloud_FriendCheckFriends(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	result, err := rc.FriendCheckFriends("user001", "user002", "user003", "user004")
	t.Log("Check friends result:", formatFriendJSON(result))
	t.Log("Error:", err)
}

// Test setting friend permission
func TestRongCloud_FriendSetPermission(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	result, err := rc.FriendSetPermission(1, "user001", "user002")
	t.Log("Set friend permission result:", formatFriendJSON(result))
	t.Log("Error:", err)
}

// Test getting friend permission
func TestRongCloud_FriendGetPermission(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	result, err := rc.FriendGetPermission("user001", "user002", "user003")
	t.Log("Get friend permission result:", formatFriendJSON(result))
	t.Log("Error:", err)
}
