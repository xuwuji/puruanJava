
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


INSERT INTO customer VALUES ('1', 'zhangsan', '13912341111', 'aaa@qq.com', '2019-03-18 22:54:21');
INSERT INTO customer VALUES ('2', 'lisi', '13912342222', 'bbb@qq.com', '2019-03-19 22:55:08');
INSERT INTO customer VALUES ('3', 'wangwu', '13912343333', 'ccc@qq.com', '2019-03-21 22:55:47');

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL COMMENT '用户名称',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `age` int(10) NOT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性别',
  `address` varchar(256) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

INSERT INTO `user` VALUES (1, '王五', NULL, 10, 'm', NULL);
INSERT INTO `user` VALUES (10, '张三', '2014-07-10', 20, 'f', '北京市');
INSERT INTO `user` VALUES (16, '张小明', NULL, 30, 'm', '上海');
INSERT INTO `user` VALUES (22, '陈小明', NULL, 14, 'm', '北京');
INSERT INTO `user` VALUES (24, '张三丰', NULL, 18, 'm', '杭州');
INSERT INTO `user` VALUES (25, '陈小明', NULL, 8, 'm', '广州');
INSERT INTO `user` VALUES (26, '王五', NULL, 19, 'f', NULL);

