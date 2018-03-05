/*
 * Copyright (C) 2016  HangZhou YuShi Technology Co.Ltd  Holdings Ltd. All rights reserved
 *
 * 本代码版权归杭州宇石科技所有，且受到相关的法律保护。
 * 没有经过版权所有者的书面同意，
 * 任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。
 *
 */
package com.gws.configuration;

import com.gws.utils.webservice.HttpService;
import com.gws.utils.webservice.impl.HttpServiceImpl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * httpservice配置
 */
@Configuration
@EnableAutoConfiguration
public class HttpServiceConfig {

	@Bean
	public HttpService createHttpService() {
		return new HttpServiceImpl();

	}
}
