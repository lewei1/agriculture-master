/*
Navicat MySQL Data Transfer

Source Server         : 172.16.5.240
Source Server Version : 50717
Source Host           : 172.16.5.240:3306
Source Database       : agriculture

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-07-15 11:50:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_plot_region
-- ----------------------------
DROP TABLE IF EXISTS `tb_plot_region`;
CREATE TABLE `tb_plot_region` (
  `region_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `region_name` varchar(32) DEFAULT NULL COMMENT '类型名称',
  `region_status` tinyint(4) DEFAULT '0' COMMENT '状态',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `farm_id` varchar(32) DEFAULT NULL COMMENT '农场id',
  PRIMARY KEY (`region_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='地块区域表';

-- ----------------------------
-- Records of tb_plot_region
-- ----------------------------
INSERT INTO `tb_plot_region` VALUES ('1', '大棚区域', '0', null, null, null, null, null);
INSERT INTO `tb_plot_region` VALUES ('2', '大田区域', '0', null, null, null, null, null);


-- ----------------------------
-- Table structure for tb_unit_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_unit_type`;
CREATE TABLE `tb_unit_type` (
  `material_unit_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '单位ID',
  `type_name` varchar(10) NOT NULL COMMENT '单位类型',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `farm_id` varchar(32) DEFAULT NULL COMMENT '农场id,UUID',
  PRIMARY KEY (`material_unit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='物品-单位表';

-- ----------------------------
-- Records of tb_unit_type
-- ----------------------------
INSERT INTO `tb_unit_type` VALUES ('1', 'g', null, null, null, null, null);
INSERT INTO `tb_unit_type` VALUES ('2', 'kg', null, null, null, null, null);
INSERT INTO `tb_unit_type` VALUES ('3', 'ml', null, null, null, null, null);
INSERT INTO `tb_unit_type` VALUES ('4', 'L', null, null, null, null, null);


-- ----------------------------
-- Table structure for tb_harvest_spec
-- ----------------------------
DROP TABLE IF EXISTS `tb_harvest_spec`;
CREATE TABLE `tb_harvest_spec` (
  `harvest_spec_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `spec_name` varchar(32) DEFAULT NULL COMMENT '规格名称',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `farm_id` varchar(32) DEFAULT NULL COMMENT '农场id',
  PRIMARY KEY (`harvest_spec_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='采收规格表';

-- ----------------------------
-- Records of tb_harvest_spec
-- ----------------------------
INSERT INTO `tb_harvest_spec` VALUES ('1', '标准', null, null, null, null, null);



-- ----------------------------
-- Table structure for tb_res_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_res_role`;
CREATE TABLE `tb_res_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` varchar(30) DEFAULT NULL COMMENT '角色名',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `farm_id` varchar(32) DEFAULT NULL COMMENT '农场ID',
  `status` tinyint(4) DEFAULT NULL COMMENT '是否有效',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of tb_res_role
-- ----------------------------
INSERT INTO `tb_res_role` VALUES ('1', '超级管理员', null, null, null, '0');




-- ----------------------------
-- Table structure for tb_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `tb_warehouse`;
CREATE TABLE `tb_warehouse` (
  `warehouse_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '仓库ID',
  `name` varchar(32) DEFAULT NULL COMMENT '仓库名称',
  `warehouse_status` tinyint(4) DEFAULT NULL COMMENT '仓库状态',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `farm_id` varchar(32) DEFAULT NULL COMMENT '农场id',
  PRIMARY KEY (`warehouse_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='仓库表';

-- ----------------------------
-- Records of tb_warehouse
-- ----------------------------
INSERT INTO `tb_warehouse` VALUES ('1', '默认仓库', '0', null, null, null, null, null);



-- ----------------------------
-- Table structure for tb_farming_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_farming_type`;
CREATE TABLE `tb_farming_type` (
  `farming_type_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `farming_type_name` varchar(32) DEFAULT NULL COMMENT '农事类型名称',
  `farming_type_status` tinyint(4) DEFAULT '0' COMMENT '类型状态',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `farm_id` varchar(32) DEFAULT NULL COMMENT '农场id',
  `tpye_record` int(1) DEFAULT '3' COMMENT '记录类型（1施肥记录，2用药记录，3劳作记录）',
  PRIMARY KEY (`farming_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='农事类型';

-- ----------------------------
-- Records of tb_farming_type
-- ----------------------------
INSERT INTO `tb_farming_type` VALUES ('1', '中耕除草', '0', null, null, null, null, null, '3');
INSERT INTO `tb_farming_type` VALUES ('2', '做畦', '0', null, null, null, null, null, '3');
INSERT INTO `tb_farming_type` VALUES ('3', '引蔓', '0', null, null, null, null, null, '3');
INSERT INTO `tb_farming_type` VALUES ('4', '打药', '0', null, null, null, null, null, '2');
INSERT INTO `tb_farming_type` VALUES ('5', '拉蔓', '0', null, null, null, null, null, '3');
INSERT INTO `tb_farming_type` VALUES ('6', '挂黄板', '0', null, null, null, null, null, '3');
INSERT INTO `tb_farming_type` VALUES ('7', '摘心', '0', null, null, null, null, null, '3');
INSERT INTO `tb_farming_type` VALUES ('8', '施底肥', '0', null, null, null, null, null, '1');
INSERT INTO `tb_farming_type` VALUES ('9', '施肥', '0', null, null, null, null, null, '1');
INSERT INTO `tb_farming_type` VALUES ('10', '浇水', '0', null, null, null, null, null, '3');
INSERT INTO `tb_farming_type` VALUES ('11', '疏花疏果', '0', null, null, null, null, null, '3');
INSERT INTO `tb_farming_type` VALUES ('12', '绕秧', '0', null, null, null, null, null, '3');
INSERT INTO `tb_farming_type` VALUES ('13', '翻耕', '0', null, null, null, null, null, '3');
INSERT INTO `tb_farming_type` VALUES ('14', '装袋 ', '0', null, null, null, null, null, '3');
INSERT INTO `tb_farming_type` VALUES ('15', '采收', '0', null, null, null, null, null, '3');
INSERT INTO `tb_farming_type` VALUES ('16', '释放天敌', '0', null, null, null, null, null, '3');
INSERT INTO `tb_farming_type` VALUES ('17', '释放授粉蜜蜂', '0', null, null, null, null, null, '3');
INSERT INTO `tb_farming_type` VALUES ('18', '闷棚', '0', null, null, null, null, null, '3');





-- ----------------------------
-- Table structure for tb_crop_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_crop_category`;
CREATE TABLE `tb_crop_category` (
  `crop_category_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `category_name` varchar(32) DEFAULT NULL COMMENT '作物名称',
  `category_status` tinyint(4) DEFAULT '0' COMMENT '作物状态',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `farm_id` varchar(32) DEFAULT NULL COMMENT '农场id',
  `sample_picture` varchar(128) DEFAULT NULL COMMENT '农作物样本图片地址',
  PRIMARY KEY (`crop_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=194 DEFAULT CHARSET=utf8 COMMENT='农作物种类表';




INSERT INTO `tb_crop_category` VALUES ('1', '敖汉荞麦', '0', null, null, null, null, null, 'crops/aohanqiaomai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('2', '芭蕉芋', '0', null, null, null, null, null, 'crops/bajiaoyu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('3', '八角金盘', '0', null, null, null, null, null, 'crops/bajiaojinpan_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('4', '白苞蒿', '0', null, null, null, null, null, 'crops/baibaohao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('5', '白扁豆', '0', null, null, null, null, null, 'crops/baibiandou_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('6', '白草莓', '0', null, null, null, null, null, 'crops/baicaomei_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('7', '白凤菜', '0', null, null, null, null, null, 'crops/baifengcai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('8', '白花蛇草', '0', null, null, null, null, null, 'crops/baihuashecao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('9', '白鸡冠', '0', null, null, null, null, null, 'crops/baijiguan_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('10', '白兰瓜', '0', null, null, null, null, null, 'crops/bailangua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('11', '白梨', '0', null, null, null, null, null, 'crops/baili_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('12', '白灵菇', '0', null, null, null, null, null, 'crops/bailinggu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('13', '白萝卜', '0', null, null, null, null, null, 'crops/bailuobo_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('14', '白落葵', '0', null, null, null, null, null, 'crops/bailuokui_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('15', '白皮胡葱', '0', null, null, null, null, null, 'crops/baipihucong_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('16', '白皮松', '0', null, null, null, null, null, 'crops/baipisong_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('17', '白皮蒜', '0', null, null, null, null, null, 'crops/baipisuan_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('18', '白皮西瓜', '0', null, null, null, null, null, 'crops/baipixigua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('19', '白葡萄', '0', null, null, null, null, null, 'crops/baiputao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('20', '白芍', '0', null, null, null, null, null, 'crops/baishao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('21', '白术', '0', null, null, null, null, null, 'crops/baishu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('22', '白桃', '0', null, null, null, null, null, 'crops/baitao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('23', '白芜青', '0', null, null, null, null, null, 'crops/baiwuqing_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('24', '白芽奇兰', '0', null, null, null, null, null, 'crops/baiyaqilan_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('25', '白玉菇', '0', null, null, null, null, null, 'crops/baiyugu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('26', '白芋', '0', null, null, null, null, null, 'crops/baiyu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('27', '白掌', '0', null, null, null, null, null, 'crops/baizhang_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('28', '白芝麻', '0', null, null, null, null, null, 'crops/baizhima_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('29', '白芨', '0', null, null, null, null, null, 'crops/baiji_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('30', '白芷', '0', null, null, null, null, null, 'crops/baizhi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('31', '百合', '0', null, null, null, null, null, 'crops/baihe_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('32', '百里香', '0', null, null, null, null, null, 'crops/bailixiang_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('33', '百香果', '0', null, null, null, null, null, 'crops/baixiangguo_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('34', '板蓝根', '0', null, null, null, null, null, 'crops/banlangen_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('35', '板栗', '0', null, null, null, null, null, 'crops/banli_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('36', '半夏', '0', null, null, null, null, null, 'crops/banxia_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('37', '包菜', '0', null, null, null, null, null, 'crops/baocai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('38', '包心芥', '0', null, null, null, null, null, 'crops/baoxinjie_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('39', '薄荷', '0', null, null, null, null, null, 'crops/bohe_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('40', '报子芥', '0', null, null, null, null, null, 'crops/baozijie_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('41', '蓖麻', '0', null, null, null, null, null, 'crops/bimayou_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('42', '扁叶葱', '0', null, null, null, null, null, 'crops/bianyecong_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('43', '菠菜', '0', null, null, null, null, null, 'crops/bocai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('44', '菠萝', '0', null, null, null, null, null, 'crops/boluo_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('45', '菜豆', '0', null, null, null, null, null, 'crops/caidou_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('46', '菜苔', '0', null, null, null, null, null, 'crops/caitai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('47', '蚕豆', '0', null, null, null, null, null, 'crops/candou_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('48', '藏红花', '0', null, null, null, null, null, 'crops/zanghonghua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('49', '草果', '0', null, null, null, null, null, 'crops/caoguo_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('50', '草坪苗', '0', null, null, null, null, null, 'crops/caopingmiao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('51', '草石蚕', '0', null, null, null, null, null, 'crops/caoshican_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('52', '草莓', '0', null, null, null, null, null, 'crops/caomei_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('53', '草莓苗', '0', null, null, null, null, null, 'crops/caomeimiao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('54', '侧柏', '0', null, null, null, null, null, 'crops/cebai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('55', '茶花', '0', null, null, null, null, null, 'crops/chahua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('56', '茶树菇', '0', null, null, null, null, null, 'crops/chashugu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('57', '茶叶', '0', null, null, null, null, null, 'crops/chaye_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('58', '柴胡', '0', null, null, null, null, null, 'crops/chaihu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('59', '朝天椒', '0', null, null, null, null, null, 'crops/chaotianjiao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('60', '车前子', '0', null, null, null, null, null, 'crops/cheqianzi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('61', '沉香木', '0', null, null, null, null, null, 'crops/chenxiangmu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('62', '橙子', '0', null, null, null, null, null, 'crops/chengzi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('63', '翅果油树', '0', null, null, null, null, null, 'crops/chiguoyoushu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('64', '虫草花', '0', null, null, null, null, null, 'crops/chongcaohua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('65', '丑橘', '0', null, null, null, null, null, 'crops/choubaguai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('66', '川贝', '0', null, null, null, null, null, 'crops/chuanbei_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('67', '穿心莲', '0', null, null, null, null, null, 'crops/chuanxinlian_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('68', '垂丝海棠', '0', null, null, null, null, null, 'crops/chuisihaitang_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('69', '慈姑', '0', null, null, null, null, null, 'crops/cigu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('70', '刺菜', '0', null, null, null, null, null, 'crops/cicai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('71', '大白菜', '0', null, null, null, null, null, 'crops/dabaicai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('72', '大黄', '0', null, null, null, null, null, 'crops/dahuang_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('73', '大桃', '0', null, null, null, null, null, 'crops/datao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('74', '大头菜', '0', null, null, null, null, null, 'crops/datoucai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('75', '大叶香菜', '0', null, null, null, null, null, 'crops/dayexiangcai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('76', '大叶榕', '0', null, null, null, null, null, 'crops/dayerong_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('77', '大茴香', '0', null, null, null, null, null, 'crops/dahuixiang_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('78', '丹参', '0', null, null, null, null, null, 'crops/danshen_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('79', '丹桂', '0', null, null, null, null, null, 'crops/dangui_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('80', '当归', '0', null, null, null, null, null, 'crops/danggui_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('81', '党参', '0', null, null, null, null, null, 'crops/dangshen_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('82', '刀豆', '0', null, null, null, null, null, 'crops/daodou_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('83', '滴水观音', '0', null, null, null, null, null, 'crops/dishuiguanyin_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('84', '地肤子', '0', null, null, null, null, null, 'crops/difuzi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('85', '地黄', '0', null, null, null, null, null, 'crops/dihuang_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('86', '吊瓜', '0', null, null, null, null, null, 'crops/diaogua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('87', '东台西瓜', '0', null, null, null, null, null, 'crops/dongtaixigua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('88', '东兴红姑娘红薯', '0', null, null, null, null, null, 'crops/dongxinghongshu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('89', '冬瓜', '0', null, null, null, null, null, 'crops/donggua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('90', '冬寒菜', '0', null, null, null, null, null, 'crops/donghancai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('91', '豆瓣菜', '0', null, null, null, null, null, 'crops/doubancai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('92', '杜仲', '0', null, null, null, null, null, 'crops/duzhong_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('93', '发财树', '0', null, null, null, null, null, 'crops/facaishu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('94', '法香', '0', null, null, null, null, null, 'crops/faxiang_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('95', '番茄(西红柿)', '0', null, null, null, null, null, 'crops/xihongshi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('96', '番杏', '0', null, null, null, null, null, 'crops/fanxing_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('97', '防风', '0', null, null, null, null, null, 'crops/fangfeng_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('98', '仿野生铁皮石斛', '0', null, null, null, null, null, 'crops/fangshihu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('99', '粉葛', '0', null, null, null, null, null, 'crops/fenge_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('100', '丰水梨', '0', null, null, null, null, null, 'crops/fengshuili_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('101', '凤梨释迦', '0', null, null, null, null, null, 'crops/fenglishijia_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('102', '佛手瓜', '0', null, null, null, null, null, 'crops/foshougua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('103', '福州茉莉花茶', '0', null, null, null, null, null, 'crops/fuzhoumolihua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('104', '福州橄榄', '0', null, null, null, null, null, 'crops/fuzhouganlan_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('105', '富贵菜', '0', null, null, null, null, null, 'crops/fuguicai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('106', '盖菜', '0', null, null, null, null, null, 'crops/gaicai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('107', '甘草', '0', null, null, null, null, null, 'crops/gancao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('108', '甘蔗', '0', null, null, null, null, null, 'crops/ganzhe_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('109', '柑橘', '0', null, null, null, null, null, 'crops/ganju_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('110', '高粱', '0', null, null, null, null, null, 'crops/gaoliang_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('111', '高山榕', '0', null, null, null, null, null, 'crops/gaoshanrong_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('112', '贡菜', '0', null, null, null, null, null, 'crops/gongcai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('113', '贡菊', '0', null, null, null, null, null, 'crops/gongju_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('114', '瓜蒌', '0', null, null, null, null, null, 'crops/gualou_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('115', '广柑', '0', null, null, null, null, null, 'crops/guanggan_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('116', '桂花树', '0', null, null, null, null, null, 'crops/guihuashu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('117', '贵妃芒', '0', null, null, null, null, null, 'crops/guifeimang_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('118', '哈密瓜', '0', null, null, null, null, null, 'crops/hamigua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('119', '海菜花', '0', null, null, null, null, null, 'crops/haicaihua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('120', '海棠果', '0', null, null, null, null, null, 'crops/haitangguo_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('121', '海鲜菇', '0', null, null, null, null, null, 'crops/haixiangu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('122', '旱荷', '0', null, null, null, null, null, 'crops/hanhe_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('123', '荷花', '0', null, null, null, null, null, 'crops/lianhua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('124', '荷兰豆', '0', null, null, null, null, null, 'crops/helandou_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('125', '核桃', '0', null, null, null, null, null, 'crops/hetao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('126', '何首乌', '0', null, null, null, null, null, 'crops/heshouwu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('127', '黑虎掌菌', '0', null, null, null, null, null, 'crops/heihuzhangjun_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('128', '黑麦草', '0', null, null, null, null, null, 'crops/heimaicao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('129', '黑米', '0', null, null, null, null, null, 'crops/heimi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('130', '黑皮冬瓜', '0', null, null, null, null, null, 'crops/heipidonggua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('131', '黑小麦', '0', null, null, null, null, null, 'crops/heixiaomai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('132', '黑枣', '0', null, null, null, null, null, 'crops/heizao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('133', '黑籽南瓜', '0', null, null, null, null, null, 'crops/heizinangua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('134', '黑枸杞', '0', null, null, null, null, null, 'crops/heigouqi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('135', '红(紫)皮洋葱', '0', null, null, null, null, null, 'crops/hongpiyangcong_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('136', '红菜苔', '0', null, null, null, null, null, 'crops/hongcaitai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('137', '红菜头', '0', null, null, null, null, null, 'crops/hongcaitou_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('138', '红枫', '0', null, null, null, null, null, 'crops/hongfeng_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('139', '红菇娘', '0', null, null, null, null, null, 'crops/hongguniang_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('140', '红花草', '0', null, null, null, null, null, 'crops/honghuacao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('141', '红萝卜', '0', null, null, null, null, null, 'crops/hongluobo_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('142', '红落葵', '0', null, null, null, null, null, 'crops/hongluokui_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('143', '红毛丹', '0', null, null, null, null, null, 'crops/hongmaodan_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('144', '红米', '0', null, null, null, null, null, 'crops/hongmi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('145', '红奶油生菜', '0', null, null, null, null, null, 'crops/hongnaiyousheng_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('146', '红秋葵', '0', null, null, null, null, null, 'crops/hongqiukui_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('147', '红薯', '0', null, null, null, null, null, 'crops/hongshu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('148', '红薯叶', '0', null, null, null, null, null, 'crops/hongshuye_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('149', '红提', '0', null, null, null, null, null, 'crops/hongti_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('150', '红芜青', '0', null, null, null, null, null, 'crops/hongwuqing_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('151', '红香蕉苹果', '0', null, null, null, null, null, 'crops/hongxiangjiaopi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('152', '红小豆', '0', null, null, null, null, null, 'crops/hongxiaodou_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('153', '红肖梨', '0', null, null, null, null, null, 'crops/hongxiaoli_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('154', '红星', '0', null, null, null, null, null, 'crops/hongxing_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('155', '红叶李', '0', null, null, null, null, null, 'crops/hongyeli_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('156', '红枣', '0', null, null, null, null, null, 'crops/hongzao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('157', '红掌', '0', null, null, null, null, null, 'crops/hongzhang_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('158', '红苋菜', '0', null, null, null, null, null, 'crops/hongxiancai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('159', '红枇杷', '0', null, null, null, null, null, 'crops/hongpipa_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('160', '红枸杞', '0', null, null, null, null, null, 'crops/honggouqi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('161', '猴头菇', '0', null, null, null, null, null, 'crops/houtougu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('162', '厚朴', '0', null, null, null, null, null, 'crops/houpu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('163', '胡萝卜', '0', null, null, null, null, null, 'crops/huluobo_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('164', '胡麻', '0', null, null, null, null, null, 'crops/huma_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('165', '蝴蝶兰', '0', null, null, null, null, null, 'crops/hudielan_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('166', '花菜', '0', null, null, null, null, null, 'crops/huacai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('167', '花菇', '0', null, null, null, null, null, 'crops/huagu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('168', '花椒', '0', null, null, null, null, null, 'crops/huajiaofen_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('169', '花椒芽', '0', null, null, null, null, null, 'crops/huajiaoya_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('170', '花牛苹果', '0', null, null, null, null, null, 'crops/huaniupingguo_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('171', '花生', '0', null, null, null, null, null, 'crops/huasheng_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('172', '滑子菇', '0', null, null, null, null, null, 'crops/huazigu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('173', '怀地黄', '0', null, null, null, null, null, 'crops/huaidihuang_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('174', '黄柏', '0', null, null, null, null, null, 'crops/huangbai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('175', '黄旦', '0', null, null, null, null, null, 'crops/huangdan_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('176', '黄豆', '0', null, null, null, null, null, 'crops/huangdou_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('177', '黄豆芽', '0', null, null, null, null, null, 'crops/huangdouya_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('178', '黄葛树', '0', null, null, null, null, null, 'crops/huanggeshu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('179', '黄瓜', '0', null, null, null, null, null, 'crops/huanggua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('180', '黄花菜', '0', null, null, null, null, null, 'crops/huanghuacai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('181', '黄花倒水莲', '0', null, null, null, null, null, 'crops/huanghuashuilian_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('182', '黄花梨', '0', null, null, null, null, null, 'crops/huanghuali_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('183', '黄花苜蓿', '0', null, null, null, null, null, 'crops/huanghuamuxu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('184', '黄椒', '0', null, null, null, null, null, 'crops/huangjiao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('185', '黄金梨', '0', null, null, null, null, null, 'crops/huangjinli_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('186', '黄金芽', '0', null, null, null, null, null, 'crops/huangjinya_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('187', '黄精', '0', null, null, null, null, null, 'crops/huangjing_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('188', '黄韭蒿', '0', null, null, null, null, null, 'crops/huangjiuhao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('189', '黄玫瑰', '0', null, null, null, null, null, 'crops/huangmeigui_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('190', '黄皮洋葱', '0', null, null, null, null, null, 'crops/huangpiyangcong_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('191', '黄秋葵', '0', null, null, null, null, null, 'crops/huangqiukui_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('192', '黄桃', '0', null, null, null, null, null, 'crops/huangtao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('193', '黄香蕉苹果', '0', null, null, null, null, null, 'crops/huangxiangjiaoping_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('194', '黄心乌', '0', null, null, null, null, null, 'crops/huangxinwu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('195', '黄杏', '0', null, null, null, null, null, 'crops/huangxing_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('196', '黄芩', '0', null, null, null, null, null, 'crops/huangqin_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('197', '黄芪', '0', null, null, null, null, null, 'crops/huangqi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('198', '黄栀子', '0', null, null, null, null, null, 'crops/huangzhizi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('199', '皇菊', '0', null, null, null, null, null, 'crops/huangju_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('200', '灰枣', '0', null, null, null, null, null, 'crops/huizao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('201', '火龙果', '0', null, null, null, null, null, 'crops/huolongguo_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('202', '鸡蛋花', '0', null, null, null, null, null, 'crops/jidanhua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('203', '鸡腿菇', '0', null, null, null, null, null, 'crops/jituigu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('204', '鸡血藤', '0', null, null, null, null, null, 'crops/jixueteng_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('205', '鸡油菌', '0', null, null, null, null, null, 'crops/jiyoujun_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('206', '姬菇', '0', null, null, null, null, null, 'crops/jigu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('207', '尖把梨', '0', null, null, null, null, null, 'crops/jianbali_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('208', '尖柿子', '0', null, null, null, null, null, 'crops/jianshizi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('209', '尖叶苦荬菜', '0', null, null, null, null, null, 'crops/jianyekumai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('210', '姜', '0', null, null, null, null, null, 'crops/jiang_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('211', '将乐竹荪', '0', null, null, null, null, null, 'crops/jianglezhusun_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('212', '蕉柑', '0', null, null, null, null, null, 'crops/jiaogan_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('213', '绞股蓝', '0', null, null, null, null, null, 'crops/jiaogulan_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('214', '节瓜', '0', null, null, null, null, null, 'crops/jiegua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('215', '结球生菜', '0', null, null, null, null, null, 'crops/shengcai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('216', '芥蓝', '0', null, null, null, null, null, 'crops/gailan_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('217', '金福菇', '0', null, null, null, null, null, 'crops/jinfugu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('218', '金桔', '0', null, null, null, null, null, 'crops/jinji_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('219', '金牡丹', '0', null, null, null, null, null, 'crops/jinmudan_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('220', '金皮西葫芦', '0', null, null, null, null, null, 'crops/jinpixihulu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('221', '金线莲', '0', null, null, null, null, null, 'crops/jinxianlian_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('222', '金叶球榆', '0', null, null, null, null, null, 'crops/jinyeqiuyu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('223', '金叶甜菜', '0', null, null, null, null, null, 'crops/jinyetiancai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('224', '金银花', '0', null, null, null, null, null, 'crops/jinyinhua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('225', '金针菇', '0', null, null, null, null, null, 'crops/jinzhengu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('226', '荆芥', '0', null, null, null, null, null, 'crops/jingjie_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('227', '荆芥穗', '0', null, null, null, null, null, 'crops/jingjiesui_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('228', '京水菜', '0', null, null, null, null, null, 'crops/jinshuicai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('229', '京桃', '0', null, null, null, null, null, 'crops/jintao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('230', '韭菜', '0', null, null, null, null, null, 'crops/jiucai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('231', '韭黄', '0', null, null, null, null, null, 'crops/jiuhuang_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('232', '九仙桃', '0', null, null, null, null, null, 'crops/jiuxiantao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('233', '菊花', '0', null, null, null, null, null, 'crops/juhua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('234', '菊薯', '0', null, null, null, null, null, 'crops/jushu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('235', '菊芋', '0', null, null, null, null, null, 'crops/juyu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('236', '菊苣', '0', null, null, null, null, null, 'crops/juju_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('237', '卷心菜', '0', null, null, null, null, null, 'crops/juanxincai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('238', '决明子', '0', null, null, null, null, null, 'crops/juemingzi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('239', '菌种期银耳', '0', null, null, null, null, null, 'crops/junzhongyiner_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('240', '骏枣', '0', null, null, null, null, null, 'crops/junzao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('241', '康乃馨', '0', null, null, null, null, null, 'crops/kangnaixin_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('242', '可可', '0', null, null, null, null, null, 'crops/keke_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('243', '空心菜', '0', null, null, null, null, null, 'crops/kongxincai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('244', '孔雀竹芋', '0', null, null, null, null, null, 'crops/kongquezhuyu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('245', '苦地丁', '0', null, null, null, null, null, 'crops/kudiding_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('246', '苦丁茶', '0', null, null, null, null, null, 'crops/kudingcha_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('247', '苦瓜', '0', null, null, null, null, null, 'crops/kugua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('248', '快菜', '0', null, null, null, null, null, 'crops/kuaicai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('249', '辣根', '0', null, null, null, null, null, 'crops/lagen_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('250', '辣椒', '0', null, null, null, null, null, 'crops/lajiao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('251', '辣椒瓜', '0', null, null, null, null, null, 'crops/lajiaogua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('252', '辣木', '0', null, null, null, null, null, 'crops/lamu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('253', '蓝花楹', '0', null, null, null, null, null, 'crops/lanhuaying_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('254', '蓝莓', '0', null, null, null, null, null, 'crops/lanmei_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('255', '兰花', '0', null, null, null, null, null, 'crops/lanhua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('256', '狼尾蕨', '0', null, null, null, null, null, 'crops/langweijue_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('257', '老人头菌', '0', null, null, null, null, null, 'crops/laorentoujun_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('258', '乐平雪梨瓜', '0', null, null, null, null, null, 'crops/lepingxueligua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('259', '梨枣', '0', null, null, null, null, null, 'crops/lizao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('260', '李子', '0', null, null, null, null, null, 'crops/lizi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('261', '荔浦芋', '0', null, null, null, null, null, 'crops/lipuyu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('262', '栗蘑', '0', null, null, null, null, null, 'crops/lishumo_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('263', '莲花', '0', null, null, null, null, null, 'crops/lianhua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('264', '莲藕', '0', null, null, null, null, null, 'crops/lianou_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('265', '莲雾', '0', null, null, null, null, null, 'crops/lianwu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('266', '莲子', '0', null, null, null, null, null, 'crops/lianzi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('267', '凉粉草', '0', null, null, null, null, null, 'crops/liangfencao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('268', '凉薯', '0', null, null, null, null, null, 'crops/liangshu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('269', '菱角', '0', null, null, null, null, null, 'crops/lingjiao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('270', '灵芝', '0', null, null, null, null, null, 'crops/lingzhi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('271', '榴莲', '0', null, null, null, null, null, 'crops/liulian_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('272', '柳树', '0', null, null, null, null, null, 'crops/liushu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('273', '龙柏', '0', null, null, null, null, null, 'crops/longbai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('274', '龙胆', '0', null, null, null, null, null, 'crops/longdan_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('275', '龙葵', '0', null, null, null, null, null, 'crops/longkui_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('276', '龙脑樟', '0', null, null, null, null, null, 'crops/longnaozhang_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('277', '龙眼(桂圆)', '0', null, null, null, null, null, 'crops/longyan_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('278', '篓蒿', '0', null, null, null, null, null, 'crops/louhao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('279', '芦柑', '0', null, null, null, null, null, 'crops/lugan_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('280', '芦笋', '0', null, null, null, null, null, 'crops/lusun_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('281', '芦荟', '0', null, null, null, null, null, 'crops/luhui_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('282', '陆河青梅', '0', null, null, null, null, null, 'crops/luheqingmei_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('283', '绿菜薹', '0', null, null, null, null, null, 'crops/lvcaitai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('284', '绿茶苗', '0', null, null, null, null, null, 'crops/lvchamiao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('285', '绿豆芽', '0', null, null, null, null, null, 'crops/lvdouya_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('286', '绿甘蓝', '0', null, null, null, null, null, 'crops/lvganlan_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('287', '萝卜芽', '0', null, null, null, null, null, 'crops/luoboya_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('288', '罗布麻叶', '0', null, null, null, null, null, 'crops/luobumaye_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('289', '罗汉松', '0', null, null, null, null, null, 'crops/luohansong_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('290', '罗勒', '0', null, null, null, null, null, 'crops/luole_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('291', '罗马花椰菜', '0', null, null, null, null, null, 'crops/luomahuayecai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('292', '落葵薯', '0', null, null, null, null, null, 'crops/luokuishu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('293', '洛宁金珠沙梨', '0', null, null, null, null, null, 'crops/luoningshali_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('294', '麻黄', '0', null, null, null, null, null, 'crops/mahuang_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('295', '马齿苋', '0', null, null, null, null, null, 'crops/machixian_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('296', '马兰', '0', null, null, null, null, null, 'crops/malan_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('297', '马铃薯', '0', null, null, null, null, null, 'crops/malinshu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('298', '马奶葡萄', '0', null, null, null, null, null, 'crops/manaiputao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('299', '马牙枣', '0', null, null, null, null, null, 'crops/mayazao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('300', '曼迪亚红豆杉', '0', null, null, null, null, null, 'crops/mandiyahongdoushan_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('301', '芒果', '0', null, null, null, null, null, 'crops/mangguo_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('302', '毛豆', '0', null, null, null, null, null, 'crops/maodou_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('303', '毛毛菜', '0', null, null, null, null, null, 'crops/maomaocai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('304', '玫瑰', '0', null, null, null, null, null, 'crops/meigui_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('305', '美人蕉', '0', null, null, null, null, null, 'crops/meirenjiao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('306', '糜子', '0', null, null, null, null, null, 'crops/mizi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('307', '迷迭香', '0', null, null, null, null, null, 'crops/midiexiang_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('308', '棉花', '0', null, null, null, null, null, 'crops/mianhua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('309', '蘑菇', '0', null, null, null, null, null, 'crops/mogu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('310', '魔芋', '0', null, null, null, null, null, 'crops/moyu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('311', '牡丹皮', '0', null, null, null, null, null, 'crops/mudanpi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('312', '牡荆叶', '0', null, null, null, null, null, 'crops/mujinye_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('313', '木耳', '0', null, null, null, null, null, 'crops/muer_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('314', '木耳菜', '0', null, null, null, null, null, 'crops/muercai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('315', '木瓜', '0', null, null, null, null, null, 'crops/mugua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('316', '木芙蓉', '0', null, null, null, null, null, 'crops/mufurong_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('317', '奶白菜', '0', null, null, null, null, null, 'crops/naibaicai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('318', '南瓜', '0', null, null, null, null, null, 'crops/nangua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('319', '南果梨', '0', null, null, null, null, null, 'crops/nanguoli_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('320', '南姜', '0', null, null, null, null, null, 'crops/nanjiang_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('321', '南蛇藤', '0', null, null, null, null, null, 'crops/nansheteng_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('322', '闹羊花', '0', null, null, null, null, null, 'crops/naoyanghua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('323', '鸟巢蕨', '0', null, null, null, null, null, 'crops/niaochaojue_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('324', '柠檬', '0', null, null, null, null, null, 'crops/ningmeng_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('325', '柠檬香茅', '0', null, null, null, null, null, 'crops/ningmengxiangmao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('326', '柠檬叶', '0', null, null, null, null, null, 'crops/ningmengye_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('327', '牛肝菌', '0', null, null, null, null, null, 'crops/niuganjun_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('328', '牛家营子北沙参', '0', null, null, null, null, null, 'crops/niujiayingshashen_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('329', '牛皮菜', '0', null, null, null, null, null, 'crops/niupicai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('330', '牛膝', '0', null, null, null, null, null, 'crops/niuxi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('331', '牛蒡', '0', null, null, null, null, null, 'crops/niubang_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('332', '排草', '0', null, null, null, null, null, 'crops/paicao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('333', '啤梨', '0', null, null, null, null, null, 'crops/pili_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('334', '苹果', '0', null, null, null, null, null, 'crops/pingguo_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('335', '苹果梨', '0', null, null, null, null, null, 'crops/pingguoli_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('336', '平贝', '0', null, null, null, null, null, 'crops/pingbei_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('337', '平菇', '0', null, null, null, null, null, 'crops/pinggu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('338', '婆枣', '0', null, null, null, null, null, 'crops/pozao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('339', '葡萄', '0', null, null, null, null, null, 'crops/putao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('340', '蒲公英', '0', null, null, null, null, null, 'crops/pugongying_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('341', '奇丹', '0', null, null, null, null, null, 'crops/qidan_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('342', '脐橙', '0', null, null, null, null, null, 'crops/qicheng_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('343', '茄子', '0', null, null, null, null, null, 'crops/qiezi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('344', '芹菜', '0', null, null, null, null, null, 'crops/qincai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('345', '青扁豆', '0', null, null, null, null, null, 'crops/qingbiandou_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('346', '青菜', '0', null, null, null, null, null, 'crops/qingcai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('347', '青花菜', '0', null, null, null, null, null, 'crops/qinghuacai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('348', '青芒', '0', null, null, null, null, null, 'crops/qingmang_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('349', '青木香', '0', null, null, null, null, null, 'crops/qingmuxiang_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('350', '青钱柳', '0', null, null, null, null, null, 'crops/qingqianliu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('351', '青芹', '0', null, null, null, null, null, 'crops/qingqin_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('352', '青葙', '0', null, null, null, null, null, 'crops/qingxiang_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('353', '青橘', '0', null, null, null, null, null, 'crops/qingju_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('354', '秋黄瓜', '0', null, null, null, null, null, 'crops/qiuhuanggua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('355', '球茎茴香', '0', null, null, null, null, null, 'crops/qiujinghuixiang_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('356', '曲麻菜', '0', null, null, null, null, null, 'crops/qumacai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('357', '雀舌', '0', null, null, null, null, null, 'crops/queshe_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('358', '人参果', '0', null, null, null, null, null, 'crops/renshenguo_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('359', '人参叶', '0', null, null, null, null, null, 'crops/renshenye_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('360', '肉桂', '0', null, null, null, null, null, 'crops/rougui_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('361', '洒金柏', '0', null, null, null, null, null, 'crops/sajinbo_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('362', '三华李', '0', null, null, null, null, null, 'crops/sanhuali_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('363', '三角梅', '0', null, null, null, null, null, 'crops/sanjiaomei_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('364', '三色堇', '0', null, null, null, null, null, 'crops/sansejin_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('365', '三叶草', '0', null, null, null, null, null, 'crops/sanyecao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('366', '桑葚', '0', null, null, null, null, null, 'crops/sangshen_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('367', '沙芥', '0', null, null, null, null, null, 'crops/shajie_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('368', '沙梨', '0', null, null, null, null, null, 'crops/shali_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('369', '沙糖桔', '0', null, null, null, null, null, 'crops/shatangju_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('370', '山茶树', '0', null, null, null, null, null, 'crops/shanchashu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('371', '山药', '0', null, null, null, null, null, 'crops/shanyao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('372', '山药豆', '0', null, null, null, null, null, 'crops/shanyaodou_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('373', '山竹', '0', null, null, null, null, null, 'crops/shanzhu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('374', '山茱萸', '0', null, null, null, null, null, 'crops/shanzhuyu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('375', '山柰', '0', null, null, null, null, null, 'crops/shannai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('376', '山楂', '0', null, null, null, null, null, 'crops/shanzha_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('377', '少花龙葵', '0', null, null, null, null, null, 'crops/shaohualongkui_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('378', '蛇瓜', '0', null, null, null, null, null, 'crops/shegua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('379', '蛇果', '0', null, null, null, null, null, 'crops/sheguo_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('380', '生菜', '0', null, null, null, null, null, 'crops/shengcai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('381', '圣女果', '0', null, null, null, null, null, 'crops/shengnvguo_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('382', '石榴', '0', null, null, null, null, null, 'crops/shiliu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('383', '食用百合', '0', null, null, null, null, null, 'crops/shiyongbaihe_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('384', '食用菊花', '0', null, null, null, null, null, 'crops/shiyongjuhua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('385', '柿子', '0', null, null, null, null, null, 'crops/shizi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('386', '手指胡萝卜', '0', null, null, null, null, null, 'crops/shouzhiluobo_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('387', '树葡萄', '0', null, null, null, null, null, 'crops/shuputao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('388', '树薯', '0', null, null, null, null, null, 'crops/shushu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('389', '树莓', '0', null, null, null, null, null, 'crops/shumei_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('390', '藠头', '0', null, null, null, null, null, 'crops/jiaotou_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('391', '双孢菇', '0', null, null, null, null, null, 'crops/shuangbaogu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('392', '水稻', '0', null, null, null, null, null, 'crops/shuidao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('393', '水东芥菜', '0', null, null, null, null, null, 'crops/shuidongjiecai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('394', '水果黄瓜', '0', null, null, null, null, null, 'crops/shuiguohuanggua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('395', '水果萝卜', '0', null, null, null, null, null, 'crops/shuiguoluobo_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('396', '水晶冰菜', '0', null, null, null, null, null, 'crops/shuijingbingcai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('397', '水蜜桃', '0', null, null, null, null, null, 'crops/shuimitao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('398', '水杉', '0', null, null, null, null, null, 'crops/shuishan_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('399', '水仙', '0', null, null, null, null, null, 'crops/shuixian_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('400', '睡莲', '0', null, null, null, null, null, 'crops/shuilian_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('401', '丝瓜', '0', null, null, null, null, null, 'crops/sigua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('402', '四季豆', '0', null, null, null, null, null, 'crops/sijidou_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('403', '四季桂', '0', null, null, null, null, null, 'crops/sijigui_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('404', '四棱豆', '0', null, null, null, null, null, 'crops/silingdou_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('405', '松柳苗', '0', null, null, null, null, null, 'crops/songliumiao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('406', '酥梨', '0', null, null, null, null, null, 'crops/suli_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('407', '蒜黄', '0', null, null, null, null, null, 'crops/suanhuang_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('408', '蒜苗', '0', null, null, null, null, null, 'crops/suanmiao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('409', '碎叶苦苣', '0', null, null, null, null, null, 'crops/suiyekuju_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('410', '胎菊', '0', null, null, null, null, null, 'crops/taiju2_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('411', '糖梨', '0', null, null, null, null, null, 'crops/tangli_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('412', '桃枝', '0', null, null, null, null, null, 'crops/taozhi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('413', '桃子', '0', null, null, null, null, null, 'crops/taozi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('414', '天南星', '0', null, null, null, null, null, 'crops/tiannanxing_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('415', '天竺桂', '0', null, null, null, null, null, 'crops/tianzhugui_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('416', '甜菜', '0', null, null, null, null, null, 'crops/tiancai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('417', '甜牛至', '0', null, null, null, null, null, 'crops/tianniuzhi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('418', '铁皮石斛', '0', null, null, null, null, null, 'crops/tiepishihu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('419', '土人参', '0', null, null, null, null, null, 'crops/turenshen_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('420', '土三七', '0', null, null, null, null, null, 'crops/tusanqi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('421', '土栾儿', '0', null, null, null, null, null, 'crops/tuluaner_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('422', '娃娃菜', '0', null, null, null, null, null, 'crops/wawacai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('423', '豌豆', '0', null, null, null, null, null, 'crops/wandou_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('424', '豌豆苗', '0', null, null, null, null, null, 'crops/wandoumiao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('425', '网纹瓜', '0', null, null, null, null, null, 'crops/wangwengua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('426', '乌龙茶树', '0', null, null, null, null, null, 'crops/wulongchashu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('427', '乌塌菜', '0', null, null, null, null, null, 'crops/wutacai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('428', '无花果', '0', null, null, null, null, null, 'crops/wuhuaguo_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('429', '芜湖椒', '0', null, null, null, null, null, 'crops/wuhujiao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('430', '吴茱萸', '0', null, null, null, null, null, 'crops/wuzhuyu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('431', '五味子', '0', null, null, null, null, null, 'crops/wuweizi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('432', '西瓜', '0', null, null, null, null, null, 'crops/xigua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('433', '西葫芦', '0', null, null, null, null, null, 'crops/xihulu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('434', '西兰花', '0', null, null, null, null, null, 'crops/xilanhua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('435', '硒砂瓜', '0', null, null, null, null, null, 'crops/xishagua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('436', '细辛', '0', null, null, null, null, null, 'crops/xixin_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('437', '仙人掌', '0', null, null, null, null, null, 'crops/xianrenzhang_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('438', '线椒', '0', null, null, null, null, null, 'crops/xianjiao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('439', '线辣椒', '0', null, null, null, null, null, 'crops/xianlajiao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('440', '香椿', '0', null, null, null, null, null, 'crops/xiangchun_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('441', '香葱', '0', null, null, null, null, null, 'crops/xiangcong_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('442', '香菇', '0', null, null, null, null, null, 'crops/xianggu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('443', '香瓜', '0', null, null, null, null, null, 'crops/xianggua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('444', '香蕉', '0', null, null, null, null, null, 'crops/xiangjiao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('445', '香蒲', '0', null, null, null, null, null, 'crops/xiangpu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('446', '橡胶树', '0', null, null, null, null, null, 'crops/xiangjiaoshu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('447', '向日葵', '0', null, null, null, null, null, 'crops/xiangrikui_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('448', '小白菜', '0', null, null, null, null, null, 'crops/xiaobaicai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('449', '小冬瓜', '0', null, null, null, null, null, 'crops/xiaodonggua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('450', '小黄瓜', '0', null, null, null, null, null, 'crops/xiaohuanggua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('451', '小麦', '0', null, null, null, null, null, 'crops/xiaomai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('452', '小米', '0', null, null, null, null, null, 'crops/xiaomi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('453', '小南瓜', '0', null, null, null, null, null, 'crops/xiaonangua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('454', '小松菜', '0', null, null, null, null, null, 'crops/xiaosongcai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('455', '小西瓜', '0', null, null, null, null, null, 'crops/xiaoxigua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('456', '小洋葱', '0', null, null, null, null, null, 'crops/xiaoyangcong_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('457', '小洋葱头', '0', null, null, null, null, null, 'crops/xiaoyangcongtou_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('458', '小叶紫薇', '0', null, null, null, null, null, 'crops/xiaoyeziwei_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('459', '小茴香', '0', null, null, null, null, null, 'crops/xiaohuixiang_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('460', '杏鲍菇', '0', null, null, null, null, null, 'crops/xingbaogu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('461', '袖珍菇', '0', null, null, null, null, null, 'crops/xiuzhengu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('462', '雪花梨', '0', null, null, null, null, null, 'crops/xuehuali_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('463', '雪梨', '0', null, null, null, null, null, 'crops/xueli_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('464', '雪松', '0', null, null, null, null, null, 'crops/xuesong_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('465', '勋章菊', '0', null, null, null, null, null, 'crops/xunzhangju_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('466', '鸭梨', '0', null, null, null, null, null, 'crops/yali_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('467', '鸭跖草', '0', null, null, null, null, null, 'crops/yazhicao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('468', '芽', '0', null, null, null, null, null, 'crops/ya_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('469', '烟叶', '0', null, null, null, null, null, 'crops/yanye_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('470', '延胡索', '0', null, null, null, null, null, 'crops/yanhusuo_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('471', '鹰嘴桃', '0', null, null, null, null, null, 'crops/yingzuitao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('472', '油菜', '0', null, null, null, null, null, 'crops/youcai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('473', '鱼腥草', '0', null, null, null, null, null, 'crops/yuxingcao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('474', '玉米', '0', null, null, null, null, null, 'crops/yumi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('475', '圆茄子', '0', null, null, null, null, null, 'crops/yuanqiezi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('476', '紫萝卜', '0', null, null, null, null, null, 'crops/ziluobo_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('477', '紫苏', '0', null, null, null, null, null, 'crops/zisu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('478', '伽师瓜', '0', null, null, null, null, null, 'crops/jiashigua_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('479', '芡实', '0', null, null, null, null, null, 'crops/qianshi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('480', '苜蓿芽', '0', null, null, null, null, null, 'crops/muxuya_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('481', '茼蒿', '0', null, null, null, null, null, 'crops/tonghao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('482', '荞麦', '0', null, null, null, null, null, 'crops/qiaomai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('483', '茯苓', '0', null, null, null, null, null, 'crops/fuling_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('484', '荠菜', '0', null, null, null, null, null, 'crops/jicai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('485', '茭白', '0', null, null, null, null, null, 'crops/jiaobai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('486', '荸荠', '0', null, null, null, null, null, 'crops/biqi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('487', '莳萝', '0', null, null, null, null, null, 'crops/shiluo_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('488', '莴笋', '0', null, null, null, null, null, 'crops/wosun_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('489', '莴苣', '0', null, null, null, null, null, 'crops/woju_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('490', '莼菜', '0', null, null, null, null, null, 'crops/chuncai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('491', '蕨菜', '0', null, null, null, null, null, 'crops/juecai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('492', '薤白', '0', null, null, null, null, null, 'crops/xiebai_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('493', '薰衣草', '0', null, null, null, null, null, 'crops/xunyicao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('494', '猕猴桃', '0', null, null, null, null, null, 'crops/mihoutao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('495', '枇杷', '0', null, null, null, null, null, 'crops/pipa_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('496', '楠木', '0', null, null, null, null, null, 'crops/nanmu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('497', '榕树', '0', null, null, null, null, null, 'crops/rongshu_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('498', '橘子', '0', null, null, null, null, null, 'crops/juzi_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('499', '砀山梨', '0', null, null, null, null, null, 'crops/dangshanli_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('500', '蟠桃', '0', null, null, null, null, null, 'crops/pantao_50x50.png');
INSERT INTO `tb_crop_category` VALUES ('501', '豇豆', '0', null, null, null, null, null, 'crops/jiangdou_50x50.png');


-- ----------------------------
-- Table structure for `tb_agricultural_machine`
-- ----------------------------
DROP TABLE IF EXISTS `tb_agricultural_machine`;
CREATE TABLE `tb_agricultural_machine` (
  `machine_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '农机ID',
  `farm_id` varchar(32) DEFAULT NULL COMMENT '农场ID',
  `machine_type_id` bigint(20) DEFAULT NULL COMMENT '农机类型ID',
  `name` varchar(32) DEFAULT NULL COMMENT '农机名称',
  `model` varchar(32) DEFAULT NULL COMMENT '农机型号',
  `price` decimal(8,2) DEFAULT NULL COMMENT '价格',
  `image` varchar(80) DEFAULT NULL COMMENT '图片',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`machine_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='农机表';

-- ----------------------------
-- Records of tb_agricultural_machine
-- ----------------------------
INSERT INTO `tb_agricultural_machine` VALUES ('1', null, null, '蓝色拖拉机', null, null, 'machine/blue-tractor_340x240.png', null, null);
INSERT INTO `tb_agricultural_machine` VALUES ('2', null, null, '绿色收割机', null, null, 'machine/green-combine_340x240.png', null, null);
INSERT INTO `tb_agricultural_machine` VALUES ('3', null, null, '绿色卡车', null, null, 'machine/green-quad-tractor_340x240.png', null, null);
INSERT INTO `tb_agricultural_machine` VALUES ('4', null, null, '绿色履带式拖拉机', null, null, 'machine/green-track-tractor_340x240.png', null, null);
INSERT INTO `tb_agricultural_machine` VALUES ('5', null, null, '绿色拖拉机', null, null, 'machine/green-tractor_340x240.png', null, null);
INSERT INTO `tb_agricultural_machine` VALUES ('6', null, null, '红色收割机', null, null, 'machine/red-combine_340x240.png', null, null);
INSERT INTO `tb_agricultural_machine` VALUES ('7', null, null, '红色拖拉机', null, null, 'machine/red-quad-tractor_340x240.png', null, null);
INSERT INTO `tb_agricultural_machine` VALUES ('8', null, null, '白卡车', null, null, 'machine/white-truck_340x240.png', null, null);
INSERT INTO `tb_agricultural_machine` VALUES ('9', null, null, '黄色收割机', null, null, 'machine/yellow-combine_340x240.png', null, null);
INSERT INTO `tb_agricultural_machine` VALUES ('10', null, null, '黄色履带式拖拉机', null, null, 'machine/yellow-track-tractor_340x240.png', null, null);


-- ----------------------------
-- Table structure for tb_irrigation_device_img
-- ----------------------------
DROP TABLE IF EXISTS `tb_irrigation_device_img`;
CREATE TABLE `tb_irrigation_device_img` (
  `img_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `img_url` varchar(128) DEFAULT NULL COMMENT '图片地址',
  `device_id` bigint(20) DEFAULT NULL COMMENT '灌溉设备id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `farm_id` varchar(32) DEFAULT NULL COMMENT '农场id,UUID',
  PRIMARY KEY (`img_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='灌溉设备图片表';

-- ----------------------------
-- Records of tb_irrigation_device_img
-- ----------------------------
INSERT INTO `tb_irrigation_device_img` VALUES ('1', 'irrigation/water_pump.png', null, null, null, null);
INSERT INTO `tb_irrigation_device_img` VALUES ('2', 'irrigation/electromagnetic_valve.png', null, null, null, null);