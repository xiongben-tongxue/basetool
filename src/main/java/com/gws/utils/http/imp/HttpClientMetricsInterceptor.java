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

import org.springframework.beans.factory.annotation.Autowired;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import static com.codahale.metrics.MetricRegistry.name;
/**
 * 和SQL一样使用Metrics来对HTTP客户端请求进行服务指标度量处理
 *
 * @version 
 * @author liuyi  2016年7月17日 下午7:09:16
 * 
 */
public class HttpClientMetricsInterceptor implements Interceptor {
	 @Autowired
	 private MetricRegistry metrics;
	 
	/**
	 * 进行拦截
	 * 
	 * (non-Javadoc)
	 * @see okhttp3.Interceptor#intercept(okhttp3.Interceptor.Chain)
	 */
	@Override
	public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String host = request.url().host();
        Response response;
        final Timer timer = metrics.timer(name(OkHttpClient.class, host, request.method()));
        final Timer.Context context = timer.time();
        try {
            response = chain.proceed(request);
        } finally {
            context.stop();
        }
        return response;
    }
	

}
