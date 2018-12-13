package RCserverSDK

import (
	"errors"
	"github.com/astaxie/beego/httplib"
	"encoding/json"
	)

// ListWordfilterReslut listWordfilter返回结果
type ListWordfilterReslut struct {
	Words []SensitiveWord `json:"words"`
}

// SensitiveWord
type SensitiveWord struct {
	Type string `json:"type"`
	Word string `json:"word"`
	ReplaceWord string `json:"replaceWord"`
}


/**
 *添加敏感词方法
 *
 *@param  word:敏感词，最长不超过 32 个字符。（必传）
 *
 *@return error
 */

func (rc *RongCloud) SensitiveAdd (keyword, replace string, sensitiveType int) error {
	if(keyword == "") {
		return errors.New("20005 Paramer 'keyword' is required");
	}
	if(replace == "") {
		return errors.New("20005 Paramer 'replace' is required");
	}
	req := httplib.Post(rc.RongCloudURI + "/sensitiveword/add." + ReqType)
	rc.FillHeader(req)
	req.Param("word", keyword)
	switch sensitiveType {
	case 0:
		req.Param("replaceWord", replace)
	case 1:

	default:
		return errors.New("20005 Paramer 'replace' is required");
	}
	byteData, err := req.Bytes()
	if err != nil {
		return err
	}
	var code CodeReslut
	if err := json.Unmarshal(byteData, &code); err != nil {
		return err
	}
	if code.Code != 200 {
		return RCErrorNew(code.Code, code.ErrorMessage)
	}

	return nil
}


/**
 *查询敏感词列表方法
 *
 *
 *@return ListWordfilterReslut
 */
func (rc *RongCloud) SensitiveGetList () (ListWordfilterReslut, error) {

	req := httplib.Post(rc.RongCloudURI + "/sensitiveword/list." + ReqType)
	rc.FillHeader(req)
	byteData, err := req.Bytes()
	if err != nil {
		return ListWordfilterReslut{},err
	}

	var ret  ListWordfilterReslut
	if err := json.Unmarshal(byteData, &ret); err != nil {
		return ListWordfilterReslut{},err
	}
	var code CodeReslut
	if err := json.Unmarshal(byteData, &code); err != nil {
		return ListWordfilterReslut{},err
	}
	if code.Code != 200 {
		return ListWordfilterReslut{},RCErrorNew(code.Code, code.ErrorMessage)
	}
	return ret,err

}


/**
 *移除敏感词方法（从敏感词列表中，移除某一敏感词。）
 *
 *@param  word:敏感词，最长不超过 32 个字符。（必传）
 *
 *@return error
 */
func (rc *RongCloud) SensitiveRemove (keywords []string) error {
	if(len(keywords) == 0) {
		return errors.New("20005 Paramer 'keywords' is required");
	}

	req := httplib.Post(rc.RongCloudURI + "/sensitiveword/batch/delete." + ReqType)
	rc.FillHeader(req)
	for _,v := range keywords{
		req.Param("words", v)
	}
	byteData, err := req.Bytes()
	if err != nil {
		return err
	}

	var code CodeReslut
	if err := json.Unmarshal(byteData, &code); err != nil {
		return err
	}
	if code.Code != 200 {
		return errors.New(code.ErrorMessage)
	}
	return nil

}
