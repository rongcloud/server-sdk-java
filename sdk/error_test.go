package sdk

import (
	"testing"
)

func TestRCErrorNew(t *testing.T) {
	t.Log(RCErrorNew(200, ""))
}

func TestRCError_Code(t *testing.T) {
	err := RCError{200, ""}
	t.Log(err.ErrorCode())
}

func TestRCError_Error(t *testing.T) {
	err := RCError{200, "rcerr"}
	t.Log(err.Error())
}
