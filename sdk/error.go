package sdk

import (
	"strconv"
	"sync"
)

var codePool = sync.Pool{
	New: func() interface{} {
		return CodeResult{}
	},
}

// CodeResult 融云返回状态码和错误码
type CodeResult struct {
	Code         int    `json:"code"`         // 返回码，200 为正常。
	ErrorMessage string `json:"errorMessage"` // 错误信息
}

// RCErrorNew 创建新的err信息
func RCErrorNew(code int, text string) error {
	return CodeResult{code, text}
}

// Error 获取错误信息
func (e CodeResult) Error() string {
	return strconv.Itoa(e.Code) + ": " + e.ErrorMessage
}

// ErrorCode 获取错误码
func (e CodeResult) ErrorCode() int {
	return e.Code
}
