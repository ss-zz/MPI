package com.sinosoft.bizblock.config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.sinosoft.block.model.BlockField;
import com.sinosoft.block.model.BlockRound;
import com.sinosoft.mpi.model.BlockCfg;
import com.sinosoft.mpi.model.BlockGroup;

/**
 * 业务初筛配置加载
 */
public class BizBlockConfig {

	private static BizBlockConfig config = new BizBlockConfig();

	private List<BlockRound> blockRounds;

	private BizBlockConfig() {
		super();
		init();
	}

	/**
	 * 获取实例
	 * 
	 * @return
	 */
	public static BizBlockConfig getInstanse() {
		return config;
	}

	/**
	 * 初始化
	 */
	@SuppressWarnings("unchecked")
	private void init() {
		blockRounds = new ArrayList<BlockRound>();
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(getClass().getClassLoader().getResourceAsStream("block-config-biz.xml"));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element root = document.getRootElement();
		List<Element> roundElements = root.element("blocking-rounds").elements("blocking-round");

		for (Element roundElement : roundElements) {
			BlockRound round = new BlockRound();
			List<Element> blockFieldElements = roundElement.element("blocking-fields").elements("blocking-field");
			List<BlockField> blockFields = new ArrayList<BlockField>();

			for (Element bockFieldElement : blockFieldElements) {
				BlockField blockField = new BlockField();
				blockField.setDbField(bockFieldElement.attributeValue("db-field"));
				blockField.setFunName(bockFieldElement.attributeValue("fun-name"));
				blockField.setField(bockFieldElement.elementTextTrim("field-name"));
				blockFields.add(blockField);
			}
			round.setBlockFields(blockFields);

			blockRounds.add(round);

		}

	}

	public List<BlockRound> getBlockRounds() {
		return blockRounds;
	}

	public void setBlockRounds(List<BlockRound> blockRounds) {
		this.blockRounds = blockRounds;
	}

	/**
	 * 重新加载配置文件
	 * 
	 * @param cfg
	 */
	public void reloadCfg(BlockCfg cfg) {
		blockRounds.clear();
		for (Integer key : cfg.getGroups().keySet()) {
			BlockRound br = new BlockRound();
			br.setBlockFields(new ArrayList<BlockField>(cfg.getGroups().get(key).size()));
			for (BlockGroup bg : cfg.getGroups().get(key)) {
				br.getBlockFields().add(bg.toBlockField());
			}
			blockRounds.add(br);
		}
	}

}
