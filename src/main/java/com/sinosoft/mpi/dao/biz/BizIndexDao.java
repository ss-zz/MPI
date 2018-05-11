package com.sinosoft.mpi.dao.biz;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sinosoft.mpi.dao.IBaseDao;
import com.sinosoft.mpi.model.biz.BizIndex;
import com.sinosoft.mpi.util.IDUtil;

/**
 * 业务主索引dao
 */
@Repository
public class BizIndexDao extends IBaseDao<BizIndex> {

	@Override
	public void add(final BizIndex entity) {
		entity.setId(IDUtil.getUUID());
		StringBuilder sql = new StringBuilder();
		sql.append("insert into mpi_person_index( ").append("REGISTER_ORG_CODE,").append("MEDICALSERVICE_NO,")
				.append("NAME_CN,").append("NAME_EN,").append("BIRTH_DATE,").append("GENDER_CD,")
				.append("CARD_MARITAL_ST_CD,").append("ID_NO_CD,").append("ID_NO,").append("MEDICARE_CD,")
				.append("MEDICAL_INSURANCE_NO,").append("CARD_OCCU_TYPE_CD,").append("CARD_NATION_CD,")
				.append("NATIONALITY_CD,").append("AR_CD,").append("AD_CD,").append("PROVINCE_NAME,")
				.append("CITY_NAME,").append("AREA_NAME,").append("STREET_ID,").append("VILLAGE_NAME,")
				.append("HOUSE_NO,").append("TEL_TYPE_CD,").append("PERSON_TEL_NO,").append("LINKMAN_NAME,")
				.append("LINKMAN_REL_CD,").append("LM_ID_NO_CD,").append("LM_ID_NO,").append("HR_ID,")
				.append("POST_CD,").append("ABO_CD,").append("RH_CD,").append("RH_NEG_CD,").append("CARD_ED_BG_CD,")
				.append("BIRTH_PLACE,").append("WORKING_UNIT_NAME,").append("EMAIL_AD,")
				.append("VETERANS_MILITARY_MARK,").append("CARD_NO,").append("CARD_CD,").append("CARD_AREA,")
				.append("PATIENT_TYPE,").append("M_PHONE_NUM,").append("WORKING_UNIT_POST,")
				.append("WORKING_UNIT_ADDR,").append("LIVING_ADDR,").append("RPR_ADDR,").append("RPR_POST,")
				.append("RPR_PHONE,").append("LINKMAN_ADDR,").append("LINKMAN_POST,").append("LINKMAN_PHONE,")
				.append("INSURE_TYPE,").append("MEDICAL_TREATMENT_NAME,").append("MEDICAL_TREATMENT_CD,")
				.append("REGISTER_DATE,").append("RECORDER_NAME,").append("REGISTER_PERSON_CODE,").append("SEND_TIME,")
				.append("SEND_ORG_CODE,").append("SEND_SYSTEM,").append("PROVIDER_NAME,").append("PROVIDER_ORG_CODE,")
				.append("CREATETIME,").append("LASTUPTIME,").append("STATE,").append("GENDER_CS,").append("GENDER_CSN,")
				.append("GENDER_CSV,").append("GENDER_DN,").append("CARD_MARITAL_ST_CS,").append("CARD_MARITAL_ST_CSN,")
				.append("CARD_MARITAL_ST_CSV,").append("CARD_MARITAL_ST_DN,").append("ID_NO_CS,").append("ID_NO_CSN,")
				.append("ID_NO_CSV,").append("ID_NO_DN,").append("MEDICARE_CS,").append("MEDICARE_CSN,")
				.append("MEDICARE_CSV,").append("MEDICARE_DN,").append("NH_CARD,").append("SSCID,")
				.append("CARD_OCCU_TYPE_CS,").append("CARD_OCCU_TYPE_CSN,").append("CARD_OCCU_TYPE_CSV,")
				.append("CARD_OCCU_TYPE_DN,").append("CARD_NATION_CS,").append("CARD_NATION_CSN,")
				.append("CARD_NATION_CSV,").append("CARD_NATION_DN,").append("NATIONALITY_CS,")
				.append("NATIONALITY_CSN,").append("NATIONALITY_CSV,").append("NATIONALITY_DN,").append("AR_CS,")
				.append("AR_CSN,").append("AR_CSV,").append("AR_DN,").append("AD_CS,").append("AD_CSN,")
				.append("AD_CSV,").append("AD_DN,").append("TEL_TYPE_CS,").append("TEL_TYPE_CSN,")
				.append("TEL_TYPE_CSV,").append("TEL_TYPE_DN,").append("LINKMAN_REL_CS,").append("LINKMAN_REL_CSN,")
				.append("LINKMAN_REL_CSV,").append("LINKMAN_REL_DN,").append("LM_ID_NO_CS,").append("LM_ID_NO_CSN,")
				.append("LM_ID_NO_CSV,").append("LM_ID_NO_DN,").append("ABO_CS,").append("ABO_CSN,").append("ABO_CSV,")
				.append("ABO_DN,").append("RH_CS,").append("RH_CSN,").append("RH_CSV,").append("RH_DN,")
				.append("CARD_ED_BG_CS,").append("CARD_ED_BG_CSN,").append("CARD_ED_BG_CSV,").append("CARD_ED_BG_DN,")
				.append("BIRTH_PLACE_CS,").append("BIRTH_PLACE_CSN,").append("BIRTH_PLACE_CSV,")
				.append("BIRTH_PLACE_DN,").append("NATIVE_PROVINCE,").append("NATIVE_PROVINCE_CS,")
				.append("NATIVE_PROVINCE_CSN,").append("NATIVE_PROVINCE_CSV,").append("NATIVE_PROVINCE_DN,")
				.append("NATIVE_CITY_CS,").append("NATIVE_CITY_CSN,").append("NATIVE_CITY_CSV,")
				.append("NATIVE_CITY_DN,").append("VETERANS_MILITARY_VALUE,").append("VETERANS_MILITARY_MARK_CS,")
				.append("VETERANS_MILITARY_MARK_CSN,").append("VETERANS_MILITARY_MARK_CSV,").append("CARD_CS,")
				.append("CARD_CSN,").append("CARD_CSV,").append("CARD_DN,").append("CARD_AREA_CS,")
				.append("CARD_AREA_CSN,").append("CARD_AREA_CSV,").append("CARD_AREA_DN,").append("PATIENT_TYPE_VALUE,")
				.append("PATIENT_TYPE_CS,").append("PATIENT_TYPE_CSN,").append("PATIENT_TYPE_CSV,")
				.append("PATIENT_TYPE_DESCR,").append("WORKING_TEL_NO,").append("MEDICAL_TREATMENT_CS,")
				.append("MEDICAL_TREATMENT_CSN,").append("MEDICAL_TREATMENT_CSV,").append("VERSION_NUM,")
				.append("REGISTER_ORG_NAME,").append("REMARK,").append("RESPONS_DOC_CODE,").append("RESPONS_DOC_NAME,")
				.append("OTHER_OCCC_DESCR,").append("USUAL_TYPE_MARK,").append("NATIVE_CITY,").append("MPI_PK, ")
				.append("DOMAIN_LEVEL,").append("patient_id,").append("drug_allergy_mark,").append("op_history_mark,")
				.append("trauma_history_mark,").append("blood_transf_mark,").append("disability_mark,")
				.append("genetic_disease_history,").append("exhaust_facility_mark,")
				.append("exhaust_facility_type_code,").append("fuel_type_code,").append("water_type_code,")
				.append("toilet_type_code,").append("livestock_pen_type_code,").append("operation_history,")
				.append("asthma_mark,").append("hedrt_dis_mark,").append("cardiovascular_code,")
				.append("epilepsy_mark,").append("coagulopathy_mark,").append("diabetes_mark,").append("glaucoma_mark,")
				.append("dialysis_mark,").append("organ_trans_mark,").append("organ_defect_mark,")
				.append("remova_pro_mark,").append("cardiac_pac_mark,").append("orther_medical_alert,")
				.append("psychiatric_mark )")
				.append("values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," + "?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				// ps.setString(1, entity.getREGISTER_ORG_CODE());
			}
		});

	}

	@Override
	public void deleteById(final BizIndex entity) {
		String sql = " delete from mpi_person_index where mpi_pk = ?";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getId());
			}
		});

	}

	@Override
	public void update(final BizIndex entity) {
		if (entity == null || entity.getId() == null) {
			return;
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" update mpi_person_index set ").append("REGISTER_ORG_CODE=?,").append("MEDICALSERVICE_NO=?,")
				.append("NAME_CN=?,").append("NAME_EN=?,").append("BIRTH_DATE=?,").append("GENDER_CD=?,")
				.append("CARD_MARITAL_ST_CD=?,").append("ID_NO_CD=?,").append("ID_NO=?,").append("MEDICARE_CD=?,")
				.append("MEDICAL_INSURANCE_NO=?,").append("CARD_OCCU_TYPE_CD=?,").append("CARD_NATION_CD=?,")
				.append("NATIONALITY_CD=?,").append("AR_CD=?,").append("AD_CD=?,").append("PROVINCE_NAME=?,")
				.append("CITY_NAME=?,").append("AREA_NAME=?,").append("STREET_ID=?,").append("VILLAGE_NAME=?,")
				.append("HOUSE_NO=?,").append("TEL_TYPE_CD=?,").append("PERSON_TEL_NO=?,").append("LINKMAN_NAME=?,")
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
				.append("VERSION_NUM=?,").append("REGISTER_ORG_NAME=?,").append("REMARK=?,")
				.append("RESPONS_DOC_CODE=?,").append("RESPONS_DOC_NAME=?,").append("OTHER_OCCC_DESCR=?,")
				.append("USUAL_TYPE_MARK=?,").append("NATIVE_CITY=?,")
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
				.append("orther_medical_alert=? ,").append("psychiatric_mark=? ")

				.append(" where mpi_pk = ? ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {

			}
		});

	}

	@Override
	public BizIndex findById(BizIndex entity) {
		String sql = " select * from mpi_person_index where mpi_pk = ? ";
		List<BizIndex> datas = find(sql, new Object[] { entity.getId() });
		BizIndex result = null;
		if (datas != null && !datas.isEmpty()) {
			result = datas.get(0);
		}
		return result;
	}

	@Override
	public List<BizIndex> find(String sql) {
		List<BizIndex> datas = find(sql, new Object[] {});
		return datas;
	}

	@Override
	public List<BizIndex> find(String sql, Object[] args) {
		List<BizIndex> datas = jdbcTemplate.query(sql, args, new RowMapper<BizIndex>() {
			@Override
			public BizIndex mapRow(ResultSet rs, int rowNum) throws SQLException {
				BizIndex result = new BizIndex();
				// result.setREGISTER_ORG_CODE(rs.getString("REGISTER_ORG_CODE"));
				return result;
			}

		});
		return datas;
	}

	@Override
	public List<BizIndex> findAll() {
		String sql = " select * from mpi_person_index where 1=1";
		List<BizIndex> datas = find(sql);
		return datas;
	}

}
