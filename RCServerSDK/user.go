package rcserversdk

import(
		
	"strconv"
	
	"errors"
	"github.com/astaxie/beego/httplib"
)
type User struct {
	AppKey    string
	AppSecret string
}



	/**
	 *获取 Token 方法 
	 * 
	 *@param  userId:用户 Id，最大长度 64 字节.是用户在 App 中的唯一标识码，必须保证在同一个 App 内不重复，重复的用户 Id 将被当作是同一用户。（必传） 
	 *@param  name:用户名称，最大长度 128 字节.用来在 Push 推送时显示用户的名称.用户名称，最大长度 128 字节.用来在 Push 推送时显示用户的名称。（必传） 
	 *@param  portraitUri:用户头像 URI，最大长度 1024 字节.用来在 Push 推送时显示用户的头像。（必传）
	 *
	 *@return TokenReslut
	 */
  func (self * User)GetToken(userId string, name string, portraitUri string)(*TokenReslut, error) {
	  if( userId == "") {
		return nil,errors.New("Paramer 'userId' is required");
      }
      
	  if( name == "") {
		return nil,errors.New("Paramer 'name' is required");
      }
      
	  if( portraitUri == "") {
		return nil,errors.New("Paramer 'portraitUri' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/user/getToken.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("userId", userId)
	  req.Param("name", name)
	  req.Param("portraitUri", portraitUri)
	  byteData, err := req.Bytes()
	  	if err != nil {
		   return nil,err
		 }else{
		   strData := string(byteData)
		   var ret = TokenReslut{}
			  err = JsonParse(strData,&ret)
			  return &ret,err
			}
	  }
  

	/**
	 *刷新用户信息方法 
	 * 
	 *@param  userId:用户 Id，最大长度 64 字节.是用户在 App 中的唯一标识码，必须保证在同一个 App 内不重复，重复的用户 Id 将被当作是同一用户。（必传） 
	 *@param  name:用户名称，最大长度 128 字节。用来在 Push 推送时，显示用户的名称，刷新用户名称后 5 分钟内生效。（可选，提供即刷新，不提供忽略） 
	 *@param  portraitUri:用户头像 URI，最大长度 1024 字节。用来在 Push 推送时显示。（可选，提供即刷新，不提供忽略）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * User)Refresh(userId string, name string, portraitUri string)(*CodeSuccessReslut, error) {
	  if( userId == "") {
		return nil,errors.New("Paramer 'userId' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/user/refresh.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("userId", userId)
	  req.Param("name", name)
	  req.Param("portraitUri", portraitUri)
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
	 *检查用户在线状态 方法 
	 * 
	 *@param  userId:用户 Id，最大长度 64 字节。是用户在 App 中的唯一标识码，必须保证在同一个 App 内不重复，重复的用户 Id 将被当作是同一用户。（必传）
	 *
	 *@return CheckOnlineReslut
	 */
  func (self * User)CheckOnline(userId string)(*CheckOnlineReslut, error) {
	  if( userId == "") {
		return nil,errors.New("Paramer 'userId' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/user/checkOnline.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("userId", userId)
	  byteData, err := req.Bytes()
	  	if err != nil {
		   return nil,err
		 }else{
		   strData := string(byteData)
		   var ret = CheckOnlineReslut{}
			  err = JsonParse(strData,&ret)
			  return &ret,err
			}
	  }
  

	/**
	 *封禁用户方法（每秒钟限 100 次） 
	 * 
	 *@param  userId:用户 Id。（必传） 
	 *@param  minute:封禁时长,单位为分钟，最大值为43200分钟。（必传）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * User)Block(userId string, minute int)(*CodeSuccessReslut, error) {
	  if( userId == "") {
		return nil,errors.New("Paramer 'userId' is required");
      }
      
	  if(minute == 0) {
		return nil,errors.New("Paramer 'minute' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/user/block.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("userId", userId)
	  req.Param("minute", strconv.Itoa(minute))
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
	 *解除用户封禁方法（每秒钟限 100 次） 
	 * 
	 *@param  userId:用户 Id。（必传）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * User)UnBlock(userId string)(*CodeSuccessReslut, error) {
	  if( userId == "") {
		return nil,errors.New("Paramer 'userId' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/user/unblock.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("userId", userId)
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
	 *获取被封禁用户方法（每秒钟限 100 次） 
	 *
	 *
	 *@return QueryBlockUserReslut
	 */
  func (self * User)QueryBlock()(*QueryBlockUserReslut, error) {
	  destinationUrl := RONGCLOUDURI + "/user/block/query.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  byteData, err := req.Bytes()
	  	if err != nil {
		   return nil,err
		 }else{
		   strData := string(byteData)
		   var ret = QueryBlockUserReslut{}
			  err = JsonParse(strData,&ret)
			  return &ret,err
			}
	  }
  

	/**
	 *添加用户到黑名单方法（每秒钟限 100 次） 
	 * 
	 *@param  userId:用户 Id。（必传） 
	 *@param  blackUserId:被加到黑名单的用户Id。（必传）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * User)AddBlacklist(userId string, blackUserId string)(*CodeSuccessReslut, error) {
	  if( userId == "") {
		return nil,errors.New("Paramer 'userId' is required");
      }
      
	  if( blackUserId == "") {
		return nil,errors.New("Paramer 'blackUserId' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/user/blacklist/add.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("userId", userId)
	  req.Param("blackUserId", blackUserId)
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
	 *获取某用户的黑名单列表方法（每秒钟限 100 次） 
	 * 
	 *@param  userId:用户 Id。（必传）
	 *
	 *@return QueryBlacklistUserReslut
	 */
  func (self * User)QueryBlacklist(userId string)(*QueryBlacklistUserReslut, error) {
	  if( userId == "") {
		return nil,errors.New("Paramer 'userId' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/user/blacklist/query.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("userId", userId)
	  byteData, err := req.Bytes()
	  	if err != nil {
		   return nil,err
		 }else{
		   strData := string(byteData)
		   var ret = QueryBlacklistUserReslut{}
			  err = JsonParse(strData,&ret)
			  return &ret,err
			}
	  }
  

	/**
	 *从黑名单中移除用户方法（每秒钟限 100 次） 
	 * 
	 *@param  userId:用户 Id。（必传） 
	 *@param  blackUserId:被移除的用户Id。（必传）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * User)RemoveBlacklist(userId string, blackUserId string)(*CodeSuccessReslut, error) {
	  if( userId == "") {
		return nil,errors.New("Paramer 'userId' is required");
      }
      
	  if( blackUserId == "") {
		return nil,errors.New("Paramer 'blackUserId' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/user/blacklist/remove.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("userId", userId)
	  req.Param("blackUserId", blackUserId)
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
  
