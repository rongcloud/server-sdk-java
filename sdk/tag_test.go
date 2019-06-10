package sdk

import (
	"os"
	"testing"
)

func TestRongCloud_TagSet(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("RC_APP_ID"),
		os.Getenv("RC_APP_SECRET"),
	)

	err := rc.TagSet(
		Tag{
			UserID: "u01",
			Tags: []string{
				"男",
			},
		})
	t.Log(err)
}

func TestRongCloud_TagBatchSet(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("RC_APP_ID"),
		os.Getenv("RC_APP_SECRET"),
	)

	err := rc.TagBatchSet(
		TagBatch{
			UserIDs: []string{
				"u02",
				"u03",
			},
			Tags: []string{
				"男",
				"bj",
			},
		})
	t.Log(err)
}

func TestRongCloud_TagGet(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("RC_APP_ID"),
		os.Getenv("RC_APP_SECRET"),
	)

	result, err := rc.TagGet(
		[]string{
			"u01",
			"u02",
			"u03",
		},
	)
	if err != nil {
		t.Log(result)
	}
	t.Log(err)
}
