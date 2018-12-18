package sdk

import (
	"fmt"
	"testing"

	"github.com/astaxie/beego/httplib"
)

func TestNewRongCloud(t *testing.T) {
	rcex := RongCloudExtra{
		RongCloudURI:    "http://172",
		RongCloudSMSURI: "123",
		TimeOut:         30,
	}
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		&rcex,
	)
	fmt.Println(rc)
}

func TestRongCloud_FillHeader(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	req := httplib.Post(rc.RongCloudURI)
	rc.FillHeader(req)
	fmt.Println(req.GetRequest().Header)
}
