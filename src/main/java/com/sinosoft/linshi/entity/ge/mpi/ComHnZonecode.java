package com.sinosoft.linshi.entity.ge.mpi;

import java.io.Serializable;
import java.util.Date;

public class ComHnZonecode implements Serializable {
    private Short years;

    private String zonecode;

    private String cnname;

    private String enname;

    private Short zonelevel;

    private Short zonetype;

    private Short povertystatus;

    private Short locationtype;

    private String othercode;

    private String notes;

    private Date bdate;

    private Date edate;

    private String gbzonecode;

    private String customise;

    private String zonename;

    private String border;

    private String minority;

    private String parentzonecode;

    private Long population;

    private static final long serialVersionUID = 1L;

    public Short getYears() {
        return years;
    }

    public void setYears(Short years) {
        this.years = years;
    }

    public String getZonecode() {
        return zonecode;
    }

    public void setZonecode(String zonecode) {
        this.zonecode = zonecode == null ? null : zonecode.trim();
    }

    public String getCnname() {
        return cnname;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname == null ? null : cnname.trim();
    }

    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname == null ? null : enname.trim();
    }

    public Short getZonelevel() {
        return zonelevel;
    }

    public void setZonelevel(Short zonelevel) {
        this.zonelevel = zonelevel;
    }

    public Short getZonetype() {
        return zonetype;
    }

    public void setZonetype(Short zonetype) {
        this.zonetype = zonetype;
    }

    public Short getPovertystatus() {
        return povertystatus;
    }

    public void setPovertystatus(Short povertystatus) {
        this.povertystatus = povertystatus;
    }

    public Short getLocationtype() {
        return locationtype;
    }

    public void setLocationtype(Short locationtype) {
        this.locationtype = locationtype;
    }

    public String getOthercode() {
        return othercode;
    }

    public void setOthercode(String othercode) {
        this.othercode = othercode == null ? null : othercode.trim();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    public Date getBdate() {
        return bdate;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }

    public String getGbzonecode() {
        return gbzonecode;
    }

    public void setGbzonecode(String gbzonecode) {
        this.gbzonecode = gbzonecode == null ? null : gbzonecode.trim();
    }

    public String getCustomise() {
        return customise;
    }

    public void setCustomise(String customise) {
        this.customise = customise == null ? null : customise.trim();
    }

    public String getZonename() {
        return zonename;
    }

    public void setZonename(String zonename) {
        this.zonename = zonename == null ? null : zonename.trim();
    }

    public String getBorder() {
        return border;
    }

    public void setBorder(String border) {
        this.border = border == null ? null : border.trim();
    }

    public String getMinority() {
        return minority;
    }

    public void setMinority(String minority) {
        this.minority = minority == null ? null : minority.trim();
    }

    public String getParentzonecode() {
        return parentzonecode;
    }

    public void setParentzonecode(String parentzonecode) {
        this.parentzonecode = parentzonecode == null ? null : parentzonecode.trim();
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
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
        ComHnZonecode other = (ComHnZonecode) that;
        return (this.getYears() == null ? other.getYears() == null : this.getYears().equals(other.getYears()))
            && (this.getZonecode() == null ? other.getZonecode() == null : this.getZonecode().equals(other.getZonecode()))
            && (this.getCnname() == null ? other.getCnname() == null : this.getCnname().equals(other.getCnname()))
            && (this.getEnname() == null ? other.getEnname() == null : this.getEnname().equals(other.getEnname()))
            && (this.getZonelevel() == null ? other.getZonelevel() == null : this.getZonelevel().equals(other.getZonelevel()))
            && (this.getZonetype() == null ? other.getZonetype() == null : this.getZonetype().equals(other.getZonetype()))
            && (this.getPovertystatus() == null ? other.getPovertystatus() == null : this.getPovertystatus().equals(other.getPovertystatus()))
            && (this.getLocationtype() == null ? other.getLocationtype() == null : this.getLocationtype().equals(other.getLocationtype()))
            && (this.getOthercode() == null ? other.getOthercode() == null : this.getOthercode().equals(other.getOthercode()))
            && (this.getNotes() == null ? other.getNotes() == null : this.getNotes().equals(other.getNotes()))
            && (this.getBdate() == null ? other.getBdate() == null : this.getBdate().equals(other.getBdate()))
            && (this.getEdate() == null ? other.getEdate() == null : this.getEdate().equals(other.getEdate()))
            && (this.getGbzonecode() == null ? other.getGbzonecode() == null : this.getGbzonecode().equals(other.getGbzonecode()))
            && (this.getCustomise() == null ? other.getCustomise() == null : this.getCustomise().equals(other.getCustomise()))
            && (this.getZonename() == null ? other.getZonename() == null : this.getZonename().equals(other.getZonename()))
            && (this.getBorder() == null ? other.getBorder() == null : this.getBorder().equals(other.getBorder()))
            && (this.getMinority() == null ? other.getMinority() == null : this.getMinority().equals(other.getMinority()))
            && (this.getParentzonecode() == null ? other.getParentzonecode() == null : this.getParentzonecode().equals(other.getParentzonecode()))
            && (this.getPopulation() == null ? other.getPopulation() == null : this.getPopulation().equals(other.getPopulation()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getYears() == null) ? 0 : getYears().hashCode());
        result = prime * result + ((getZonecode() == null) ? 0 : getZonecode().hashCode());
        result = prime * result + ((getCnname() == null) ? 0 : getCnname().hashCode());
        result = prime * result + ((getEnname() == null) ? 0 : getEnname().hashCode());
        result = prime * result + ((getZonelevel() == null) ? 0 : getZonelevel().hashCode());
        result = prime * result + ((getZonetype() == null) ? 0 : getZonetype().hashCode());
        result = prime * result + ((getPovertystatus() == null) ? 0 : getPovertystatus().hashCode());
        result = prime * result + ((getLocationtype() == null) ? 0 : getLocationtype().hashCode());
        result = prime * result + ((getOthercode() == null) ? 0 : getOthercode().hashCode());
        result = prime * result + ((getNotes() == null) ? 0 : getNotes().hashCode());
        result = prime * result + ((getBdate() == null) ? 0 : getBdate().hashCode());
        result = prime * result + ((getEdate() == null) ? 0 : getEdate().hashCode());
        result = prime * result + ((getGbzonecode() == null) ? 0 : getGbzonecode().hashCode());
        result = prime * result + ((getCustomise() == null) ? 0 : getCustomise().hashCode());
        result = prime * result + ((getZonename() == null) ? 0 : getZonename().hashCode());
        result = prime * result + ((getBorder() == null) ? 0 : getBorder().hashCode());
        result = prime * result + ((getMinority() == null) ? 0 : getMinority().hashCode());
        result = prime * result + ((getParentzonecode() == null) ? 0 : getParentzonecode().hashCode());
        result = prime * result + ((getPopulation() == null) ? 0 : getPopulation().hashCode());
        return result;
    }
}