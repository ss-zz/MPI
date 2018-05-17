package com.sinosoft.linshi.entity.ge.mpi;

import java.io.Serializable;

public class ComHnOrganise implements Serializable {
    private Short years;

    private String orgcode;

    private String apanagecode;

    private String orgname;

    private String ecomtype;

    private Short orgtype;

    private Short mgrtype;

    private String addr;

    private String telphone;

    private String ogran;

    private String sub;

    private String phtOrgtype;

    private String direct;

    private String jikongzhongxin;

    private Short orgclass;

    private Short orglevel;

    private String netReportCond;

    private String orgSys;

    private String orgClassLevel;

    private String orgnameAll;

    private static final long serialVersionUID = 1L;

    public Short getYears() {
        return years;
    }

    public void setYears(Short years) {
        this.years = years;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode == null ? null : orgcode.trim();
    }

    public String getApanagecode() {
        return apanagecode;
    }

    public void setApanagecode(String apanagecode) {
        this.apanagecode = apanagecode == null ? null : apanagecode.trim();
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname == null ? null : orgname.trim();
    }

    public String getEcomtype() {
        return ecomtype;
    }

    public void setEcomtype(String ecomtype) {
        this.ecomtype = ecomtype == null ? null : ecomtype.trim();
    }

    public Short getOrgtype() {
        return orgtype;
    }

    public void setOrgtype(Short orgtype) {
        this.orgtype = orgtype;
    }

    public Short getMgrtype() {
        return mgrtype;
    }

    public void setMgrtype(Short mgrtype) {
        this.mgrtype = mgrtype;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public String getOgran() {
        return ogran;
    }

    public void setOgran(String ogran) {
        this.ogran = ogran == null ? null : ogran.trim();
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub == null ? null : sub.trim();
    }

    public String getPhtOrgtype() {
        return phtOrgtype;
    }

    public void setPhtOrgtype(String phtOrgtype) {
        this.phtOrgtype = phtOrgtype == null ? null : phtOrgtype.trim();
    }

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct == null ? null : direct.trim();
    }

    public String getJikongzhongxin() {
        return jikongzhongxin;
    }

    public void setJikongzhongxin(String jikongzhongxin) {
        this.jikongzhongxin = jikongzhongxin == null ? null : jikongzhongxin.trim();
    }

    public Short getOrgclass() {
        return orgclass;
    }

    public void setOrgclass(Short orgclass) {
        this.orgclass = orgclass;
    }

    public Short getOrglevel() {
        return orglevel;
    }

    public void setOrglevel(Short orglevel) {
        this.orglevel = orglevel;
    }

    public String getNetReportCond() {
        return netReportCond;
    }

    public void setNetReportCond(String netReportCond) {
        this.netReportCond = netReportCond == null ? null : netReportCond.trim();
    }

    public String getOrgSys() {
        return orgSys;
    }

    public void setOrgSys(String orgSys) {
        this.orgSys = orgSys == null ? null : orgSys.trim();
    }

    public String getOrgClassLevel() {
        return orgClassLevel;
    }

    public void setOrgClassLevel(String orgClassLevel) {
        this.orgClassLevel = orgClassLevel == null ? null : orgClassLevel.trim();
    }

    public String getOrgnameAll() {
        return orgnameAll;
    }

    public void setOrgnameAll(String orgnameAll) {
        this.orgnameAll = orgnameAll == null ? null : orgnameAll.trim();
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
        ComHnOrganise other = (ComHnOrganise) that;
        return (this.getYears() == null ? other.getYears() == null : this.getYears().equals(other.getYears()))
            && (this.getOrgcode() == null ? other.getOrgcode() == null : this.getOrgcode().equals(other.getOrgcode()))
            && (this.getApanagecode() == null ? other.getApanagecode() == null : this.getApanagecode().equals(other.getApanagecode()))
            && (this.getOrgname() == null ? other.getOrgname() == null : this.getOrgname().equals(other.getOrgname()))
            && (this.getEcomtype() == null ? other.getEcomtype() == null : this.getEcomtype().equals(other.getEcomtype()))
            && (this.getOrgtype() == null ? other.getOrgtype() == null : this.getOrgtype().equals(other.getOrgtype()))
            && (this.getMgrtype() == null ? other.getMgrtype() == null : this.getMgrtype().equals(other.getMgrtype()))
            && (this.getAddr() == null ? other.getAddr() == null : this.getAddr().equals(other.getAddr()))
            && (this.getTelphone() == null ? other.getTelphone() == null : this.getTelphone().equals(other.getTelphone()))
            && (this.getOgran() == null ? other.getOgran() == null : this.getOgran().equals(other.getOgran()))
            && (this.getSub() == null ? other.getSub() == null : this.getSub().equals(other.getSub()))
            && (this.getPhtOrgtype() == null ? other.getPhtOrgtype() == null : this.getPhtOrgtype().equals(other.getPhtOrgtype()))
            && (this.getDirect() == null ? other.getDirect() == null : this.getDirect().equals(other.getDirect()))
            && (this.getJikongzhongxin() == null ? other.getJikongzhongxin() == null : this.getJikongzhongxin().equals(other.getJikongzhongxin()))
            && (this.getOrgclass() == null ? other.getOrgclass() == null : this.getOrgclass().equals(other.getOrgclass()))
            && (this.getOrglevel() == null ? other.getOrglevel() == null : this.getOrglevel().equals(other.getOrglevel()))
            && (this.getNetReportCond() == null ? other.getNetReportCond() == null : this.getNetReportCond().equals(other.getNetReportCond()))
            && (this.getOrgSys() == null ? other.getOrgSys() == null : this.getOrgSys().equals(other.getOrgSys()))
            && (this.getOrgClassLevel() == null ? other.getOrgClassLevel() == null : this.getOrgClassLevel().equals(other.getOrgClassLevel()))
            && (this.getOrgnameAll() == null ? other.getOrgnameAll() == null : this.getOrgnameAll().equals(other.getOrgnameAll()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getYears() == null) ? 0 : getYears().hashCode());
        result = prime * result + ((getOrgcode() == null) ? 0 : getOrgcode().hashCode());
        result = prime * result + ((getApanagecode() == null) ? 0 : getApanagecode().hashCode());
        result = prime * result + ((getOrgname() == null) ? 0 : getOrgname().hashCode());
        result = prime * result + ((getEcomtype() == null) ? 0 : getEcomtype().hashCode());
        result = prime * result + ((getOrgtype() == null) ? 0 : getOrgtype().hashCode());
        result = prime * result + ((getMgrtype() == null) ? 0 : getMgrtype().hashCode());
        result = prime * result + ((getAddr() == null) ? 0 : getAddr().hashCode());
        result = prime * result + ((getTelphone() == null) ? 0 : getTelphone().hashCode());
        result = prime * result + ((getOgran() == null) ? 0 : getOgran().hashCode());
        result = prime * result + ((getSub() == null) ? 0 : getSub().hashCode());
        result = prime * result + ((getPhtOrgtype() == null) ? 0 : getPhtOrgtype().hashCode());
        result = prime * result + ((getDirect() == null) ? 0 : getDirect().hashCode());
        result = prime * result + ((getJikongzhongxin() == null) ? 0 : getJikongzhongxin().hashCode());
        result = prime * result + ((getOrgclass() == null) ? 0 : getOrgclass().hashCode());
        result = prime * result + ((getOrglevel() == null) ? 0 : getOrglevel().hashCode());
        result = prime * result + ((getNetReportCond() == null) ? 0 : getNetReportCond().hashCode());
        result = prime * result + ((getOrgSys() == null) ? 0 : getOrgSys().hashCode());
        result = prime * result + ((getOrgClassLevel() == null) ? 0 : getOrgClassLevel().hashCode());
        result = prime * result + ((getOrgnameAll() == null) ? 0 : getOrgnameAll().hashCode());
        return result;
    }
}