user表：

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
  `age` int DEFAULT NULL COMMENT '年龄',
  `sex` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户信息表';

jobdata表：

CREATE TABLE `jobdata` (
`id` int(255) NOT NULL AUTO_INCREMENT,
`position` varchar(255) DEFAULT NULL,
`company` varchar(255) DEFAULT NULL,
`salary` varchar(255) DEFAULT NULL,
`smid` double DEFAULT NULL,
`smax` double DEFAULT NULL,
`saverage` double DEFAULT NULL,
`time` date DEFAULT NULL,
`condition` varchar(255) DEFAULT NULL,
`region` varchar(255) DEFAULT NULL,
`adress` varchar(255) DEFAULT NULL,
`x` double DEFAULT NULL,
`y` double DEFAULT NULL,
`type` varchar(255) DEFAULT NULL,
`label` varchar(255) DEFAULT NULL,
`treat` varchar(255) DEFAULT NULL,
`scale` varchar(255) DEFAULT NULL,
`require` varchar(6000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
`introduce` varchar(6000) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4013 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;