package io.rong.models;

public class CommonConstrants {
    public static final int RCLOUD_TESTUSERS_OUT= 20008; // Exceeded the limit of test users. Please apply for additional quota.
    public static final int RCLOUD_SIGN_ERROR= 20000; // Signature error. Please verify the App Key and Secret.
    public static final int RCLOUD_FREQUENCY_OUT = 20001; // Exceeded the frequency limit. "20002= {{name}} count exceeds the limit, {{name}} >= {{min}} and {{name}} <= {{max}}"
    public static final int RCLOUD_LEN_OUT = 20003; // Exceeded the maximum length of userId (64 bytes).
    public static final int RCLOUD_BAN_TIMEOUT = 20004; // Invalid ban duration. The minute value must be >= 1 and <= 43200 (in minutes).
    public static final int RCLOUD_PARAM_NULL = 1002; // Parameter error. Required field is missing.
    public static final int RCLOUD_PARAM_ERROR= 20006; // "{{name}} parameter is incorrect. Expected type: {{type}}, but got {{currentType}}."
    public static final int RCLOUD_ADDRESS_ILLEGAL= 20007; // "{{name}} is not a valid address. Please verify the correctness of {{name}}."
}
