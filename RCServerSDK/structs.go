package rcserversdk

/**
 *getToken 返回结果
 */
type TokenReslut struct {
	Code int `json:"code"`
	Token string `json:"token"`
	UserId string `json:"userId"`
	ErrorMessage string `json:"errorMessage"`
}
/**
 * getImageCode 成功返回结果
 */
type SMSImageCodeReslut struct {
	Code int `json:"code"`
	Url string `json:"url"`
	VerifyId string `json:"verifyId"`
	ErrorMessage string `json:"errorMessage"`
}
/**
 * SMSSendCodeReslut 成功返回结果
 */
type SMSSendCodeReslut struct {
	Code int `json:"code"`
	SessionId string `json:"sessionId"`
	ErrorMessage string `json:"errorMessage"`
}
/**
 * http 成功返回结果
 */
type CodeSuccessReslut struct {
	Code int `json:"code"`
	ErrorMessage string `json:"errorMessage"`
}
/**
 *checkOnlineUser返回结果
 */
type CheckOnlineReslut struct {
	Code int `json:"code"`
	Status string `json:"status"`
	ErrorMessage string `json:"errorMessage"`
}
/**
 *queryBlockUser返回结果
 */
type QueryBlockUserReslut struct {
	Code int `json:"code"`
	Users []BlockUsers `json:"users"`
	ErrorMessage string `json:"errorMessage"`
}
/**
 *queryBlacklistUser返回结果
 */
type QueryBlacklistUserReslut struct {
	Code int `json:"code"`
	Users []string `json:"users"`
	ErrorMessage string `json:"errorMessage"`
}
/**
 *listWordfilter返回结果
 */
type ListWordfilterReslut struct {
	Code int `json:"code"`
	Word string `json:"word"`
	ErrorMessage string `json:"errorMessage"`
}
/**
 *historyMessage返回结果
 */
type HistoryMessageReslut struct {
	Code int `json:"code"`
	Url string `json:"url"`
	Date string `json:"date"`
	ErrorMessage string `json:"errorMessage"`
}
/**
 *groupUserQuery返回结果
 */
type GroupUserQueryReslut struct {
	Code int `json:"code"`
	Id string `json:"id"`
	Users []GroupUser `json:"users"`
}
/**
 * lisitGagGroupUser 返回结果
 */
type ListGagGroupUserReslut struct {
	Code int `json:"code"`
	Users []GagGroupUser `json:"users"`
	ErrorMessage string `json:"errorMessage"`
}
/**
 * chatroomQuery 返回结果
 */
type ChatroomQueryReslut struct {
	Code int `json:"code"`
	ChatRooms []ChatRoom `json:"chatRooms"`
	ErrorMessage string `json:"errorMessage"`
}
/**
 * chatroomUserQuery 返回结果
 */
type ChatroomUserQueryReslut struct {
	Code int `json:"code"`
	Total int `json:"total"`
	Users []ChatRoomUser `json:"users"`
	ErrorMessage string `json:"errorMessage"`
}
/**
 *listBlockChatroomUser返回结果
 */
type ListBlockChatroomUserReslut struct {
	Code int `json:"code"`
	Users []BlockChatRoomUser `json:"users"`
	ErrorMessage string `json:"errorMessage"`
}
/**
 *listGagChatroomUser返回结果
 */
type ListGagChatroomUserReslut struct {
	Code int `json:"code"`
	Users []GagChatRoomUser `json:"users"`
	ErrorMessage string `json:"errorMessage"`
}
/**
 *封禁用户信息
 */
type BlockUsers struct {
	UserId string `json:"userId"`
	BlockEndTime string `json:"blockEndTime"`
}
/**
 *群组用户信息。
 */
type GroupUser struct {
	Id string `json:"id"`
}
/**
 *群组用户信息。
 */
type GagGroupUser struct {
	Time string `json:"time"`
	UserId string `json:"userId"`
}
/**
 *聊天室信息。
 */
type ChatRoom struct {
	ChrmId string `json:"chrmId"`
	Name string `json:"name"`
	Time string `json:"time"`
}
/**
 *聊天室用户信息。
 */
type ChatRoomUser struct {
	Id string `json:"id"`
	Time string `json:"time"`
}
/**
 *聊天室被封禁用户信息。
 */
type BlockChatRoomUser struct {
	Id string `json:"id"`
	Time string `json:"time"`
}
/**
 *聊天室被禁言用户信息。
 */
type GagChatRoomUser struct {
	Time string `json:"time"`
	UserId string `json:"userId"`
}
/**
 *群组信息。
 */
type GroupInfo struct {
	Id string `json:"id"`
	Name string `json:"name"`
}
/**
 *聊天室信息。
 */
type ChatRoomInfo struct {
	Id string `json:"id"`
	Name string `json:"name"`
}
/**
 *用于打标签的对象。
 */
type UserTag struct {
	Tags []string `json:"tags"`
	UserId string `json:"userId"`
}
/**
 *用于Push中的message。
 */
type MsgObj struct {
	Content string `json:"content"`
	ObjectName string `json:"objectName"`
}
/**
 *用于Push中的 标签。
 */
type TagObj struct {
	Tag []string `json:"tag"`
	Userid []string `json:"userid"`
	Is_to_all bool `json:"is_to_all"`
}
/**
 *按操作系统类型推送消息内容，如 platform 中设置了给 ios 和 android 系统推送消息，而在 notification 中只设置了 ios 的推送内容，则 android 的推送内容为最初 alert 设置的内容。（非必传）
 */
type Notification struct {
	Alert string `json:"alert"`
	Ios *PlatformNotification `json:"ios"`
	Android *PlatformNotification `json:"android"`
}
/**
 *设备中的推送内容。（非必传）
 */
type PlatformNotification struct {
	Alert string `json:"alert"`
	Extras map[string]string `json:"extras"`
}
/**
 *不落地 push 消息体。
 */
type PushMessage struct {
	Platform []string `json:"platform"`
	Fromuserid string `json:"fromuserid"`
	Audience *TagObj `json:"audience"`
	Message *MsgObj `json:"message"`
	Notification *Notification `json:"notification"`
}
/**
 *模版消息对象。
 */
type TemplateMessage struct {
	FromUserId string `json:"fromUserId"`
	ToUserId []string `json:"toUserId"`
	Content string `json:"content"`
	Values []map[string]string `json:"values"`
	ObjectName string `json:"objectName"`
	PushContent []string `json:"pushContent"`
	PushData []string `json:"pushData"`
	VerifyBlacklist int `json:"verifyBlacklist"`
}

/**
文本消息。
*/
type TxtMessage struct {
	Content string `json:"content"`
	Extra string `json:"extra"`
	messageType string  
}
func (self *TxtMessage)GetType()string{
	return self.messageType
}
func (self *TxtMessage)SetType(messageType string){
	  self.messageType = messageType
}
/**
图片消息。
*/
type ImgMessage struct {
	Content string `json:"content"`
	Extra string `json:"extra"`
	ImageUri string `json:"imageUri"`
	messageType string  
}
func (self *ImgMessage)GetType()string{
	return self.messageType
}
func (self *ImgMessage)SetType(messageType string){
	  self.messageType = messageType
}
/**
语音消息。
*/
type VoiceMessage struct {
	Content string `json:"content"`
	Extra string `json:"extra"`
	Duration int64 `json:"duration"`
	messageType string  
}
func (self *VoiceMessage)GetType()string{
	return self.messageType
}
func (self *VoiceMessage)SetType(messageType string){
	  self.messageType = messageType
}
/**
图文消息。
*/
type ImgTextMessage struct {
	Content string `json:"content"`
	Extra string `json:"extra"`
	Title string `json:"title"`
	ImageUri string `json:"imageUri"`
	Url string `json:"url"`
	messageType string  
}
func (self *ImgTextMessage)GetType()string{
	return self.messageType
}
func (self *ImgTextMessage)SetType(messageType string){
	  self.messageType = messageType
}
/**
位置消息。
*/
type LBSMessage struct {
	Content string `json:"content"`
	Extra string `json:"extra"`
	Latitude float64 `json:"latitude"`
	Longitude float64 `json:"longitude"`
	Poi string `json:"poi"`
	messageType string  
}
func (self *LBSMessage)GetType()string{
	return self.messageType
}
func (self *LBSMessage)SetType(messageType string){
	  self.messageType = messageType
}
/**
添加联系人消息。
*/
type ContactNtfMessage struct {
	Operation string `json:"operation"`
	Extra string `json:"extra"`
	SourceUserId string `json:"sourceUserId"`
	TargetUserId string `json:"targetUserId"`
	Message string `json:"message"`
	messageType string  
}
func (self *ContactNtfMessage)GetType()string{
	return self.messageType
}
func (self *ContactNtfMessage)SetType(messageType string){
	  self.messageType = messageType
}
/**
提示条（小灰条）通知消息。此类型消息没有 Push 通知。
*/
type InfoNtfMessage struct {
	Message string `json:"message"`
	Extra string `json:"extra"`
	messageType string  
}
func (self *InfoNtfMessage)GetType()string{
	return self.messageType
}
func (self *InfoNtfMessage)SetType(messageType string){
	  self.messageType = messageType
}
/**
资料通知消息。此类型消息没有 Push 通知。
*/
type ProfileNtfMessage struct {
	Operation string `json:"operation"`
	Data string `json:"data"`
	Extra string `json:"extra"`
	messageType string  
}
func (self *ProfileNtfMessage)GetType()string{
	return self.messageType
}
func (self *ProfileNtfMessage)SetType(messageType string){
	  self.messageType = messageType
}
/**
通用命令通知消息。此类型消息没有 Push 通知。
*/
type CmdNtfMessage struct {
	Name string `json:"name"`
	Data string `json:"data"`
	messageType string  
}
func (self *CmdNtfMessage)GetType()string{
	return self.messageType
}
func (self *CmdNtfMessage)SetType(messageType string){
	  self.messageType = messageType
}
/**
通用命令通知消息。此类型消息没有 Push 通知。此类型消息没有 Push 通知，与通用命令通知消息的区别是不存储、不计数。
*/
type CmdMsgMessage struct {
	Name string `json:"name"`
	Data string `json:"data"`
	messageType string  
}
func (self *CmdMsgMessage)GetType()string{
	return self.messageType
}
func (self *CmdMsgMessage)SetType(messageType string){
	  self.messageType = messageType
}
/**
自定义消息
*/
type CustomTxtMessage struct {
	Content string `json:"content"`
	messageType string  
}
func (self *CustomTxtMessage)GetType()string{
	return self.messageType
}
func (self *CustomTxtMessage)SetType(messageType string){
	  self.messageType = messageType
}



