/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50615
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50615
File Encoding         : 65001

Date: 2017-07-24 16:01:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_user_roles`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_roles`;
CREATE TABLE `sys_user_roles` (
  `sys_user_id` varchar(255) NOT NULL,
  `roles_id` varchar(255) NOT NULL,
  KEY `FKdpvc6d7xqpqr43dfuk1s27cqh` (`roles_id`),
  KEY `FKd0ut7sloes191bygyf7a3pk52` (`sys_user_id`),
  CONSTRAINT `FKd0ut7sloes191bygyf7a3pk52` FOREIGN KEY (`sys_user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKdpvc6d7xqpqr43dfuk1s27cqh` FOREIGN KEY (`roles_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_roles
-- ----------------------------
INSERT INTO `sys_user_roles` VALUES ('2e87950f-36dc-11e7-8e56-dc4a3e709f1e', '2e9f70e9-36dc-11e7-8e56-dc4a3e709f1e');
INSERT INTO `sys_user_roles` VALUES ('2e8b28cd-36dc-11e7-8e56-dc4a3e709f1e', '2ea34a85-36dc-11e7-8e56-dc4a3e709f1e');
INSERT INTO `sys_user_roles` VALUES ('2e87950f-36dc-11e7-8e56-dc4a3e709f1e', 'b1530554-bb97-498f-a89d-94d19458f738');
