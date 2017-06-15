/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : db_ttms

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-06-15 17:44:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_auth`
-- ----------------------------
DROP TABLE IF EXISTS `t_auth`;
CREATE TABLE `t_auth` (
  `authId` int(11) NOT NULL AUTO_INCREMENT,
  `authName` varchar(20) DEFAULT NULL,
  `authPath` varchar(50) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `authDescription` varchar(200) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  `iconCls` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`authId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_auth
-- ----------------------------
INSERT INTO `t_auth` VALUES ('1', '达塔系统', null, '-1', null, 'closed', 'icon-home');
INSERT INTO `t_auth` VALUES ('2', '权限管理', null, '1', null, 'closed', 'icon-permission');
INSERT INTO `t_auth` VALUES ('3', '系统管理', null, '1', null, 'closed', 'icon-student');
INSERT INTO `t_auth` VALUES ('4', '剧目管理', null, '1', null, 'closed', 'icon-course');
INSERT INTO `t_auth` VALUES ('5', '售票管理', null, '1', null, 'closed', 'icon-item');
INSERT INTO `t_auth` VALUES ('6', '用户管理', 'userManage.html', '2', null, 'open', 'icon-userManage');
INSERT INTO `t_auth` VALUES ('7', '角色管理', 'roleManage.html', '2', null, 'open', 'icon-roleManage');
INSERT INTO `t_auth` VALUES ('8', '菜单管理', 'menuManage.html', '2', null, 'open', 'icon-menuManage');
INSERT INTO `t_auth` VALUES ('9', '演出厅管理', 'hallManage.html', '3', null, 'open', 'icon-item');
INSERT INTO `t_auth` VALUES ('10', '会员管理', 'VIPManage.html', '3', null, 'open', 'icon-item');
INSERT INTO `t_auth` VALUES ('11', '剧目管理', 'movieManage.html', '4', null, 'open', 'icon-item');
INSERT INTO `t_auth` VALUES ('12', '演出管理', 'showManage.html', '4', null, 'open', 'icon-item');
INSERT INTO `t_auth` VALUES ('13', '售票管理', 'ticketManage.html', '5', null, 'open', 'icon-item');
INSERT INTO `t_auth` VALUES ('14', '销售管理', 'salesManage.html', '5', null, 'open', 'icon-item');
INSERT INTO `t_auth` VALUES ('15', '修改密码', null, '1', null, 'open', 'icon-modifyPassword');
INSERT INTO `t_auth` VALUES ('16', '安全退出', null, '1', null, 'open', 'icon-exit');

-- ----------------------------
-- Table structure for `t_class`
-- ----------------------------
DROP TABLE IF EXISTS `t_class`;
CREATE TABLE `t_class` (
  `classId` int(11) NOT NULL AUTO_INCREMENT,
  `className` varchar(20) DEFAULT NULL,
  `classDiscount` int(11) DEFAULT NULL,
  `classDescription` varchar(200) DEFAULT NULL,
  `classIsActive` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`classId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_class
-- ----------------------------
INSERT INTO `t_class` VALUES ('1', '青铜会员', '9', '可享受九折优惠', '1');
INSERT INTO `t_class` VALUES ('2', '白银会员', '8', '可享受八折优惠', '1');
INSERT INTO `t_class` VALUES ('3', '黄金会员', '7', '可享受七折优惠', '1');
INSERT INTO `t_class` VALUES ('4', '铂金会员', '6', '可享受六折优惠', '1');
INSERT INTO `t_class` VALUES ('5', '钻石会员', '5', '可享受五折优惠', '1');

-- ----------------------------
-- Table structure for `t_customer`
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
  `customerId` int(11) NOT NULL AUTO_INCREMENT,
  `customerName` varchar(20) DEFAULT NULL,
  `customerPassWord` varchar(20) DEFAULT NULL,
  `customerEmail` varchar(30) DEFAULT NULL,
  `customerMobile` varchar(15) DEFAULT NULL,
  `classId` int(11) DEFAULT NULL,
  PRIMARY KEY (`customerId`),
  KEY `classId` (`classId`),
  CONSTRAINT `t_customer_ibfk_1` FOREIGN KEY (`classId`) REFERENCES `t_class` (`classId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_customer
-- ----------------------------
INSERT INTO `t_customer` VALUES ('1', '吊炸天', '123456', '1054390839@qq.com', '18829071621', '1');
INSERT INTO `t_customer` VALUES ('2', '锤爆你狗头', '123456', '1140916254@qq.com', '18829071534', '2');
INSERT INTO `t_customer` VALUES ('3', '爸爸来收拾你了', '123456', 'freedomfanye@gmail.com', '13892771562', '3');
INSERT INTO `t_customer` VALUES ('4', '捶你胸口', '123456', '781562863@qq.com', '18816243303', '4');
INSERT INTO `t_customer` VALUES ('5', '大坏蛋', '123456', '1146975523@qq.com', '13892654892', '5');
INSERT INTO `t_customer` VALUES ('6', '叫爸爸', '123456', '1054390739@qq.com', '18829041635', '1');
INSERT INTO `t_customer` VALUES ('7', '追风666', '123456', '1054390739@qq.com', '18829071621', '1');

-- ----------------------------
-- Table structure for `t_hall`
-- ----------------------------
DROP TABLE IF EXISTS `t_hall`;
CREATE TABLE `t_hall` (
  `hallId` int(11) NOT NULL AUTO_INCREMENT,
  `hallName` varchar(30) DEFAULT NULL,
  `hallRow` int(4) DEFAULT NULL,
  `hallColumn` int(4) DEFAULT NULL,
  `hallDescription` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`hallId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_hall
-- ----------------------------
INSERT INTO `t_hall` VALUES ('1', '1号厅', '10', '10', '898');
INSERT INTO `t_hall` VALUES ('2', '2号厅', '20', '20', '巨幕银幕');
INSERT INTO `t_hall` VALUES ('3', '3号厅', '15', '15', '3D环绕音+巨幕银幕');
INSERT INTO `t_hall` VALUES ('4', '4号厅', '10', '10', '标配银幕');
INSERT INTO `t_hall` VALUES ('5', '5号厅', '20', '20', '巨幕银幕');
INSERT INTO `t_hall` VALUES ('6', '6号厅', '10', '10', '标配银幕');
INSERT INTO `t_hall` VALUES ('7', '7号厅', '15', '15', '至尊vip银幕');
INSERT INTO `t_hall` VALUES ('8', '8号厅', '10', '10', '标配银幕');

-- ----------------------------
-- Table structure for `t_movie`
-- ----------------------------
DROP TABLE IF EXISTS `t_movie`;
CREATE TABLE `t_movie` (
  `movieId` int(11) NOT NULL AUTO_INCREMENT,
  `movieName` varchar(30) DEFAULT NULL,
  `movieMainActor` varchar(100) DEFAULT NULL,
  `movieDirector` varchar(20) DEFAULT NULL,
  `movieDuration` int(4) DEFAULT NULL,
  `movieDescription` text,
  PRIMARY KEY (`movieId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_movie
-- ----------------------------
INSERT INTO `t_movie` VALUES ('1', '速度与激情8', '范·迪塞尔、道恩·强森、杰森·斯坦森', 'F·加里·格雷', '136', '多米尼克（范·迪塞尔 Vin Diesel 饰）与莱蒂（米歇尔·罗德里格兹 Michelle Rodriguez 饰）共度蜜月，布莱恩与米娅退出了赛车界，这支曾环游世界的顶级飞车家族队伍的生活正渐趋平淡。然而，一位神秘女子Cipher（查理兹·塞隆 Charlize T heron 饰）的出现，令整个队伍卷入信任与背叛的危机，面临前所未有的考验。');
INSERT INTO `t_movie` VALUES ('2', '傲娇偏见', '迪丽热巴  张云龙  高伟光', '李海蜀  黄彦威', '108', '北漂网络写手唐楠楠（迪丽热巴 饰）脑洞大于常人，整日穿行在各种“白日梦”之中，梦想着有一天能成为网文大神，然而在她误打误撞毁掉了贱萌海龟男朱侯（张云龙 饰）为追求女神精心布置的告白仪式之后，这对冤家阴差阳错之下被迫带着各自的傲娇与偏见开始了同居生活。朱侯的高富帅好基友萧见君（高伟光 饰）因对唐楠楠一见钟情展开猛烈攻势，却不料遭到朱侯再三阻挠。当“傲娇”任性撞上“偏见”无理，一场鸡飞狗跳的脱线恋情正在袭来，一次跨越银河系的世纪大和解即将上演。');
INSERT INTO `t_movie` VALUES ('3', '拆弹专家', '刘德华  姜武  宋佳  吴卓羲  姜皓文', '邱礼涛', '120', '章在山（刘德华 饰）是香港警队“爆炸品处理科”的一名高级督察。七年前，他潜伏到头号通缉犯火爆（姜武 饰）的犯罪团伙中，在一次打劫金库的行动中，章在山表露了其拆弹组卧底的身份，与警方里应外合，成功阻止炸弹引爆，并将火爆及其弟的犯罪组织一网打尽，可惜在千钧一发之间，火爆逃脱并扬言誓要报仇。复职后的章在山很快被晋升为警队的拆弹专家。七年后，香港接二连三遭遇炸弹恐怖袭击，警方更收到线报大批爆炸品已偷运入港，一切迹象显示香港将有大案发生。就在香港人心惶惶之际，城中最繁忙的红磡海底隧道被悍匪围堵拦截，数百名人质被胁持，终于现身的火爆威胁警方炸毁隧道。章在山唯有将火爆绳之于法，才能拆解这场反恐风暴背后的惊天阴谋。');
INSERT INTO `t_movie` VALUES ('4', '超凡战队', '戴克·蒙哥马利  娜奥米·斯科特  RJ·赛勒  林路迪  贝姬·戈麦斯', '迪恩·以色列特', '124', '五个在校园被孤立的高中生，因为青春的荷尔蒙面临着许许多多的烦恼与迷茫。同时，黑暗势力丽达女王正蠢蠢欲动，为了寻找更多能量打造金人怪兽哥达来到地球。五名青少年命中注定地相遇，团结克服难关成为五色超凡战队，与他们的恐龙佐德人机合一雷霆出击拯救世界，毁天灭地一触即发');
INSERT INTO `t_movie` VALUES ('5', '春娇救志明', '余文乐  杨千嬅  蒋梦婕  曾国祥  邵音音', '彭浩翔', '121', '2009年，春娇志明后巷邂逅，演绎烟火世界中的极致浪漫。2017年，饮食男女分分合合，志明仍未长大，春娇急需爱情救火。相恋多年的春娇与志明，渐渐从“可遇不可求”的惊涛骇浪变成了普通的情侣。面临“中女危机”的春娇，深感颜值危机，可志明却还如同孩子一般无法长大。不仅两人平日生活磕磕绊绊不断，让春娇更为担心的是，家里的神秘来访者Flora不仅年轻漂亮，神经大条，还要跟志明借精生子。八年爱情紧急SOS！世间红男绿女，且看春娇志明。“志明与春娇”系列第三部温暖回归，好久不见，好救不贱！');
INSERT INTO `t_movie` VALUES ('6', '记忆大师', '黄渤  徐静蕾  段奕宏  杨子姗  许玮甯', '陈正道', '119', '故事发生在2025年，因为和妻子张代晨（徐静蕾 饰）婚姻破裂，男主角江丰（黄渤 饰）走进记忆大师医疗中心接受手术，却不料手术失误记忆被错误重载，他莫名其妙变成了“杀人凶手”。警官沈汉强（段奕宏 饰）的穷追不舍让他逐渐发现，自己脑内的错误记忆不仅是破案的关键，更是救赎自己的唯一希望。与此同时，妻子身边出现的女人陈姗姗（杨子姗 饰）、记忆中浮现出的神秘女子（许玮甯 饰），似乎也和真相有着千丝万缕的联系，一场记忆烧脑战也随之开始。');
INSERT INTO `t_movie` VALUES ('7', '金刚：骷髅岛', '汤姆·希德勒斯顿  布丽·拉尔森  塞缪尔·杰克逊  约翰·古德曼  景甜', '乔丹·沃格特-罗伯茨', '119', '上世纪70年代，一支集结了科考队员、探险家、战地摄影记者、军人的探险队，冒险前往南太平洋上的神秘岛屿——骷髅岛。他们的到来惊扰了岛上之神——史上最大金刚。经过一番惨烈的激战之后，探险队员散落在了岛屿各处。此时，队员们才意识到这次探险并不是一次单纯的科考任务，而是去探索怪兽存在的证明。');
INSERT INTO `t_movie` VALUES ('8', '麻烦家族', '黄磊  海清  王迅  李立群  孙莉', '黄磊', '103', '一对老夫妇结婚50周年前夕，丈夫问妻子想要什么生日礼物，得到的答案却是“离婚协 议书”。老夫妻闹离婚的事件在家族里引起了轩然大波，家族中的每个人都借此事宣泄心中积攒的怨气，导致家里“乱成一团”。');
INSERT INTO `t_movie` VALUES ('9', '摔跤吧！爸爸', '阿米尔·汗  法缇玛·萨那·纱卡  桑亚·玛荷塔  阿帕尔夏克提·库拉那', '涅提·蒂瓦里', '169', '马哈维亚·辛格·珀尕（阿米尔·汗 Aamir Khan 饰）曾是印度国家摔跤冠军，因生活所迫放弃摔跤。他希望让儿子可以帮他完成梦想——赢得世界级金牌。结果生了四个女儿本以为梦想就此破碎的辛格却意外发现女儿身上的惊人天赋，看到冠军希望的他决定不能让女儿的天赋浪费，像其他女孩一样只能洗衣做饭过一生 ，再三考虑之后，与妻子约定一年时间按照摔跤手的标准训练两个女儿：换掉裙子 、剪掉了长发，让她们练习摔跤，并赢得一个又一个冠军，最终赢来了成为榜样激励千千万万女性的机会');
INSERT INTO `t_movie` VALUES ('10', '喜欢你', '金城武  周冬雨  孙艺洲  奚梦瑶  杨祐宁', '许宏宇', '106', '掌管跨国经济体的路晋（金城武 饰）刻薄挑剔，仅有美食这一个爱好。创意厨师顾胜男（周冬雨 饰）迷糊邋遢得过且过。一次收购，一道女巫汤，路晋喜欢上了顾胜男的菜却讨厌极了她这个人。喜欢还是讨厌究竟何去何从？');
INSERT INTO `t_movie` VALUES ('11', '亚瑟王：斗兽争霸', '查理·汉纳姆  裘德·洛  阿斯特丽德·伯格斯-弗瑞斯贝  米卡埃尔·佩斯布兰特', '盖·里奇', '126', '本片改编自亚瑟王圣剑的传说，剧情颠覆传统，一路探索亚瑟从市井到王座的征途。亚瑟的父亲在他小时候就惨遭杀害，亚瑟的叔叔伏提庚（裘德·洛 Jude Law 饰）篡取王位，剥夺了亚瑟（查理·汉纳姆 Charlie Hunnam 饰）的天赐之权。他浑然不知自己的身世，在城市的穷街陋巷里摸爬滚打着长大，但当他拔出石中剑，他的人生就彻底地天翻地覆了，亚瑟不得不接受自己真正的使命。');
INSERT INTO `t_movie` VALUES ('12', '银河护卫队2', '克里斯·普拉特  佐伊·索尔达娜  戴夫·巴蒂斯塔  范·迪塞尔  布莱德利·库珀', '詹姆斯·古恩', '136', '漫威影业最新力作《银河护卫队2》带着全新劲爆好听的“劲歌金曲第二辑”回归大银幕！银河护卫队在本集中穿越宇宙，继续外太空的史诗冒险之旅。他们必须共同作战，守护彼此；同时要解开“星爵”彼得·奎尔的身世之谜。旧日敌人变为盟友，漫画中深受喜爱的角色也会现身，对护卫队出手援助。漫威电影宇宙则将持续扩张，进入新纪元！');

-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `orderId` int(11) NOT NULL AUTO_INCREMENT,
  `seatId` int(11) DEFAULT NULL,
  `customerId` int(11) DEFAULT NULL,
  `scheduleId` int(11) DEFAULT NULL,
  `orderAdjustedPrice` float(5,2) DEFAULT NULL,
  `orderBuyDate` datetime DEFAULT NULL,
  PRIMARY KEY (`orderId`),
  KEY `customerId` (`customerId`),
  KEY `scheduleId` (`scheduleId`),
  KEY `seatId` (`seatId`),
  CONSTRAINT `t_order_ibfk_1` FOREIGN KEY (`customerId`) REFERENCES `t_customer` (`customerId`),
  CONSTRAINT `t_order_ibfk_2` FOREIGN KEY (`scheduleId`) REFERENCES `t_schedule` (`scheduleId`),
  CONSTRAINT `t_order_ibfk_3` FOREIGN KEY (`seatId`) REFERENCES `t_seat` (`seatId`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('26', '72', '4', '1', '16.00', '2017-06-09 11:23:13');
INSERT INTO `t_order` VALUES ('27', '73', '4', '1', '16.00', '2017-06-09 11:23:13');
INSERT INTO `t_order` VALUES ('28', '74', '4', '1', '16.00', '2017-06-09 11:23:23');
INSERT INTO `t_order` VALUES ('29', '75', '4', '1', '16.00', '2017-06-09 11:23:23');
INSERT INTO `t_order` VALUES ('30', '76', '5', '1', '16.00', '2017-06-09 11:24:00');
INSERT INTO `t_order` VALUES ('31', '77', '5', '1', '16.00', '2017-06-09 11:24:00');

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(20) DEFAULT NULL,
  `authIds` varchar(50) DEFAULT NULL,
  `roleDescription` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '超级管理员', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16', '具有最高权限');
INSERT INTO `t_role` VALUES ('2', '系统管理员', '1,3,9,10,15,16', '管理演出厅和会员用户');
INSERT INTO `t_role` VALUES ('3', '经理', '1,4,11,12,5,13,14,15,16', '有他该干的事');
INSERT INTO `t_role` VALUES ('4', '售票员', '1,5,13,14,15,16', '他只是个卖票的');

-- ----------------------------
-- Table structure for `t_schedule`
-- ----------------------------
DROP TABLE IF EXISTS `t_schedule`;
CREATE TABLE `t_schedule` (
  `scheduleId` int(11) NOT NULL AUTO_INCREMENT,
  `movieId` int(11) DEFAULT NULL,
  `hallId` int(11) DEFAULT NULL,
  `schedulePrice` float(5,2) DEFAULT NULL,
  `scheduleBeginDate` datetime DEFAULT NULL,
  PRIMARY KEY (`scheduleId`),
  KEY `movieId` (`movieId`),
  KEY `hallId` (`hallId`),
  CONSTRAINT `t_schedule_ibfk_1` FOREIGN KEY (`movieId`) REFERENCES `t_movie` (`movieId`),
  CONSTRAINT `t_schedule_ibfk_2` FOREIGN KEY (`hallId`) REFERENCES `t_hall` (`hallId`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_schedule
-- ----------------------------
INSERT INTO `t_schedule` VALUES ('1', '1', '1', '16.00', '2017-05-13 22:03:00');
INSERT INTO `t_schedule` VALUES ('2', '1', '2', '16.00', '2017-05-16 17:28:05');
INSERT INTO `t_schedule` VALUES ('3', '1', '3', '16.00', '2017-05-16 17:28:16');
INSERT INTO `t_schedule` VALUES ('4', '2', '4', '35.00', '2017-05-16 17:28:30');
INSERT INTO `t_schedule` VALUES ('5', '2', '5', '35.00', '2017-05-16 17:28:54');
INSERT INTO `t_schedule` VALUES ('6', '2', '6', '48.00', '2017-05-16 17:29:05');
INSERT INTO `t_schedule` VALUES ('7', '2', '7', '45.00', '2017-05-16 17:29:17');
INSERT INTO `t_schedule` VALUES ('8', '3', '8', '45.00', '2017-05-16 17:29:33');
INSERT INTO `t_schedule` VALUES ('9', '3', '4', '32.00', '2017-05-16 17:29:43');
INSERT INTO `t_schedule` VALUES ('10', '3', '8', '32.00', '2017-05-16 17:29:56');
INSERT INTO `t_schedule` VALUES ('11', '4', '1', '45.00', '2017-05-16 17:30:12');
INSERT INTO `t_schedule` VALUES ('12', '4', '2', '21.00', '2017-05-16 17:30:29');
INSERT INTO `t_schedule` VALUES ('13', '5', '6', '15.00', '2017-05-16 17:30:51');
INSERT INTO `t_schedule` VALUES ('14', '5', '2', '54.00', '2017-05-16 17:31:03');
INSERT INTO `t_schedule` VALUES ('15', '5', '6', '64.00', '2017-05-16 17:31:18');
INSERT INTO `t_schedule` VALUES ('16', '6', '4', '49.00', '2017-05-16 17:31:30');
INSERT INTO `t_schedule` VALUES ('17', '6', '5', '16.00', '2017-05-16 17:31:43');
INSERT INTO `t_schedule` VALUES ('18', '7', '5', '64.00', '2017-05-16 17:31:56');
INSERT INTO `t_schedule` VALUES ('19', '7', '7', '48.00', '2017-05-16 17:32:10');
INSERT INTO `t_schedule` VALUES ('20', '7', '5', '47.00', '2017-05-16 20:45:27');
INSERT INTO `t_schedule` VALUES ('21', '8', '1', '64.00', '2017-05-16 17:32:50');
INSERT INTO `t_schedule` VALUES ('22', '8', '2', '67.00', '2017-05-16 17:32:58');
INSERT INTO `t_schedule` VALUES ('23', '9', '6', '74.00', '2017-05-16 17:33:08');
INSERT INTO `t_schedule` VALUES ('24', '9', '4', '32.00', '2017-05-16 17:33:17');
INSERT INTO `t_schedule` VALUES ('25', '10', '6', '132.00', '2017-05-16 17:33:27');
INSERT INTO `t_schedule` VALUES ('26', '11', '3', '62.00', '2017-05-16 17:33:36');
INSERT INTO `t_schedule` VALUES ('27', '11', '4', '32.00', '2017-05-16 17:33:47');
INSERT INTO `t_schedule` VALUES ('28', '12', '5', '45.00', '2017-05-16 17:33:57');
INSERT INTO `t_schedule` VALUES ('29', '12', '4', '65.00', '2017-05-16 17:34:06');
INSERT INTO `t_schedule` VALUES ('40', '9', '8', '46.00', '2017-05-16 23:19:44');
INSERT INTO `t_schedule` VALUES ('41', '6', '8', '46.00', '2017-05-17 08:41:45');
INSERT INTO `t_schedule` VALUES ('42', '10', '7', '32.00', '2017-05-17 14:36:30');
INSERT INTO `t_schedule` VALUES ('43', '10', '8', '21.00', '2017-06-07 11:36:07');

-- ----------------------------
-- Table structure for `t_seat`
-- ----------------------------
DROP TABLE IF EXISTS `t_seat`;
CREATE TABLE `t_seat` (
  `seatId` int(11) NOT NULL AUTO_INCREMENT,
  `customerId` int(11) DEFAULT NULL,
  `scheduleId` int(11) DEFAULT NULL,
  `seatRow` int(4) DEFAULT NULL,
  `seatColumn` int(4) DEFAULT NULL,
  `seatIsActive` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`seatId`),
  KEY `scheduleId` (`scheduleId`),
  KEY `customerId` (`customerId`),
  CONSTRAINT `t_seat_ibfk_1` FOREIGN KEY (`scheduleId`) REFERENCES `t_schedule` (`scheduleId`),
  CONSTRAINT `t_seat_ibfk_2` FOREIGN KEY (`customerId`) REFERENCES `t_customer` (`customerId`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_seat
-- ----------------------------
INSERT INTO `t_seat` VALUES ('72', '4', '1', '2', '7', '1');
INSERT INTO `t_seat` VALUES ('73', '4', '1', '2', '8', '1');
INSERT INTO `t_seat` VALUES ('74', '4', '1', '3', '7', '1');
INSERT INTO `t_seat` VALUES ('75', '4', '1', '3', '8', '1');
INSERT INTO `t_seat` VALUES ('76', '5', '1', '2', '6', '1');
INSERT INTO `t_seat` VALUES ('77', '5', '1', '3', '6', '1');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `passWord` varchar(16) DEFAULT NULL,
  `userType` tinyint(4) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  `userDescription` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `t_user_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '123456', '1', '1', '我是爸爸');
INSERT INTO `t_user` VALUES ('6', 'jack', '123456', '2', '4', '售票员');
INSERT INTO `t_user` VALUES ('7', 'marry', '123456', '2', '2', '系统管理员');
INSERT INTO `t_user` VALUES ('8', 'rose', '123456', '2', '3', '经理');
