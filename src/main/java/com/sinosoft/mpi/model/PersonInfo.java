package com.sinosoft.mpi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import com.sinosoft.mpi.annotation.PropertyDesc;
import com.sinosoft.mpi.dics.PersonType;

/**
 * 主索引人员
 */
@Entity(name = "MPI_PERSON_INFO")
public class PersonInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@PropertyDesc(name = "主键", column = "FIELD_PK")
	private String fieldPk;

	@PropertyDesc(name = "注册机构代码", column = "REGISTER_ORG_CODE")
	private String registerOrgCode;

	@PropertyDesc(name = "注册机构名称", column = "REGISTER_ORG_NAME")
	private String registerOrgName;

	@PropertyDesc(name = "医疗服务编号", column = "MEDICALSERVICE_NO")
	private String medicalserviceNo;

	/* 从姓名到精神病标志与mpiinfo信息一致>>> */
	@PropertyDesc(name = "姓名", column = "NAME_CN")
	private String nameCn;

	@PropertyDesc(name = "英文姓名", column = "NAME_EN")
	private String nameEn;

	@PropertyDesc(name = "出生日期", column = "BIRTH_DATE")
	private Date birthDate;

	@PropertyDesc(name = "性别代码", column = "GENDER_CD")
	private String genderCd;

	@PropertyDesc(name = "婚姻状况类别代码", column = "CARD_MARITAL_ST_CD")
	private String cardMaritalStCd;

	@PropertyDesc(name = "标识号-类别代码", column = "ID_NO_CD")
	private String idNoCd;

	@PropertyDesc(name = "标识号-号码", column = "ID_NO")
	private String idNo;

	@PropertyDesc(name = "医疗保险-类别代码", column = "MEDICARE_CD")
	private String medicareCd;

	@PropertyDesc(name = "医疗保险-号码", column = "MEDICAL_INSURANCE_NO")
	private String medicalInsuranceNo;

	@PropertyDesc(name = "职业类别代码(国标)", column = "CARD_OCCU_TYPE_CD")
	private String cardOccuTypeCd;

	@PropertyDesc(name = "民族代码", column = "CARD_NATION_CD")
	private String cardNationCd;

	@PropertyDesc(name = "国籍代码", column = "NATIONALITY_CD")
	private String nationalityCd;

	@PropertyDesc(name = "行政区划代码", column = "AR_CD")
	private String arCd;

	@PropertyDesc(name = "地址类别代码", column = "AD_CD")
	private String adCd;

	@PropertyDesc(name = "地址-省（自治区、直辖市）", column = "PROVINCE_NAME")
	private String provinceName;

	@PropertyDesc(name = "地址-市（地区）", column = "CITY_NAME")
	private String cityName;

	@PropertyDesc(name = "地址-县（区）", column = "AREA_NAME")
	private String areaName;

	@PropertyDesc(name = "地址-乡（镇、街道办事处）", column = "STREET_ID")
	private String streetId;

	@PropertyDesc(name = "地址-村（街、路、弄等）", column = "VILLAGE_NAME")
	private String villageName;

	@PropertyDesc(name = "地址-门牌号码", column = "HOUSE_NO")
	private String houseNo;

	@PropertyDesc(name = "联系电话-类别代码", column = "TEL_TYPE_CD")
	private String telTypeCd;

	@PropertyDesc(name = "联系电话-号码", column = "PERSON_TEL_NO")
	private String personTelNo;

	@PropertyDesc(name = "联系人姓名", column = "LINKMAN_NAME")
	private String linkmanName;

	@PropertyDesc(name = "联系人关系代码", column = "LINKMAN_REL_CD")
	private String linkmanRelCd;

	@PropertyDesc(name = "标识号-类别代码（联系人）", column = "LM_ID_NO_CD")
	private String lmIdNoCd;

	@PropertyDesc(name = "标识号-号码（联系人）", column = "LM_ID_NO")
	private String lmIdNo;

	@PropertyDesc(name = "城乡居民健康档案编号", column = "HR_ID")
	private String hrId;

	@PropertyDesc(name = "邮政编码", column = "POST_CD")
	private String postCd;

	@PropertyDesc(name = "ABO血型", column = "ABO_CD")
	private String aboCd;

	@PropertyDesc(name = "Rh血型", column = "RH_CD")
	private String rhCd;

	@PropertyDesc(name = "RH阴性代码", column = "RH_NEG_CD")
	private String rhNegCd;

	@PropertyDesc(name = "文化程度代码", column = "CARD_ED_BG_CD")
	private String cardEdBgCd;

	@PropertyDesc(name = "出生地", column = "BIRTH_PLACE")
	private String birthPlace;

	@PropertyDesc(name = "工作单位名称", column = "WORKING_UNIT_NAME")
	private String workingUnitName;

	@PropertyDesc(name = "电子邮件地址", column = "EMAIL_AD")
	private String emailAd;

	@PropertyDesc(name = "退伍军人标志", column = "VETERANS_MILITARY_MARK")
	private Short veteransMilitaryMark;

	@PropertyDesc(name = "卡号", column = "CARD_NO")
	private String cardNo;

	@PropertyDesc(name = "卡类型", column = "CARD_CD")
	private String cardCd;

	@PropertyDesc(name = "发卡地区", column = "CARD_AREA")
	private String cardArea;

	@PropertyDesc(name = "患者类型", column = "PATIENT_TYPE")
	private String patientType;

	@PropertyDesc(name = "手机号码", column = "M_PHONE_NUM")
	private String mPhoneNum;

	@PropertyDesc(name = "工作单位邮编", column = "WORKING_UNIT_POST")
	private String workingUnitPost;

	@PropertyDesc(name = "工作单位地址", column = "WORKING_UNIT_ADDR")
	private String workingUnitAddr;

	@PropertyDesc(name = "居住地址", column = "LIVING_ADDR")
	private String livingAddr;

	@PropertyDesc(name = "户口地址", column = "RPR_ADDR")
	private String rprAddr;

	@PropertyDesc(name = "户口地址邮编", column = "RPR_POST")
	private String rprPost;

	@PropertyDesc(name = "户口电话", column = "RPR_PHONE")
	private String rprPhone;

	@PropertyDesc(name = "联系人地址", column = "LINKMAN_ADDR")
	private String linkmanAddr;

	@PropertyDesc(name = "联系人邮编", column = "LINKMAN_POST")
	private String linkmanPost;

	@PropertyDesc(name = "联系人电话", column = "LINKMAN_PHONE")
	private String linkmanPhone;

	@PropertyDesc(name = "保险类型", column = "INSURE_TYPE")
	private String insureType;

	@PropertyDesc(name = "医疗待遇名称", column = "MEDICAL_TREATMENT_NAME")
	private String medicalTreatmentName;

	@PropertyDesc(name = "医疗待遇代码", column = "MEDICAL_TREATMENT_CD")
	private String medicalTreatmentCd;

	@PropertyDesc(name = "建档日期", column = "REGISTER_DATE")
	private Date registerDate;

	@PropertyDesc(name = "建档人员姓名", column = "RECORDER_NAME")
	private String recorderName;

	@PropertyDesc(name = "建档人员代码", column = "REGISTER_PERSON_CODE")
	private String registerPersonCode;

	@PropertyDesc(name = "上报日期", column = "SEND_TIME")
	private Date sendTime;

	@PropertyDesc(name = "上报机构", column = "SEND_ORG_CODE")
	private String sendOrgCode;

	@PropertyDesc(name = "上报系统", column = "SEND_SYSTEM")
	private String sendSystem;

	@PropertyDesc(name = "系统开发商", column = "PROVIDER_NAME")
	private String providerName;

	@PropertyDesc(name = "开发商机构代码", column = "PROVIDER_ORG_CODE")
	private String providerOrgCode;

	@PropertyDesc(name = "创建日期", column = "CREATETIME")
	private Date createtime;

	@PropertyDesc(name = "最终修改日期", column = "LASTUPTIME")
	private Date lastuptime;

	@PropertyDesc(name = "上传状态", column = "STATE")
	private Short state;

	@PropertyDesc(name = "性别代码的字典表代码", column = "GENDER_CS")
	private String genderCs;

	@PropertyDesc(name = "性别代码的字典名称", column = "GENDER_CSN")
	private String genderCsn;

	@PropertyDesc(name = "性别代码的字典版本", column = "GENDER_CSV")
	private String genderCsv;

	@PropertyDesc(name = "性别代码的值", column = "GENDER_DN")
	private String genderDn;

	@PropertyDesc(name = "婚姻状况的字典代码", column = "CARD_MARITAL_ST_CS")
	private String cardMaritalStCs;

	@PropertyDesc(name = "婚姻状况的字典名称", column = "CARD_MARITAL_ST_CSN")
	private String cardMaritalStCsn;

	@PropertyDesc(name = "婚姻状况的字典版本", column = "CARD_MARITAL_ST_CSV")
	private String cardMaritalStCsv;

	@PropertyDesc(name = "婚姻状况的值", column = "CARD_MARITAL_ST_DN")
	private String cardMaritalStDn;

	@PropertyDesc(name = "身份证类别代码的字典代码", column = "ID_NO_CS")
	private String idNoCs;

	@PropertyDesc(name = "身份证类别代码的字典名称", column = "ID_NO_CSN")
	private String idNoCsn;

	@PropertyDesc(name = "身份证类别代码的字典版本", column = "ID_NO_CSV")
	private String idNoCsv;

	@PropertyDesc(name = "身份证类别代码的值", column = "ID_NO_DN")
	private String idNoDn;

	@PropertyDesc(name = "医疗保险-类别的字典代码", column = "MEDICARE_CS")
	private String medicareCs;

	@PropertyDesc(name = "医疗保险-类别的字典名称", column = "MEDICARE_CSN")
	private String medicareCsn;

	@PropertyDesc(name = "医疗保险-类别的字典版本", column = "MEDICARE_CSV")
	private String medicareCsv;

	@PropertyDesc(name = "医疗保险-类别的值", column = "MEDICARE_DN")
	private String medicareDn;

	@PropertyDesc(name = "新农合卡号", column = "NH_CARD")
	private String nhCard;

	@PropertyDesc(name = "社会保障卡号", column = "SSCID")
	private String sscid;

	@PropertyDesc(name = "职业类别的字典代码", column = "CARD_OCCU_TYPE_CS")
	private String cardOccuTypeCs;

	@PropertyDesc(name = "职业类别的字典名称", column = "CARD_OCCU_TYPE_CSN")
	private String cardOccuTypeCsn;

	@PropertyDesc(name = "职业类别的字典版本", column = "CARD_OCCU_TYPE_CSV")
	private String cardOccuTypeCsv;

	@PropertyDesc(name = "职业类别的值", column = "CARD_OCCU_TYPE_DN")
	private String cardOccuTypeDn;

	@PropertyDesc(name = "民族的字典代码", column = "CARD_NATION_CS")
	private String cardNationCs;

	@PropertyDesc(name = "民族的字典名称", column = "CARD_NATION_CSN")
	private String cardNationCsn;

	@PropertyDesc(name = "民族的字典版本", column = "CARD_NATION_CSV")
	private String cardNationCsv;

	@PropertyDesc(name = "民族的值", column = "CARD_NATION_DN")
	private String cardNationDn;

	@PropertyDesc(name = "国籍的字典代码", column = "NATIONALITY_CS")
	private String nationalityCs;

	@PropertyDesc(name = "国籍的字典名称", column = "NATIONALITY_CSN")
	private String nationalityCsn;

	@PropertyDesc(name = "国籍的字典版本", column = "NATIONALITY_CSV")
	private String nationalityCsv;

	@PropertyDesc(name = "国籍的值", column = "NATIONALITY_DN")
	private String nationalityDn;

	@PropertyDesc(name = "行政区划的字典代码", column = "AR_CS")
	private String arCs;

	@PropertyDesc(name = "行政区划的字典名称", column = "AR_CSN")
	private String arCsn;

	@PropertyDesc(name = "行政区划的字典版本", column = "AR_CSV")
	private String arCsv;

	@PropertyDesc(name = "行政区划的值", column = "AR_DN")
	private String arDn;

	@PropertyDesc(name = "地址类别的字典代码", column = "AD_CS")
	private String adCs;

	@PropertyDesc(name = "地址类别的字典名称", column = "AD_CSN")
	private String adCsn;

	@PropertyDesc(name = "地址类别的字典版本", column = "AD_CSV")
	private String adCsv;

	@PropertyDesc(name = "地址类别的值", column = "AD_DN")
	private String adDn;

	@PropertyDesc(name = "联系电话类别的字典代码", column = "TEL_TYPE_CS")
	private String telTypeCs;

	@PropertyDesc(name = "联系电话类别的字典名称", column = "TEL_TYPE_CSN")
	private String telTypeCsn;

	@PropertyDesc(name = "联系电话类别的字典版本", column = "TEL_TYPE_CSV")
	private String telTypeCsv;

	@PropertyDesc(name = "联系电话类别的值", column = "TEL_TYPE_DN")
	private String telTypeDn;

	@PropertyDesc(name = "联系人关系的字典代码", column = "LINKMAN_REL_CS")
	private String linkmanRelCs;

	@PropertyDesc(name = "联系人关系的字典名称", column = "LINKMAN_REL_CSN")
	private String linkmanRelCsn;

	@PropertyDesc(name = "联系人关系的字典版本", column = "LINKMAN_REL_CSV")
	private String linkmanRelCsv;

	@PropertyDesc(name = "联系人关系的值", column = "LINKMAN_REL_DN")
	private String linkmanRelDn;

	@PropertyDesc(name = "联系人身份证件类别的字典代码", column = "LM_ID_NO_CS")
	private String lmIdNoCs;

	@PropertyDesc(name = "联系人身份证件类别的字典名称", column = "LM_ID_NO_CSN")
	private String lmIdNoCsn;

	@PropertyDesc(name = "联系人身份证件类别的字典版本", column = "LM_ID_NO_CSV")
	private String lmIdNoCsv;

	@PropertyDesc(name = "联系人身份证件类别的值", column = "LM_ID_NO_DN")
	private String lmIdNoDn;

	@PropertyDesc(name = "ABO血型的字典代码", column = "ABO_CS")
	private String aboCs;

	@PropertyDesc(name = "ABO血型的字典名称", column = "ABO_CSN")
	private String aboCsn;

	@PropertyDesc(name = "ABO血型的字典版本", column = "ABO_CSV")
	private String aboCsv;

	@PropertyDesc(name = "ABO血型的值", column = "ABO_DN")
	private String aboDn;

	@PropertyDesc(name = "RH血型的字典代码", column = "RH_CS")
	private String rhCs;

	@PropertyDesc(name = "RH血型的字典名称", column = "RH_CSN")
	private String rhCsn;

	@PropertyDesc(name = "RH血型的字典版本", column = "RH_CSV")
	private String rhCsv;

	@PropertyDesc(name = "RH血型的值", column = "RH_DN")
	private String rhDn;

	@PropertyDesc(name = "学历代码的字典代码", column = "CARD_ED_BG_CS")
	private String cardEdBgCs;

	@PropertyDesc(name = "学历代码的字典名称", column = "CARD_ED_BG_CSN")
	private String cardEdBgCsn;

	@PropertyDesc(name = "学历代码的字典版本", column = "CARD_ED_BG_CSV")
	private String cardEdBgCsv;

	@PropertyDesc(name = "学历代码的值", column = "CARD_ED_BG_DN")
	private String cardEdBgDn;

	@PropertyDesc(name = "出生地的字典代码", column = "BIRTH_PLACE_CS")
	private String birthPlaceCs;

	@PropertyDesc(name = "出生地的字典名称", column = "BIRTH_PLACE_CSN")
	private String birthPlaceCsn;

	@PropertyDesc(name = "出生地的字典版本", column = "BIRTH_PLACE_CSV")
	private String birthPlaceCsv;

	@PropertyDesc(name = "出生地的值", column = "BIRTH_PLACE_DN")
	private String birthPlaceDn;

	@PropertyDesc(name = "籍贯-省取值", column = "NATIVE_PROVINCE")
	private String nativeProvince;

	@PropertyDesc(name = "籍贯-省的字典代码", column = "NATIVE_PROVINCE_CS")
	private String nativeProvinceCs;

	@PropertyDesc(name = "籍贯-省的字典名称", column = "NATIVE_PROVINCE_CSN")
	private String nativeProvinceCsn;

	@PropertyDesc(name = "籍贯-省的字典版本", column = "NATIVE_PROVINCE_CSV")
	private String nativeProvinceCsv;

	@PropertyDesc(name = "籍贯-省的值", column = "NATIVE_PROVINCE_DN")
	private String nativeProvinceDn;

	@PropertyDesc(name = "籍贯-市的字典代码", column = "NATIVE_CITY_CS")
	private String nativeCityCs;

	@PropertyDesc(name = "籍贯-市的字典名称", column = "NATIVE_CITY_CSN")
	private String nativeCityCsn;

	@PropertyDesc(name = "籍贯-市的字典版本", column = "NATIVE_CITY_CSV")
	private String nativeCityCsv;

	@PropertyDesc(name = "籍贯-市的值", column = "NATIVE_CITY_DN")
	private String nativeCityDn;

	@PropertyDesc(name = "退伍军人标志的值", column = "VETERANS_MILITARY_VAULE")
	private String veteransMilitaryValue;

	@PropertyDesc(name = "退伍军人标志的字典代码", column = "VETERANS_MILITARY_MARK_CS")
	private String veteransMilitaryMarkCs;

	@PropertyDesc(name = "退伍军人标志的字典名称", column = "VETERANS_MILITARY_MARK_CSN")
	private String veteransMilitaryMarkCsn;

	@PropertyDesc(name = "退伍军人标志的字典版本", column = "VETERANS_MILITARY_MARK_CSV")
	private String veteransMilitaryMarkCsv;

	@PropertyDesc(name = "卡类型的字典代码", column = "CARD_CS")
	private String cardCs;

	@PropertyDesc(name = "卡类型的字典名称", column = "CARD_CSN")
	private String cardCsn;

	@PropertyDesc(name = "卡类型的字典版本", column = "CARD_CSV")
	private String cardCsv;

	@PropertyDesc(name = "卡类型的值", column = "CARD_DN")
	private String cardDn;

	@PropertyDesc(name = "发卡地区的字典代码", column = "CARD_AREA_CS")
	private String cardAreaCs;

	@PropertyDesc(name = "发卡地区的字典名称", column = "CARD_AREA_CSN")
	private String cardAreaCsn;

	@PropertyDesc(name = "发卡地区的字典版本", column = "CARD_AREA_CSV")
	private String cardAreaCsv;

	@PropertyDesc(name = "发卡地区的值", column = "CARD_AREA_DN")
	private String cardAreaDn;

	@PropertyDesc(name = "患者类型的值", column = "PATIENT_TYPE_VALUE")
	private String patientTypeValue;

	@PropertyDesc(name = "患者类型的字典代码", column = "PATIENT_TYPE_CS")
	private String patientTypeCs;

	@PropertyDesc(name = "患者类型的字典名称", column = "PATIENT_TYPE_CSN")
	private String patientTypeCsn;

	@PropertyDesc(name = "患者类型的字典版本", column = "PATIENT_TYPE_CSV")
	private String patientTypeCsv;

	@PropertyDesc(name = "患者就诊类别描述", column = "PATIENT_TYPE_DESCR")
	private String patientTypeDescr;

	@PropertyDesc(name = "工作单位电话号码", column = "WORKING_TEL_NO")
	private String workingTelNo;
	@PropertyDesc(name = "医疗待遇的字典代码", column = "MEDICAL_TREATMENT_CS")
	private String medicalTreatmentCs;
	@PropertyDesc(name = "医疗待遇的字典名称", column = "MEDICAL_TREATMENT_CSN")
	private String medicalTreatmentCsn;
	@PropertyDesc(name = "医疗待遇的字典版本", column = "MEDICAL_TREATMENT_CSV")
	private String medicalTreatmentCsv;
	@PropertyDesc(name = "版本号", column = "VERSION_NUM")
	private String versionNum;
	@PropertyDesc(name = "责任医师代码", column = "RESPONS_DOC_CODE")
	private String responsDocCode;
	@PropertyDesc(name = "责任医师姓名", column = "RESPONS_DOC_NAME")
	private String responsDocName;
	@PropertyDesc(name = "其他职业描述", column = "OTHER_OCCC_DESCR")
	private String otherOcccDescr;
	@PropertyDesc(name = "常住地址户籍标志", column = "USUAL_TYPE_MARK")
	private Short usualTypeMark;
	@PropertyDesc(name = "籍贯—市取值", column = "NATIVE_CITY")
	private String nativeCity;
	@PropertyDesc(name = "病人 ID", column = "PATIENT_ID")
	private String patientId;
	@PropertyDesc(name = "药物过敏史标志", column = "DRUG_ALLERGY_MARK")
	private String drugAllergyMark;
	@PropertyDesc(name = "手术史标志", column = "OP_HISTORY_MARK")
	private String opHistoryMark;
	@PropertyDesc(name = "外伤史标志", column = "TRAUMA_HISTORY_MARK")
	private String traumaHistoryMark;
	@PropertyDesc(name = "输血史标志", column = "BLOOD_TRANSF_MARK")
	private String bloodTransfMark;
	@PropertyDesc(name = "残疾标志", column = "DISABILITY_MARK")
	private String disabilityMark;
	@PropertyDesc(name = "遗传性疾病史", column = "GENETIC_DISEASE_HISTORY")
	private String geneticDiseaseHistory;
	@PropertyDesc(name = "家庭厨房排风设施标志", column = "EXHAUST_FACILITY_MARK")
	private String exhaustFacilityMark;
	@PropertyDesc(name = "家庭厨房排风设施类别代码", column = "EXHAUST_FACILITY_TYPE_CODE")
	private String exhaustFacilityTypeCode;
	@PropertyDesc(name = "家庭燃料类型类别代码", column = "FUEL_TYPE_CODE")
	private String fuelTypeCode;
	@PropertyDesc(name = "家庭饮水类别代码", column = "WATER_TYPE_CODE")
	private String waterTypeCode;
	@PropertyDesc(name = "家庭厕所类别代码", column = "TOILET_TYPE_CODE")
	private String toiletTypeCode;
	@PropertyDesc(name = "家庭禽畜栏类别代码", column = "LIVESTOCK_PEN_TYPE_CODE")
	private String livestockPenTypeCode;
	@PropertyDesc(name = "手术史", column = "OPERATION_HISTORY")
	private String operationHistory;
	@PropertyDesc(name = "哮喘标志", column = "ASTHMA_MARK")
	private String asthmaMark;
	@PropertyDesc(name = "心脏病标志", column = "HEDRT_DIS_MARK")
	private String hedrtDisMark;
	@PropertyDesc(name = "心脑血管病标志", column = "CARDIOVASCULAR_CODE")
	private String cardiovascularCode;
	@PropertyDesc(name = "癫痫病标志", column = "EPILEPSY_MARK")
	private String epilepsyMark;
	@PropertyDesc(name = "凝血紊乱标志", column = "COAGULOPATHY_MARK")
	private String coagulopathyMark;
	@PropertyDesc(name = "糖尿病标志", column = "DIABETES_MARK")
	private String diabetesMark;
	@PropertyDesc(name = "青光眼标志", column = "GLAUCOMA_MARK")
	private String glaucomaMark;
	@PropertyDesc(name = "透析标志", column = "DIALYSIS_MARK")
	private String dialysisMark;
	@PropertyDesc(name = "器官移植标志", column = "ORGAN_TRANS_MARK")
	private String organTransMark;
	@PropertyDesc(name = "器官缺失标志", column = "ORGAN_DEFECT_MARK")
	private String organDefectMark;
	@PropertyDesc(name = "可装卸的义肢标志", column = "REMOVA_PRO_MARK")
	private String removaProMark;
	@PropertyDesc(name = "心脏起搏器标志", column = "CARDIAC_PAC_MARK")
	private String cardiacPacMark;
	@PropertyDesc(name = "其他医学警示名称", column = "ORTHER_MEDICAL_ALERT")
	private String ortherMedicalAlert;
	@PropertyDesc(name = "精神病标志", column = "PSYCHIATRIC_MARK")
	private String psychiatricMark;
	/* 从姓名到精神病标志与mpiinfo信息一致<<< */

	@PropertyDesc(name = "备注", column = "REMARK")
	private String remark;

	@PropertyDesc(name = "数据源级别", column = "SRC_LEVEL")
	private Short srcLevel;
	@PropertyDesc(name = "数据源类型", column = "TYPE")
	private String type;
	@PropertyDesc(name = "原始主键", column = "RELATION_PK")
	private String relationPk;
	@PropertyDesc(name = "关联标识", column = "RELATION_TYPE")
	private String relationType;

	private Date dtCreate;

	// 扩展
	@Transient
	private String domainId;
	@Transient
	private String uniqueSign;
	@Transient
	private String domainDesc;

	public String getFieldPk() {
		return fieldPk;
	}

	public void setFieldPk(String fieldPk) {
		this.fieldPk = fieldPk == null ? null : fieldPk.trim();
	}

	public String getRegisterOrgCode() {
		return registerOrgCode;
	}

	public void setRegisterOrgCode(String registerOrgCode) {
		this.registerOrgCode = registerOrgCode == null ? null : registerOrgCode.trim();
	}

	public String getRegisterOrgName() {
		return registerOrgName;
	}

	public void setRegisterOrgName(String registerOrgName) {
		this.registerOrgName = registerOrgName == null ? null : registerOrgName.trim();
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
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

	public Short getSrcLevel() {
		return srcLevel;
	}

	public void setSrcLevel(Short srcLevel) {
		this.srcLevel = srcLevel;
	}

	public String getRelationPk() {
		return relationPk;
	}

	public void setRelationPk(String relationPk) {
		this.relationPk = relationPk == null ? null : relationPk.trim();
	}

	public String getRelationType() {
		return relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType == null ? null : relationType.trim();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public Date getDtCreate() {
		return dtCreate;
	}

	public void setDtCreate(Date dtCreate) {
		this.dtCreate = dtCreate;
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

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getUniqueSign() {
		return uniqueSign;
	}

	public void setUniqueSign(String uniqueSign) {
		this.uniqueSign = uniqueSign;
	}

	public String getDomainDesc() {
		return domainDesc;
	}

	public void setDomainDesc(String domainDesc) {
		this.domainDesc = domainDesc;
	}

	/**
	 * 获取唯一标识信息
	 * 
	 * @return
	 */
	public String getIdentifier() {
		if (PersonType.PERSONAL.getCode().equals(this.type)) {// 个人
			return this.hrId;
		} else if (PersonType.PATIENT.getCode().equals(this.type)) {// 患者
			return this.medicalserviceNo;
		} else {// 其他
			return this.patientId;
		}
	}

	public PersonIndex toPersonIndex() {
		PersonIndex index = new PersonIndex();
		BeanUtils.copyProperties(this, index);
		return index;
	}

	public PersonInfoHistory toPersonInfoHistory() {
		PersonInfoHistory his = new PersonInfoHistory();
		BeanUtils.copyProperties(this, his);
		return his;
	}

}
