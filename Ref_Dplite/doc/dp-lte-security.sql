/*
Navicat MySQL Data Transfer

Source Server         : mysql192.168.180.134
Source Server Version : 50548
Source Host           : 192.168.180.134:3306
Source Database       : dp-lte

Target Server Type    : MYSQL
Target Server Version : 50548
File Encoding         : 65001

Date: 2017-08-28 16:35:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '基础管理', null, '', '0', 'fa fa-coffee', '0', '2017-08-09 22:49:47', null);
INSERT INTO `sys_menu` VALUES ('2', '3', '系统菜单', 'base/menu/list.html', null, '1', 'fa fa-th-list', '2', '2017-08-09 22:55:15', null);
INSERT INTO `sys_menu` VALUES ('3', '0', '系统管理', null, null, '0', 'fa fa-desktop', '1', '2017-08-09 23:06:55', '2017-08-14 14:13:43');
INSERT INTO `sys_menu` VALUES ('4', '1', '通用字典', 'base/macro/list.html', null, '1', 'fa fa-book', '1', '2017-08-09 23:06:58', '2017-08-14 10:46:53');
INSERT INTO `sys_menu` VALUES ('6', '3', '用户管理', 'base/user/list.html', null, '1', 'fa fa-user', '0', '2017-08-10 14:12:11', null);
INSERT INTO `sys_menu` VALUES ('7', '3', '角色管理', 'base/role/list.html', null, '1', 'fa fa-paw', '1', '2017-08-10 14:13:19', null);
INSERT INTO `sys_menu` VALUES ('11', '6', '刷新', null, 'sys:user:list', '2', null, '0', '2017-08-14 10:51:05', null);
INSERT INTO `sys_menu` VALUES ('12', '6', '新增', null, 'sys:user:save', '2', null, '0', '2017-08-14 10:51:35', null);
INSERT INTO `sys_menu` VALUES ('13', '6', '编辑', null, 'sys:user:edit', '2', null, '0', '2017-08-14 10:52:06', null);
INSERT INTO `sys_menu` VALUES ('14', '6', '删除', null, 'sys:user:remove', '2', null, '0', '2017-08-14 10:52:24', null);
INSERT INTO `sys_menu` VALUES ('15', '7', '刷新', null, 'sys:role:list', '2', null, '0', '2017-08-14 10:56:37', null);
INSERT INTO `sys_menu` VALUES ('16', '7', '新增', null, 'sys:role:save', '2', null, '0', '2017-08-14 10:57:02', null);
INSERT INTO `sys_menu` VALUES ('17', '7', '编辑', null, 'sys:user:edit', '2', null, '0', '2017-08-14 10:57:31', null);
INSERT INTO `sys_menu` VALUES ('18', '7', '删除', null, 'sys:role:remove', '2', null, '0', '2017-08-14 10:57:50', null);
INSERT INTO `sys_menu` VALUES ('19', '7', '分配权限', null, 'sys:role:authorize', '2', null, '0', '2017-08-14 10:58:55', null);
INSERT INTO `sys_menu` VALUES ('20', '2', '刷新', null, 'sys:menu:list', '2', null, '0', '2017-08-14 10:59:32', null);
INSERT INTO `sys_menu` VALUES ('21', '2', '新增', null, 'sys:menu:save', '2', null, '0', '2017-08-14 10:59:56', null);
INSERT INTO `sys_menu` VALUES ('22', '2', '编辑', null, 'sys:menu:edit', '2', null, '0', '2017-08-14 11:00:26', null);
INSERT INTO `sys_menu` VALUES ('23', '2', '删除', null, 'sys:menu:remove', '2', null, '0', '2017-08-14 11:00:58', null);
INSERT INTO `sys_menu` VALUES ('24', '6', '启用', null, 'sys:user:enable', '2', null, '0', '2017-08-14 17:27:18', null);
INSERT INTO `sys_menu` VALUES ('25', '6', '停用', null, 'sys:user:disable', '2', null, '0', '2017-08-14 17:27:43', null);
INSERT INTO `sys_menu` VALUES ('26', '6', '重置密码', null, 'sys:user:resetPassword', '2', null, '0', '2017-08-14 17:28:34', null);
INSERT INTO `sys_menu` VALUES ('27', '1', '系统日志', 'base/log/list.html', null, '1', 'fa fa-warning', '2', '2017-08-14 22:11:53', null);
INSERT INTO `sys_menu` VALUES ('28', '27', '刷新', null, 'sys:log:list', '2', null, '0', '2017-08-14 22:30:22', null);
INSERT INTO `sys_menu` VALUES ('29', '27', '删除', null, 'sys:log:remove', '2', null, '0', '2017-08-14 22:30:43', null);
INSERT INTO `sys_menu` VALUES ('30', '27', '清空', null, 'sys:log:clear', '2', null, '0', '2017-08-14 22:31:02', null);
INSERT INTO `sys_menu` VALUES ('32', '4', '刷新', null, 'sys:macro:list', '2', null, '0', '2017-08-15 16:55:33', null);
INSERT INTO `sys_menu` VALUES ('33', '4', '新增', null, 'sys:macro:save', '2', null, '0', '2017-08-15 16:55:52', null);
INSERT INTO `sys_menu` VALUES ('34', '4', '编辑', null, 'sys:macro:edit', '2', null, '0', '2017-08-15 16:56:09', null);
INSERT INTO `sys_menu` VALUES ('35', '4', '删除', null, 'sys:macro:remove', '2', null, '0', '2017-08-15 16:56:29', null);
INSERT INTO `sys_menu` VALUES ('37', '1', '行政区域', 'base/area/list.html', null, '1', 'fa fa-leaf', '0', '2017-08-21 17:21:18', '2017-08-21 17:21:41');
INSERT INTO `sys_menu` VALUES ('38', '37', '刷新', null, 'sys:area:list', '2', null, '0', '2017-08-17 10:01:33', '2017-08-17 10:01:52');
INSERT INTO `sys_menu` VALUES ('39', '37', '新增', null, 'sys:area:save', '2', null, '0', '2017-08-17 10:02:16', null);
INSERT INTO `sys_menu` VALUES ('40', '37', '编辑', null, 'sys:area:edit', '2', null, '0', '2017-08-17 10:02:33', null);
INSERT INTO `sys_menu` VALUES ('41', '37', '删除', null, 'sys:area:remove', '2', null, '0', '2017-08-17 10:02:50', null);
INSERT INTO `sys_menu` VALUES ('42', '1', '定时任务', 'base/quartz/list.html', null, '1', 'fa fa-bell', '4', '2017-08-19 23:00:08', null);
INSERT INTO `sys_menu` VALUES ('43', '42', '刷新', null, 'quartz:job:list', '2', null, '0', '2017-08-19 23:00:54', null);
INSERT INTO `sys_menu` VALUES ('44', '42', '新增', null, 'quartz:job:save', '2', null, '0', '2017-08-19 23:01:29', null);
INSERT INTO `sys_menu` VALUES ('45', '42', '编辑', null, 'quartz:job:edit', '2', null, '0', '2017-08-19 23:01:58', null);
INSERT INTO `sys_menu` VALUES ('46', '42', '删除', null, 'quartz:job:remove', '2', null, '0', '2017-08-19 23:02:30', null);
INSERT INTO `sys_menu` VALUES ('47', '42', '启用', null, 'quartz:job:enable', '2', null, '0', '2017-08-19 23:08:59', null);
INSERT INTO `sys_menu` VALUES ('48', '42', '停用', null, 'quartz:job:disable', '2', null, '0', '2017-08-19 23:09:31', null);
INSERT INTO `sys_menu` VALUES ('49', '42', '立即执行', null, 'quartz:job:run', '2', null, '0', '2017-08-19 23:10:09', null);
INSERT INTO `sys_menu` VALUES ('50', '42', '日志列表', null, 'quartz:job:log', '2', null, '0', '2017-08-19 23:10:40', null);
INSERT INTO `sys_menu` VALUES ('51', '42', '刷新', null, 'quartz:log:list', '2', null, '0', '2017-08-21 13:25:33', null);
INSERT INTO `sys_menu` VALUES ('52', '42', '删除', null, 'quartz:log:remove', '2', null, '0', '2017-08-21 13:25:52', null);
INSERT INTO `sys_menu` VALUES ('53', '42', '清空', null, 'quartz:log:clear', '2', null, '0', '2017-08-21 13:26:11', null);
INSERT INTO `sys_menu` VALUES ('54', '1', '接口管理', 'swagger-ui.html', null, '1', 'fa fa-support', '5', '2017-08-19 23:00:08', null);
INSERT INTO `sys_menu` VALUES ('55', '1', '系统监控', 'druid/index.html', null, '1', 'fa fa-bug', '6', '2017-08-19 23:00:08', null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `role_sign` varchar(100) DEFAULT NULL COMMENT '角色标识',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', '【系统内置】', '2', '2017-08-12 00:43:52', '2017-08-12 19:14:59');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (null, '1', '1');
INSERT INTO `sys_role_menu` VALUES (null, '1', '2');
INSERT INTO `sys_role_menu` VALUES (null, '1', '4');
INSERT INTO `sys_role_menu` VALUES (null, '1', '3');
INSERT INTO `sys_role_menu` VALUES (null, '1', '6');
INSERT INTO `sys_role_menu` VALUES (null, '1', '7');
INSERT INTO `sys_role_menu` VALUES (null, '1', '11');
INSERT INTO `sys_role_menu` VALUES (null, '1', '12');
INSERT INTO `sys_role_menu` VALUES (null, '1', '13');
INSERT INTO `sys_role_menu` VALUES (null, '1', '14');
INSERT INTO `sys_role_menu` VALUES (null, '1', '15');
INSERT INTO `sys_role_menu` VALUES (null, '1', '16');
INSERT INTO `sys_role_menu` VALUES (null, '1', '17');
INSERT INTO `sys_role_menu` VALUES (null, '1', '18');
INSERT INTO `sys_role_menu` VALUES (null, '1', '19');
INSERT INTO `sys_role_menu` VALUES (null, '1', '20');
INSERT INTO `sys_role_menu` VALUES (null, '1', '21');
INSERT INTO `sys_role_menu` VALUES (null, '1', '22');
INSERT INTO `sys_role_menu` VALUES (null, '1', '23');
INSERT INTO `sys_role_menu` VALUES (null, '1', '24');
INSERT INTO `sys_role_menu` VALUES (null, '1', '25');
INSERT INTO `sys_role_menu` VALUES (null, '1', '26');
INSERT INTO `sys_role_menu` VALUES (null, '1', '27');
INSERT INTO `sys_role_menu` VALUES (null, '1', '28');
INSERT INTO `sys_role_menu` VALUES (null, '1', '29');
INSERT INTO `sys_role_menu` VALUES (null, '1', '30');
INSERT INTO `sys_role_menu` VALUES (null, '1', '32');
INSERT INTO `sys_role_menu` VALUES (null, '1', '33');
INSERT INTO `sys_role_menu` VALUES (null, '1', '34');
INSERT INTO `sys_role_menu` VALUES (null, '1', '35');
INSERT INTO `sys_role_menu` VALUES (null, '1', '37');
INSERT INTO `sys_role_menu` VALUES (null, '1', '38');
INSERT INTO `sys_role_menu` VALUES (null, '1', '39');
INSERT INTO `sys_role_menu` VALUES (null, '1', '40');
INSERT INTO `sys_role_menu` VALUES (null, '1', '41');
INSERT INTO `sys_role_menu` VALUES (null, '1', '42');
INSERT INTO `sys_role_menu` VALUES (null, '1', '43');
INSERT INTO `sys_role_menu` VALUES (null, '1', '44');
INSERT INTO `sys_role_menu` VALUES (null, '1', '45');
INSERT INTO `sys_role_menu` VALUES (null, '1', '46');
INSERT INTO `sys_role_menu` VALUES (null, '1', '47');
INSERT INTO `sys_role_menu` VALUES (null, '1', '48');
INSERT INTO `sys_role_menu` VALUES (null, '1', '49');
INSERT INTO `sys_role_menu` VALUES (null, '1', '50');
INSERT INTO `sys_role_menu` VALUES (null, '1', '51');
INSERT INTO `sys_role_menu` VALUES (null, '1', '52');
INSERT INTO `sys_role_menu` VALUES (null, '1', '53');
INSERT INTO `sys_role_menu` VALUES (null, '1', '54');
INSERT INTO `sys_role_menu` VALUES (null, '1', '55');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(255) DEFAULT NULL COMMENT '状态 0:禁用，1:正常',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '33808479d49ca8a3cdc93d4f976d1e3d', 'admin@example.com', '123456', '1', '1', '2017-08-15 21:40:39', '2017-08-15 21:41:00');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (null, '1', '1');
