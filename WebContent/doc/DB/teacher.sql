/*
Navicat MySQL Data Transfer

Source Server         : 10.2.17.176_3306
Source Server Version : 50610
Source Host           : 10.2.17.176:3306
Source Database       : teacher

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2016-09-12 11:46:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `r_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `r_user_role`;
CREATE TABLE `r_user_role` (
  `t_role_id` varchar(50) NOT NULL,
  `t_user_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`t_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of r_user_role
-- ----------------------------
INSERT INTO `r_user_role` VALUES ('1', 'd51f6c5d-e2f8-4700-9689-7bb9b8f93701');
INSERT INTO `r_user_role` VALUES ('3', 'd51f6c5d-e2f8-4700-9689-7bb9b8f93702');

-- ----------------------------
-- Table structure for `t_department`
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department` (
  `t_dept_id` varchar(50) NOT NULL,
  `t_dept_name` varchar(45) DEFAULT NULL COMMENT '部门名称',
  `t_status` varchar(20) DEFAULT NULL COMMENT '是否启用',
  `t_budget` varchar(45) DEFAULT NULL COMMENT '部门预算\n',
  `t_dept_desc` varchar(45) DEFAULT NULL COMMENT '部门职能描述',
  `t_admin_id` varchar(45) DEFAULT NULL COMMENT '部门总监',
  `t_bianzhi` varchar(45) DEFAULT NULL COMMENT '人员编制',
  PRIMARY KEY (`t_dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_department
-- ----------------------------
INSERT INTO `t_department` VALUES ('85fc4079-8af3-427f-9f62-92a6cae0122c', 'ee', '22', '22', '22', '22', '22');
INSERT INTO `t_department` VALUES ('cac5fe50-f73f-4c0e-ad14-4ede6c02c91a', '邯郸银行', '11', '骄傲事故', '哈吉', '我是挖掘体验', '安居客');

-- ----------------------------
-- Table structure for `t_modules`
-- ----------------------------
DROP TABLE IF EXISTS `t_modules`;
CREATE TABLE `t_modules` (
  `t_module_id` varchar(50) NOT NULL,
  `t_module_name` varchar(45) DEFAULT NULL COMMENT '模块名称  比如 预约管理  用户管理   \n',
  `t_module_pid` varchar(50) DEFAULT NULL COMMENT '模块的父ID  树形结构 需要',
  `t_module_url` varchar(200) DEFAULT NULL COMMENT '模块的url 链接地址  ： person/showPerson.jsp\nmain.jsp主页使用 iframe  点击树形模块节点  触发url 页面跳转',
  PRIMARY KEY (`t_module_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_modules
-- ----------------------------
INSERT INTO `t_modules` VALUES ('1', 'HRpooling', '-1', 'jumpAction_goToSysUser.action');
INSERT INTO `t_modules` VALUES ('2', '用户管理', '4', 'jumpAction_goToSysUser.action');
INSERT INTO `t_modules` VALUES ('3', '人员管理', '4', 'jumpAction_goToPersonPage.action');
INSERT INTO `t_modules` VALUES ('4', '系统管理', '1', 'sysUserAction_goToSysUser.action');
INSERT INTO `t_modules` VALUES ('5', '简历管理', '1', 'sysUserAction_goToSysUser.action');
INSERT INTO `t_modules` VALUES ('6', '模块管理', '4', 'jumpAction_goToModulesPage.action');
INSERT INTO `t_modules` VALUES ('7', '角色管理', '4', 'jumpAction_goToRolePage.action');
INSERT INTO `t_modules` VALUES ('8', '部门管理', '4', 'jumpAction_goToDepartPage.action');

-- ----------------------------
-- Table structure for `t_person`
-- ----------------------------
DROP TABLE IF EXISTS `t_person`;
CREATE TABLE `t_person` (
  `t_person_id` varchar(50) NOT NULL,
  `t_person_name` varchar(45) DEFAULT NULL COMMENT '员工姓名',
  `t_mobile` varchar(45) DEFAULT NULL COMMENT '手机号',
  `t_work_num` varchar(45) DEFAULT NULL COMMENT '员工 工号',
  `t_education` varchar(10) DEFAULT NULL COMMENT '学历',
  `t_id_num` varchar(45) DEFAULT NULL COMMENT '身份证号',
  `t_address` varchar(10) DEFAULT NULL COMMENT '现住址',
  `t_gender` varchar(10) DEFAULT NULL COMMENT '性别',
  `t_birth_date` varchar(45) DEFAULT NULL COMMENT '生日',
  `t_add_date` varchar(45) DEFAULT NULL COMMENT '入职日期',
  `t_work_phone` varchar(45) DEFAULT NULL COMMENT '办公电话',
  `t_type` varchar(10) DEFAULT NULL COMMENT '员工类型      合同工、临时工、试用期',
  `t_job_id` varchar(50) DEFAULT NULL COMMENT '职位id',
  `t_department_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`t_person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员信息表';

-- ----------------------------
-- Records of t_person
-- ----------------------------
INSERT INTO `t_person` VALUES ('12', '马化腾', '13999999999', '001', '研究生', '123', '深圳', '男', '1970-01-01', '1970-01-01', '021', '正式工', '11', '');
INSERT INTO `t_person` VALUES ('134', '杨紫', '137777777777', '000', '本科', '110', '北京', '女', '1970-01-01', '1970-01-01', '010', '合同工', '11', '3');
INSERT INTO `t_person` VALUES ('73a3ab12-47f6-4524-93fa-ff31b8faec7f', '水电费v', '', ' 的vddf ', ' 地方 ', ' 地方', ' 发给', '女', '2016-09-07', '2016-09-22', '', '', 'd0021a4f-64c5-456a-b567-e7a291e63991', '0abb2a53-1fe8-4e88-b27c-ae3c839786c6');
INSERT INTO `t_person` VALUES ('cc10fcf6-bfe0-40ed-8409-1014b5d7a801', 'ffff', '111111', '1111', '111', '11', '11', '女', '2016-08-31', '2016-09-13', '111', '111', 'b065864b-2cff-4136-9ff6-ac6770665435', '96ca9879-a437-4a9f-81d1-be400aef5832');
INSERT INTO `t_person` VALUES ('f8aeff84-bf6f-4e2d-bb64-ab5cab6fe982', '马云', '13888888888', '003', '本科', '130202', '杭州西湖', '男', '2016-09-07', '2016-09-07', '031', '合同工', '123', '3');

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `t_role_id` varchar(50) NOT NULL,
  `t_role_name` varchar(45) DEFAULT NULL COMMENT '角色名称：1.超级管理员   2.普通管理员  3.学生    用户的角色  必须由上一级管理员来分配 \n普通教师管理员 由超级管理员来分配，学生由普通管理员来分配',
  PRIMARY KEY (`t_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '普通管理员');
INSERT INTO `t_role` VALUES ('2', '超级管理员');
INSERT INTO `t_role` VALUES ('3', '学生');
INSERT INTO `t_role` VALUES ('7e70614d-5f69-4d8e-9ed3-3bd5565031c8', '系统管理员');
INSERT INTO `t_role` VALUES ('d7da6c40-5c11-4349-ad52-0074446b487b', '会考');

-- ----------------------------
-- Table structure for `t_sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `t_user_id` varchar(50) NOT NULL,
  `t_user_name` varchar(45) DEFAULT NULL,
  `t_user_pwd` varchar(45) DEFAULT NULL COMMENT '用户密码 进行MD5 加密，不能在数据库 直接观察到  为了防止学生窃取管理员密码',
  `t_person_id` varchar(50) NOT NULL COMMENT '用户先注册账号，然后再进入系统 完善个人信息',
  `t_user_state` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`t_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户表';

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('', 'hanyuwen', '123', '', '0');
INSERT INTO `t_sys_user` VALUES ('7bb9b8f93702', 'jianghu', '123', '', '0');
INSERT INTO `t_sys_user` VALUES ('d51f6c5d-e2f8-4700-9689-7bb9b8f93701', 'zhangsan', '123', '', '0');
INSERT INTO `t_sys_user` VALUES ('d51f6c5d-e2f8-4700-9689-7bb9b8f93702', 'lisi', '123', '6967be84-36c1-4a00-826e-e9a5879551b2', '0');
INSERT INTO `t_sys_user` VALUES ('fb3d4519-b12f-4a99-9ad3-1fe42d84a4b8', 'wangwu', '123', 'a86aa80f-f590-47c0-97d8-0593cf6f1272', '0');
