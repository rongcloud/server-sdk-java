package RCserverSDK

import(
	"errors"
	"github.com/astaxie/beego/httplib"
	"encoding/json"
	"strconv"
)


// ChatRoomInfo 聊天室信息
type ChatRoomInfo struct {
	ID string `json:"id"`
	Name string `json:"name"`
}


// ChatroomQueryReslut chatroomQuery 返回结果
//type ChatroomQueryReslut struct {
//	Code int `json:"code"`
//	ChatRooms []ChatRoom `json:"chatRooms"`
//	ErrorMessage string `json:"errorMessage"`
//}


// ChatRoom 聊天室信息
type ChatRoom struct {
	ChrmId string `json:"chrmId"`
	Name string `json:"name"`
	Time string `json:"time"`
}

// ChatroomReslut Chatroom 返回结果
type ChatroomReslut struct {
	Total int `json:"total"`
	Users []ChatRoomUser `json:"users"`
	Reslut []ChatRoomUser `json:"reslut"`
	ObjectNames []string `json:"objectNames"`
	ChatroomIds []string `json:"chatroomids"`
}

// ChatRoomUser 聊天室用户信息
type ChatRoomUser struct {
	ID string `json:"id"`
	UserID string `json:"userId"`
	Time string `json:"time"`
	IsInChrm int `json:"isInChrm"`
}



//BlockChatRoomUser 聊天室被封禁用户信息。

type BlockChatRoomUser struct {
	UserId string `json:"userId"`
	Time string `json:"time"`
}

/**
 *创建聊天室方法
 *
 *@param  chatRoomInfo:id:要创建的聊天室的id；name:要创建的聊天室的name。（必传）
 *
 *@return error
 */
func (rc *RongCloud) ChatroomCreate (id, name string) error {
	if(id == "") {
		return RCErrorNew(20005,"Paramer 'id' is required");
	}
	if(name == "") {
		return RCErrorNew(20005,"Paramer 'name' is required");
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/create." + ReqType)
	rc.FillHeader(req)

	req.Param("chatroom[" + id + "]", name)

	rep, err := req.Bytes()
	if err != nil {
		return err
	}
	var code CodeReslut
	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}
	if code.Code != 200 {
		return RCErrorNew(code.Code, code.ErrorMessage)
	}
	return nil
}

/**
 *销毁聊天室方法
 *
 *@param  chatroomId:要销毁的聊天室 Id。（必传）
 *
 *@return CodeSuccessReslut
 */
func (rc *RongCloud) ChatroomDestroy (id string) error {
	if(id == "") {
		return RCErrorNew(20005,"Paramer 'id' is required");
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/destroy." + ReqType)
	rc.FillHeader(req)

	req.Param("chatroomId", id)

	rep, err := req.Bytes()
	if err != nil {
		return err
	}
	var code CodeReslut
	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}
	if code.Code != 200 {
		return RCErrorNew(code.Code, code.ErrorMessage)
	}
	return nil
}

/**
 *查询聊天室内用户方法
 *
 *@param  chatroomId:要查询的聊天室 ID。（必传）
 *@param  count:要获取的聊天室成员数，上限为 500 ，超过 500 时最多返回 500 个成员。（必传）
 *@param  order:加入聊天室的先后顺序， 1 为加入时间正序， 2 为加入时间倒序。（必传）
 *
 *@return ChatroomUserQueryReslut
 */
func (rc *RongCloud) ChatroomGet (id string, count, order int)(ChatroomReslut, error) {
	if(id == "") {
		return ChatroomReslut{}, errors.New("Paramer 'chatroomId' is required");
	}

	if( count <= 0) {
		return ChatroomReslut{},errors.New("Paramer 'count' is required");
	}

	if( order <= 0) {
		return ChatroomReslut{},errors.New("Paramer 'order' is required");
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/user/query." + ReqType)
	rc.FillHeader(req)
	req.Param("chatroomId", id)
	req.Param("count", strconv.Itoa(count))
	req.Param("order", strconv.Itoa(order))
	rep, err := req.Bytes()
	if err != nil {
		return ChatroomReslut{},err
	}
	var code CodeReslut
	if err := json.Unmarshal(rep, &code); err != nil {
		return ChatroomReslut{},err
	}
	if code.Code != 200 {
		return ChatroomReslut{},RCErrorNew(code.Code, code.ErrorMessage)
	}

	var dat ChatroomReslut
	if err := json.Unmarshal(rep, &dat); err != nil {
		return ChatroomReslut{},err
	}
	return dat,nil
}

func (rc *RongCloud) ChatroomisExist (id string, members []string)([]ChatRoomUser, error) {
	if(id == "") {
		return []ChatRoomUser{}, errors.New("Paramer 'chatroomId' is required");
	}

	if(len(members) == 0) {
		return []ChatRoomUser{},errors.New("Paramer 'count' is required");
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/users/exist." + ReqType)
	rc.FillHeader(req)
	req.Param("chatroomId", id)
	for _, v := range members{
		req.Param("userId", v)
	}

	rep, err := req.Bytes()
	if err != nil {
		return []ChatRoomUser{},err
	}
	var code CodeReslut
	if err := json.Unmarshal(rep, &code); err != nil {
		return []ChatRoomUser{},err
	}
	if code.Code != 200 {
		return []ChatRoomUser{},RCErrorNew(code.Code, code.ErrorMessage)
	}

	var dat ChatroomReslut
	if err := json.Unmarshal(rep, &dat); err != nil {
		return []ChatRoomUser{},err
	}

	return dat.Reslut, nil
}


/**
 *添加封禁聊天室成员方法
 *
 *@param  userId:用户 Id。（必传）
 *@param  chatroomId:聊天室 Id。（必传）
 *@param  minute:封禁时长，以分钟为单位，最大值为43200分钟。（必传）
 *
 *@return CodeSuccessReslut
 */
func (rc *RongCloud) ChatroomBlockAdd (id string, members []string, minute uint) error {
	if(id == "") {
		return errors.New("Paramer 'id' is required");
	}

	if(len(members) == 0) {
		return errors.New("Paramer 'members' is required");
	}

	if(minute == 0) {
		return errors.New("Paramer 'minute' is required");
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/user/block/add." + ReqType)
	rc.FillHeader(req)
	req.Param("chatroomId", id)
	for _,v := range members{
		req.Param("userId", v)
	}

	req.Param("minute", strconv.Itoa(int(minute)))
	rep, err := req.Bytes()
	if err != nil {
		return err
	}
	var code CodeReslut
	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}
	if code.Code != 200 {
		return RCErrorNew(code.Code, code.ErrorMessage)
	}
	return nil
}


/**
 *移除封禁聊天室成员方法
 *
 *@param  userId:用户 Id。（必传）
 *@param  chatroomId:聊天室 Id。（必传）
 *
 *@return CodeSuccessReslut
 */
func (rc *RongCloud) ChatroomBlockRemove (id string, members []string) error {
	if(id == "") {
		return errors.New("Paramer 'id' is required");
	}

	if(len(members) == 0) {
		return errors.New("Paramer 'members' is required");
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/user/block/rollback." + ReqType)
	rc.FillHeader(req)
	for _,v := range members{
		req.Param("userId", v)
	}
	req.Param("chatroomId", id)
	rep, err := req.Bytes()
	if err != nil {
		return err
	}
	var code CodeReslut
	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}
	if code.Code != 200 {
		return RCErrorNew(code.Code, code.ErrorMessage)
	}
	return nil
}

/**
 *查询被封禁聊天室成员方法
 *
 *@param  chatroomId:聊天室 Id。（必传）
 *
 *@return ListBlockChatroomUserReslut
 */
func (rc *RongCloud) ChatroomBlockGetList (id string)( ChatroomReslut, error) {
	var dat ChatroomReslut
	var code CodeReslut
	if(id == "") {
		return dat,errors.New("Paramer 'chatroomId' is required");
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/user/block/list." + ReqType)
	rc.FillHeader(req)
	req.Param("chatroomId", id)
	rep, err := req.Bytes()
	if err != nil {
		return dat,err
	}

	if err := json.Unmarshal(rep, &code); err != nil {
		return dat,err
	}
	if code.Code != 200 {
		return dat,RCErrorNew(code.Code, code.ErrorMessage)
	}

	if err := json.Unmarshal(rep, &dat); err != nil {
		return dat,err
	}

	return dat,nil
}

func (rc *RongCloud) ChatroomBanAdd (members []string, minute uint) error {

	var code CodeReslut
	if(len(members) == 0) {
		return errors.New("Paramer 'members' is required");
	}
	if (minute == 0) {
		return errors.New("Paramer 'minute' is required");
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/user/ban/add." + ReqType)
	rc.FillHeader(req)
	for _, v := range members {
		req.Param("userId", v)
	}
	req.Param("minute",strconv.Itoa(int(minute)))
	rep, err := req.Bytes()
	if err != nil {
		return err
	}

	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}
	if code.Code != 200 {
		return RCErrorNew(code.Code, code.ErrorMessage)
	}

	return nil
}

func (rc *RongCloud) ChatroomBanRemove (members []string) error {

	var code CodeReslut
	if(len(members) == 0) {
		return errors.New("Paramer 'members' is required");
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/user/ban/remove." + ReqType)
	rc.FillHeader(req)
	for _, v := range members {
		req.Param("userId", v)
	}
	rep, err := req.Bytes()
	if err != nil {
		return err
	}

	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}
	if code.Code != 200 {
		return RCErrorNew(code.Code, code.ErrorMessage)
	}

	return nil
}

func (rc *RongCloud) ChatroomBanGetList () ([]ChatRoomUser,error) {

	var code CodeReslut
	var dat ChatroomReslut
	req := httplib.Post(rc.RongCloudURI + "/chatroom/user/ban/query." + ReqType)
	rc.FillHeader(req)

	rep, err := req.Bytes()
	if err != nil {
		return []ChatRoomUser{},err
	}

	if err := json.Unmarshal(rep, &code); err != nil {
		return []ChatRoomUser{},err
	}
	if code.Code != 200 {
		return []ChatRoomUser{},RCErrorNew(code.Code, code.ErrorMessage)
	}
	if err := json.Unmarshal(rep, &dat); err != nil {
		return []ChatRoomUser{},err
	}
	return dat.Users, nil
}

/**
 *添加禁言聊天室成员方法（在 App 中如果不想让某一用户在聊天室中发言时，可将此用户在聊天室中禁言，被禁言用户可以接收查看聊天室中用户聊天信息，但不能发送消息.）
 *
 *@param  userId:用户 Id。（必传）
 *@param  chatroomId:聊天室 Id。（必传）
 *@param  minute:禁言时长，以分钟为单位，最大值为43200分钟。（必传）
 *
 *@return CodeSuccessReslut
 */
func (rc *RongCloud) ChatroomGagAdd (id string, members []string, minute uint) error {
	if(id == "") {
		return errors.New("Paramer 'userId' is required");
	}

	if(len(members) == 0) {
		return errors.New("Paramer 'members' is required");
	}

	if(minute == 0) {
		return errors.New("Paramer 'minute' is required");
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/user/gag/add." + ReqType)
	rc.FillHeader(req)
	for _, v := range members{
		req.Param("userId", v)
	}

	req.Param("chatroomId", id)
	req.Param("minute", strconv.Itoa(int(minute)))
	rep, err := req.Bytes()
	if err != nil {
		return err
	}
	var code CodeReslut
	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}
	if code.Code != 200 {
		return RCErrorNew(code.Code, code.ErrorMessage)
	}
	return nil
}


/**
 *移除禁言聊天室成员方法
 *
 *@param  userId:用户 Id。（必传）
 *@param  chatroomId:聊天室Id。（必传）
 *
 *@return CodeSuccessReslut
 */
func (rc *RongCloud) ChatroomGagRemove (id string, members []string)  error {
	if(id == "") {
		return errors.New("Paramer 'id' is required");
	}

	if(len(members) == 0) {
		return errors.New("Paramer 'members' is required");
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/user/gag/rollback." + ReqType)
	rc.FillHeader(req)
	for _, v := range members{
		req.Param("userId", v)
	}
	req.Param("chatroomId", id)
	rep, err := req.Bytes()
	if err != nil {
		return err
	}
	var code CodeReslut
	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}
	if code.Code != 200 {
		return RCErrorNew(code.Code, code.ErrorMessage)
	}
	return nil
}


/**
 *查询被禁言聊天室成员方法
 *
 *@param  id:聊天室 ID。（必传）
 *
 *@return []ChatRoomUser error
 */
func (rc *RongCloud) ChatroomGagGetList (chatroomId string)( []ChatRoomUser, error) {
	var dat ChatroomReslut
	if( chatroomId == "") {
		return []ChatRoomUser{},errors.New("Paramer 'chatroomId' is required");
	}

	req := httplib.Post(RONGCLOUDURI + "/chatroom/user/gag/list." + ReqType)
	rc.FillHeader(req)
	req.Param("chatroomId", chatroomId)
	rep, err := req.Bytes()
	if err != nil {
		return []ChatRoomUser{}, err
	}
	var code CodeReslut
	if err := json.Unmarshal(rep, &code); err != nil {
		return []ChatRoomUser{}, err
	}
	if code.Code != 200 {
		return []ChatRoomUser{}, RCErrorNew(code.Code, code.ErrorMessage)
	}
	if err := json.Unmarshal(rep, &dat); err != nil {
		return []ChatRoomUser{}, err
	}
	return dat.Users, nil
}


/**
 *添加聊天室消息优先级方法
 *
 *@param  objectName:低优先级的消息类型，每次最多提交 5 个，设置的消息类型最多不超过 20 个。（必传）
 *
 *@return err
 */
func (rc *RongCloud) ChatroomDemotionAdd (objectNames []string) error {
	if(len(objectNames) == 0) {
		return errors.New("Paramer 'objectName' is required");
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/message/priority/add." + ReqType)
	rc.FillHeader(req)
	for _,v := range objectNames {
		req.Param("objectName", v)
	}
	rep, err := req.Bytes()
	if err != nil {
		return err
	}
	var code CodeReslut
	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}
	if code.Code != 200 {
		return RCErrorNew(code.Code, code.ErrorMessage)
	}
	return nil
}

func (rc *RongCloud) ChatroomDemotionRemove (objectNames []string) error {
	if(len(objectNames) == 0) {
		return errors.New("Paramer 'objectName' is required");
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/message/priority/remove." + ReqType)
	rc.FillHeader(req)
	for _,v := range objectNames {
		req.Param("objectName", v)
	}
	rep, err := req.Bytes()
	if err != nil {
		return err
	}
	var code CodeReslut
	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}
	if code.Code != 200 {
		return RCErrorNew(code.Code, code.ErrorMessage)
	}
	return nil
}


func (rc *RongCloud) ChatroomDemotionGetList () ([]string, error) {
	var dat ChatroomReslut

	req := httplib.Post(rc.RongCloudURI + "/chatroom/message/priority/query." + ReqType)
	rc.FillHeader(req)
	rep, err := req.Bytes()
	if err != nil {
		return []string{}, err
	}
	var code CodeReslut
	if err := json.Unmarshal(rep, &code); err != nil {
		return []string{}, err
	}
	if code.Code != 200 {
		return []string{},RCErrorNew(code.Code, code.ErrorMessage)
	}
	if err := json.Unmarshal(rep, &dat); err != nil {
		return []string{}, err
	}
	return dat.ObjectNames, nil
}

/**
 *聊天室消息停止分发方法（可实现控制对聊天室中消息是否进行分发，停止分发后聊天室中用户发送的消息，融云服务端不会再将消息发送给聊天室中其他用户。）
 *
 *@param  chatroomId:聊天室 Id。（必传）
 *
 *@return CodeSuccessReslut
 */
func (rc *RongCloud) ChatroomDistributionStop (id string) error {
	if(id == "") {
		return errors.New("Paramer 'chatroomId' is required");
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/message/stopDistribution." + ReqType)
	rc.FillHeader(req)
	req.Param("chatroomId", id)
	rep, err := req.Bytes()
	if err != nil {
		return err
	}
	var code CodeReslut
	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}
	if code.Code != 200 {
		return RCErrorNew(code.Code, code.ErrorMessage)
	}
	return nil
}


/**
 *聊天室消息恢复分发方法
 *
 *@param  chatroomId:聊天室 Id。（必传）
 *
 *@return CodeSuccessReslut
 */
func (rc *RongCloud) ChatroomDistributionResume (id string) error {
	if(id == "") {
		return errors.New("Paramer 'chatroomId' is required");
	}
	req := httplib.Post(rc.RongCloudURI + "/chatroom/message/resumeDistribution." + ReqType)
	rc.FillHeader(req)
	req.Param("chatroomId", id)
	rep, err := req.Bytes()
	if err != nil {
		return err
	}
	var code CodeReslut
	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}
	if code.Code != 200 {
		return RCErrorNew(code.Code, code.ErrorMessage)
	}
	return nil
}



func (rc *RongCloud) ChatroomKeepAliveAdd (id string) error {
	if(id == "") {
		return errors.New("Paramer 'chatroomId' is required");
	}
	req := httplib.Post(rc.RongCloudURI + "/chatroom/keepalive/add." + ReqType)
	rc.FillHeader(req)
	req.Param("chatroomId", id)
	rep, err := req.Bytes()
	if err != nil {
		return err
	}
	var code CodeReslut
	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}
	if code.Code != 200 {
		return RCErrorNew(code.Code, code.ErrorMessage)
	}
	return nil
}

func (rc *RongCloud) ChatroomKeepAliveRemove (id string) error {
	if(id == "") {
		return errors.New("Paramer 'chatroomId' is required");
	}
	req := httplib.Post(rc.RongCloudURI + "/chatroom/keepalive/remove." + ReqType)
	rc.FillHeader(req)
	req.Param("chatroomId", id)
	rep, err := req.Bytes()
	if err != nil {
		return err
	}
	var code CodeReslut
	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}
	if code.Code != 200 {
		return RCErrorNew(code.Code, code.ErrorMessage)
	}
	return nil
}


func (rc *RongCloud) ChatroomKeepAliveGetList (id string) ([]string,error) {
	var dat ChatroomReslut
	if(id == "") {
		return []string{}, errors.New("Paramer 'chatroomId' is required");
	}
	req := httplib.Post(rc.RongCloudURI + "/chatroom/keepalive/query." + ReqType)
	rc.FillHeader(req)
	req.Param("chatroomId", id)
	rep, err := req.Bytes()
	if err != nil {
		return []string{}, err
	}
	var code CodeReslut
	if err := json.Unmarshal(rep, &code); err != nil {
		return []string{},err
	}
	if code.Code != 200 {
		return []string{}, RCErrorNew(code.Code, code.ErrorMessage)
	}
	if err := json.Unmarshal(rep, &dat); err != nil {
		return []string{},err
	}
	return dat.ChatroomIds, nil
}

func (rc *RongCloud) ChatroomWhitelistAdd (objectNames []string) error {

	if(len(objectNames) == 0) {
		return errors.New("Paramer 'members' is required");
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/whitelist/add." + ReqType)
	rc.FillHeader(req)
	for _,v := range objectNames {
		req.Param("userId", v)
	}
	rep, err := req.Bytes()
	if err != nil {
		return err
	}
	var code CodeReslut
	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}
	if code.Code != 200 {
		return RCErrorNew(code.Code, code.ErrorMessage)
	}
	return nil
}

func (rc *RongCloud) ChatroomWhitelistRemove (objectNames []string) error {

	if(len(objectNames) == 0) {
		return errors.New("Paramer 'members' is required");
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/whitelist/remove." + ReqType)
	rc.FillHeader(req)

	for _,v := range objectNames {
		req.Param("userId", v)
	}
	rep, err := req.Bytes()
	if err != nil {
		return err
	}
	var code CodeReslut
	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}
	if code.Code != 200 {
		return RCErrorNew(code.Code, code.ErrorMessage)
	}
	return nil
}

func (rc *RongCloud) ChatroomWhitelistGetList () ([]string, error) {
	var dat ChatroomReslut

	req := httplib.Post(rc.RongCloudURI + "/chatroom/whitelist/query." + ReqType)
	rc.FillHeader(req)

	rep, err := req.Bytes()
	if err != nil {
		return []string{}, err
	}
	var code CodeReslut
	if err := json.Unmarshal(rep, &code); err != nil {
		return []string{}, err
	}
	if code.Code != 200 {
		return []string{},RCErrorNew(code.Code, code.ErrorMessage)
	}
	if err := json.Unmarshal(rep, &dat); err != nil {
		return []string{}, err
	}
	return dat.ObjectNames, nil
}


/**
 *添加聊天室白名单成员方法
 *
 *@param  chatroomId:聊天室中用户 Id，可提交多个，聊天室中白名单用户最多不超过 5 个。（必传）
 *@param  userId:聊天室 Id。（必传）
 *
 *@return CodeSuccessReslut
 */
func (rc *RongCloud) ChatroomUserWhitelistAdd (id string, members []string) error {
	if(id == "") {
		return errors.New("Paramer 'id' is required");
	}

	if(len(members) == 0) {
		return errors.New("Paramer 'members' is required");
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/user/whitelist/add." + ReqType)
	rc.FillHeader(req)
	req.Param("chatroomId", id)
	for _,v := range members {
		req.Param("userId", v)
	}
	rep, err := req.Bytes()
	if err != nil {
		return err
	}
	var code CodeReslut
	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}
	if code.Code != 200 {
		return RCErrorNew(code.Code, code.ErrorMessage)
	}
	return nil
}


func (rc *RongCloud) ChatroomUserWhitelistRemove (id string, members []string) error {
	if(id == "") {
		return errors.New("Paramer 'id' is required");
	}

	if(len(members) == 0) {
		return errors.New("Paramer 'members' is required");
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/user/whitelist/remove." + ReqType)
	rc.FillHeader(req)
	req.Param("chatroomId", id)
	for _,v := range members {
		req.Param("userId", v)
	}
	rep, err := req.Bytes()
	if err != nil {
		return err
	}
	var code CodeReslut
	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}
	if code.Code != 200 {
		return RCErrorNew(code.Code, code.ErrorMessage)
	}
	return nil
}

func (rc *RongCloud) ChatroomUserWhitelistGetList (id string) ([]ChatRoomUser, error) {
	var dat ChatroomReslut
	if(id == "") {
		return []ChatRoomUser{}, errors.New("Paramer 'id' is required");
	}
	req := httplib.Post(rc.RongCloudURI + "/chatroom/user/whitelist/query." + ReqType)
	rc.FillHeader(req)
	req.Param("chatroomId", id)

	rep, err := req.Bytes()
	if err != nil {
		return []ChatRoomUser{}, err
	}
	var code CodeReslut
	if err := json.Unmarshal(rep, &code); err != nil {
		return []ChatRoomUser{}, err
	}
	if code.Code != 200 {
		return []ChatRoomUser{},RCErrorNew(code.Code, code.ErrorMessage)
	}
	if err := json.Unmarshal(rep, &dat); err != nil {
		return []ChatRoomUser{}, err
	}
	return dat.Users,nil
}



































