package sdk

import (
	"encoding/json"
	"github.com/astaxie/beego/httplib"
	"time"
)

// ListWordFilterResult Result of listWordFilter
type ListWordFilterResult struct {
	Words []SensitiveWord `json:"words"`
}

// SensitiveWord Sensitive word
type SensitiveWord struct {
	Type        string `json:"type"`
	Word        string `json:"word"`
	ReplaceWord string `json:"replaceWord"`
}

// SensitiveAdd Add a sensitive word
/*
*@param  keyword: Sensitive word, max length 32 characters, format: Chinese characters, numbers, letters
*@param  replace: Replacement for sensitive word, max length 32 characters, can be empty for blocking
*@param  sensitiveType: 0: Replace sensitive word, 1: Block sensitive word
*
*@return error
 */
func (rc *RongCloud) SensitiveAdd(keyword, replace string, sensitiveType int) error {
	if keyword == "" {
		return RCErrorNew(1002, "Paramer 'keyword' is required")
	}
	if replace == "" {
		return RCErrorNew(1002, "Paramer 'replace' is required")
	}
	req := httplib.Post(rc.rongCloudURI + "/sensitiveword/add." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("word", keyword)
	switch sensitiveType {
	case 0:
		req.Param("replaceWord", replace)
	case 1:

	default:
		return RCErrorNew(1002, "Paramer 'replace' is required")
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// SensitiveGetList Method to query the sensitive word list
/*
*@return ListWordFilterResult error
 */
func (rc *RongCloud) SensitiveGetList() (ListWordFilterResult, error) {

	req := httplib.Post(rc.rongCloudURI + "/sensitiveword/list." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return ListWordFilterResult{}, err
	}

	var ret ListWordFilterResult
	if err := json.Unmarshal(resp, &ret); err != nil {
		return ListWordFilterResult{}, err
	}
	return ret, err

}

// SensitiveRemove Method to remove a sensitive word (Removes a specific sensitive word from the sensitive word list.)
/*
*@param  keywords: You can delete up to 50 sensitive words at a time, and the changes will take effect after 2 hours.
*
*@return error
 */
func (rc *RongCloud) SensitiveRemove(keywords []string) error {
	if len(keywords) == 0 {
		return RCErrorNew(1002, "Paramer 'keywords' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/sensitiveword/batch/delete." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	for _, v := range keywords {
		req.Param("words", v)
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err

}
