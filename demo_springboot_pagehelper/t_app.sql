/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : app

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 18/04/2019 22:42:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_app
-- ----------------------------
DROP TABLE IF EXISTS `t_app`;
CREATE TABLE `t_app` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(255) DEFAULT NULL,
  `app_id` int(10) DEFAULT NULL,
  `app_status` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_app
-- ----------------------------
BEGIN;
INSERT INTO `t_app` VALUES (1, 'app11', 1, 1);
INSERT INTO `t_app` VALUES (2, 'app10', 1, 1);
INSERT INTO `t_app` VALUES (3, 'app1', 1, 1);
INSERT INTO `t_app` VALUES (4, 'app2', 1, 1);
INSERT INTO `t_app` VALUES (5, 'app3', 1, 1);
INSERT INTO `t_app` VALUES (6, 'app4', 1, 1);
INSERT INTO `t_app` VALUES (7, 'app5', 1, 1);
INSERT INTO `t_app` VALUES (8, 'app6', 1, 1);
INSERT INTO `t_app` VALUES (9, 'app7', 1, 1);
INSERT INTO `t_app` VALUES (10, 'app8', 1, 1);
INSERT INTO `t_app` VALUES (11, 'app9', 1, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
