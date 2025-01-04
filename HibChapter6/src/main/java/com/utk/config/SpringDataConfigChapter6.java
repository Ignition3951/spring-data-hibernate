package com.utk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

public class SpringDataConfigChapter6 extends SpringDataConfig {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = super.entityManagerFactory();
		localContainerEntityManagerFactoryBean.setPackagesToScan("com.utk.entity");
		return localContainerEntityManagerFactoryBean;
	}


}
