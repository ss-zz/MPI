package com.sinosoft.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sinosoft.mpi.cache.CacheGenerator;

/**
 * 缓存配置
 */
@Configuration
public class CacheConfig {

	/**
	 * 基础数据缓存
	 */
	@Bean("cacheGenerator")
	public CacheGenerator cacheGenerator() {
		CacheGenerator ge = new CacheGenerator();
		List<String> clzNames = new ArrayList<>();

		// clzNames.add("com.sinosoft.mpi.model.SexCode");
		// clzNames.add("com.sinosoft.mpi.model.CertCode");
		// clzNames.add("com.sinosoft.mpi.model.EduCode");
		// clzNames.add("com.sinosoft.mpi.model.MarriageCode");
		// clzNames.add("com.sinosoft.mpi.model.NationCode");
		// clzNames.add("com.sinosoft.mpi.model.ProfessionCode");
		clzNames.add("com.sinosoft.mpi.model.SexCode");

		ge.setClzNames(clzNames.toArray(new String[0]));
		return ge;
	}

}
