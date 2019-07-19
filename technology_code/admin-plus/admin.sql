/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : admin

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-01-18 15:02:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for act_acticle
-- ----------------------------
DROP TABLE IF EXISTS `act_acticle`;
CREATE TABLE `act_acticle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `auther` varchar(20) DEFAULT NULL COMMENT '作者',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `is_deleted` varchar(10) DEFAULT 'N' COMMENT '是否删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of act_acticle
-- ----------------------------
INSERT INTO `act_acticle` VALUES ('1', 'rstyro11', 'aabb', 'aabb', 'N', '2019-01-17 10:05:21', '2019-01-17 10:05:22');
INSERT INTO `act_acticle` VALUES ('2', 'rstyro', 'aa', 'aa', 'N', '2019-01-17 10:05:21', '2019-01-17 10:35:02');


-- ----------------------------
-- Table structure for admin_login
-- ----------------------------
DROP TABLE IF EXISTS `admin_login`;
CREATE TABLE `admin_login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `last_login_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8mb4;


-- ----------------------------
-- Table structure for admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_menu`;
CREATE TABLE `admin_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级ID',
  `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `menu_url` varchar(50) DEFAULT '#' COMMENT '菜单地址',
  `menu_type` enum('2','1') DEFAULT '2' COMMENT '1 -- 系统菜单，2 -- 业务菜单',
  `menu_icon` varchar(50) DEFAULT '#' COMMENT '菜单Icon',
  `sort_num` int(11) DEFAULT '1' COMMENT '排序',
  `user_id` int(11) DEFAULT '1' COMMENT '创建这个菜单的用户id',
  `is_del` int(11) DEFAULT '0' COMMENT '1-- 删除状态，0 -- 正常',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of admin_menu
-- ----------------------------
INSERT INTO `admin_menu` VALUES (1, 0, '系统管理', '#', '1', 'fa fa-gears', 1, 1, 0,NOW(),NOW());
INSERT INTO `admin_menu` VALUES (2, 1, '菜单管理', 'admin/menu/list', '1', '#', 1, 1, 0,NOW(),NOW());
INSERT INTO `admin_menu` VALUES (3, 1, '角色管理', 'admin/role/list', '1', '', 2, 1, 0,NOW(),NOW());
INSERT INTO `admin_menu` VALUES (4, 1, '用户管理', 'admin/user/list', '1', '', 3, 1, 0,NOW(),NOW());
INSERT INTO `admin_menu` VALUES (5, 0, '商户管理', '#', '2', 'fa fa-tasks', 2, 1, 0,NOW(),NOW());
INSERT INTO `admin_menu` VALUES (6, 5, '商户列表', 'act/acticle/list', '2', '', 1, 1, 0,NOW(),NOW());
INSERT INTO `admin_menu` VALUES (7, 0, 'FMIS', '#', '2', 'fa fa-tasks', 2, 1, 0,NOW(),NOW());
INSERT INTO `admin_menu` VALUES (8, 0, 'PSS', '#', '2', 'fa fa-tasks', 2, 1, 0,NOW(),NOW());
INSERT INTO `admin_menu` VALUES (9, 0, 'FSS', '#', '2', 'fa fa-tasks', 2, 1, 0,NOW(),NOW());
INSERT INTO `admin_menu` VALUES (10, 0, 'TSS', '#', '2', 'fa fa-tasks', 2, 1, 0,NOW(),NOW());

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名',
  `role_desc` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `rights` varchar(255) DEFAULT '0' COMMENT '最大权限的值',
  `add_qx` varchar(255) DEFAULT '0' COMMENT '添加权限',
  `del_qx` varchar(255) DEFAULT '0' COMMENT '删除权限',
  `edit_qx` varchar(255) DEFAULT '0' COMMENT '编辑权限',
  `query_qx` varchar(255) DEFAULT '0' COMMENT '查看权限',
  `user_id` varchar(10) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES ('1', '管理员', '管理员权限', '1267650600228229401496703205375', '254', '254', '254', '254', '1', '2019-01-17 09:59:29');
INSERT INTO `admin_role` VALUES ('2', '普通用户', '用户权限', '190', '190', '160', '190', '190', '1', '2019-01-03 16:31:18');

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `password` varchar(50) DEFAULT NULL,
  `pic_path` varchar(200) DEFAULT '/images/logo.png' COMMENT '头像地址',
  `status` enum('unlock','lock') DEFAULT 'unlock',
  `sessionId` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES ('1', 'admin', '超级管理员', 'd033e22ae348aeb5660fc2140aec35850c4da997', 'http://www.lrshuai.top/upload/user/20170612/05976238.png', 'unlock', '87906722879D6DFCC6032721B4935B00', '2017-08-18 13:57:32');
-- ----------------------------
-- Table structure for admin_user_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_role`;
CREATE TABLE `admin_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4;




