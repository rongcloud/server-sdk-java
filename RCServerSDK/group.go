package rcserversdk

import(
		
	"errors"
	"github.com/astaxie/beego/httplib"
)
type Group struct {
	AppKey    string
	AppSecret string
}



	/**
	 *创建群组方法（创建群组，并将用户加入该群组，用户将可以收到该群的消息，同一用户最多可加入 500 个群，每个群最大至 3000 人，App 内的群组数量没有限制.注：其实本方法是加入群组方法 /group/join 的别名。） 
	 * 
	 *@param  userId:要加入群的用户 Id。（必传） 
	 *@param  groupId:创建群组 Id。（必传） 
	 *@param  groupName:群组 Id 对应的名称。（必传）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Group)Create(userId []string, groupId string, groupName string)(*CodeSuccessReslut, error) {
	  if(len(userId) == 0) {
		return nil,errors.New("Paramer 'userId' is required");
      }
      
	  if( groupId == "") {
		return nil,errors.New("Paramer 'groupId' is required");
      }
      
	  if( groupName == "") {
		return nil,errors.New("Paramer 'groupName' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/group/create.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  for _,item := range userId {
	  req.Param("userId", item)
	  }
	  req.Param("groupId", groupId)
	  req.Param("groupName", groupName)
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
	 *同步用户所属群组方法（当第一次连接融云服务器时，需要向融云服务器提交 userId 对应的用户当前所加入的所有群组，此接口主要为防止应用中用户群信息同融云已知的用户所属群信息不同步。） 
	 * 
	 *@param  userId:被同步群信息的用户 Id。（必传） 
	 *@param  groupInfo:该用户的群信息，如群 Id 已经存在，则不会刷新对应群组名称，如果想刷新群组名称请调用刷新群组信息方法。
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Group)Sync(userId string, groupInfo []GroupInfo)(*CodeSuccessReslut, error) {
	  if( userId == "") {
		return nil,errors.New("Paramer 'userId' is required");
      }
      
	  if(len(groupInfo) == 0) {
		return nil,errors.New("Paramer 'groupInfo' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/group/sync.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("userId", userId)
	   for _,item := range groupInfo {
	     req.Param("group["+item.Id+"]", item.Name)
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
	 *刷新群组信息方法 
	 * 
	 *@param  groupId:群组 Id。（必传） 
	 *@param  groupName:群名称。（必传）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Group)Refresh(groupId string, groupName string)(*CodeSuccessReslut, error) {
	  if( groupId == "") {
		return nil,errors.New("Paramer 'groupId' is required");
      }
      
	  if( groupName == "") {
		return nil,errors.New("Paramer 'groupName' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/group/refresh.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("groupId", groupId)
	  req.Param("groupName", groupName)
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
	 *将用户加入指定群组，用户将可以收到该群的消息，同一用户最多可加入 500 个群，每个群最大至 3000 人。 
	 * 
	 *@param  userId:要加入群的用户 Id，可提交多个，最多不超过 1000 个。（必传） 
	 *@param  groupId:要加入的群 Id。（必传） 
	 *@param  groupName:要加入的群 Id 对应的名称。（必传）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Group)Join(userId []string, groupId string, groupName string)(*CodeSuccessReslut, error) {
	  if(len(userId) == 0) {
		return nil,errors.New("Paramer 'userId' is required");
      }
      
	  if( groupId == "") {
		return nil,errors.New("Paramer 'groupId' is required");
      }
      
	  if( groupName == "") {
		return nil,errors.New("Paramer 'groupName' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/group/join.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  for _,item := range userId {
	  req.Param("userId", item)
	  }
	  req.Param("groupId", groupId)
	  req.Param("groupName", groupName)
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
	 *查询群成员方法 
	 * 
	 *@param  groupId:群组Id。（必传）
	 *
	 *@return GroupUserQueryReslut
	 */
  func (self * Group)QueryUser(groupId string)(*GroupUserQueryReslut, error) {
	  if( groupId == "") {
		return nil,errors.New("Paramer 'groupId' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/group/user/query.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("groupId", groupId)
	  byteData, err := req.Bytes()
	  	if err != nil {
		   return nil,err
		 }else{
		   strData := string(byteData)
		   var ret = GroupUserQueryReslut{}
			  err = JsonParse(strData,&ret)
			  return &ret,err
			}
	  }
  

	/**
	 *退出群组方法（将用户从群中移除，不再接收该群组的消息.） 
	 * 
	 *@param  userId:要退出群的用户 Id.（必传） 
	 *@param  groupId:要退出的群 Id.（必传）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Group)Quit(userId []string, groupId string)(*CodeSuccessReslut, error) {
	  if(len(userId) == 0) {
		return nil,errors.New("Paramer 'userId' is required");
      }
      
	  if( groupId == "") {
		return nil,errors.New("Paramer 'groupId' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/group/quit.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  for _,item := range userId {
	  req.Param("userId", item)
	  }
	  req.Param("groupId", groupId)
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
	 *添加禁言群成员方法（在 App 中如果不想让某一用户在群中发言时，可将此用户在群组中禁言，被禁言用户可以接收查看群组中用户聊天信息，但不能发送消息。） 
	 * 
	 *@param  userId:用户 Id。（必传） 
	 *@param  groupId:群组 Id。（必传） 
	 *@param  minute:禁言时长，以分钟为单位，最大值为43200分钟。（必传）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Group)AddGagUser(userId string, groupId string, minute string)(*CodeSuccessReslut, error) {
	  if( userId == "") {
		return nil,errors.New("Paramer 'userId' is required");
      }
      
	  if( groupId == "") {
		return nil,errors.New("Paramer 'groupId' is required");
      }
      
	  if( minute == "") {
		return nil,errors.New("Paramer 'minute' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/group/user/gag/add.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("userId", userId)
	  req.Param("groupId", groupId)
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
	 *查询被禁言群成员方法 
	 * 
	 *@param  groupId:群组Id。（必传）
	 *
	 *@return ListGagGroupUserReslut
	 */
  func (self * Group)LisGagUser(groupId string)(*ListGagGroupUserReslut, error) {
	  if( groupId == "") {
		return nil,errors.New("Paramer 'groupId' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/group/user/gag/list.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("groupId", groupId)
	  byteData, err := req.Bytes()
	  	if err != nil {
		   return nil,err
		 }else{
		   strData := string(byteData)
		   var ret = ListGagGroupUserReslut{}
			  err = JsonParse(strData,&ret)
			  return &ret,err
			}
	  }
  

	/**
	 *移除禁言群成员方法 
	 * 
	 *@param  userId:用户Id。支持同时移除多个群成员（必传） 
	 *@param  groupId:群组Id。（必传）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Group)RollBackGagUser(userId []string, groupId string)(*CodeSuccessReslut, error) {
	  if(len(userId) == 0) {
		return nil,errors.New("Paramer 'userId' is required");
      }
      
	  if( groupId == "") {
		return nil,errors.New("Paramer 'groupId' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/group/user/gag/rollback.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  for _,item := range userId {
	  req.Param("userId", item)
	  }
	  req.Param("groupId", groupId)
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
	 *解散群组方法。（将该群解散，所有用户都无法再接收该群的消息。） 
	 * 
	 *@param  userId:操作解散群的用户 Id。（必传） 
	 *@param  groupId:要解散的群 Id。（必传）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Group)Dismiss(userId string, groupId string)(*CodeSuccessReslut, error) {
	  if( userId == "") {
		return nil,errors.New("Paramer 'userId' is required");
      }
      
	  if( groupId == "") {
		return nil,errors.New("Paramer 'groupId' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/group/dismiss.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("userId", userId)
	  req.Param("groupId", groupId)
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
  
