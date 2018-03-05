/*
 * Copyright (C) 2016  HangZhou YuShi Technology Co.Ltd  Holdings Ltd. All rights reserved
 *
 * 本代码版权归杭州宇石科技所有，且受到相关的法律保护。
 * 没有经过版权所有者的书面同意，
 * 任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。
 *
 */
package com.gws.utils.event;

/**
 * message事件
 *
 * @version 
 * @author liuyi  2017年4月18日 下午11:30:34
 * 
 */
public interface LocalEvent {
	  /**
     * 事件所属主题
     *
     * @return
     */
    String getTopic();
    
    /**
     * 事件内容
     *
     * @return
     */
    Object getContent();
    
    
}
