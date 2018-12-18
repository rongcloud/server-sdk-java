package sdk

import (
	"fmt"
	"testing"
)

func TestRCErrorNew(t *testing.T) {
	fmt.Println(RCErrorNew(200, ""))
}

func TestRCError_Code(t *testing.T) {
	rcerr := RCError{200, ""}
	fmt.Println(rcerr.ErrorCode())
}

func TestRCError_Error(t *testing.T) {
	rcerr := RCError{200, "rcerr"}
	fmt.Println(rcerr.Error())
}
