package com.gws.entity.frontuser;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 【用户基本资料信息表】实体类
*
 * @version
 * @author wangdong 2018年04月16日 PM14:10:21
 */
@Data
@Entity
@Table(name = "user_base_info") 
public class UserBaseInfo{

	/**
	 * uid
	 */
	@Id
	@Column(name = "uid")
	private Long uid;

	/**
	 * 头像路径
	 */
	@Column(name = "avatar")
	private String avatar;

	/**
	 * 用户昵称
	 */
	@Column(name = "user_name")
	private String userName;

	/**
	 * 性别(1-男,2-女)
	 */
	@Column(name = "gender")
	private Integer gender;

	/**
	 * 电话号码
	 */
	@Column(name = "phone_number")
	private String phoneNumber;

	/**
	 * 邮件地址
	 */
	@Column(name = "email_address")
	private String emailAddress;

	/**
	 * 用户状态(0-正常,1-冻结)
	 */
	@Column(name = "user_status")
	private Integer userStatus;

	/**
	 * 创建时间
	 */
	@Column(name = "ctime")
	private Integer ctime;

	/**
	 * 更新时间
	 */
	@Column(name = "utime")
	private Integer utime;

}

