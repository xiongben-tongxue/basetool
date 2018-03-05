/*
 * Copyright (C) 2016  HangZhou YuShi Technology Co.Ltd  Holdings Ltd. All rights reserved
 *
 * 本代码版权归杭州宇石科技所有，且受到相关的法律保护。
 * 没有经过版权所有者的书面同意，
 * 任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。
 *
 */
package com.gws.utils.http.imp;

import java.io.IOException;

import okhttp3.Response;

/**
 * http自定义异常类
 *
 * @version 
 * @author liuyi  2016年7月17日 下午2:28:00
 * 
 */
public class HttpGwsException extends IOException {
	private Response response;
	private static final long serialVersionUID = -4802242016364002941L;
	
    public HttpGwsException(IOException e) {
        super(e);
    }
    
    public HttpGwsException(Response response,String message) {
        super(message);
        this.setResponse(response);
    }

	/**
	 * @return the response
	 */
	public Response getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(Response response) {
		this.response = response;
	}
}
