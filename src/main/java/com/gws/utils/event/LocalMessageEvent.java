/*
 * Copyright (C) 2016  HangZhou YuShi Technology Co.Ltd  Holdings Ltd. All rights reserved
 *
 * 本代码版权归杭州宇石科技所有，且受到相关的法律保护。
 * 没有经过版权所有者的书面同意，
 * 任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。
 *
 */
package com.gws.utils.event;


import com.google.common.base.Strings;
import org.springframework.stereotype.Component;
import reactor.bus.Event;
import reactor.bus.EventBus;

/**
 * 本地消息事件
 *
 * @version 
 * @author liuyi  2017年4月18日 下午11:33:40
 * 
 */
@Component
public class LocalMessageEvent implements LocalEvent {
	private EventBus eventBus;
	
    private String topic;

    private Object content;
    
	public static LocalMessageEvent builder(EventBus eventBus){
		LocalMessageEvent me= new LocalMessageEvent();
		me.setEventBus(eventBus);
		return me;
	}
	 
	/**
	 * 
	 * 消息发布，投递
	 * 
	 * @author liuyi 2017年4月19日
	 * @throws Exception
	 */
	public void dispatch() throws Exception{
		if(Strings.isNullOrEmpty(topic)||eventBus==null){
			throw new Exception("topic ,eventBus不能为空");
		}
		eventBus.notify(topic.trim(), Event.wrap(content));
	}


    public EventBus getEventBus() {
		return eventBus;
	}

	public LocalMessageEvent setEventBus(EventBus eventBus) {
		this.eventBus = eventBus;
		return this;
	}

	public LocalMessageEvent() {
    }

    public LocalMessageEvent(String topic, Object content) {
        this.topic = topic;
        this.content = content;
    }

    public LocalMessageEvent setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public LocalMessageEvent setContent(Object content) {
        this.content = content;
        return this;
    }

    /**
     * 事件所属主题
     *
     * @return
     */
    public String getTopic() {
        return topic;
    }

    /**
     * 事件内容
     *
     * @return
     */
    public Object getContent() {
        return content;
    }}
