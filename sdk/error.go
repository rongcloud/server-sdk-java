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

// CodeResult represents the status code and error code returned by RongCloud.
type CodeResult struct {
	Code         int    `json:"code"`         // The return code, 200 indicates success.
	ErrorMessage string `json:"errorMessage"` // The error message.
}

// RCErrorNew creates a new error message.
func RCErrorNew(code int, text string) error {
	return CodeResult{code, text}
}

// Error retrieves the error message.
func (e CodeResult) Error() string {
	return strconv.Itoa(e.Code) + ": " + e.ErrorMessage
}

// ErrorCode retrieves the error code.
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
	Code    int    `json:"code"` // The return code, 10000 indicates success.
	Message string `json:"msg"`  // The error message.
}

// RCErrorNewV2 creates a new error message for API v2.
func RCErrorNewV2(code int, text string) error {
	return CodeResultV2{code, text}
}

// Error retrieves the error message
func (e CodeResultV2) Error() string {
	return strconv.Itoa(e.Code) + ": " + e.Message
}

// ErrorCode retrieves the error code
func (e CodeResultV2) ErrorCode() int {
	return e.Code
}
