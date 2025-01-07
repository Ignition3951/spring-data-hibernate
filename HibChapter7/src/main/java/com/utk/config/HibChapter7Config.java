package com.utk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@Import(SpringDataConfig.class)
@EnableJpaRepositories(basePackages = { "com.utk.repo" })
public class HibChapter7Config {

}
