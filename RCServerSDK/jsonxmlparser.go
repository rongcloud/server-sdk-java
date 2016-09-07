package rcserversdk

import (
	"encoding/json"
	"encoding/xml"
	"strings"
)

/**
 * parse json string to struct
 */
func JsonParse(jsonStr string, v interface{}) error {
	dec := json.NewDecoder(strings.NewReader(jsonStr))
	err := dec.Decode(v)
	if err != nil {
		return err
	}
	return nil
}

/**
 * convert struct to json string
 */
func ToJson(v interface{}) (string, error) {
	bytes, err := json.Marshal(v)
	if err != nil {
		return "", err
	} else {
		return string(bytes), nil
	}
}

/**
 * parse json string to struct
 */
func XmlParse(xmlStr string, v interface{}) error {
	dec := xml.NewDecoder(strings.NewReader(xmlStr))
	err := dec.Decode(v)
	if err != nil {
		return err
	}
	return nil
}

/**
 * convert struct to json string
 */
func ToXml(v interface{}) (string, error) {
	bytes, err := xml.Marshal(v)
	if err != nil {
		return "", err
	} else {
		return string(bytes), nil
	}
}
