/*
 * Copyright (C) 2016  HangZhou YuShi Technology Co.Ltd  Holdings Ltd. All rights reserved
 *
 * 本代码版权归杭州宇石科技所有，且受到相关的法律保护。
 * 没有经过版权所有者的书面同意，
 * 任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。
 *
 */
package com.gws.utils.query.core;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * query基类，注意分库分表的sharding-col是不能用使用In查询的，路由不了。
 *
 * @version 
 * @author liuyi  2016年4月16日 下午3:48:02
 * 
 */
public abstract class BaseQuery {
	//排序对象
	private Sort sort;
	private Pageable Page;
	public Sort getSort() {
		return sort;
	}
	public void setSort(Sort sort) {
		this.sort = sort;
	}
	public Pageable getPage() {
		return Page;
	}
	public void setPage(Pageable page) {
		Page = page;
	}

	
}
