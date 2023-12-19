CREATE TABLE `comment` (
  `comment_no` int NOT NULL AUTO_INCREMENT,
  `board_no` int NOT NULL,
  `parent_table` varchar(50) NOT NULL,
  `parent_no` int NOT NULL,
  `writer` varchar(100) NOT NULL,
  `content` text NOT NULL,
  `group_no` int DEFAULT '0',
  `super_no` int DEFAULT '0',
  `depth_no` int NOT NULL DEFAULT '0',
  `seq_no` int NOT NULL DEFAULT '0',
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `upd_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `sub_count` int DEFAULT '0',
  PRIMARY KEY (`comment_no`)
) COMMENT='댓글';
