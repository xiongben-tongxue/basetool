/*
 * Copyright (C) 2016  HangZhou YuShi Technology Co.Ltd  Holdings Ltd. All rights reserved
 *
 * 本代码版权归杭州宇石科技所有，且受到相关的法律保护。
 * 没有经过版权所有者的书面同意，
 * 任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。
 *
 */
package com.gws.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.gws.base.GwsBaseTestCase;
import com.gws.base.TestInject;
import com.gws.utils.http.HTTP;
import com.gws.utils.http.imp.HttpGwsException;

/**
 * HttpInitTest
 *
 * @version 
 * @author liuyi  2016年7月17日 下午6:20:25
 * 
 */
public class HttpInitTest extends GwsBaseTestCase {

	@TestInject
    private HTTP httpClient;
	
	public void testGet(){
		try {
			Map<String, String> queryParams = new HashMap<String, String>();
			queryParams.put("ip", "117.89.35.58");
			for(int i=0;i<1;i++){
				byte[] resP =httpClient.GET("http://apis.baidu.com/apistore/iplookupservice/iplookup",queryParams);
				System.out.println(new String(resP));
			}
		} catch (HttpGwsException e) {
			// TODO 这是系统自动生成描述，请在此补完后续代码
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 这是系统自动生成描述，请在此补完后续代码
			e.printStackTrace();
		}
	}
}
