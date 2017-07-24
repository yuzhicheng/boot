/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50615
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50615
File Encoding         : 65001

Date: 2017-07-24 16:01:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('2e87950f-36dc-11e7-8e56-dc4a3e709f1e', 'yzc', 'yzc');
INSERT INTO `sys_user` VALUES ('2e8b28cd-36dc-11e7-8e56-dc4a3e709f1e', 'admin', 'admin');
