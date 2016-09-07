package rcserversdk

import(
		
	"errors"
	"github.com/astaxie/beego/httplib"
)
type Wordfilter struct {
	AppKey    string
	AppSecret string
}



	/**
	 *添加敏感词方法（设置敏感词后，App 中用户不会收到含有敏感词的消息内容，默认最多设置 50 个敏感词。） 
	 * 
	 *@param  word:敏感词，最长不超过 32 个字符。（必传）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Wordfilter)Add(word string)(*CodeSuccessReslut, error) {
	  if( word == "") {
		return nil,errors.New("Paramer 'word' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/wordfilter/add.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("word", word)
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
	 *查询敏感词列表方法 
	 *
	 *
	 *@return ListWordfilterReslut
	 */
  func (self * Wordfilter)GetList()(*ListWordfilterReslut, error) {
	  destinationUrl := RONGCLOUDURI + "/wordfilter/list.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  byteData, err := req.Bytes()
	  	if err != nil {
		   return nil,err
		 }else{
		   strData := string(byteData)
		   var ret = ListWordfilterReslut{}
			  err = JsonParse(strData,&ret)
			  return &ret,err
			}
	  }
  

	/**
	 *移除敏感词方法（从敏感词列表中，移除某一敏感词。） 
	 * 
	 *@param  word:敏感词，最长不超过 32 个字符。（必传）
	 *
	 *@return CodeSuccessReslut
	 */
  func (self * Wordfilter)Delete(word string)(*CodeSuccessReslut, error) {
	  if( word == "") {
		return nil,errors.New("Paramer 'word' is required");
      }
      
	  destinationUrl := RONGCLOUDURI + "/wordfilter/delete.json" 
	  req := httplib.Post(destinationUrl)
	  fillHeader(req, self.AppKey, self.AppSecret)
	  req.Param("word", word)
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
  
