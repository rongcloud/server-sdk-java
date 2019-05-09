package sdk

import (
	"testing"
)

func TestRongCloud_ConversationMute(t *testing.T) {
	conversation := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
	)
	err := conversation.ConversationMute(
		PRIVATE,
		"u01",
		"u02")

	t.Log(err)

}

func TestRongCloud_ConversationUnmute(t *testing.T) {
	conversation := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
	)
	err := conversation.ConversationMute(
		PRIVATE,
		"u01",
		"u02")

	t.Log(err)

}

func TestRongCloud_ConversationGet(t *testing.T) {
	conversation := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
	)
	isMuted, err := conversation.ConversationGet(
		PRIVATE,
		"u01",
		"u02")

	t.Log(err)
	t.Log(isMuted)

}
