/*
 * Copyright (C) 2016  HangZhou YuShi Technology Co.Ltd  Holdings Ltd. All rights reserved
 *
 * 本代码版权归杭州宇石科技所有，且受到相关的法律保护。
 * 没有经过版权所有者的书面同意，
 * 任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。
 *
 */
package com.gws.enums;

/**
 * 是与否枚举类
 * @author yangjh
 */
public enum YesNoEnum {
	YES(1,"是"),
	NO(2,"否")
	;
	private Integer code;
	private String message;

	private YesNoEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}


	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public static YesNoEnum getEnum(Integer code){
		if (code == null)
            return null;
        for (YesNoEnum yesNoEnum: YesNoEnum.values()) {
            if (yesNoEnum.getCode().equals(code))
                return yesNoEnum;
        }
        return null;
	}
}
