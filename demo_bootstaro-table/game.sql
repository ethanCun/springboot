/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : bootstrap-table

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 08/05/2019 17:51:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for game
-- ----------------------------
DROP TABLE IF EXISTS `game`;
CREATE TABLE `game` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `cn_name` varchar(255) DEFAULT NULL COMMENT '中文名',
  `jp_name` varchar(255) DEFAULT NULL COMMENT '日文名',
  `en_name` varchar(255) DEFAULT NULL COMMENT '英文名',
  `nature` varchar(255) DEFAULT NULL COMMENT '属性',
  `generation` varchar(255) DEFAULT NULL COMMENT '世代',
  `power` varchar(255) DEFAULT NULL COMMENT '威力',
  `hirate` varchar(255) DEFAULT NULL COMMENT '命中',
  `type` varchar(255) DEFAULT NULL COMMENT '攻击类型',
  `pp` varchar(255) DEFAULT NULL COMMENT 'pp点数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game
-- ----------------------------
BEGIN;
INSERT INTO `game` VALUES (1, 'cn_name1', 'jp_name1', 'en_name1', 'nature1', 'generation1', 'power1', 'hirate1', 'type1', 'pp1');
INSERT INTO `game` VALUES (2, 'cn_name2', 'jp_name2', 'en_name2', 'nature2', 'generation2', 'power2', 'hirate2', 'type2', 'pp2');
INSERT INTO `game` VALUES (3, 'cn_name3', 'jp_name3', 'en_name3', 'nature3', 'generation3', 'power3', 'hirate3', 'type3', 'pp3');
INSERT INTO `game` VALUES (4, 'cn_name3', 'jp_name3', 'en_name3', 'nature3', 'generation3', 'power3', 'hirate3', 'type3', 'pp3');
INSERT INTO `game` VALUES (5, 'cn_name3', 'jp_name3', 'en_name3', 'nature3', 'generation3', 'power3', 'hirate3', 'type3', 'pp3');
INSERT INTO `game` VALUES (6, 'cn_name3', 'jp_name3', 'en_name3', 'nature3', 'generation3', 'power3', 'hirate3', 'type3', 'pp3');
INSERT INTO `game` VALUES (7, 'cn_name3', 'jp_name3', 'en_name3', 'nature3', 'generation3', 'power3', 'hirate3', 'type3', 'pp3');
INSERT INTO `game` VALUES (8, 'cn_name3', 'jp_name3', 'en_name3', 'nature3', 'generation3', 'power3', 'hirate3', 'type3', 'pp3');
INSERT INTO `game` VALUES (9, 'cn_name3', 'jp_name3', 'en_name3', 'nature3', 'generation3', 'power3', 'hirate3', 'type3', 'pp3');
INSERT INTO `game` VALUES (10, 'cn_name3', 'jp_name3', 'en_name3', 'nature3', 'generation3', 'power3', 'hirate3', 'type3', 'pp3');
INSERT INTO `game` VALUES (11, 'cn_name3', 'jp_name3', 'en_name3', 'nature3', 'generation3', 'power3', 'hirate3', 'type3', 'pp3');
INSERT INTO `game` VALUES (12, 'cn_name3', 'jp_name3', 'en_name3', 'nature3', 'generation3', 'power3', 'hirate3', 'type3', 'pp3');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
