package sdk

// RCErrorNew 创建新的err信息
func RCErrorNew(code int, text string) error {
	return &RCError{code, text}
}

// RCError 容云error信息增加错误码
type RCError struct {
	code    int
	message string
}

// Error 获取错误信息
func (e *RCError) Error() string {
	return string(e.code) + ":" + e.message
}

// ErrorCode 获取错误码
func (e *RCError) ErrorCode() string {
	return string(e.code)
}
