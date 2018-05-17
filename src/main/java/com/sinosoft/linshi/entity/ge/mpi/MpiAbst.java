package com.sinosoft.linshi.entity.ge.mpi;

import java.io.Serializable;
import java.util.Date;

public class MpiAbst implements Serializable {
    private String fieldPk;

    private String name;

    private Date birthDate;

    private String genderCd;

    private String cardMaritalStCd;

    private String idNoCd;

    private String idNo;

    private String searchCondition;

    private String mpiPk;

    private String medicalInsuranceNo;

    private String cardOccuTypeCd;

    private String medicareCd;

    private String cardNationCd;

    private String nationalityCd;

    private String hrId;

    private String telTypeCd;

    private String personTelNo;

    private String abst1;

    private String abst2;

    private String abst3;

    private String abst4;

    private String abst5;

    private static final long serialVersionUID = 1L;

    public String getFieldPk() {
        return fieldPk;
    }

    public void setFieldPk(String fieldPk) {
        this.fieldPk = fieldPk == null ? null : fieldPk.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGenderCd() {
        return genderCd;
    }

    public void setGenderCd(String genderCd) {
        this.genderCd = genderCd == null ? null : genderCd.trim();
    }

    public String getCardMaritalStCd() {
        return cardMaritalStCd;
    }

    public void setCardMaritalStCd(String cardMaritalStCd) {
        this.cardMaritalStCd = cardMaritalStCd == null ? null : cardMaritalStCd.trim();
    }

    public String getIdNoCd() {
        return idNoCd;
    }

    public void setIdNoCd(String idNoCd) {
        this.idNoCd = idNoCd == null ? null : idNoCd.trim();
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }

    public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition == null ? null : searchCondition.trim();
    }

    public String getMpiPk() {
        return mpiPk;
    }

    public void setMpiPk(String mpiPk) {
        this.mpiPk = mpiPk == null ? null : mpiPk.trim();
    }

    public String getMedicalInsuranceNo() {
        return medicalInsuranceNo;
    }

    public void setMedicalInsuranceNo(String medicalInsuranceNo) {
        this.medicalInsuranceNo = medicalInsuranceNo == null ? null : medicalInsuranceNo.trim();
    }

    public String getCardOccuTypeCd() {
        return cardOccuTypeCd;
    }

    public void setCardOccuTypeCd(String cardOccuTypeCd) {
        this.cardOccuTypeCd = cardOccuTypeCd == null ? null : cardOccuTypeCd.trim();
    }

    public String getMedicareCd() {
        return medicareCd;
    }

    public void setMedicareCd(String medicareCd) {
        this.medicareCd = medicareCd == null ? null : medicareCd.trim();
    }

    public String getCardNationCd() {
        return cardNationCd;
    }

    public void setCardNationCd(String cardNationCd) {
        this.cardNationCd = cardNationCd == null ? null : cardNationCd.trim();
    }

    public String getNationalityCd() {
        return nationalityCd;
    }

    public void setNationalityCd(String nationalityCd) {
        this.nationalityCd = nationalityCd == null ? null : nationalityCd.trim();
    }

    public String getHrId() {
        return hrId;
    }

    public void setHrId(String hrId) {
        this.hrId = hrId == null ? null : hrId.trim();
    }

    public String getTelTypeCd() {
        return telTypeCd;
    }

    public void setTelTypeCd(String telTypeCd) {
        this.telTypeCd = telTypeCd == null ? null : telTypeCd.trim();
    }

    public String getPersonTelNo() {
        return personTelNo;
    }

    public void setPersonTelNo(String personTelNo) {
        this.personTelNo = personTelNo == null ? null : personTelNo.trim();
    }

    public String getAbst1() {
        return abst1;
    }

    public void setAbst1(String abst1) {
        this.abst1 = abst1 == null ? null : abst1.trim();
    }

    public String getAbst2() {
        return abst2;
    }

    public void setAbst2(String abst2) {
        this.abst2 = abst2 == null ? null : abst2.trim();
    }

    public String getAbst3() {
        return abst3;
    }

    public void setAbst3(String abst3) {
        this.abst3 = abst3 == null ? null : abst3.trim();
    }

    public String getAbst4() {
        return abst4;
    }

    public void setAbst4(String abst4) {
        this.abst4 = abst4 == null ? null : abst4.trim();
    }

    public String getAbst5() {
        return abst5;
    }

    public void setAbst5(String abst5) {
        this.abst5 = abst5 == null ? null : abst5.trim();
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
        MpiAbst other = (MpiAbst) that;
        return (this.getFieldPk() == null ? other.getFieldPk() == null : this.getFieldPk().equals(other.getFieldPk()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getBirthDate() == null ? other.getBirthDate() == null : this.getBirthDate().equals(other.getBirthDate()))
            && (this.getGenderCd() == null ? other.getGenderCd() == null : this.getGenderCd().equals(other.getGenderCd()))
            && (this.getCardMaritalStCd() == null ? other.getCardMaritalStCd() == null : this.getCardMaritalStCd().equals(other.getCardMaritalStCd()))
            && (this.getIdNoCd() == null ? other.getIdNoCd() == null : this.getIdNoCd().equals(other.getIdNoCd()))
            && (this.getIdNo() == null ? other.getIdNo() == null : this.getIdNo().equals(other.getIdNo()))
            && (this.getSearchCondition() == null ? other.getSearchCondition() == null : this.getSearchCondition().equals(other.getSearchCondition()))
            && (this.getMpiPk() == null ? other.getMpiPk() == null : this.getMpiPk().equals(other.getMpiPk()))
            && (this.getMedicalInsuranceNo() == null ? other.getMedicalInsuranceNo() == null : this.getMedicalInsuranceNo().equals(other.getMedicalInsuranceNo()))
            && (this.getCardOccuTypeCd() == null ? other.getCardOccuTypeCd() == null : this.getCardOccuTypeCd().equals(other.getCardOccuTypeCd()))
            && (this.getMedicareCd() == null ? other.getMedicareCd() == null : this.getMedicareCd().equals(other.getMedicareCd()))
            && (this.getCardNationCd() == null ? other.getCardNationCd() == null : this.getCardNationCd().equals(other.getCardNationCd()))
            && (this.getNationalityCd() == null ? other.getNationalityCd() == null : this.getNationalityCd().equals(other.getNationalityCd()))
            && (this.getHrId() == null ? other.getHrId() == null : this.getHrId().equals(other.getHrId()))
            && (this.getTelTypeCd() == null ? other.getTelTypeCd() == null : this.getTelTypeCd().equals(other.getTelTypeCd()))
            && (this.getPersonTelNo() == null ? other.getPersonTelNo() == null : this.getPersonTelNo().equals(other.getPersonTelNo()))
            && (this.getAbst1() == null ? other.getAbst1() == null : this.getAbst1().equals(other.getAbst1()))
            && (this.getAbst2() == null ? other.getAbst2() == null : this.getAbst2().equals(other.getAbst2()))
            && (this.getAbst3() == null ? other.getAbst3() == null : this.getAbst3().equals(other.getAbst3()))
            && (this.getAbst4() == null ? other.getAbst4() == null : this.getAbst4().equals(other.getAbst4()))
            && (this.getAbst5() == null ? other.getAbst5() == null : this.getAbst5().equals(other.getAbst5()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFieldPk() == null) ? 0 : getFieldPk().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getBirthDate() == null) ? 0 : getBirthDate().hashCode());
        result = prime * result + ((getGenderCd() == null) ? 0 : getGenderCd().hashCode());
        result = prime * result + ((getCardMaritalStCd() == null) ? 0 : getCardMaritalStCd().hashCode());
        result = prime * result + ((getIdNoCd() == null) ? 0 : getIdNoCd().hashCode());
        result = prime * result + ((getIdNo() == null) ? 0 : getIdNo().hashCode());
        result = prime * result + ((getSearchCondition() == null) ? 0 : getSearchCondition().hashCode());
        result = prime * result + ((getMpiPk() == null) ? 0 : getMpiPk().hashCode());
        result = prime * result + ((getMedicalInsuranceNo() == null) ? 0 : getMedicalInsuranceNo().hashCode());
        result = prime * result + ((getCardOccuTypeCd() == null) ? 0 : getCardOccuTypeCd().hashCode());
        result = prime * result + ((getMedicareCd() == null) ? 0 : getMedicareCd().hashCode());
        result = prime * result + ((getCardNationCd() == null) ? 0 : getCardNationCd().hashCode());
        result = prime * result + ((getNationalityCd() == null) ? 0 : getNationalityCd().hashCode());
        result = prime * result + ((getHrId() == null) ? 0 : getHrId().hashCode());
        result = prime * result + ((getTelTypeCd() == null) ? 0 : getTelTypeCd().hashCode());
        result = prime * result + ((getPersonTelNo() == null) ? 0 : getPersonTelNo().hashCode());
        result = prime * result + ((getAbst1() == null) ? 0 : getAbst1().hashCode());
        result = prime * result + ((getAbst2() == null) ? 0 : getAbst2().hashCode());
        result = prime * result + ((getAbst3() == null) ? 0 : getAbst3().hashCode());
        result = prime * result + ((getAbst4() == null) ? 0 : getAbst4().hashCode());
        result = prime * result + ((getAbst5() == null) ? 0 : getAbst5().hashCode());
        return result;
    }
}