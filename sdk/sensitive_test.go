package sdk

import (
	"fmt"
	"testing"
)

func TestRongCloud_SensitiveAdd(t *testing.T) {

	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)

	err := rc.SensitiveAdd(
		"7Szq13MKRVortoknTAk7W8",
		"7Szq13MKRVortoknTAk7W8",
		1,
	)

	fmt.Println(err)
}

func TestRongCloud_SensitiveGetList(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)

	rep, err := rc.SensitiveGetList()
	if err == nil {
		fmt.Println(rep)
	}
	fmt.Println(err)
}

func TestRongCloud_SensitiveRemove(t *testing.T) {

	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)

	err := rc.SensitiveRemove(
		[]string{"7Szq13MKRVortoknTAk7W8"},
	)

	fmt.Println(err)
}
