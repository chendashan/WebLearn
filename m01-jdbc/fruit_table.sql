CREATE TABLE `t_fruit`
(
    `fid`    int(11) NOT NULL AUTO_INCREMENT,
    `fname`  varchar(20) NOT NULL,
    `price`  int(11) DEFAULT NULL,
    `fcount` int(11) DEFAULT NULL,
    `remark` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

insert into `t_fruit`(`fid`, `fname`, `price`, `fcount`, `remark`)
values (1, '红富士', 5, 16, '红富士也是苹果!'),
       (2, '西瓜', 3, 31, '西瓜很好吃'),
       (3, '香蕉', 4, 8, '水果真好吃'),
       (4, '菠萝', 5, 63, 'OK'),
       (5, '莲雾', 9, 99, '莲雾是一种神奇的水果'),
       (6, '羊角蜜', 4, 30, '羊角蜜是一种神奇的瓜'),
       (7, '猕猴桃', 13, 123, '猕猴桃是水果之王');