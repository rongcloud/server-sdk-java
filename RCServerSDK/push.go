package rcserversdk

import(
		"github.com/astaxie/beego/httplib"
)
type Push struct {
	AppKey    string
	AppSecret string
}



	/**
	 *添加 Push 标签方法 
	 * 
	 *@param  userTag:用户标签。
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Push)SetUserPushTag(userTag UserTag)(*CodeSuccessReslut, error) {
	  destinationUrl := RONGCLOUDURI + "/user/tag/set.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  jsonStr,err := ToJson(userTag)
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
	 *广播消息方法（fromuserid 和 message为null即为不落地的push） 
	 * 
	 *@param  pushMessage:json数据
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Push)BroadcastPush(pushMessage PushMessage)(*CodeSuccessReslut, error) {
	  destinationUrl := RONGCLOUDURI + "/push.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  jsonStr,err := ToJson(pushMessage)
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
  
