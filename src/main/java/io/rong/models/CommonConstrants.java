package io.rong.models;

public class CommonConstrants {
    public static final int RCLOUD_TESTUSERS_OUT= 20008;//测试用户数量超限，测试用户数量超限。请提供蛋申请
    public static final int RCLOUD_SIGN_ERROR= 20000;//签名错误, 请排查 Appkey、Secret 是否正确
    public static final int RCLOUD_FREQUENCY_OUT = 20001;//调用频率超限，每秒钟限制 100 次，调整频率上限请移步至专有云 http："//www.rongcloud.cn/pricing#pay20002=	{{name}} 个数超限, {{name}} >= {{min}} 且 {{name}} <= {{max}}"
    public static final int RCLOUD_LEN_OUT = 20003;//userId 长度超限 最大长度 64字节
    public static final int RCLOUD_BAN_TIMEOUT = 20004;//封禁时间 minute 不正确，minute ">=" 1 且 minute "<=" 43200 单位： 分钟
    public static final int RCLOUD_PARAM_NULL = CommonConstrants.RCLOUD_PARAM_NULL;//非空必传项
    public static final int RCLOUD_PARAM_ERROR= 20006;//"{{name}} 参数不正确，请检查参数类型,应该为 {{type}} 传入为 {{currentType}}"
    public static final int RCLOUD_ADDRESS_ILLEGAL= 20007;//"{{name}} 不是合法地址，请检查 {{name}} 是否正确"

}
