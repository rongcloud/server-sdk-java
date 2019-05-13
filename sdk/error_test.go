package sdk

import (
	"testing"
)

func TestRCErrorNew(t *testing.T) {
	t.Log(RCErrorNew(200, ""))
}

func TestCodeResult_Code(t *testing.T) {
	err := CodeResult{200, ""}
	t.Log(err.ErrorCode())
}

func TestCodeResult_Error(t *testing.T) {
	err := CodeResult{200, "rcerr"}
	t.Log(err.Error())
}
