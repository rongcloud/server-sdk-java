package sdk

import (
	"os"
	"testing"
)

func TestRongCloud_ConversationTop(t *testing.T) {
	conversation := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)
	err := conversation.ConversationTop(
		PRIVATE,
		"u01",
		"u02",
		"true",
	)
	t.Log(err)
}

func TestRongCloud_ConversationMute(t *testing.T) {
	conversation := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)
	err := conversation.ConversationMute(
		PRIVATE,
		"u01",
		"u02",
	)
	t.Log(err)

}

func TestRongCloud_ConversationUnmute(t *testing.T) {
	conversation := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)
	err := conversation.ConversationMute(
		PRIVATE,
		"u01",
		"u02",
	)

	t.Log(err)

}

func TestRongCloud_ConversationGet(t *testing.T) {
	conversation := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)
	isMuted, err := conversation.ConversationGet(
		PRIVATE,
		"u01",
		"u02")

	t.Log(err)
	t.Log(isMuted)

}
