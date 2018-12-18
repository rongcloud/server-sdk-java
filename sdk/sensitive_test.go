package sdk

import (
	"fmt"
	"testing"
)

func TestRongCloud_SensitiveAdd(t *testing.T) {

	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)

	err := rc.SensitiveAdd(
		"7Szq13MKRVortoknTAk7W8",
		"7Szq13MKRVortoknTAk7W8",
		1,
	)

	if err != nil {
		t.Fatal(err)
	}
}

func TestRongCloud_SensitiveGetList(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)

	rep, err := rc.SensitiveGetList()
	if err == nil {
		fmt.Println(rep)
	}
	if err != nil {
		t.Fatal(err)
	}
}

func TestRongCloud_SensitiveRemove(t *testing.T) {

	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)

	err := rc.SensitiveRemove(
		[]string{"7Szq13MKRVortoknTAk7W8"},
	)

	if err != nil {
		t.Fatal(err)
	}

}
