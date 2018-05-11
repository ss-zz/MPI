package com.sinosoft.bizmatch.config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.sinosoft.match.model.MatchField;
import com.sinosoft.mpi.model.MatchCfg;
import com.sinosoft.mpi.model.MatchFieldCfg;

/**
 * 人员匹配的配置
 */
public class BizMatchConfig {
	private static BizMatchConfig config = new BizMatchConfig();

	private List<MatchField> matchFields;// 各个属性的匹配情况
	private float agreementWeightThreshold;// 完全匹配筏值
	private float matchWeightThreshold;// 可能匹配筏值

	private BizMatchConfig() {
		super();
		init();
	}

	@SuppressWarnings("unchecked")
	private void init() {
		matchFields = new ArrayList<MatchField>(0);

		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(getClass().getClassLoader().getResourceAsStream("match-config-biz.xml"));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element root = document.getRootElement();
		agreementWeightThreshold = Float.parseFloat(root.attributeValue("agreement-weight-threshold"));
		matchWeightThreshold = Float.parseFloat(root.attributeValue("match-weight-threshold"));

		List<Element> matchFieldElements = root.elements();
		for (Element matchFieldElement : matchFieldElements) {
			MatchField matchField = new MatchField();

			String agreementProbability = matchFieldElement.attributeValue("agreement-probability");
			String comparatorFunction = matchFieldElement.attributeValue("comparator-function");
			String fieldName = matchFieldElement.attributeValue("field-name");
			String matchThreshold = matchFieldElement.attributeValue("match-threshold");
			String disagreementProbability = matchFieldElement.attributeValue("disagreement-probability");
			String weight = matchFieldElement.attributeValue("weight");
			String desc = matchFieldElement.attributeValue("desc");

			matchField.setFieldName(fieldName);
			matchField.setComparatorFunction(comparatorFunction);
			matchField.setDisagreementProbability(Float.parseFloat(disagreementProbability));
			matchField.setMatchThreshold(Float.parseFloat(matchThreshold));
			matchField.setAgreementProbability(Float.parseFloat(agreementProbability));
			matchField.setWeight(Float.parseFloat(weight));
			matchField.setDesc(desc);
			matchFields.add(matchField);
		}
	}

	public static BizMatchConfig getInstanse() {
		return config;
	}

	public List<MatchField> getMatchFields() {
		return matchFields;
	}

	public void reloadCfg(MatchCfg cfg) {
		matchFields.clear();
		this.agreementWeightThreshold = Float.parseFloat(cfg.getAgreeThreshold());
		this.matchWeightThreshold = Float.parseFloat(cfg.getMatchThreshold());
		for (MatchFieldCfg mfc : cfg.getMatchFieldCfgs()) {
			matchFields.add(mfc.toMatchField());
		}
	}

	public float getAgreementWeightThreshold() {
		return agreementWeightThreshold;
	}

	public void setAgreementWeightThreshold(float agreementWeightThreshold) {
		this.agreementWeightThreshold = agreementWeightThreshold;
	}

	public float getMatchWeightThreshold() {
		return matchWeightThreshold;
	}

	public void setMatchWeightThreshold(float matchWeightThreshold) {
		this.matchWeightThreshold = matchWeightThreshold;
	}

	public void setMatchFields(List<MatchField> matchFields) {
		this.matchFields = matchFields;
	}

}
