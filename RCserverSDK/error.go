package sdk

// RCErrorNew 创建新的err信息
func RCErrorNew(code int,text string) *RCError {
	return &RCError{code,text}
}

// RCError 容云error信息增加错误码
type RCError struct {
	code int
	s string
}

// Error 获取错误信息
func (e *RCError) Error() string {
	return e.s
}


// Code 获取错误码
func (e *RCError) Code() int {
	return e.code
}
