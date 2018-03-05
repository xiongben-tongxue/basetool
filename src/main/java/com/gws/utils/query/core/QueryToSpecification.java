/*
 * Copyright (C) 2016  HangZhou YuShi Technology Co.Ltd  Holdings Ltd. All rights reserved
 *
 * 本代码版权归杭州宇石科技所有，且受到相关的法律保护。
 * 没有经过版权所有者的书面同意，
 * 任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。
 *
 */
package com.gws.utils.query.core;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 自定义Query语言转Specification
 *
 * @version 
 * @author liuyi  2016年4月16日 下午3:46:50
 * @param <T>
 * 
 */
public class QueryToSpecification implements Specification {
	private BaseQuery query;
	
	
	public QueryToSpecification(BaseQuery query) {
		super();
		this.query = query;
	}

	/**
	 * 【请在此输入描述文字】
	 * 
	 * (non-Javadoc)
	 * @see org.springframework.data.jpa.domain.Specification#toPredicate(Root, CriteriaQuery, CriteriaBuilder)
	 */
	@Override
	public Predicate toPredicate(Root root, CriteriaQuery cquery, CriteriaBuilder cb) {
		return BaseQueryPredicateBuilder.getPredicate(root, cb, cquery,this.query);
	}



}
