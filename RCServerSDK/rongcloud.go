/**
 * 融云 Server API go 客户端
 * create by kitName
 * create datetime : 2017-02-09 
 * 
 * v2.0.1
 */
package rcserversdk;
import(
	"crypto/sha1"
  	"fmt"
  	"io"
  	"math/rand"
  	"strconv"
  	"time"

  	"github.com/astaxie/beego/httplib"
)

const (
	RONGCLOUDSMSURI = "http://api.sms.ronghub.com";
	RONGCLOUDURI = "http://api.cn.ronghub.com"
	UTF8         = "UTF-8"
)

type RongCloud struct {
	AppKey string
	AppSecret string
	
	User User
	Message Message
	Wordfilter Wordfilter
	Group Group
	Chatroom Chatroom
	Push Push
	SMS SMS
}


func CreateRongCloud(appKey, appSecret string)*RongCloud {
	rc := &RongCloud {
		AppKey : appKey,
		AppSecret : appSecret,
		
		User : User {
			AppKey : appKey,
			AppSecret : appSecret,
		},
		
		Message : Message {
			AppKey : appKey,
			AppSecret : appSecret,
		},
		
		Wordfilter : Wordfilter {
			AppKey : appKey,
			AppSecret : appSecret,
		},
		
		Group : Group {
			AppKey : appKey,
			AppSecret : appSecret,
		},
		
		Chatroom : Chatroom {
			AppKey : appKey,
			AppSecret : appSecret,
		},
		
		Push : Push {
			AppKey : appKey,
			AppSecret : appSecret,
		},
		
		SMS : SMS {
			AppKey : appKey,
			AppSecret : appSecret,
		},
		
	}
	return rc
}

//本地生成签名
//Signature (数据签名)计算方法：将系统分配的 App Secret、Nonce (随机数)、
//Timestamp (时间戳)三个字符串按先后顺序拼接成一个字符串并进行 SHA1 哈希计算。如果调用的数据签名验证失败，接口调用会返回 HTTP 状态码 401。
func getSignature(appSecret string) (nonce, timestamp, signature string) {
	nonceInt := rand.Int()
	nonce = strconv.Itoa(nonceInt)
	timeInt64 := time.Now().Unix()
	timestamp = strconv.FormatInt(timeInt64, 10)
	h := sha1.New()
	io.WriteString(h, appSecret+nonce+timestamp)
	signature = fmt.Sprintf("%x", h.Sum(nil))
	return
}

//API签名
func fillHeader(req *httplib.BeegoHTTPRequest, appKey, appSecret string) {
  nonce, timestamp, signature := getSignature(appSecret)
  req.Header("App-Key", appKey)
  req.Header("Nonce", nonce)
  req.Header("Timestamp", timestamp)
  req.Header("Signature", signature)
  req.Header("Content-Type", "application/x-www-form-urlencoded")
}

func fillJsonHeader(req *httplib.BeegoHTTPRequest){
  req.Header("Content-Type", "application/json")
}
