CREATE TABLE `boardreply` (
  `replyno` int NOT NULL AUTO_INCREMENT,
  `articleno` int DEFAULT NULL,
  `userid` varchar(20) DEFAULT NULL,
  `subject` varchar(100) DEFAULT NULL,
  `content` text,
  `replytime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`replyno`),
  KEY `articleno_idx` (`articleno`),
  CONSTRAINT `articleno` FOREIGN KEY (`articleno`) REFERENCES `board` (`articleno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci