package com.sinosoft.mpi.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.sinosoft.mpi.model.register.PersonRegister;

/**
 * 主索引人员注册信息参数
 */
public class MpiPersonInfoRegister implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 人员id
	 */
	private String patientId;
	private String medicalserviceNo;
	private String nameCn;
	private String nameEn;
	private Date birthDate;
	private String genderCd;
	private String cardMaritalStCd;
	private String idNoCd;
	private String idNo;
	private String medicareCd;
	private String medicalInsuranceNo;
	private String cardOccuTypeCd;
	private String cardNationCd;
	private String nationalityCd;
	private String arCd;
	private String adCd;
	private String provinceName;
	private String cityName;
	private String areaName;
	private String streetId;
	private String villageName;
	private String houseNo;
	private String telTypeCd;
	private String personTelNo;
	private String linkmanName;
	private String linkmanRelCd;
	private String lmIdNoCd;
	private String lmIdNo;
	private String hrId;
	private String postCd;
	private String aboCd;
	private String rhCd;
	private String rhNegCd;
	private String cardEdBgCd;
	private String birthPlace;
	private String workingUnitName;
	private String emailAd;
	private Short veteransMilitaryMark;
	private String cardNo;
	private String cardCd;
	private String cardArea;
	private String patientType;
	private String mPhoneNum;
	private String workingUnitPost;
	private String workingUnitAddr;
	private String livingAddr;
	private String rprAddr;
	private String rprPost;
	private String rprPhone;
	private String linkmanAddr;
	private String linkmanPost;
	private String linkmanPhone;
	private String insureType;
	private String medicalTreatmentName;
	private String medicalTreatmentCd;
	private String recorderName;
	private String registerPersonCode;
	private String genderCs;
	private String genderCsn;
	private String genderCsv;
	private String genderDn;
	private String cardMaritalStCs;
	private String cardMaritalStCsn;
	private String cardMaritalStCsv;
	private String cardMaritalStDn;
	private String idNoCs;
	private String idNoCsn;
	private String idNoCsv;
	private String idNoDn;
	private String medicareCs;
	private String medicareCsn;
	private String medicareCsv;
	private String medicareDn;
	private String nhCard;
	private String sscid;
	private String cardOccuTypeCs;
	private String cardOccuTypeCsn;
	private String cardOccuTypeCsv;
	private String cardOccuTypeDn;
	private String cardNationCs;
	private String cardNationCsn;
	private String cardNationCsv;
	private String cardNationDn;
	private String nationalityCs;
	private String nationalityCsn;
	private String nationalityCsv;
	private String nationalityDn;
	private String arCs;
	private String arCsn;
	private String arCsv;
	private String arDn;
	private String adCs;
	private String adCsn;
	private String adCsv;
	private String adDn;
	private String telTypeCs;
	private String telTypeCsn;
	private String telTypeCsv;
	private String telTypeDn;
	private String linkmanRelCs;
	private String linkmanRelCsn;
	private String linkmanRelCsv;
	private String linkmanRelDn;
	private String lmIdNoCs;
	private String lmIdNoCsn;
	private String lmIdNoCsv;
	private String lmIdNoDn;
	private String aboCs;
	private String aboCsn;
	private String aboCsv;
	private String aboDn;
	private String rhCs;
	private String rhCsn;
	private String rhCsv;
	private String rhDn;
	private String cardEdBgCs;
	private String cardEdBgCsn;
	private String cardEdBgCsv;
	private String cardEdBgDn;
	private String birthPlaceCs;
	private String birthPlaceCsn;
	private String birthPlaceCsv;
	private String birthPlaceDn;
	private String nativeProvince;
	private String nativeProvinceCs;
	private String nativeProvinceCsn;
	private String nativeProvinceCsv;
	private String nativeProvinceDn;
	private String nativeCityCs;
	private String nativeCityCsn;
	private String nativeCityCsv;
	private String nativeCityDn;
	private String veteransMilitaryValue;
	private String veteransMilitaryMarkCs;
	private String veteransMilitaryMarkCsn;
	private String veteransMilitaryMarkCsv;
	private String cardCs;
	private String cardCsn;
	private String cardCsv;
	private String cardDn;
	private String cardAreaCs;
	private String cardAreaCsn;
	private String cardAreaCsv;
	private String cardAreaDn;
	private String patientTypeValue;
	private String patientTypeCs;
	private String patientTypeCsn;
	private String patientTypeCsv;
	private String patientTypeDescr;
	private String workingTelNo;
	private String medicalTreatmentCs;
	private String medicalTreatmentCsn;
	private String medicalTreatmentCsv;
	private String versionNum;
	private String responsDocCode;
	private String responsDocName;
	private String otherOcccDescr;
	private Short usualTypeMark;
	private String nativeCity;
	private String drugAllergyMark;
	private String opHistoryMark;
	private String traumaHistoryMark;
	private String bloodTransfMark;
	private String disabilityMark;
	private String geneticDiseaseHistory;
	private String exhaustFacilityMark;
	private String exhaustFacilityTypeCode;
	private String fuelTypeCode;
	private String waterTypeCode;
	private String toiletTypeCode;
	private String livestockPenTypeCode;
	private String operationHistory;
	private String asthmaMark;
	private String hedrtDisMark;
	private String cardiovascularCode;
	private String epilepsyMark;
	private String coagulopathyMark;
	private String diabetesMark;
	private String glaucomaMark;
	private String dialysisMark;
	private String organTransMark;
	private String organDefectMark;
	private String removaProMark;
	private String cardiacPacMark;
	private String ortherMedicalAlert;
	private String psychiatricMark;

	public String getMedicalserviceNo() {
		return medicalserviceNo;
	}

	public void setMedicalserviceNo(String medicalserviceNo) {
		this.medicalserviceNo = medicalserviceNo;
	}

	public String getNameCn() {
		return nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
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
		this.genderCd = genderCd;
	}

	public String getCardMaritalStCd() {
		return cardMaritalStCd;
	}

	public void setCardMaritalStCd(String cardMaritalStCd) {
		this.cardMaritalStCd = cardMaritalStCd;
	}

	public String getIdNoCd() {
		return idNoCd;
	}

	public void setIdNoCd(String idNoCd) {
		this.idNoCd = idNoCd;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getMedicareCd() {
		return medicareCd;
	}

	public void setMedicareCd(String medicareCd) {
		this.medicareCd = medicareCd;
	}

	public String getMedicalInsuranceNo() {
		return medicalInsuranceNo;
	}

	public void setMedicalInsuranceNo(String medicalInsuranceNo) {
		this.medicalInsuranceNo = medicalInsuranceNo;
	}

	public String getCardOccuTypeCd() {
		return cardOccuTypeCd;
	}

	public void setCardOccuTypeCd(String cardOccuTypeCd) {
		this.cardOccuTypeCd = cardOccuTypeCd;
	}

	public String getCardNationCd() {
		return cardNationCd;
	}

	public void setCardNationCd(String cardNationCd) {
		this.cardNationCd = cardNationCd;
	}

	public String getNationalityCd() {
		return nationalityCd;
	}

	public void setNationalityCd(String nationalityCd) {
		this.nationalityCd = nationalityCd;
	}

	public String getArCd() {
		return arCd;
	}

	public void setArCd(String arCd) {
		this.arCd = arCd;
	}

	public String getAdCd() {
		return adCd;
	}

	public void setAdCd(String adCd) {
		this.adCd = adCd;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getStreetId() {
		return streetId;
	}

	public void setStreetId(String streetId) {
		this.streetId = streetId;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getTelTypeCd() {
		return telTypeCd;
	}

	public void setTelTypeCd(String telTypeCd) {
		this.telTypeCd = telTypeCd;
	}

	public String getPersonTelNo() {
		return personTelNo;
	}

	public void setPersonTelNo(String personTelNo) {
		this.personTelNo = personTelNo;
	}

	public String getLinkmanName() {
		return linkmanName;
	}

	public void setLinkmanName(String linkmanName) {
		this.linkmanName = linkmanName;
	}

	public String getLinkmanRelCd() {
		return linkmanRelCd;
	}

	public void setLinkmanRelCd(String linkmanRelCd) {
		this.linkmanRelCd = linkmanRelCd;
	}

	public String getLmIdNoCd() {
		return lmIdNoCd;
	}

	public void setLmIdNoCd(String lmIdNoCd) {
		this.lmIdNoCd = lmIdNoCd;
	}

	public String getLmIdNo() {
		return lmIdNo;
	}

	public void setLmIdNo(String lmIdNo) {
		this.lmIdNo = lmIdNo;
	}

	public String getHrId() {
		return hrId;
	}

	public void setHrId(String hrId) {
		this.hrId = hrId;
	}

	public String getPostCd() {
		return postCd;
	}

	public void setPostCd(String postCd) {
		this.postCd = postCd;
	}

	public String getAboCd() {
		return aboCd;
	}

	public void setAboCd(String aboCd) {
		this.aboCd = aboCd;
	}

	public String getRhCd() {
		return rhCd;
	}

	public void setRhCd(String rhCd) {
		this.rhCd = rhCd;
	}

	public String getRhNegCd() {
		return rhNegCd;
	}

	public void setRhNegCd(String rhNegCd) {
		this.rhNegCd = rhNegCd;
	}

	public String getCardEdBgCd() {
		return cardEdBgCd;
	}

	public void setCardEdBgCd(String cardEdBgCd) {
		this.cardEdBgCd = cardEdBgCd;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getWorkingUnitName() {
		return workingUnitName;
	}

	public void setWorkingUnitName(String workingUnitName) {
		this.workingUnitName = workingUnitName;
	}

	public String getEmailAd() {
		return emailAd;
	}

	public void setEmailAd(String emailAd) {
		this.emailAd = emailAd;
	}

	public Short getVeteransMilitaryMark() {
		return veteransMilitaryMark;
	}

	public void setVeteransMilitaryMark(Short veteransMilitaryMark) {
		this.veteransMilitaryMark = veteransMilitaryMark;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardCd() {
		return cardCd;
	}

	public void setCardCd(String cardCd) {
		this.cardCd = cardCd;
	}

	public String getCardArea() {
		return cardArea;
	}

	public void setCardArea(String cardArea) {
		this.cardArea = cardArea;
	}

	public String getPatientType() {
		return patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	public String getmPhoneNum() {
		return mPhoneNum;
	}

	public void setmPhoneNum(String mPhoneNum) {
		this.mPhoneNum = mPhoneNum;
	}

	public String getWorkingUnitPost() {
		return workingUnitPost;
	}

	public void setWorkingUnitPost(String workingUnitPost) {
		this.workingUnitPost = workingUnitPost;
	}

	public String getWorkingUnitAddr() {
		return workingUnitAddr;
	}

	public void setWorkingUnitAddr(String workingUnitAddr) {
		this.workingUnitAddr = workingUnitAddr;
	}

	public String getLivingAddr() {
		return livingAddr;
	}

	public void setLivingAddr(String livingAddr) {
		this.livingAddr = livingAddr;
	}

	public String getRprAddr() {
		return rprAddr;
	}

	public void setRprAddr(String rprAddr) {
		this.rprAddr = rprAddr;
	}

	public String getRprPost() {
		return rprPost;
	}

	public void setRprPost(String rprPost) {
		this.rprPost = rprPost;
	}

	public String getRprPhone() {
		return rprPhone;
	}

	public void setRprPhone(String rprPhone) {
		this.rprPhone = rprPhone;
	}

	public String getLinkmanAddr() {
		return linkmanAddr;
	}

	public void setLinkmanAddr(String linkmanAddr) {
		this.linkmanAddr = linkmanAddr;
	}

	public String getLinkmanPost() {
		return linkmanPost;
	}

	public void setLinkmanPost(String linkmanPost) {
		this.linkmanPost = linkmanPost;
	}

	public String getLinkmanPhone() {
		return linkmanPhone;
	}

	public void setLinkmanPhone(String linkmanPhone) {
		this.linkmanPhone = linkmanPhone;
	}

	public String getInsureType() {
		return insureType;
	}

	public void setInsureType(String insureType) {
		this.insureType = insureType;
	}

	public String getMedicalTreatmentName() {
		return medicalTreatmentName;
	}

	public void setMedicalTreatmentName(String medicalTreatmentName) {
		this.medicalTreatmentName = medicalTreatmentName;
	}

	public String getMedicalTreatmentCd() {
		return medicalTreatmentCd;
	}

	public void setMedicalTreatmentCd(String medicalTreatmentCd) {
		this.medicalTreatmentCd = medicalTreatmentCd;
	}

	public String getRecorderName() {
		return recorderName;
	}

	public void setRecorderName(String recorderName) {
		this.recorderName = recorderName;
	}

	public String getRegisterPersonCode() {
		return registerPersonCode;
	}

	public void setRegisterPersonCode(String registerPersonCode) {
		this.registerPersonCode = registerPersonCode;
	}

	public String getGenderCs() {
		return genderCs;
	}

	public void setGenderCs(String genderCs) {
		this.genderCs = genderCs;
	}

	public String getGenderCsn() {
		return genderCsn;
	}

	public void setGenderCsn(String genderCsn) {
		this.genderCsn = genderCsn;
	}

	public String getGenderCsv() {
		return genderCsv;
	}

	public void setGenderCsv(String genderCsv) {
		this.genderCsv = genderCsv;
	}

	public String getGenderDn() {
		return genderDn;
	}

	public void setGenderDn(String genderDn) {
		this.genderDn = genderDn;
	}

	public String getCardMaritalStCs() {
		return cardMaritalStCs;
	}

	public void setCardMaritalStCs(String cardMaritalStCs) {
		this.cardMaritalStCs = cardMaritalStCs;
	}

	public String getCardMaritalStCsn() {
		return cardMaritalStCsn;
	}

	public void setCardMaritalStCsn(String cardMaritalStCsn) {
		this.cardMaritalStCsn = cardMaritalStCsn;
	}

	public String getCardMaritalStCsv() {
		return cardMaritalStCsv;
	}

	public void setCardMaritalStCsv(String cardMaritalStCsv) {
		this.cardMaritalStCsv = cardMaritalStCsv;
	}

	public String getCardMaritalStDn() {
		return cardMaritalStDn;
	}

	public void setCardMaritalStDn(String cardMaritalStDn) {
		this.cardMaritalStDn = cardMaritalStDn;
	}

	public String getIdNoCs() {
		return idNoCs;
	}

	public void setIdNoCs(String idNoCs) {
		this.idNoCs = idNoCs;
	}

	public String getIdNoCsn() {
		return idNoCsn;
	}

	public void setIdNoCsn(String idNoCsn) {
		this.idNoCsn = idNoCsn;
	}

	public String getIdNoCsv() {
		return idNoCsv;
	}

	public void setIdNoCsv(String idNoCsv) {
		this.idNoCsv = idNoCsv;
	}

	public String getIdNoDn() {
		return idNoDn;
	}

	public void setIdNoDn(String idNoDn) {
		this.idNoDn = idNoDn;
	}

	public String getMedicareCs() {
		return medicareCs;
	}

	public void setMedicareCs(String medicareCs) {
		this.medicareCs = medicareCs;
	}

	public String getMedicareCsn() {
		return medicareCsn;
	}

	public void setMedicareCsn(String medicareCsn) {
		this.medicareCsn = medicareCsn;
	}

	public String getMedicareCsv() {
		return medicareCsv;
	}

	public void setMedicareCsv(String medicareCsv) {
		this.medicareCsv = medicareCsv;
	}

	public String getMedicareDn() {
		return medicareDn;
	}

	public void setMedicareDn(String medicareDn) {
		this.medicareDn = medicareDn;
	}

	public String getNhCard() {
		return nhCard;
	}

	public void setNhCard(String nhCard) {
		this.nhCard = nhCard;
	}

	public String getSscid() {
		return sscid;
	}

	public void setSscid(String sscid) {
		this.sscid = sscid;
	}

	public String getCardOccuTypeCs() {
		return cardOccuTypeCs;
	}

	public void setCardOccuTypeCs(String cardOccuTypeCs) {
		this.cardOccuTypeCs = cardOccuTypeCs;
	}

	public String getCardOccuTypeCsn() {
		return cardOccuTypeCsn;
	}

	public void setCardOccuTypeCsn(String cardOccuTypeCsn) {
		this.cardOccuTypeCsn = cardOccuTypeCsn;
	}

	public String getCardOccuTypeCsv() {
		return cardOccuTypeCsv;
	}

	public void setCardOccuTypeCsv(String cardOccuTypeCsv) {
		this.cardOccuTypeCsv = cardOccuTypeCsv;
	}

	public String getCardOccuTypeDn() {
		return cardOccuTypeDn;
	}

	public void setCardOccuTypeDn(String cardOccuTypeDn) {
		this.cardOccuTypeDn = cardOccuTypeDn;
	}

	public String getCardNationCs() {
		return cardNationCs;
	}

	public void setCardNationCs(String cardNationCs) {
		this.cardNationCs = cardNationCs;
	}

	public String getCardNationCsn() {
		return cardNationCsn;
	}

	public void setCardNationCsn(String cardNationCsn) {
		this.cardNationCsn = cardNationCsn;
	}

	public String getCardNationCsv() {
		return cardNationCsv;
	}

	public void setCardNationCsv(String cardNationCsv) {
		this.cardNationCsv = cardNationCsv;
	}

	public String getCardNationDn() {
		return cardNationDn;
	}

	public void setCardNationDn(String cardNationDn) {
		this.cardNationDn = cardNationDn;
	}

	public String getNationalityCs() {
		return nationalityCs;
	}

	public void setNationalityCs(String nationalityCs) {
		this.nationalityCs = nationalityCs;
	}

	public String getNationalityCsn() {
		return nationalityCsn;
	}

	public void setNationalityCsn(String nationalityCsn) {
		this.nationalityCsn = nationalityCsn;
	}

	public String getNationalityCsv() {
		return nationalityCsv;
	}

	public void setNationalityCsv(String nationalityCsv) {
		this.nationalityCsv = nationalityCsv;
	}

	public String getNationalityDn() {
		return nationalityDn;
	}

	public void setNationalityDn(String nationalityDn) {
		this.nationalityDn = nationalityDn;
	}

	public String getArCs() {
		return arCs;
	}

	public void setArCs(String arCs) {
		this.arCs = arCs;
	}

	public String getArCsn() {
		return arCsn;
	}

	public void setArCsn(String arCsn) {
		this.arCsn = arCsn;
	}

	public String getArCsv() {
		return arCsv;
	}

	public void setArCsv(String arCsv) {
		this.arCsv = arCsv;
	}

	public String getArDn() {
		return arDn;
	}

	public void setArDn(String arDn) {
		this.arDn = arDn;
	}

	public String getAdCs() {
		return adCs;
	}

	public void setAdCs(String adCs) {
		this.adCs = adCs;
	}

	public String getAdCsn() {
		return adCsn;
	}

	public void setAdCsn(String adCsn) {
		this.adCsn = adCsn;
	}

	public String getAdCsv() {
		return adCsv;
	}

	public void setAdCsv(String adCsv) {
		this.adCsv = adCsv;
	}

	public String getAdDn() {
		return adDn;
	}

	public void setAdDn(String adDn) {
		this.adDn = adDn;
	}

	public String getTelTypeCs() {
		return telTypeCs;
	}

	public void setTelTypeCs(String telTypeCs) {
		this.telTypeCs = telTypeCs;
	}

	public String getTelTypeCsn() {
		return telTypeCsn;
	}

	public void setTelTypeCsn(String telTypeCsn) {
		this.telTypeCsn = telTypeCsn;
	}

	public String getTelTypeCsv() {
		return telTypeCsv;
	}

	public void setTelTypeCsv(String telTypeCsv) {
		this.telTypeCsv = telTypeCsv;
	}

	public String getTelTypeDn() {
		return telTypeDn;
	}

	public void setTelTypeDn(String telTypeDn) {
		this.telTypeDn = telTypeDn;
	}

	public String getLinkmanRelCs() {
		return linkmanRelCs;
	}

	public void setLinkmanRelCs(String linkmanRelCs) {
		this.linkmanRelCs = linkmanRelCs;
	}

	public String getLinkmanRelCsn() {
		return linkmanRelCsn;
	}

	public void setLinkmanRelCsn(String linkmanRelCsn) {
		this.linkmanRelCsn = linkmanRelCsn;
	}

	public String getLinkmanRelCsv() {
		return linkmanRelCsv;
	}

	public void setLinkmanRelCsv(String linkmanRelCsv) {
		this.linkmanRelCsv = linkmanRelCsv;
	}

	public String getLinkmanRelDn() {
		return linkmanRelDn;
	}

	public void setLinkmanRelDn(String linkmanRelDn) {
		this.linkmanRelDn = linkmanRelDn;
	}

	public String getLmIdNoCs() {
		return lmIdNoCs;
	}

	public void setLmIdNoCs(String lmIdNoCs) {
		this.lmIdNoCs = lmIdNoCs;
	}

	public String getLmIdNoCsn() {
		return lmIdNoCsn;
	}

	public void setLmIdNoCsn(String lmIdNoCsn) {
		this.lmIdNoCsn = lmIdNoCsn;
	}

	public String getLmIdNoCsv() {
		return lmIdNoCsv;
	}

	public void setLmIdNoCsv(String lmIdNoCsv) {
		this.lmIdNoCsv = lmIdNoCsv;
	}

	public String getLmIdNoDn() {
		return lmIdNoDn;
	}

	public void setLmIdNoDn(String lmIdNoDn) {
		this.lmIdNoDn = lmIdNoDn;
	}

	public String getAboCs() {
		return aboCs;
	}

	public void setAboCs(String aboCs) {
		this.aboCs = aboCs;
	}

	public String getAboCsn() {
		return aboCsn;
	}

	public void setAboCsn(String aboCsn) {
		this.aboCsn = aboCsn;
	}

	public String getAboCsv() {
		return aboCsv;
	}

	public void setAboCsv(String aboCsv) {
		this.aboCsv = aboCsv;
	}

	public String getAboDn() {
		return aboDn;
	}

	public void setAboDn(String aboDn) {
		this.aboDn = aboDn;
	}

	public String getRhCs() {
		return rhCs;
	}

	public void setRhCs(String rhCs) {
		this.rhCs = rhCs;
	}

	public String getRhCsn() {
		return rhCsn;
	}

	public void setRhCsn(String rhCsn) {
		this.rhCsn = rhCsn;
	}

	public String getRhCsv() {
		return rhCsv;
	}

	public void setRhCsv(String rhCsv) {
		this.rhCsv = rhCsv;
	}

	public String getRhDn() {
		return rhDn;
	}

	public void setRhDn(String rhDn) {
		this.rhDn = rhDn;
	}

	public String getCardEdBgCs() {
		return cardEdBgCs;
	}

	public void setCardEdBgCs(String cardEdBgCs) {
		this.cardEdBgCs = cardEdBgCs;
	}

	public String getCardEdBgCsn() {
		return cardEdBgCsn;
	}

	public void setCardEdBgCsn(String cardEdBgCsn) {
		this.cardEdBgCsn = cardEdBgCsn;
	}

	public String getCardEdBgCsv() {
		return cardEdBgCsv;
	}

	public void setCardEdBgCsv(String cardEdBgCsv) {
		this.cardEdBgCsv = cardEdBgCsv;
	}

	public String getCardEdBgDn() {
		return cardEdBgDn;
	}

	public void setCardEdBgDn(String cardEdBgDn) {
		this.cardEdBgDn = cardEdBgDn;
	}

	public String getBirthPlaceCs() {
		return birthPlaceCs;
	}

	public void setBirthPlaceCs(String birthPlaceCs) {
		this.birthPlaceCs = birthPlaceCs;
	}

	public String getBirthPlaceCsn() {
		return birthPlaceCsn;
	}

	public void setBirthPlaceCsn(String birthPlaceCsn) {
		this.birthPlaceCsn = birthPlaceCsn;
	}

	public String getBirthPlaceCsv() {
		return birthPlaceCsv;
	}

	public void setBirthPlaceCsv(String birthPlaceCsv) {
		this.birthPlaceCsv = birthPlaceCsv;
	}

	public String getBirthPlaceDn() {
		return birthPlaceDn;
	}

	public void setBirthPlaceDn(String birthPlaceDn) {
		this.birthPlaceDn = birthPlaceDn;
	}

	public String getNativeProvince() {
		return nativeProvince;
	}

	public void setNativeProvince(String nativeProvince) {
		this.nativeProvince = nativeProvince;
	}

	public String getNativeProvinceCs() {
		return nativeProvinceCs;
	}

	public void setNativeProvinceCs(String nativeProvinceCs) {
		this.nativeProvinceCs = nativeProvinceCs;
	}

	public String getNativeProvinceCsn() {
		return nativeProvinceCsn;
	}

	public void setNativeProvinceCsn(String nativeProvinceCsn) {
		this.nativeProvinceCsn = nativeProvinceCsn;
	}

	public String getNativeProvinceCsv() {
		return nativeProvinceCsv;
	}

	public void setNativeProvinceCsv(String nativeProvinceCsv) {
		this.nativeProvinceCsv = nativeProvinceCsv;
	}

	public String getNativeProvinceDn() {
		return nativeProvinceDn;
	}

	public void setNativeProvinceDn(String nativeProvinceDn) {
		this.nativeProvinceDn = nativeProvinceDn;
	}

	public String getNativeCityCs() {
		return nativeCityCs;
	}

	public void setNativeCityCs(String nativeCityCs) {
		this.nativeCityCs = nativeCityCs;
	}

	public String getNativeCityCsn() {
		return nativeCityCsn;
	}

	public void setNativeCityCsn(String nativeCityCsn) {
		this.nativeCityCsn = nativeCityCsn;
	}

	public String getNativeCityCsv() {
		return nativeCityCsv;
	}

	public void setNativeCityCsv(String nativeCityCsv) {
		this.nativeCityCsv = nativeCityCsv;
	}

	public String getNativeCityDn() {
		return nativeCityDn;
	}

	public void setNativeCityDn(String nativeCityDn) {
		this.nativeCityDn = nativeCityDn;
	}

	public String getVeteransMilitaryValue() {
		return veteransMilitaryValue;
	}

	public void setVeteransMilitaryValue(String veteransMilitaryValue) {
		this.veteransMilitaryValue = veteransMilitaryValue;
	}

	public String getVeteransMilitaryMarkCs() {
		return veteransMilitaryMarkCs;
	}

	public void setVeteransMilitaryMarkCs(String veteransMilitaryMarkCs) {
		this.veteransMilitaryMarkCs = veteransMilitaryMarkCs;
	}

	public String getVeteransMilitaryMarkCsn() {
		return veteransMilitaryMarkCsn;
	}

	public void setVeteransMilitaryMarkCsn(String veteransMilitaryMarkCsn) {
		this.veteransMilitaryMarkCsn = veteransMilitaryMarkCsn;
	}

	public String getVeteransMilitaryMarkCsv() {
		return veteransMilitaryMarkCsv;
	}

	public void setVeteransMilitaryMarkCsv(String veteransMilitaryMarkCsv) {
		this.veteransMilitaryMarkCsv = veteransMilitaryMarkCsv;
	}

	public String getCardCs() {
		return cardCs;
	}

	public void setCardCs(String cardCs) {
		this.cardCs = cardCs;
	}

	public String getCardCsn() {
		return cardCsn;
	}

	public void setCardCsn(String cardCsn) {
		this.cardCsn = cardCsn;
	}

	public String getCardCsv() {
		return cardCsv;
	}

	public void setCardCsv(String cardCsv) {
		this.cardCsv = cardCsv;
	}

	public String getCardDn() {
		return cardDn;
	}

	public void setCardDn(String cardDn) {
		this.cardDn = cardDn;
	}

	public String getCardAreaCs() {
		return cardAreaCs;
	}

	public void setCardAreaCs(String cardAreaCs) {
		this.cardAreaCs = cardAreaCs;
	}

	public String getCardAreaCsn() {
		return cardAreaCsn;
	}

	public void setCardAreaCsn(String cardAreaCsn) {
		this.cardAreaCsn = cardAreaCsn;
	}

	public String getCardAreaCsv() {
		return cardAreaCsv;
	}

	public void setCardAreaCsv(String cardAreaCsv) {
		this.cardAreaCsv = cardAreaCsv;
	}

	public String getCardAreaDn() {
		return cardAreaDn;
	}

	public void setCardAreaDn(String cardAreaDn) {
		this.cardAreaDn = cardAreaDn;
	}

	public String getPatientTypeValue() {
		return patientTypeValue;
	}

	public void setPatientTypeValue(String patientTypeValue) {
		this.patientTypeValue = patientTypeValue;
	}

	public String getPatientTypeCs() {
		return patientTypeCs;
	}

	public void setPatientTypeCs(String patientTypeCs) {
		this.patientTypeCs = patientTypeCs;
	}

	public String getPatientTypeCsn() {
		return patientTypeCsn;
	}

	public void setPatientTypeCsn(String patientTypeCsn) {
		this.patientTypeCsn = patientTypeCsn;
	}

	public String getPatientTypeCsv() {
		return patientTypeCsv;
	}

	public void setPatientTypeCsv(String patientTypeCsv) {
		this.patientTypeCsv = patientTypeCsv;
	}

	public String getPatientTypeDescr() {
		return patientTypeDescr;
	}

	public void setPatientTypeDescr(String patientTypeDescr) {
		this.patientTypeDescr = patientTypeDescr;
	}

	public String getWorkingTelNo() {
		return workingTelNo;
	}

	public void setWorkingTelNo(String workingTelNo) {
		this.workingTelNo = workingTelNo;
	}

	public String getMedicalTreatmentCs() {
		return medicalTreatmentCs;
	}

	public void setMedicalTreatmentCs(String medicalTreatmentCs) {
		this.medicalTreatmentCs = medicalTreatmentCs;
	}

	public String getMedicalTreatmentCsn() {
		return medicalTreatmentCsn;
	}

	public void setMedicalTreatmentCsn(String medicalTreatmentCsn) {
		this.medicalTreatmentCsn = medicalTreatmentCsn;
	}

	public String getMedicalTreatmentCsv() {
		return medicalTreatmentCsv;
	}

	public void setMedicalTreatmentCsv(String medicalTreatmentCsv) {
		this.medicalTreatmentCsv = medicalTreatmentCsv;
	}

	public String getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}

	public String getResponsDocCode() {
		return responsDocCode;
	}

	public void setResponsDocCode(String responsDocCode) {
		this.responsDocCode = responsDocCode;
	}

	public String getResponsDocName() {
		return responsDocName;
	}

	public void setResponsDocName(String responsDocName) {
		this.responsDocName = responsDocName;
	}

	public String getOtherOcccDescr() {
		return otherOcccDescr;
	}

	public void setOtherOcccDescr(String otherOcccDescr) {
		this.otherOcccDescr = otherOcccDescr;
	}

	public Short getUsualTypeMark() {
		return usualTypeMark;
	}

	public void setUsualTypeMark(Short usualTypeMark) {
		this.usualTypeMark = usualTypeMark;
	}

	public String getNativeCity() {
		return nativeCity;
	}

	public void setNativeCity(String nativeCity) {
		this.nativeCity = nativeCity;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getDrugAllergyMark() {
		return drugAllergyMark;
	}

	public void setDrugAllergyMark(String drugAllergyMark) {
		this.drugAllergyMark = drugAllergyMark;
	}

	public String getOpHistoryMark() {
		return opHistoryMark;
	}

	public void setOpHistoryMark(String opHistoryMark) {
		this.opHistoryMark = opHistoryMark;
	}

	public String getTraumaHistoryMark() {
		return traumaHistoryMark;
	}

	public void setTraumaHistoryMark(String traumaHistoryMark) {
		this.traumaHistoryMark = traumaHistoryMark;
	}

	public String getBloodTransfMark() {
		return bloodTransfMark;
	}

	public void setBloodTransfMark(String bloodTransfMark) {
		this.bloodTransfMark = bloodTransfMark;
	}

	public String getDisabilityMark() {
		return disabilityMark;
	}

	public void setDisabilityMark(String disabilityMark) {
		this.disabilityMark = disabilityMark;
	}

	public String getGeneticDiseaseHistory() {
		return geneticDiseaseHistory;
	}

	public void setGeneticDiseaseHistory(String geneticDiseaseHistory) {
		this.geneticDiseaseHistory = geneticDiseaseHistory;
	}

	public String getExhaustFacilityMark() {
		return exhaustFacilityMark;
	}

	public void setExhaustFacilityMark(String exhaustFacilityMark) {
		this.exhaustFacilityMark = exhaustFacilityMark;
	}

	public String getExhaustFacilityTypeCode() {
		return exhaustFacilityTypeCode;
	}

	public void setExhaustFacilityTypeCode(String exhaustFacilityTypeCode) {
		this.exhaustFacilityTypeCode = exhaustFacilityTypeCode;
	}

	public String getFuelTypeCode() {
		return fuelTypeCode;
	}

	public void setFuelTypeCode(String fuelTypeCode) {
		this.fuelTypeCode = fuelTypeCode;
	}

	public String getWaterTypeCode() {
		return waterTypeCode;
	}

	public void setWaterTypeCode(String waterTypeCode) {
		this.waterTypeCode = waterTypeCode;
	}

	public String getToiletTypeCode() {
		return toiletTypeCode;
	}

	public void setToiletTypeCode(String toiletTypeCode) {
		this.toiletTypeCode = toiletTypeCode;
	}

	public String getLivestockPenTypeCode() {
		return livestockPenTypeCode;
	}

	public void setLivestockPenTypeCode(String livestockPenTypeCode) {
		this.livestockPenTypeCode = livestockPenTypeCode;
	}

	public String getOperationHistory() {
		return operationHistory;
	}

	public void setOperationHistory(String operationHistory) {
		this.operationHistory = operationHistory;
	}

	public String getAsthmaMark() {
		return asthmaMark;
	}

	public void setAsthmaMark(String asthmaMark) {
		this.asthmaMark = asthmaMark;
	}

	public String getHedrtDisMark() {
		return hedrtDisMark;
	}

	public void setHedrtDisMark(String hedrtDisMark) {
		this.hedrtDisMark = hedrtDisMark;
	}

	public String getCardiovascularCode() {
		return cardiovascularCode;
	}

	public void setCardiovascularCode(String cardiovascularCode) {
		this.cardiovascularCode = cardiovascularCode;
	}

	public String getEpilepsyMark() {
		return epilepsyMark;
	}

	public void setEpilepsyMark(String epilepsyMark) {
		this.epilepsyMark = epilepsyMark;
	}

	public String getCoagulopathyMark() {
		return coagulopathyMark;
	}

	public void setCoagulopathyMark(String coagulopathyMark) {
		this.coagulopathyMark = coagulopathyMark;
	}

	public String getDiabetesMark() {
		return diabetesMark;
	}

	public void setDiabetesMark(String diabetesMark) {
		this.diabetesMark = diabetesMark;
	}

	public String getGlaucomaMark() {
		return glaucomaMark;
	}

	public void setGlaucomaMark(String glaucomaMark) {
		this.glaucomaMark = glaucomaMark;
	}

	public String getDialysisMark() {
		return dialysisMark;
	}

	public void setDialysisMark(String dialysisMark) {
		this.dialysisMark = dialysisMark;
	}

	public String getOrganTransMark() {
		return organTransMark;
	}

	public void setOrganTransMark(String organTransMark) {
		this.organTransMark = organTransMark;
	}

	public String getOrganDefectMark() {
		return organDefectMark;
	}

	public void setOrganDefectMark(String organDefectMark) {
		this.organDefectMark = organDefectMark;
	}

	public String getRemovaProMark() {
		return removaProMark;
	}

	public void setRemovaProMark(String removaProMark) {
		this.removaProMark = removaProMark;
	}

	public String getCardiacPacMark() {
		return cardiacPacMark;
	}

	public void setCardiacPacMark(String cardiacPacMark) {
		this.cardiacPacMark = cardiacPacMark;
	}

	public String getOrtherMedicalAlert() {
		return ortherMedicalAlert;
	}

	public void setOrtherMedicalAlert(String ortherMedicalAlert) {
		this.ortherMedicalAlert = ortherMedicalAlert;
	}

	public String getPsychiatricMark() {
		return psychiatricMark;
	}

	public void setPsychiatricMark(String psychiatricMark) {
		this.psychiatricMark = psychiatricMark;
	}

	/**
	 * 将注册的人员信息转换为实际的人员信息并设置相关值
	 * 
	 * @param personRegister
	 *            注册的人员信息
	 * @return 转换后的人员信息
	 */
	public PersonInfo toPersonInfo(PersonRegister personRegister) {
		PersonInfo info = new PersonInfo();
		String S_DEFAULT = "default";
		BeanUtils.copyProperties(this, info);
		// 默认参数
		info.setRegisterDate(new Date());
		info.setRegisterOrgCode(S_DEFAULT);
		info.setRegisterOrgName(S_DEFAULT);

		info.setArCd(S_DEFAULT);

		info.setSendOrgCode(S_DEFAULT);
		info.setSendSystem(S_DEFAULT);
		info.setSendTime(new Date());

		info.setProviderName(S_DEFAULT);
		info.setProviderOrgCode(S_DEFAULT);

		info.setCreatetime(new Date());

		Short state = personRegister.getType();
		info.setState(state == null ? Short.valueOf("0") : state);

		String domainId = personRegister.getSystemKey();
		info.setUniqueSign(domainId);
		info.setDomainId(domainId);

		return info;
	}

}
