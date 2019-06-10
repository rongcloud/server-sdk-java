package sdk

import (
	"encoding/json"
	"time"

	"github.com/astaxie/beego/httplib"
)

// Tag TagSet 参数
type Tag struct {
	UserID string   `json:"userId"` // 用户 Id。（必传）
	Tags   []string `json:"tags"`   // 用户标签，一个用户最多添加 20 个标签，每个 tag 最大不能超过 40 个字节，标签中不能包含特殊字符。（必传）
}

// TagBatch TagBatchSet 参数
type TagBatch struct {
	UserIDs []string `json:"userIds"` // 用户 Id，一次最多支持 1000 个用户。（必传）
	Tags    []string `json:"tags"`    // 用户标签，一个用户最多添加 20 个标签，每个 tag 最大不能超过 40 个字节，标签中不能包含特殊字符。（必传）
}

// TagResult TagGet 返回值
type TagResult struct {
	*CodeResult
	Result map[string][]string `json:"result"` // 用户所有的标签数组。
}

// TagSet 为应用中的用户添加标签，如果某用户已经添加了标签，再次对用户添加标签时将覆盖之前设置的标签内容。
/*
*@param  tag :标签 Tag 构造体。
*
*@return error
 */
func (rc *RongCloud) TagSet(tag Tag) error {
	req := httplib.Post(rc.rongCloudURI + "/user/tag/set." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req, err := req.JSONBody(tag)
	if err != nil {
		rc.urlError(err)
		return err
	}
	rep, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
		return err
	}
	var code CodeResult
	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}
	if code.Code != 200 {

		return RCErrorNew(code.Code, code.ErrorMessage)
	}
	return nil
}

// TagBatchSet 为应用中的用户批量添加标签，如果某用户已经添加了标签，再次对用户添加标签时将覆盖之前设置的标签内容。
/*
*@param  t :标签 TagBatch 构造体。
*
*@return error
 */
func (rc *RongCloud) TagBatchSet(tagBatch TagBatch) error {
	req := httplib.Post(rc.rongCloudURI + "/user/tag/batch/set." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req, err := req.JSONBody(tagBatch)
	if err != nil {
		rc.urlError(err)
		return err
	}

	rep, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
		return err
	}
	var code CodeResult
	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}
	if code.Code != 200 {

		return RCErrorNew(code.Code, code.ErrorMessage)
	}
	return nil
}

// TagGet 查询用户所有标签功能，支持批量查询每次最多查询 50 个用户。
/*
*@param  userIds: 用户 ID。
*
*@return error
 */
func (rc *RongCloud) TagGet(userIds []string) (TagResult, error) {
	req := httplib.Post(rc.rongCloudURI + "/user/tags/get." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	for _, v := range userIds {
		req.Param("userIds", v)
	}
	rep, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
		return TagResult{}, err
	}

	var tag TagResult
	if err := json.Unmarshal(rep, &tag); err != nil {
		return TagResult{}, err
	}
	if tag.Code != 200 {
		return TagResult{}, RCErrorNew(tag.Code, tag.ErrorMessage)
	}

	return tag, nil

}
