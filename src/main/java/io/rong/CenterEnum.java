package io.rong;

public enum CenterEnum {

    BJ("https://api.rong-api.com", "https://api-b.rong-api.com"),
    SG("https://api.sg-light-api.com", "https://api-b.sg-light-api.com"),
    SG_B("https://api.sg-b-light-api.com", "https://api-b.sg-b-light-api.com"),
    NA("https://api.us-light-api.com", "https://api-b.us-light-api.com"),
    SAU("https://api.sau-light-api.com", "https://api-b.sau-light-api.com"),
    ;


    private final String primaryUrl;
    private final String backupUrl;

    CenterEnum(String primaryUrl, String backupUrl) {
        this.primaryUrl = primaryUrl;
        this.backupUrl = backupUrl;
    }

    public String getPrimaryUrl() {
        return primaryUrl;
    }

    public String getBackupUrl() {
        return backupUrl;
    }

}
