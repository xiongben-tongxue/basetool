/*
 * Copyright (C) 2016  HangZhou YuShi Technology Co.Ltd  Holdings Ltd. All rights reserved
 *
 * 本代码版权归杭州宇石科技所有，且受到相关的法律保护。
 * 没有经过版权所有者的书面同意，
 * 任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。
 *
 */
package com.gws.configuration;

import com.gws.interceptor.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * spring web 配置
 *
 * @version 
 * @author yangjh
 */
@Configuration
@ComponentScan("com.gws.interceptor")
public class GwsWebConfig extends WebMvcConfigurerAdapter {

	@Value("${spring.corsOrigins}")
	private String corsOrigins;
	/**
	 *
	 * 1、通用日志模块 httpInterceptor
     *
	 * 2、app, sdk拦截
	 * 1) 身份认证模块: uid, channelName, channelId
	 * 2) 公共参数
	 * 3) 解密
	 *
	 * 3、api拦截器 ：
	 * 1) ip白名单
	 *
	 **/
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(httpInterceptor());

		/**api接口**/
		registry.addInterceptor(iPWhitelistInterceptor()).addPathPatterns("/api/**");

	}


	@Bean
	public HandlerInterceptor httpInterceptor() {
		return new HttpInterceptor();
	}

	@Bean
	public HandlerInterceptor iPWhitelistInterceptor() {
		return new IPWhitelistInterceptor();
	}


}
