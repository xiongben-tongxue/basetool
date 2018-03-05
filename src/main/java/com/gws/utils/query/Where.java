/*
 * Copyright (C) 2016  HangZhou YuShi Technology Co.Ltd  Holdings Ltd. All rights reserved
 *
 * 本代码版权归杭州宇石科技所有，且受到相关的法律保护。
 * 没有经过版权所有者的书面同意，
 * 任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。
 *
 */
package com.gws.utils.query;

/**
 * 目前系统支持的where查询条件关键字
 *
 * @version 
 * @author liuyi  2016年4月16日 下午6:40:05
 * 
 */
public enum Where {
	in,
	like,
	equal,
	notEqual,
	lessThan,
	lessThanOrEqualTo,
	greaterThan,
	greaterThanOrEqualTo
}
