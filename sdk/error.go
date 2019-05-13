package sdk

import "strconv"

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
