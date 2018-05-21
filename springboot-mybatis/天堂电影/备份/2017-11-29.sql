/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : yiyiye

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-11-29 10:12:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `movie`
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `name` varchar(50) NOT NULL COMMENT '电影名称',
  `asName` varchar(255) DEFAULT NULL COMMENT '又名',
  `coverImg` varchar(1000) DEFAULT NULL COMMENT '封面图片',
  `isHot` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否为热门(0否1是)',
  `brief` varchar(255) DEFAULT NULL COMMENT '简要介绍',
  `actors` varchar(255) DEFAULT NULL COMMENT '演员',
  `typeId` int(11) DEFAULT NULL COMMENT '电影类型ID',
  `languageId` int(11) DEFAULT NULL COMMENT '电影语言ID',
  `areaId` int(11) DEFAULT NULL COMMENT '电影地区ID',
  `year` int(11) DEFAULT NULL COMMENT '上映年份',
  `director` varchar(50) DEFAULT NULL COMMENT '导演',
  `releaseDate` varchar(50) DEFAULT NULL COMMENT '上映日期',
  `updatedDate` varchar(50) DEFAULT NULL COMMENT '更新日期',
  `timeSize` int(11) DEFAULT NULL COMMENT '电影时长',
  `watercressScore` double(11,1) DEFAULT NULL COMMENT '豆瓣评分',
  `synopsis` varchar(1000) DEFAULT NULL COMMENT '剧情介绍',
  `specialLabel` varchar(20) DEFAULT NULL COMMENT '特殊标签',
  `videoCapture` varchar(1000) DEFAULT NULL COMMENT '视频截图',
  `createOn` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of movie
-- ----------------------------

-- ----------------------------
-- Table structure for `movie_area`
-- ----------------------------
DROP TABLE IF EXISTS `movie_area`;
CREATE TABLE `movie_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `areaName` varchar(10) NOT NULL COMMENT '地区名称',
  `sort` int(11) NOT NULL COMMENT '排序',
  `createOn` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of movie_area
-- ----------------------------

-- ----------------------------
-- Table structure for `movie_language`
-- ----------------------------
DROP TABLE IF EXISTS `movie_language`;
CREATE TABLE `movie_language` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `languageName` varchar(10) NOT NULL COMMENT '语言名称',
  `sort` int(10) NOT NULL COMMENT '排序',
  `createOn` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of movie_language
-- ----------------------------

-- ----------------------------
-- Table structure for `movie_resources`
-- ----------------------------
DROP TABLE IF EXISTS `movie_resources`;
CREATE TABLE `movie_resources` (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `movieId` bigint(20) NOT NULL COMMENT '电影ID',
  `clarity` varchar(20) DEFAULT NULL COMMENT '清晰度',
  `size` double(11,1) DEFAULT NULL COMMENT '视频大小',
  `downlodMode` bit(1) DEFAULT NULL COMMENT '下载方式(1迅雷2百度云)',
  `downlodLink` varchar(10000) DEFAULT NULL COMMENT '资源种子或资源链接',
  `createOn` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of movie_resources
-- ----------------------------

-- ----------------------------
-- Table structure for `movie_type`
-- ----------------------------
DROP TABLE IF EXISTS `movie_type`;
CREATE TABLE `movie_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `typeName` varchar(10) NOT NULL COMMENT '类型名称',
  `sort` int(11) NOT NULL COMMENT '排序',
  `createOn` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of movie_type
-- ----------------------------

-- ----------------------------
-- Table structure for `tv`
-- ----------------------------
DROP TABLE IF EXISTS `tv`;
CREATE TABLE `tv` (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `name` varchar(50) NOT NULL COMMENT '电视剧名称',
  `asName` varchar(255) DEFAULT NULL COMMENT '又名',
  `coverImg` varchar(1000) DEFAULT NULL COMMENT '封面图片',
  `isHot` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否为热门(0否1是)',
  `recent` int(11) NOT NULL COMMENT '最近更新至多少集',
  `total` int(11) NOT NULL COMMENT '总共多少集',
  `updateCycle` varchar(255) NOT NULL COMMENT '更新周期',
  `actors` varchar(255) DEFAULT NULL COMMENT '演员',
  `typeId` int(11) DEFAULT NULL COMMENT '分类ID',
  `areaId` int(11) DEFAULT NULL COMMENT '地区ID',
  `director` varchar(255) DEFAULT NULL COMMENT '导演',
  `year` int(11) NOT NULL COMMENT '上映年份',
  `releaseDate` varchar(50) NOT NULL COMMENT '上映日期',
  `updatedDate` varchar(50) DEFAULT NULL COMMENT '更新日期',
  `timeSize` int(5) DEFAULT NULL COMMENT '时长',
  `watercressScore` double(5,1) DEFAULT NULL COMMENT '豆瓣评分',
  `synopsis` varchar(1000) DEFAULT NULL COMMENT '剧情介绍',
  `videoCapture` varchar(1000) DEFAULT NULL COMMENT '视频截图',
  `createOn` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tv
-- ----------------------------
INSERT INTO `tv` VALUES ('1', 'a', 'a', null, '', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1.0', '1', '1', '1');

-- ----------------------------
-- Table structure for `tv_area`
-- ----------------------------
DROP TABLE IF EXISTS `tv_area`;
CREATE TABLE `tv_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `areaName` varchar(50) NOT NULL COMMENT '地区名称',
  `sort` int(5) NOT NULL COMMENT '排序',
  `createOn` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tv_area
-- ----------------------------

-- ----------------------------
-- Table structure for `tv_resources`
-- ----------------------------
DROP TABLE IF EXISTS `tv_resources`;
CREATE TABLE `tv_resources` (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `tvId` bigint(20) NOT NULL COMMENT '电视剧ID',
  `episodes` int(11) NOT NULL COMMENT '集数',
  `clarity` varchar(50) DEFAULT NULL COMMENT '清晰度',
  `size` double(11,1) DEFAULT NULL COMMENT '视频大小',
  `downlodMode` bit(1) DEFAULT NULL COMMENT '下载方式(1迅雷2百度云)',
  `downlodLink` varchar(10000) DEFAULT NULL COMMENT '资源种子或资源链接',
  `createOn` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tv_resources
-- ----------------------------

-- ----------------------------
-- Table structure for `tv_type`
-- ----------------------------
DROP TABLE IF EXISTS `tv_type`;
CREATE TABLE `tv_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `typeName` varchar(20) NOT NULL COMMENT '分类名称',
  `sort` int(5) NOT NULL COMMENT '排序',
  `createOn` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tv_type
-- ----------------------------
INSERT INTO `tv_type` VALUES ('1000', '历史', '1', '10236256465');
INSERT INTO `tv_type` VALUES ('1001', '战争', '2', '10236256465');
INSERT INTO `tv_type` VALUES ('1002', '家庭', '3', '10236256465');
INSERT INTO `tv_type` VALUES ('1003', '宫斗', '4', '1511877590340');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL COMMENT '编号',
  `phone` int(11) NOT NULL COMMENT '手机号',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `createOn` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '123456', 'e10adc3949ba59abbe56e057f20f883e', '1');

-- ----------------------------
-- Table structure for `variety`
-- ----------------------------
DROP TABLE IF EXISTS `variety`;
CREATE TABLE `variety` (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `name` bigint(20) NOT NULL COMMENT '综艺名称',
  `asName` varchar(255) DEFAULT NULL COMMENT '又名',
  `coverImg` varchar(1000) DEFAULT NULL COMMENT '封面图片',
  `isHot` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否为热门(0否1是)',
  `recent` varchar(255) DEFAULT NULL COMMENT '最近更新一期的名称',
  `updateCycle` varchar(255) NOT NULL COMMENT '更新周期',
  `actors` varchar(255) DEFAULT NULL COMMENT '演员',
  `typeId` int(11) DEFAULT NULL COMMENT '类型ID',
  `areaId` int(11) DEFAULT NULL COMMENT '地区ID',
  `director` varchar(255) NOT NULL COMMENT '导演',
  `year` int(11) NOT NULL COMMENT '上映年份',
  `releaseDate` varchar(50) NOT NULL COMMENT '上映日期',
  `updatedDate` varchar(50) NOT NULL COMMENT '更新日期',
  `timeSize` int(11) NOT NULL COMMENT '时长',
  `watercressScore` double(11,1) DEFAULT NULL COMMENT '豆瓣评分',
  `synopsis` varchar(1000) DEFAULT NULL COMMENT '剧情介绍',
  `videoCapture` varchar(1000) DEFAULT NULL COMMENT '视频截图',
  `createOn` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of variety
-- ----------------------------

-- ----------------------------
-- Table structure for `variety_area`
-- ----------------------------
DROP TABLE IF EXISTS `variety_area`;
CREATE TABLE `variety_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `areaName` varchar(20) NOT NULL COMMENT '地区名称',
  `sort` int(11) NOT NULL COMMENT '排序',
  `createOn` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of variety_area
-- ----------------------------
INSERT INTO `variety_area` VALUES ('1', '中国', '1', '2');
INSERT INTO `variety_area` VALUES ('2', '外国', '2', '2');
INSERT INTO `variety_area` VALUES ('3', '\'123456\'', '2', '1511254444698');
INSERT INTO `variety_area` VALUES ('4', '27123456', '2', '1511254467943');
INSERT INTO `variety_area` VALUES ('5', '27121qwerqwe3456', '2', '1511254472289');
INSERT INTO `variety_area` VALUES ('6', '27121qwerqwe3456', '2', '1511254977236');
INSERT INTO `variety_area` VALUES ('7', '27121qwerqwe3456', '2', '1511255063683');
INSERT INTO `variety_area` VALUES ('8', '27121qwerqwe3456', '2', '1511255122121');
INSERT INTO `variety_area` VALUES ('9', '27121qwerqwe3456', '2', '1511255153339');
INSERT INTO `variety_area` VALUES ('10', '27121qwerqwe3456', '2', '1511255299207');
INSERT INTO `variety_area` VALUES ('11', '27121qwerqwe3456', '2', '1511269062697');
INSERT INTO `variety_area` VALUES ('12', '12', '341234', '1511784799702');
INSERT INTO `variety_area` VALUES ('13', '1234', '12341', '1511784965525');
INSERT INTO `variety_area` VALUES ('14', '1234', '12341', '1511785088833');
INSERT INTO `variety_area` VALUES ('15', '123', '1234', '1511785672458');
INSERT INTO `variety_area` VALUES ('16', '1234', '1234', '1511785709729');
INSERT INTO `variety_area` VALUES ('17', '4123', '123', '1511785776725');
INSERT INTO `variety_area` VALUES ('18', '34123', '12', '1511785788271');
INSERT INTO `variety_area` VALUES ('19', '1234', '12341234', '1511785821958');
INSERT INTO `variety_area` VALUES ('20', '1234', '1234', '1511785937287');
INSERT INTO `variety_area` VALUES ('21', '41234', '1231234', '1511785941446');
INSERT INTO `variety_area` VALUES ('22', '41234', '1241', '1511785945182');
INSERT INTO `variety_area` VALUES ('23', '234123', '1', '1511785953889');
INSERT INTO `variety_area` VALUES ('24', '41234', '123', '1511785992989');
INSERT INTO `variety_area` VALUES ('25', '1234', '1234', '1511786010536');
INSERT INTO `variety_area` VALUES ('26', '2341', '1', '1511786037193');
INSERT INTO `variety_area` VALUES ('27', '2341', '1', '1511786042507');
INSERT INTO `variety_area` VALUES ('28', '123', '1234', '1511786068895');
INSERT INTO `variety_area` VALUES ('29', '23412', '1', '1511786151876');
INSERT INTO `variety_area` VALUES ('30', '23412', '1', '1511786152657');
INSERT INTO `variety_area` VALUES ('31', '23412', '1', '1511786152817');
INSERT INTO `variety_area` VALUES ('32', '23412', '11234', '1511786174414');
INSERT INTO `variety_area` VALUES ('33', '1234', '12341', '1511786214806');
INSERT INTO `variety_area` VALUES ('34', '23412', '134123', '1511786299647');
INSERT INTO `variety_area` VALUES ('35', '23412', '134123', '1511786301162');
INSERT INTO `variety_area` VALUES ('36', '23412', '134123', '1511786301362');
INSERT INTO `variety_area` VALUES ('37', '23412', '134123', '1511786301541');
INSERT INTO `variety_area` VALUES ('38', '23412', '134123', '1511786301728');
INSERT INTO `variety_area` VALUES ('39', '23412', '134123', '1511786301913');
INSERT INTO `variety_area` VALUES ('40', '23412', '134123', '1511786302072');
INSERT INTO `variety_area` VALUES ('41', '23412', '134123', '1511786302224');
INSERT INTO `variety_area` VALUES ('42', '2341', '4123412', '1511786315139');
INSERT INTO `variety_area` VALUES ('43', '2341', '4123412', '1511786315293');
INSERT INTO `variety_area` VALUES ('44', 'eqwrqwer', '4123412', '1511786318550');
INSERT INTO `variety_area` VALUES ('45', 'qwer', '123', '1511786406430');
INSERT INTO `variety_area` VALUES ('46', '', '1', '1511786552550');
INSERT INTO `variety_area` VALUES ('47', '1', '1', '1511786553996');
INSERT INTO `variety_area` VALUES ('48', '1', '1', '1511786554762');
INSERT INTO `variety_area` VALUES ('49', '1', '1', '1511786554923');
INSERT INTO `variety_area` VALUES ('50', '1', '1', '1511786555226');
INSERT INTO `variety_area` VALUES ('51', '1', '1', '1511786555400');
INSERT INTO `variety_area` VALUES ('52', '1', '1', '1511786555606');
INSERT INTO `variety_area` VALUES ('53', '1', '1', '1511786585217');
INSERT INTO `variety_area` VALUES ('54', '1', '1', '1511786585443');

-- ----------------------------
-- Table structure for `variety_resources`
-- ----------------------------
DROP TABLE IF EXISTS `variety_resources`;
CREATE TABLE `variety_resources` (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `varietyId` bigint(20) NOT NULL COMMENT '综艺ID',
  `programName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '节目名称（xx月xx日：标题）',
  `clarity` varchar(20) DEFAULT NULL COMMENT '清晰度',
  `size` double(11,1) DEFAULT NULL COMMENT '视频大小',
  `downlodMode` bit(1) DEFAULT NULL COMMENT '下载方式(1迅雷2百度云)',
  `downlodLink` varchar(10000) DEFAULT NULL COMMENT '资源种子或资源链接',
  `createOn` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of variety_resources
-- ----------------------------

-- ----------------------------
-- Table structure for `variety_type`
-- ----------------------------
DROP TABLE IF EXISTS `variety_type`;
CREATE TABLE `variety_type` (
  `id` bigint(40) NOT NULL COMMENT '编号',
  `typeName` varchar(20) NOT NULL COMMENT '类型名称',
  `sort` int(11) NOT NULL COMMENT '排序',
  `createOn` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of variety_type
-- ----------------------------
INSERT INTO `variety_type` VALUES ('1', '1', '1', '1');
