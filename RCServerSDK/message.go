package rcserversdk

import(
		
	"strconv"
	
	"errors"
	"github.com/astaxie/beego/httplib"
)
type Message struct {
	AppKey    string
	AppSecret string
}



	/**
	 *发送单聊消息方法（一个用户向另外一个用户发送消息，单条消息最大 128k。每分钟最多发送 6000 条信息，每次发送用户上限为 1000 人，如：一次发送 1000 人时，示为 1000 条消息。） 
	 * 
	 *@param  fromUserId:发送人用户 Id。（必传） 
	 *@param  toUserId:接收用户 Id，可以实现向多人发送消息，每次上限为 1000 人。（必传） 
	 *@param  voiceMessage:消息。 
	 *@param  pushContent:定义显示的 Push 内容，如果 objectName 为融云内置消息类型时，则发送后用户一定会收到 Push 信息。如果为自定义消息，则 pushContent 为自定义消息显示的 Push 内容，如果不传则用户不会收到 Push 通知。（可选） 
	 *@param  pushData:针对 iOS 平台为 Push 通知时附加到 payload 中，Android 客户端收到推送消息时对应字段名为 pushData。（可选） 
	 *@param  count:针对 iOS 平台，Push 时用来控制未读消息显示数，只有在 toUserId 为一个用户 Id 的时候有效。（可选） 
	 *@param  verifyBlacklist:是否过滤发送人黑名单列表，0 表示为不过滤、 1 表示为过滤，默认为 0 不过滤。（可选） 
	 *@param  isPersisted:当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行存储，0 表示为不存储、 1 表示为存储，默认为 1 存储消息。（可选） 
	 *@param  isCounted:当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行未读消息计数，0 表示为不计数、 1 表示为计数，默认为 1 计数，未读消息数增加 1。（可选） 
	 *@param  isIncludeSender:发送用户自已是否接收消息，0 表示为不接收，1 表示为接收，默认为 0 不接收。（可选）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Message)PublishPrivate(fromUserId string, toUserId []string, voiceMessage VoiceMessage, pushContent string, pushData string, count string, verifyBlacklist int, isPersisted int, isCounted int, isIncludeSender int)(*CodeSuccessReslut, error) {
	  if( fromUserId == "") {
		return nil,errors.New("Paramer 'fromUserId' is required");
      }
      
	  if(len(toUserId) == 0) {
		return nil,errors.New("Paramer 'toUserId' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/message/private/publish.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("fromUserId", fromUserId)
	  for _,item := range toUserId {
	  req.Param("toUserId", item)
	  }
	  req.Param("objectName", voiceMessage.GetType())
	  jsonStr,err := ToJson(voiceMessage)
	  if err != nil{
	    return nil,err
	  }
	  req.Param("content",jsonStr)
	  req.Param("pushContent", pushContent)
	  req.Param("pushData", pushData)
	  req.Param("count", count)
	  req.Param("verifyBlacklist", strconv.Itoa(verifyBlacklist))
	  req.Param("isPersisted", strconv.Itoa(isPersisted))
	  req.Param("isCounted", strconv.Itoa(isCounted))
	  req.Param("isIncludeSender", strconv.Itoa(isIncludeSender))
	  byteData, err := req.Bytes()
	  	if err != nil {
		   return nil,err
		 }else{
		   strData := string(byteData)
		   var ret = CodeSuccessReslut{}
			  err = JsonParse(strData,&ret)
			  return &ret,err
			}
	  }
  

	/**
	 *发送单聊模板消息方法（一个用户向多个用户发送不同消息内容，单条消息最大 128k。每分钟最多发送 6000 条信息，每次发送用户上限为 1000 人。） 
	 * 
	 *@param  templateMessage:单聊模版消息。
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Message)PublishTemplate(templateMessage TemplateMessage)(*CodeSuccessReslut, error) {
	  destinationUrl := RONGCLOUDURI + "/message/private/publish_template.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  jsonStr,err := ToJson(templateMessage)
	  if err != nil {
	    return nil,err
	  }
	  req.Body(jsonStr)
	  fillJsonHeader(req)
	  byteData, err := req.Bytes()
	  	if err != nil {
		   return nil,err
		 }else{
		   strData := string(byteData)
		   var ret = CodeSuccessReslut{}
			  err = JsonParse(strData,&ret)
			  return &ret,err
			}
	  }
  

	/**
	 *发送系统消息方法（一个用户向一个或多个用户发送系统消息，单条消息最大 128k，会话类型为 SYSTEM。每秒钟最多发送 100 条消息，每次最多同时向 100 人发送，如：一次发送 100 人时，示为 100 条消息。） 
	 * 
	 *@param  fromUserId:发送人用户 Id。（必传） 
	 *@param  toUserId:接收用户 Id，提供多个本参数可以实现向多人发送消息，上限为 1000 人。（必传） 
	 *@param  txtMessage:发送消息内容（必传） 
	 *@param  pushContent:如果为自定义消息，定义显示的 Push 内容，内容中定义标识通过 values 中设置的标识位内容进行替换.如消息类型为自定义不需要 Push 通知，则对应数组传空值即可。（可选） 
	 *@param  pushData:针对 iOS 平台为 Push 通知时附加到 payload 中，Android 客户端收到推送消息时对应字段名为 pushData。如不需要 Push 功能对应数组传空值即可。（可选） 
	 *@param  isPersisted:当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行存储，0 表示为不存储、 1 表示为存储，默认为 1 存储消息。（可选） 
	 *@param  isCounted:当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行未读消息计数，0 表示为不计数、 1 表示为计数，默认为 1 计数，未读消息数增加 1。（可选）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Message)PublishSystem(fromUserId string, toUserId []string, txtMessage TxtMessage, pushContent string, pushData string, isPersisted int, isCounted int)(*CodeSuccessReslut, error) {
	  if( fromUserId == "") {
		return nil,errors.New("Paramer 'fromUserId' is required");
      }
      
	  if(len(toUserId) == 0) {
		return nil,errors.New("Paramer 'toUserId' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/message/system/publish.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("fromUserId", fromUserId)
	  for _,item := range toUserId {
	  req.Param("toUserId", item)
	  }
	  req.Param("objectName", txtMessage.GetType())
	  jsonStr,err := ToJson(txtMessage)
	  if err != nil{
	    return nil,err
	  }
	  req.Param("content",jsonStr)
	  req.Param("pushContent", pushContent)
	  req.Param("pushData", pushData)
	  req.Param("isPersisted", strconv.Itoa(isPersisted))
	  req.Param("isCounted", strconv.Itoa(isCounted))
	  byteData, err := req.Bytes()
	  	if err != nil {
		   return nil,err
		 }else{
		   strData := string(byteData)
		   var ret = CodeSuccessReslut{}
			  err = JsonParse(strData,&ret)
			  return &ret,err
			}
	  }
  

	/**
	 *发送系统模板消息方法（一个用户向一个或多个用户发送系统消息，单条消息最大 128k，会话类型为 SYSTEM.每秒钟最多发送 100 条消息，每次最多同时向 100 人发送，如：一次发送 100 人时，示为 100 条消息。） 
	 * 
	 *@param  templateMessage:系统模版消息。
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Message)PublishSystemTemplate(templateMessage TemplateMessage)(*CodeSuccessReslut, error) {
	  destinationUrl := RONGCLOUDURI + "/message/system/publish_template.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  jsonStr,err := ToJson(templateMessage)
	  if err != nil {
	    return nil,err
	  }
	  req.Body(jsonStr)
	  fillJsonHeader(req)
	  byteData, err := req.Bytes()
	  	if err != nil {
		   return nil,err
		 }else{
		   strData := string(byteData)
		   var ret = CodeSuccessReslut{}
			  err = JsonParse(strData,&ret)
			  return &ret,err
			}
	  }
  

	/**
	 *发送群组消息方法（以一个用户身份向群组发送消息，单条消息最大 128k.每秒钟最多发送 20 条消息，每次最多向 3 个群组发送，如：一次向 3 个群组发送消息，示为 3 条消息。） 
	 * 
	 *@param  fromUserId:发送人用户 Id 。（必传） 
	 *@param  toGroupId:接收群Id，提供多个本参数可以实现向多群发送消息，最多不超过 3 个群组。（必传） 
	 *@param  txtMessage:发送消息内容（必传） 
	 *@param  pushContent:定义显示的 Push 内容，如果 objectName 为融云内置消息类型时，则发送后用户一定会收到 Push 信息. 如果为自定义消息，则 pushContent 为自定义消息显示的 Push 内容，如果不传则用户不会收到 Push 通知。（可选） 
	 *@param  pushData:针对 iOS 平台为 Push 通知时附加到 payload 中，Android 客户端收到推送消息时对应字段名为 pushData。（可选） 
	 *@param  isPersisted:当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行存储，0 表示为不存储、 1 表示为存储，默认为 1 存储消息。（可选） 
	 *@param  isCounted:当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行未读消息计数，0 表示为不计数、 1 表示为计数，默认为 1 计数，未读消息数增加 1。（可选） 
	 *@param  isIncludeSender:发送用户自已是否接收消息，0 表示为不接收，1 表示为接收，默认为 0 不接收。（可选）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Message)PublishGroup(fromUserId string, toGroupId []string, txtMessage TxtMessage, pushContent string, pushData string, isPersisted int, isCounted int, isIncludeSender int)(*CodeSuccessReslut, error) {
	  if( fromUserId == "") {
		return nil,errors.New("Paramer 'fromUserId' is required");
      }
      
	  if(len(toGroupId) == 0) {
		return nil,errors.New("Paramer 'toGroupId' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/message/group/publish.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("fromUserId", fromUserId)
	  for _,item := range toGroupId {
	  req.Param("toGroupId", item)
	  }
	  req.Param("objectName", txtMessage.GetType())
	  jsonStr,err := ToJson(txtMessage)
	  if err != nil{
	    return nil,err
	  }
	  req.Param("content",jsonStr)
	  req.Param("pushContent", pushContent)
	  req.Param("pushData", pushData)
	  req.Param("isPersisted", strconv.Itoa(isPersisted))
	  req.Param("isCounted", strconv.Itoa(isCounted))
	  req.Param("isIncludeSender", strconv.Itoa(isIncludeSender))
	  byteData, err := req.Bytes()
	  	if err != nil {
		   return nil,err
		 }else{
		   strData := string(byteData)
		   var ret = CodeSuccessReslut{}
			  err = JsonParse(strData,&ret)
			  return &ret,err
			}
	  }
  

	/**
	 *发送讨论组消息方法（以一个用户身份向讨论组发送消息，单条消息最大 128k，每秒钟最多发送 20 条消息.） 
	 * 
	 *@param  fromUserId:发送人用户 Id。（必传） 
	 *@param  toDiscussionId:接收讨论组 Id。（必传） 
	 *@param  txtMessage:发送消息内容（必传） 
	 *@param  pushContent:定义显示的 Push 内容，如果 objectName 为融云内置消息类型时，则发送后用户一定会收到 Push 信息. 如果为自定义消息，则 pushContent 为自定义消息显示的 Push 内容，如果不传则用户不会收到 Push 通知。（可选） 
	 *@param  pushData:针对 iOS 平台为 Push 通知时附加到 payload 中，Android 客户端收到推送消息时对应字段名为 pushData.（可选） 
	 *@param  isPersisted:当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行存储，0 表示为不存储、 1 表示为存储，默认为 1 存储消息.（可选） 
	 *@param  isCounted:当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行未读消息计数，0 表示为不计数、 1 表示为计数，默认为 1 计数，未读消息数增加 1。（可选） 
	 *@param  isIncludeSender:发送用户自已是否接收消息，0 表示为不接收，1 表示为接收，默认为 0 不接收。（可选）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Message)PublishDiscussion(fromUserId string, toDiscussionId string, txtMessage TxtMessage, pushContent string, pushData string, isPersisted int, isCounted int, isIncludeSender int)(*CodeSuccessReslut, error) {
	  if( fromUserId == "") {
		return nil,errors.New("Paramer 'fromUserId' is required");
      }
      
	  if( toDiscussionId == "") {
		return nil,errors.New("Paramer 'toDiscussionId' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/message/discussion/publish.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("fromUserId", fromUserId)
	  req.Param("toDiscussionId", toDiscussionId)
	  req.Param("objectName", txtMessage.GetType())
	  jsonStr,err := ToJson(txtMessage)
	  if err != nil{
	    return nil,err
	  }
	  req.Param("content",jsonStr)
	  req.Param("pushContent", pushContent)
	  req.Param("pushData", pushData)
	  req.Param("isPersisted", strconv.Itoa(isPersisted))
	  req.Param("isCounted", strconv.Itoa(isCounted))
	  req.Param("isIncludeSender", strconv.Itoa(isIncludeSender))
	  byteData, err := req.Bytes()
	  	if err != nil {
		   return nil,err
		 }else{
		   strData := string(byteData)
		   var ret = CodeSuccessReslut{}
			  err = JsonParse(strData,&ret)
			  return &ret,err
			}
	  }
  

	/**
	 *发送聊天室消息方法（一个用户向聊天室发送消息，单条消息最大 128k。每秒钟限 100 次。） 
	 * 
	 *@param  fromUserId:发送人用户 Id。（必传） 
	 *@param  toChatroomId:接收聊天室Id，提供多个本参数可以实现向多个聊天室发送消息。（必传） 
	 *@param  txtMessage:发送消息内容（必传）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Message)PublishChatroom(fromUserId string, toChatroomId []string, txtMessage TxtMessage)(*CodeSuccessReslut, error) {
	  if( fromUserId == "") {
		return nil,errors.New("Paramer 'fromUserId' is required");
      }
      
	  if(len(toChatroomId) == 0) {
		return nil,errors.New("Paramer 'toChatroomId' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/message/chatroom/publish.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("fromUserId", fromUserId)
	  for _,item := range toChatroomId {
	  req.Param("toChatroomId", item)
	  }
	  req.Param("objectName", txtMessage.GetType())
	  jsonStr,err := ToJson(txtMessage)
	  if err != nil{
	    return nil,err
	  }
	  req.Param("content",jsonStr)
	  byteData, err := req.Bytes()
	  	if err != nil {
		   return nil,err
		 }else{
		   strData := string(byteData)
		   var ret = CodeSuccessReslut{}
			  err = JsonParse(strData,&ret)
			  return &ret,err
			}
	  }
  

	/**
	 *发送广播消息方法（发送消息给一个应用下的所有注册用户，如用户未在线会对满足条件（绑定手机终端）的用户发送 Push 信息，单条消息最大 128k，会话类型为 SYSTEM。每小时只能发送 1 次，每天最多发送 3 次。） 
	 * 
	 *@param  fromUserId:发送人用户 Id。（必传） 
	 *@param  txtMessage:文本消息。 
	 *@param  pushContent:定义显示的 Push 内容，如果 objectName 为融云内置消息类型时，则发送后用户一定会收到 Push 信息. 如果为自定义消息，则 pushContent 为自定义消息显示的 Push 内容，如果不传则用户不会收到 Push 通知.（可选） 
	 *@param  pushData:针对 iOS 平台为 Push 通知时附加到 payload 中，Android 客户端收到推送消息时对应字段名为 pushData。（可选） 
	 *@param  os:针对操作系统发送 Push，值为 iOS 表示对 iOS 手机用户发送 Push ,为 Android 时表示对 Android 手机用户发送 Push ，如对所有用户发送 Push 信息，则不需要传 os 参数。（可选）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Message)Broadcast(fromUserId string, txtMessage TxtMessage, pushContent string, pushData string, os string)(*CodeSuccessReslut, error) {
	  if( fromUserId == "") {
		return nil,errors.New("Paramer 'fromUserId' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/message/broadcast.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("fromUserId", fromUserId)
	  req.Param("objectName", txtMessage.GetType())
	  jsonStr,err := ToJson(txtMessage)
	  if err != nil{
	    return nil,err
	  }
	  req.Param("content",jsonStr)
	  req.Param("pushContent", pushContent)
	  req.Param("pushData", pushData)
	  req.Param("os", os)
	  byteData, err := req.Bytes()
	  	if err != nil {
		   return nil,err
		 }else{
		   strData := string(byteData)
		   var ret = CodeSuccessReslut{}
			  err = JsonParse(strData,&ret)
			  return &ret,err
			}
	  }
  

	/**
	 *消息历史记录下载地址获取 方法消息历史记录下载地址获取方法。获取 APP 内指定某天某小时内的所有会话消息记录的下载地址。（目前支持二人会话、讨论组、群组、聊天室、客服、系统通知消息历史记录下载） 
	 * 
	 *@param  date:指定北京时间某天某小时，格式为2014010101,表示：2014年1月1日凌晨1点。（必传）
	 *
	 *@return HistoryMessageReslut
	 */
  func (self * Message)GetHistory(date string)(*HistoryMessageReslut, error) {
	  if( date == "") {
		return nil,errors.New("Paramer 'date' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/message/history.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("date", date)
	  byteData, err := req.Bytes()
	  	if err != nil {
		   return nil,err
		 }else{
		   strData := string(byteData)
		   var ret = HistoryMessageReslut{}
			  err = JsonParse(strData,&ret)
			  return &ret,err
			}
	  }
  

	/**
	 *消息历史记录删除方法（删除 APP 内指定某天某小时内的所有会话消息记录。调用该接口返回成功后，date参数指定的某小时的消息记录文件将在随后的5-10分钟内被永久删除。） 
	 * 
	 *@param  date:指定北京时间某天某小时，格式为2014010101,表示：2014年1月1日凌晨1点。（必传）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Message)DeleteMessage(date string)(*CodeSuccessReslut, error) {
	  if( date == "") {
		return nil,errors.New("Paramer 'date' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/message/history/delete.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("date", date)
	  byteData, err := req.Bytes()
	  	if err != nil {
		   return nil,err
		 }else{
		   strData := string(byteData)
		   var ret = CodeSuccessReslut{}
			  err = JsonParse(strData,&ret)
			  return &ret,err
			}
	  }
  
