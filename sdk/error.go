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

// api v2 error
var codePoolV2 = sync.Pool{
	New: func() interface{} {
		return CodeResultV2{}
	},
}

type CodeResultV2 struct {
	Code    int    `json:"code"` // 返回码，10000 为正常。
	Message string `json:"msg"`  // 错误信息
}

// RCErrorNew 创建新的err信息
func RCErrorNewV2(code int, text string) error {
	return CodeResultV2{code, text}
}

// Error 获取错误信息
func (e CodeResultV2) Error() string {
	return strconv.Itoa(e.Code) + ": " + e.Message
}

// ErrorCode 获取错误码
func (e CodeResultV2) ErrorCode() int {
	return e.Code
}
