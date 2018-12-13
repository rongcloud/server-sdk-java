package sdk

import (
	"testing"
	"fmt"
)

func TestRCErrorNew(t *testing.T) {
	fmt.Println("========TestRCErrorNew========")
	fmt.Println(RCErrorNew(200,"nil"))
	fmt.Println("========End========")
}

func TestRCError_Code(t *testing.T) {
	fmt.Println("========TestRCError_Code========")
	rcerr := RCError{200,"nil"}
	fmt.Println(rcerr.Code())
	fmt.Println("========End========")
}

func TestRCError_Error(t *testing.T) {
	fmt.Println("========TestRCError_Error========")
	rcerr := RCError{200,"nil"}
	fmt.Println(rcerr.Error())
	fmt.Println("========End========")
}