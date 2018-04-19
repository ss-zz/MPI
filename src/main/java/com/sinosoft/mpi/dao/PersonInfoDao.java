package com.sinosoft.mpi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sinosoft.mpi.model.PersonInfo;

@Repository("personInfoDao")
public class PersonInfoDao implements IPersonInfoDao {
	@Resource
	private JdbcTemplate jdbcTemplate;
	static final String PERSONTABLE = "mpi_person_info";
	static final String PERSONTABLEHISTORY = "mpi_personinfo_history";

	@Override
	public void add(final PersonInfo entity) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into ").append(PERSONTABLE).append("( register_org_code,").append("register_org_name,")
				.append("medicalservice_no,").append("name_cn,").append("name_en,").append("remark,")
				.append("birth_date,").append("gender_cd,").append("card_marital_st_cd,").append("id_no_cd,")
				.append("id_no,").append("medicare_cd,").append("medical_insurance_no,").append("card_occu_type_cd,")
				.append("card_nation_cd,").append("nationality_cd,").append("ar_cd,").append("ad_cd,")
				.append("province_name,").append("city_name,").append("area_name,").append("street_id,")
				.append("village_name,").append("house_no,").append("tel_type_cd,").append("person_tel_no,")
				.append("linkman_name,").append("linkman_rel_cd,").append("lm_id_no_cd,").append("lm_id_no,")
				.append("hr_id,").append("post_cd,").append("abo_cd,").append("rh_cd,").append("rh_neg_cd,")
				.append("card_ed_bg_cd,").append("birth_place,").append("working_unit_name,").append("email_ad,")
				.append("veterans_military_mark,").append("card_no,").append("card_cd,").append("card_area,")
				.append("patient_type,").append("m_phone_num,").append("working_unit_post,")
				.append("working_unit_addr,").append("living_addr,").append("rpr_addr,").append("rpr_post,")
				.append("rpr_phone,").append("linkman_addr,").append("linkman_post,").append("linkman_phone,")
				.append("insure_type,").append("medical_treatment_name,").append("medical_treatment_cd,")
				.append("register_date,").append("recorder_name,").append("register_person_code,").append("send_time,")
				.append("send_org_code,").append("send_system,").append("provider_name,").append("provider_org_code,")
				.append("createtime,").append("lastuptime,").append("state,").append("field_pk,").append("gender_cs,")
				.append("gender_csn,").append("gender_csv,").append("gender_dn,").append("card_marital_st_cs,")
				.append("card_marital_st_csn,").append("card_marital_st_csv,").append("card_marital_st_dn,")
				.append("id_no_cs,").append("id_no_csn,").append("id_no_csv,").append("id_no_dn,")
				.append("medicare_cs,").append("medicare_csn,").append("medicare_csv,").append("medicare_dn,")
				.append("nh_card,").append("sscid,").append("card_occu_type_cs,").append("card_occu_type_csn,")
				.append("card_occu_type_csv,").append("card_occu_type_dn,").append("card_nation_cs,")
				.append("card_nation_csn,").append("card_nation_csv,").append("card_nation_dn,")
				.append("nationality_cs,").append("nationality_csn,").append("nationality_csv,")
				.append("nationality_dn,").append("ar_cs,").append("ar_csn,").append("ar_csv,").append("ar_dn,")
				.append("ad_cs,").append("ad_csn,").append("ad_csv,").append("ad_dn,").append("tel_type_cs,")
				.append("tel_type_csn,").append("tel_type_csv,").append("tel_type_dn,").append("linkman_rel_cs,")
				.append("linkman_rel_csn,").append("linkman_rel_csv,").append("linkman_rel_dn,").append("lm_id_no_cs,")
				.append("lm_id_no_csn,").append("lm_id_no_csv,").append("lm_id_no_dn,").append("abo_cs,")
				.append("abo_csn,").append("abo_csv,").append("abo_dn,").append("rh_cs,").append("rh_csn,")
				.append("rh_csv,").append("rh_dn,").append("card_ed_bg_cs,").append("card_ed_bg_csn,")
				.append("card_ed_bg_csv,").append("card_ed_bg_dn,").append("birth_place_cs,").append("birth_place_csn,")
				.append("birth_place_csv,").append("birth_place_dn,").append("native_province,")
				.append("native_province_cs,").append("native_province_csn,").append("native_province_csv,")
				.append("native_province_dn,").append("native_city_cs,").append("native_city_csn,")
				.append("native_city_csv,").append("native_city_dn,").append("VETERANS_MILITARY_VALUE,")
				.append("veterans_military_mark_cs,").append("veterans_military_mark_csn,")
				.append("veterans_military_mark_csv,").append("card_cs,").append("card_csn,").append("card_csv,")
				.append("card_dn,").append("card_area_cs,").append("card_area_csn,").append("card_area_csv,")
				.append("card_area_dn,").append("PATIENT_TYPE_VALUE,").append("patient_type_cs,")
				.append("patient_type_csn,").append("patient_type_csv,").append("patient_type_descr,")
				.append("working_tel_no,").append("medical_treatment_cs,").append("medical_treatment_csn,")
				.append("medical_treatment_csv,").append("version_num,").append("respons_doc_code,")
				.append("respons_doc_name,").append("other_occc_descr,").append("usual_type_mark,").append("src_level,")
				.append("type,").append("relation_pk,").append("relation_type,")
				// updated WHN 20170301
				.append("patient_id,").append("drug_allergy_mark,").append("op_history_mark,")
				.append("trauma_history_mark,").append("blood_transf_mark,").append("disability_mark,")
				.append("genetic_disease_history,").append("exhaust_facility_mark,")
				.append("exhaust_facility_type_code,").append("fuel_type_code,").append("water_type_code,")
				.append("toilet_type_code,").append("livestock_pen_type_code,").append("operation_history,")
				.append("asthma_mark,").append("hedrt_dis_mark,").append("cardiovascular_code,")
				.append("epilepsy_mark,").append("coagulopathy_mark,").append("diabetes_mark,").append("glaucoma_mark,")
				.append("dialysis_mark,").append("organ_trans_mark,").append("organ_defect_mark,")
				.append("remova_pro_mark,").append("cardiac_pac_mark,").append("orther_medical_alert,")
				.append("psychiatric_mark )")
				.append("values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," + "?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getREGISTER_ORG_CODE());
				ps.setString(2, entity.getREGISTER_ORG_NAME());
				ps.setString(3, entity.getMEDICALSERVICE_NO());
				ps.setString(4, entity.getNAME_CN());
				ps.setString(5, entity.getNAME_EN());
				ps.setString(6, entity.getREMARK());
				/*
				 * ps.setDate(7, new java.sql.Date(entity.getBIRTH_DATE() .getTime()));
				 */
				ps.setDate(7,
						entity.getBIRTH_DATE() == null ? null : new java.sql.Date(entity.getBIRTH_DATE().getTime()));
				ps.setString(8, entity.getGENDER_CD());
				ps.setString(9, entity.getCARD_MARITAL_ST_CD());
				ps.setString(10, entity.getID_NO_CD());
				ps.setString(11, entity.getID_NO());
				ps.setString(12, entity.getMEDICARE_CD());
				ps.setString(13, entity.getMEDICAL_INSURANCE_NO());
				ps.setString(14, entity.getCARD_OCCU_TYPE_CD());
				ps.setString(15, entity.getCARD_NATION_CD());
				ps.setString(16, entity.getNATIONALITY_CD());
				ps.setString(17, entity.getAR_CD());
				ps.setString(18, entity.getAD_CD());
				ps.setString(19, entity.getPROVINCE_NAME());
				ps.setString(20, entity.getCITY_NAME());
				ps.setString(21, entity.getAREA_NAME());
				ps.setString(22, entity.getSTREET_ID());
				ps.setString(23, entity.getVILLAGE_NAME());
				ps.setString(24, entity.getHOUSE_NO());
				ps.setString(25, entity.getTEL_TYPE_CD());
				ps.setString(26, entity.getPERSON_TEL_NO());
				ps.setString(27, entity.getLINKMAN_NAME());
				ps.setString(28, entity.getLINKMAN_REL_CD());
				ps.setString(29, entity.getLM_ID_NO_CD());
				ps.setString(30, entity.getLM_ID_NO());
				ps.setString(31, entity.getHR_ID());
				ps.setString(32, entity.getPOST_CD());
				ps.setString(33, entity.getABO_CD());
				ps.setString(34, entity.getRH_CD());
				ps.setString(35, entity.getRH_NEG_CD());
				ps.setString(36, entity.getCARD_ED_BG_CD());
				ps.setString(37, entity.getBIRTH_PLACE());
				ps.setString(38, entity.getWORKING_UNIT_NAME());
				ps.setString(39, entity.getEMAIL_AD());
				ps.setShort(40, entity.getVETERANS_MILITARY_MARK() == null ? 0 : entity.getVETERANS_MILITARY_MARK());
				ps.setString(41, entity.getCARD_NO());
				ps.setString(42, entity.getCARD_CD());
				ps.setString(43, entity.getCARD_AREA());
				ps.setString(44, entity.getPATIENT_TYPE());
				ps.setString(45, entity.getM_PHONE_NUM());
				ps.setString(46, entity.getWORKING_UNIT_POST());
				ps.setString(47, entity.getWORKING_UNIT_ADDR());
				ps.setString(48, entity.getLIVING_ADDR());
				ps.setString(49, entity.getRPR_ADDR());
				ps.setString(50, entity.getRPR_POST());
				ps.setString(51, entity.getRPR_PHONE());
				ps.setString(52, entity.getLINKMAN_ADDR());
				ps.setString(53, entity.getLINKMAN_POST());
				ps.setString(54, entity.getLINKMAN_PHONE());
				ps.setString(55, entity.getINSURE_TYPE());
				ps.setString(56, entity.getMEDICAL_TREATMENT_NAME());
				ps.setString(57, entity.getMEDICAL_TREATMENT_CD());
				ps.setDate(58, entity.getREGISTER_DATE() == null ? null
						: new java.sql.Date(entity.getREGISTER_DATE().getTime()));
				ps.setString(59, entity.getRECORDER_NAME());
				ps.setString(60, entity.getREGISTER_PERSON_CODE());
				ps.setDate(61,
						entity.getSEND_TIME() == null ? null : new java.sql.Date(entity.getSEND_TIME().getTime()));
				ps.setString(62, entity.getSEND_ORG_CODE());
				ps.setString(63, entity.getSEND_SYSTEM());
				ps.setString(64, entity.getPROVIDER_NAME());
				ps.setString(65, entity.getPROVIDER_ORG_CODE());
				ps.setDate(66,
						entity.getCREATETIME() == null ? null : new java.sql.Date(entity.getCREATETIME().getTime()));
				ps.setDate(67,
						entity.getLASTUPTIME() == null ? null : new java.sql.Date(entity.getLASTUPTIME().getTime()));
				ps.setShort(68, entity.getSTATE());
				ps.setString(69, entity.getFIELD_PK());
				ps.setString(70, entity.getGENDER_CS());
				ps.setString(71, entity.getGENDER_CSN());
				ps.setString(72, entity.getGENDER_CSV());
				ps.setString(73, entity.getGENDER_DN());
				ps.setString(74, entity.getCARD_MARITAL_ST_CS());
				ps.setString(75, entity.getCARD_MARITAL_ST_CSN());
				ps.setString(76, entity.getCARD_MARITAL_ST_CSV());
				ps.setString(77, entity.getCARD_MARITAL_ST_DN());
				ps.setString(78, entity.getID_NO_CS());
				ps.setString(79, entity.getID_NO_CSN());
				ps.setString(80, entity.getID_NO_CSV());
				ps.setString(81, entity.getID_NO_DN());
				ps.setString(82, entity.getMEDICARE_CS());
				ps.setString(83, entity.getMEDICARE_CSN());
				ps.setString(84, entity.getMEDICARE_CSV());
				ps.setString(85, entity.getMEDICARE_DN());
				ps.setString(86, entity.getNH_CARD());
				ps.setString(87, entity.getSSCID());
				ps.setString(88, entity.getCARD_OCCU_TYPE_CS());
				ps.setString(89, entity.getCARD_OCCU_TYPE_CSN());
				ps.setString(90, entity.getCARD_OCCU_TYPE_CSV());
				ps.setString(91, entity.getCARD_OCCU_TYPE_DN());
				ps.setString(92, entity.getCARD_NATION_CS());
				ps.setString(93, entity.getCARD_NATION_CSN());
				ps.setString(94, entity.getCARD_NATION_CSV());
				ps.setString(95, entity.getCARD_NATION_DN());
				ps.setString(96, entity.getNATIONALITY_CS());
				ps.setString(97, entity.getNATIONALITY_CSN());
				ps.setString(98, entity.getNATIONALITY_CSV());
				ps.setString(99, entity.getNATIONALITY_DN());
				ps.setString(100, entity.getAR_CS());
				ps.setString(101, entity.getAR_CSN());
				ps.setString(102, entity.getAR_CSV());
				ps.setString(103, entity.getAR_DN());
				ps.setString(104, entity.getAD_CS());
				ps.setString(105, entity.getAD_CSN());
				ps.setString(106, entity.getAD_CSV());
				ps.setString(107, entity.getAD_DN());
				ps.setString(108, entity.getTEL_TYPE_CS());
				ps.setString(109, entity.getTEL_TYPE_CSN());
				ps.setString(110, entity.getTEL_TYPE_CSV());
				ps.setString(111, entity.getTEL_TYPE_DN());
				ps.setString(112, entity.getLINKMAN_REL_CS());
				ps.setString(113, entity.getLINKMAN_REL_CSN());
				ps.setString(114, entity.getLINKMAN_REL_CSV());
				ps.setString(115, entity.getLINKMAN_REL_DN());
				ps.setString(116, entity.getLM_ID_NO_CS());
				ps.setString(117, entity.getLM_ID_NO_CSN());
				ps.setString(118, entity.getLM_ID_NO_CSV());
				ps.setString(119, entity.getLM_ID_NO_DN());
				ps.setString(120, entity.getABO_CS());
				ps.setString(121, entity.getABO_CSN());
				ps.setString(122, entity.getABO_CSV());
				ps.setString(123, entity.getABO_DN());
				ps.setString(124, entity.getRH_CS());
				ps.setString(125, entity.getRH_CSN());
				ps.setString(126, entity.getRH_CSV());
				ps.setString(127, entity.getRH_DN());
				ps.setString(128, entity.getCARD_ED_BG_CS());
				ps.setString(129, entity.getCARD_ED_BG_CSN());
				ps.setString(130, entity.getCARD_ED_BG_CSV());
				ps.setString(131, entity.getCARD_ED_BG_DN());
				ps.setString(132, entity.getBIRTH_PLACE_CS());
				ps.setString(133, entity.getBIRTH_PLACE_CSN());
				ps.setString(134, entity.getBIRTH_PLACE_CSV());
				ps.setString(135, entity.getBIRTH_PLACE_DN());
				ps.setString(136, entity.getNATIVE_PROVINCE());
				ps.setString(137, entity.getNATIVE_PROVINCE_CS());
				ps.setString(138, entity.getNATIVE_PROVINCE_CSN());
				ps.setString(139, entity.getNATIVE_PROVINCE_CSV());
				ps.setString(140, entity.getNATIVE_PROVINCE_DN());
				ps.setString(141, entity.getNATIVE_CITY_CS());
				ps.setString(142, entity.getNATIVE_CITY_CSN());
				ps.setString(143, entity.getNATIVE_CITY_CSV());
				ps.setString(144, entity.getNATIVE_CITY_DN());
				ps.setString(145, entity.getVETERANS_MILITARY_VALUE());
				ps.setString(146, entity.getVETERANS_MILITARY_MARK_CS());
				ps.setString(147, entity.getVETERANS_MILITARY_MARK_CSN());
				ps.setString(148, entity.getVETERANS_MILITARY_MARK_CSV());
				ps.setString(149, entity.getCARD_CS());
				ps.setString(150, entity.getCARD_CSN());
				ps.setString(151, entity.getCARD_CSV());
				ps.setString(152, entity.getCARD_DN());
				ps.setString(153, entity.getCARD_AREA_CS());
				ps.setString(154, entity.getCARD_AREA_CSN());
				ps.setString(155, entity.getCARD_AREA_CSV());
				ps.setString(156, entity.getCARD_AREA_DN());
				ps.setString(157, entity.getPATIENT_TYPE_VALUE());
				ps.setString(158, entity.getPATIENT_TYPE_CS());
				ps.setString(159, entity.getPATIENT_TYPE_CSN());
				ps.setString(160, entity.getPATIENT_TYPE_CSV());
				ps.setString(161, entity.getPATIENT_TYPE_DESCR());
				ps.setString(162, entity.getWORKING_TEL_NO());
				ps.setString(163, entity.getMEDICAL_TREATMENT_CS());
				ps.setString(164, entity.getMEDICAL_TREATMENT_CSN());
				ps.setString(165, entity.getMEDICAL_TREATMENT_CSV());
				ps.setString(166, entity.getVERSION_NUM());
				ps.setString(167, entity.getRESPONS_DOC_CODE());
				ps.setString(168, entity.getRESPONS_DOC_NAME());
				ps.setString(169, entity.getOTHER_OCCC_DESCR());
				ps.setShort(170, entity.getUSUAL_TYPE_MARK() == null ? 0 : entity.getUSUAL_TYPE_MARK());
				ps.setShort(171, entity.getSRC_LEVEL());
				ps.setString(172, entity.getTYPE());
				ps.setString(173, entity.getRELATION_PK());
				ps.setString(174, entity.getRELATION_TYPE());
				// updated WHN 20170301
				ps.setString(175, entity.getPATIENT_ID());
				ps.setString(176, entity.getDRUG_ALLERGY_MARK());
				ps.setString(177, entity.getOP_HISTORY_MARK());
				ps.setString(178, entity.getTRAUMA_HISTORY_MARK());
				ps.setString(179, entity.getBLOOD_TRANSF_MARK());
				ps.setString(180, entity.getDISABILITY_MARK());
				ps.setString(181, entity.getGENETIC_DISEASE_HISTORY());
				ps.setString(182, entity.getEXHAUST_FACILITY_MARK());
				ps.setString(183, entity.getEXHAUST_FACILITY_TYPE_CODE());
				ps.setString(184, entity.getFUEL_TYPE_CODE());
				ps.setString(185, entity.getWATER_TYPE_CODE());
				ps.setString(186, entity.getTOILET_TYPE_CODE());
				ps.setString(187, entity.getLIVESTOCK_PEN_TYPE_CODE());
				ps.setString(188, entity.getOPERATION_HISTORY());
				ps.setString(189, entity.getASTHMA_MARK());
				ps.setString(190, entity.getHEDRT_DIS_MARK());
				ps.setString(191, entity.getCARDIOVASCULAR_CODE());
				ps.setString(192, entity.getEPILEPSY_MARK());
				ps.setString(193, entity.getCOAGULOPATHY_MARK());
				ps.setString(194, entity.getDIABETES_MARK());
				ps.setString(195, entity.getGLAUCOMA_MARK());
				ps.setString(196, entity.getDIALYSIS_MARK());
				ps.setString(197, entity.getORGAN_TRANS_MARK());
				ps.setString(198, entity.getORGAN_DEFECT_MARK());
				ps.setString(199, entity.getREMOVA_PRO_MARK());
				ps.setString(200, entity.getCARDIAC_PAC_MARK());
				ps.setString(201, entity.getORTHER_MEDICAL_ALERT());
				ps.setString(202, entity.getPSYCHIATRIC_MARK());
			}
		});
	}

	@Override
	public void deleteById(final PersonInfo entity) {
		String sql = " delete from mpi_person_info where FIELD_PK = ?";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getFIELD_PK());
			}
		});

	}

	@Override
	public void update(final PersonInfo entity) {
		if (entity == null || entity.getFIELD_PK() == null) {
			return;
		}
		// lpk update 2013年4月17日18:25:04
		StringBuilder sql = new StringBuilder();
		sql.append(" update mpi_person_info set ").append("REGISTER_ORG_CODE=?,").append("REGISTER_ORG_NAME=?,")
				.append("MEDICALSERVICE_NO=?,").append("NAME_CN=?,").append("NAME_EN=?,").append("REMARK=?,")
				.append("BIRTH_DATE=?,").append("GENDER_CD=?,").append("CARD_MARITAL_ST_CD=?,").append("ID_NO_CD=?,")
				.append("ID_NO=?,").append("MEDICARE_CD=?,").append("MEDICAL_INSURANCE_NO=?,")
				.append("CARD_OCCU_TYPE_CD=?,").append("CARD_NATION_CD=?,").append("NATIONALITY_CD=?,")
				.append("AR_CD=?,").append("AD_CD=?,").append("PROVINCE_NAME=?,").append("CITY_NAME=?,")
				.append("AREA_NAME=?,").append("STREET_ID=?,").append("VILLAGE_NAME=?,").append("HOUSE_NO=?,")
				.append("TEL_TYPE_CD=?,").append("PERSON_TEL_NO=?,").append("LINKMAN_NAME=?,")
				.append("LINKMAN_REL_CD=?,").append("LM_ID_NO_CD=?,").append("LM_ID_NO=?,").append("HR_ID=?,")
				.append("POST_CD=?,").append("ABO_CD=?,").append("RH_CD=?,").append("RH_NEG_CD=?,")
				.append("CARD_ED_BG_CD=?,").append("BIRTH_PLACE=?,").append("WORKING_UNIT_NAME=?,")
				.append("EMAIL_AD=?,").append("VETERANS_MILITARY_MARK=?,").append("CARD_NO=?,").append("CARD_CD=?,")
				.append("CARD_AREA=?,").append("PATIENT_TYPE=?,").append("M_PHONE_NUM=?,")
				.append("WORKING_UNIT_POST=?,").append("WORKING_UNIT_ADDR=?,").append("LIVING_ADDR=?,")
				.append("RPR_ADDR=?,").append("RPR_POST=?,").append("RPR_PHONE=?,").append("LINKMAN_ADDR=?,")
				.append("LINKMAN_POST=?,").append("LINKMAN_PHONE=?,").append("INSURE_TYPE=?,")
				.append("MEDICAL_TREATMENT_NAME=?,").append("MEDICAL_TREATMENT_CD=?,").append("REGISTER_DATE=?,")
				.append("RECORDER_NAME=?,").append("REGISTER_PERSON_CODE=?,").append("SEND_TIME=?,")
				.append("SEND_ORG_CODE=?,").append("SEND_SYSTEM=?,").append("PROVIDER_NAME=?,")
				.append("PROVIDER_ORG_CODE=?,").append("CREATETIME=?,").append("LASTUPTIME=?,").append("STATE=?,")
				.append("GENDER_CS=?,").append("GENDER_CSN=?,").append("GENDER_CSV=?,").append("GENDER_DN=?,")
				.append("CARD_MARITAL_ST_CS=?,").append("CARD_MARITAL_ST_CSN=?,").append("CARD_MARITAL_ST_CSV=?,")
				.append("CARD_MARITAL_ST_DN=?,").append("ID_NO_CS=?,").append("ID_NO_CSN=?,").append("ID_NO_CSV=?,")
				.append("ID_NO_DN=?,").append("MEDICARE_CS=?,").append("MEDICARE_CSN=?,").append("MEDICARE_CSV=?,")
				.append("MEDICARE_DN=?,").append("NH_CARD=?,").append("SSCID=?,").append("CARD_OCCU_TYPE_CS=?,")
				.append("CARD_OCCU_TYPE_CSN=?,").append("CARD_OCCU_TYPE_CSV=?,").append("CARD_OCCU_TYPE_DN=?,")
				.append("CARD_NATION_CS=?,").append("CARD_NATION_CSN=?,").append("CARD_NATION_CSV=?,")
				.append("CARD_NATION_DN=?,").append("NATIONALITY_CS=?,").append("NATIONALITY_CSN=?,")
				.append("NATIONALITY_CSV=?,").append("NATIONALITY_DN=?,").append("AR_CS=?,").append("AR_CSN=?,")
				.append("AR_CSV=?,").append("AR_DN=?,").append("AD_CS=?,").append("AD_CSN=?,").append("AD_CSV=?,")
				.append("AD_DN=?,").append("TEL_TYPE_CS=?,").append("TEL_TYPE_CSN=?,").append("TEL_TYPE_CSV=?,")
				.append("TEL_TYPE_DN=?,").append("LINKMAN_REL_CS=?,").append("LINKMAN_REL_CSN=?,")
				.append("LINKMAN_REL_CSV=?,").append("LINKMAN_REL_DN=?,").append("LM_ID_NO_CS=?,")
				.append("LM_ID_NO_CSN=?,").append("LM_ID_NO_CSV=?,").append("LM_ID_NO_DN=?,").append("ABO_CS=?,")
				.append("ABO_CSN=?,").append("ABO_CSV=?,").append("ABO_DN=?,").append("RH_CS=?,").append("RH_CSN=?,")
				.append("RH_CSV=?,").append("RH_DN=?,").append("CARD_ED_BG_CS=?,").append("CARD_ED_BG_CSN=?,")
				.append("CARD_ED_BG_CSV=?,").append("CARD_ED_BG_DN=?,").append("BIRTH_PLACE_CS=?,")
				.append("BIRTH_PLACE_CSN=?,").append("BIRTH_PLACE_CSV=?,").append("BIRTH_PLACE_DN=?,")
				.append("NATIVE_PROVINCE=?,").append("NATIVE_PROVINCE_CS=?,").append("NATIVE_PROVINCE_CSN=?,")
				.append("NATIVE_PROVINCE_CSV=?,").append("NATIVE_PROVINCE_DN=?,").append("NATIVE_CITY_CS=?,")
				.append("NATIVE_CITY_CSN=?,").append("NATIVE_CITY_CSV=?,").append("NATIVE_CITY_DN=?,")
				.append("VETERANS_MILITARY_VALUE=?,").append("VETERANS_MILITARY_MARK_CS=?,")
				.append("VETERANS_MILITARY_MARK_CSN=?,").append("VETERANS_MILITARY_MARK_CSV=?,").append("CARD_CS=?,")
				.append("CARD_CSN=?,").append("CARD_CSV=?,").append("CARD_DN=?,").append("CARD_AREA_CS=?,")
				.append("CARD_AREA_CSN=?,").append("CARD_AREA_CSV=?,").append("CARD_AREA_DN=?,")
				.append("PATIENT_TYPE_VALUE=?,").append("PATIENT_TYPE_CS=?,").append("PATIENT_TYPE_CSN=?,")
				.append("PATIENT_TYPE_CSV=?,").append("PATIENT_TYPE_DESCR=?,").append("WORKING_TEL_NO=?,")
				.append("MEDICAL_TREATMENT_CS=?,").append("MEDICAL_TREATMENT_CSN=?,").append("MEDICAL_TREATMENT_CSV=?,")
				.append("VERSION_NUM=?,").append("RESPONS_DOC_CODE=?,").append("RESPONS_DOC_NAME=?,")
				.append("OTHER_OCCC_DESCR=?,").append("USUAL_TYPE_MARK=?,").append("SRC_LEVEL=?, ").append("TYPE=?, ")
				.append("RELATION_PK=?, ").append("RELATION_TYPE=?, ")
				// updated 2017-03-01 WHN
				.append("patient_id=? ,").append("drug_allergy_mark=? ,").append("op_history_mark=? ,")
				.append("trauma_history_mark=? ,").append("blood_transf_mark=? ,").append("disability_mark=? ,")
				.append("genetic_disease_history=? ,").append("exhaust_facility_mark=? ,")
				.append("exhaust_facility_type_code=? ,").append("fuel_type_code=? ,").append("water_type_code=? ,")
				.append("toilet_type_code=? ,").append("livestock_pen_type_code=? ,").append("operation_history=? ,")
				.append("asthma_mark=? ,").append("hedrt_dis_mark=? ,").append("cardiovascular_code=? ,")
				.append("epilepsy_mark=? ,").append("coagulopathy_mark=? ,").append("diabetes_mark=? ,")
				.append("glaucoma_mark=? ,").append("dialysis_mark=? ,").append("organ_trans_mark=? ,")
				.append("organ_defect_mark=? ,").append("remova_pro_mark=? ,").append("cardiac_pac_mark=? ,")
				.append("orther_medical_alert=? ,").append("psychiatric_mark=? ").append(" where FIELD_PK = ?");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getREGISTER_ORG_CODE());
				ps.setString(2, entity.getREGISTER_ORG_NAME());
				ps.setString(3, entity.getMEDICALSERVICE_NO());
				ps.setString(4, entity.getNAME_CN());
				ps.setString(5, entity.getNAME_EN());
				ps.setString(6, entity.getREMARK());
				// ps.setDate(7,new java.sql.Date(entity.getBIRTH_DATE().getTime()));
				ps.setDate(7,
						entity.getBIRTH_DATE() == null ? null : new java.sql.Date(entity.getBIRTH_DATE().getTime()));
				ps.setString(8, entity.getGENDER_CD());
				ps.setString(9, entity.getCARD_MARITAL_ST_CD());
				ps.setString(10, entity.getID_NO_CD());
				ps.setString(11, entity.getID_NO());
				ps.setString(12, entity.getMEDICARE_CD());
				ps.setString(13, entity.getMEDICAL_INSURANCE_NO());
				ps.setString(14, entity.getCARD_OCCU_TYPE_CD());
				ps.setString(15, entity.getCARD_NATION_CD());
				ps.setString(16, entity.getNATIONALITY_CD());
				ps.setString(17, entity.getAR_CD());
				ps.setString(18, entity.getAD_CD());
				ps.setString(19, entity.getPROVINCE_NAME());
				ps.setString(20, entity.getCITY_NAME());
				ps.setString(21, entity.getAREA_NAME());
				ps.setString(22, entity.getSTREET_ID());
				ps.setString(23, entity.getVILLAGE_NAME());
				ps.setString(24, entity.getHOUSE_NO());
				ps.setString(25, entity.getTEL_TYPE_CD());
				ps.setString(26, entity.getPERSON_TEL_NO());
				ps.setString(27, entity.getLINKMAN_NAME());
				ps.setString(28, entity.getLINKMAN_REL_CD());
				ps.setString(29, entity.getLM_ID_NO_CD());
				ps.setString(30, entity.getLM_ID_NO());
				ps.setString(31, entity.getHR_ID());
				ps.setString(32, entity.getPOST_CD());
				ps.setString(33, entity.getABO_CD());
				ps.setString(34, entity.getRH_CD());
				ps.setString(35, entity.getRH_NEG_CD());
				ps.setString(36, entity.getCARD_ED_BG_CD());
				ps.setString(37, entity.getBIRTH_PLACE());
				ps.setString(38, entity.getWORKING_UNIT_NAME());
				ps.setString(39, entity.getEMAIL_AD());
				ps.setShort(40, entity.getVETERANS_MILITARY_MARK() == null ? 0 : entity.getVETERANS_MILITARY_MARK());
				ps.setString(41, entity.getCARD_NO());
				ps.setString(42, entity.getCARD_CD());
				ps.setString(43, entity.getCARD_AREA());
				ps.setString(44, entity.getPATIENT_TYPE());
				ps.setString(45, entity.getM_PHONE_NUM());
				ps.setString(46, entity.getWORKING_UNIT_POST());
				ps.setString(47, entity.getWORKING_UNIT_ADDR());
				ps.setString(48, entity.getLIVING_ADDR());
				ps.setString(49, entity.getRPR_ADDR());
				ps.setString(50, entity.getRPR_POST());
				ps.setString(51, entity.getRPR_PHONE());
				ps.setString(52, entity.getLINKMAN_ADDR());
				ps.setString(53, entity.getLINKMAN_POST());
				ps.setString(54, entity.getLINKMAN_PHONE());
				ps.setString(55, entity.getINSURE_TYPE());
				ps.setString(56, entity.getMEDICAL_TREATMENT_NAME());
				ps.setString(57, entity.getMEDICAL_TREATMENT_CD());
				// ps.setDate(58,new java.sql.Date(entity.getREGISTER_DATE().getTime()));
				ps.setDate(58, entity.getREGISTER_DATE() == null ? null
						: new java.sql.Date(entity.getREGISTER_DATE().getTime()));
				ps.setString(59, entity.getRECORDER_NAME());
				ps.setString(60, entity.getREGISTER_PERSON_CODE());
				ps.setDate(61, new java.sql.Date(entity.getSEND_TIME().getTime()));
				ps.setString(62, entity.getSEND_ORG_CODE());
				ps.setString(63, entity.getSEND_SYSTEM());
				ps.setString(64, entity.getPROVIDER_NAME());
				ps.setString(65, entity.getPROVIDER_ORG_CODE());
				ps.setDate(66, new java.sql.Date(entity.getCREATETIME().getTime()));
				ps.setDate(67,
						entity.getLASTUPTIME() == null ? null : new java.sql.Date(entity.getLASTUPTIME().getTime()));
				ps.setShort(68, entity.getSTATE());
				ps.setString(69, entity.getGENDER_CS());
				ps.setString(70, entity.getGENDER_CSN());
				ps.setString(71, entity.getGENDER_CSV());
				ps.setString(72, entity.getGENDER_DN());
				ps.setString(73, entity.getCARD_MARITAL_ST_CS());
				ps.setString(74, entity.getCARD_MARITAL_ST_CSN());
				ps.setString(75, entity.getCARD_MARITAL_ST_CSV());
				ps.setString(76, entity.getCARD_MARITAL_ST_DN());
				ps.setString(77, entity.getID_NO_CS());
				ps.setString(78, entity.getID_NO_CSN());
				ps.setString(79, entity.getID_NO_CSV());
				ps.setString(80, entity.getID_NO_DN());
				ps.setString(81, entity.getMEDICARE_CS());
				ps.setString(82, entity.getMEDICARE_CSN());
				ps.setString(83, entity.getMEDICARE_CSV());
				ps.setString(84, entity.getMEDICARE_DN());
				ps.setString(85, entity.getNH_CARD());
				ps.setString(86, entity.getSSCID());
				ps.setString(87, entity.getCARD_OCCU_TYPE_CS());
				ps.setString(88, entity.getCARD_OCCU_TYPE_CSN());
				ps.setString(89, entity.getCARD_OCCU_TYPE_CSV());
				ps.setString(90, entity.getCARD_OCCU_TYPE_DN());
				ps.setString(91, entity.getCARD_NATION_CS());
				ps.setString(92, entity.getCARD_NATION_CSN());
				ps.setString(93, entity.getCARD_NATION_CSV());
				ps.setString(94, entity.getCARD_NATION_DN());
				ps.setString(95, entity.getNATIONALITY_CS());
				ps.setString(96, entity.getNATIONALITY_CSN());
				ps.setString(97, entity.getNATIONALITY_CSV());
				ps.setString(98, entity.getNATIONALITY_DN());
				ps.setString(99, entity.getAR_CS());
				ps.setString(100, entity.getAR_CSN());
				ps.setString(101, entity.getAR_CSV());
				ps.setString(102, entity.getAR_DN());
				ps.setString(103, entity.getAD_CS());
				ps.setString(104, entity.getAD_CSN());
				ps.setString(105, entity.getAD_CSV());
				ps.setString(106, entity.getAD_DN());
				ps.setString(107, entity.getTEL_TYPE_CS());
				ps.setString(108, entity.getTEL_TYPE_CSN());
				ps.setString(109, entity.getTEL_TYPE_CSV());
				ps.setString(110, entity.getTEL_TYPE_DN());
				ps.setString(111, entity.getLINKMAN_REL_CS());
				ps.setString(112, entity.getLINKMAN_REL_CSN());
				ps.setString(113, entity.getLINKMAN_REL_CSV());
				ps.setString(114, entity.getLINKMAN_REL_DN());
				ps.setString(115, entity.getLM_ID_NO_CS());
				ps.setString(116, entity.getLM_ID_NO_CSN());
				ps.setString(117, entity.getLM_ID_NO_CSV());
				ps.setString(118, entity.getLM_ID_NO_DN());
				ps.setString(119, entity.getABO_CS());
				ps.setString(120, entity.getABO_CSN());
				ps.setString(121, entity.getABO_CSV());
				ps.setString(122, entity.getABO_DN());
				ps.setString(123, entity.getRH_CS());
				ps.setString(124, entity.getRH_CSN());
				ps.setString(125, entity.getRH_CSV());
				ps.setString(126, entity.getRH_DN());
				ps.setString(127, entity.getCARD_ED_BG_CS());
				ps.setString(128, entity.getCARD_ED_BG_CSN());
				ps.setString(129, entity.getCARD_ED_BG_CSV());
				ps.setString(130, entity.getCARD_ED_BG_DN());
				ps.setString(131, entity.getBIRTH_PLACE_CS());
				ps.setString(132, entity.getBIRTH_PLACE_CSN());
				ps.setString(133, entity.getBIRTH_PLACE_CSV());
				ps.setString(134, entity.getBIRTH_PLACE_DN());
				ps.setString(135, entity.getNATIVE_PROVINCE());
				ps.setString(136, entity.getNATIVE_PROVINCE_CS());
				ps.setString(137, entity.getNATIVE_PROVINCE_CSN());
				ps.setString(138, entity.getNATIVE_PROVINCE_CSV());
				ps.setString(139, entity.getNATIVE_PROVINCE_DN());
				ps.setString(140, entity.getNATIVE_CITY_CS());
				ps.setString(141, entity.getNATIVE_CITY_CSN());
				ps.setString(142, entity.getNATIVE_CITY_CSV());
				ps.setString(143, entity.getNATIVE_CITY_DN());
				ps.setString(144, entity.getVETERANS_MILITARY_VALUE());
				ps.setString(145, entity.getVETERANS_MILITARY_MARK_CS());
				ps.setString(146, entity.getVETERANS_MILITARY_MARK_CSN());
				ps.setString(147, entity.getVETERANS_MILITARY_MARK_CSV());
				ps.setString(148, entity.getCARD_CS());
				ps.setString(149, entity.getCARD_CSN());
				ps.setString(150, entity.getCARD_CSV());
				ps.setString(151, entity.getCARD_DN());
				ps.setString(152, entity.getCARD_AREA_CS());
				ps.setString(153, entity.getCARD_AREA_CSN());
				ps.setString(154, entity.getCARD_AREA_CSV());
				ps.setString(155, entity.getCARD_AREA_DN());
				ps.setString(156, entity.getPATIENT_TYPE_VALUE());
				ps.setString(157, entity.getPATIENT_TYPE_CS());
				ps.setString(158, entity.getPATIENT_TYPE_CSN());
				ps.setString(159, entity.getPATIENT_TYPE_CSV());
				ps.setString(160, entity.getPATIENT_TYPE_DESCR());
				ps.setString(161, entity.getWORKING_TEL_NO());
				ps.setString(162, entity.getMEDICAL_TREATMENT_CS());
				ps.setString(163, entity.getMEDICAL_TREATMENT_CSN());
				ps.setString(164, entity.getMEDICAL_TREATMENT_CSV());
				ps.setString(165, entity.getVERSION_NUM());
				ps.setString(166, entity.getRESPONS_DOC_CODE());
				ps.setString(167, entity.getRESPONS_DOC_NAME());
				ps.setString(168, entity.getOTHER_OCCC_DESCR());
				ps.setShort(169, entity.getUSUAL_TYPE_MARK() == null ? 0 : entity.getUSUAL_TYPE_MARK());
				ps.setShort(170, entity.getSRC_LEVEL() == null ? 0 : entity.getSRC_LEVEL());
				// lpk update 2013年4月16日10:57:44
				ps.setString(171, entity.getTYPE());
				ps.setString(172, entity.getRELATION_PK());
				ps.setString(173, entity.getRELATION_TYPE());

				// whn updated 20170301
				ps.setString(174, entity.getPATIENT_ID());
				ps.setString(175, entity.getDRUG_ALLERGY_MARK());
				ps.setString(176, entity.getOP_HISTORY_MARK());
				ps.setString(177, entity.getTRAUMA_HISTORY_MARK());
				ps.setString(178, entity.getBLOOD_TRANSF_MARK());
				ps.setString(179, entity.getDISABILITY_MARK());
				ps.setString(180, entity.getGENETIC_DISEASE_HISTORY());
				ps.setString(181, entity.getEXHAUST_FACILITY_MARK());
				ps.setString(182, entity.getEXHAUST_FACILITY_TYPE_CODE());
				ps.setString(183, entity.getFUEL_TYPE_CODE());
				ps.setString(184, entity.getWATER_TYPE_CODE());
				ps.setString(185, entity.getTOILET_TYPE_CODE());
				ps.setString(186, entity.getLIVESTOCK_PEN_TYPE_CODE());
				ps.setString(187, entity.getOPERATION_HISTORY());
				ps.setString(188, entity.getASTHMA_MARK());
				ps.setString(189, entity.getHEDRT_DIS_MARK());
				ps.setString(190, entity.getCARDIOVASCULAR_CODE());
				ps.setString(191, entity.getEPILEPSY_MARK());
				ps.setString(192, entity.getCOAGULOPATHY_MARK());
				ps.setString(193, entity.getDIABETES_MARK());
				ps.setString(194, entity.getGLAUCOMA_MARK());
				ps.setString(195, entity.getDIALYSIS_MARK());
				ps.setString(196, entity.getORGAN_TRANS_MARK());
				ps.setString(197, entity.getORGAN_DEFECT_MARK());
				ps.setString(198, entity.getREMOVA_PRO_MARK());
				ps.setString(199, entity.getCARDIAC_PAC_MARK());
				ps.setString(200, entity.getORTHER_MEDICAL_ALERT());
				ps.setString(201, entity.getPSYCHIATRIC_MARK());

				ps.setString(202, entity.getFIELD_PK());

			}
		});

	}

	// state=1 由relationpk更新对应的记录
	public void updateByRelationpk(final PersonInfo entity) {
		if (entity == null || entity.getFIELD_PK() == null) {
			return;
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" update mpi_person_info set ").append("ABO_CD=?,").append("ABO_CS=?,").append("ABO_CSN=?,")
				.append("ABO_CSV=?,").append("ABO_DN=?,").append("AD_CD=?,").append("AD_CS=?,").append("AD_CSN=?,")
				.append("AD_CSV=?,").append("AD_DN=?,").append("AR_CD=?,").append("AR_CS=?,").append("AR_CSN=?,")
				.append("AR_CSV=?,").append("AR_DN=?,").append("AREA_NAME=?,").append("BIRTH_DATE=?,")
				.append("BIRTH_PLACE=?,").append("BIRTH_PLACE_CS=?,").append("BIRTH_PLACE_CSN=?,")
				.append("BIRTH_PLACE_CSV=?,").append("BIRTH_PLACE_DN=?,").append("CARD_AREA=?,")
				.append("CARD_AREA_CS=?,").append("CARD_AREA_CSN=?,").append("CARD_AREA_CSV=?,")
				.append("CARD_AREA_DN=?,").append("CARD_CD=?,").append("CARD_CS=?,").append("CARD_CSN=?,")
				.append("CARD_CSV=?,").append("CARD_DN=?,").append("CARD_ED_BG_CD=?,").append("CARD_ED_BG_CS=?,")
				.append("CARD_ED_BG_CSN=?,").append("CARD_ED_BG_CSV=?,").append("CARD_ED_BG_DN=?,")
				.append("CARD_MARITAL_ST_CD=?,").append("CARD_MARITAL_ST_CS=?,").append("CARD_MARITAL_ST_CSN=?,")
				.append("CARD_MARITAL_ST_CSV=?,").append("CARD_MARITAL_ST_DN=?,").append("CARD_NATION_CD=?,")
				.append("CARD_NATION_CS=?,").append("CARD_NATION_CSN=?,").append("CARD_NATION_CSV=?,")
				.append("CARD_NATION_DN=?,").append("CARD_NO=?,").append("CARD_OCCU_TYPE_CD=?,")
				.append("CARD_OCCU_TYPE_CS=?,").append("CARD_OCCU_TYPE_CSN=?,").append("CARD_OCCU_TYPE_CSV=?,")
				.append("CARD_OCCU_TYPE_DN=?,").append("CITY_NAME=?,").append("CREATETIME=?,").append("EMAIL_AD=?,")
				.append("GENDER_CD=?,").append("GENDER_CS=?,").append("GENDER_CSN=?,").append("GENDER_CSV=?,")
				.append("GENDER_DN=?,").append("HOUSE_NO=?,").append("ID_NO=?,").append("ID_NO_CD=?,")
				.append("ID_NO_CS=?,").append("ID_NO_CSN=?,").append("ID_NO_CSV=?,").append("ID_NO_DN=?,")
				.append("INSURE_TYPE=?,").append("LASTUPTIME=?,").append("LINKMAN_ADDR=?,").append("LINKMAN_NAME=?,")
				.append("LINKMAN_PHONE=?,").append("LINKMAN_POST=?,").append("LINKMAN_REL_CD=?,")
				.append("LINKMAN_REL_CS=?,").append("LINKMAN_REL_CSN=?,").append("LINKMAN_REL_CSV=?,")
				.append("LINKMAN_REL_DN=?,").append("LIVING_ADDR=?,").append("LM_ID_NO=?,").append("LM_ID_NO_CD=?,")
				.append("LM_ID_NO_CS=?,").append("LM_ID_NO_CSN=?,").append("LM_ID_NO_CSV=?,").append("LM_ID_NO_DN=?,")
				.append("M_PHONE_NUM=?,").append("MEDICAL_INSURANCE_NO=?,").append("MEDICAL_TREATMENT_CD=?,")
				.append("MEDICAL_TREATMENT_CS=?,").append("MEDICAL_TREATMENT_CSN=?,").append("MEDICAL_TREATMENT_CSV=?,")
				.append("MEDICAL_TREATMENT_NAME=?,").append("MEDICARE_CD=?,").append("MEDICARE_CS=?,")
				.append("MEDICARE_CSN=?,").append("MEDICARE_CSV=?,").append("MEDICARE_DN=?,").append("NAME_CN=?,")
				.append("NAME_EN=?,").append("NATIONALITY_CD=?,").append("NATIONALITY_CS=?,")
				.append("NATIONALITY_CSN=?,").append("NATIONALITY_CSV=?,").append("NATIONALITY_DN=?,")
				.append("NATIVE_CITY=?,").append("NATIVE_CITY_CS=?,").append("NATIVE_CITY_CSN=?,")
				.append("NATIVE_CITY_CSV=?,").append("NATIVE_CITY_DN=?,").append("NATIVE_PROVINCE=?,")
				.append("NATIVE_PROVINCE_CS=?,").append("NATIVE_PROVINCE_CSN=?,").append("NATIVE_PROVINCE_CSV=?,")
				.append("NATIVE_PROVINCE_DN=?,").append("NH_CARD=?,").append("OTHER_OCCC_DESCR=?,")
				.append("PATIENT_TYPE=?,").append("PATIENT_TYPE_CS=?,").append("PATIENT_TYPE_CSN=?,")
				.append("PATIENT_TYPE_CSV=?,").append("PATIENT_TYPE_DESCR=?,").append("PATIENT_TYPE_VALUE=?,")
				.append("PERSON_TEL_NO=?,").append("POST_CD=?,").append("PROVIDER_NAME=?,")
				.append("PROVIDER_ORG_CODE=?,").append("PROVINCE_NAME=?,").append("RECORDER_NAME=?,")
				.append("REGISTER_DATE=?,").append("REGISTER_ORG_CODE=?,").append("REGISTER_ORG_NAME=?,")
				.append("REGISTER_PERSON_CODE=?,").append("REMARK=?,").append("RESPONS_DOC_CODE=?,")
				.append("RESPONS_DOC_NAME=?,").append("RH_CD=?,").append("RH_CS=?,").append("RH_CSN=?,")
				.append("RH_CSV=?,").append("RH_DN=?,").append("RH_NEG_CD=?,").append("RPR_ADDR=?,")
				.append("RPR_PHONE=?,").append("RPR_POST=?,").append("SEND_ORG_CODE=?,").append("SEND_SYSTEM=?,")
				.append("SEND_TIME=?,").append("SRC_LEVEL=?,").append("SSCID=?,").append("STATE=?,")
				.append("STREET_ID=?,").append("TEL_TYPE_CD=?,").append("TEL_TYPE_CS=?,").append("TEL_TYPE_CSN=?,")
				.append("TEL_TYPE_CSV=?,").append("TEL_TYPE_DN=?,").append("USUAL_TYPE_MARK=?,")
				.append("VERSION_NUM=?,").append("VETERANS_MILITARY_MARK=?,").append("VETERANS_MILITARY_MARK_CS=?,")
				.append("VETERANS_MILITARY_MARK_CSN=?,").append("VETERANS_MILITARY_MARK_CSV=?,")
				.append("VETERANS_MILITARY_VALUE=?,").append("VILLAGE_NAME=?,").append("WORKING_TEL_NO=?,")
				.append("WORKING_UNIT_ADDR=?,").append("WORKING_UNIT_NAME=?,").append("WORKING_UNIT_POST=?,")
				// .append("FIELD_PK=?,")
				.append("TYPE=?,").append("RELATION_PK=?")
				// .append("RELATION_TYPE=?,")
				// .append("MEDICALSERVICE_NO=?,")
				// .append("HR_ID=?");

				/* updated 20170301 WHN */
				.append("patient_id=? ,").append("drug_allergy_mark=? ,").append("op_history_mark=? ,")
				.append("trauma_history_mark=? ,").append("blood_transf_mark=? ,").append("disability_mark=? ,")
				.append("genetic_disease_history=? ,").append("exhaust_facility_mark=? ,")
				.append("exhaust_facility_type_code=? ,").append("fuel_type_code=? ,").append("water_type_code=? ,")
				.append("toilet_type_code=? ,").append("livestock_pen_type_code=? ,").append("operation_history=? ,")
				.append("asthma_mark=? ,").append("hedrt_dis_mark=? ,").append("cardiovascular_code=? ,")
				.append("epilepsy_mark=? ,").append("coagulopathy_mark=? ,").append("diabetes_mark=? ,")
				.append("glaucoma_mark=? ,").append("dialysis_mark=? ,").append("organ_trans_mark=? ,")
				.append("organ_defect_mark=? ,").append("remova_pro_mark=? ,").append("cardiac_pac_mark=? ,")
				.append("orther_medical_alert=? ,").append("psychiatric_mark=? ");

		sql.append(" where field_pk = ? ");
		/*
		 * if("0".equals(type)){//个人
		 * sql.append(" where type = ? and hr_id=? and REGISTER_ORG_CODE=?"); }else{//患者
		 * sql.append(" where type = ? and MEDICALSERVICE_NO=? and REGISTER_ORG_CODE=?"
		 * ); }
		 */
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getABO_CD());
				ps.setString(2, entity.getABO_CS());
				ps.setString(3, entity.getABO_CSN());
				ps.setString(4, entity.getABO_CSV());
				ps.setString(5, entity.getABO_DN());
				ps.setString(6, entity.getAD_CD());
				ps.setString(7, entity.getAD_CS());
				ps.setString(8, entity.getAD_CSN());
				ps.setString(9, entity.getAD_CSV());
				ps.setString(10, entity.getAD_DN());
				ps.setString(11, entity.getAR_CD());
				ps.setString(12, entity.getAR_CS());
				ps.setString(13, entity.getAR_CSN());
				ps.setString(14, entity.getAR_CSV());
				ps.setString(15, entity.getAR_DN());
				ps.setString(16, entity.getAREA_NAME());
				// ps.setDate(17,new java.sql.Date(entity.getBIRTH_DATE().getTime()));
				ps.setDate(17,
						entity.getBIRTH_DATE() == null ? null : new java.sql.Date(entity.getBIRTH_DATE().getTime()));
				ps.setString(18, entity.getBIRTH_PLACE());
				ps.setString(19, entity.getBIRTH_PLACE_CS());
				ps.setString(20, entity.getBIRTH_PLACE_CSN());
				ps.setString(21, entity.getBIRTH_PLACE_CSV());
				ps.setString(22, entity.getBIRTH_PLACE_DN());
				ps.setString(23, entity.getCARD_AREA());
				ps.setString(24, entity.getCARD_AREA_CS());
				ps.setString(25, entity.getCARD_AREA_CSN());
				ps.setString(26, entity.getCARD_AREA_CSV());
				ps.setString(27, entity.getCARD_AREA_DN());
				ps.setString(28, entity.getCARD_CD());
				ps.setString(29, entity.getCARD_CS());
				ps.setString(30, entity.getCARD_CSN());
				ps.setString(31, entity.getCARD_CSV());
				ps.setString(32, entity.getCARD_DN());
				ps.setString(33, entity.getCARD_ED_BG_CD());
				ps.setString(34, entity.getCARD_ED_BG_CS());
				ps.setString(35, entity.getCARD_ED_BG_CSN());
				ps.setString(36, entity.getCARD_ED_BG_CSV());
				ps.setString(37, entity.getCARD_ED_BG_DN());
				ps.setString(38, entity.getCARD_MARITAL_ST_CD());
				ps.setString(39, entity.getCARD_MARITAL_ST_CS());
				ps.setString(40, entity.getCARD_MARITAL_ST_CSN());
				ps.setString(41, entity.getCARD_MARITAL_ST_CSV());
				ps.setString(42, entity.getCARD_MARITAL_ST_DN());
				ps.setString(43, entity.getCARD_NATION_CD());
				ps.setString(44, entity.getCARD_NATION_CS());
				ps.setString(45, entity.getCARD_NATION_CSN());
				ps.setString(46, entity.getCARD_NATION_CSV());
				ps.setString(47, entity.getCARD_NATION_DN());
				ps.setString(48, entity.getCARD_NO());
				ps.setString(49, entity.getCARD_OCCU_TYPE_CD());
				ps.setString(50, entity.getCARD_OCCU_TYPE_CS());
				ps.setString(51, entity.getCARD_OCCU_TYPE_CSN());
				ps.setString(52, entity.getCARD_OCCU_TYPE_CSV());
				ps.setString(53, entity.getCARD_OCCU_TYPE_DN());
				ps.setString(54, entity.getCITY_NAME());
				ps.setDate(55, new java.sql.Date(entity.getCREATETIME().getTime()));
				ps.setString(56, entity.getEMAIL_AD());
				ps.setString(57, entity.getGENDER_CD());
				ps.setString(58, entity.getGENDER_CS());
				ps.setString(59, entity.getGENDER_CSN());
				ps.setString(60, entity.getGENDER_CSV());
				ps.setString(61, entity.getGENDER_DN());
				ps.setString(62, entity.getHOUSE_NO());
				ps.setString(63, entity.getID_NO());
				ps.setString(64, entity.getID_NO_CD());
				ps.setString(65, entity.getID_NO_CS());
				ps.setString(66, entity.getID_NO_CSN());
				ps.setString(67, entity.getID_NO_CSV());
				ps.setString(68, entity.getID_NO_DN());
				ps.setString(69, entity.getINSURE_TYPE());
				ps.setDate(70, new java.sql.Date(entity.getLASTUPTIME().getTime()));
				ps.setString(71, entity.getLINKMAN_ADDR());
				ps.setString(72, entity.getLINKMAN_NAME());
				ps.setString(73, entity.getLINKMAN_PHONE());
				ps.setString(74, entity.getLINKMAN_POST());
				ps.setString(75, entity.getLINKMAN_REL_CD());
				ps.setString(76, entity.getLINKMAN_REL_CS());
				ps.setString(77, entity.getLINKMAN_REL_CSN());
				ps.setString(78, entity.getLINKMAN_REL_CSV());
				ps.setString(79, entity.getLINKMAN_REL_DN());
				ps.setString(80, entity.getLIVING_ADDR());
				ps.setString(81, entity.getLM_ID_NO());
				ps.setString(82, entity.getLM_ID_NO_CD());
				ps.setString(83, entity.getLM_ID_NO_CS());
				ps.setString(84, entity.getLM_ID_NO_CSN());
				ps.setString(85, entity.getLM_ID_NO_CSV());
				ps.setString(86, entity.getLM_ID_NO_DN());
				ps.setString(87, entity.getM_PHONE_NUM());
				ps.setString(88, entity.getMEDICAL_INSURANCE_NO());
				ps.setString(89, entity.getMEDICAL_TREATMENT_CD());
				ps.setString(90, entity.getMEDICAL_TREATMENT_CS());
				ps.setString(91, entity.getMEDICAL_TREATMENT_CSN());
				ps.setString(92, entity.getMEDICAL_TREATMENT_CSV());
				ps.setString(93, entity.getMEDICAL_TREATMENT_NAME());
				ps.setString(94, entity.getMEDICARE_CD());
				ps.setString(95, entity.getMEDICARE_CS());
				ps.setString(96, entity.getMEDICARE_CSN());
				ps.setString(97, entity.getMEDICARE_CSV());
				ps.setString(98, entity.getMEDICARE_DN());
				ps.setString(99, entity.getNAME_CN());
				ps.setString(100, entity.getNAME_EN());
				ps.setString(101, entity.getNATIONALITY_CD());
				ps.setString(102, entity.getNATIONALITY_CS());
				ps.setString(103, entity.getNATIONALITY_CSN());
				ps.setString(104, entity.getNATIONALITY_CSV());
				ps.setString(105, entity.getNATIONALITY_DN());
				ps.setString(106, entity.getNATIVE_CITY());
				ps.setString(107, entity.getNATIVE_CITY_CS());
				ps.setString(108, entity.getNATIVE_CITY_CSN());
				ps.setString(109, entity.getNATIVE_CITY_CSV());
				ps.setString(110, entity.getNATIVE_CITY_DN());
				ps.setString(111, entity.getNATIVE_PROVINCE());
				ps.setString(112, entity.getNATIVE_PROVINCE_CS());
				ps.setString(113, entity.getNATIVE_PROVINCE_CSN());
				ps.setString(114, entity.getNATIVE_PROVINCE_CSV());
				ps.setString(115, entity.getNATIVE_PROVINCE_DN());
				ps.setString(116, entity.getNH_CARD());
				ps.setString(117, entity.getOTHER_OCCC_DESCR());
				ps.setString(118, entity.getPATIENT_TYPE());
				ps.setString(119, entity.getPATIENT_TYPE_CS());
				ps.setString(120, entity.getPATIENT_TYPE_CSN());
				ps.setString(121, entity.getPATIENT_TYPE_CSV());
				ps.setString(122, entity.getPATIENT_TYPE_DESCR());
				ps.setString(123, entity.getPATIENT_TYPE_VALUE());
				ps.setString(124, entity.getPERSON_TEL_NO());
				ps.setString(125, entity.getPOST_CD());
				ps.setString(126, entity.getPROVIDER_NAME());
				ps.setString(127, entity.getPROVIDER_ORG_CODE());
				ps.setString(128, entity.getPROVINCE_NAME());
				ps.setString(129, entity.getRECORDER_NAME());
				// ps.setDate(130,new java.sql.Date(entity.getREGISTER_DATE().getTime()));
				ps.setDate(130, entity.getREGISTER_DATE() == null ? null
						: new java.sql.Date(entity.getREGISTER_DATE().getTime()));
				ps.setString(131, entity.getREGISTER_ORG_CODE());
				ps.setString(132, entity.getREGISTER_ORG_NAME());
				ps.setString(133, entity.getREGISTER_PERSON_CODE());
				ps.setString(134, entity.getREMARK());
				ps.setString(135, entity.getRESPONS_DOC_CODE());
				ps.setString(136, entity.getRESPONS_DOC_NAME());
				ps.setString(137, entity.getRH_CD());
				ps.setString(138, entity.getRH_CS());
				ps.setString(139, entity.getRH_CSN());
				ps.setString(140, entity.getRH_CSV());
				ps.setString(141, entity.getRH_DN());
				ps.setString(142, entity.getRH_NEG_CD());
				ps.setString(143, entity.getRPR_ADDR());
				ps.setString(144, entity.getRPR_PHONE());
				ps.setString(145, entity.getRPR_POST());
				ps.setString(146, entity.getSEND_ORG_CODE());
				ps.setString(147, entity.getSEND_SYSTEM());
				ps.setDate(148, new java.sql.Date(entity.getSEND_TIME().getTime()));
				ps.setShort(149, entity.getSRC_LEVEL() == null ? 0 : entity.getSRC_LEVEL());
				ps.setString(150, entity.getSSCID());
				ps.setShort(151, entity.getSTATE() == null ? 0 : entity.getSTATE());
				ps.setString(152, entity.getSTREET_ID());
				ps.setString(153, entity.getTEL_TYPE_CD());
				ps.setString(154, entity.getTEL_TYPE_CS());
				ps.setString(155, entity.getTEL_TYPE_CSN());
				ps.setString(156, entity.getTEL_TYPE_CSV());
				ps.setString(157, entity.getTEL_TYPE_DN());
				ps.setShort(158, entity.getUSUAL_TYPE_MARK());
				ps.setString(159, entity.getVERSION_NUM());
				ps.setShort(160, entity.getVETERANS_MILITARY_MARK() == null ? 0 : entity.getVETERANS_MILITARY_MARK());
				ps.setString(161, entity.getVETERANS_MILITARY_MARK_CS());
				ps.setString(162, entity.getVETERANS_MILITARY_MARK_CSN());
				ps.setString(163, entity.getVETERANS_MILITARY_MARK_CSV());
				ps.setString(164, entity.getVETERANS_MILITARY_VALUE());
				ps.setString(165, entity.getVILLAGE_NAME());
				ps.setString(166, entity.getWORKING_TEL_NO());
				ps.setString(167, entity.getWORKING_UNIT_ADDR());
				ps.setString(168, entity.getWORKING_UNIT_NAME());
				ps.setString(169, entity.getWORKING_UNIT_POST());
				// ps.setString(170,entity.getFIELD_PK());
				ps.setString(170, entity.getTYPE());
				ps.setString(171, entity.getRELATION_PK());

				// updated 20170301 WHN
				ps.setString(172, entity.getPATIENT_ID());
				ps.setString(173, entity.getDRUG_ALLERGY_MARK());
				ps.setString(174, entity.getOP_HISTORY_MARK());
				ps.setString(175, entity.getTRAUMA_HISTORY_MARK());
				ps.setString(176, entity.getBLOOD_TRANSF_MARK());
				ps.setString(177, entity.getDISABILITY_MARK());
				ps.setString(178, entity.getGENETIC_DISEASE_HISTORY());
				ps.setString(179, entity.getEXHAUST_FACILITY_MARK());
				ps.setString(180, entity.getEXHAUST_FACILITY_TYPE_CODE());
				ps.setString(181, entity.getFUEL_TYPE_CODE());
				ps.setString(182, entity.getWATER_TYPE_CODE());
				ps.setString(183, entity.getTOILET_TYPE_CODE());
				ps.setString(184, entity.getLIVESTOCK_PEN_TYPE_CODE());
				ps.setString(185, entity.getOPERATION_HISTORY());
				ps.setString(186, entity.getASTHMA_MARK());
				ps.setString(187, entity.getHEDRT_DIS_MARK());
				ps.setString(188, entity.getCARDIOVASCULAR_CODE());
				ps.setString(189, entity.getEPILEPSY_MARK());
				ps.setString(190, entity.getCOAGULOPATHY_MARK());
				ps.setString(191, entity.getDIABETES_MARK());
				ps.setString(192, entity.getGLAUCOMA_MARK());
				ps.setString(193, entity.getDIALYSIS_MARK());
				ps.setString(194, entity.getORGAN_TRANS_MARK());
				ps.setString(195, entity.getORGAN_DEFECT_MARK());
				ps.setString(196, entity.getREMOVA_PRO_MARK());
				ps.setString(197, entity.getCARDIAC_PAC_MARK());
				ps.setString(198, entity.getORTHER_MEDICAL_ALERT());
				ps.setString(199, entity.getPSYCHIATRIC_MARK());

				ps.setString(200, entity.getFIELD_PK());

				/*
				 * ps.setString(172,entity.getTYPE());
				 * ps.setString(173,entity.getRELATION_PK());
				 * ps.setString(174,entity.getREGISTER_ORG_CODE())
				 */;
			}
		});
	}

	@Override
	public PersonInfo findById(PersonInfo entity) {
		String sql = " select * from mpi_person_info where field_pk = ? ";
		List<PersonInfo> datas = find(sql, new Object[] { entity.getFIELD_PK() });
		PersonInfo result = null;
		if (datas != null && !datas.isEmpty()) {
			result = datas.get(0);
		}
		return result;
	}

	// 根据机构号和ID查询
	@Override
	public PersonInfo findByOrgId(PersonInfo entity) {
		// lpk 2013年4月16日9:25:51
		String type = entity.getTYPE();
		String sql = " select * from mpi_person_info  t where t.MEDICALSERVICE_NO= ? and t.REGISTER_ORG_CODE=? and t.type=?";
		List<PersonInfo> datas = null;
		if ("0".equals(type)) {// 个人
			sql = " select * from mpi_person_info  t where t.HR_ID= ? and t.REGISTER_ORG_CODE=?  and t.type=?";
			try {
				datas = find(sql, new Object[] { entity.getHR_ID(), entity.getREGISTER_ORG_CODE(), type });
			} catch (Exception e) {
				System.out.println(e);
			}

		} else if ("1".equals(type)) {
			datas = find(sql, new Object[] { entity.getMEDICALSERVICE_NO(), entity.getREGISTER_ORG_CODE(), type });
		} else if ("3".equals(type)) {// WHN updated 2017-03-01
			datas = find(sql, new Object[] { entity.getPATIENT_ID(), entity.getREGISTER_ORG_CODE(), type });
		}
		PersonInfo result = null;
		if (datas != null && !datas.isEmpty()) {
			result = datas.get(0);
		}
		return result;
	}

	@Override
	public PersonInfo findWithDomainInfoById(PersonInfo entity) {
		/*
		 * String sql = " select a.*,c.domain_id,c.unique_sign,c.domain_desc from " +
		 * " mpi_person_info a left join mpi_index_identifier_rel b on a.person_id = b.person_id "
		 * +
		 * " left join mpi_identifier_domain c on b.domain_id = c.domain_id where a.field_pk = ? "
		 * ;
		 */
		String sql = " select a.*,c.domain_id,c.unique_sign,c.domain_desc from "
				+ " mpi_person_info a left join mpi_index_identifier_rel b on a.field_pk = b.field_pk "
				+ " left join mpi_identifier_domain c on b.domain_id = c.domain_id where a.field_pk = ? ";
		List<PersonInfo> datas = jdbcTemplate.query(sql, new Object[] { entity.getFIELD_PK() }, new PersonRowMapper() {
			@Override
			public PersonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				PersonInfo p = super.mapRow(rs, rowNum);
				p.setDOMAIN_ID(rs.getString("DOMAIN_ID"));
				p.setUNIQUE_SIGN(rs.getString("UNIQUE_SIGN"));
				p.setDOMAIN_DESC(rs.getString("DOMAIN_DESC"));
				return p;
			}
		});
		PersonInfo result = null;
		if (datas != null && !datas.isEmpty()) {
			result = datas.get(0);
		}
		return result;
	}

	@Override
	public List<PersonInfo> find(String sql) {
		List<PersonInfo> datas = find(sql, new Object[] {});
		return datas;
	}

	private class PersonRowMapper implements RowMapper<PersonInfo> {
		@Override
		public PersonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			PersonInfo result = new PersonInfo();
			result.setREGISTER_ORG_CODE(rs.getString("REGISTER_ORG_CODE"));
			result.setREGISTER_ORG_NAME(rs.getString("REGISTER_ORG_NAME"));
			result.setMEDICALSERVICE_NO(rs.getString("MEDICALSERVICE_NO"));
			result.setNAME_CN(rs.getString("NAME_CN"));
			result.setNAME_EN(rs.getString("NAME_EN"));
			result.setREMARK(rs.getString("REMARK"));
			result.setBIRTH_DATE(rs.getDate("BIRTH_DATE"));
			result.setGENDER_CD(rs.getString("GENDER_CD"));
			result.setCARD_MARITAL_ST_CD(rs.getString("CARD_MARITAL_ST_CD"));
			result.setID_NO_CD(rs.getString("ID_NO_CD"));
			result.setID_NO(rs.getString("ID_NO"));
			result.setMEDICARE_CD(rs.getString("MEDICARE_CD"));
			result.setMEDICAL_INSURANCE_NO(rs.getString("MEDICAL_INSURANCE_NO"));
			result.setCARD_OCCU_TYPE_CD(rs.getString("CARD_OCCU_TYPE_CD"));
			result.setCARD_NATION_CD(rs.getString("CARD_NATION_CD"));
			result.setNATIONALITY_CD(rs.getString("NATIONALITY_CD"));
			result.setAR_CD(rs.getString("AR_CD"));
			result.setAD_CD(rs.getString("AD_CD"));
			result.setPROVINCE_NAME(rs.getString("PROVINCE_NAME"));
			result.setCITY_NAME(rs.getString("CITY_NAME"));
			result.setAREA_NAME(rs.getString("AREA_NAME"));
			result.setSTREET_ID(rs.getString("STREET_ID"));
			result.setVILLAGE_NAME(rs.getString("VILLAGE_NAME"));
			result.setHOUSE_NO(rs.getString("HOUSE_NO"));
			result.setTEL_TYPE_CD(rs.getString("TEL_TYPE_CD"));
			result.setPERSON_TEL_NO(rs.getString("PERSON_TEL_NO"));
			result.setLINKMAN_NAME(rs.getString("LINKMAN_NAME"));
			result.setLINKMAN_REL_CD(rs.getString("LINKMAN_REL_CD"));
			result.setLM_ID_NO_CD(rs.getString("LM_ID_NO_CD"));
			result.setLM_ID_NO(rs.getString("LM_ID_NO"));
			result.setHR_ID(rs.getString("HR_ID"));
			result.setPOST_CD(rs.getString("POST_CD"));
			result.setABO_CD(rs.getString("ABO_CD"));
			result.setRH_CD(rs.getString("RH_CD"));
			result.setRH_NEG_CD(rs.getString("RH_NEG_CD"));
			result.setCARD_ED_BG_CD(rs.getString("CARD_ED_BG_CD"));
			result.setBIRTH_PLACE(rs.getString("BIRTH_PLACE"));
			result.setWORKING_UNIT_NAME(rs.getString("WORKING_UNIT_NAME"));
			result.setEMAIL_AD(rs.getString("EMAIL_AD"));
			result.setVETERANS_MILITARY_MARK(rs.getShort("VETERANS_MILITARY_MARK"));
			result.setCARD_NO(rs.getString("CARD_NO"));
			result.setCARD_CD(rs.getString("CARD_CD"));
			result.setCARD_AREA(rs.getString("CARD_AREA"));
			result.setPATIENT_TYPE(rs.getString("PATIENT_TYPE"));
			result.setM_PHONE_NUM(rs.getString("M_PHONE_NUM"));
			result.setWORKING_UNIT_POST(rs.getString("WORKING_UNIT_POST"));
			result.setWORKING_UNIT_ADDR(rs.getString("WORKING_UNIT_ADDR"));
			result.setLIVING_ADDR(rs.getString("LIVING_ADDR"));
			result.setRPR_ADDR(rs.getString("RPR_ADDR"));
			result.setRPR_POST(rs.getString("RPR_POST"));
			result.setRPR_PHONE(rs.getString("RPR_PHONE"));
			result.setLINKMAN_ADDR(rs.getString("LINKMAN_ADDR"));
			result.setLINKMAN_POST(rs.getString("LINKMAN_POST"));
			result.setLINKMAN_PHONE(rs.getString("LINKMAN_PHONE"));
			result.setINSURE_TYPE(rs.getString("INSURE_TYPE"));
			result.setMEDICAL_TREATMENT_NAME(rs.getString("MEDICAL_TREATMENT_NAME"));
			result.setMEDICAL_TREATMENT_CD(rs.getString("MEDICAL_TREATMENT_CD"));
			result.setREGISTER_DATE(rs.getDate("REGISTER_DATE"));
			result.setRECORDER_NAME(rs.getString("RECORDER_NAME"));
			result.setREGISTER_PERSON_CODE(rs.getString("REGISTER_PERSON_CODE"));
			result.setSEND_TIME(rs.getDate("SEND_TIME"));
			result.setSEND_ORG_CODE(rs.getString("SEND_ORG_CODE"));
			result.setSEND_SYSTEM(rs.getString("SEND_SYSTEM"));
			result.setPROVIDER_NAME(rs.getString("PROVIDER_NAME"));
			result.setPROVIDER_ORG_CODE(rs.getString("PROVIDER_ORG_CODE"));
			result.setCREATETIME(rs.getDate("CREATETIME"));
			result.setLASTUPTIME(rs.getDate("LASTUPTIME"));
			result.setSTATE(rs.getShort("STATE"));
			result.setFIELD_PK(rs.getString("FIELD_PK"));
			result.setGENDER_CS(rs.getString("GENDER_CS"));
			result.setGENDER_CSN(rs.getString("GENDER_CSN"));
			result.setGENDER_CSV(rs.getString("GENDER_CSV"));
			result.setGENDER_DN(rs.getString("GENDER_DN"));
			result.setCARD_MARITAL_ST_CS(rs.getString("CARD_MARITAL_ST_CS"));
			result.setCARD_MARITAL_ST_CSN(rs.getString("CARD_MARITAL_ST_CSN"));
			result.setCARD_MARITAL_ST_CSV(rs.getString("CARD_MARITAL_ST_CSV"));
			result.setCARD_MARITAL_ST_DN(rs.getString("CARD_MARITAL_ST_DN"));
			result.setID_NO_CS(rs.getString("ID_NO_CS"));
			result.setID_NO_CSN(rs.getString("ID_NO_CSN"));
			result.setID_NO_CSV(rs.getString("ID_NO_CSV"));
			result.setID_NO_DN(rs.getString("ID_NO_DN"));
			result.setMEDICARE_CS(rs.getString("MEDICARE_CS"));
			result.setMEDICARE_CSN(rs.getString("MEDICARE_CSN"));
			result.setMEDICARE_CSV(rs.getString("MEDICARE_CSV"));
			result.setMEDICARE_DN(rs.getString("MEDICARE_DN"));
			result.setNH_CARD(rs.getString("NH_CARD"));
			result.setSSCID(rs.getString("SSCID"));
			result.setCARD_OCCU_TYPE_CS(rs.getString("CARD_OCCU_TYPE_CS"));
			result.setCARD_OCCU_TYPE_CSN(rs.getString("CARD_OCCU_TYPE_CSN"));
			result.setCARD_OCCU_TYPE_CSV(rs.getString("CARD_OCCU_TYPE_CSV"));
			result.setCARD_OCCU_TYPE_DN(rs.getString("CARD_OCCU_TYPE_DN"));
			result.setCARD_NATION_CS(rs.getString("CARD_NATION_CS"));
			result.setCARD_NATION_CSN(rs.getString("CARD_NATION_CSN"));
			result.setCARD_NATION_CSV(rs.getString("CARD_NATION_CSV"));
			result.setCARD_NATION_DN(rs.getString("CARD_NATION_DN"));
			result.setNATIONALITY_CS(rs.getString("NATIONALITY_CS"));
			result.setNATIONALITY_CSN(rs.getString("NATIONALITY_CSN"));
			result.setNATIONALITY_CSV(rs.getString("NATIONALITY_CSV"));
			result.setNATIONALITY_DN(rs.getString("NATIONALITY_DN"));
			result.setAR_CS(rs.getString("AR_CS"));
			result.setAR_CSN(rs.getString("AR_CSN"));
			result.setAR_CSV(rs.getString("AR_CSV"));
			result.setAR_DN(rs.getString("AR_DN"));
			result.setAD_CS(rs.getString("AD_CS"));
			result.setAD_CSN(rs.getString("AD_CSN"));
			result.setAD_CSV(rs.getString("AD_CSV"));
			result.setAD_DN(rs.getString("AD_DN"));
			result.setTEL_TYPE_CS(rs.getString("TEL_TYPE_CS"));
			result.setTEL_TYPE_CSN(rs.getString("TEL_TYPE_CSN"));
			result.setTEL_TYPE_CSV(rs.getString("TEL_TYPE_CSV"));
			result.setTEL_TYPE_DN(rs.getString("TEL_TYPE_DN"));
			result.setLINKMAN_REL_CS(rs.getString("LINKMAN_REL_CS"));
			result.setLINKMAN_REL_CSN(rs.getString("LINKMAN_REL_CSN"));
			result.setLINKMAN_REL_CSV(rs.getString("LINKMAN_REL_CSV"));
			result.setLINKMAN_REL_DN(rs.getString("LINKMAN_REL_DN"));
			result.setLM_ID_NO_CS(rs.getString("LM_ID_NO_CS"));
			result.setLM_ID_NO_CSN(rs.getString("LM_ID_NO_CSN"));
			result.setLM_ID_NO_CSV(rs.getString("LM_ID_NO_CSV"));
			result.setLM_ID_NO_DN(rs.getString("LM_ID_NO_DN"));
			result.setABO_CS(rs.getString("ABO_CS"));
			result.setABO_CSN(rs.getString("ABO_CSN"));
			result.setABO_CSV(rs.getString("ABO_CSV"));
			result.setABO_DN(rs.getString("ABO_DN"));
			result.setRH_CS(rs.getString("RH_CS"));
			result.setRH_CSN(rs.getString("RH_CSN"));
			result.setRH_CSV(rs.getString("RH_CSV"));
			result.setRH_DN(rs.getString("RH_DN"));
			result.setCARD_ED_BG_CS(rs.getString("CARD_ED_BG_CS"));
			result.setCARD_ED_BG_CSN(rs.getString("CARD_ED_BG_CSN"));
			result.setCARD_ED_BG_CSV(rs.getString("CARD_ED_BG_CSV"));
			result.setCARD_ED_BG_DN(rs.getString("CARD_ED_BG_DN"));
			result.setBIRTH_PLACE_CS(rs.getString("BIRTH_PLACE_CS"));
			result.setBIRTH_PLACE_CSN(rs.getString("BIRTH_PLACE_CSN"));
			result.setBIRTH_PLACE_CSV(rs.getString("BIRTH_PLACE_CSV"));
			result.setBIRTH_PLACE_DN(rs.getString("BIRTH_PLACE_DN"));
			result.setNATIVE_PROVINCE(rs.getString("NATIVE_PROVINCE"));
			result.setNATIVE_PROVINCE_CS(rs.getString("NATIVE_PROVINCE_CS"));
			result.setNATIVE_PROVINCE_CSN(rs.getString("NATIVE_PROVINCE_CSN"));
			result.setNATIVE_PROVINCE_CSV(rs.getString("NATIVE_PROVINCE_CSV"));
			result.setNATIVE_PROVINCE_DN(rs.getString("NATIVE_PROVINCE_DN"));
			result.setNATIVE_CITY_CS(rs.getString("NATIVE_CITY_CS"));
			result.setNATIVE_CITY_CSN(rs.getString("NATIVE_CITY_CSN"));
			result.setNATIVE_CITY_CSV(rs.getString("NATIVE_CITY_CSV"));
			result.setNATIVE_CITY_DN(rs.getString("NATIVE_CITY_DN"));
			result.setVETERANS_MILITARY_VALUE(rs.getString("VETERANS_MILITARY_VALUE"));
			result.setVETERANS_MILITARY_MARK_CS(rs.getString("VETERANS_MILITARY_MARK_CS"));
			result.setVETERANS_MILITARY_MARK_CSN(rs.getString("VETERANS_MILITARY_MARK_CSN"));
			result.setVETERANS_MILITARY_MARK_CSV(rs.getString("VETERANS_MILITARY_MARK_CSV"));
			result.setCARD_CS(rs.getString("CARD_CS"));
			result.setCARD_CSN(rs.getString("CARD_CSN"));
			result.setCARD_CSV(rs.getString("CARD_CSV"));
			result.setCARD_DN(rs.getString("CARD_DN"));
			result.setCARD_AREA_CS(rs.getString("CARD_AREA_CS"));
			result.setCARD_AREA_CSN(rs.getString("CARD_AREA_CSN"));
			result.setCARD_AREA_CSV(rs.getString("CARD_AREA_CSV"));
			result.setCARD_AREA_DN(rs.getString("CARD_AREA_DN"));
			result.setPATIENT_TYPE_VALUE(rs.getString("PATIENT_TYPE_VALUE"));
			result.setPATIENT_TYPE_CS(rs.getString("PATIENT_TYPE_CS"));
			result.setPATIENT_TYPE_CSN(rs.getString("PATIENT_TYPE_CSN"));
			result.setPATIENT_TYPE_CSV(rs.getString("PATIENT_TYPE_CSV"));
			result.setPATIENT_TYPE_DESCR(rs.getString("PATIENT_TYPE_DESCR"));
			result.setWORKING_TEL_NO(rs.getString("WORKING_TEL_NO"));
			result.setMEDICAL_TREATMENT_CS(rs.getString("MEDICAL_TREATMENT_CS"));
			result.setMEDICAL_TREATMENT_CSN(rs.getString("MEDICAL_TREATMENT_CSN"));
			result.setMEDICAL_TREATMENT_CSV(rs.getString("MEDICAL_TREATMENT_CSV"));
			result.setVERSION_NUM(rs.getString("VERSION_NUM"));
			result.setRESPONS_DOC_CODE(rs.getString("RESPONS_DOC_CODE"));
			result.setRESPONS_DOC_NAME(rs.getString("RESPONS_DOC_NAME"));
			result.setOTHER_OCCC_DESCR(rs.getString("OTHER_OCCC_DESCR"));
			result.setUSUAL_TYPE_MARK(rs.getShort("USUAL_TYPE_MARK"));
			result.setSRC_LEVEL(rs.getShort("SRC_LEVEL"));
			result.setTYPE(rs.getString("TYPE"));
			result.setRELATION_PK(rs.getString("RELATION_PK"));
			result.setRELATION_TYPE(rs.getString("RELATION_TYPE"));
			// updated WHN 20170301
			result.setPATIENT_ID(rs.getString("PATIENT_ID"));
			result.setDRUG_ALLERGY_MARK(rs.getString("DRUG_ALLERGY_MARK"));
			result.setOP_HISTORY_MARK(rs.getString("OP_HISTORY_MARK"));
			result.setTRAUMA_HISTORY_MARK(rs.getString("TRAUMA_HISTORY_MARK"));
			result.setBLOOD_TRANSF_MARK(rs.getString("BLOOD_TRANSF_MARK"));
			result.setDISABILITY_MARK(rs.getString("DISABILITY_MARK"));
			result.setGENETIC_DISEASE_HISTORY(rs.getString("GENETIC_DISEASE_HISTORY"));
			result.setEXHAUST_FACILITY_MARK(rs.getString("EXHAUST_FACILITY_MARK"));
			result.setEXHAUST_FACILITY_TYPE_CODE(rs.getString("EXHAUST_FACILITY_TYPE_CODE"));
			result.setFUEL_TYPE_CODE(rs.getString("FUEL_TYPE_CODE"));
			result.setWATER_TYPE_CODE(rs.getString("WATER_TYPE_CODE"));
			result.setTOILET_TYPE_CODE(rs.getString("TOILET_TYPE_CODE"));
			result.setLIVESTOCK_PEN_TYPE_CODE(rs.getString("LIVESTOCK_PEN_TYPE_CODE"));
			result.setOPERATION_HISTORY(rs.getString("OPERATION_HISTORY"));
			result.setASTHMA_MARK(rs.getString("ASTHMA_MARK"));
			result.setHEDRT_DIS_MARK(rs.getString("HEDRT_DIS_MARK"));
			result.setCARDIOVASCULAR_CODE(rs.getString("CARDIOVASCULAR_CODE"));
			result.setEPILEPSY_MARK(rs.getString("EPILEPSY_MARK"));
			result.setCOAGULOPATHY_MARK(rs.getString("COAGULOPATHY_MARK"));
			result.setDIABETES_MARK(rs.getString("DIABETES_MARK"));
			result.setGLAUCOMA_MARK(rs.getString("GLAUCOMA_MARK"));
			result.setDIALYSIS_MARK(rs.getString("DIALYSIS_MARK"));
			result.setORGAN_TRANS_MARK(rs.getString("ORGAN_TRANS_MARK"));
			result.setORGAN_DEFECT_MARK(rs.getString("ORGAN_DEFECT_MARK"));
			result.setREMOVA_PRO_MARK(rs.getString("REMOVA_PRO_MARK"));
			result.setCARDIAC_PAC_MARK(rs.getString("CARDIAC_PAC_MARK"));
			result.setORTHER_MEDICAL_ALERT(rs.getString("ORTHER_MEDICAL_ALERT"));
			result.setPSYCHIATRIC_MARK(rs.getString("PSYCHIATRIC_MARK"));
			return result;
		}
	}

	@Override
	public List<PersonInfo> find(String sql, Object[] args) {
		List<PersonInfo> datas = jdbcTemplate.query(sql, args, new PersonRowMapper());
		return datas;
	}

	@Override
	public List<PersonInfo> findAll() {
		String sql = " select * from mpi_person_info where 1=1 ";
		List<PersonInfo> datas = find(sql);
		return datas;
	}

	@Override
	public int getCount(String sql) {
		return getCount(sql, new Object[] {});
	}

	@Override
	public int getCount(String sql, Object[] args) {
		return jdbcTemplate.queryForObject(sql, args, Integer.class);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Map<String, Object>> findForMap(String sql, Object[] args) {
		return jdbcTemplate.queryForList(sql, args);
	}

	@Override
	public List<Map<String, Object>> findForMap(String sql) {
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<PersonInfo> findWithDomainInfo(String sql, Object[] args) {
		List<PersonInfo> datas = jdbcTemplate.query(sql, args, new PersonRowMapper() {
			@Override
			public PersonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				PersonInfo p = super.mapRow(rs, rowNum);
				// p.setDOMAINUNIQUESIGN(rs.getString("UNIQUE_SIGN"));
				return p;
			}

		});
		return datas;
	}

	public Map<String, Object> findById(String person_id, String org_code, String type) {
		StringBuilder sql = new StringBuilder();
		List<Map<String, Object>> list = null;
		if ("0".equals(type)) {// 个人
			sql.append("select * from mpi_person_info a where a.type='" + type + "' and a.hr_id='" + person_id
					+ "' and a.REGISTER_ORG_CODE='" + org_code + "'");
		} else if ("1".equals(type)) {// 患者
			sql.append("select * from mpi_person_info a where a.type='" + type + "' and a.MEDICALSERVICE_NO='"
					+ person_id + "' and a.REGISTER_ORG_CODE='" + org_code + "'");
		} else if ("3".equals(type)) {
			sql.append("select * from mpi_person_info a where a.type='" + type + "' and a.PATIENT_ID='" + person_id
					+ "' and a.REGISTER_ORG_CODE='" + org_code + "'");
		}
		try {
			list = jdbcTemplate.queryForList(sql.toString());
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public PersonInfo findByPK(String fieldpk) {
		String sql = " select * from mpi_person_info where field_pk = ?";
		List<PersonInfo> datas = find(sql, new Object[] { fieldpk });
		PersonInfo result = null;
		if (datas != null && !datas.isEmpty()) {
			result = datas.get(0);
		}
		return result;
	}

	@Override
	public void addHistory(final PersonInfo entity) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into ").append(PERSONTABLEHISTORY).append("( register_org_code,")
				.append("register_org_name,").append("medicalservice_no,").append("name_cn,").append("name_en,")
				.append("remark,").append("birth_date,").append("gender_cd,").append("card_marital_st_cd,")
				.append("id_no_cd,").append("id_no,").append("medicare_cd,").append("medical_insurance_no,")
				.append("card_occu_type_cd,").append("card_nation_cd,").append("nationality_cd,").append("ar_cd,")
				.append("ad_cd,").append("province_name,").append("city_name,").append("area_name,")
				.append("street_id,").append("village_name,").append("house_no,").append("tel_type_cd,")
				.append("person_tel_no,").append("linkman_name,").append("linkman_rel_cd,").append("lm_id_no_cd,")
				.append("lm_id_no,").append("hr_id,").append("post_cd,").append("abo_cd,").append("rh_cd,")
				.append("rh_neg_cd,").append("card_ed_bg_cd,").append("birth_place,").append("working_unit_name,")
				.append("email_ad,").append("veterans_military_mark,").append("card_no,").append("card_cd,")
				.append("card_area,").append("patient_type,").append("m_phone_num,").append("working_unit_post,")
				.append("working_unit_addr,").append("living_addr,").append("rpr_addr,").append("rpr_post,")
				.append("rpr_phone,").append("linkman_addr,").append("linkman_post,").append("linkman_phone,")
				.append("insure_type,").append("medical_treatment_name,").append("medical_treatment_cd,")
				.append("register_date,").append("recorder_name,").append("register_person_code,").append("send_time,")
				.append("send_org_code,").append("send_system,").append("provider_name,").append("provider_org_code,")
				.append("createtime,").append("lastuptime,").append("state,").append("field_pk,").append("gender_cs,")
				.append("gender_csn,").append("gender_csv,").append("gender_dn,").append("card_marital_st_cs,")
				.append("card_marital_st_csn,").append("card_marital_st_csv,").append("card_marital_st_dn,")
				.append("id_no_cs,").append("id_no_csn,").append("id_no_csv,").append("id_no_dn,")
				.append("medicare_cs,").append("medicare_csn,").append("medicare_csv,").append("medicare_dn,")
				.append("nh_card,").append("sscid,").append("card_occu_type_cs,").append("card_occu_type_csn,")
				.append("card_occu_type_csv,").append("card_occu_type_dn,").append("card_nation_cs,")
				.append("card_nation_csn,").append("card_nation_csv,").append("card_nation_dn,")
				.append("nationality_cs,").append("nationality_csn,").append("nationality_csv,")
				.append("nationality_dn,").append("ar_cs,").append("ar_csn,").append("ar_csv,").append("ar_dn,")
				.append("ad_cs,").append("ad_csn,").append("ad_csv,").append("ad_dn,").append("tel_type_cs,")
				.append("tel_type_csn,").append("tel_type_csv,").append("tel_type_dn,").append("linkman_rel_cs,")
				.append("linkman_rel_csn,").append("linkman_rel_csv,").append("linkman_rel_dn,").append("lm_id_no_cs,")
				.append("lm_id_no_csn,").append("lm_id_no_csv,").append("lm_id_no_dn,").append("abo_cs,")
				.append("abo_csn,").append("abo_csv,").append("abo_dn,").append("rh_cs,").append("rh_csn,")
				.append("rh_csv,").append("rh_dn,").append("card_ed_bg_cs,").append("card_ed_bg_csn,")
				.append("card_ed_bg_csv,").append("card_ed_bg_dn,").append("birth_place_cs,").append("birth_place_csn,")
				.append("birth_place_csv,").append("birth_place_dn,").append("native_province,")
				.append("native_province_cs,").append("native_province_csn,").append("native_province_csv,")
				.append("native_province_dn,").append("native_city_cs,").append("native_city_csn,")
				.append("native_city_csv,").append("native_city_dn,").append("VETERANS_MILITARY_VALUE,")
				.append("veterans_military_mark_cs,").append("veterans_military_mark_csn,")
				.append("veterans_military_mark_csv,").append("card_cs,").append("card_csn,").append("card_csv,")
				.append("card_dn,").append("card_area_cs,").append("card_area_csn,").append("card_area_csv,")
				.append("card_area_dn,").append("PATIENT_TYPE_VALUE,").append("patient_type_cs,")
				.append("patient_type_csn,").append("patient_type_csv,").append("patient_type_descr,")
				.append("working_tel_no,").append("medical_treatment_cs,").append("medical_treatment_csn,")
				.append("medical_treatment_csv,").append("version_num,").append("respons_doc_code,")
				.append("respons_doc_name,").append("other_occc_descr,").append("usual_type_mark,").append("src_level,")
				.append("type,").append("relation_pk,").append("relation_type,").append("patient_id,")
				.append("drug_allergy_mark,").append("op_history_mark,").append("trauma_history_mark,")
				.append("blood_transf_mark,").append("disability_mark,").append("genetic_disease_history,")
				.append("exhaust_facility_mark,").append("exhaust_facility_type_code,").append("fuel_type_code,")
				.append("water_type_code,").append("toilet_type_code,").append("livestock_pen_type_code,")
				.append("operation_history,").append("asthma_mark,").append("hedrt_dis_mark,")
				.append("cardiovascular_code,").append("epilepsy_mark,").append("coagulopathy_mark,")
				.append("diabetes_mark,").append("glaucoma_mark,").append("dialysis_mark,").append("organ_trans_mark,")
				.append("organ_defect_mark,").append("remova_pro_mark,").append("cardiac_pac_mark,")
				.append("orther_medical_alert,").append("psychiatric_mark )")
				.append("values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," + "?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) {
				try {
					ps.setString(1, entity.getREGISTER_ORG_CODE());
					ps.setString(2, entity.getREGISTER_ORG_NAME());
					ps.setString(3, entity.getMEDICALSERVICE_NO());
					ps.setString(4, entity.getNAME_CN());
					ps.setString(5, entity.getNAME_EN());
					ps.setString(6, entity.getREMARK());
					// ps.setDate(7, new java.sql.Date(entity.getBIRTH_DATE().getTime()));
					ps.setDate(7, entity.getBIRTH_DATE() == null ? null
							: new java.sql.Date(entity.getBIRTH_DATE().getTime()));
					ps.setString(8, entity.getGENDER_CD());
					ps.setString(9, entity.getCARD_MARITAL_ST_CD());
					ps.setString(10, entity.getID_NO_CD());
					ps.setString(11, entity.getID_NO());
					ps.setString(12, entity.getMEDICARE_CD());
					ps.setString(13, entity.getMEDICAL_INSURANCE_NO());
					ps.setString(14, entity.getCARD_OCCU_TYPE_CD());
					ps.setString(15, entity.getCARD_NATION_CD());
					ps.setString(16, entity.getNATIONALITY_CD());
					ps.setString(17, entity.getAR_CD());
					ps.setString(18, entity.getAD_CD());
					ps.setString(19, entity.getPROVINCE_NAME());
					ps.setString(20, entity.getCITY_NAME());
					ps.setString(21, entity.getAREA_NAME());
					ps.setString(22, entity.getSTREET_ID());
					ps.setString(23, entity.getVILLAGE_NAME());
					ps.setString(24, entity.getHOUSE_NO());
					ps.setString(25, entity.getTEL_TYPE_CD());
					ps.setString(26, entity.getPERSON_TEL_NO());
					ps.setString(27, entity.getLINKMAN_NAME());
					ps.setString(28, entity.getLINKMAN_REL_CD());
					ps.setString(29, entity.getLM_ID_NO_CD());
					ps.setString(30, entity.getLM_ID_NO());
					ps.setString(31, entity.getHR_ID());
					ps.setString(32, entity.getPOST_CD());
					ps.setString(33, entity.getABO_CD());
					ps.setString(34, entity.getRH_CD());
					ps.setString(35, entity.getRH_NEG_CD());
					ps.setString(36, entity.getCARD_ED_BG_CD());
					ps.setString(37, entity.getBIRTH_PLACE());
					ps.setString(38, entity.getWORKING_UNIT_NAME());
					ps.setString(39, entity.getEMAIL_AD());
					ps.setShort(40,
							entity.getVETERANS_MILITARY_MARK() == null ? 0 : entity.getVETERANS_MILITARY_MARK());
					ps.setString(41, entity.getCARD_NO());
					ps.setString(42, entity.getCARD_CD());
					ps.setString(43, entity.getCARD_AREA());
					ps.setString(44, entity.getPATIENT_TYPE());
					ps.setString(45, entity.getM_PHONE_NUM());
					ps.setString(46, entity.getWORKING_UNIT_POST());
					ps.setString(47, entity.getWORKING_UNIT_ADDR());
					ps.setString(48, entity.getLIVING_ADDR());
					ps.setString(49, entity.getRPR_ADDR());
					ps.setString(50, entity.getRPR_POST());
					ps.setString(51, entity.getRPR_PHONE());
					ps.setString(52, entity.getLINKMAN_ADDR());
					ps.setString(53, entity.getLINKMAN_POST());
					ps.setString(54, entity.getLINKMAN_PHONE());
					ps.setString(55, entity.getINSURE_TYPE());
					ps.setString(56, entity.getMEDICAL_TREATMENT_NAME());
					ps.setString(57, entity.getMEDICAL_TREATMENT_CD());
					/*
					 * ps.setDate(58, new java.sql.Date(entity.getREGISTER_DATE() .getTime()));
					 */
					ps.setDate(58, entity.getREGISTER_DATE() == null ? null
							: new java.sql.Date(entity.getREGISTER_DATE().getTime()));
					ps.setString(59, entity.getRECORDER_NAME());
					ps.setString(60, entity.getREGISTER_PERSON_CODE());
					ps.setDate(61,
							entity.getSEND_TIME() == null ? null : new java.sql.Date(entity.getSEND_TIME().getTime()));
					ps.setString(62, entity.getSEND_ORG_CODE());
					ps.setString(63, entity.getSEND_SYSTEM());
					ps.setString(64, entity.getPROVIDER_NAME());
					ps.setString(65, entity.getPROVIDER_ORG_CODE());
					ps.setDate(66, entity.getCREATETIME() == null ? null
							: new java.sql.Date(entity.getCREATETIME().getTime()));
					ps.setDate(67, entity.getLASTUPTIME() == null ? null
							: new java.sql.Date(entity.getLASTUPTIME().getTime()));
					ps.setShort(68, entity.getSTATE());
					ps.setString(69, entity.getFIELD_PK());
					ps.setString(70, entity.getGENDER_CS());
					ps.setString(71, entity.getGENDER_CSN());
					ps.setString(72, entity.getGENDER_CSV());
					ps.setString(73, entity.getGENDER_DN());
					ps.setString(74, entity.getCARD_MARITAL_ST_CS());
					ps.setString(75, entity.getCARD_MARITAL_ST_CSN());
					ps.setString(76, entity.getCARD_MARITAL_ST_CSV());
					ps.setString(77, entity.getCARD_MARITAL_ST_DN());
					ps.setString(78, entity.getID_NO_CS());
					ps.setString(79, entity.getID_NO_CSN());
					ps.setString(80, entity.getID_NO_CSV());
					ps.setString(81, entity.getID_NO_DN());
					ps.setString(82, entity.getMEDICARE_CS());
					ps.setString(83, entity.getMEDICARE_CSN());
					ps.setString(84, entity.getMEDICARE_CSV());
					ps.setString(85, entity.getMEDICARE_DN());
					ps.setString(86, entity.getNH_CARD());
					ps.setString(87, entity.getSSCID());
					ps.setString(88, entity.getCARD_OCCU_TYPE_CS());
					ps.setString(89, entity.getCARD_OCCU_TYPE_CSN());
					ps.setString(90, entity.getCARD_OCCU_TYPE_CSV());
					ps.setString(91, entity.getCARD_OCCU_TYPE_DN());
					ps.setString(92, entity.getCARD_NATION_CS());
					ps.setString(93, entity.getCARD_NATION_CSN());
					ps.setString(94, entity.getCARD_NATION_CSV());
					ps.setString(95, entity.getCARD_NATION_DN());
					ps.setString(96, entity.getNATIONALITY_CS());
					ps.setString(97, entity.getNATIONALITY_CSN());
					ps.setString(98, entity.getNATIONALITY_CSV());
					ps.setString(99, entity.getNATIONALITY_DN());
					ps.setString(100, entity.getAR_CS());
					ps.setString(101, entity.getAR_CSN());
					ps.setString(102, entity.getAR_CSV());
					ps.setString(103, entity.getAR_DN());
					ps.setString(104, entity.getAD_CS());
					ps.setString(105, entity.getAD_CSN());
					ps.setString(106, entity.getAD_CSV());
					ps.setString(107, entity.getAD_DN());
					ps.setString(108, entity.getTEL_TYPE_CS());
					ps.setString(109, entity.getTEL_TYPE_CSN());
					ps.setString(110, entity.getTEL_TYPE_CSV());
					ps.setString(111, entity.getTEL_TYPE_DN());
					ps.setString(112, entity.getLINKMAN_REL_CS());
					ps.setString(113, entity.getLINKMAN_REL_CSN());
					ps.setString(114, entity.getLINKMAN_REL_CSV());
					ps.setString(115, entity.getLINKMAN_REL_DN());
					ps.setString(116, entity.getLM_ID_NO_CS());
					ps.setString(117, entity.getLM_ID_NO_CSN());
					ps.setString(118, entity.getLM_ID_NO_CSV());
					ps.setString(119, entity.getLM_ID_NO_DN());
					ps.setString(120, entity.getABO_CS());
					ps.setString(121, entity.getABO_CSN());
					ps.setString(122, entity.getABO_CSV());
					ps.setString(123, entity.getABO_DN());
					ps.setString(124, entity.getRH_CS());
					ps.setString(125, entity.getRH_CSN());
					ps.setString(126, entity.getRH_CSV());
					ps.setString(127, entity.getRH_DN());
					ps.setString(128, entity.getCARD_ED_BG_CS());
					ps.setString(129, entity.getCARD_ED_BG_CSN());
					ps.setString(130, entity.getCARD_ED_BG_CSV());
					ps.setString(131, entity.getCARD_ED_BG_DN());
					ps.setString(132, entity.getBIRTH_PLACE_CS());
					ps.setString(133, entity.getBIRTH_PLACE_CSN());
					ps.setString(134, entity.getBIRTH_PLACE_CSV());
					ps.setString(135, entity.getBIRTH_PLACE_DN());
					ps.setString(136, entity.getNATIVE_PROVINCE());
					ps.setString(137, entity.getNATIVE_PROVINCE_CS());
					ps.setString(138, entity.getNATIVE_PROVINCE_CSN());
					ps.setString(139, entity.getNATIVE_PROVINCE_CSV());
					ps.setString(140, entity.getNATIVE_PROVINCE_DN());
					ps.setString(141, entity.getNATIVE_CITY_CS());
					ps.setString(142, entity.getNATIVE_CITY_CSN());
					ps.setString(143, entity.getNATIVE_CITY_CSV());
					ps.setString(144, entity.getNATIVE_CITY_DN());
					ps.setString(145, entity.getVETERANS_MILITARY_VALUE());
					ps.setString(146, entity.getVETERANS_MILITARY_MARK_CS());
					ps.setString(147, entity.getVETERANS_MILITARY_MARK_CSN());
					ps.setString(148, entity.getVETERANS_MILITARY_MARK_CSV());
					ps.setString(149, entity.getCARD_CS());
					ps.setString(150, entity.getCARD_CSN());
					ps.setString(151, entity.getCARD_CSV());
					ps.setString(152, entity.getCARD_DN());
					ps.setString(153, entity.getCARD_AREA_CS());
					ps.setString(154, entity.getCARD_AREA_CSN());
					ps.setString(155, entity.getCARD_AREA_CSV());
					ps.setString(156, entity.getCARD_AREA_DN());
					ps.setString(157, entity.getPATIENT_TYPE_VALUE());
					ps.setString(158, entity.getPATIENT_TYPE_CS());
					ps.setString(159, entity.getPATIENT_TYPE_CSN());
					ps.setString(160, entity.getPATIENT_TYPE_CSV());
					ps.setString(161, entity.getPATIENT_TYPE_DESCR());
					ps.setString(162, entity.getWORKING_TEL_NO());
					ps.setString(163, entity.getMEDICAL_TREATMENT_CS());
					ps.setString(164, entity.getMEDICAL_TREATMENT_CSN());
					ps.setString(165, entity.getMEDICAL_TREATMENT_CSV());
					ps.setString(166, entity.getVERSION_NUM());
					ps.setString(167, entity.getRESPONS_DOC_CODE());
					ps.setString(168, entity.getRESPONS_DOC_NAME());
					ps.setString(169, entity.getOTHER_OCCC_DESCR());
					ps.setShort(170, entity.getUSUAL_TYPE_MARK() == null ? 0 : entity.getUSUAL_TYPE_MARK());
					ps.setShort(171, entity.getSRC_LEVEL());
					ps.setString(172, entity.getTYPE());
					ps.setString(173, entity.getRELATION_PK());
					ps.setString(174, entity.getRELATION_TYPE());
					// updated WHN 20170301
					ps.setString(175, entity.getPATIENT_ID());
					ps.setString(176, entity.getDRUG_ALLERGY_MARK());
					ps.setString(177, entity.getOP_HISTORY_MARK());
					ps.setString(178, entity.getTRAUMA_HISTORY_MARK());
					ps.setString(179, entity.getBLOOD_TRANSF_MARK());
					ps.setString(180, entity.getDISABILITY_MARK());
					ps.setString(181, entity.getGENETIC_DISEASE_HISTORY());
					ps.setString(182, entity.getEXHAUST_FACILITY_MARK());
					ps.setString(183, entity.getEXHAUST_FACILITY_TYPE_CODE());
					ps.setString(184, entity.getFUEL_TYPE_CODE());
					ps.setString(185, entity.getWATER_TYPE_CODE());
					ps.setString(186, entity.getTOILET_TYPE_CODE());
					ps.setString(187, entity.getLIVESTOCK_PEN_TYPE_CODE());
					ps.setString(188, entity.getOPERATION_HISTORY());
					ps.setString(189, entity.getASTHMA_MARK());
					ps.setString(190, entity.getHEDRT_DIS_MARK());
					ps.setString(191, entity.getCARDIOVASCULAR_CODE());
					ps.setString(192, entity.getEPILEPSY_MARK());
					ps.setString(193, entity.getCOAGULOPATHY_MARK());
					ps.setString(194, entity.getDIABETES_MARK());
					ps.setString(195, entity.getGLAUCOMA_MARK());
					ps.setString(196, entity.getDIALYSIS_MARK());
					ps.setString(197, entity.getORGAN_TRANS_MARK());
					ps.setString(198, entity.getORGAN_DEFECT_MARK());
					ps.setString(199, entity.getREMOVA_PRO_MARK());
					ps.setString(200, entity.getCARDIAC_PAC_MARK());
					ps.setString(201, entity.getORTHER_MEDICAL_ALERT());
					ps.setString(202, entity.getPSYCHIATRIC_MARK());
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	@Override
	public Map<String, Object> findById(String person_id, String org_code) {
		return null;
	}

}
