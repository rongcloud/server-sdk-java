package user

import (
	"io/ioutil"
	"encoding/json"
	"time"
	"github.com/astaxie/beego/httplib"
	"github.com/rongcloud/server-sdk-go/rcserversdk"
	"server-sdk-go/sdk"
)


//  UserReslut User 返回信息
type UserReslut struct {
	Code int `json:"code"`
	Token string `json:"token"`
	UserId string `json:"userId"`
	ErrorMessage string `json:"errorMessage"`
}

// User user模块
type User struct {
	*rcserversdk.RongCloud
}




/*
	*注册用户，生成用户在融云的唯一身份标识 Token 方法
	*
	*@param  userId:用户 Id，最大长度 64 字节.是用户在 App 中的唯一标识码，必须保证在同一个 App 内不重复，重复的用户 Id 将被当作是同一用户。
	*@param  name:用户名称，最大长度 128 字节.用来在 Push 推送时显示用户的名称.用户名称，最大长度 128 字节.用来在 Push 推送时显示用户的名称。
	*@param  portraitUri:用户头像 URI，最大长度 1024 字节.用来在 Push 推送时显示用户的头像。
	*
	*@return UserReslut, RCError
*/

// Register 注册用户，生成用户在融云的唯一身份标识 Token
func (u *User)Register(userId, name, portraitUri string) (*UserReslut, *rcserversdk.RCError) {

	if (userId == "" ){
		return nil ,rcserversdk.RCErrorNew(20005,"Paramer 'userId' is required");
	}
	if (name == "" ){
		return nil ,rcserversdk.RCErrorNew(20005,"Paramer 'name' is required");
	}
	if (portraitUri == "" ){
		return nil ,rcserversdk.RCErrorNew(20005,"Paramer 'portraitUri' is required");
	}

	req := httplib.Post(rcserversdk.RONGCLOUDURI + "/user/getToken." + rcserversdk.ReqType)
	req.SetTimeout(time.Second * 5, time.Second * 5)
	u.FillHeader(req)

	req.Param("userId",userId)

	req.Param("name", name)
	req.Param("portraitUri", portraitUri)



	rep, err := req.DoRequest()
	if err != nil {
		return nil,rcserversdk.RCErrorNew(20013,err.Error());
	}

	body, err := ioutil.ReadAll(rep.Body)
	if err != nil {
		return nil,rcserversdk.RCErrorNew(20013,err.Error());
	}

	var dat map[string]interface{}
	if err := json.Unmarshal(body, &dat); err != nil {
		return nil,rcserversdk.RCErrorNew(20013,err.Error());
	}

	if int(dat["code"].(float64)) != 200 {
		return nil,rcserversdk.RCErrorNew(20013,err.Error());
	}

	var userReslut UserReslut
	json.Unmarshal(body,&userReslut)

	return &userReslut,nil
}

/*
	*修改用户信息方法
	*
	*@param  userId:用户 Id，最大长度 64 字节.是用户在 App 中的唯一标识码，必须保证在同一个 App 内不重复，重复的用户 Id 将被当作是同一用户。
	*@param  name:用户名称，最大长度 128 字节。用来在 Push 推送时，显示用户的名称，刷新用户名称后 5 分钟内生效。（可选，提供即刷新，不提供忽略）
	*@param  portraitUri:用户头像 URI，最大长度 1024 字节。用来在 Push 推送时显示。（可选，提供即刷新，不提供忽略）
	*
	*@return RCError
*/

// Update 修改用户信息
func (u *User)Update(userId, name, portraitUri string) *sdk.RCError {

	if( userId == "") {
		return sdk.RCErrorNew(20005,"Paramer 'userId' is required");
	}
	if (name == "" ){
		return sdk.RCErrorNew(20005,"Paramer 'name' is required");
	}
	if (portraitUri == "" ){
		return sdk.RCErrorNew(20005,"Paramer 'portraitUri' is required");
	}

	req := httplib.Post(sdk.RONGCLOUDURI + "/user/refresh." + sdk.ReqType)
	req.SetTimeout(time.Second * 5, time.Second * 5)
	u.FillHeader(req)
	req.Param("userId", userId)
	req.Param("name", name)
	req.Param("portraitUri", portraitUri)

	rep, err := req.DoRequest()
	if err != nil {
		return sdk.RCErrorNew(20013,err.Error());
	}

	body, err := ioutil.ReadAll(rep.Body)
	if err != nil {
		return sdk.RCErrorNew(20013,err.Error());
	}

	var dat map[string]interface{}
	if err := json.Unmarshal(body, &dat); err != nil {
		return sdk.RCErrorNew(20013,err.Error());
	}

	if int(dat["code"].(float64)) != 200 {
		return sdk.RCErrorNew(20013,err.Error());
	}
	return nil;
}


