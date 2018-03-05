-- ----------------------------
-- Table structure for t_init_platform_account
-- ----------------------------
CREATE TABLE `user_platform_account` (
  `account` BIGINT NOT NULL COMMENT '喵号',
  `is_use` smallint(6) NOT NULL DEFAULT '1' COMMENT '是否使用(1-是,2-否)',
  `is_nice_no` smallint(6) NOT NULL DEFAULT '2' COMMENT '是否靓号(1-是,2-否)',
  `first_num` smallint(6) DEFAULT NULL COMMENT '号码首位数字',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  `is_delete` smallint(6) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='喵号生成表';

-- ----------------------------
-- Table structure for user_game_binding
-- ----------------------------
CREATE TABLE `user_game_binding` (
  `binding_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '绑定ID',
  `uid` bigint NOT NULL COMMENT '用户id',
  `outter_game_id` varchar(64) NOT NULL COMMENT '游戏id',
  `open_id` varchar(50) DEFAULT NULL COMMENT '用户openId',
  `chl_id` varchar(64) NOT NULL COMMENT '渠道id',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  `is_delete` smallint(6) NOT NULL DEFAULT '0' COMMENT '是否删除(0-正常,1-删除)',
  PRIMARY KEY (`binding_id`),
  UNIQUE KEY `index_user_game_id` (`uid`,`outter_game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户首次注册游戏表';

-- ----------------------------
-- Table structure for t_user_base_info
-- ----------------------------
CREATE TABLE `user_base_info` (
  `uid` bigint NOT NULL COMMENT '用户ID',
  `account` bigint NOT NULL COMMENT '喵号',
  `avatar` varchar(256) DEFAULT NULL COMMENT '头像路径',
  `user_name` varchar(30) NOT NULL DEFAULT '游戏猫' COMMENT '用户昵称',
  `gender` smallint(1) DEFAULT 0 COMMENT '性别(0-男,1-女)',
  `birthdate` DATE DEFAULT NULL COMMENT '出生日期',
  `user_status` smallint(1) NOT NULL COMMENT '用户状态(0-正常,1-冻结)',
  `first_zm` varchar(1) DEFAULT '#' COMMENT '首字母',
  `sign_name` varchar(100) DEFAULT '' COMMENT '用户签名',
  `area` VARCHAR(20) DEFAULT '' COMMENT '用户所在地区',
  `user_register_chl` varchar(50) DEFAULT '' COMMENT '用户所属渠道号',
  `has_identified` SMALLINT DEFAULT 2 COMMENT '1、是,2、否',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uk_account`(`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基本资料信息表';


-- ----------------------------
-- Table structure for user_identity
-- ----------------------------
CREATE TABLE `user_identity` (
  `uid` bigint NOT NULL COMMENT '用户ID',
  `real_name` varchar(50) DEFAULT '' COMMENT '用户实名',
  `IDCard` varchar(50) DEFAULT '' COMMENT '身份证号',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户身份信息表';


-- ----------------------------
-- Table structure for user_register_info
-- ----------------------------
CREATE TABLE `user_register_info` (
  `uid` bigint NOT NULL COMMENT '用户ID',
  `register_ip` varchar(150) DEFAULT '' COMMENT '注册ip',
  `terminal_type` smallint(1) DEFAULT '0' COMMENT '注册终端(0-非终端,1-ios,2-安卓)',
  `register_terminal` VARCHAR(30) DEFAULT '' COMMENT '注册终端名',
  `terminal_os` VARCHAR(30) DEFAULT '' COMMENT '注册终端版本',
  `version` VARCHAR(30) DEFAULT '' COMMENT '注册来源版本',
  `register_ua` smallint(6) DEFAULT NULL COMMENT '用户注册来源（1-app,2-sdk,3-web）',
  `outter_game_id` VARCHAR(30) DEFAULT '' COMMENT '注册游戏ID',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户注册信息';


-- ----------------------------
-- Table structure for user_pwd
-- ----------------------------
CREATE TABLE `user_pwd` (
  `uid` bigint NOT NULL COMMENT '用户ID',
  `pwd` varchar(50) NOT NULL COMMENT '密码',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户密码表';


-- ----------------------------
-- Table structure for user_exp
-- ----------------------------
CREATE TABLE `user_exp` (
  `uid` bigint NOT NULL COMMENT '用户Id',
  `exp_total` varchar(22) DEFAULT NULL COMMENT '经验总数',
  `exp_grade` smallint(2) DEFAULT NULL COMMENT '当前经验等级',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户经验统计表';

-- ----------------------------
-- Table structure for t_user_lng_lat
-- ----------------------------
CREATE TABLE `user_lng_lat` (
  `uid` bigint NOT NULL COMMENT '用户Id',
  `lng` decimal(9,6) NOT NULL COMMENT '经度',
  `lat` decimal(9,6) NOT NULL COMMENT '纬度',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户经度纬度表';


-- ----------------------------
-- Table structure for user_qq_unionid
-- ----------------------------
CREATE TABLE `user_qq_union_id` (
  `uid` bigint NOT NULL COMMENT '用户id',
  `union_id` varchar(100) NOT NULL COMMENT '第三方登录unionId',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uk_unionid` (`union_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户unionId绑定表';

-- ----------------------------
-- Table structure for user_register_account
-- ----------------------------
CREATE TABLE `user_register_account` (
  `account_id` bigint NOT NULL COMMENT '唯一标识',
  `uid` bigint NOT NULL COMMENT '用户ID',
  `account_type` int(2) NOT NULL COMMENT '用户拥有的登录类型(1-平台ID,2-手机,4-自定义账号注册,5-AppQQ,6-微信，8-webQQ)',
  `account` varchar(40) NOT NULL COMMENT '注册账号(如果是QQ则记录openId)',
  `ctime` int NOT NULL COMMENT '创建时间',
  `utime` int NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`account_id`),
  KEY `idx_userid_accounttype` (`uid`, `account_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户拥有登录类型信息表';


-- ----------------------------
-- Table structure for user_app_token
-- ----------------------------
CREATE TABLE `user_app_token` (
  `uid` bigint(20) NOT NULL COMMENT '用户id',
  `token` varchar(50) DEFAULT NULL COMMENT '用户Token',
  `ctime` int NOT NULL COMMENT '创建时间',
  `utime` int NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='app token信息表';

-- ----------------------------
-- Table structure for user_sdk_token
-- ----------------------------
CREATE TABLE `user_sdk_token` (
  `id` bigint(6) NOT NULL COMMENT 'id',
  `uid` bigint(10) NOT NULL COMMENT '用户id',
  `game_id` varchar(50) NOT NULL COMMENT '游戏id',
  `token` varchar(50) NOT NULL,
  `ctime` int NOT NULL COMMENT '创建时间',
  `utime` int NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_userid_gameid` (`uid`, `game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT ='sdk token信息';


-- ----------------------------
-- Table structure for user_sid_binding
-- 一键注册功能
-- ----------------------------
CREATE TABLE `user_sid_binding` (
  `id` bigint(6) NOT NULL ,
  `uid` bigint(10) NOT NULL COMMENT '用户id',
  `sid` varchar(50) NOT NULL COMMENT 'sid',
  `ctime` int NOT NULL COMMENT '创建时间',
  `utime` int NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_sid` (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT ='sid绑定情况';

-- ----------------------------
-- Table structure for user_label
-- ----------------------------
CREATE TABLE `user_label` (
  `label_id` bigint(20) NOT NULL COMMENT '标签id',
  `label_name` varchar(20) DEFAULT '' COMMENT '标签名称',
  `heatness` int(11) NOT NULL DEFAULT 0 COMMENT '热度，每引用一次加1',
  `visible` smallint(2) NOT NULL DEFAULT '0' COMMENT '是否可见:0:隐藏,1:显示',
  `ctime` int NOT NULL COMMENT '创建时间',
  `utime` int NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`label_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签表';


-- ----------------------------
-- Table structure for user_label_detail
-- ----------------------------
CREATE TABLE `user_label_detail` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `label_id` bigint(20) NOT NULL COMMENT '标签id',
  `lable_name` varchar(20) DEFAULT NULL COMMENT '标签名称',
  `is_delete` smallint(6) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `ctime` int NOT NULL COMMENT '创建时间',
  `utime` int NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  Index `uk_userid_labelid` (`user_id`, `label_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户标签使用明细表';

-- ----------------------------
-- Table structure for sid
-- ----------------------------
CREATE TABLE `sid` (
  `id` BIGINT NOT NULL COMMENT 'id',
  `sid` VARCHAR(64) NOT NULL COMMENT 'sid',
  `ctime` int NOT NULL COMMENT '创建时间',
  `utime` int NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_sid` (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='sid管理表';
