package rcserversdk

import(
		
	"errors"
	"github.com/astaxie/beego/httplib"
)
type SMS struct {
	AppKey    string
	AppSecret string
}



	/**
	 *获取图片验证码方法 
	 * 
	 *@param  appKey:应用Id
	 *
	 *@return SMSImageCodeReslut
	 */
  func (self * SMS)GetImageCode(appKey string)(*SMSImageCodeReslut, error) {
	  getValue :="?appKey=" + appKey
	  destinationUrl := RONGCLOUDSMSURI + "/getImgCode.json" + getValue
	  req := httplib.Get(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  byteData, err := req.Bytes()
	  	if err != nil {
		   return nil,err
		 }else{
		   strData := string(byteData)
		   var ret = SMSImageCodeReslut{}
			  err = JsonParse(strData,&ret)
			  return &ret,err
			}
	  }
  

	/**
	 *发送短信验证码方法。 
	 * 
	 *@param  mobile:接收短信验证码的目标手机号，每分钟同一手机号只能发送一次短信验证码，同一手机号 1 小时内最多发送 3 次。（必传） 
	 *@param  templateId:短信模板 Id，在开发者后台->短信服务->服务设置->短信模版中获取。（必传） 
	 *@param  region:手机号码所属国家区号，目前只支持中图区号 86） 
	 *@param  verifyId:图片验证标识 Id ，开启图片验证功能后此参数必传，否则可以不传。在获取图片验证码方法返回值中获取。 
	 *@param  verifyCode:图片验证码，开启图片验证功能后此参数必传，否则可以不传。
	 *
	 *@return SMSSendCodeReslut
	 */
  func (self * SMS)SendCode(mobile string, templateId string, region string, verifyId string, verifyCode string)(*SMSSendCodeReslut, error) {
	  if( mobile == "") {
		return nil,errors.New("Paramer 'mobile' is required");
      }
      
	  if( templateId == "") {
		return nil,errors.New("Paramer 'templateId' is required");
      }
      
	  if( region == "") {
		return nil,errors.New("Paramer 'region' is required");
      }
      
	  destinationUrl := RONGCLOUDSMSURI + "/sendCode.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("mobile", mobile)
	  req.Param("templateId", templateId)
	  req.Param("region", region)
	  req.Param("verifyId", verifyId)
	  req.Param("verifyCode", verifyCode)
	  byteData, err := req.Bytes()
	  	if err != nil {
		   return nil,err
		 }else{
		   strData := string(byteData)
		   var ret = SMSSendCodeReslut{}
			  err = JsonParse(strData,&ret)
			  return &ret,err
			}
	  }
  

	/**
	 *验证码验证方法 
	 * 
	 *@param  sessionId:短信验证码唯一标识，在发送短信验证码方法，返回值中获取。（必传） 
	 *@param  code:短信验证码内容。（必传）
	 *
	 *@return SMSVerifyCodeResult
	 */
  func (self * SMS)VerifyCode(sessionId string, code string)(*SMSVerifyCodeResult, error) {
	  if( sessionId == "") {
		return nil,errors.New("Paramer 'sessionId' is required");
      }
      
	  if( code == "") {
		return nil,errors.New("Paramer 'code' is required");
      }
      
	  destinationUrl := RONGCLOUDSMSURI + "/verifyCode.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("sessionId", sessionId)
	  req.Param("code", code)
	  byteData, err := req.Bytes()
	  	if err != nil {
		   return nil,err
		 }else{
		   strData := string(byteData)
		   var ret = SMSVerifyCodeResult{}
			  err = JsonParse(strData,&ret)
			  return &ret,err
			}
	  }
  
