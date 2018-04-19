package com.sinosoft.pixpdqv3.dto;

import java.io.Serializable;

/**
 * com.sinosoft.pixpdqv3.dto.MergeDto
 *
 * @author bysun
 *         13-4-25 上午11:26
 */
public class MergeDto implements Serializable{

    private static final long serialVersionUID = 2662909905308346459L;
    private String retiredId;
    private String survivingId;
    private String domainUniqueSign;

    public String getRetiredId() {
        return retiredId;
    }

    public void setRetiredId(String retiredId) {
        this.retiredId = retiredId;
    }

    public String getSurvivingId() {
        return survivingId;
    }

    public void setSurvivingId(String survivingId) {
        this.survivingId = survivingId;
    }

    public String getDomainUniqueSign() {
        return domainUniqueSign;
    }

    public void setDomainUniqueSign(String domainUniqueSign) {
        this.domainUniqueSign = domainUniqueSign;
    }
}
