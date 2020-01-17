/*
 * @Descripttion:
 * @version:
 * @Author: ran.ding
 * @Date: 2019-09-02 18:29:55
 * @LastEditors: ran.ding
 * @LastEditTime: 2019-09-10 12:14:41
 */
package sdk

import (
	"os"
	"testing"
)

func TestQueryWhiteList(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	_, err := rc.QueryWhiteList("123")
	if err != nil {
		t.Errorf("ERROR: %v", err)
	} else {
		t.Log("PASS")
	}
}

func TestRemoveWhiteList(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	if err := rc.RemoveWhiteList("123", []string{"234", "456"}); err != nil {
		t.Errorf("ERROR: %v", err)
	} else {
		t.Log("PASS")
	}
}

func TestAddWhiteList(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	if err := rc.AddWhiteList("123", []string{"234", "345"}); err != nil {
		t.Errorf("ERROR: %v", err)
	} else {
		t.Log("PASS")
	}
}

func TestRongCloud_UserRegister(t *testing.T) {

	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	rep, err := rc.UserRegister(
		"u01",
		"name01",
		"http://rongcloud.cn/portrait.jpg",
	)
	t.Log(err)
	t.Log(rep)
}

func TestRongCloud_UserUpdate(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	rep := rc.UserUpdate("7Szq13MKRVortoknTAk7W8", "7Szq13MKRVortoknTAk7W8", "http://rongcloud.cn/portrait.jpg")
	t.Log(rep)
}

func TestRongCloud_BlockAdd(t *testing.T) {

	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	err := rc.BlockAdd(
		"4kIvGJmETlYqDoVFgWdYdM",
		5,
	)
	t.Log(err)
}

func TestRongCloud_BlockGetList(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)
	rep, err := rc.BlockGetList()
	t.Log(err)
	t.Log(rep)
}

func TestRongCloud_BlockRemove(t *testing.T) {

	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)
	err := rc.BlockRemove(
		"4kIvGJmETlYqDoVFgWdYdM",
	)
	t.Log(err)
}

func TestRongCloud_BlacklistAdd(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)
	err := rc.BlacklistAdd(
		"4kIvGJmETlYqDoVFgWdYdM",
		[]string{"u01"},
	)

	t.Log(err)
}

func TestRongCloud_BlacklistGetList(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)
	req, err := rc.BlacklistGet(
		"4kIvGJmETlYqDoVFgWdYdM",
	)
	t.Log(err)
	t.Log(req)
}

func TestRongCloud_BlacklistRemove(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)
	err := rc.BlacklistRemove(
		"4kIvGJmETlYqDoVFgWdYdM",
		[]string{"u01"},
	)
	t.Log(err)
}

func TestRongCloud_OnlineStatusCheck(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)
	status, err := rc.OnlineStatusCheck("4kIvGJmETlYqDoVFgWdYdM")
	t.Log(err)
	t.Log(status)
}

func TestRongCloud_TagSet(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
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
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
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
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	result, err := rc.TagGet(
		[]string{
			"u01",
			"u02",
			"u03",
		},
	)
	if err == nil {
		for k, v := range result.Result {
			t.Log(k)
			for _, tag := range v {
				t.Log(tag)
			}
		}
	}
	t.Log(err)
}
