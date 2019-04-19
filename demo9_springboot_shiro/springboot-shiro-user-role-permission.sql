/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 18/04/2019 18:26:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
BEGIN;
INSERT INTO `hibernate_sequence` VALUES (1);
INSERT INTO `hibernate_sequence` VALUES (1);
INSERT INTO `hibernate_sequence` VALUES (1);
COMMIT;

-- ----------------------------
-- Table structure for student_copy1
-- ----------------------------
DROP TABLE IF EXISTS `student_copy1`;
CREATE TABLE `student_copy1` (
  `sid` int(10) NOT NULL AUTO_INCREMENT,
  `sname` varchar(255) DEFAULT NULL,
  `gid` int(10) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(10) DEFAULT NULL,
  `available` int(10) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `parent_ids` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `resource_type` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` VALUES (1, 0, '用户管理', '0', '0/', 'userInfo:view', 'menu', 'userInfo/userList');
INSERT INTO `sys_permission` VALUES (2, 0, '用户添加', '1', '0/1', 'userInfo:add', 'button', 'userInfo/userAdd');
INSERT INTO `sys_permission` VALUES (3, 0, '用户删除', '1', '0/1', 'userInfo:del', 'button', 'userInfo/userDel');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(10) DEFAULT NULL,
  `available` int(10) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `avaliable` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, 0, '管理员', 'admin', NULL);
INSERT INTO `sys_role` VALUES (2, 0, 'VIP会员', 'vip', NULL);
INSERT INTO `sys_role` VALUES (3, 1, 'test', 'test', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `permission_id` int(10) DEFAULT NULL,
  `role_id` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_permission` VALUES (1, 1);
INSERT INTO `sys_role_permission` VALUES (1, 1);
INSERT INTO `sys_role_permission` VALUES (2, 1);
INSERT INTO `sys_role_permission` VALUES (3, 2);
INSERT INTO `sys_role_permission` VALUES (1, 3);
INSERT INTO `sys_role_permission` VALUES (3, 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `role_id` int(10) DEFAULT NULL,
  `uid` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (3, 1);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `pass_word` varchar(255) DEFAULT NULL,
  `reg_time` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `uid` varchar(100) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `state` int(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
BEGIN;
INSERT INTO `user_info` VALUES ('1', 'admin', '管理员', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', 0);
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `userName` varchar(32) DEFAULT NULL COMMENT '用户名',
  `passWord` varchar(32) DEFAULT NULL COMMENT '密码',
  `user_sex` varchar(32) DEFAULT NULL,
  `nick_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES (34, '关于', '121', '1212', '1212');
INSERT INTO `users` VALUES (35, '12312', '2312312', '331231', '312312');
INSERT INTO `users` VALUES (36, 'zhangzzz', '121', '121', '1212');
INSERT INTO `users` VALUES (37, '12312', '123', '12123', '3123');
INSERT INTO `users` VALUES (38, '123111', '231', '12312', '123');
INSERT INTO `users` VALUES (39, '23', '23', '234', '4234');
COMMIT;

-- ----------------------------
-- View structure for allroles
-- ----------------------------
DROP VIEW IF EXISTS `allroles`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `allroles` AS select `r`.`id` AS `id`,`r`.`available` AS `available`,`r`.`description` AS `description`,`r`.`role` AS `role`,`r`.`avaliable` AS `avaliable` from ((`sys_role` `r` left join `sys_user_role` `ur` on((`ur`.`role_id` = `r`.`id`))) left join `user_info` `u` on((`u`.`uid` = `ur`.`uid`))) where (`u`.`username` = 'admin');

-- ----------------------------
-- View structure for myview
-- ----------------------------
DROP VIEW IF EXISTS `myview`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `myview` AS select `u`.`uid` AS `uuid`,`u`.`username` AS `uusername`,`u`.`name` AS `uname`,`r`.`id` AS `rid`,`r`.`description` AS `description`,`p`.`id` AS `pid`,`p`.`permission` AS `pper` from ((((`user_info` `u` left join `sys_user_role` `ur` on((`ur`.`uid` = `u`.`uid`))) left join `sys_role` `r` on((`r`.`id` = `ur`.`role_id`))) left join `sys_role_permission` `rp` on((`rp`.`role_id` = `r`.`id`))) left join `sys_permission` `p` on((`p`.`id` = `rp`.`permission_id`)));

-- ----------------------------
-- View structure for zy
-- ----------------------------
DROP VIEW IF EXISTS `zy`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `zy` AS select `p`.`permission` AS `pp` from ((((`sys_permission` `p` left join `sys_role_permission` `rp` on((`rp`.`permission_id` = `p`.`id`))) left join `sys_role` `r` on((`r`.`id` = `rp`.`role_id`))) left join `sys_user_role` `ur` on((`ur`.`role_id` = `r`.`id`))) left join `user_info` `u` on((`u`.`uid` = `ur`.`uid`))) where (`r`.`role` = 'vip');

SET FOREIGN_KEY_CHECKS = 1;
