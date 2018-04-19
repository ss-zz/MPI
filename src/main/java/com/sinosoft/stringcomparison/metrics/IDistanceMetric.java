
package com.sinosoft.stringcomparison.metrics;


/**
 * 
*    
* @Description  距离计算函数接口   
* 
* 
* 其实现类，必须给出标志
* 
* @Package com.sinosoft.stringcomparison.metrics 
* @author <a href="mailto:qinshouxin@sinosoft.com.cn">Qin Shouxin </a> 
* @version v1.0,2012-3-20
* @see	
* @since	（可选）	
*
 */
public interface IDistanceMetric
{
	public double score(String left, String right);
	
	public String getName();
	
	public String getNameCn();
}
