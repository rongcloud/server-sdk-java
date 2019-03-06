package sdk

import (
	"fmt"
	"testing"
)

func TestRongCloud_ConversationMute(t *testing.T) {
	conversation := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	err := conversation.ConversationMute(
		PRIVATE,
		"u01",
		"u02")

	fmt.Println(err)

}

func TestRongCloud_ConversationUnmute(t *testing.T) {
	conversation := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	err := conversation.ConversationMute(
		PRIVATE,
		"u01",
		"u02")

	fmt.Println(err)

}

func TestRongCloud_ConversationGet(t *testing.T) {
	conversation := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	isMuted, err := conversation.ConversationGet(
		PRIVATE,
		"u01",
		"u02")

	fmt.Println(err)
	fmt.Println(isMuted)

}
