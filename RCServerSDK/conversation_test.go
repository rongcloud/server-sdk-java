package RCserverSDK

import (
	"testing"
		)

func TestRongCloud_ConversationMute(t *testing.T) {
	conversation := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	err := conversation.ConversationMute(
		PRIVATE,
		"u01",
		"u02")

	if err != nil{
		t.Fatal(err)
	}

}

func TestRongCloud_ConversationUnmute(t *testing.T) {
	conversation := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	err := conversation.ConversationMute(
		PRIVATE,
		"u01",
		"u02")

	if err != nil{
		t.Fatal(err)
	}

}
