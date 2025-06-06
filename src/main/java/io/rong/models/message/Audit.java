package io.rong.models.message;

/**
 * @author RongCloud
 */
public class Audit {

    private Integer auditType;
    private String strategy;

    public Audit(Integer auditType, String strategy) {
        this.auditType = auditType;
        this.strategy = strategy;
    }
    public Audit() {
    }

    public Integer getAuditType() {
        return auditType;
    }

    public void setAuditType(Integer auditType) {
        this.auditType = auditType;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

}
