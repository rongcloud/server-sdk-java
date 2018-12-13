package user

import (
	"testing"
	"fmt"
	"github.com/rongcloud/server-sdk-go/rcserversdk"
	)

func TestUser_Register(t *testing.T) {

	fmt.Println("========TestUser_Register========")
	user := User{
		&rcserversdk.RongCloud{
			AppKey: "25wehl3u2shww",	//appkey
			AppSecret: "trU1ZBmeAPg08J", //appsecret
			RongCloudURI: rcserversdk.RONGCLOUDURI,
			RongCloudSMSURI: rcserversdk.RONGCLOUDSMSURI,
		},
	}

	//res := UserOption{Name:"u02",PortraitUri:"http://rongcloud.cn/portrait.jpg"}

	rep, err := user.Register(
		"u01",
		"u01",
		"http://rongcloud.cn/portrait.jpg",
		)
	if err == nil {
		fmt.Println(rep)
	}
	fmt.Println(err)
	fmt.Println("========End========")
}

func TestUser_Update(t *testing.T) {

	fmt.Println("========TestUser_Update========")
	users := User{
		&rcserversdk.RongCloud{
			AppKey: "25wehl3u2shww",	//appkey
			AppSecret: "trU1ZBmeAPg08J", //appsecret
		},
	}
	rep := users.Update("u01","u01","http://rongcloud.cn/portrait.jpg")
	fmt.Println(rep)
	fmt.Println("========End========")
}