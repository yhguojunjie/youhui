/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50018
Source Host           : localhost:3306
Source Database       : hudongtui

Target Server Type    : MYSQL
Target Server Version : 50018
File Encoding         : 65001

Date: 2015-07-19 20:15:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_aboutus`
-- ----------------------------
DROP TABLE IF EXISTS `t_aboutus`;
CREATE TABLE `t_aboutus` (
  `id` int(11) NOT NULL COMMENT 'id',
  `content` text COMMENT '内容',
  `agentId` int(11) default NULL COMMENT '代理商id',
  `createTime` datetime default NULL COMMENT '创建时间',
  `updateTime` datetime default NULL COMMENT '修改时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关于我们';

-- ----------------------------
-- Records of t_aboutus
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_bird_conf`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_bird_conf`;
CREATE TABLE `t_act_bird_conf` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `name` varchar(50) default NULL COMMENT '活动名称',
  `bAdvert` char(1) default NULL COMMENT '插入广告',
  `advertImgUrl` varchar(100) default NULL COMMENT '广告图',
  `advertUrl` varchar(500) default NULL COMMENT '广告链接地址',
  `shareImgUrl` varchar(100) default NULL COMMENT '分享图片',
  `shareTitle` varchar(100) default NULL COMMENT '分享标题',
  `shareDescription` varchar(250) default NULL COMMENT '分享简介',
  `awards` text COMMENT '奖项设置',
  `startTime` datetime default NULL COMMENT '开始时间',
  `endTime` datetime default NULL COMMENT '结束时间',
  `bUrlType` char(1) default NULL COMMENT '关注门槛',
  `followUrl` varchar(500) default NULL COMMENT '关注链接',
  `playTimes` int(11) default NULL COMMENT '每天玩的次数',
  `rule` text COMMENT '活动规则',
  `exExplain` varchar(150) default NULL COMMENT '兑换说明',
  `userinfo` varchar(100) default NULL COMMENT '用户信息',
  `createTime` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_bird_conf
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_bird_count`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_bird_count`;
CREATE TABLE `t_act_bird_count` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `mpOpenId` varchar(50) default NULL COMMENT '微信公众平台openId',
  `activityId` int(11) default NULL COMMENT '活动id',
  `leftTimes` int(11) default '0' COMMENT '剩余次数',
  `shareTime` datetime default NULL COMMENT '分享时间',
  `finalTime` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_bird_count
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_bird_prize`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_bird_prize`;
CREATE TABLE `t_act_bird_prize` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `prizeType` tinyint(4) default '0' COMMENT '奖品类型',
  `realNum` int(11) default '0' COMMENT '奖品实际数量',
  `deliverNum` int(11) default '0' COMMENT '奖品发出数量',
  `version` int(11) default '0' COMMENT '更新版本',
  `finalTime` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_bird_prize
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_bird_record`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_bird_record`;
CREATE TABLE `t_act_bird_record` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `mpOpenId` varchar(50) default NULL COMMENT '微信公众平台openId',
  `activityId` int(11) default NULL COMMENT '活动id',
  `prizeType` tinyint(4) default '0' COMMENT '奖项',
  `prizeName` varchar(50) default NULL COMMENT '奖品名称',
  `mailAddress` varchar(100) default NULL COMMENT '邮寄地址',
  `qq` varchar(50) default NULL COMMENT 'qq',
  `wechatId` varchar(50) default NULL COMMENT '微信号',
  `otherInfo` varchar(50) default NULL COMMENT '其他信息',
  `tel` varchar(11) default NULL COMMENT '电话号码',
  `username` varchar(50) default NULL COMMENT '用户名',
  `prizeTime` datetime default NULL COMMENT '中奖时间',
  `exchangeTime` datetime default NULL COMMENT '兑换时间',
  `opState` char(1) default NULL COMMENT '操作状态',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_bird_record
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_classic`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_classic`;
CREATE TABLE `t_act_classic` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `actId` int(11) default NULL COMMENT '活动id',
  `seq` int(11) default NULL COMMENT '顺序',
  `bannerName` varchar(50) default NULL COMMENT '品牌名(此字段为空，则使用活动默认)',
  `bannerLogo` varchar(150) default NULL COMMENT '品牌logo(此字段为空，则使用活动默认)',
  `createTime` datetime default NULL COMMENT '创建时间',
  `updateTime` datetime default NULL COMMENT '修改时间',
  `pluginId` int(11) default NULL COMMENT '插件id',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经典活动表';

-- ----------------------------
-- Records of t_act_classic
-- ----------------------------
INSERT INTO `t_act_classic` VALUES ('1', '1', '1', 'as', 'group1/M00/00/0A/cxyp0lUTtaCAfIVMAAAPB4Nl66E701.png', '2015-03-26 16:04:06', null, null);
INSERT INTO `t_act_classic` VALUES ('2', '2', '2', '2', 'group1/M00/00/0A/cxyp0lUT-qCAJe19AAAzJ90v7jA330.png', '2015-03-26 15:30:41', '2015-03-26 20:38:23', null);
INSERT INTO `t_act_classic` VALUES ('3', '3', '3', 'asdfsd', 'group1/M00/00/0A/cxyp0lUT-qCAJe19AAAzJ90v7jA330.png', '2015-03-26 21:08:48', null, null);
INSERT INTO `t_act_classic` VALUES ('4', '4', '4', 'rree', 'group1/M00/00/0A/cxyp0lUT-qCAJe19AAAzJ90v7jA330.png', '2015-03-26 21:10:08', null, null);
INSERT INTO `t_act_classic` VALUES ('5', '5', '5', 'bv', 'group1/M00/00/0A/cxyp0lUT-qCAJe19AAAzJ90v7jA330.png', '2015-03-26 21:10:24', null, null);
INSERT INTO `t_act_classic` VALUES ('6', '6', '6', 'dffd', 'group1/M00/00/0A/cxyp0lUT-qCAJe19AAAzJ90v7jA330.png', '2015-03-26 21:10:48', null, null);
INSERT INTO `t_act_classic` VALUES ('7', '7', '7', 'hjgh', 'group1/M00/00/0A/cxyp0lUT-qCAJe19AAAzJ90v7jA330.png', '2015-03-26 21:11:08', null, null);
INSERT INTO `t_act_classic` VALUES ('8', '8', '8', 'fghgf', 'group1/M00/00/0A/cxyp0lUT-qCAJe19AAAzJ90v7jA330.png', '2015-03-26 21:11:25', null, null);
INSERT INTO `t_act_classic` VALUES ('9', '9', '9', 'gfjhgf', 'group1/M00/00/0A/cxyp0lUT-qCAJe19AAAzJ90v7jA330.png', '2015-03-26 21:12:39', null, null);
INSERT INTO `t_act_classic` VALUES ('10', '10', '10', 'ccc', 'group1/M00/00/0A/cxyp0lUT-qCAJe19AAAzJ90v7jA330.png', '2015-03-26 21:12:54', null, null);
INSERT INTO `t_act_classic` VALUES ('11', '11', '11', '11', 'group1/M00/00/0A/cxyp0lUT-qCAJe19AAAzJ90v7jA330.png', '2015-03-27 09:40:14', null, null);

-- ----------------------------
-- Table structure for `t_act_classic_pic`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_classic_pic`;
CREATE TABLE `t_act_classic_pic` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `classicId` int(11) default NULL COMMENT '经典案件id',
  `url` varchar(100) default NULL COMMENT '图片url',
  `seq` int(11) default NULL COMMENT '顺序',
  `createTime` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经典活动案例图片表';

-- ----------------------------
-- Records of t_act_classic_pic
-- ----------------------------
INSERT INTO `t_act_classic_pic` VALUES ('1', '1', '1', null, null);
INSERT INTO `t_act_classic_pic` VALUES ('2', '1', '2', null, null);
INSERT INTO `t_act_classic_pic` VALUES ('9', '2', 'group1/M00/00/0A/cxyp0lUT-1GAKhSUAAAPB4Nl66E710.png', '1', '2015-03-26 20:28:11');

-- ----------------------------
-- Table structure for `t_act_fingerprint_conf`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_fingerprint_conf`;
CREATE TABLE `t_act_fingerprint_conf` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `name` varchar(50) default NULL COMMENT '活动名称',
  `bAdvert` char(1) default NULL COMMENT '插入广告',
  `advertImgUrl` varchar(100) default NULL COMMENT '广告图',
  `advertUrl` varchar(500) default NULL COMMENT '广告链接地址',
  `shareImgUrl` varchar(100) default NULL COMMENT '分享图片',
  `shareTitle` varchar(100) default NULL COMMENT '分享标题',
  `shareDescription` varchar(250) default NULL COMMENT '分享简介',
  `bShowList` char(1) default NULL COMMENT '显示名单',
  `awards` text COMMENT '奖项设置',
  `startTime` datetime default NULL COMMENT '开始时间',
  `endTime` datetime default NULL COMMENT '结束时间',
  `probability` int(11) default '0' COMMENT '中奖概率',
  `bUrlType` char(1) default NULL COMMENT '关注门槛',
  `followUrl` varchar(500) default NULL COMMENT '关注链接',
  `bCanPlay` char(1) default NULL COMMENT '中奖用户是否继续玩',
  `rule` text COMMENT '活动规则',
  `exExplain` varchar(150) default NULL COMMENT '兑换说明',
  `userinfo` varchar(100) default NULL COMMENT '用户信息',
  `relation` text COMMENT '关系',
  `prizeRelation` varchar(50) default NULL COMMENT '中奖关系',
  `coverUrl` varchar(100) default NULL COMMENT '封面',
  `bgUrl` varchar(100) default NULL COMMENT '首页背景',
  `createTime` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_fingerprint_conf
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_fingerprint_count`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_fingerprint_count`;
CREATE TABLE `t_act_fingerprint_count` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `mpOpenId` varchar(50) default NULL COMMENT '微信公众平台openId',
  `activityId` int(11) default NULL COMMENT '活动id',
  `helpNum` int(11) default '0' COMMENT '配对人数',
  `finalTime` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `mpOpenId_2` (`mpOpenId`,`activityId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_fingerprint_count
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_fingerprint_help`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_fingerprint_help`;
CREATE TABLE `t_act_fingerprint_help` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `helpOpenId` varchar(50) default NULL COMMENT '帮助人的openId',
  `helpedOpenId` varchar(50) default NULL COMMENT '被帮助人的openId',
  `status` char(1) default NULL COMMENT '状态(0:未中奖 1:中奖 2:访客)',
  `relation` char(10) default NULL COMMENT '前世关系',
  `helpTime` datetime default NULL COMMENT '帮助时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_fingerprint_help
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_fingerprint_prize`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_fingerprint_prize`;
CREATE TABLE `t_act_fingerprint_prize` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `realNum` int(11) default '0' COMMENT '奖品实际数量',
  `deliverNum` int(11) default '0' COMMENT '奖品发出数量',
  `version` int(11) default '0' COMMENT '更新版本',
  `finalTime` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_fingerprint_prize
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_fingerprint_record`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_fingerprint_record`;
CREATE TABLE `t_act_fingerprint_record` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `mpOpenId` varchar(50) default NULL COMMENT '微信公众平台openId',
  `helpOpenId` varchar(50) default NULL COMMENT '帮助人的openid',
  `activityId` int(11) default NULL COMMENT '活动id',
  `prizeName` varchar(50) default NULL COMMENT '奖品名称',
  `mailAddress` varchar(100) default NULL COMMENT '邮寄地址',
  `qq` varchar(50) default NULL COMMENT 'qq',
  `wechatId` varchar(50) default NULL COMMENT '微信号',
  `otherInfo` varchar(50) default NULL COMMENT '其他信息',
  `tel` varchar(11) default NULL COMMENT '电话号码',
  `username` varchar(50) default NULL COMMENT '用户名',
  `prizeTime` datetime default NULL COMMENT '中奖时间',
  `exchangeTime` datetime default NULL COMMENT '兑换时间',
  `opState` char(1) default NULL COMMENT '操作状态',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_fingerprint_record
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_foolredpacket_conf`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_foolredpacket_conf`;
CREATE TABLE `t_act_foolredpacket_conf` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `name` varchar(50) default NULL COMMENT '活动名称',
  `shareImgUrl` varchar(100) default NULL COMMENT '分享图片',
  `shareTitle` varchar(100) default NULL COMMENT '分享标题',
  `shareDescription` varchar(250) default NULL COMMENT '分享简介',
  `advertUrl` varchar(500) default NULL COMMENT '广告链接地址',
  `startTime` datetime default NULL COMMENT '开始时间',
  `endTime` datetime default NULL COMMENT '结束时间',
  `bUrlType` char(1) default NULL COMMENT '关注门槛',
  `followUrl` varchar(500) default NULL COMMENT '关注链接',
  `createTime` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_foolredpacket_conf
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_giftbox_box`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_giftbox_box`;
CREATE TABLE `t_act_giftbox_box` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `mpOpenId` varchar(50) default NULL COMMENT '微信公众平台openId',
  `helpTimes` int(11) default '0' COMMENT '礼盒被领取次数',
  `type` char(1) default NULL COMMENT '盒子的类型',
  `isPrize` char(1) default NULL COMMENT '是否中奖',
  `state` char(1) default NULL COMMENT '是否被领取',
  `createTime` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_giftbox_box
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_giftbox_conf`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_giftbox_conf`;
CREATE TABLE `t_act_giftbox_conf` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `name` varchar(50) default NULL COMMENT '活动名称',
  `bAdvert` char(1) default NULL COMMENT '插入广告',
  `advertImgUrl` varchar(100) default NULL COMMENT '广告图',
  `advertUrl` varchar(500) default NULL COMMENT '广告链接地址',
  `logoUrl` varchar(100) default NULL COMMENT '商家logo',
  `shareImgUrl` varchar(100) default NULL COMMENT '分享图片',
  `shareTitle` varchar(100) default NULL COMMENT '分享标题',
  `shareDescription` varchar(250) default NULL COMMENT '分享简介',
  `bShowNum` char(1) default NULL COMMENT '显示奖品数量',
  `bShowList` char(1) default NULL COMMENT '显示名单',
  `awards` text COMMENT '奖项设置',
  `startTime` datetime default NULL COMMENT '开始时间',
  `endTime` datetime default NULL COMMENT '结束时间',
  `playTimes` int(11) default '0' COMMENT '每人每天玩次数',
  `helpedTimes` int(11) default '0' COMMENT '需要帮拆的人数',
  `bCanPlay` char(1) default NULL COMMENT '中奖用户是否继续玩',
  `bUrlType` char(1) default NULL COMMENT '关注门槛',
  `followUrl` varchar(500) default NULL COMMENT '关注链接',
  `rule` text COMMENT '活动规则',
  `exExplain` varchar(150) default NULL COMMENT '兑换说明',
  `userinfo` varchar(100) default NULL COMMENT '用户信息',
  `bgUrl` varchar(100) default NULL COMMENT '首页背景',
  `boxBgUrl` varchar(100) default NULL COMMENT '我的礼盒背景',
  `selectBgUrl` varchar(100) default NULL COMMENT '挑选礼盒背景',
  `createTime` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_giftbox_conf
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_giftbox_count`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_giftbox_count`;
CREATE TABLE `t_act_giftbox_count` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `mpOpenId` varchar(50) default NULL COMMENT '微信公众平台openId',
  `activityId` int(11) default NULL COMMENT '活动id',
  `leftTimes` int(11) default NULL COMMENT '剩余次数',
  `finalTime` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_giftbox_count
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_giftbox_help`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_giftbox_help`;
CREATE TABLE `t_act_giftbox_help` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `helpOpenId` varchar(50) default NULL COMMENT '帮助人的openId',
  `helpedOpenId` varchar(50) default NULL COMMENT '被帮助人的openId',
  `boxId` int(11) default NULL COMMENT '礼盒id',
  `helpTime` datetime default NULL COMMENT '帮助时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_giftbox_help
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_giftbox_prize`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_giftbox_prize`;
CREATE TABLE `t_act_giftbox_prize` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `prizeType` tinyint(4) default '0' COMMENT '奖品类型',
  `realNum` int(11) default '0' COMMENT '奖品实际数量',
  `deliverNum` int(11) default '0' COMMENT '奖品发出数量',
  `version` int(11) default '0' COMMENT '更新版本',
  `finalTime` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_giftbox_prize
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_giftbox_record`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_giftbox_record`;
CREATE TABLE `t_act_giftbox_record` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `mpOpenId` varchar(50) default NULL COMMENT '微信公众平台openId',
  `activityId` int(11) default NULL COMMENT '活动id',
  `boxId` int(11) default NULL COMMENT '礼盒id',
  `prizeType` tinyint(4) default NULL COMMENT '奖项',
  `prizeName` varchar(50) default NULL COMMENT '奖品名称',
  `prizeImgUrl` varchar(100) default NULL COMMENT '奖品图片',
  `mailAddress` varchar(100) default NULL COMMENT '邮寄地址',
  `qq` varchar(50) default NULL COMMENT 'qq',
  `wechatId` varchar(50) default NULL COMMENT '微信号',
  `otherInfo` varchar(50) default NULL COMMENT '其他信息',
  `tel` varchar(11) default NULL COMMENT '电话号码',
  `username` varchar(50) default NULL COMMENT '用户名',
  `prizeTime` datetime default NULL COMMENT '中奖时间',
  `exchangeTime` datetime default NULL COMMENT '兑换时间',
  `opState` char(1) default NULL COMMENT '操作状态',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_giftbox_record
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_givegift_conf`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_givegift_conf`;
CREATE TABLE `t_act_givegift_conf` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `advertImgUrl` varchar(100) default NULL COMMENT '广告图',
  `advertUrl` varchar(500) default NULL COMMENT '广告链接地址',
  `shareImgUrl` varchar(100) default NULL COMMENT '分享图片',
  `shareTitle` varchar(100) default NULL COMMENT '分享标题',
  `shareDescription` varchar(250) default NULL COMMENT '分享简介',
  `title` varchar(50) default NULL COMMENT '活动标题',
  `setContent` text COMMENT '礼物设置',
  `startTime` datetime default NULL COMMENT '开始时间',
  `endTime` datetime default NULL COMMENT '结束时间',
  `bUrlType` char(1) default NULL COMMENT '关注门槛',
  `followUrl` varchar(500) default NULL COMMENT '关注链接',
  `couponName` varchar(100) default NULL COMMENT '优惠券名称',
  `rule` text COMMENT '使用规则',
  `bExchange` char(1) default NULL COMMENT '是否需要到店兑换',
  `bgUrl` varchar(150) default NULL COMMENT '背景图',
  `createTime` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_givegift_conf
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_givegift_record`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_givegift_record`;
CREATE TABLE `t_act_givegift_record` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `mpOpenId` varchar(50) default NULL COMMENT '微信公众平台openId',
  `prizeName` varchar(50) default NULL COMMENT '奖品名称',
  `tel` varchar(11) default NULL COMMENT '电话号码',
  `opState` char(1) default '0' COMMENT '操作状态',
  `prizeTime` datetime default NULL COMMENT '中奖时间',
  `exchangeTime` datetime default NULL COMMENT '兑换时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_givegift_record
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_givegift_userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_givegift_userinfo`;
CREATE TABLE `t_act_givegift_userinfo` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `mpOpenId` varchar(50) default NULL COMMENT '微信公众平台openId',
  `nickname` varchar(50) default NULL COMMENT '用户的昵称',
  `headimgurl` varchar(250) default NULL COMMENT '最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_givegift_userinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_greetingcard_conf`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_greetingcard_conf`;
CREATE TABLE `t_act_greetingcard_conf` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `name` varchar(50) default NULL COMMENT '活动名称',
  `shareLink` varchar(500) default NULL COMMENT '分享后跳转链接',
  `logoUrl` varchar(100) default NULL COMMENT '商家logo',
  `shareImgUrl` varchar(100) default NULL COMMENT '分享图片',
  `startTime` datetime default NULL COMMENT '开始时间',
  `endTime` datetime default NULL COMMENT '结束时间',
  `bUrlType` char(1) default NULL COMMENT '关注门槛',
  `followUrl` varchar(500) default NULL COMMENT '关注链接',
  `createTime` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_greetingcard_conf
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_guessgame_admin`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_guessgame_admin`;
CREATE TABLE `t_act_guessgame_admin` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `name` varchar(50) default NULL COMMENT '比赛名称',
  `leftTeam` varchar(50) default NULL COMMENT '左队',
  `leftPicUrl` varchar(100) default NULL COMMENT '左图',
  `rightTeam` varchar(50) default NULL COMMENT '右队',
  `rightPicUrl` varchar(100) default NULL COMMENT '右图',
  `startTime` datetime default NULL COMMENT '开始时间',
  `endTime` datetime default NULL COMMENT '结束时间',
  `leftScore` int(11) default '-1' COMMENT '左队成绩',
  `rightScore` int(11) default '-1' COMMENT '右队成绩',
  `leftPeople` int(11) default '0' COMMENT '猜左队赢的人',
  `rightPeople` int(11) default '0' COMMENT '猜右队赢的人',
  `updateTime` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_guessgame_admin
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_guessgame_betrecord`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_guessgame_betrecord`;
CREATE TABLE `t_act_guessgame_betrecord` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `mpOpenId` varchar(50) default NULL COMMENT '微信公众平台openId',
  `matchId` int(11) default NULL COMMENT '比赛id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `comment` varchar(150) default NULL COMMENT '评论',
  `bJoinType` char(1) default NULL COMMENT '参与形式(0：猜输赢，1：猜比分)',
  `bPrize` char(1) default NULL COMMENT '是否猜对中奖（1：中奖）',
  `leftScore` int(11) default '0' COMMENT '左队分数',
  `rightScore` int(11) default '0' COMMENT '右队分数',
  `betTime` datetime default NULL COMMENT '投注时间',
  `mailAddress` varchar(100) default NULL COMMENT '邮寄地址',
  `qq` varchar(50) default NULL COMMENT 'qq',
  `wechatId` varchar(50) default NULL COMMENT '微信号',
  `otherInfo` varchar(50) default NULL COMMENT '其他信息',
  `tel` varchar(11) default NULL COMMENT '电话号码',
  `username` varchar(50) default NULL COMMENT '用户名',
  `exchangeTime` datetime default NULL COMMENT '兑换时间',
  `opState` char(1) default NULL COMMENT '操作状态',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_guessgame_betrecord
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_guessgame_conf`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_guessgame_conf`;
CREATE TABLE `t_act_guessgame_conf` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `name` varchar(50) default NULL COMMENT '活动名称',
  `bAdvert` char(1) default NULL COMMENT '插入广告',
  `advertImgUrl` varchar(100) default NULL COMMENT '广告图',
  `advertUrl` varchar(500) default NULL COMMENT '广告链接地址',
  `shareImgUrl` varchar(100) default NULL COMMENT '分享图片',
  `shareTitle` varchar(100) default NULL COMMENT '分享标题',
  `shareDescription` varchar(250) default NULL COMMENT '分享简介',
  `startTime` datetime default NULL COMMENT '开始时间',
  `endTime` datetime default NULL COMMENT '结束时间',
  `bUrlType` char(1) default NULL COMMENT '关注门槛',
  `followUrl` varchar(500) default NULL COMMENT '关注链接',
  `bJoinType` char(1) default NULL COMMENT '中奖用户是否继续玩',
  `rule` text COMMENT '活动规则',
  `exExplain` varchar(150) default NULL COMMENT '兑换说明',
  `userinfo` varchar(100) default NULL COMMENT '用户信息',
  `bgUrl` varchar(100) default NULL COMMENT '首页背景',
  `createTime` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_guessgame_conf
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_magazine_conf`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_magazine_conf`;
CREATE TABLE `t_act_magazine_conf` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `name` varchar(100) default NULL COMMENT '名称',
  `shareImgUrl` varchar(100) default NULL COMMENT '分享图片',
  `shareTitle` varchar(100) default NULL COMMENT '分享标题',
  `shareDescription` varchar(250) default NULL COMMENT '分享简介',
  `shareLink` varchar(500) default NULL COMMENT '分享后跳转链接',
  `effect` char(1) default NULL COMMENT '微场景效果',
  `bgMusic` char(1) default NULL COMMENT '背景音乐',
  `bgMusicUrl` varchar(150) default NULL COMMENT '背景音乐链接',
  `iconEffect` char(1) default NULL COMMENT '音乐图表及效果',
  `content` text COMMENT '内容配置',
  `startTime` datetime default NULL COMMENT '开始时间',
  `endTime` datetime default NULL COMMENT '结束时间',
  `createTime` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_magazine_conf
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_mature_conf`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_mature_conf`;
CREATE TABLE `t_act_mature_conf` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `title` varchar(100) default NULL COMMENT '活动标题',
  `shareImgUrl` varchar(100) default NULL COMMENT '分享图片',
  `bAdvert` char(1) default NULL COMMENT '插入广告',
  `advertImgUrl` varchar(150) default NULL COMMENT '广告图',
  `advertUrl` varchar(500) default NULL COMMENT '广告链接地址',
  `shareTitle` varchar(100) default NULL COMMENT '分享标题',
  `shareDescription` varchar(250) default NULL COMMENT '分享简介',
  `startTime` datetime default NULL COMMENT '开始时间',
  `endTime` datetime default NULL COMMENT '结束时间',
  `needCount` int(11) default '0' COMMENT '兑换个数',
  `rule` text COMMENT '活动规则',
  `exExplain` varchar(150) default NULL COMMENT '兑换说明',
  `userinfo` varchar(100) default NULL COMMENT '用户信息',
  `coverUrl` varchar(100) default NULL COMMENT '封面',
  `createTime` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_mature_conf
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_mature_count`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_mature_count`;
CREATE TABLE `t_act_mature_count` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `mpOpenId` varchar(50) default NULL COMMENT '微信公众平台openId',
  `activityId` int(11) default NULL COMMENT '活动id',
  `totalNum` int(11) default '0' COMMENT '总的红包数',
  `getNum` int(11) default '0' COMMENT '采到的红包个数',
  `stealNum` int(11) default '0' COMMENT '总的红包数',
  `todayNum` int(11) default '0' COMMENT '当天的红包总数',
  `totalExNum` int(11) default '0' COMMENT '总的兑换红包总数',
  `finalTime` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_mature_count
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_mature_help`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_mature_help`;
CREATE TABLE `t_act_mature_help` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `helpOpenId` varchar(50) default NULL COMMENT '帮助人的openId',
  `helpedOpenId` varchar(50) default NULL COMMENT '被帮助人的openId',
  `status` char(1) default NULL COMMENT '状态（0：浇水 1：采摘）',
  `bless` varchar(50) default NULL COMMENT '祝福语',
  `createTime` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_mature_help
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_mature_record`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_mature_record`;
CREATE TABLE `t_act_mature_record` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `mpOpenId` varchar(50) default NULL COMMENT '微信公众平台openId',
  `activityId` int(11) default NULL COMMENT '活动id',
  `mailAddress` varchar(100) default NULL COMMENT '邮寄地址',
  `qq` varchar(50) default NULL COMMENT 'qq',
  `wechatId` varchar(50) default NULL COMMENT '微信号',
  `otherInfo` varchar(50) default NULL COMMENT '其他信息',
  `tel` varchar(11) default NULL COMMENT '电话号码',
  `username` varchar(50) default NULL COMMENT '用户名',
  `count` int(11) default '0' COMMENT '红包个数',
  `submitTime` datetime default NULL COMMENT '提交时间',
  `exchangeTime` datetime default NULL COMMENT '兑换时间',
  `opState` char(1) default NULL COMMENT '操作状态',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_mature_record
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_pintu_conf`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_pintu_conf`;
CREATE TABLE `t_act_pintu_conf` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `title` varchar(100) default NULL COMMENT '活动标题',
  `bAdvert` char(1) default NULL COMMENT '插入广告',
  `advertImgUrl` varchar(150) default NULL COMMENT '广告图',
  `advertUrl` varchar(500) default NULL COMMENT '广告链接地址',
  `shareImgUrl` varchar(100) default NULL COMMENT '分享图片',
  `shareTitle` varchar(100) default NULL COMMENT '分享标题',
  `shareDescription` varchar(250) default NULL COMMENT '分享简介',
  `pictures` text COMMENT '图库',
  `bShowNum` char(1) default NULL COMMENT '显示奖品数量',
  `awards` text COMMENT '奖项设置',
  `startTime` datetime default NULL COMMENT '开始时间',
  `endTime` datetime default NULL COMMENT '结束时间',
  `bUrlType` char(1) default NULL COMMENT '关注门槛',
  `followUrl` varchar(500) default NULL COMMENT '关注链接',
  `levelTimes` int(11) default '0' COMMENT '关数',
  `countTime` int(11) default '0' COMMENT '倒计时间',
  `playTimes` int(11) default '0' COMMENT '每人每天抽奖次数',
  `rule` text COMMENT '活动规则',
  `exExplain` varchar(150) default NULL COMMENT '兑换说明',
  `userinfo` varchar(100) default NULL COMMENT '用户信息',
  `coverUrl` varchar(150) default NULL COMMENT '封面',
  `bgUrl` varchar(150) default NULL COMMENT '背景图',
  `createTime` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_pintu_conf
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_pintu_count`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_pintu_count`;
CREATE TABLE `t_act_pintu_count` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `mpOpenId` varchar(50) default NULL COMMENT '微信公众平台openId',
  `bShare` char(1) default NULL COMMENT '当天是否分享',
  `activityId` int(11) default NULL COMMENT '活动id',
  `afterShareTimes` int(11) default '0' COMMENT '分享之后玩的次数',
  `leftTimes` int(11) default '0' COMMENT '剩余次数',
  `shareTime` datetime default NULL COMMENT '分享时间',
  `finalTime` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_pintu_count
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_pintu_prize`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_pintu_prize`;
CREATE TABLE `t_act_pintu_prize` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `prizeType` tinyint(4) default '0' COMMENT '奖品类型',
  `realNum` int(11) default '0' COMMENT '奖品实际数量',
  `deliverNum` int(11) default '0' COMMENT '奖品发出数量',
  `version` int(11) default '0' COMMENT '更新版本',
  `finalTime` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_pintu_prize
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_pintu_record`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_pintu_record`;
CREATE TABLE `t_act_pintu_record` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `mpOpenId` varchar(50) default NULL COMMENT '微信公众平台openId',
  `activityId` int(11) default NULL COMMENT '活动id',
  `prizeType` tinyint(4) default '0' COMMENT '奖项',
  `prizeName` varchar(50) default NULL COMMENT '奖品名称',
  `prizeImgUrl` varchar(100) default NULL COMMENT '奖品图片',
  `mailAddress` varchar(100) default NULL COMMENT '邮寄地址',
  `qq` varchar(50) default NULL COMMENT 'qq',
  `wechatId` varchar(50) default NULL COMMENT '微信号',
  `otherInfo` varchar(50) default NULL COMMENT '其他信息',
  `tel` varchar(11) default NULL COMMENT '电话号码',
  `username` varchar(50) default NULL COMMENT '用户名',
  `bestScore` int(11) default '0' COMMENT '最高分',
  `prizeTime` datetime default NULL COMMENT '中奖时间',
  `exchangeTime` datetime default NULL COMMENT '兑换时间',
  `opState` char(1) default NULL COMMENT '操作状态',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_pintu_record
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_poker_conf`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_poker_conf`;
CREATE TABLE `t_act_poker_conf` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `name` varchar(50) default NULL COMMENT '活动名称',
  `bAdvert` char(1) default NULL COMMENT '插入广告',
  `advertImgUrl` varchar(100) default NULL COMMENT '广告图',
  `advertUrl` varchar(500) default NULL COMMENT '广告链接地址',
  `shareImgUrl` varchar(100) default NULL COMMENT '分享图片',
  `shareTitle` varchar(100) default NULL COMMENT '分享标题',
  `shareDescription` varchar(250) default NULL COMMENT '分享简介',
  `bShowNum` char(1) default NULL COMMENT '显示奖品数量',
  `bShowList` char(1) default NULL COMMENT '显示名单',
  `awards` text COMMENT '奖项设置',
  `startTime` datetime default NULL COMMENT '开始时间',
  `endTime` datetime default NULL COMMENT '结束时间',
  `playTimes` int(11) default '0' COMMENT '每盘几局连胜获奖',
  `bUrlType` char(1) default NULL COMMENT '关注门槛',
  `followUrl` varchar(500) default NULL COMMENT '关注链接',
  `rule` text COMMENT '活动规则',
  `exExplain` varchar(150) default NULL COMMENT '兑换说明',
  `userinfo` varchar(100) default NULL COMMENT '用户信息',
  `coverUrl` varchar(100) default NULL COMMENT '封面',
  `bgUrl` varchar(100) default NULL COMMENT '首页背景',
  `createTime` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_poker_conf
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_poker_count`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_poker_count`;
CREATE TABLE `t_act_poker_count` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `mpOpenId` varchar(50) default NULL COMMENT '微信公众平台openId',
  `activityId` int(11) default NULL COMMENT '活动id',
  `winNum` int(11) default '0' COMMENT '连赢盘数',
  `pkNum` int(11) default '0' COMMENT '挑战人数',
  `finalTime` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_poker_count
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_poker_pk`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_poker_pk`;
CREATE TABLE `t_act_poker_pk` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `pkOpenId` varchar(50) default NULL COMMENT 'pk的openId',
  `pkedOpenId` varchar(50) default NULL COMMENT '被pk的openId',
  `pkCards` varchar(15) default NULL COMMENT 'pk的牌',
  `pkedCards` varchar(15) default NULL COMMENT '被pk的牌',
  `bResult` char(1) default NULL COMMENT 'pk人输赢结果（0：输 1：赢）',
  `bOpen` char(1) default NULL COMMENT '是否开牌',
  `createTime` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_poker_pk
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_poker_prize`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_poker_prize`;
CREATE TABLE `t_act_poker_prize` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `realNum` int(11) default '0' COMMENT '奖品实际数量',
  `deliverNum` int(11) default '0' COMMENT '奖品发出数量',
  `version` int(11) default '0' COMMENT '更新版本',
  `finalTime` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_poker_prize
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_poker_record`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_poker_record`;
CREATE TABLE `t_act_poker_record` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `mpOpenId` varchar(50) default NULL COMMENT '微信公众平台openId',
  `activityId` int(11) default NULL COMMENT '活动id',
  `prizeName` varchar(50) default NULL COMMENT '奖品名称',
  `mailAddress` varchar(100) default NULL COMMENT '邮寄地址',
  `qq` varchar(50) default NULL COMMENT 'qq',
  `wechatId` varchar(50) default NULL COMMENT '微信号',
  `otherInfo` varchar(50) default NULL COMMENT '其他信息',
  `tel` varchar(11) default NULL COMMENT '电话号码',
  `username` varchar(50) default NULL COMMENT '用户名',
  `prizeTime` datetime default NULL COMMENT '中奖时间',
  `exchangeTime` datetime default NULL COMMENT '兑换时间',
  `opState` char(1) default NULL COMMENT '操作状态',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_poker_record
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_question_bank`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_question_bank`;
CREATE TABLE `t_act_question_bank` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `question` text COMMENT '题目',
  `finalTime` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_question_bank
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_question_conf`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_question_conf`;
CREATE TABLE `t_act_question_conf` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `name` varchar(50) default NULL COMMENT '活动名称',
  `bAdvert` char(1) default NULL COMMENT '插入广告',
  `advertImgUrl` varchar(100) default NULL COMMENT '广告图',
  `advertUrl` varchar(500) default NULL COMMENT '广告链接地址',
  `shareImgUrl` varchar(100) default NULL COMMENT '分享图片',
  `shareTitle` varchar(100) default NULL COMMENT '分享标题',
  `shareDescription` varchar(250) default NULL COMMENT '分享简介',
  `bQestType` char(1) default NULL COMMENT '题目设置',
  `questionNum` int(11) default NULL COMMENT '题目数量',
  `questionTime` int(11) default NULL COMMENT '答题时间',
  `awards` text COMMENT '奖项设置',
  `startTime` datetime default NULL COMMENT '开始时间',
  `endTime` datetime default NULL COMMENT '结束时间',
  `bUrlType` char(1) default NULL COMMENT '关注门槛',
  `followUrl` varchar(500) default NULL COMMENT '关注链接',
  `playTimes` int(11) default NULL COMMENT '每天玩的次数',
  `rule` text COMMENT '活动规则',
  `exExplain` varchar(150) default NULL COMMENT '兑换说明',
  `userinfo` varchar(100) default NULL COMMENT '用户信息',
  `coverUrl` varchar(100) default NULL COMMENT '封面',
  `bgUrl` varchar(100) default NULL COMMENT '首页背景',
  `createTime` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_question_conf
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_question_count`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_question_count`;
CREATE TABLE `t_act_question_count` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `mpOpenId` varchar(50) default NULL COMMENT '微信公众平台openId',
  `activityId` int(11) default NULL COMMENT '活动id',
  `leftTimes` int(11) default '0' COMMENT '剩余次数',
  `shareTime` datetime default NULL COMMENT '分享时间',
  `finalTime` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_question_count
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_question_prize`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_question_prize`;
CREATE TABLE `t_act_question_prize` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `prizeType` tinyint(4) default '0' COMMENT '奖品类型',
  `realNum` int(11) default '0' COMMENT '奖品实际数量',
  `deliverNum` int(11) default '0' COMMENT '奖品发出数量',
  `version` int(11) default '0' COMMENT '更新版本',
  `finalTime` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_question_prize
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_question_record`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_question_record`;
CREATE TABLE `t_act_question_record` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `mpOpenId` varchar(50) default NULL COMMENT '微信公众平台openId',
  `activityId` int(11) default NULL COMMENT '活动id',
  `prizeType` tinyint(4) default '0' COMMENT '奖项',
  `prizeName` varchar(50) default NULL COMMENT '奖品名称',
  `prizeImgUrl` varchar(100) default NULL COMMENT '奖品图片',
  `mailAddress` varchar(100) default NULL COMMENT '邮寄地址',
  `qq` varchar(50) default NULL COMMENT 'qq',
  `wechatId` varchar(50) default NULL COMMENT '微信号',
  `otherInfo` varchar(50) default NULL COMMENT '其他信息',
  `tel` varchar(11) default NULL COMMENT '电话号码',
  `username` varchar(50) default NULL COMMENT '用户名',
  `prizeTime` datetime default NULL COMMENT '中奖时间',
  `exchangeTime` datetime default NULL COMMENT '兑换时间',
  `opState` char(1) default NULL COMMENT '操作状态',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_question_record
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_question_score`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_question_score`;
CREATE TABLE `t_act_question_score` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `mpOpenId` varchar(50) default NULL COMMENT '微信公众平台openId',
  `activityId` int(11) default NULL COMMENT '活动id',
  `playTime` int(11) default '0' COMMENT '玩的时间',
  `rightNum` int(11) default '0' COMMENT '答对题数',
  `updateTime` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_question_score
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_runman_conf`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_runman_conf`;
CREATE TABLE `t_act_runman_conf` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `title` varchar(100) default NULL COMMENT '活动标题',
  `bAdvert` char(1) default NULL COMMENT '插入广告',
  `advertImgUrl` varchar(150) default NULL COMMENT '广告图',
  `advertUrl` varchar(500) default NULL COMMENT '广告链接地址',
  `shareImgUrl` varchar(100) default NULL COMMENT '分享图片',
  `shareTitle` varchar(100) default NULL COMMENT '分享标题',
  `shareDescription` varchar(250) default NULL COMMENT '分享简介',
  `afterShareUrl` varchar(100) default NULL COMMENT '分享成功跳转链接',
  `bShowNum` char(1) default NULL COMMENT '显示奖品数量',
  `awards` text COMMENT '奖项设置',
  `startTime` datetime default NULL COMMENT '开始时间',
  `endTime` datetime default NULL COMMENT '结束时间',
  `bUrlType` char(1) default NULL COMMENT '关注门槛',
  `followUrl` varchar(500) default NULL COMMENT '关注链接',
  `playTimes` int(11) default '0' COMMENT '每人每天抽奖次数',
  `rule` text COMMENT '活动规则',
  `exExplain` varchar(150) default NULL COMMENT '兑换说明',
  `userinfo` varchar(100) default NULL COMMENT '用户信息',
  `createTime` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_runman_conf
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_runman_count`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_runman_count`;
CREATE TABLE `t_act_runman_count` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `mpOpenId` varchar(50) default NULL COMMENT '微信公众平台openId',
  `bShare` char(1) default NULL COMMENT '当天是否分享',
  `activityId` int(11) default NULL COMMENT '活动id',
  `afterShareTimes` int(11) default '0' COMMENT '分享之后玩的次数',
  `leftTimes` int(11) default '0' COMMENT '剩余次数',
  `shareTime` datetime default NULL COMMENT '分享时间',
  `finalTime` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_runman_count
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_runman_prize`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_runman_prize`;
CREATE TABLE `t_act_runman_prize` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `prizeType` tinyint(4) default '0' COMMENT '奖品类型',
  `realNum` int(11) default '0' COMMENT '奖品实际数量',
  `deliverNum` int(11) default '0' COMMENT '奖品发出数量',
  `version` int(11) default '0' COMMENT '更新版本',
  `finalTime` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_runman_prize
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_runman_record`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_runman_record`;
CREATE TABLE `t_act_runman_record` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `mpOpenId` varchar(50) default NULL COMMENT '微信公众平台openId',
  `activityId` int(11) default NULL COMMENT '活动id',
  `prizeType` tinyint(4) default '0' COMMENT '奖项',
  `prizeName` varchar(50) default NULL COMMENT '奖品名称',
  `prizeImgUrl` varchar(100) default NULL COMMENT '奖品图片',
  `mailAddress` varchar(100) default NULL COMMENT '邮寄地址',
  `qq` varchar(50) default NULL COMMENT 'qq',
  `wechatId` varchar(50) default NULL COMMENT '微信号',
  `otherInfo` varchar(50) default NULL COMMENT '其他信息',
  `tel` varchar(11) default NULL COMMENT '电话号码',
  `username` varchar(50) default NULL COMMENT '用户名',
  `bestScore` int(11) default '0' COMMENT '最高分',
  `prizeTime` datetime default NULL COMMENT '中奖时间',
  `exchangeTime` datetime default NULL COMMENT '兑换时间',
  `opState` char(1) default NULL COMMENT '操作状态',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_runman_record
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_tree_conf`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_tree_conf`;
CREATE TABLE `t_act_tree_conf` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `name` varchar(50) default NULL COMMENT '活动名称',
  `bAdvert` char(1) default NULL COMMENT '插入广告',
  `advertImgUrl` varchar(100) default NULL COMMENT '广告图',
  `advertUrl` varchar(500) default NULL COMMENT '广告链接地址',
  `shareImgUrl` varchar(100) default NULL COMMENT '分享图片',
  `shareTitle` varchar(100) default NULL COMMENT '分享标题',
  `shareDescription` varchar(250) default NULL COMMENT '分享简介',
  `bShowNum` char(1) default NULL COMMENT '显示奖品数量',
  `bShowList` char(1) default NULL COMMENT '显示名单',
  `awards` text COMMENT '奖项设置',
  `startTime` datetime default NULL COMMENT '开始时间',
  `endTime` datetime default NULL COMMENT '结束时间',
  `bUrlType` char(1) default NULL COMMENT '关注门槛',
  `followUrl` varchar(500) default NULL COMMENT '关注链接',
  `playTimes` int(11) default '0' COMMENT '每人每天玩次数',
  `bCanPlay` char(1) default NULL COMMENT '中奖用户是否继续玩',
  `rule` text COMMENT '活动规则',
  `exExplain` varchar(150) default NULL COMMENT '兑换说明',
  `userinfo` varchar(100) default NULL COMMENT '用户信息',
  `coverUrl` varchar(150) default NULL COMMENT '封面',
  `treeUrl` varchar(150) default NULL COMMENT '树图',
  `bgUrl` varchar(150) default NULL COMMENT '背景图',
  `createTime` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_tree_conf
-- ----------------------------
INSERT INTO `t_act_tree_conf` VALUES ('1', '23', '摇一摇，中大奖', '0', '', 'http://wx.tchajian.com', 'group1/M00/00/0A/eSgThlSfzeOAAYz7AABACQHEeFE892.gif', '快来摇一摇！中大奖哦~', '就快掉下来了！加把劲呀~', '0', '0', '[{\"prizeType\":\"1\",\"prizeName\":\"立可拍\",\"showNum\":\"10\",\"realNum\":\"0\",\"probability\":\"0\",\"picUrl\":\"group1/M00/00/12/eSgThlSjndiAPAnFAAEO0gZHW7c637.png\",\"exchangeState\":\"1\"},{\"prizeType\":\"2\",\"prizeName\":\"100元红包\",\"showNum\":\"50\",\"realNum\":\"0\",\"probability\":\"0\",\"picUrl\":\"group1/M00/00/0A/eSgThlSetF6AUnpSAAA-qb6oTZk859.png\",\"exchangeState\":\"1\"},{\"prizeType\":\"3\",\"prizeName\":\"10元话费\",\"showNum\":\"100\",\"realNum\":\"10\",\"probability\":\"10\",\"picUrl\":\"group1/M00/00/12/eSgThlSjnmiAYwgYAAA7AbvWi0U111.png\",\"exchangeState\":\"1\"}]', '2015-03-31 00:00:00', '2015-04-19 23:59:59', '0', 'http://www.baidu.com', '2', '1', '• 活动时间：  至  。\r\n• 每人每天可抽奖1次。\r\n• 分享到朋友圈可额外获得1次抽奖机会。\r\n• 请中奖用户关注公众号，回复手机号兑奖。\r\n(插件演示，纯属体验，不予兑奖！)', '请填写以下资料：(插件演示，纯属体验，不予兑奖！)', 'qq,', 'group1/M00/00/A1/eSgThlUZJGuAPihqAACjZ9SSdp8672.gif', 'group1/M00/00/0A/eSgThlSf6tCAFgxNAAFrx6rKnlM990.gif', 'group1/M00/00/0A/eSgThlSetFWAEYXAAAGrFoad0TM063.jpg', '2015-03-31 21:07:59');

-- ----------------------------
-- Table structure for `t_act_tree_count`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_tree_count`;
CREATE TABLE `t_act_tree_count` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `mpOpenId` varchar(50) default NULL COMMENT '微信公众平台openId',
  `activityId` int(11) default NULL COMMENT '活动id',
  `bShare` char(1) default NULL COMMENT '当天是否分享过',
  `afterShareTimes` int(11) default '0' COMMENT '分享之后玩的次数',
  `leftTimes` int(11) default '0' COMMENT '剩余次数',
  `shareTime` datetime default NULL COMMENT '分享时间',
  `finalTime` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`),
  KEY `idx_t_act_tree_count_mpOpenId` (`mpOpenId`),
  KEY `idx_t_act_tree_count_openId_actId` (`mpOpenId`,`activityId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_tree_count
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_tree_prize`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_tree_prize`;
CREATE TABLE `t_act_tree_prize` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `prizeType` tinyint(4) default '0' COMMENT '奖品类型',
  `realNum` int(11) default '0' COMMENT '奖品实际数量',
  `deliverNum` int(11) default '0' COMMENT '奖品发出数量',
  `version` int(11) default '0' COMMENT '更新版本',
  `finalTime` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_tree_prize
-- ----------------------------
INSERT INTO `t_act_tree_prize` VALUES ('1', '23', '1', '0', '0', '2', '2015-03-31 21:13:40');
INSERT INTO `t_act_tree_prize` VALUES ('2', '23', '2', '0', '0', '2', '2015-03-31 21:13:40');
INSERT INTO `t_act_tree_prize` VALUES ('3', '23', '3', '10', '0', '2', '2015-03-31 21:13:40');

-- ----------------------------
-- Table structure for `t_act_tree_record`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_tree_record`;
CREATE TABLE `t_act_tree_record` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `mpOpenId` varchar(50) default NULL COMMENT '微信公众平台openId',
  `activityId` int(11) default NULL COMMENT '活动id',
  `prizeType` tinyint(4) default NULL COMMENT '奖项',
  `prizeName` varchar(50) default NULL COMMENT '奖品名称',
  `prizeImgUrl` varchar(100) default NULL COMMENT '奖品图片',
  `mailAddress` varchar(100) default NULL COMMENT '邮寄地址',
  `qq` varchar(50) default NULL COMMENT 'qq',
  `wechatId` varchar(50) default NULL COMMENT '微信号',
  `otherInfo` varchar(50) default NULL COMMENT '其他信息',
  `tel` varchar(11) default NULL COMMENT '电话号码',
  `username` varchar(50) default NULL COMMENT '用户名',
  `prizeTime` datetime default NULL COMMENT '中奖时间',
  `exchangeTime` datetime default NULL COMMENT '兑换时间',
  `opState` char(1) default NULL COMMENT '操作状态',
  PRIMARY KEY  (`id`),
  KEY `idx_t_act_tree_record_acid` (`activityId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_tree_record
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_vote_conf`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_vote_conf`;
CREATE TABLE `t_act_vote_conf` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `name` varchar(50) default NULL COMMENT '活动名称',
  `bAdvert` char(1) default NULL COMMENT '插入广告',
  `advertImgUrl` varchar(100) default NULL COMMENT '广告图',
  `advertUrl` varchar(500) default NULL COMMENT '广告链接地址',
  `shareImgUrl` varchar(100) default NULL COMMENT '分享图片',
  `shareTitle` varchar(100) default NULL COMMENT '分享标题',
  `shareDescription` varchar(250) default NULL COMMENT '分享简介',
  `startTime` datetime default NULL COMMENT '开始时间',
  `endTime` datetime default NULL COMMENT '结束时间',
  `bUrlType` char(1) default NULL COMMENT '关注门槛',
  `followUrl` varchar(500) default NULL COMMENT '关注链接',
  `playPeople` int(11) default NULL COMMENT '每天玩的次数',
  `rule` text COMMENT '活动规则',
  `publicInfo` varchar(200) default NULL COMMENT '兑换说明',
  `hideInfo` varchar(200) default NULL COMMENT '用户信息',
  `bgUrl` varchar(100) default NULL COMMENT '首页背景',
  `createTime` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_vote_conf
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_vote_count`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_vote_count`;
CREATE TABLE `t_act_vote_count` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `mpOpenId` varchar(50) default NULL COMMENT '微信公众平台openId',
  `bVoted` char(1) default NULL COMMENT '操作状态',
  `voterId` int(11) default NULL COMMENT '活动id',
  `finalTime` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_vote_count
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_vote_voter`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_vote_voter`;
CREATE TABLE `t_act_vote_voter` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `voterId` int(11) default NULL COMMENT '活动id',
  `picUrl` varchar(100) default NULL COMMENT '首页背景',
  `title` varchar(50) default NULL COMMENT '用户名',
  `descs` varchar(150) default NULL COMMENT '用户名',
  `otherInfo1` varchar(50) default NULL COMMENT '用户名',
  `otherInfo2` varchar(50) default NULL COMMENT '用户名',
  `otherInfo3` varchar(50) default NULL COMMENT '用户名',
  `tel` varchar(11) default NULL COMMENT '电话号码',
  `username` varchar(50) default NULL COMMENT '用户名',
  `mailAddress` varchar(100) default NULL COMMENT '邮寄地址',
  `qq` varchar(50) default NULL COMMENT 'qq',
  `wechatId` varchar(50) default NULL COMMENT '微信号',
  `otherInfo4` varchar(50) default NULL COMMENT '其他信息',
  `voteNum` int(11) default '0' COMMENT '票数',
  `finalTime` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_vote_voter
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_vote_votercount`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_vote_votercount`;
CREATE TABLE `t_act_vote_votercount` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `num` int(11) default '0' COMMENT '活动id',
  `finalTime` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_vote_votercount
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_weixin_userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_weixin_userinfo`;
CREATE TABLE `t_act_weixin_userinfo` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `mpOpenId` varchar(50) default NULL COMMENT '微信公众平台openId',
  `nickname` varchar(50) default NULL COMMENT '用户的昵称',
  `headimgurl` varchar(250) default NULL COMMENT '最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空',
  `updateTime` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_weixin_userinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_zanrenqi_conf`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_zanrenqi_conf`;
CREATE TABLE `t_act_zanrenqi_conf` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `name` varchar(50) default NULL COMMENT '活动名称',
  `bAdvert` char(1) default NULL COMMENT '插入广告',
  `advertImgUrl` varchar(100) default NULL COMMENT '广告图',
  `advertUrl` varchar(500) default NULL COMMENT '广告链接地址',
  `shareImgUrl` varchar(100) default NULL COMMENT '分享图片',
  `shareTitle` varchar(100) default NULL COMMENT '分享标题',
  `shareDescription` varchar(250) default NULL COMMENT '分享简介',
  `awards` text COMMENT '奖项设置',
  `startTime` datetime default NULL COMMENT '开始时间',
  `endTime` datetime default NULL COMMENT '结束时间',
  `bUrlType` char(1) default NULL COMMENT '关注门槛',
  `followUrl` varchar(500) default NULL COMMENT '关注链接',
  `rule` text COMMENT '活动规则',
  `exExplain` varchar(150) default NULL COMMENT '兑换说明',
  `userinfo` varchar(100) default NULL COMMENT '用户信息',
  `createTime` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_zanrenqi_conf
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_zanrenqi_count`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_zanrenqi_count`;
CREATE TABLE `t_act_zanrenqi_count` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `mpOpenId` varchar(50) default NULL COMMENT '寰俊鍏紬骞冲彴openId',
  `activityId` int(11) default NULL COMMENT '娲诲姩id',
  `helpNum` int(11) default '0' COMMENT '甯姪浜烘暟',
  `finalTime` datetime default NULL COMMENT '鏇存柊鏃堕棿',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `idx_zanrenqi_count_actidAndopenid` (`mpOpenId`,`activityId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_zanrenqi_count
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_zanrenqi_help`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_zanrenqi_help`;
CREATE TABLE `t_act_zanrenqi_help` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `helpOpenId` varchar(50) default NULL COMMENT '帮助人的openId',
  `helpedOpenId` varchar(50) default NULL COMMENT '被帮助人的openId',
  `helpTime` datetime default NULL COMMENT '帮助时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_zanrenqi_help
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_zanrenqi_prize`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_zanrenqi_prize`;
CREATE TABLE `t_act_zanrenqi_prize` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `activityId` int(11) default NULL COMMENT '活动id',
  `prizeType` tinyint(4) default '0' COMMENT '奖品类型',
  `realNum` int(11) default '0' COMMENT '奖品实际数量',
  `deliverNum` int(11) default '0' COMMENT '奖品发出数量',
  `version` int(11) default '0' COMMENT '更新版本',
  `finalTime` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_zanrenqi_prize
-- ----------------------------

-- ----------------------------
-- Table structure for `t_act_zanrenqi_record`
-- ----------------------------
DROP TABLE IF EXISTS `t_act_zanrenqi_record`;
CREATE TABLE `t_act_zanrenqi_record` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `mpOpenId` varchar(50) default NULL COMMENT '微信公众平台openId',
  `activityId` int(11) default NULL COMMENT '活动id',
  `prizeType` tinyint(4) default NULL COMMENT '奖项',
  `prizeName` varchar(50) default NULL COMMENT '奖品名称',
  `mailAddress` varchar(100) default NULL COMMENT '邮寄地址',
  `qq` varchar(50) default NULL COMMENT 'qq',
  `wechatId` varchar(50) default NULL COMMENT '微信号',
  `otherInfo` varchar(50) default NULL COMMENT '其他信息',
  `tel` varchar(11) default NULL COMMENT '电话号码',
  `username` varchar(50) default NULL COMMENT '用户名',
  `prizeTime` datetime default NULL COMMENT '中奖时间',
  `exchangeTime` datetime default NULL COMMENT '兑换时间',
  `opState` char(1) default NULL COMMENT '操作状态',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_zanrenqi_record
-- ----------------------------

-- ----------------------------
-- Table structure for `t_agent_banner`
-- ----------------------------
DROP TABLE IF EXISTS `t_agent_banner`;
CREATE TABLE `t_agent_banner` (
  `id` int(11) NOT NULL COMMENT 'id',
  `agentId` int(11) default NULL COMMENT '代理商id',
  `seq` int(11) default NULL COMMENT '顺序',
  `sbannerId` int(11) default NULL COMMENT '平台设置的bannerId',
  `pcLogo` varchar(100) default NULL COMMENT 'pc版图片',
  `pcLink` varchar(100) default NULL COMMENT 'pc版链接',
  `wxLogo` varchar(100) default NULL COMMENT '微信版图片',
  `wxLink` varchar(150) default NULL COMMENT '微信版链接',
  `type` char(1) default NULL COMMENT '(0 系统同步，1代理商自己添加)',
  `createTime` datetime default NULL COMMENT '创建时间',
  `updateTime` datetime default NULL COMMENT '修改时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代理商banner管理';

-- ----------------------------
-- Records of t_agent_banner
-- ----------------------------

-- ----------------------------
-- Table structure for `t_agent_brand`
-- ----------------------------
DROP TABLE IF EXISTS `t_agent_brand`;
CREATE TABLE `t_agent_brand` (
  `id` int(11) NOT NULL,
  `userId` int(11) default NULL COMMENT '用户id(品牌用户id)',
  `seq` int(11) default NULL COMMENT '顺序',
  `type` char(1) default NULL COMMENT '0 系统设置，1代理商设置',
  `agentId` int(11) default NULL COMMENT '代理商id',
  `createTime` datetime default NULL COMMENT '创建时间',
  `updateTime` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代理商品牌管理';

-- ----------------------------
-- Records of t_agent_brand
-- ----------------------------

-- ----------------------------
-- Table structure for `t_agent_classic_act`
-- ----------------------------
DROP TABLE IF EXISTS `t_agent_classic_act`;
CREATE TABLE `t_agent_classic_act` (
  `id` int(11) NOT NULL COMMENT 'id',
  `agentId` int(11) default NULL COMMENT '代理商id',
  `actId` int(11) default NULL COMMENT '活动id',
  `type` char(1) default '0' COMMENT '0 系统设定，1代理商设定',
  `seq` int(11) default NULL COMMENT '顺序',
  `createTime` datetime default NULL COMMENT '创建时间',
  `updateTime` datetime default NULL COMMENT '修改时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代理商经典活动表';

-- ----------------------------
-- Records of t_agent_classic_act
-- ----------------------------

-- ----------------------------
-- Table structure for `t_agent_cont_audit`
-- ----------------------------
DROP TABLE IF EXISTS `t_agent_cont_audit`;
CREATE TABLE `t_agent_cont_audit` (
  `id` int(11) NOT NULL COMMENT 'id',
  `agentId` int(11) default NULL COMMENT '代理商id',
  `contId` int(11) default NULL COMMENT '提交审内容Id',
  `type` varchar(3) default NULL COMMENT '1 banner，2入驻品牌，3活动推荐，4经典案例，5插件推荐，6常见问题，7渠道管理',
  `applyTime` datetime default NULL COMMENT '提交时间',
  `auditState` char(1) default NULL COMMENT '0 未审核，1审核通过',
  `auditTime` datetime default NULL COMMENT '审核时间',
  `auditUserId` int(11) default NULL COMMENT '审核人',
  `status` char(1) default '0' COMMENT '0 未启用，1启用',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代理商提交内容审核';

-- ----------------------------
-- Records of t_agent_cont_audit
-- ----------------------------

-- ----------------------------
-- Table structure for `t_agent_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_agent_info`;
CREATE TABLE `t_agent_info` (
  `id` int(11) NOT NULL COMMENT '代理商用id',
  `companyName` varchar(100) default NULL COMMENT '公司名称',
  `province` varchar(20) default NULL COMMENT '省',
  `city` varchar(20) default NULL COMMENT '市',
  `district` varchar(20) default NULL COMMENT '区',
  `address` varchar(100) default NULL COMMENT '地址',
  `telephone` varchar(15) default NULL COMMENT '办公电话',
  `fax` varchar(15) default NULL COMMENT '传真号码',
  `mobile` varchar(15) default NULL COMMENT '手机号',
  `contract` varchar(30) default NULL COMMENT '联系人',
  `logo` varchar(100) default NULL COMMENT 'logoURL',
  `logoDesc` varchar(50) default NULL COMMENT 'logo描述',
  `wxqrcode` varchar(100) default NULL COMMENT '公众号二维码URL',
  `focusAdd` varchar(100) default NULL COMMENT '关注地址',
  `mydomain` varchar(50) default NULL COMMENT '指向域名',
  `redirecDomain` varchar(50) default NULL COMMENT '分享跳转域名',
  `version` varchar(250) default NULL COMMENT '版权信息',
  `filePath` varchar(150) default NULL COMMENT '其它资料文件Url',
  `pluginRecState` char(1) default '0' COMMENT '0 开启更新，1关闭更新',
  `actRecState` char(1) default '0' COMMENT '0 开启更新，1关闭',
  `faqState` char(1) default '0' COMMENT '0 开启更新，1关闭',
  `caseState` char(1) default '0' COMMENT '0 开启更新，1关闭',
  `bannerState` char(1) default '0' COMMENT '0 开启更新，1关闭',
  `brandState` char(1) default '0' COMMENT '0 开启更新，1关闭',
  `alipay` varchar(30) default NULL COMMENT '支付宝账号',
  `blance` double(12,2) default '0.00' COMMENT '当前账户余额',
  `totalIncome` double(12,2) default '0.00' COMMENT '累计收益',
  `totalCash` double(12,2) default '0.00' COMMENT '累计提现',
  `bankAccount` varchar(30) default NULL COMMENT '银行账号',
  `debtTime` datetime default NULL COMMENT '上次欠费时间',
  `bankType` char(2) default NULL COMMENT '银行类型',
  `isOfficial` char(1) default '0' COMMENT '0否,1是',
  `createTime` datetime default NULL COMMENT '创建时间',
  `updateTime` datetime default NULL COMMENT '更新时间',
  `websiteIcon` varchar(100) default NULL COMMENT '网站icon',
  `websiteDesc` varchar(200) default NULL COMMENT '网站描述',
  `websiteTitle` varchar(30) default NULL COMMENT '网站标题',
  `websiteKeyword` varchar(200) default NULL COMMENT '网站关键字',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代理商信息';

-- ----------------------------
-- Records of t_agent_info
-- ----------------------------
INSERT INTO `t_agent_info` VALUES ('13', '高级代理商1', '天津市', '和平区', null, '厦门市', '059245855', '', '13459226737', 'sfdasd', 'https://mp.weixin.qq.com/misc/getheadimg?token=169914860&fakeid=3083052661&r=756343', 'logo描述', 'https://mp.weixin.qq.com/misc/getqrcode?fakeid=3083052661&token=169914860&style=1', '', 'http://test.hudongtui.com', null, '', null, null, null, null, null, null, null, 'asdfa', '-24.00', '48.00', '0.00', '', '2015-03-31 17:37:35', '11', '0', null, '2015-03-31 17:37:35', null, '网站描述', '网站标题', '网站关键字');
INSERT INTO `t_agent_info` VALUES ('14', '普通代理1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0.00', '0.00', '0.00', null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `t_agent_info` VALUES ('16', '官方代理1', '山西省', '太原市', '市辖区', '', '', '', '', '', null, '', '', '', '', null, '', '', null, null, null, null, null, null, '', '0.00', '0.00', '0.00', '', null, '', '1', null, null, null, null, null, null);
INSERT INTO `t_agent_info` VALUES ('20', 'asdfasdfa', '河北省', '邯郸市', '武安市', 'asdfasdfsfg', 'ddfghd', 'dfghd', '13459226737', 'fghjfghj', 'group1/M00/00/0A/cxyp0lURKUmAH5HDAAyss3NZRFw725.png', 'fghjf', 'group1/M00/00/0A/cxyp0lURKimAeuJbAAAPB4Nl66E753.png', '', '', null, 'fghjf', 'hj', null, null, null, null, null, null, 'ghjfg', '624.00', '14680.00', '13000.00', '6227 0019 3547 0202 333', null, '6', '0', '2015-03-24 12:02:58', '2015-03-24 12:02:58', null, null, null, null);

-- ----------------------------
-- Table structure for `t_agent_mon_income`
-- ----------------------------
DROP TABLE IF EXISTS `t_agent_mon_income`;
CREATE TABLE `t_agent_mon_income` (
  `id` int(11) NOT NULL COMMENT 'id',
  `orderCount` int(11) default '0' COMMENT '订单总数',
  `dealCount` int(11) default '0' COMMENT '成交订单数',
  `dealAmount` double(10,2) default NULL COMMENT '成交金额',
  `income` double(10,2) default NULL COMMENT '收益',
  `month` varchar(15) default NULL COMMENT '计算月份',
  `agentId` int(11) default NULL COMMENT '代理商id',
  `createTime` datetime default NULL COMMENT '创建时间',
  `modifyTime` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收益月表';

-- ----------------------------
-- Records of t_agent_mon_income
-- ----------------------------

-- ----------------------------
-- Table structure for `t_agent_withdraw`
-- ----------------------------
DROP TABLE IF EXISTS `t_agent_withdraw`;
CREATE TABLE `t_agent_withdraw` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `cash` double(10,2) default NULL COMMENT '提现金额',
  `cashType` char(1) default NULL COMMENT '0 支付宝，1银行账号',
  `applyUserId` int(11) default NULL COMMENT '申请人',
  `applyTime` datetime default NULL COMMENT '申请时间',
  `auditUserId` int(11) default NULL COMMENT '审核人',
  `auditState` char(1) default NULL COMMENT '审核状态(0未处理，1处理)',
  `auditTime` datetime default NULL COMMENT '审核时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代理商提现表';

-- ----------------------------
-- Records of t_agent_withdraw
-- ----------------------------
INSERT INTO `t_agent_withdraw` VALUES ('39', '56.00', '1', '20', '2015-03-29 16:42:38', '1', '1', '2015-03-29 16:43:15');
INSERT INTO `t_agent_withdraw` VALUES ('40', '1000.00', '1', '20', '2015-03-29 16:43:31', '1', '1', '2015-03-29 16:43:50');
INSERT INTO `t_agent_withdraw` VALUES ('41', '56.00', '1', '20', '2015-03-29 16:46:45', null, '0', null);
INSERT INTO `t_agent_withdraw` VALUES ('42', '56.00', '1', '20', '2015-03-29 16:46:50', null, '0', null);
INSERT INTO `t_agent_withdraw` VALUES ('43', '34.00', '1', '20', '2015-03-29 16:48:53', null, '0', null);

-- ----------------------------
-- Table structure for `t_banner`
-- ----------------------------
DROP TABLE IF EXISTS `t_banner`;
CREATE TABLE `t_banner` (
  `id` int(11) NOT NULL COMMENT 'id',
  `pcLink` varchar(150) default NULL COMMENT 'pc版链接',
  `wxLink` varchar(150) default NULL COMMENT 'wx版链接',
  `agentId` int(11) default NULL COMMENT '代理商id',
  `pcLogo` varchar(150) default NULL COMMENT 'pc版本Logo',
  `wxLogo` varchar(150) default NULL COMMENT 'wx版Logo',
  `seq` int(11) default NULL COMMENT '顺序',
  `createTime` datetime default NULL COMMENT '创建时间',
  `updateTime` datetime default NULL COMMENT '修改时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='banner管理';

-- ----------------------------
-- Records of t_banner
-- ----------------------------

-- ----------------------------
-- Table structure for `t_brand`
-- ----------------------------
DROP TABLE IF EXISTS `t_brand`;
CREATE TABLE `t_brand` (
  `id` int(11) NOT NULL,
  `logo` varchar(100) default NULL COMMENT 'logo',
  `userId` int(11) default NULL COMMENT '用户id',
  `nickName` varchar(50) default NULL COMMENT '用户呢称',
  `seq` int(11) default NULL COMMENT '顺序',
  `createTime` datetime default NULL COMMENT '创建时间',
  `updateTime` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌管理';

-- ----------------------------
-- Records of t_brand
-- ----------------------------

-- ----------------------------
-- Table structure for `t_buser_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_buser_order`;
CREATE TABLE `t_buser_order` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `buserId` int(11) default NULL COMMENT '用户id，这里指后台用户即代理商用户id',
  `charge` double(10,2) default NULL COMMENT '支付金额',
  `purchaseType` char(3) default NULL COMMENT '支付类型(1 代币，2支付宝WEB，3支付宝WAP，4银联WEB，5银联WAP，6微信支付，7代码与第三支付方式)',
  `payOrderStatus` char(1) default '0' COMMENT '支付状态(0：未支付、1：已支付、2：支付失败)',
  `payTradeNo` varchar(50) default NULL COMMENT '支付交易号(第三方订单号)',
  `payNoticeTime` datetime default NULL COMMENT '支付通知时间',
  `orderTime` datetime default NULL COMMENT '下单时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户定单表';

-- ----------------------------
-- Records of t_buser_order
-- ----------------------------

-- ----------------------------
-- Table structure for `t_channel_copy`
-- ----------------------------
DROP TABLE IF EXISTS `t_channel_copy`;
CREATE TABLE `t_channel_copy` (
  `id` int(11) NOT NULL auto_increment,
  `userId` int(11) default NULL COMMENT '用户id(平台用户)',
  `type` char(3) default NULL COMMENT '渠道类型 1:微信订阅号，微信服务号，微信个人号；2:微博帐号，网站，APP；3:手机号',
  `name` varchar(50) default NULL COMMENT '渠道名称',
  `logo` varchar(100) default NULL COMMENT 'LOGO',
  `introduce` varchar(150) default NULL COMMENT '简介',
  `qrcode` varchar(100) default NULL COMMENT '二维码',
  `website` varchar(40) default NULL COMMENT '网址',
  `fansNum` char(2) default NULL COMMENT '粉丝数0:1W~5W;1:5W~10W;2:10W~50W;3:50W~100W;4:100W~500W;5:500W~1000W;6:1000W以上',
  `price` double(10,2) default NULL COMMENT '发布价位 元/条',
  `qq` varchar(14) default NULL COMMENT '联系人QQ号',
  `createTime` datetime default NULL COMMENT '创建时间',
  `updateTime` datetime default NULL COMMENT '更新时间',
  `auditState` char(1) default NULL COMMENT '审核状态 0 未审核，1通过,2:不通过',
  `auditorId` int(11) default NULL COMMENT '审核人id',
  `agentId` int(11) default NULL COMMENT '代理商id',
  `seq` int(11) default NULL COMMENT '顺序',
  PRIMARY KEY  (`id`),
  KEY `idx_t_channel_userId` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='渠道表';

-- ----------------------------
-- Records of t_channel_copy
-- ----------------------------
INSERT INTO `t_channel_copy` VALUES ('1', '1', '1', '1', 'group1/M00/00/09/cxyp0lUPz6GAR11QAAAv0nSmZfQ152.png', '1', '../../images/code1.jpg', 'www.baidu.com', '1', '1.00', '1', '2015-03-23 20:25:22', null, '1', null, '13', null);
INSERT INTO `t_channel_copy` VALUES ('2', '2', '1', '2', 'group1/M00/00/09/cxyp0lUPz6GAR11QAAAv0nSmZfQ152.png', '2', '../../images/code1.jpg', 'www.baidu.com', '2', '2.00', '2', null, null, '1', null, '13', null);
INSERT INTO `t_channel_copy` VALUES ('3', '3', '1', '3', 'group1/M00/00/09/cxyp0lUPz6GAR11QAAAv0nSmZfQ152.png', '3', '../../images/code1.jpg', 'www.baidu.com', '3', '3.00', '3', null, null, '1', null, '13', null);
INSERT INTO `t_channel_copy` VALUES ('4', '4', '1', '4', 'group1/M00/00/09/cxyp0lUPz6GAR11QAAAv0nSmZfQ152.png', '4', '../../images/code1.jpg', 'www.baidu.com', '4', '4.00', '4', null, null, '1', null, '13', null);
INSERT INTO `t_channel_copy` VALUES ('5', '5', '0', '5', 'group1/M00/00/09/cxyp0lUPz6GAR11QAAAv0nSmZfQ152.png', '5', '../../images/code1.jpg', 'www.baidu.com', '5', '5.00', '5', null, '2015-04-01 21:23:59', '1', '1', '13', null);
INSERT INTO `t_channel_copy` VALUES ('6', '6', '0', '66', 'group1/M00/00/09/cxyp0lUPz6GAR11QAAAv0nSmZfQ152.png', '6', '../../images/code1.jpg', 'www.baidu.com', '6', '6.00', '6', null, '2015-04-01 21:23:55', '1', '1', '13', null);
INSERT INTO `t_channel_copy` VALUES ('11', '9', '1', '111111', 'group1/M00/00/09/cxyp0lUPz6GAR11QAAAv0nSmZfQ152.png', '1111', 'group1/M00/00/0B/cxyp0lUcow2ACiIhAAAsQtHfkAE506.jpg', null, '7', '111.00', '11111', '2015-04-02 10:01:48', null, '1', null, '13', null);
INSERT INTO `t_channel_copy` VALUES ('12', '9', '1', '3333', 'group1/M00/00/09/cxyp0lUPz6GAR11QAAAv0nSmZfQ152.png', '333', null, '11', '11', '11.00', '11', null, null, '1', '1', '13', null);
INSERT INTO `t_channel_copy` VALUES ('13', '9', '1', '222', 'group1/M00/00/09/cxyp0lUPz6GAR11QAAAv0nSmZfQ152.png', '22', null, '11', '11', '11.00', '11', null, null, '1', '1', '13', null);

-- ----------------------------
-- Table structure for `t_channel_rec`
-- ----------------------------
DROP TABLE IF EXISTS `t_channel_rec`;
CREATE TABLE `t_channel_rec` (
  `id` int(11) NOT NULL auto_increment COMMENT '推荐ID',
  `channelId` int(11) default NULL COMMENT '渠道id',
  `seq` int(11) default NULL COMMENT '排列顺序',
  `createTime` datetime default NULL COMMENT '创建时间',
  `updateTime` datetime default NULL COMMENT '修改时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='渠道推荐表';

-- ----------------------------
-- Records of t_channel_rec
-- ----------------------------
INSERT INTO `t_channel_rec` VALUES ('1', '1', '1', '2015-03-23 20:25:46', null);

-- ----------------------------
-- Table structure for `t_contactus`
-- ----------------------------
DROP TABLE IF EXISTS `t_contactus`;
CREATE TABLE `t_contactus` (
  `id` int(11) NOT NULL COMMENT 'id',
  `servicePhone` varchar(14) default NULL COMMENT '客服电话',
  `qqGroup` varchar(12) default NULL COMMENT '粉丝QQ群',
  `serviceqq` varchar(12) default NULL COMMENT '客服QQ',
  `email` varchar(20) default NULL COMMENT '邮箱',
  `agentId` int(11) default NULL COMMENT '代理商id',
  `applyTime` datetime default NULL COMMENT '提交时间',
  `auditState` char(1) default NULL COMMENT '0 未审核，1审核通过',
  `auditTime` datetime default NULL COMMENT '审核时间',
  `auditUserId` int(11) default NULL COMMENT '审核人',
  `status` char(1) default NULL COMMENT '0 未启用，1启用',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='联系我们';

-- ----------------------------
-- Records of t_contactus
-- ----------------------------
INSERT INTO `t_contactus` VALUES ('1', '059241234', '456221', '456655548', '542@qq.com', '13', '2015-03-21 15:28:29', '1', '2015-03-21 15:28:32', null, '1');

-- ----------------------------
-- Table structure for `t_faq`
-- ----------------------------
DROP TABLE IF EXISTS `t_faq`;
CREATE TABLE `t_faq` (
  `id` int(11) NOT NULL COMMENT 'id',
  `question` varchar(150) default NULL COMMENT '问题',
  `answer` varchar(250) default NULL COMMENT '答案',
  `seq` int(11) default NULL COMMENT '顺序',
  `createTime` datetime default NULL COMMENT '创建时间',
  `updateTime` datetime default NULL COMMENT '修改时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='常见问题';

-- ----------------------------
-- Records of t_faq
-- ----------------------------

-- ----------------------------
-- Table structure for `t_plugin`
-- ----------------------------
DROP TABLE IF EXISTS `t_plugin`;
CREATE TABLE `t_plugin` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `name` varchar(100) default NULL COMMENT '插件名称',
  `userId` int(11) default NULL COMMENT '开发者用户id(为平台用户id)',
  `icon` varchar(100) default NULL COMMENT '插件图标',
  `description` varchar(250) default NULL COMMENT '插件描述',
  `showUrl` varchar(150) default NULL COMMENT '演示地址',
  `version` varchar(20) default NULL COMMENT '版本信息',
  `copyright` varchar(50) default NULL COMMENT '版权信息',
  `serverUrl` varchar(100) default NULL COMMENT '服务器路径未带参数',
  `buyNum` int(11) default '0' COMMENT '已购买人数',
  `tryoutNum` int(11) default '0' COMMENT '试用总人数',
  `price` double(10,2) default '0.00' COMMENT '价格(成本价)',
  `promPrice` double(10,2) default '0.00' COMMENT '优惠价格',
  `type` char(2) default '1' COMMENT '插件类型(1 即插即用型，2用户定制)',
  `guide` text COMMENT '使用说明',
  `valid` int(4) default '1' COMMENT '有效期(以月为单位)',
  `filePath` varchar(200) default NULL COMMENT '源代码文件地址',
  `videoUrl` varchar(200) default NULL COMMENT '视频录像地址',
  `actAddUrl` varchar(100) default NULL COMMENT '新增活动地址',
  `actEditUrl` varchar(100) default NULL COMMENT '编辑活动地址',
  `actAccessUrl` varchar(100) default NULL COMMENT '活动访问地址',
  `showActId` int(11) default NULL COMMENT '演示活动id',
  `status` char(1) default '0' COMMENT '状态值(0 下线，1上线)',
  `publisherId` int(11) default NULL COMMENT '发布人userId(后台用户Id)',
  `publishTime` datetime default NULL COMMENT '发布时间',
  `auditUserId` int(11) default NULL COMMENT '审核人用户id',
  `auditState` char(2) default '0' COMMENT '(0 待审核，1审核通过，2审核不通过)',
  `auditDesc` varchar(250) default NULL COMMENT '审核意见',
  `auditTime` datetime default NULL COMMENT '审核时间',
  `uploadTime` datetime default NULL COMMENT '上传时间',
  `updateTime` datetime default NULL COMMENT '修改时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='插件表';

-- ----------------------------
-- Records of t_plugin
-- ----------------------------
INSERT INTO `t_plugin` VALUES ('48', '摇一摇，中大奖', '8', 'group1/M00/00/09/cxyp0lUPzUKAdEt_AAAeiFsRaPA691.png', '圣诞抽奖，奖品挂在圣诞树上，手机摇一摇，奖品就会按中奖概率掉下来。', null, null, null, null, '9', '0', '10.00', null, '1', '四地分公司', '1', '', 'http://player.youku.com/player.php/sid/XODk5MTIyNjE2/v.swf', 'pluginadmin/tree/add', 'pluginadmin/tree/edit', 'cj/tree/index', null, '1', '1', '2015-03-25 09:56:41', null, null, '0', null, '2015-03-23 14:45:58', '2015-03-31 17:37:35');
INSERT INTO `t_plugin` VALUES ('49', '阿斯顿发生地方', '8', 'group1/M00/00/09/cxyp0lUPz6GAR11QAAAv0nSmZfQ152.png', '阿斯顿发送到发生地方', null, null, null, null, '0', '0', '45.00', null, '1', '', '1', 'group1/M00/00/09/cxyp0lUPz6eATr1OAAALX-Ky9aQ734.rar', 'http://www.baidu.com', null, null, null, null, '1', '1', '2015-03-25 09:56:52', null, null, '0', null, '2015-03-23 15:01:03', null);
INSERT INTO `t_plugin` VALUES ('50', 'sdfgsdf', '8', 'group1/M00/00/09/cxyp0lUP-CmAD1b_AAAeiFsRaPA791.png', 'fghjfghjfghjfghjfghjf', null, null, null, null, '0', '0', '65.00', null, '1', 'fgh', '1', 'group1/M00/00/09/cxyp0lUP-CGAcrb3AAALX-Ky9aQ585.rar', 'http://ww.baidu.com', null, null, null, null, '1', '1', '2015-03-25 09:56:56', null, null, '0', null, '2015-03-23 19:25:52', null);
INSERT INTO `t_plugin` VALUES ('72', '张三', '8', 'group1/M00/00/0A/cxyp0lUSFAOAE-2lAAAPB4Nl66E909.png', '神烦大叔短发1阿斯蒂芬', null, null, null, null, '0', '0', '45.00', null, '1', '缺乏水电费', '1', '', 'http://www.baidu.com', null, null, null, null, '1', '1', '2015-03-25 09:55:53', null, null, '0', null, '2015-03-25 09:49:22', null);
INSERT INTO `t_plugin` VALUES ('73', 'asdfad', '8', 'group1/M00/00/0A/cxyp0lUSFC-AXVitAAAzJ90v7jA746.png', 'asdfasdfsdfgsdfg', null, null, null, null, '0', '0', '23.00', null, '1', '21321', '1', '', 'http://www.baidu.com', null, null, null, null, '1', '1', '2015-03-25 09:55:58', null, null, '0', null, '2015-03-25 09:49:50', null);
INSERT INTO `t_plugin` VALUES ('74', 'xvbxbsdfg', '8', 'group1/M00/00/0A/cxyp0lUSFE-AI4HHAAAxRI9CgCc638.png', 'asdfasdfasdfasd', null, null, null, null, '0', '0', '23.00', null, '1', 'asdfasdf', '1', '', 'http://www.baidu.com', null, null, null, null, '1', '1', '2015-03-25 09:57:02', null, null, '0', null, '2015-03-25 09:50:24', null);
INSERT INTO `t_plugin` VALUES ('75', '法规和监管框架', '8', 'group1/M00/00/0A/cxyp0lUSFTmAXmO7AAAzJ90v7jA111.png', '更换健康歌回家看法国红酒', null, null, null, null, '0', '0', '32.00', null, '1', '发货甲方', '1', '', 'http://www.baidu.com', null, null, null, null, '1', '1', '2015-03-25 09:57:06', null, null, '0', null, '2015-03-25 09:54:14', null);
INSERT INTO `t_plugin` VALUES ('76', '风格化就', '8', 'group1/M00/00/0A/cxyp0lUSFVWAWkSvAAAeiFsRaPA349.png', '法国红酒发给回家更换尽快更换', null, null, null, null, '0', '0', '87.00', null, '1', '风格化', '1', '', 'http://www.baidu.com', null, null, null, null, '1', '1', '2015-03-25 09:57:13', null, null, '0', null, '2015-03-25 09:54:48', null);
INSERT INTO `t_plugin` VALUES ('77', 'dfg折叠缝合', '8', 'group1/M00/00/0A/cxyp0lUSFXSAXrWZAAAPB4Nl66E211.png', '地方更好的发给东方红', null, null, null, null, '0', '0', '34.00', null, '1', '的', '1', '', 'http://www.baidu.com', null, null, null, null, '1', '1', '2015-03-25 09:57:18', null, null, '0', null, '2015-03-25 09:55:14', null);
INSERT INTO `t_plugin` VALUES ('78', 'ad下次v下次', '8', 'group1/M00/00/0A/cxyp0lUSFZGAVjmiAAAv0nSmZfQ715.png', '阿斯顿发生地方水电费', null, null, null, null, '0', '0', '98.00', null, '1', '是否刚收到发给', '1', '', 'http://www.baidu.com', null, null, null, null, '1', '1', '2015-03-25 09:57:44', null, null, '0', null, '2015-03-25 09:55:44', null);
INSERT INTO `t_plugin` VALUES ('79', '撒大法师大法书', '8', 'group1/M00/00/0A/cxyp0lUSWGOAEjGzAAAzJ90v7jA434.png', '阿斯顿发生发生大幅阿斯蒂芬', null, null, null, null, '0', '0', '23.00', null, '1', '阿斯蒂芬', '0', '', 'http://www.baidu.com', null, null, null, null, '0', null, null, null, null, null, null, '2015-03-25 14:41:45', null);

-- ----------------------------
-- Table structure for `t_plugin_agent`
-- ----------------------------
DROP TABLE IF EXISTS `t_plugin_agent`;
CREATE TABLE `t_plugin_agent` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `pluginId` int(11) default NULL COMMENT '插件id',
  `agentId` int(11) default NULL COMMENT '代理商id',
  `salePrice` double(10,2) default '0.00' COMMENT '销售价格',
  `onlineState` char(1) default '0' COMMENT '0 上架，1下架',
  `createTime` datetime default NULL COMMENT '创建时间',
  `updateTime` datetime default NULL COMMENT '修改时间',
  `delState` char(1) default '0' COMMENT '0 未删除，1已删除',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代理商插件表';

-- ----------------------------
-- Records of t_plugin_agent
-- ----------------------------
INSERT INTO `t_plugin_agent` VALUES ('106', '72', '13', '190.00', '0', '2015-03-25 09:55:54', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('107', '72', '14', '100.00', '0', '2015-03-25 09:55:54', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('108', '72', '16', '100.00', '0', '2015-03-25 09:55:54', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('109', '72', '20', '100.00', '0', '2015-03-25 09:55:54', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('110', '73', '13', '180.00', '0', '2015-03-25 09:55:58', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('111', '73', '14', '100.00', '0', '2015-03-25 09:55:58', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('112', '73', '16', '100.00', '0', '2015-03-25 09:55:58', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('113', '73', '20', '100.00', '0', '2015-03-25 09:55:59', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('114', '48', '13', '20.00', '0', '2015-03-25 09:56:41', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('115', '48', '14', '100.00', '0', '2015-03-25 09:56:41', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('116', '48', '16', '100.00', '0', '2015-03-25 09:56:42', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('117', '48', '20', '100.00', '0', '2015-03-25 09:56:42', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('118', '49', '13', '160.00', '0', '2015-03-25 09:56:53', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('119', '49', '14', '100.00', '0', '2015-03-25 09:56:53', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('120', '49', '16', '100.00', '0', '2015-03-25 09:56:53', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('121', '49', '20', '100.00', '0', '2015-03-25 09:56:53', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('122', '50', '13', '150.00', '0', '2015-03-25 09:56:57', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('123', '50', '14', '100.00', '0', '2015-03-25 09:56:57', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('124', '50', '16', '100.00', '0', '2015-03-25 09:56:57', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('125', '50', '20', '100.00', '0', '2015-03-25 09:56:57', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('126', '74', '13', '140.00', '0', '2015-03-25 09:57:02', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('127', '74', '14', '100.00', '0', '2015-03-25 09:57:02', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('128', '74', '16', '100.00', '0', '2015-03-25 09:57:03', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('129', '74', '20', '100.00', '0', '2015-03-25 09:57:03', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('130', '75', '13', '130.00', '0', '2015-03-25 09:57:07', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('131', '75', '14', '100.00', '0', '2015-03-25 09:57:07', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('132', '75', '16', '100.00', '0', '2015-03-25 09:57:07', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('133', '75', '20', '100.00', '0', '2015-03-25 09:57:08', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('134', '76', '13', '120.00', '0', '2015-03-25 09:57:13', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('135', '76', '14', '100.00', '0', '2015-03-25 09:57:13', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('136', '76', '16', '100.00', '0', '2015-03-25 09:57:14', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('137', '76', '20', '100.00', '0', '2015-03-25 09:57:14', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('138', '77', '13', '110.00', '0', '2015-03-25 09:57:18', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('139', '77', '14', '100.00', '0', '2015-03-25 09:57:19', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('140', '77', '16', '100.00', '0', '2015-03-25 09:57:19', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('141', '77', '20', '100.00', '0', '2015-03-25 09:57:19', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('142', '78', '13', '100.00', '0', '2015-03-25 09:57:45', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('143', '78', '14', '100.00', '0', '2015-03-25 09:57:45', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('144', '78', '16', '100.00', '0', '2015-03-25 09:57:45', null, '0');
INSERT INTO `t_plugin_agent` VALUES ('145', '78', '20', '100.00', '0', '2015-03-25 09:57:45', null, '0');

-- ----------------------------
-- Table structure for `t_plugin_prev`
-- ----------------------------
DROP TABLE IF EXISTS `t_plugin_prev`;
CREATE TABLE `t_plugin_prev` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `name` varchar(50) default NULL COMMENT '模板名称',
  `description` varchar(200) default NULL COMMENT '简介',
  `shelTime` date default NULL COMMENT '预计上架时间',
  `seq` int(11) default NULL COMMENT '顺序',
  `createTime` datetime default NULL COMMENT '创建时间',
  `updateTime` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模板预告表';

-- ----------------------------
-- Records of t_plugin_prev
-- ----------------------------
INSERT INTO `t_plugin_prev` VALUES ('1', '11', '111', '2015-03-23', null, '2015-03-23 20:21:08', '2015-03-23 20:21:11');

-- ----------------------------
-- Table structure for `t_plugin_rec`
-- ----------------------------
DROP TABLE IF EXISTS `t_plugin_rec`;
CREATE TABLE `t_plugin_rec` (
  `id` int(11) NOT NULL COMMENT '推荐ID',
  `pluginId` int(11) default NULL COMMENT '插件id',
  `seq` int(11) default NULL COMMENT '排列顺序',
  `createTime` datetime default NULL COMMENT '创建时间',
  `updateTime` datetime default NULL COMMENT '修改时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='插件推荐';

-- ----------------------------
-- Records of t_plugin_rec
-- ----------------------------
INSERT INTO `t_plugin_rec` VALUES ('2', '48', '2', null, null);

-- ----------------------------
-- Table structure for `t_sysconfig`
-- ----------------------------
DROP TABLE IF EXISTS `t_sysconfig`;
CREATE TABLE `t_sysconfig` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `code` varchar(200) default NULL COMMENT '代号',
  `value` varchar(200) default NULL COMMENT '值',
  `remark` varchar(250) default NULL COMMENT '描述说明',
  `createTime` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sysconfig
-- ----------------------------
INSERT INTO `t_sysconfig` VALUES ('1', 'accesstoken_wx3222d64731e90fa1', 'DRmbRFe16HGi5LwB43n4IfS9f--DL7vT4DQOja6OfKyYQKqrIA9iLDNN6hGR5RDSPPNJGmW7OFnCtI31UJCtOp6CkyijxqGNxyj3zzWkwd0', '公众号token', '2015-03-10 16:36:40');
INSERT INTO `t_sysconfig` VALUES ('2', 'accessToken_gettime_wx3222d64731e90fa1', '1425976701923', '最近一次获取公众号token时间', '2015-03-10 16:36:40');

-- ----------------------------
-- Table structure for `t_s_code_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_s_code_type`;
CREATE TABLE `t_s_code_type` (
  `id` char(4) NOT NULL,
  `typeName` varchar(100) NOT NULL,
  `codeName1Label` varchar(50) default NULL,
  `codeName2Label` varchar(50) default NULL,
  `codeName3Label` varchar(50) default NULL,
  `codeName4Label` varchar(50) default NULL,
  `allowAdd` bit(1) NOT NULL,
  `allowModify` bit(1) NOT NULL,
  `allowDelete` bit(1) NOT NULL,
  `typeMemo` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_s_code_type
-- ----------------------------
INSERT INTO `t_s_code_type` VALUES ('突然风格', '双方各', '', '', '', '', '', '', '', '');

-- ----------------------------
-- Table structure for `t_s_operation`
-- ----------------------------
DROP TABLE IF EXISTS `t_s_operation`;
CREATE TABLE `t_s_operation` (
  `id` varchar(32) NOT NULL,
  `iconid` varchar(32) default NULL,
  `functionid` varchar(32) default NULL,
  `operationName` varchar(50) default NULL,
  `operationCode` varchar(50) default NULL,
  `operationIcon` varchar(100) default NULL,
  `status` smallint(6) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作码表';

-- ----------------------------
-- Records of t_s_operation
-- ----------------------------
INSERT INTO `t_s_operation` VALUES ('402880e74bf8ad26014bf8b3af340000', '13', '28', '删除代理商', 'AGENTINFO_DEL', null, '1');
INSERT INTO `t_s_operation` VALUES ('402880e74bf92e92014bf935fd560000', '17', '28', '添加代理商', 'AGENTINFO_ADD', null, '1');
INSERT INTO `t_s_operation` VALUES ('402880e94bfc8bf2014bfc98910d0000', '6', '28', '编辑', 'AGENTINFO_EDIT', null, '1');
INSERT INTO `t_s_operation` VALUES ('402880f44c2bdc5a014c2be1be940012', '3', '31', '添加插件', 'PLUGIN_ADD', null, '1');
INSERT INTO `t_s_operation` VALUES ('402880f44c46292a014c463176760000', '3', '38', '修改', 'AGENT_UPDATE', null, '1');
INSERT INTO `t_s_operation` VALUES ('402880f44c46292a014c4632affe0001', '3', '38', '上下架', 'AGENT_ONLINE_OFF', null, '2');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `userId` int(11) NOT NULL auto_increment COMMENT 'userId',
  `account` varchar(150) default NULL COMMENT 'account',
  `email` varchar(150) default NULL COMMENT 'email',
  `mobile` varchar(15) default NULL COMMENT '手机号',
  `mobileOpen` char(1) default '0' COMMENT '手机号是否公众(0不公开,1公开)',
  `qqAccount` varchar(15) default NULL COMMENT 'qq号码',
  `qqOpen` char(1) default '0' COMMENT 'QQ号是否公众(0不公开,1公开)',
  `weixinAccount` varchar(20) default NULL COMMENT '微信账号',
  `weixinOpen` char(1) default '0' COMMENT '微信号是否公众(0不公开,1公开)',
  `nickName` varchar(100) default NULL COMMENT '呢称',
  `headimgUrl` varchar(200) default NULL COMMENT '头像url',
  `role` char(2) default NULL COMMENT '1普通商家用户，2开发者',
  `sex` char(1) default NULL COMMENT '性别(0,男性 1 女性)',
  `realName` varchar(150) default NULL COMMENT '真实姓名',
  `address` varchar(150) default NULL COMMENT '联系地址',
  `personIdCard` varchar(20) default NULL COMMENT '身份证号',
  `source` char(2) default NULL COMMENT '来源(1,用户自行注册，2 QQ，3微信 4新浪微博 5腾讯微博)',
  `repreCoin` double(10,2) default '0.00' COMMENT '代币',
  `friendNum` int(11) default '0' COMMENT '朋友数',
  `qcode` varchar(100) default NULL COMMENT '二维码地址',
  `introduce` varchar(500) default NULL COMMENT '简介描述',
  `pluginNum` int(11) default '0' COMMENT '已拥有插件数',
  `province` varchar(20) default NULL COMMENT '省',
  `city` varchar(30) default NULL COMMENT '市',
  `district` varchar(30) default NULL COMMENT '区',
  `createTime` datetime default NULL COMMENT '创建时间',
  `updateTime` datetime default NULL COMMENT '修改时间',
  `password` varchar(150) default NULL COMMENT '密码',
  `state` char(1) default '0' COMMENT '0 正常使用，1禁用',
  `agentId` int(11) default NULL COMMENT '所属代理商id',
  `dealOrederNum` int(11) default '0' COMMENT '成交订单数',
  `dealAmount` double(10,2) default '0.00' COMMENT '成交金额',
  PRIMARY KEY  (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('3', 'flyking771@163.com', 'asdfa', '13459226737', '0', '', '0', null, '0', '有戏', 'http://wx.qlogo.cn/mmopen/Q3auHgzwzM7BDoiaJAhYq2NMaaiak6zI9vhIURNcsiaAh1qibek8svg4EPSxgWbDSS3VsOeg3R6Kt0AicOYweiaFRJAiciatqiaKCksxAv6ic1keBRtuc/0', '1', null, null, null, null, null, '25.00', '0', null, null, '10', null, null, null, '2015-03-17 16:53:37', '2015-03-31 17:37:35', 'fa6fa1e18b7b520faf22900263643a68d095663af7f1adc4', '0', '13', '0', '0.00');
INSERT INTO `t_user` VALUES ('8', 'erere@qq.com', null, null, '0', null, '0', null, '0', '22222', null, '2', null, null, null, null, null, '0.00', '0', null, null, '0', null, null, null, '2015-03-19 15:22:22', '2015-03-19 15:22:22', '822d9ea4de9001182c394d4ca3d8debe', '0', '16', '0', '0.00');
INSERT INTO `t_user` VALUES ('9', '395239537@qq.com', null, null, '0', null, '0', null, '0', '111122', null, '1', null, null, null, null, null, '0.00', '0', null, null, '0', null, null, null, '2015-03-20 12:23:13', '2015-03-22 15:29:18', 'a1ef3c00b79e539788b768662722bc499be56712297c1958', '0', '16', '0', '0.00');
INSERT INTO `t_user` VALUES ('10', '43243@qq.com', null, null, '0', null, '0', null, '0', '5555555', null, '2', null, null, null, null, null, '0.00', '0', null, null, '0', null, null, null, '2015-03-22 18:27:42', '2015-03-22 18:27:42', '558ed9ef232c30a6134cc724fe272a06', '0', '16', '0', '0.00');
INSERT INTO `t_user` VALUES ('11', 'flyking772@163.com', null, null, '0', null, '0', null, '0', '35互联', 'group1/M00/00/0B/cxyp0lUZDIeAd5XSAAFE8nWwwdk023.jpg', '1', null, null, null, null, null, '0.00', '0', null, null, '0', null, null, null, '2015-03-30 16:42:46', '2015-03-30 16:42:46', 'fa6fa1e18b7b520fea8aeed2b5f251a89369190d36f3d9eb', '0', '13', '0', '0.00');
