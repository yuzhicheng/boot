/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50615
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50615
File Encoding         : 65001

Date: 2017-07-24 16:01:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('2e9f70e9-36dc-11e7-8e56-dc4a3e709f1e', 'ROLE_USER');
INSERT INTO `sys_role` VALUES ('2ea34a85-36dc-11e7-8e56-dc4a3e709f1e', 'ROLE_ADMIN');
INSERT INTO `sys_role` VALUES ('b1530554-bb97-498f-a89d-94d19458f738', 'ROLE_ACTUATOR');
