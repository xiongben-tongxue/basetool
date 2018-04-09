-- -------------- ---
-- test---------------
-- -------------- ---
CREATE TABLE `t_person` (
  `uid` BIGINT NOT NULL COMMENT '用户id',
  `name` VARCHAR(10) NOT NULL COMMENT '姓名',
  `age` INTEGER (5) NOT NULL COMMENT '年龄',
  `gender` TINYINT NOT NULL COMMENT '性别',
  `ctime` INT NOT NULL COMMENT '创建时间',
  `utime` INT NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`),
  KEY `idx_uid` (`uid`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '人的实体类';


CREATE TABLE `t_messages_record` (
  `id` BIGINT NOT NULL COMMENT '消息主键id',
  `uid` BIGINT NOT NULL COMMENT '用户id',
  `account_id` BIGINT COMMENT '账号Id,账户变动的各种ID',
  `type` TINYINT NOT NULL COMMENT '消息类型',
  `title` VARCHAR(30) NOT NULL COMMENT '消息标题',
  `content` VARCHAR(512) NOT NULL COMMENT '消息内容',
  `url` VARCHAR(30) COMMENT '给以后的h5跳转用',
  `is_delete` TINYINT NOT NULL COMMENT '是否删除：1、是，2、否',
  `ctime` INT NOT NULL COMMENT '创建时间',
  `utime` INT NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_uid` (`uid`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '个人消息记录表';



CREATE TABLE `t_notices_record` (
  `id` BIGINT NOT NULL COMMENT '消息主键id',
  `activity_id` BIGINT COMMENT '各种通知广播ID',
  `type` TINYINT NOT NULL COMMENT '消息类型',
  `title` VARCHAR(30) NOT NULL COMMENT '消息标题',
  `content` VARCHAR(512) NOT NULL COMMENT '消息内容',
  `os_type` TINYINT NOT NULL COMMENT '操作系统类型,1,IOS,2,安卓,3,全平台',
  `url` VARCHAR(30) COMMENT '给以后的h5跳转用',
  `is_delete` TINYINT NOT NULL COMMENT '是否删除：1、是，2、否',
  `ctime` INT NOT NULL COMMENT '创建时间',
  `utime` INT NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '广播通知记录表';


/*
暂时不用
CREATE TABLE `t_uid_bindsid` (
  `id` BIGINT NOT NULL COMMENT '主键id',
  `uid` BIGINT NOT NULL COMMENT '用户id',
  `sid` BIGINT NOT NULL COMMENT '设备id',
  `is_delete` TINYINT NOT NULL COMMENT '是否删除：1、是，2、否',
  `ctime` INT NOT NULL COMMENT '创建时间',
  `utime` INT NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '用户Id和设备ID的绑定表';*/
