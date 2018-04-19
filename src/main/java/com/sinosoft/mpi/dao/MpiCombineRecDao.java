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

import com.sinosoft.mpi.model.MpiCombineRec;

@Repository("mpiCombineRecDao")
public class MpiCombineRecDao implements IMpiCombineRecDao {
	@Resource
	JdbcTemplate jdbcTemplate;

	@Override
	public void add(final MpiCombineRec entity) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into MPI_COMBINE_REC( ")
				.append("REGISTER_ORG_CODE,")
				.append("MEDICALSERVICE_NO,")
				.append("NAME_CN,")
				.append("NAME_EN,")
				.append("BIRTH_DATE,")
				.append("GENDER_CD,")
				.append("CARD_MARITAL_ST_CD,")
				.append("ID_NO_CD,")
				.append("ID_NO,")
				.append("MEDICARE_CD,")
				.append("MEDICAL_INSURANCE_NO,")
				.append("CARD_OCCU_TYPE_CD,")
				.append("CARD_NATION_CD,")
				.append("NATIONALITY_CD,")
				.append("AR_CD,")
				.append("AD_CD,")
				.append("PROVINCE_NAME,")
				.append("CITY_NAME,")
				.append("AREA_NAME,")
				.append("STREET_ID,")
				.append("VILLAGE_NAME,")
				.append("HOUSE_NO,")
				.append("TEL_TYPE_CD,")
				.append("PERSON_TEL_NO,")
				.append("LINKMAN_NAME,")
				.append("LINKMAN_REL_CD,")
				.append("LM_ID_NO_CD,")
				.append("LM_ID_NO,")
				.append("HR_ID,")
				.append("POST_CD,")
				.append("ABO_CD,")
				.append("RH_CD,")
				.append("RH_NEG_CD,")
				.append("CARD_ED_BG_CD,")
				.append("BIRTH_PLACE,")
				.append("WORKING_UNIT_NAME,")
				.append("EMAIL_AD,")
				.append("VETERANS_MILITARY_MARK,")
				.append("CARD_NO,")
				.append("CARD_CD,")
				.append("CARD_AREA,")
				.append("PATIENT_TYPE,")
				.append("M_PHONE_NUM,")
				.append("WORKING_UNIT_POST,")
				.append("WORKING_UNIT_ADDR,")
				.append("LIVING_ADDR,")
				.append("RPR_ADDR,")
				.append("RPR_POST,")
				.append("RPR_PHONE,")
				.append("LINKMAN_ADDR,")
				.append("LINKMAN_POST,")
				.append("LINKMAN_PHONE,")
				.append("INSURE_TYPE,")
				.append("MEDICAL_TREATMENT_NAME,")
				.append("MEDICAL_TREATMENT_CD,")
				.append("REGISTER_DATE,")
				.append("RECORDER_NAME,")
				.append("REGISTER_PERSON_CODE,")
				.append("SEND_TIME,")
				.append("SEND_ORG_CODE,")
				.append("SEND_SYSTEM,")
				.append("PROVIDER_NAME,")
				.append("PROVIDER_ORG_CODE,")
				.append("CREATETIME,")
				.append("LASTUPTIME,")
				.append("STATE,")
				.append("GENDER_CS,")
				.append("GENDER_CSN,")
				.append("GENDER_CSV,")
				.append("GENDER_DN,")
				.append("CARD_MARITAL_ST_CS,")
				.append("CARD_MARITAL_ST_CSN,")
				.append("CARD_MARITAL_ST_CSV,")
				.append("CARD_MARITAL_ST_DN,")
				.append("ID_NO_CS,")
				.append("ID_NO_CSN,")
				.append("ID_NO_CSV,")
				.append("ID_NO_DN,")
				.append("MEDICARE_CS,")
				.append("MEDICARE_CSN,")
				.append("MEDICARE_CSV,")
				.append("MEDICARE_DN,")
				.append("NH_CARD,")
				.append("SSCID,")
				.append("CARD_OCCU_TYPE_CS,")
				.append("CARD_OCCU_TYPE_CSN,")
				.append("CARD_OCCU_TYPE_CSV,")
				.append("CARD_OCCU_TYPE_DN,")
				.append("CARD_NATION_CS,")
				.append("CARD_NATION_CSN,")
				.append("CARD_NATION_CSV,")
				.append("CARD_NATION_DN,")
				.append("NATIONALITY_CS,")
				.append("NATIONALITY_CSN,")
				.append("NATIONALITY_CSV,")
				.append("NATIONALITY_DN,")
				.append("AR_CS,")
				.append("AR_CSN,")
				.append("AR_CSV,")
				.append("AR_DN,")
				.append("AD_CS,")
				.append("AD_CSN,")
				.append("AD_CSV,")
				.append("AD_DN,")
				.append("TEL_TYPE_CS,")
				.append("TEL_TYPE_CSN,")
				.append("TEL_TYPE_CSV,")
				.append("TEL_TYPE_DN,")
				.append("LINKMAN_REL_CS,")
				.append("LINKMAN_REL_CSN,")
				.append("LINKMAN_REL_CSV,")
				.append("LINKMAN_REL_DN,")
				.append("LM_ID_NO_CS,")
				.append("LM_ID_NO_CSN,")
				.append("LM_ID_NO_CSV,")
				.append("LM_ID_NO_DN,")
				.append("ABO_CS,")
				.append("ABO_CSN,")
				.append("ABO_CSV,")
				.append("ABO_DN,")
				.append("RH_CS,")
				.append("RH_CSN,")
				.append("RH_CSV,")
				.append("RH_DN,")
				.append("CARD_ED_BG_CS,")
				.append("CARD_ED_BG_CSN,")
				.append("CARD_ED_BG_CSV,")
				.append("CARD_ED_BG_DN,")
				.append("BIRTH_PLACE_CS,")
				.append("BIRTH_PLACE_CSN,")
				.append("BIRTH_PLACE_CSV,")
				.append("BIRTH_PLACE_DN,")
				.append("NATIVE_PROVINCE,")
				.append("NATIVE_PROVINCE_CS,")
				.append("NATIVE_PROVINCE_CSN,")
				.append("NATIVE_PROVINCE_CSV,")
				.append("NATIVE_PROVINCE_DN,")
				.append("NATIVE_CITY_CS,")
				.append("NATIVE_CITY_CSN,")
				.append("NATIVE_CITY_CSV,")
				.append("NATIVE_CITY_DN,")
				.append("VETERANS_MILITARY_VALUE,")
				.append("VETERANS_MILITARY_MARK_CS,")
				.append("VETERANS_MILITARY_MARK_CSN,")
				.append("VETERANS_MILITARY_MARK_CSV,")
				.append("CARD_CS,")
				.append("CARD_CSN,")
				.append("CARD_CSV,")
				.append("CARD_DN,")
				.append("CARD_AREA_CS,")
				.append("CARD_AREA_CSN,")
				.append("CARD_AREA_CSV,")
				.append("CARD_AREA_DN,")
				.append("PATIENT_TYPE_VALUE,")
				.append("PATIENT_TYPE_CS,")
				.append("PATIENT_TYPE_CSN,")
				.append("PATIENT_TYPE_CSV,")
				.append("PATIENT_TYPE_DESCR,")
				.append("WORKING_TEL_NO,")
				.append("MEDICAL_TREATMENT_CS,")
				.append("MEDICAL_TREATMENT_CSN,")
				.append("MEDICAL_TREATMENT_CSV,")
				.append("VERSION_NUM,")
				.append("REGISTER_ORG_NAME,")
				.append("REMARK,")
				.append("RESPONS_DOC_CODE,")
				.append("RESPONS_DOC_NAME,")
				.append("OTHER_OCCC_DESCR,")
				.append("USUAL_TYPE_MARK,")
				.append("NATIVE_CITY,")
				.append("COMBINE_NO,")
				.append("SRC_LEVEL,")
				//updated WHN 20170301
				.append("patient_id,")
				.append("drug_allergy_mark,")
				.append("op_history_mark,")
				.append("trauma_history_mark,")
				.append("blood_transf_mark,")
				.append("disability_mark,")
				.append("genetic_disease_history,")
				.append("exhaust_facility_mark,")
				.append("exhaust_facility_type_code,")
				.append("fuel_type_code,")
				.append("water_type_code,")
				.append("toilet_type_code,")
				.append("livestock_pen_type_code,")
				.append("operation_history,")
				.append("asthma_mark,")
				.append("hedrt_dis_mark,")
				.append("cardiovascular_code,")
				.append("epilepsy_mark,")
				.append("coagulopathy_mark,")
				.append("diabetes_mark,")
				.append("glaucoma_mark,")
				.append("dialysis_mark,")
				.append("organ_trans_mark,")
				.append("organ_defect_mark,")
				.append("remova_pro_mark,")
				.append("cardiac_pac_mark,")
				.append("orther_medical_alert,")
				.append("psychiatric_mark )")
				.append("values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,"
						+"?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getREGISTER_ORG_CODE());
				ps.setString(2, entity.getMEDICALSERVICE_NO());
				ps.setString(3, entity.getNAME_CN());
				ps.setString(4, entity.getNAME_EN());
				ps.setDate(5, new java.sql.Date(entity.getBIRTH_DATE()
						.getTime()));
				ps.setString(6, entity.getGENDER_CD());
				ps.setString(7, entity.getCARD_MARITAL_ST_CD());
				ps.setString(8, entity.getID_NO_CD());
				ps.setString(9, entity.getID_NO());
				ps.setString(10, entity.getMEDICARE_CD());
				ps.setString(11, entity.getMEDICAL_INSURANCE_NO());
				ps.setString(12, entity.getCARD_OCCU_TYPE_CD());
				ps.setString(13, entity.getCARD_NATION_CD());
				ps.setString(14, entity.getNATIONALITY_CD());
				ps.setString(15, entity.getAR_CD());
				ps.setString(16, entity.getAD_CD());
				ps.setString(17, entity.getPROVINCE_NAME());
				ps.setString(18, entity.getCITY_NAME());
				ps.setString(19, entity.getAREA_NAME());
				ps.setString(20, entity.getSTREET_ID());
				ps.setString(21, entity.getVILLAGE_NAME());
				ps.setString(22, entity.getHOUSE_NO());
				ps.setString(23, entity.getTEL_TYPE_CD());
				ps.setString(24, entity.getPERSON_TEL_NO());
				ps.setString(25, entity.getLINKMAN_NAME());
				ps.setString(26, entity.getLINKMAN_REL_CD());
				ps.setString(27, entity.getLM_ID_NO_CD());
				ps.setString(28, entity.getLM_ID_NO());
				ps.setString(29, entity.getHR_ID());
				ps.setString(30, entity.getPOST_CD());
				ps.setString(31, entity.getABO_CD());
				ps.setString(32, entity.getRH_CD());
				ps.setString(33, entity.getRH_NEG_CD());
				ps.setString(34, entity.getCARD_ED_BG_CD());
				ps.setString(35, entity.getBIRTH_PLACE());
				ps.setString(36, entity.getWORKING_UNIT_NAME());
				ps.setString(37, entity.getEMAIL_AD());
				ps.setShort(38, entity.getVETERANS_MILITARY_MARK()==null?0: entity.getVETERANS_MILITARY_MARK());
				ps.setString(39, entity.getCARD_NO());
				ps.setString(40, entity.getCARD_CD());
				ps.setString(41, entity.getCARD_AREA());
				ps.setString(42, entity.getPATIENT_TYPE());
				ps.setString(43, entity.getM_PHONE_NUM());
				ps.setString(44, entity.getWORKING_UNIT_POST());
				ps.setString(45, entity.getWORKING_UNIT_ADDR());
				ps.setString(46, entity.getLIVING_ADDR());
				ps.setString(47, entity.getRPR_ADDR());
				ps.setString(48, entity.getRPR_POST());
				ps.setString(49, entity.getRPR_PHONE());
				ps.setString(50, entity.getLINKMAN_ADDR());
				ps.setString(51, entity.getLINKMAN_POST());
				ps.setString(52, entity.getLINKMAN_PHONE());
				ps.setString(53, entity.getINSURE_TYPE());
				ps.setString(54, entity.getMEDICAL_TREATMENT_NAME());
				ps.setString(55, entity.getMEDICAL_TREATMENT_CD());
				ps.setDate(56, new java.sql.Date(entity.getREGISTER_DATE()
						.getTime()));
				ps.setString(57, entity.getRECORDER_NAME());
				ps.setString(58, entity.getREGISTER_PERSON_CODE());
				ps.setDate(59, new java.sql.Date(entity.getSEND_TIME()
						.getTime()));
				ps.setString(60, entity.getSEND_ORG_CODE());
				ps.setString(61, entity.getSEND_SYSTEM());
				ps.setString(62, entity.getPROVIDER_NAME());
				ps.setString(63, entity.getPROVIDER_ORG_CODE());
				ps.setDate(64, new java.sql.Date(entity.getCREATETIME()
						.getTime()));
				ps.setDate(65, entity.getLASTUPTIME()==null?null:new java.sql.Date(entity.getLASTUPTIME()
						.getTime()));
				ps.setShort(66, entity.getSTATE());
				ps.setString(67, entity.getGENDER_CS());
				ps.setString(68, entity.getGENDER_CSN());
				ps.setString(69, entity.getGENDER_CSV());
				ps.setString(70, entity.getGENDER_DN());
				ps.setString(71, entity.getCARD_MARITAL_ST_CS());
				ps.setString(72, entity.getCARD_MARITAL_ST_CSN());
				ps.setString(73, entity.getCARD_MARITAL_ST_CSV());
				ps.setString(74, entity.getCARD_MARITAL_ST_DN());
				ps.setString(75, entity.getID_NO_CS());
				ps.setString(76, entity.getID_NO_CSN());
				ps.setString(77, entity.getID_NO_CSV());
				ps.setString(78, entity.getID_NO_DN());
				ps.setString(79, entity.getMEDICARE_CS());
				ps.setString(80, entity.getMEDICARE_CSN());
				ps.setString(81, entity.getMEDICARE_CSV());
				ps.setString(82, entity.getMEDICARE_DN());
				ps.setString(83, entity.getNH_CARD());
				ps.setString(84, entity.getSSCID());
				ps.setString(85, entity.getCARD_OCCU_TYPE_CS());
				ps.setString(86, entity.getCARD_OCCU_TYPE_CSN());
				ps.setString(87, entity.getCARD_OCCU_TYPE_CSV());
				ps.setString(88, entity.getCARD_OCCU_TYPE_DN());
				ps.setString(89, entity.getCARD_NATION_CS());
				ps.setString(90, entity.getCARD_NATION_CSN());
				ps.setString(91, entity.getCARD_NATION_CSV());
				ps.setString(92, entity.getCARD_NATION_DN());
				ps.setString(93, entity.getNATIONALITY_CS());
				ps.setString(94, entity.getNATIONALITY_CSN());
				ps.setString(95, entity.getNATIONALITY_CSV());
				ps.setString(96, entity.getNATIONALITY_DN());
				ps.setString(97, entity.getAR_CS());
				ps.setString(98, entity.getAR_CSN());
				ps.setString(99, entity.getAR_CSV());
				ps.setString(100, entity.getAR_DN());
				ps.setString(101, entity.getAD_CS());
				ps.setString(102, entity.getAD_CSN());
				ps.setString(103, entity.getAD_CSV());
				ps.setString(104, entity.getAD_DN());
				ps.setString(105, entity.getTEL_TYPE_CS());
				ps.setString(106, entity.getTEL_TYPE_CSN());
				ps.setString(107, entity.getTEL_TYPE_CSV());
				ps.setString(108, entity.getTEL_TYPE_DN());
				ps.setString(109, entity.getLINKMAN_REL_CS());
				ps.setString(110, entity.getLINKMAN_REL_CSN());
				ps.setString(111, entity.getLINKMAN_REL_CSV());
				ps.setString(112, entity.getLINKMAN_REL_DN());
				ps.setString(113, entity.getLM_ID_NO_CS());
				ps.setString(114, entity.getLM_ID_NO_CSN());
				ps.setString(115, entity.getLM_ID_NO_CSV());
				ps.setString(116, entity.getLM_ID_NO_DN());
				ps.setString(117, entity.getABO_CS());
				ps.setString(118, entity.getABO_CSN());
				ps.setString(119, entity.getABO_CSV());
				ps.setString(120, entity.getABO_DN());
				ps.setString(121, entity.getRH_CS());
				ps.setString(122, entity.getRH_CSN());
				ps.setString(123, entity.getRH_CSV());
				ps.setString(124, entity.getRH_DN());
				ps.setString(125, entity.getCARD_ED_BG_CS());
				ps.setString(126, entity.getCARD_ED_BG_CSN());
				ps.setString(127, entity.getCARD_ED_BG_CSV());
				ps.setString(128, entity.getCARD_ED_BG_DN());
				ps.setString(129, entity.getBIRTH_PLACE_CS());
				ps.setString(130, entity.getBIRTH_PLACE_CSN());
				ps.setString(131, entity.getBIRTH_PLACE_CSV());
				ps.setString(132, entity.getBIRTH_PLACE_DN());
				ps.setString(133, entity.getNATIVE_PROVINCE());
				ps.setString(134, entity.getNATIVE_PROVINCE_CS());
				ps.setString(135, entity.getNATIVE_PROVINCE_CSN());
				ps.setString(136, entity.getNATIVE_PROVINCE_CSV());
				ps.setString(137, entity.getNATIVE_PROVINCE_DN());
				ps.setString(138, entity.getNATIVE_CITY_CS());
				ps.setString(139, entity.getNATIVE_CITY_CSN());
				ps.setString(140, entity.getNATIVE_CITY_CSV());
				ps.setString(141, entity.getNATIVE_CITY_DN());
				ps.setString(142, entity.getVETERANS_MILITARY_VALUE());
				ps.setString(143, entity.getVETERANS_MILITARY_MARK_CS());
				ps.setString(144, entity.getVETERANS_MILITARY_MARK_CSN());
				ps.setString(145, entity.getVETERANS_MILITARY_MARK_CSV());
				ps.setString(146, entity.getCARD_CS());
				ps.setString(147, entity.getCARD_CSN());
				ps.setString(148, entity.getCARD_CSV());
				ps.setString(149, entity.getCARD_DN());
				ps.setString(150, entity.getCARD_AREA_CS());
				ps.setString(151, entity.getCARD_AREA_CSN());
				ps.setString(152, entity.getCARD_AREA_CSV());
				ps.setString(153, entity.getCARD_AREA_DN());
				ps.setString(154, entity.getPATIENT_TYPE_VALUE());
				ps.setString(155, entity.getPATIENT_TYPE_CS());
				ps.setString(156, entity.getPATIENT_TYPE_CSN());
				ps.setString(157, entity.getPATIENT_TYPE_CSV());
				ps.setString(158, entity.getPATIENT_TYPE_DESCR());
				ps.setString(159, entity.getWORKING_TEL_NO());
				ps.setString(160, entity.getMEDICAL_TREATMENT_CS());
				ps.setString(161, entity.getMEDICAL_TREATMENT_CSN());
				ps.setString(162, entity.getMEDICAL_TREATMENT_CSV());
				ps.setString(163, entity.getVERSION_NUM());
				ps.setString(164, entity.getREGISTER_ORG_NAME());
				ps.setString(165, entity.getREMARK());
				ps.setString(166, entity.getRESPONS_DOC_CODE());
				ps.setString(167, entity.getRESPONS_DOC_NAME());
				ps.setString(168, entity.getOTHER_OCCC_DESCR());
				ps.setShort(169, entity.getUSUAL_TYPE_MARK()==null?0:entity.getUSUAL_TYPE_MARK());
				ps.setString(170, entity.getNATIVE_CITY());
				ps.setLong(171, entity.getCOMBINE_NO());
				ps.setString(172, entity.getSRC_LEVEL());
				//updated WHN 20170301
				ps.setString(173, entity.getPATIENT_ID());
				ps.setString(174, entity.getDRUG_ALLERGY_MARK());
				ps.setString(175, entity.getOP_HISTORY_MARK());
				ps.setString(176, entity.getTRAUMA_HISTORY_MARK());
				ps.setString(177, entity.getBLOOD_TRANSF_MARK());
				ps.setString(178, entity.getDISABILITY_MARK());
				ps.setString(179, entity.getGENETIC_DISEASE_HISTORY());
				ps.setString(180, entity.getEXHAUST_FACILITY_MARK());
				ps.setString(181, entity.getEXHAUST_FACILITY_TYPE_CODE());
				ps.setString(182, entity.getFUEL_TYPE_CODE());
				ps.setString(183, entity.getWATER_TYPE_CODE());
				ps.setString(184, entity.getTOILET_TYPE_CODE());
				ps.setString(185, entity.getLIVESTOCK_PEN_TYPE_CODE());
				ps.setString(186, entity.getOPERATION_HISTORY());
				ps.setString(187, entity.getASTHMA_MARK());
				ps.setString(188, entity.getHEDRT_DIS_MARK());
				ps.setString(189, entity.getCARDIOVASCULAR_CODE());
				ps.setString(190, entity.getEPILEPSY_MARK());
				ps.setString(191, entity.getCOAGULOPATHY_MARK());
				ps.setString(192, entity.getDIABETES_MARK());
				ps.setString(193, entity.getGLAUCOMA_MARK());
				ps.setString(194, entity.getDIALYSIS_MARK());
				ps.setString(195, entity.getORGAN_TRANS_MARK());
				ps.setString(196, entity.getORGAN_DEFECT_MARK());
				ps.setString(197, entity.getREMOVA_PRO_MARK());
				ps.setString(198, entity.getCARDIAC_PAC_MARK());
				ps.setString(199, entity.getORTHER_MEDICAL_ALERT());
				ps.setString(200, entity.getPSYCHIATRIC_MARK());
			}
		});
	}

	@Override
	public void deleteById(MpiCombineRec entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(MpiCombineRec entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public MpiCombineRec findById(MpiCombineRec entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MpiCombineRec> find(String sql) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public MpiCombineRec find(Long field_pk) {
		StringBuilder sql=new StringBuilder();
		sql.append("select * from MPI_COMBINE_REC where COMBINE_NO=?");
		List<MpiCombineRec> ret=jdbcTemplate.query(sql.toString(), new Object[]{field_pk},new RecRowMapper(){
			@Override
			public MpiCombineRec mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				MpiCombineRec p = super.mapRow(rs, rowNum);
				return p;
			}
		});
		MpiCombineRec result = null;
		if (ret != null && !ret.isEmpty()) {
			result = ret.get(0);
		}
		return result;
	}
	@Override
	public List<MpiCombineRec> find(String sql, Object[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MpiCombineRec> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount(String sql) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCount(String sql, Object[] args) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Map<String, Object>> findForMap(String sql, Object[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> findForMap(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		// TODO Auto-generated method stub
		return null;
	}
	private class RecRowMapper implements RowMapper<MpiCombineRec> {
		@Override
		public MpiCombineRec mapRow(ResultSet rs, int rowNum) throws SQLException {
			MpiCombineRec result = new MpiCombineRec();
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
			result.setCOMBINE_NO(rs.getLong("COMBINE_NO"));
			result.setSRC_LEVEL(rs.getString("SRC_LEVEL"));
			//updated WHN 20170301
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
	
}
