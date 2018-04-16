-- ----------------------------
-- Table structure for user_drawcoin_apply
-- 用户提币的申请
-- ----------------------------
CREATE TABLE `user_drawcoin_apply` (
  `uid` bigint NOT NULL COMMENT '用户id',
  `coin_type` tinyint(1) NOT NULL COMMENT '提币的类型：1、usdg，2、bty',
  `inner_address` varchar(50) NOT NULL COMMENT '用户在交易所对应币种的地址',
  `outer_address` varchar(50) NOT NULL COMMENT '用户把币提出去对应的地址',
  `coin_amount` decimal(10,5) NOT NULL COMMENT '提币的数量',
  `miner_amount` decimal(10,5) NOT NULL COMMENT '矿工费',
  `apply_statue` tinyint(1) NOT NULL COMMENT '提币审核：1、待审核，2、通过，3、拒绝',
  `ctime` int NOT NULL COMMENT '创建时间',
  `utime` int NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户提币的申请表';

-- ----------------------------
-- Table structure for user_rechargecoin
-- 用户充币的申请
-- ----------------------------
CREATE TABLE `user_rechargecoin` (
  `uid` bigint NOT NULL COMMENT '用户id',
  `coin_type` tinyint(1) NOT NULL COMMENT '充币的类型：1、usdg，2、bty',
  `inner_address` varchar(50) NOT NULL COMMENT '用户在交易所对应币种的地址',
  `outer_address` varchar(50) NOT NULL COMMENT '用户把币充值进来对应的地址',
  `coin_amount` decimal(10,5) NOT NULL COMMENT '充币的数量',
  `ctime` int NOT NULL COMMENT '创建时间',
  `utime` int NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户充币的申请表';

-- ----------------------------
-- Table structure for user_rechargecoin_apply
-- usdg交易所的官方信息表
-- ----------------------------
CREATE TABLE `usdg_official_account` (
  `id` bigint NOT NULL COMMENT '主键id',
  `type` tinyint(1) NOT NULL COMMENT '官方币的类型：1、usdg，2、bty',
  `name` varchar(50) NOT NULL COMMENT '币的名字',
  `address` varchar(50) NOT NULL COMMENT '交易所对应币种的地址',
  `real_amount` decimal(10,8) NOT NULL COMMENT '币的真实数量',
  `usable_amount` decimal(10,8) NOT NULL COMMENT '币的可用数量',
  `freeze_amount` decimal(10,8) NOT NULL COMMENT '币的可用数量',
  `ctime` int NOT NULL COMMENT '创建时间',
  `utime` int NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='usdg交易所的官方信息表';

-- ----------------------------
-- Table structure for usdg_user_account
-- 个人在usdg交易所的账户信息
-- ----------------------------
CREATE TABLE `usdg_user_account` (
  `uid` bigint NOT NULL COMMENT '主键uid',
  `type` tinyint(1) NOT NULL COMMENT '币的类型：1、usdg，2、bty',
  `name` varchar(50) NOT NULL COMMENT '币的名字',
  `address` varchar(50) NOT NULL COMMENT '交易所对应币种的地址',
  `real_amount` decimal(10,8) NOT NULL COMMENT '币的真实数量',
  `usable_amount` decimal(10,8) NOT NULL COMMENT '币的可用数量',
  `freeze_amount` decimal(10,8) NOT NULL COMMENT '币的可用数量',
  `ctime` int NOT NULL COMMENT '创建时间',
  `utime` int NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='个人在usdg交易所的账户信息';

-- ----------------------------
-- Table structure for bty_trade_marketprice
-- 比特元的市价交易表
-- ----------------------------
CREATE TABLE `bty_trade_marketprice` (
  `id` bigint NOT NULL COMMENT '流水编号',
  `uid` bigint NOT NULL COMMENT '主键uid',
  `send_address` varchar(50) NOT NULL COMMENT '打币的地址',
  `receive_address` varchar(50) NOT NULL COMMENT '打币的地址',
  `amount` decimal(10,8) NOT NULL COMMENT '币的交易数量',
  `rate` decimal(10,8) NOT NULL COMMENT '兑换比率，1bty对多少外币',
  `type` tinyint(1) NOT NULL COMMENT '兑换其他币的类型：1、usdg',
  `status` tinyint(1) NOT NULL COMMENT '交易的状态：1、确认中，2、已完成',
  `ctime` int NOT NULL COMMENT '创建时间',
  `utime` int NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='比特元的市价交易表';

-- ----------------------------
-- Table structure for usdg_trade_marketprice
-- usdg的市价交易表
-- ----------------------------
CREATE TABLE `usdg_trade_marketprice` (
  `id` bigint NOT NULL COMMENT '流水编号',
  `uid` bigint NOT NULL COMMENT '主键uid',
  `send_address` varchar(50) NOT NULL COMMENT '打币的地址',
  `receive_address` varchar(50) NOT NULL COMMENT '打币的地址',
  `amount` decimal(10,8) NOT NULL COMMENT '币的交易数量',
  `rate` decimal(10,8) NOT NULL COMMENT '兑换比率，1usdg对多少外币',
  `type` tinyint(1) NOT NULL COMMENT '兑换其他币的类型：1、bty',
  `status` tinyint(1) NOT NULL COMMENT '交易的状态：1、确认中，2、已完成',
  `ctime` int NOT NULL COMMENT '创建时间',
  `utime` int NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='usdg的市价交易表';
-- ----------------------------
-- Table structure for bty_trade_customprice
-- 比特元的限价交易表(用户自定义的价格)
-- ----------------------------
CREATE TABLE `bty_trade_customprice` (
  `id` bigint NOT NULL COMMENT '流水编号',
  `uid` bigint NOT NULL COMMENT '主键uid',
  `send_address` varchar(50) NOT NULL COMMENT '打币的地址',
  `receive_address` varchar(50) NOT NULL COMMENT '打币的地址',
  `amount` decimal(10,8) NOT NULL COMMENT '币的交易数量',
  `rate` decimal(10,8) NOT NULL COMMENT '兑换比率，1bty对多少外币',
  `type` tinyint(1) NOT NULL COMMENT '兑换其他币的类型：1、usdg',
  `status` tinyint(1) NOT NULL COMMENT '交易的状态：1、确认中，2、已完成',
  `ctime` int NOT NULL COMMENT '创建时间',
  `utime` int NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='比特元的限价交易表';

-- ----------------------------
-- Table structure for usdg_trade_customprice
-- usdg的限价交易表(用户自定义的价格)
-- ----------------------------
CREATE TABLE `usdg_trade_customprice` (
  `id` bigint NOT NULL COMMENT '流水编号',
  `uid` bigint NOT NULL COMMENT '主键uid',
  `send_address` varchar(50) NOT NULL COMMENT '打币的地址',
  `receive_address` varchar(50) NOT NULL COMMENT '打币的地址',
  `amount` decimal(10,8) NOT NULL COMMENT '币的交易数量',
  `rate` decimal(10,8) NOT NULL COMMENT '兑换比率，1usdg对多少外币',
  `type` tinyint(1) NOT NULL COMMENT '兑换其他币的类型：1、bty',
  `status` tinyint(1) NOT NULL COMMENT '交易的状态：1、确认中，2、已完成',
  `ctime` int NOT NULL COMMENT '创建时间',
  `utime` int NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='usdg的限价交易表';