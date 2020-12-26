
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

