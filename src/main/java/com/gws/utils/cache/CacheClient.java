/*
 * Copyright (C) 2016  HangZhou YuShi Technology Co.Ltd  Holdings Ltd. All rights reserved
 *
 * 本代码版权归杭州宇石科技所有，且受到相关的法律保护。
 * 没有经过版权所有者的书面同意，
 * 任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。
 *
 */
package com.gws.utils.cache;

import java.util.List;

/**
 * 
 * 缓存客户端接口类
 *
 * @version 
 * @author liuyi  2016年4月25日 下午1:48:15
 *
 */
public interface CacheClient {
	/**
	 * 
	 * 设置对象
	 * 
	 * @author liuyi 2016年4月20日
	 * @param key
	 * @param object
	 * @param timeout
	 * @param clazz
	 * @return
	 */
    public <T> boolean set(String prefix,String key, T t, Long timeout);  
    
    
    /**
     * 
     * 设置列表缓存
     * 
     * @author liuyi 2016年4月25日
     * @param prefix
     * @param key
     * @param t
     * @param timeout
     * @return
     */
    public <T> boolean setList(String prefix, String key, List<T> t);
    
    /**
     * 
     * 获取对象
     * 
     * @author liuyi 2016年4月20日
     * @param key
     * @return
     */
    public <T> T get(String prefix,String key);
    
    
    
    /**
     * 
     * 获取列表缓存
     * 
     * @author liuyi 2016年4月25日
     * @param prefix
     * @param key
     * @param t
     * @return
     */
    public <T> List<T> getList(String prefix,String key);
    
    /**
     * 
     * 删除缓存
     * 
     * @author liuyi 2016年4月20日
     * @param key
     */

    public void delete(String prefix,String key);


	/**
	 * 不存在时添加
	 * @param prefix
	 * @param key
     * @return
     */
	public boolean setIfAbsent(String prefix, String key);
}
