/*
 * Copyright (C) 2016  HangZhou YuShi Technology Co.Ltd  Holdings Ltd. All rights reserved
 *
 * 本代码版权归杭州宇石科技所有，且受到相关的法律保护。
 * 没有经过版权所有者的书面同意，
 * 任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。
 *
 */
package com.gws.interceptor;

import com.alibaba.fastjson.JSON;
import com.gws.controllers.JsonResult;
import com.gws.enums.SystemCode;
import com.gws.utils.GwsLogger;
import com.gws.utils.IPUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 【ip白名单拦截器】
 *
 * @version 4.0.0
 * @author yangjh
 *
 */

@Configuration
public class IPWhitelistInterceptor extends HandlerInterceptorAdapter {


	@Value("${service.api.ipwhite}")
    private String ipWhilteList = "*";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String reqIp = request.getRemoteAddr();
		if (!IPUtil.isIpInWhitelist(reqIp, ipWhilteList)) {
			GwsLogger.info("the IP address : " + reqIp + "is not allowed" );
			JsonResult jsonResult  = new JsonResult(SystemCode.NOT_IN_WHITELIST);
			response.getOutputStream().write(JSON.toJSONString(jsonResult).getBytes());
			return false;
		}
		return true;
	}

}
