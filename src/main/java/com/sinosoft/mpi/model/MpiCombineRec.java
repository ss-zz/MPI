package com.sinosoft.mpi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.beans.BeanUtils;

/**
 * 合并记录表
 */
@Entity(name = "MPI_COMBINE_REC")
public class MpiCombineRec implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long combineNo;

	private String registerOrgCode;

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

	private String veteransMilitaryMark;

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

	private Date registerDate;

	private String recorderName;

	private String registerPersonCode;

	private Date sendTime;

	private String sendOrgCode;

	private String sendSystem;

	private String providerName;

	private String providerOrgCode;

	private Date createtime;

	private Date lastuptime;

	private Short state;

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

	private String nativeCity;

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

	private Date updatetime;

	private String srcLevel;

	private String registerOrgName;

	private String remark;

	private String responsDocCode;

	private String responsDocName;

	private String otherOcccDescr;

	private Short usualTypeMark;

	private String patientId;

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

	public Long getCombineNo() {
		return combineNo;
	}

	public void setCombineNo(Long combineNo) {
		this.combineNo = combineNo;
	}

	public String getRegisterOrgCode() {
		return registerOrgCode;
	}

	public void setRegisterOrgCode(String registerOrgCode) {
		this.registerOrgCode = registerOrgCode == null ? null : registerOrgCode.trim();
	}

	public String getMedicalserviceNo() {
		return medicalserviceNo;
	}

	public void setMedicalserviceNo(String medicalserviceNo) {
		this.medicalserviceNo = medicalserviceNo == null ? null : medicalserviceNo.trim();
	}

	public String getNameCn() {
		return nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn == null ? null : nameCn.trim();
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn == null ? null : nameEn.trim();
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

	public String getMedicareCd() {
		return medicareCd;
	}

	public void setMedicareCd(String medicareCd) {
		this.medicareCd = medicareCd == null ? null : medicareCd.trim();
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

	public String getArCd() {
		return arCd;
	}

	public void setArCd(String arCd) {
		this.arCd = arCd == null ? null : arCd.trim();
	}

	public String getAdCd() {
		return adCd;
	}

	public void setAdCd(String adCd) {
		this.adCd = adCd == null ? null : adCd.trim();
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName == null ? null : provinceName.trim();
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName == null ? null : cityName.trim();
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName == null ? null : areaName.trim();
	}

	public String getStreetId() {
		return streetId;
	}

	public void setStreetId(String streetId) {
		this.streetId = streetId == null ? null : streetId.trim();
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName == null ? null : villageName.trim();
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo == null ? null : houseNo.trim();
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

	public String getLinkmanName() {
		return linkmanName;
	}

	public void setLinkmanName(String linkmanName) {
		this.linkmanName = linkmanName == null ? null : linkmanName.trim();
	}

	public String getLinkmanRelCd() {
		return linkmanRelCd;
	}

	public void setLinkmanRelCd(String linkmanRelCd) {
		this.linkmanRelCd = linkmanRelCd == null ? null : linkmanRelCd.trim();
	}

	public String getLmIdNoCd() {
		return lmIdNoCd;
	}

	public void setLmIdNoCd(String lmIdNoCd) {
		this.lmIdNoCd = lmIdNoCd == null ? null : lmIdNoCd.trim();
	}

	public String getLmIdNo() {
		return lmIdNo;
	}

	public void setLmIdNo(String lmIdNo) {
		this.lmIdNo = lmIdNo == null ? null : lmIdNo.trim();
	}

	public String getHrId() {
		return hrId;
	}

	public void setHrId(String hrId) {
		this.hrId = hrId == null ? null : hrId.trim();
	}

	public String getPostCd() {
		return postCd;
	}

	public void setPostCd(String postCd) {
		this.postCd = postCd == null ? null : postCd.trim();
	}

	public String getAboCd() {
		return aboCd;
	}

	public void setAboCd(String aboCd) {
		this.aboCd = aboCd == null ? null : aboCd.trim();
	}

	public String getRhCd() {
		return rhCd;
	}

	public void setRhCd(String rhCd) {
		this.rhCd = rhCd == null ? null : rhCd.trim();
	}

	public String getRhNegCd() {
		return rhNegCd;
	}

	public void setRhNegCd(String rhNegCd) {
		this.rhNegCd = rhNegCd == null ? null : rhNegCd.trim();
	}

	public String getCardEdBgCd() {
		return cardEdBgCd;
	}

	public void setCardEdBgCd(String cardEdBgCd) {
		this.cardEdBgCd = cardEdBgCd == null ? null : cardEdBgCd.trim();
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace == null ? null : birthPlace.trim();
	}

	public String getWorkingUnitName() {
		return workingUnitName;
	}

	public void setWorkingUnitName(String workingUnitName) {
		this.workingUnitName = workingUnitName == null ? null : workingUnitName.trim();
	}

	public String getEmailAd() {
		return emailAd;
	}

	public void setEmailAd(String emailAd) {
		this.emailAd = emailAd == null ? null : emailAd.trim();
	}

	public String getVeteransMilitaryMark() {
		return veteransMilitaryMark;
	}

	public void setVeteransMilitaryMark(String veteransMilitaryMark) {
		this.veteransMilitaryMark = veteransMilitaryMark == null ? null : veteransMilitaryMark.trim();
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo == null ? null : cardNo.trim();
	}

	public String getCardCd() {
		return cardCd;
	}

	public void setCardCd(String cardCd) {
		this.cardCd = cardCd == null ? null : cardCd.trim();
	}

	public String getCardArea() {
		return cardArea;
	}

	public void setCardArea(String cardArea) {
		this.cardArea = cardArea == null ? null : cardArea.trim();
	}

	public String getPatientType() {
		return patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType == null ? null : patientType.trim();
	}

	public String getmPhoneNum() {
		return mPhoneNum;
	}

	public void setmPhoneNum(String mPhoneNum) {
		this.mPhoneNum = mPhoneNum == null ? null : mPhoneNum.trim();
	}

	public String getWorkingUnitPost() {
		return workingUnitPost;
	}

	public void setWorkingUnitPost(String workingUnitPost) {
		this.workingUnitPost = workingUnitPost == null ? null : workingUnitPost.trim();
	}

	public String getWorkingUnitAddr() {
		return workingUnitAddr;
	}

	public void setWorkingUnitAddr(String workingUnitAddr) {
		this.workingUnitAddr = workingUnitAddr == null ? null : workingUnitAddr.trim();
	}

	public String getLivingAddr() {
		return livingAddr;
	}

	public void setLivingAddr(String livingAddr) {
		this.livingAddr = livingAddr == null ? null : livingAddr.trim();
	}

	public String getRprAddr() {
		return rprAddr;
	}

	public void setRprAddr(String rprAddr) {
		this.rprAddr = rprAddr == null ? null : rprAddr.trim();
	}

	public String getRprPost() {
		return rprPost;
	}

	public void setRprPost(String rprPost) {
		this.rprPost = rprPost == null ? null : rprPost.trim();
	}

	public String getRprPhone() {
		return rprPhone;
	}

	public void setRprPhone(String rprPhone) {
		this.rprPhone = rprPhone == null ? null : rprPhone.trim();
	}

	public String getLinkmanAddr() {
		return linkmanAddr;
	}

	public void setLinkmanAddr(String linkmanAddr) {
		this.linkmanAddr = linkmanAddr == null ? null : linkmanAddr.trim();
	}

	public String getLinkmanPost() {
		return linkmanPost;
	}

	public void setLinkmanPost(String linkmanPost) {
		this.linkmanPost = linkmanPost == null ? null : linkmanPost.trim();
	}

	public String getLinkmanPhone() {
		return linkmanPhone;
	}

	public void setLinkmanPhone(String linkmanPhone) {
		this.linkmanPhone = linkmanPhone == null ? null : linkmanPhone.trim();
	}

	public String getInsureType() {
		return insureType;
	}

	public void setInsureType(String insureType) {
		this.insureType = insureType == null ? null : insureType.trim();
	}

	public String getMedicalTreatmentName() {
		return medicalTreatmentName;
	}

	public void setMedicalTreatmentName(String medicalTreatmentName) {
		this.medicalTreatmentName = medicalTreatmentName == null ? null : medicalTreatmentName.trim();
	}

	public String getMedicalTreatmentCd() {
		return medicalTreatmentCd;
	}

	public void setMedicalTreatmentCd(String medicalTreatmentCd) {
		this.medicalTreatmentCd = medicalTreatmentCd == null ? null : medicalTreatmentCd.trim();
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getRecorderName() {
		return recorderName;
	}

	public void setRecorderName(String recorderName) {
		this.recorderName = recorderName == null ? null : recorderName.trim();
	}

	public String getRegisterPersonCode() {
		return registerPersonCode;
	}

	public void setRegisterPersonCode(String registerPersonCode) {
		this.registerPersonCode = registerPersonCode == null ? null : registerPersonCode.trim();
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getSendOrgCode() {
		return sendOrgCode;
	}

	public void setSendOrgCode(String sendOrgCode) {
		this.sendOrgCode = sendOrgCode == null ? null : sendOrgCode.trim();
	}

	public String getSendSystem() {
		return sendSystem;
	}

	public void setSendSystem(String sendSystem) {
		this.sendSystem = sendSystem == null ? null : sendSystem.trim();
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName == null ? null : providerName.trim();
	}

	public String getProviderOrgCode() {
		return providerOrgCode;
	}

	public void setProviderOrgCode(String providerOrgCode) {
		this.providerOrgCode = providerOrgCode == null ? null : providerOrgCode.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getLastuptime() {
		return lastuptime;
	}

	public void setLastuptime(Date lastuptime) {
		this.lastuptime = lastuptime;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public String getGenderCs() {
		return genderCs;
	}

	public void setGenderCs(String genderCs) {
		this.genderCs = genderCs == null ? null : genderCs.trim();
	}

	public String getGenderCsn() {
		return genderCsn;
	}

	public void setGenderCsn(String genderCsn) {
		this.genderCsn = genderCsn == null ? null : genderCsn.trim();
	}

	public String getGenderCsv() {
		return genderCsv;
	}

	public void setGenderCsv(String genderCsv) {
		this.genderCsv = genderCsv == null ? null : genderCsv.trim();
	}

	public String getGenderDn() {
		return genderDn;
	}

	public void setGenderDn(String genderDn) {
		this.genderDn = genderDn == null ? null : genderDn.trim();
	}

	public String getCardMaritalStCs() {
		return cardMaritalStCs;
	}

	public void setCardMaritalStCs(String cardMaritalStCs) {
		this.cardMaritalStCs = cardMaritalStCs == null ? null : cardMaritalStCs.trim();
	}

	public String getCardMaritalStCsn() {
		return cardMaritalStCsn;
	}

	public void setCardMaritalStCsn(String cardMaritalStCsn) {
		this.cardMaritalStCsn = cardMaritalStCsn == null ? null : cardMaritalStCsn.trim();
	}

	public String getCardMaritalStCsv() {
		return cardMaritalStCsv;
	}

	public void setCardMaritalStCsv(String cardMaritalStCsv) {
		this.cardMaritalStCsv = cardMaritalStCsv == null ? null : cardMaritalStCsv.trim();
	}

	public String getCardMaritalStDn() {
		return cardMaritalStDn;
	}

	public void setCardMaritalStDn(String cardMaritalStDn) {
		this.cardMaritalStDn = cardMaritalStDn == null ? null : cardMaritalStDn.trim();
	}

	public String getIdNoCs() {
		return idNoCs;
	}

	public void setIdNoCs(String idNoCs) {
		this.idNoCs = idNoCs == null ? null : idNoCs.trim();
	}

	public String getIdNoCsn() {
		return idNoCsn;
	}

	public void setIdNoCsn(String idNoCsn) {
		this.idNoCsn = idNoCsn == null ? null : idNoCsn.trim();
	}

	public String getIdNoCsv() {
		return idNoCsv;
	}

	public void setIdNoCsv(String idNoCsv) {
		this.idNoCsv = idNoCsv == null ? null : idNoCsv.trim();
	}

	public String getIdNoDn() {
		return idNoDn;
	}

	public void setIdNoDn(String idNoDn) {
		this.idNoDn = idNoDn == null ? null : idNoDn.trim();
	}

	public String getMedicareCs() {
		return medicareCs;
	}

	public void setMedicareCs(String medicareCs) {
		this.medicareCs = medicareCs == null ? null : medicareCs.trim();
	}

	public String getMedicareCsn() {
		return medicareCsn;
	}

	public void setMedicareCsn(String medicareCsn) {
		this.medicareCsn = medicareCsn == null ? null : medicareCsn.trim();
	}

	public String getMedicareCsv() {
		return medicareCsv;
	}

	public void setMedicareCsv(String medicareCsv) {
		this.medicareCsv = medicareCsv == null ? null : medicareCsv.trim();
	}

	public String getMedicareDn() {
		return medicareDn;
	}

	public void setMedicareDn(String medicareDn) {
		this.medicareDn = medicareDn == null ? null : medicareDn.trim();
	}

	public String getNhCard() {
		return nhCard;
	}

	public void setNhCard(String nhCard) {
		this.nhCard = nhCard == null ? null : nhCard.trim();
	}

	public String getSscid() {
		return sscid;
	}

	public void setSscid(String sscid) {
		this.sscid = sscid == null ? null : sscid.trim();
	}

	public String getCardOccuTypeCs() {
		return cardOccuTypeCs;
	}

	public void setCardOccuTypeCs(String cardOccuTypeCs) {
		this.cardOccuTypeCs = cardOccuTypeCs == null ? null : cardOccuTypeCs.trim();
	}

	public String getCardOccuTypeCsn() {
		return cardOccuTypeCsn;
	}

	public void setCardOccuTypeCsn(String cardOccuTypeCsn) {
		this.cardOccuTypeCsn = cardOccuTypeCsn == null ? null : cardOccuTypeCsn.trim();
	}

	public String getCardOccuTypeCsv() {
		return cardOccuTypeCsv;
	}

	public void setCardOccuTypeCsv(String cardOccuTypeCsv) {
		this.cardOccuTypeCsv = cardOccuTypeCsv == null ? null : cardOccuTypeCsv.trim();
	}

	public String getCardOccuTypeDn() {
		return cardOccuTypeDn;
	}

	public void setCardOccuTypeDn(String cardOccuTypeDn) {
		this.cardOccuTypeDn = cardOccuTypeDn == null ? null : cardOccuTypeDn.trim();
	}

	public String getCardNationCs() {
		return cardNationCs;
	}

	public void setCardNationCs(String cardNationCs) {
		this.cardNationCs = cardNationCs == null ? null : cardNationCs.trim();
	}

	public String getCardNationCsn() {
		return cardNationCsn;
	}

	public void setCardNationCsn(String cardNationCsn) {
		this.cardNationCsn = cardNationCsn == null ? null : cardNationCsn.trim();
	}

	public String getCardNationCsv() {
		return cardNationCsv;
	}

	public void setCardNationCsv(String cardNationCsv) {
		this.cardNationCsv = cardNationCsv == null ? null : cardNationCsv.trim();
	}

	public String getCardNationDn() {
		return cardNationDn;
	}

	public void setCardNationDn(String cardNationDn) {
		this.cardNationDn = cardNationDn == null ? null : cardNationDn.trim();
	}

	public String getNationalityCs() {
		return nationalityCs;
	}

	public void setNationalityCs(String nationalityCs) {
		this.nationalityCs = nationalityCs == null ? null : nationalityCs.trim();
	}

	public String getNationalityCsn() {
		return nationalityCsn;
	}

	public void setNationalityCsn(String nationalityCsn) {
		this.nationalityCsn = nationalityCsn == null ? null : nationalityCsn.trim();
	}

	public String getNationalityCsv() {
		return nationalityCsv;
	}

	public void setNationalityCsv(String nationalityCsv) {
		this.nationalityCsv = nationalityCsv == null ? null : nationalityCsv.trim();
	}

	public String getNationalityDn() {
		return nationalityDn;
	}

	public void setNationalityDn(String nationalityDn) {
		this.nationalityDn = nationalityDn == null ? null : nationalityDn.trim();
	}

	public String getArCs() {
		return arCs;
	}

	public void setArCs(String arCs) {
		this.arCs = arCs == null ? null : arCs.trim();
	}

	public String getArCsn() {
		return arCsn;
	}

	public void setArCsn(String arCsn) {
		this.arCsn = arCsn == null ? null : arCsn.trim();
	}

	public String getArCsv() {
		return arCsv;
	}

	public void setArCsv(String arCsv) {
		this.arCsv = arCsv == null ? null : arCsv.trim();
	}

	public String getArDn() {
		return arDn;
	}

	public void setArDn(String arDn) {
		this.arDn = arDn == null ? null : arDn.trim();
	}

	public String getAdCs() {
		return adCs;
	}

	public void setAdCs(String adCs) {
		this.adCs = adCs == null ? null : adCs.trim();
	}

	public String getAdCsn() {
		return adCsn;
	}

	public void setAdCsn(String adCsn) {
		this.adCsn = adCsn == null ? null : adCsn.trim();
	}

	public String getAdCsv() {
		return adCsv;
	}

	public void setAdCsv(String adCsv) {
		this.adCsv = adCsv == null ? null : adCsv.trim();
	}

	public String getAdDn() {
		return adDn;
	}

	public void setAdDn(String adDn) {
		this.adDn = adDn == null ? null : adDn.trim();
	}

	public String getTelTypeCs() {
		return telTypeCs;
	}

	public void setTelTypeCs(String telTypeCs) {
		this.telTypeCs = telTypeCs == null ? null : telTypeCs.trim();
	}

	public String getTelTypeCsn() {
		return telTypeCsn;
	}

	public void setTelTypeCsn(String telTypeCsn) {
		this.telTypeCsn = telTypeCsn == null ? null : telTypeCsn.trim();
	}

	public String getTelTypeCsv() {
		return telTypeCsv;
	}

	public void setTelTypeCsv(String telTypeCsv) {
		this.telTypeCsv = telTypeCsv == null ? null : telTypeCsv.trim();
	}

	public String getTelTypeDn() {
		return telTypeDn;
	}

	public void setTelTypeDn(String telTypeDn) {
		this.telTypeDn = telTypeDn == null ? null : telTypeDn.trim();
	}

	public String getLinkmanRelCs() {
		return linkmanRelCs;
	}

	public void setLinkmanRelCs(String linkmanRelCs) {
		this.linkmanRelCs = linkmanRelCs == null ? null : linkmanRelCs.trim();
	}

	public String getLinkmanRelCsn() {
		return linkmanRelCsn;
	}

	public void setLinkmanRelCsn(String linkmanRelCsn) {
		this.linkmanRelCsn = linkmanRelCsn == null ? null : linkmanRelCsn.trim();
	}

	public String getLinkmanRelCsv() {
		return linkmanRelCsv;
	}

	public void setLinkmanRelCsv(String linkmanRelCsv) {
		this.linkmanRelCsv = linkmanRelCsv == null ? null : linkmanRelCsv.trim();
	}

	public String getLinkmanRelDn() {
		return linkmanRelDn;
	}

	public void setLinkmanRelDn(String linkmanRelDn) {
		this.linkmanRelDn = linkmanRelDn == null ? null : linkmanRelDn.trim();
	}

	public String getLmIdNoCs() {
		return lmIdNoCs;
	}

	public void setLmIdNoCs(String lmIdNoCs) {
		this.lmIdNoCs = lmIdNoCs == null ? null : lmIdNoCs.trim();
	}

	public String getLmIdNoCsn() {
		return lmIdNoCsn;
	}

	public void setLmIdNoCsn(String lmIdNoCsn) {
		this.lmIdNoCsn = lmIdNoCsn == null ? null : lmIdNoCsn.trim();
	}

	public String getLmIdNoCsv() {
		return lmIdNoCsv;
	}

	public void setLmIdNoCsv(String lmIdNoCsv) {
		this.lmIdNoCsv = lmIdNoCsv == null ? null : lmIdNoCsv.trim();
	}

	public String getLmIdNoDn() {
		return lmIdNoDn;
	}

	public void setLmIdNoDn(String lmIdNoDn) {
		this.lmIdNoDn = lmIdNoDn == null ? null : lmIdNoDn.trim();
	}

	public String getAboCs() {
		return aboCs;
	}

	public void setAboCs(String aboCs) {
		this.aboCs = aboCs == null ? null : aboCs.trim();
	}

	public String getAboCsn() {
		return aboCsn;
	}

	public void setAboCsn(String aboCsn) {
		this.aboCsn = aboCsn == null ? null : aboCsn.trim();
	}

	public String getAboCsv() {
		return aboCsv;
	}

	public void setAboCsv(String aboCsv) {
		this.aboCsv = aboCsv == null ? null : aboCsv.trim();
	}

	public String getAboDn() {
		return aboDn;
	}

	public void setAboDn(String aboDn) {
		this.aboDn = aboDn == null ? null : aboDn.trim();
	}

	public String getRhCs() {
		return rhCs;
	}

	public void setRhCs(String rhCs) {
		this.rhCs = rhCs == null ? null : rhCs.trim();
	}

	public String getRhCsn() {
		return rhCsn;
	}

	public void setRhCsn(String rhCsn) {
		this.rhCsn = rhCsn == null ? null : rhCsn.trim();
	}

	public String getRhCsv() {
		return rhCsv;
	}

	public void setRhCsv(String rhCsv) {
		this.rhCsv = rhCsv == null ? null : rhCsv.trim();
	}

	public String getRhDn() {
		return rhDn;
	}

	public void setRhDn(String rhDn) {
		this.rhDn = rhDn == null ? null : rhDn.trim();
	}

	public String getCardEdBgCs() {
		return cardEdBgCs;
	}

	public void setCardEdBgCs(String cardEdBgCs) {
		this.cardEdBgCs = cardEdBgCs == null ? null : cardEdBgCs.trim();
	}

	public String getCardEdBgCsn() {
		return cardEdBgCsn;
	}

	public void setCardEdBgCsn(String cardEdBgCsn) {
		this.cardEdBgCsn = cardEdBgCsn == null ? null : cardEdBgCsn.trim();
	}

	public String getCardEdBgCsv() {
		return cardEdBgCsv;
	}

	public void setCardEdBgCsv(String cardEdBgCsv) {
		this.cardEdBgCsv = cardEdBgCsv == null ? null : cardEdBgCsv.trim();
	}

	public String getCardEdBgDn() {
		return cardEdBgDn;
	}

	public void setCardEdBgDn(String cardEdBgDn) {
		this.cardEdBgDn = cardEdBgDn == null ? null : cardEdBgDn.trim();
	}

	public String getBirthPlaceCs() {
		return birthPlaceCs;
	}

	public void setBirthPlaceCs(String birthPlaceCs) {
		this.birthPlaceCs = birthPlaceCs == null ? null : birthPlaceCs.trim();
	}

	public String getBirthPlaceCsn() {
		return birthPlaceCsn;
	}

	public void setBirthPlaceCsn(String birthPlaceCsn) {
		this.birthPlaceCsn = birthPlaceCsn == null ? null : birthPlaceCsn.trim();
	}

	public String getBirthPlaceCsv() {
		return birthPlaceCsv;
	}

	public void setBirthPlaceCsv(String birthPlaceCsv) {
		this.birthPlaceCsv = birthPlaceCsv == null ? null : birthPlaceCsv.trim();
	}

	public String getBirthPlaceDn() {
		return birthPlaceDn;
	}

	public void setBirthPlaceDn(String birthPlaceDn) {
		this.birthPlaceDn = birthPlaceDn == null ? null : birthPlaceDn.trim();
	}

	public String getNativeProvince() {
		return nativeProvince;
	}

	public void setNativeProvince(String nativeProvince) {
		this.nativeProvince = nativeProvince == null ? null : nativeProvince.trim();
	}

	public String getNativeProvinceCs() {
		return nativeProvinceCs;
	}

	public void setNativeProvinceCs(String nativeProvinceCs) {
		this.nativeProvinceCs = nativeProvinceCs == null ? null : nativeProvinceCs.trim();
	}

	public String getNativeProvinceCsn() {
		return nativeProvinceCsn;
	}

	public void setNativeProvinceCsn(String nativeProvinceCsn) {
		this.nativeProvinceCsn = nativeProvinceCsn == null ? null : nativeProvinceCsn.trim();
	}

	public String getNativeProvinceCsv() {
		return nativeProvinceCsv;
	}

	public void setNativeProvinceCsv(String nativeProvinceCsv) {
		this.nativeProvinceCsv = nativeProvinceCsv == null ? null : nativeProvinceCsv.trim();
	}

	public String getNativeProvinceDn() {
		return nativeProvinceDn;
	}

	public void setNativeProvinceDn(String nativeProvinceDn) {
		this.nativeProvinceDn = nativeProvinceDn == null ? null : nativeProvinceDn.trim();
	}

	public String getNativeCity() {
		return nativeCity;
	}

	public void setNativeCity(String nativeCity) {
		this.nativeCity = nativeCity == null ? null : nativeCity.trim();
	}

	public String getNativeCityCs() {
		return nativeCityCs;
	}

	public void setNativeCityCs(String nativeCityCs) {
		this.nativeCityCs = nativeCityCs == null ? null : nativeCityCs.trim();
	}

	public String getNativeCityCsn() {
		return nativeCityCsn;
	}

	public void setNativeCityCsn(String nativeCityCsn) {
		this.nativeCityCsn = nativeCityCsn == null ? null : nativeCityCsn.trim();
	}

	public String getNativeCityCsv() {
		return nativeCityCsv;
	}

	public void setNativeCityCsv(String nativeCityCsv) {
		this.nativeCityCsv = nativeCityCsv == null ? null : nativeCityCsv.trim();
	}

	public String getNativeCityDn() {
		return nativeCityDn;
	}

	public void setNativeCityDn(String nativeCityDn) {
		this.nativeCityDn = nativeCityDn == null ? null : nativeCityDn.trim();
	}

	public String getVeteransMilitaryValue() {
		return veteransMilitaryValue;
	}

	public void setVeteransMilitaryValue(String veteransMilitaryValue) {
		this.veteransMilitaryValue = veteransMilitaryValue == null ? null : veteransMilitaryValue.trim();
	}

	public String getVeteransMilitaryMarkCs() {
		return veteransMilitaryMarkCs;
	}

	public void setVeteransMilitaryMarkCs(String veteransMilitaryMarkCs) {
		this.veteransMilitaryMarkCs = veteransMilitaryMarkCs == null ? null : veteransMilitaryMarkCs.trim();
	}

	public String getVeteransMilitaryMarkCsn() {
		return veteransMilitaryMarkCsn;
	}

	public void setVeteransMilitaryMarkCsn(String veteransMilitaryMarkCsn) {
		this.veteransMilitaryMarkCsn = veteransMilitaryMarkCsn == null ? null : veteransMilitaryMarkCsn.trim();
	}

	public String getVeteransMilitaryMarkCsv() {
		return veteransMilitaryMarkCsv;
	}

	public void setVeteransMilitaryMarkCsv(String veteransMilitaryMarkCsv) {
		this.veteransMilitaryMarkCsv = veteransMilitaryMarkCsv == null ? null : veteransMilitaryMarkCsv.trim();
	}

	public String getCardCs() {
		return cardCs;
	}

	public void setCardCs(String cardCs) {
		this.cardCs = cardCs == null ? null : cardCs.trim();
	}

	public String getCardCsn() {
		return cardCsn;
	}

	public void setCardCsn(String cardCsn) {
		this.cardCsn = cardCsn == null ? null : cardCsn.trim();
	}

	public String getCardCsv() {
		return cardCsv;
	}

	public void setCardCsv(String cardCsv) {
		this.cardCsv = cardCsv == null ? null : cardCsv.trim();
	}

	public String getCardDn() {
		return cardDn;
	}

	public void setCardDn(String cardDn) {
		this.cardDn = cardDn == null ? null : cardDn.trim();
	}

	public String getCardAreaCs() {
		return cardAreaCs;
	}

	public void setCardAreaCs(String cardAreaCs) {
		this.cardAreaCs = cardAreaCs == null ? null : cardAreaCs.trim();
	}

	public String getCardAreaCsn() {
		return cardAreaCsn;
	}

	public void setCardAreaCsn(String cardAreaCsn) {
		this.cardAreaCsn = cardAreaCsn == null ? null : cardAreaCsn.trim();
	}

	public String getCardAreaCsv() {
		return cardAreaCsv;
	}

	public void setCardAreaCsv(String cardAreaCsv) {
		this.cardAreaCsv = cardAreaCsv == null ? null : cardAreaCsv.trim();
	}

	public String getCardAreaDn() {
		return cardAreaDn;
	}

	public void setCardAreaDn(String cardAreaDn) {
		this.cardAreaDn = cardAreaDn == null ? null : cardAreaDn.trim();
	}

	public String getPatientTypeValue() {
		return patientTypeValue;
	}

	public void setPatientTypeValue(String patientTypeValue) {
		this.patientTypeValue = patientTypeValue == null ? null : patientTypeValue.trim();
	}

	public String getPatientTypeCs() {
		return patientTypeCs;
	}

	public void setPatientTypeCs(String patientTypeCs) {
		this.patientTypeCs = patientTypeCs == null ? null : patientTypeCs.trim();
	}

	public String getPatientTypeCsn() {
		return patientTypeCsn;
	}

	public void setPatientTypeCsn(String patientTypeCsn) {
		this.patientTypeCsn = patientTypeCsn == null ? null : patientTypeCsn.trim();
	}

	public String getPatientTypeCsv() {
		return patientTypeCsv;
	}

	public void setPatientTypeCsv(String patientTypeCsv) {
		this.patientTypeCsv = patientTypeCsv == null ? null : patientTypeCsv.trim();
	}

	public String getPatientTypeDescr() {
		return patientTypeDescr;
	}

	public void setPatientTypeDescr(String patientTypeDescr) {
		this.patientTypeDescr = patientTypeDescr == null ? null : patientTypeDescr.trim();
	}

	public String getWorkingTelNo() {
		return workingTelNo;
	}

	public void setWorkingTelNo(String workingTelNo) {
		this.workingTelNo = workingTelNo == null ? null : workingTelNo.trim();
	}

	public String getMedicalTreatmentCs() {
		return medicalTreatmentCs;
	}

	public void setMedicalTreatmentCs(String medicalTreatmentCs) {
		this.medicalTreatmentCs = medicalTreatmentCs == null ? null : medicalTreatmentCs.trim();
	}

	public String getMedicalTreatmentCsn() {
		return medicalTreatmentCsn;
	}

	public void setMedicalTreatmentCsn(String medicalTreatmentCsn) {
		this.medicalTreatmentCsn = medicalTreatmentCsn == null ? null : medicalTreatmentCsn.trim();
	}

	public String getMedicalTreatmentCsv() {
		return medicalTreatmentCsv;
	}

	public void setMedicalTreatmentCsv(String medicalTreatmentCsv) {
		this.medicalTreatmentCsv = medicalTreatmentCsv == null ? null : medicalTreatmentCsv.trim();
	}

	public String getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum == null ? null : versionNum.trim();
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getSrcLevel() {
		return srcLevel;
	}

	public void setSrcLevel(String srcLevel) {
		this.srcLevel = srcLevel == null ? null : srcLevel.trim();
	}

	public String getRegisterOrgName() {
		return registerOrgName;
	}

	public void setRegisterOrgName(String registerOrgName) {
		this.registerOrgName = registerOrgName == null ? null : registerOrgName.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getResponsDocCode() {
		return responsDocCode;
	}

	public void setResponsDocCode(String responsDocCode) {
		this.responsDocCode = responsDocCode == null ? null : responsDocCode.trim();
	}

	public String getResponsDocName() {
		return responsDocName;
	}

	public void setResponsDocName(String responsDocName) {
		this.responsDocName = responsDocName == null ? null : responsDocName.trim();
	}

	public String getOtherOcccDescr() {
		return otherOcccDescr;
	}

	public void setOtherOcccDescr(String otherOcccDescr) {
		this.otherOcccDescr = otherOcccDescr == null ? null : otherOcccDescr.trim();
	}

	public Short getUsualTypeMark() {
		return usualTypeMark;
	}

	public void setUsualTypeMark(Short usualTypeMark) {
		this.usualTypeMark = usualTypeMark;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId == null ? null : patientId.trim();
	}

	public String getDrugAllergyMark() {
		return drugAllergyMark;
	}

	public void setDrugAllergyMark(String drugAllergyMark) {
		this.drugAllergyMark = drugAllergyMark == null ? null : drugAllergyMark.trim();
	}

	public String getOpHistoryMark() {
		return opHistoryMark;
	}

	public void setOpHistoryMark(String opHistoryMark) {
		this.opHistoryMark = opHistoryMark == null ? null : opHistoryMark.trim();
	}

	public String getTraumaHistoryMark() {
		return traumaHistoryMark;
	}

	public void setTraumaHistoryMark(String traumaHistoryMark) {
		this.traumaHistoryMark = traumaHistoryMark == null ? null : traumaHistoryMark.trim();
	}

	public String getBloodTransfMark() {
		return bloodTransfMark;
	}

	public void setBloodTransfMark(String bloodTransfMark) {
		this.bloodTransfMark = bloodTransfMark == null ? null : bloodTransfMark.trim();
	}

	public String getDisabilityMark() {
		return disabilityMark;
	}

	public void setDisabilityMark(String disabilityMark) {
		this.disabilityMark = disabilityMark == null ? null : disabilityMark.trim();
	}

	public String getGeneticDiseaseHistory() {
		return geneticDiseaseHistory;
	}

	public void setGeneticDiseaseHistory(String geneticDiseaseHistory) {
		this.geneticDiseaseHistory = geneticDiseaseHistory == null ? null : geneticDiseaseHistory.trim();
	}

	public String getExhaustFacilityMark() {
		return exhaustFacilityMark;
	}

	public void setExhaustFacilityMark(String exhaustFacilityMark) {
		this.exhaustFacilityMark = exhaustFacilityMark == null ? null : exhaustFacilityMark.trim();
	}

	public String getExhaustFacilityTypeCode() {
		return exhaustFacilityTypeCode;
	}

	public void setExhaustFacilityTypeCode(String exhaustFacilityTypeCode) {
		this.exhaustFacilityTypeCode = exhaustFacilityTypeCode == null ? null : exhaustFacilityTypeCode.trim();
	}

	public String getFuelTypeCode() {
		return fuelTypeCode;
	}

	public void setFuelTypeCode(String fuelTypeCode) {
		this.fuelTypeCode = fuelTypeCode == null ? null : fuelTypeCode.trim();
	}

	public String getWaterTypeCode() {
		return waterTypeCode;
	}

	public void setWaterTypeCode(String waterTypeCode) {
		this.waterTypeCode = waterTypeCode == null ? null : waterTypeCode.trim();
	}

	public String getToiletTypeCode() {
		return toiletTypeCode;
	}

	public void setToiletTypeCode(String toiletTypeCode) {
		this.toiletTypeCode = toiletTypeCode == null ? null : toiletTypeCode.trim();
	}

	public String getLivestockPenTypeCode() {
		return livestockPenTypeCode;
	}

	public void setLivestockPenTypeCode(String livestockPenTypeCode) {
		this.livestockPenTypeCode = livestockPenTypeCode == null ? null : livestockPenTypeCode.trim();
	}

	public String getOperationHistory() {
		return operationHistory;
	}

	public void setOperationHistory(String operationHistory) {
		this.operationHistory = operationHistory == null ? null : operationHistory.trim();
	}

	public String getAsthmaMark() {
		return asthmaMark;
	}

	public void setAsthmaMark(String asthmaMark) {
		this.asthmaMark = asthmaMark == null ? null : asthmaMark.trim();
	}

	public String getHedrtDisMark() {
		return hedrtDisMark;
	}

	public void setHedrtDisMark(String hedrtDisMark) {
		this.hedrtDisMark = hedrtDisMark == null ? null : hedrtDisMark.trim();
	}

	public String getCardiovascularCode() {
		return cardiovascularCode;
	}

	public void setCardiovascularCode(String cardiovascularCode) {
		this.cardiovascularCode = cardiovascularCode == null ? null : cardiovascularCode.trim();
	}

	public String getEpilepsyMark() {
		return epilepsyMark;
	}

	public void setEpilepsyMark(String epilepsyMark) {
		this.epilepsyMark = epilepsyMark == null ? null : epilepsyMark.trim();
	}

	public String getCoagulopathyMark() {
		return coagulopathyMark;
	}

	public void setCoagulopathyMark(String coagulopathyMark) {
		this.coagulopathyMark = coagulopathyMark == null ? null : coagulopathyMark.trim();
	}

	public String getDiabetesMark() {
		return diabetesMark;
	}

	public void setDiabetesMark(String diabetesMark) {
		this.diabetesMark = diabetesMark == null ? null : diabetesMark.trim();
	}

	public String getGlaucomaMark() {
		return glaucomaMark;
	}

	public void setGlaucomaMark(String glaucomaMark) {
		this.glaucomaMark = glaucomaMark == null ? null : glaucomaMark.trim();
	}

	public String getDialysisMark() {
		return dialysisMark;
	}

	public void setDialysisMark(String dialysisMark) {
		this.dialysisMark = dialysisMark == null ? null : dialysisMark.trim();
	}

	public String getOrganTransMark() {
		return organTransMark;
	}

	public void setOrganTransMark(String organTransMark) {
		this.organTransMark = organTransMark == null ? null : organTransMark.trim();
	}

	public String getOrganDefectMark() {
		return organDefectMark;
	}

	public void setOrganDefectMark(String organDefectMark) {
		this.organDefectMark = organDefectMark == null ? null : organDefectMark.trim();
	}

	public String getRemovaProMark() {
		return removaProMark;
	}

	public void setRemovaProMark(String removaProMark) {
		this.removaProMark = removaProMark == null ? null : removaProMark.trim();
	}

	public String getCardiacPacMark() {
		return cardiacPacMark;
	}

	public void setCardiacPacMark(String cardiacPacMark) {
		this.cardiacPacMark = cardiacPacMark == null ? null : cardiacPacMark.trim();
	}

	public String getOrtherMedicalAlert() {
		return ortherMedicalAlert;
	}

	public void setOrtherMedicalAlert(String ortherMedicalAlert) {
		this.ortherMedicalAlert = ortherMedicalAlert == null ? null : ortherMedicalAlert.trim();
	}

	public String getPsychiatricMark() {
		return psychiatricMark;
	}

	public void setPsychiatricMark(String psychiatricMark) {
		this.psychiatricMark = psychiatricMark == null ? null : psychiatricMark.trim();
	}

	public PersonIndex mpiCombineRecToPersonIndex() {
		PersonIndex index = new PersonIndex();
		BeanUtils.copyProperties(this, index);
		index.setDomainLevel(this.srcLevel);
		return index;
	}
}