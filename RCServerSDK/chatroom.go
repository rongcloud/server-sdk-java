package rcserversdk

import(
		
	"errors"
	"github.com/astaxie/beego/httplib"
)
type Chatroom struct {
	AppKey    string
	AppSecret string
}



	/**
	 *创建聊天室方法 
	 * 
	 *@param  chatRoomInfo:id:要创建的聊天室的id；name:要创建的聊天室的name。（必传）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Chatroom)Create(chatRoomInfo []ChatRoomInfo)(*CodeSuccessReslut, error) {
	  if(len(chatRoomInfo) == 0) {
		return nil,errors.New("Paramer 'chatRoomInfo' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/chatroom/create.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	   for _,item := range chatRoomInfo {
	     req.Param("chatroom["+item.Id+"]", item.Name)
	  }
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
	 *加入聊天室方法 
	 * 
	 *@param  userId:要加入聊天室的用户 Id，可提交多个，最多不超过 50 个。（必传） 
	 *@param  chatroomId:要加入的聊天室 Id。（必传）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Chatroom)Join(userId []string, chatroomId string)(*CodeSuccessReslut, error) {
	  if(len(userId) == 0) {
		return nil,errors.New("Paramer 'userId' is required");
      }
      
	  if( chatroomId == "") {
		return nil,errors.New("Paramer 'chatroomId' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/chatroom/join.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  for _,item := range userId {
	  req.Param("userId", item)
	  }
	  req.Param("chatroomId", chatroomId)
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
	 *查询聊天室信息方法 
	 * 
	 *@param  chatroomId:要查询的聊天室id（必传）
	 *
	 *@return ChatroomQueryReslut
	 */
  func (self * Chatroom)Query(chatroomId []string)(*ChatroomQueryReslut, error) {
	  if(len(chatroomId) == 0) {
		return nil,errors.New("Paramer 'chatroomId' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/chatroom/query.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  for _,item := range chatroomId {
	  req.Param("chatroomId", item)
	  }
	  byteData, err := req.Bytes()
	  	if err != nil {
		   return nil,err
		 }else{
		   strData := string(byteData)
		   var ret = ChatroomQueryReslut{}
			  err = JsonParse(strData,&ret)
			  return &ret,err
			}
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
  func (self * Chatroom)QueryUser(chatroomId string, count string, order string)(*ChatroomUserQueryReslut, error) {
	  if( chatroomId == "") {
		return nil,errors.New("Paramer 'chatroomId' is required");
      }
      
	  if( count == "") {
		return nil,errors.New("Paramer 'count' is required");
      }
      
	  if( order == "") {
		return nil,errors.New("Paramer 'order' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/chatroom/user/query.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("chatroomId", chatroomId)
	  req.Param("count", count)
	  req.Param("order", order)
	  byteData, err := req.Bytes()
	  	if err != nil {
		   return nil,err
		 }else{
		   strData := string(byteData)
		   var ret = ChatroomUserQueryReslut{}
			  err = JsonParse(strData,&ret)
			  return &ret,err
			}
	  }
  

	/**
	 *聊天室消息停止分发方法（可实现控制对聊天室中消息是否进行分发，停止分发后聊天室中用户发送的消息，融云服务端不会再将消息发送给聊天室中其他用户。） 
	 * 
	 *@param  chatroomId:聊天室 Id。（必传）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Chatroom)StopDistributionMessage(chatroomId string)(*CodeSuccessReslut, error) {
	  if( chatroomId == "") {
		return nil,errors.New("Paramer 'chatroomId' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/chatroom/message/stopDistribution.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("chatroomId", chatroomId)
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
	 *聊天室消息恢复分发方法 
	 * 
	 *@param  chatroomId:聊天室 Id。（必传）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Chatroom)ResumeDistributionMessage(chatroomId string)(*CodeSuccessReslut, error) {
	  if( chatroomId == "") {
		return nil,errors.New("Paramer 'chatroomId' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/chatroom/message/resumeDistribution.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("chatroomId", chatroomId)
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
	 *添加禁言聊天室成员方法（在 App 中如果不想让某一用户在聊天室中发言时，可将此用户在聊天室中禁言，被禁言用户可以接收查看聊天室中用户聊天信息，但不能发送消息.） 
	 * 
	 *@param  userId:用户 Id。（必传） 
	 *@param  chatroomId:聊天室 Id。（必传） 
	 *@param  minute:禁言时长，以分钟为单位，最大值为43200分钟。（必传）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Chatroom)AddGagUser(userId string, chatroomId string, minute string)(*CodeSuccessReslut, error) {
	  if( userId == "") {
		return nil,errors.New("Paramer 'userId' is required");
      }
      
	  if( chatroomId == "") {
		return nil,errors.New("Paramer 'chatroomId' is required");
      }
      
	  if( minute == "") {
		return nil,errors.New("Paramer 'minute' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/chatroom/user/gag/add.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("userId", userId)
	  req.Param("chatroomId", chatroomId)
	  req.Param("minute", minute)
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
	 *查询被禁言聊天室成员方法 
	 * 
	 *@param  chatroomId:聊天室 Id。（必传）
	 *
	 *@return ListGagChatroomUserReslut
	 */
  func (self * Chatroom)ListGagUser(chatroomId string)(*ListGagChatroomUserReslut, error) {
	  if( chatroomId == "") {
		return nil,errors.New("Paramer 'chatroomId' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/chatroom/user/gag/list.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("chatroomId", chatroomId)
	  byteData, err := req.Bytes()
	  	if err != nil {
		   return nil,err
		 }else{
		   strData := string(byteData)
		   var ret = ListGagChatroomUserReslut{}
			  err = JsonParse(strData,&ret)
			  return &ret,err
			}
	  }
  

	/**
	 *移除禁言聊天室成员方法 
	 * 
	 *@param  userId:用户 Id。（必传） 
	 *@param  chatroomId:聊天室Id。（必传）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Chatroom)RollbackGagUser(userId string, chatroomId string)(*CodeSuccessReslut, error) {
	  if( userId == "") {
		return nil,errors.New("Paramer 'userId' is required");
      }
      
	  if( chatroomId == "") {
		return nil,errors.New("Paramer 'chatroomId' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/chatroom/user/gag/rollback.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("userId", userId)
	  req.Param("chatroomId", chatroomId)
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
	 *添加封禁聊天室成员方法 
	 * 
	 *@param  userId:用户 Id。（必传） 
	 *@param  chatroomId:聊天室 Id。（必传） 
	 *@param  minute:封禁时长，以分钟为单位，最大值为43200分钟。（必传）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Chatroom)AddBlockUser(userId string, chatroomId string, minute string)(*CodeSuccessReslut, error) {
	  if( userId == "") {
		return nil,errors.New("Paramer 'userId' is required");
      }
      
	  if( chatroomId == "") {
		return nil,errors.New("Paramer 'chatroomId' is required");
      }
      
	  if( minute == "") {
		return nil,errors.New("Paramer 'minute' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/chatroom/user/block/add.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("userId", userId)
	  req.Param("chatroomId", chatroomId)
	  req.Param("minute", minute)
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
	 *查询被封禁聊天室成员方法 
	 * 
	 *@param  chatroomId:聊天室 Id。（必传）
	 *
	 *@return ListBlockChatroomUserReslut
	 */
  func (self * Chatroom)GetListBlockUser(chatroomId string)(*ListBlockChatroomUserReslut, error) {
	  if( chatroomId == "") {
		return nil,errors.New("Paramer 'chatroomId' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/chatroom/user/block/list.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("chatroomId", chatroomId)
	  byteData, err := req.Bytes()
	  	if err != nil {
		   return nil,err
		 }else{
		   strData := string(byteData)
		   var ret = ListBlockChatroomUserReslut{}
			  err = JsonParse(strData,&ret)
			  return &ret,err
			}
	  }
  

	/**
	 *移除封禁聊天室成员方法 
	 * 
	 *@param  userId:用户 Id。（必传） 
	 *@param  chatroomId:聊天室 Id。（必传）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Chatroom)RollbackBlockUser(userId string, chatroomId string)(*CodeSuccessReslut, error) {
	  if( userId == "") {
		return nil,errors.New("Paramer 'userId' is required");
      }
      
	  if( chatroomId == "") {
		return nil,errors.New("Paramer 'chatroomId' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/chatroom/user/block/rollback.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("userId", userId)
	  req.Param("chatroomId", chatroomId)
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
	 *添加聊天室消息优先级方法 
	 * 
	 *@param  objectName:低优先级的消息类型，每次最多提交 5 个，设置的消息类型最多不超过 20 个。（必传）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Chatroom)AddPriority(objectName []string)(*CodeSuccessReslut, error) {
	  if(len(objectName) == 0) {
		return nil,errors.New("Paramer 'objectName' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/chatroom/message/priority/add.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  for _,item := range objectName {
	  req.Param("objectName", item)
	  }
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
	 *销毁聊天室方法 
	 * 
	 *@param  chatroomId:要销毁的聊天室 Id。（必传）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Chatroom)Destroy(chatroomId []string)(*CodeSuccessReslut, error) {
	  if(len(chatroomId) == 0) {
		return nil,errors.New("Paramer 'chatroomId' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/chatroom/destroy.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  for _,item := range chatroomId {
	  req.Param("chatroomId", item)
	  }
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
	 *添加聊天室白名单成员方法 
	 * 
	 *@param  chatroomId:聊天室中用户 Id，可提交多个，聊天室中白名单用户最多不超过 5 个。（必传） 
	 *@param  userId:聊天室 Id。（必传）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Chatroom)AddWhiteListUser(chatroomId string, userId []string)(*CodeSuccessReslut, error) {
	  if( chatroomId == "") {
		return nil,errors.New("Paramer 'chatroomId' is required");
      }
      
	  if(len(userId) == 0) {
		return nil,errors.New("Paramer 'userId' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/chatroom/user/whitelist/add.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("chatroomId", chatroomId)
	  for _,item := range userId {
	  req.Param("userId", item)
	  }
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
  
