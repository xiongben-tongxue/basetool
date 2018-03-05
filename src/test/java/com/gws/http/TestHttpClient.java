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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.gws.utils.http.imp.GwsHttpClientImpl;

import antlr.collections.List;
import junit.framework.TestCase;
import okhttp3.HttpUrl;
import okhttp3.HttpUrl.Builder;
import okhttp3.Response;

/**
 * 【请在此写上此类功能描述文字】
 *
 * @version 
 * @author liuyi  2016年7月17日 下午2:16:21
 * 
 */
public class TestHttpClient extends TestCase {
	
	public void testHttpUrl(){
		Builder urlBuilder = HttpUrl.parse("https://github.com/search/").newBuilder();
		Map<String, String> mapObj= new HashMap<String, String>();
		mapObj.put("key1", "value1");
		mapObj.put("key2", "value2");
		mapObj.put("key3", "value3");
		for (Map.Entry<String, String> item : mapObj.entrySet()) {
			urlBuilder.addQueryParameter(item.getKey(), item.getValue());
		}
		HttpUrl url= urlBuilder.build();
	    System.out.println(url.toString());

	}

	/**
	 * 【请在此输入描述文字】
	 * 
	 * @author liuyi 2016年7月17日
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Object> oos= new ArrayList<Object>();
		oos.add(2);
		oos.add(3);
		oos.add(4);
		//String[] arr = (String[])oos.toArray(new String[oos.size()]);
	    System.out.println(oos);


	}

}
