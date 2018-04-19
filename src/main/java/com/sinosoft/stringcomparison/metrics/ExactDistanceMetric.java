package com.sinosoft.stringcomparison.metrics;
/**
 * 
*    
* @Description  全字符匹配   
* 
* 
*
* 
* @Package com.sinosoft.stringcomparison.metrics 
* @author <a href="mailto:qinshouxin@sinosoft.com.cn">Qin Shouxin </a> 
* @version v1.0,2012-3-27
* @see	
* @since	（可选）	
*
 */
public class ExactDistanceMetric implements IDistanceMetric {

	
	@Override
	public double score(String left, String right) {
		double result=0;
		if(left==null||right==null){
			
		}else{
			if(left.equals(right)){
				result=1;
			}
		}
		return result;
	}

	@Override
	public String getName() {
		return "exact";
	}

	@Override
	public String getNameCn() {
		return "精确";
	}

}
