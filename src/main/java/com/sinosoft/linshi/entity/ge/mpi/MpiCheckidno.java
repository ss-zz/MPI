package com.sinosoft.linshi.entity.ge.mpi;

import java.io.Serializable;
import java.util.Date;

public class MpiCheckidno implements Serializable {
    private String arCd;

    private Long bhgidno;

    private Long hgidno;

    private Date registerDate;

    private Long personCount;

    private String bl;

    private Date cteatetime;

    private static final long serialVersionUID = 1L;

    public String getArCd() {
        return arCd;
    }

    public void setArCd(String arCd) {
        this.arCd = arCd == null ? null : arCd.trim();
    }

    public Long getBhgidno() {
        return bhgidno;
    }

    public void setBhgidno(Long bhgidno) {
        this.bhgidno = bhgidno;
    }

    public Long getHgidno() {
        return hgidno;
    }

    public void setHgidno(Long hgidno) {
        this.hgidno = hgidno;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Long getPersonCount() {
        return personCount;
    }

    public void setPersonCount(Long personCount) {
        this.personCount = personCount;
    }

    public String getBl() {
        return bl;
    }

    public void setBl(String bl) {
        this.bl = bl == null ? null : bl.trim();
    }

    public Date getCteatetime() {
        return cteatetime;
    }

    public void setCteatetime(Date cteatetime) {
        this.cteatetime = cteatetime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        MpiCheckidno other = (MpiCheckidno) that;
        return (this.getArCd() == null ? other.getArCd() == null : this.getArCd().equals(other.getArCd()))
            && (this.getBhgidno() == null ? other.getBhgidno() == null : this.getBhgidno().equals(other.getBhgidno()))
            && (this.getHgidno() == null ? other.getHgidno() == null : this.getHgidno().equals(other.getHgidno()))
            && (this.getRegisterDate() == null ? other.getRegisterDate() == null : this.getRegisterDate().equals(other.getRegisterDate()))
            && (this.getPersonCount() == null ? other.getPersonCount() == null : this.getPersonCount().equals(other.getPersonCount()))
            && (this.getBl() == null ? other.getBl() == null : this.getBl().equals(other.getBl()))
            && (this.getCteatetime() == null ? other.getCteatetime() == null : this.getCteatetime().equals(other.getCteatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getArCd() == null) ? 0 : getArCd().hashCode());
        result = prime * result + ((getBhgidno() == null) ? 0 : getBhgidno().hashCode());
        result = prime * result + ((getHgidno() == null) ? 0 : getHgidno().hashCode());
        result = prime * result + ((getRegisterDate() == null) ? 0 : getRegisterDate().hashCode());
        result = prime * result + ((getPersonCount() == null) ? 0 : getPersonCount().hashCode());
        result = prime * result + ((getBl() == null) ? 0 : getBl().hashCode());
        result = prime * result + ((getCteatetime() == null) ? 0 : getCteatetime().hashCode());
        return result;
    }
}