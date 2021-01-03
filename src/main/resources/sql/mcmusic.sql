/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : mcmusic

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 02/06/2019 22:31:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `adminId` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员编号，主键',
  `loginId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员密码',
  `adminName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员名称',
  `userType` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`adminId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, '17860541931', '1999420114', '王明', 0);

-- ----------------------------
-- Table structure for area
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area`  (
  `areaId` int(11) NOT NULL AUTO_INCREMENT COMMENT '地区编号，主键',
  `areaName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地区名称',
  PRIMARY KEY (`areaId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '地区' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of area
-- ----------------------------
INSERT INTO `area` VALUES (1, '中国');
INSERT INTO `area` VALUES (2, '美国');
INSERT INTO `area` VALUES (3, '日本');

-- ----------------------------
-- Table structure for cd
-- ----------------------------
DROP TABLE IF EXISTS `cd`;
CREATE TABLE `cd`  (
  `CDId` int(11) NOT NULL AUTO_INCREMENT COMMENT '专辑编号，主键',
  `CDName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专辑名称',
  `coverUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专辑封面图片',
  `songCount` int(11) NOT NULL DEFAULT 0 COMMENT '歌曲数量',
  `publishDate` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `singerId` int(11) NOT NULL COMMENT '歌手编号，外键',
  `introduce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专辑简介',
  `collectionCount` int(11) NOT NULL DEFAULT 0 COMMENT '收藏次数',
  PRIMARY KEY (`CDId`) USING BTREE,
  INDEX `cd_fk_singerId`(`singerId`) USING BTREE,
  CONSTRAINT `cd_ibfk_1` FOREIGN KEY (`singerId`) REFERENCES `singer` (`singerId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '专辑表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cd
-- ----------------------------
INSERT INTO `cd` VALUES (1, '百鸟朝凤', '无', 5, '2019-04-25 14:01:46', 1, '非常有名，听的人非常多', 0);
INSERT INTO `cd` VALUES (2, '百鸟朝凤', '无', 5, '2019-04-25 14:02:21', 2, '非常有名，听的人非常多', 0);
INSERT INTO `cd` VALUES (3, '百鸟朝凤', '无', 5, '2019-04-25 14:02:21', 3, '非常有名，听的人非常多', 0);
INSERT INTO `cd` VALUES (4, '百鸟朝凤', '无', 5, '2019-04-25 14:02:21', 4, '非常有名，听的人非常多', 0);
INSERT INTO `cd` VALUES (5, '百鸟朝凤', '无', 5, '2019-04-25 14:02:21', 5, '非常有名，听的人非常多', 0);
INSERT INTO `cd` VALUES (6, '百鸟朝凤', '无', 5, '2019-04-25 14:02:21', 6, '非常有名，听的人非常多', 0);
INSERT INTO `cd` VALUES (7, '百鸟朝凤', '无', 5, '2019-04-25 14:02:21', 7, '非常有名，听的人非常多', 0);
INSERT INTO `cd` VALUES (8, '百鸟朝凤', '无', 5, '2019-04-25 14:02:21', 8, '非常有名，听的人非常多', 0);
INSERT INTO `cd` VALUES (9, '百鸟朝凤', '无', 5, '2019-04-25 14:02:21', 9, '非常有名，听的人非常多', 0);
INSERT INTO `cd` VALUES (10, '百鸟朝凤', '无', 5, '2019-04-25 14:02:21', 10, '非常有名，听的人非常多', 0);

-- ----------------------------
-- Table structure for commentreplymessage
-- ----------------------------
DROP TABLE IF EXISTS `commentreplymessage`;
CREATE TABLE `commentreplymessage`  (
  `replyMsgId` int(11) NOT NULL AUTO_INCREMENT COMMENT '回复编号，主键',
  `uCommentId` int(11) NOT NULL COMMENT '用户评论编号，外键',
  `userId` int(11) NOT NULL COMMENT '用户编号，外键',
  `replyMessage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回复内容',
  `replyDate` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`replyMsgId`) USING BTREE,
  INDEX `commentreply_fk_uCommentId`(`uCommentId`) USING BTREE,
  INDEX `commentreply_fk_userId`(`userId`) USING BTREE,
  CONSTRAINT `commentreplymessage_ibfk_1` FOREIGN KEY (`uCommentId`) REFERENCES `usersongcomment` (`uCommentId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `commentreplymessage_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评论信息回复表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for singer
-- ----------------------------
DROP TABLE IF EXISTS `singer`;
CREATE TABLE `singer`  (
  `singerId` int(11) NOT NULL AUTO_INCREMENT COMMENT '歌手编号，主键',
  `singerName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '歌手名字',
  `accessCount` int(11) NOT NULL DEFAULT 0 COMMENT '热度，访问次数，默认0',
  `collectionCount` int(11) NOT NULL DEFAULT 0 COMMENT '收藏次数，默认0',
  `areaId` int(11) NOT NULL COMMENT '地区编号，外键',
  `introduce` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '歌手简介',
  `birthday` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `singerSex` tinyint(4) NOT NULL DEFAULT 0 COMMENT '歌手性别',
  `photoUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `debutDate` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`singerId`) USING BTREE,
  INDEX `singer_fk_areaId`(`areaId`) USING BTREE,
  CONSTRAINT `singer_ibfk_1` FOREIGN KEY (`areaId`) REFERENCES `area` (`areaId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '歌手表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of singer
-- ----------------------------
INSERT INTO `singer` VALUES (1, '王明', 0, 0, 1, '内地知名歌手', '1980-3-20 22:15:30', 0, '没有图片', '2019-04-25 13:50:33');
INSERT INTO `singer` VALUES (2, '张明', 0, 0, 1, '内地知名歌手', '1980-3-20 22:15:30', 1, '没有图片', '2019-04-25 13:54:09');
INSERT INTO `singer` VALUES (3, '张三', 0, 0, 2, '内地知名歌手', '1980-3-20 22:15:30', 0, '没有图片', '2019-04-25 13:54:09');
INSERT INTO `singer` VALUES (4, '梨花', 0, 0, 2, '内地知名歌手', '1980-3-20 22:15:30', 1, '没有图片', '2019-04-25 13:54:09');
INSERT INTO `singer` VALUES (5, '三月风', 0, 0, 3, '内地知名歌手', '1980-3-20 22:15:30', 1, '没有图片', '2019-04-25 13:54:09');
INSERT INTO `singer` VALUES (6, '五月天', 0, 0, 3, '内地知名歌手', '1980-3-20 22:15:30', 0, '没有图片', '2019-04-25 13:54:09');
INSERT INTO `singer` VALUES (7, '四月', 0, 0, 3, '内地知名歌手', '1980-3-20 22:15:30', 1, '没有图片', '2019-04-25 13:54:09');
INSERT INTO `singer` VALUES (8, '中间件', 0, 0, 1, '内地知名歌手', '1980-3-20 22:15:30', 0, '没有图片', '2019-04-25 13:54:09');
INSERT INTO `singer` VALUES (9, '大哥家', 0, 0, 1, '内地知名歌手', '1980-3-20 22:15:30', 0, '没有图片', '2019-04-25 13:54:09');
INSERT INTO `singer` VALUES (10, '花开', 0, 0, 3, '内地知名歌手', '1980-3-20 22:15:30', 1, '没有图片', '2019-04-25 13:54:09');

-- ----------------------------
-- Table structure for song
-- ----------------------------
DROP TABLE IF EXISTS `song`;
CREATE TABLE `song`  (
  `songId` int(11) NOT NULL AUTO_INCREMENT,
  `songName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '歌曲录入名称',
  `singerId` int(11) NOT NULL COMMENT '歌手编号，外键',
  `CDId` int(11) NOT NULL COMMENT 'CD专辑编号，外键',
  `language` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '国语' COMMENT '歌曲语种',
  `playCount` int(11) NOT NULL DEFAULT 0 COMMENT '歌曲播放次数，默认为0',
  `downloadCount` int(11) NOT NULL DEFAULT 0 COMMENT '歌曲下载次数，默认0',
  `collectionCount` int(11) NOT NULL DEFAULT 0 COMMENT '歌曲收藏次数，默认0',
  `publishDate` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `songUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '歌曲链接地址',
  `cyricUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '歌词连接地址',
  `songTime` double NULL DEFAULT NULL COMMENT '歌曲时间长度',
  `typeId` int(11) NULL DEFAULT NULL COMMENT '歌曲类型编号，外键',
  PRIMARY KEY (`songId`) USING BTREE,
  INDEX `song_fk_singerId`(`singerId`) USING BTREE,
  INDEX `song_fk_CDId`(`CDId`) USING BTREE,
  INDEX `song_fk_typeId`(`typeId`) USING BTREE,
  CONSTRAINT `song_ibfk_1` FOREIGN KEY (`singerId`) REFERENCES `singer` (`singerId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `song_ibfk_2` FOREIGN KEY (`CDId`) REFERENCES `cd` (`CDId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `song_ibfk_3` FOREIGN KEY (`typeId`) REFERENCES `type` (`typeId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '歌曲表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of song
-- ----------------------------
INSERT INTO `song` VALUES (1, '不说再见', 1, 1, '国语', 39, 0, 6, '2019-04-25 14:13:08', '/musicFile/1_1559455196116_71121.mp3', '无', NULL, 1);
INSERT INTO `song` VALUES (2, '玫瑰', 1, 1, '国语', 17, 0, 4, '2019-04-25 14:13:08', '/musicFile/2_1559459445415_11345.mp3', '无', NULL, 2);
INSERT INTO `song` VALUES (3, '那些花儿', 1, 1, '国语', 28, 0, 6, '2019-04-25 14:13:08', '/musicFile/3_1559458281470_5152.mp3', '无', NULL, 5);
INSERT INTO `song` VALUES (4, '送别', 1, 1, '国语', 26, 0, 6, '2019-04-25 14:13:08', '/musicFile/4_1559458328266_46770.mp3', '无', NULL, 4);
INSERT INTO `song` VALUES (5, '南山南', 1, 1, '国语', 11, 0, 6, '2019-04-25 14:13:08', '/musicFile/5_1559459466538_14545.mp3', '无', NULL, 1);
INSERT INTO `song` VALUES (6, '第六首歌', 2, 2, '国语', 3, 0, 2, '2019-04-25 14:14:41', '无', '无', NULL, 1);
INSERT INTO `song` VALUES (7, '遇见你的时候所有星星都落到我头上', 2, 2, '国语', 5, 0, 3, '2019-04-25 14:14:41', '/musicFile/7_1559463058009_11954.mp3', '无', NULL, 2);
INSERT INTO `song` VALUES (8, '第八首歌', 2, 2, '国语', 2, 0, 3, '2019-04-25 14:14:41', '/musicFile/8_1559462643957_80646.mp3', '无', NULL, 5);
INSERT INTO `song` VALUES (9, '我会想起你', 2, 2, '国语', 19, 0, 7, '2019-04-25 14:14:41', '/musicFile/9_1559462351088_12628.mp3', '无', NULL, 4);
INSERT INTO `song` VALUES (10, '茶底世界', 2, 2, '国语', 7, 0, 1, '2019-04-25 14:14:41', '/musicFile/10_1559474211647_31054.mp3', '无', NULL, 1);
INSERT INTO `song` VALUES (11, '第十一首歌', 3, 3, '国语', 8, 0, 5, '2019-04-25 14:15:20', '无', '无', NULL, 1);
INSERT INTO `song` VALUES (12, '我会想起你', 3, 3, '国语', 1, 0, 3, '2019-04-25 14:15:20', '/musicFile/12_1559477756649_94977.mp3', '无', NULL, 2);
INSERT INTO `song` VALUES (13, '第十三首歌', 3, 3, '国语', 1, 0, 1, '2019-04-25 14:15:20', '无', '无', NULL, 5);
INSERT INTO `song` VALUES (14, '第十四首歌', 3, 3, '国语', 1, 0, 1, '2019-04-25 14:15:20', '无', '无', NULL, 4);
INSERT INTO `song` VALUES (15, '第十五首歌', 3, 3, '国语', 5, 0, 1, '2019-04-25 14:15:20', '无', '无', NULL, 1);
INSERT INTO `song` VALUES (16, '第十六首歌', 4, 4, '国语', 0, 0, 0, '2019-04-25 14:16:25', '无', '无', NULL, 1);
INSERT INTO `song` VALUES (17, '第十七首歌', 4, 4, '国语', 0, 0, 0, '2019-04-25 14:16:25', '无', '无', NULL, 2);
INSERT INTO `song` VALUES (18, '第十八首歌', 4, 4, '国语', 0, 0, 0, '2019-04-25 14:16:25', '无', '无', NULL, 5);
INSERT INTO `song` VALUES (19, '第十九首歌', 4, 4, '国语', 0, 0, 0, '2019-04-25 14:16:25', '无', '无', NULL, 4);
INSERT INTO `song` VALUES (20, '第二十首歌', 4, 4, '国语', 0, 0, 0, '2019-04-25 14:16:25', '无', '无', NULL, 1);
INSERT INTO `song` VALUES (21, '第二十一首歌', 5, 5, '国语', 26, 0, 2, '2019-04-25 14:17:05', '无', '无', NULL, 1);
INSERT INTO `song` VALUES (22, '第二十二首歌', 5, 5, '国语', 0, 0, 0, '2019-04-25 14:17:05', '无', '无', NULL, 2);
INSERT INTO `song` VALUES (23, '第二十三首歌', 5, 5, '国语', 1, 0, 1, '2019-04-25 14:17:05', '无', '无', NULL, 5);
INSERT INTO `song` VALUES (24, '第二十四首歌', 5, 5, '国语', 0, 0, 0, '2019-04-25 14:17:05', '无', '无', NULL, 4);
INSERT INTO `song` VALUES (25, '第二十五首歌', 5, 5, '国语', 0, 0, 0, '2019-04-25 14:17:05', '无', '无', NULL, 1);
INSERT INTO `song` VALUES (26, '第二十六首歌', 6, 6, '国语', 1, 0, 0, '2019-04-25 14:17:49', '无', '无', NULL, 1);
INSERT INTO `song` VALUES (27, '第二十七首歌', 6, 6, '国语', 0, 0, 0, '2019-04-25 14:17:49', '无', '无', NULL, 2);
INSERT INTO `song` VALUES (28, '第二十八首歌', 6, 6, '国语', 0, 0, 0, '2019-04-25 14:17:49', '无', '无', NULL, 5);
INSERT INTO `song` VALUES (29, '第二十九首歌', 6, 6, '国语', 0, 0, 0, '2019-04-25 14:17:49', '无', '无', NULL, 4);
INSERT INTO `song` VALUES (30, '第三十首歌', 6, 6, '国语', 0, 0, 0, '2019-04-25 14:17:49', '无', '无', NULL, 1);
INSERT INTO `song` VALUES (31, '第三十一首歌', 7, 7, '国语', 0, 0, 0, '2019-04-25 14:18:43', '无', '无', NULL, 1);
INSERT INTO `song` VALUES (32, '第三十二首歌', 7, 7, '国语', 0, 0, 0, '2019-04-25 14:18:43', '无', '无', NULL, 2);
INSERT INTO `song` VALUES (33, '第三十三首歌', 7, 7, '国语', 0, 0, 0, '2019-04-25 14:18:43', '无', '无', NULL, 5);
INSERT INTO `song` VALUES (34, '第三十四首歌', 7, 7, '国语', 0, 0, 0, '2019-04-25 14:18:43', '无', '无', NULL, 4);
INSERT INTO `song` VALUES (35, '第三十五首歌', 7, 7, '国语', 2, 0, 0, '2019-04-25 14:18:43', '无', '无', NULL, 1);
INSERT INTO `song` VALUES (36, '第三十六首歌', 8, 8, '国语', 0, 0, 0, '2019-04-25 14:19:40', '无', '无', NULL, 1);
INSERT INTO `song` VALUES (37, '第三十七首歌', 8, 8, '国语', 0, 0, 0, '2019-04-25 14:19:40', '无', '无', NULL, 2);
INSERT INTO `song` VALUES (38, '第三十八首歌', 8, 8, '国语', 1, 0, 0, '2019-04-25 14:19:40', '无', '无', NULL, 5);
INSERT INTO `song` VALUES (39, '第三十九首歌', 8, 8, '国语', 1, 0, 0, '2019-04-25 14:19:40', '无', '无', NULL, 4);
INSERT INTO `song` VALUES (40, '第四十首歌', 8, 8, '国语', 1, 0, 0, '2019-04-25 14:19:40', '无', '无', NULL, 1);
INSERT INTO `song` VALUES (41, '第四十一首歌', 9, 9, '国语', 1, 0, 0, '2019-04-25 14:20:23', '无', '无', NULL, 1);
INSERT INTO `song` VALUES (42, '第四十二首歌', 9, 9, '国语', 1, 0, 0, '2019-04-25 14:20:23', '无', '无', NULL, 2);
INSERT INTO `song` VALUES (43, '第四十三首歌', 9, 9, '国语', 1, 0, 0, '2019-04-25 14:20:23', '无', '无', NULL, 5);
INSERT INTO `song` VALUES (44, '第四十四首歌', 9, 9, '国语', 1, 0, 0, '2019-04-25 14:20:23', '无', '无', NULL, 4);
INSERT INTO `song` VALUES (45, '第四十五首歌', 9, 9, '国语', 1, 0, 0, '2019-04-25 14:20:23', '无', '无', NULL, 1);
INSERT INTO `song` VALUES (46, '第四十六首歌', 10, 10, '国语', 1, 0, 0, '2019-04-25 14:21:22', '无', '无', NULL, 1);
INSERT INTO `song` VALUES (47, '第四十七首歌', 10, 10, '国语', 0, 0, 0, '2019-04-25 14:21:22', '无', '无', NULL, 2);
INSERT INTO `song` VALUES (48, '第四十八首歌', 10, 10, '国语', 0, 0, 0, '2019-04-25 14:21:22', '无', '无', NULL, 5);
INSERT INTO `song` VALUES (49, '第四十九首歌', 10, 10, '国语', 0, 0, 0, '2019-04-25 14:21:22', '无', '无', NULL, 4);
INSERT INTO `song` VALUES (50, '第五十首歌', 10, 10, '国语', 0, 0, 0, '2019-04-25 14:21:22', '无', '无', NULL, 1);

-- ----------------------------
-- Table structure for songlist
-- ----------------------------
DROP TABLE IF EXISTS `songlist`;
CREATE TABLE `songlist`  (
  `songListId` int(11) NOT NULL AUTO_INCREMENT COMMENT '歌单编号，主键',
  `userId` int(11) NOT NULL COMMENT '用户编号，外键',
  `songListName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '歌单名称',
  `typeId` int(11) NULL DEFAULT NULL COMMENT '类型编号，外键',
  `accessCount` int(11) NOT NULL DEFAULT 0 COMMENT '热度，访问次数，默认0',
  `introduce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '歌单介绍',
  `collectionCount` int(11) NOT NULL DEFAULT 0 COMMENT '收藏次数，默认0',
  `tags` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '歌单标签',
  `songListStateId` int(11) NOT NULL DEFAULT 0,
  `songListImg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`songListId`) USING BTREE,
  INDEX `songlist_fk_userId`(`userId`) USING BTREE,
  INDEX `songlist_fk_typeId`(`typeId`) USING BTREE,
  CONSTRAINT `songlist_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `songlist_ibfk_2` FOREIGN KEY (`typeId`) REFERENCES `type` (`typeId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '歌单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of songlist
-- ----------------------------
INSERT INTO `songlist` VALUES (1, 1, '喜欢的', 1, 841, '这是我喜欢的歌曲', 0, '电子 古典 工作', 0, '/songListImgs/1_1559114707757_64965.jpg');
INSERT INTO `songlist` VALUES (2, 1, '喜欢的', 1, 288, '这是我喜欢的歌曲', 0, '爵士 乡村 学习', 0, '/songListImgs/2_1559145027137_69190.jpg');
INSERT INTO `songlist` VALUES (3, 1, '第二的', 1, 169, '这是我喜欢的歌曲', 1, '夜晚 学习 工作', 0, '/songListImgs/3_1559060232703_67148.jpg');
INSERT INTO `songlist` VALUES (4, 1, '第三的', 1, 91, '这是我喜欢的歌曲', 1, '电子 古典', 0, '/songListImgs/4_1559060250241_29910.jpg');
INSERT INTO `songlist` VALUES (5, 1, '第四的', 1, 165, '这是我喜欢的歌曲', 2, '摇滚 舞曲', 0, '/songListImgs/5_1559060271140_2677.jpg');
INSERT INTO `songlist` VALUES (6, 1, '第五的', 1, 81, '这是我喜欢的歌曲', 0, '回忆 摇滚', 0, '/songListImgs/6_1559060435695_28120.jpg');
INSERT INTO `songlist` VALUES (7, 1, '第六的', 1, 90, '这是我喜欢的歌曲', 0, '民谣 雷鬼', 0, '/songListImgs/7_1559174583262_90609.jpg');
INSERT INTO `songlist` VALUES (8, 1, '第七', 1, 37, '这是我喜欢的歌曲', 2, '民谣 乡村 蓝调', 0, NULL);
INSERT INTO `songlist` VALUES (9, 1, '测试1', NULL, 24, '', 0, '浪漫 夜晚 流行', 0, '/songListImgs/9_1559060287638_39500.jpg');
INSERT INTO `songlist` VALUES (10, 1, '测试2', NULL, 18, '', 0, '年代 夜晚 学习', 0, '/songListImgs/10_1559060395986_20721.jpg');
INSERT INTO `songlist` VALUES (11, 1, '测试3', NULL, 15, '', 0, '学习 摇滚 电子', 0, NULL);
INSERT INTO `songlist` VALUES (12, 1, '测试4', NULL, 23, '', 0, '工作 电子 说唱', 0, '/songListImgs/12_1559060414744_96135.jpg');
INSERT INTO `songlist` VALUES (13, 1, '测试5', NULL, 42, '', 0, '学习 说唱 世界音乐', 0, NULL);
INSERT INTO `songlist` VALUES (14, 1, '测试6', NULL, 28, '', 0, '工作 午休 下午茶', 0, '/songListImgs/14_1559089927530_10552.jpg');
INSERT INTO `songlist` VALUES (15, 1, '测试7', NULL, 19, '', 0, '儿童 80后 90后', 0, '/songListImgs/15_1559089946690_5528.jpg');
INSERT INTO `songlist` VALUES (16, 1, '测试8', NULL, 25, '', 0, '', 0, '/songListImgs/16_1559094633054_68949.jpg');
INSERT INTO `songlist` VALUES (17, 1, '测试9', NULL, 6, NULL, 0, NULL, 0, '/songListImgs/17_1559089965028_35490.jpg');
INSERT INTO `songlist` VALUES (18, 1, '测试10', NULL, 7, NULL, 0, NULL, 0, '/songListImgs/18_1559089982460_76807.jpg');
INSERT INTO `songlist` VALUES (19, 1, '测试11', NULL, 3, NULL, 0, NULL, 0, NULL);
INSERT INTO `songlist` VALUES (20, 1, '测试12', NULL, 3, NULL, 0, NULL, 0, '/songListImgs/20_1559485793585_63130.jpg');
INSERT INTO `songlist` VALUES (21, 1, '测试13', NULL, 1, NULL, 0, NULL, 0, NULL);
INSERT INTO `songlist` VALUES (22, 1, '测试14', NULL, 2, NULL, 0, NULL, 0, NULL);
INSERT INTO `songlist` VALUES (23, 1, '测试15', NULL, 6, NULL, 0, NULL, 0, '/songListImgs/23_1559114812953_70119.jpg');
INSERT INTO `songlist` VALUES (24, 1, '测试16', NULL, 16, NULL, 0, NULL, 0, NULL);
INSERT INTO `songlist` VALUES (25, 1, '测试17', NULL, 1, NULL, 0, NULL, 0, '/songListImgs/25_1559485780198_81168.jpg');
INSERT INTO `songlist` VALUES (26, 1, '测试18', NULL, 1, NULL, 1, NULL, 0, NULL);
INSERT INTO `songlist` VALUES (27, 1, '测试19', NULL, 3, NULL, 0, NULL, 0, NULL);
INSERT INTO `songlist` VALUES (28, 1, '测试20', NULL, 6, NULL, 0, NULL, 0, '/songListImgs/28_1559485758866_70152.jpg');
INSERT INTO `songlist` VALUES (29, 1, '测试21', NULL, 5, NULL, 1, NULL, 0, NULL);
INSERT INTO `songlist` VALUES (30, 1, '测试22', NULL, 8, NULL, 0, NULL, 0, '/songListImgs/30_1559060372660_24154.jpg');
INSERT INTO `songlist` VALUES (31, 1, '测试23', NULL, 7, NULL, 0, NULL, 0, '/songListImgs/31_1559485718081_3474.jpg');
INSERT INTO `songlist` VALUES (32, 1, '测试24', NULL, 11, NULL, 1, NULL, 0, '/songListImgs/32_1559485698973_91355.jpg');
INSERT INTO `songlist` VALUES (45, 2, '还好', NULL, 17, '里面都不错哦', 0, '年代 夜晚 电子', 0, '/songListImgs/45_1559485333667_16257.jpg');
INSERT INTO `songlist` VALUES (46, 2, '挺喜欢的', NULL, 17, '适合运动哦', 0, '清晨 流行 电子', 0, '/songListImgs/46_1559485344199_91987.jpg');
INSERT INTO `songlist` VALUES (47, 2, '第一的', NULL, 51, '非常不错', 0, '清晨 乡村 雷鬼', 0, '/songListImgs/47_1559485359259_53338.jpg');
INSERT INTO `songlist` VALUES (48, 2, '第三的', NULL, 24, '老歌经典', 1, '80后 性感 伤感', 0, '/songListImgs/48_1559485370309_4544.jpg');
INSERT INTO `songlist` VALUES (49, 2, '这个不错', NULL, 7, '适合恋爱', 0, '清新 浪漫 性感', 0, '/songListImgs/49_1559485592999_92636.jpg');
INSERT INTO `songlist` VALUES (50, 2, '经典老歌', NULL, 5, '我们的老歌', 0, '70后 80后 90后', 0, '/songListImgs/50_1559485612279_44619.jpg');
INSERT INTO `songlist` VALUES (51, 2, '西游记', NULL, 7, '', 0, '年代 回忆 工作', 0, '/songListImgs/51_1559485625612_32157.jpg');
INSERT INTO `songlist` VALUES (52, 2, '天天向上', NULL, 6, '', 0, '学习 工作 午休', 0, '/songListImgs/52_1559485640225_92385.jpg');
INSERT INTO `songlist` VALUES (53, 2, '明天会更好', NULL, 8, '', 0, '夜晚 摇滚 世界音乐', 0, '/songListImgs/53_1559485655464_28746.jpg');
INSERT INTO `songlist` VALUES (54, 4, '经典歌曲', NULL, 16, '', 0, '年代 电子 古典', 0, NULL);
INSERT INTO `songlist` VALUES (55, 3, '测试9', NULL, 26, '', 0, '学习 浪漫 治愈', 0, NULL);
INSERT INTO `songlist` VALUES (56, 3, '新船', NULL, 23, '', 0, '游戏 70后 80后', 0, NULL);
INSERT INTO `songlist` VALUES (57, 3, '微幅新词', NULL, 22, '', 0, '蓝调 清新 浪漫', 0, NULL);
INSERT INTO `songlist` VALUES (58, 3, '啦啦啦', NULL, 30, '', 0, '民族 90后', 0, NULL);
INSERT INTO `songlist` VALUES (59, 3, '请说丑', NULL, 22, '', 0, '乡村 古风 民谣', 0, NULL);
INSERT INTO `songlist` VALUES (60, 5, '我是第五个的歌单', NULL, 79, NULL, 3, NULL, 0, '/songListImgs/60_1559095001483_81797.jpg');
INSERT INTO `songlist` VALUES (61, 6, '歌单1', NULL, 17, '', 0, '学习 古典 民族', 0, NULL);
INSERT INTO `songlist` VALUES (62, 6, '第三的', NULL, 21, '', 1, '校园 90后 青春', 0, NULL);
INSERT INTO `songlist` VALUES (63, 8, '轻音乐', NULL, 3, NULL, 0, NULL, 0, '/songListImgs/63_1559094770250_13396.jpg');
INSERT INTO `songlist` VALUES (64, 1, '测试888', NULL, 3, NULL, 0, NULL, 0, '/songListImgs/64_1559485736519_38720.jpg');

-- ----------------------------
-- Table structure for songlistwithsong
-- ----------------------------
DROP TABLE IF EXISTS `songlistwithsong`;
CREATE TABLE `songlistwithsong`  (
  `slSongId` int(11) NOT NULL AUTO_INCREMENT COMMENT '歌曲歌单编号，主键',
  `songListId` int(11) NOT NULL COMMENT '歌单编号，外键',
  `songId` int(11) NOT NULL COMMENT '歌曲编号，外键',
  `collectionDate` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`slSongId`) USING BTREE,
  INDEX `slsong_fk_songListId`(`songListId`) USING BTREE,
  INDEX `slsong_fk_songId`(`songId`) USING BTREE,
  CONSTRAINT `songlistwithsong_ibfk_1` FOREIGN KEY (`songListId`) REFERENCES `songlist` (`songListId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `songlistwithsong_ibfk_2` FOREIGN KEY (`songId`) REFERENCES `song` (`songId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 174 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '歌曲歌单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of songlistwithsong
-- ----------------------------
INSERT INTO `songlistwithsong` VALUES (107, 1, 1, '2019-05-21 09:32:38');
INSERT INTO `songlistwithsong` VALUES (114, 2, 9, '2019-05-21 09:36:11');
INSERT INTO `songlistwithsong` VALUES (115, 4, 10, '2019-05-21 09:36:14');
INSERT INTO `songlistwithsong` VALUES (116, 5, 11, '2019-05-21 09:36:16');
INSERT INTO `songlistwithsong` VALUES (117, 4, 11, '2019-05-21 09:36:18');
INSERT INTO `songlistwithsong` VALUES (118, 5, 15, '2019-05-21 09:36:21');
INSERT INTO `songlistwithsong` VALUES (119, 12, 21, '2019-05-21 09:36:24');
INSERT INTO `songlistwithsong` VALUES (120, 3, 1, '2019-05-21 09:36:45');
INSERT INTO `songlistwithsong` VALUES (121, 3, 2, '2019-05-21 09:36:48');
INSERT INTO `songlistwithsong` VALUES (122, 3, 3, '2019-05-21 09:36:50');
INSERT INTO `songlistwithsong` VALUES (123, 20, 3, '2019-05-22 10:34:55');
INSERT INTO `songlistwithsong` VALUES (125, 27, 9, '2019-05-22 15:26:55');
INSERT INTO `songlistwithsong` VALUES (126, 3, 9, '2019-05-22 15:27:00');
INSERT INTO `songlistwithsong` VALUES (129, 5, 9, '2019-05-29 00:18:44');
INSERT INTO `songlistwithsong` VALUES (130, 5, 1, '2019-05-29 00:18:53');
INSERT INTO `songlistwithsong` VALUES (131, 6, 11, '2019-05-29 00:18:59');
INSERT INTO `songlistwithsong` VALUES (132, 6, 9, '2019-05-29 00:19:02');
INSERT INTO `songlistwithsong` VALUES (133, 63, 12, '2019-05-29 09:53:01');
INSERT INTO `songlistwithsong` VALUES (134, 63, 4, '2019-05-29 09:53:03');
INSERT INTO `songlistwithsong` VALUES (135, 63, 23, '2019-05-29 09:53:07');
INSERT INTO `songlistwithsong` VALUES (136, 2, 21, '2019-05-30 08:04:09');
INSERT INTO `songlistwithsong` VALUES (137, 3, 11, '2019-05-30 08:04:22');
INSERT INTO `songlistwithsong` VALUES (138, 4, 4, '2019-05-30 08:04:32');
INSERT INTO `songlistwithsong` VALUES (139, 7, 3, '2019-06-02 20:45:49');
INSERT INTO `songlistwithsong` VALUES (140, 7, 5, '2019-06-02 20:45:53');
INSERT INTO `songlistwithsong` VALUES (141, 7, 7, '2019-06-02 20:45:55');
INSERT INTO `songlistwithsong` VALUES (142, 8, 5, '2019-06-02 20:46:00');
INSERT INTO `songlistwithsong` VALUES (143, 8, 3, '2019-06-02 20:46:03');
INSERT INTO `songlistwithsong` VALUES (144, 8, 1, '2019-06-02 20:46:05');
INSERT INTO `songlistwithsong` VALUES (145, 45, 1, '2019-06-02 22:19:46');
INSERT INTO `songlistwithsong` VALUES (146, 46, 2, '2019-06-02 22:19:49');
INSERT INTO `songlistwithsong` VALUES (147, 46, 1, '2019-06-02 22:19:51');
INSERT INTO `songlistwithsong` VALUES (148, 45, 2, '2019-06-02 22:19:52');
INSERT INTO `songlistwithsong` VALUES (149, 45, 4, '2019-06-02 22:19:55');
INSERT INTO `songlistwithsong` VALUES (150, 45, 5, '2019-06-02 22:20:00');
INSERT INTO `songlistwithsong` VALUES (151, 46, 5, '2019-06-02 22:20:02');
INSERT INTO `songlistwithsong` VALUES (152, 46, 3, '2019-06-02 22:20:04');
INSERT INTO `songlistwithsong` VALUES (153, 47, 5, '2019-06-02 22:20:11');
INSERT INTO `songlistwithsong` VALUES (154, 48, 7, '2019-06-02 22:20:13');
INSERT INTO `songlistwithsong` VALUES (155, 49, 8, '2019-06-02 22:20:15');
INSERT INTO `songlistwithsong` VALUES (156, 49, 9, '2019-06-02 22:20:17');
INSERT INTO `songlistwithsong` VALUES (157, 48, 8, '2019-06-02 22:20:19');
INSERT INTO `songlistwithsong` VALUES (158, 48, 11, '2019-06-02 22:20:26');
INSERT INTO `songlistwithsong` VALUES (159, 50, 12, '2019-06-02 22:20:28');
INSERT INTO `songlistwithsong` VALUES (160, 51, 13, '2019-06-02 22:20:30');
INSERT INTO `songlistwithsong` VALUES (161, 51, 14, '2019-06-02 22:20:33');
INSERT INTO `songlistwithsong` VALUES (162, 51, 6, '2019-06-02 22:20:40');
INSERT INTO `songlistwithsong` VALUES (163, 51, 4, '2019-06-02 22:20:42');
INSERT INTO `songlistwithsong` VALUES (164, 51, 9, '2019-06-02 22:20:45');
INSERT INTO `songlistwithsong` VALUES (165, 52, 4, '2019-06-02 22:20:50');
INSERT INTO `songlistwithsong` VALUES (166, 52, 2, '2019-06-02 22:20:52');
INSERT INTO `songlistwithsong` VALUES (167, 52, 5, '2019-06-02 22:20:54');
INSERT INTO `songlistwithsong` VALUES (168, 52, 3, '2019-06-02 22:20:59');
INSERT INTO `songlistwithsong` VALUES (169, 53, 12, '2019-06-02 22:21:02');
INSERT INTO `songlistwithsong` VALUES (170, 53, 8, '2019-06-02 22:21:04');
INSERT INTO `songlistwithsong` VALUES (171, 53, 7, '2019-06-02 22:21:07');
INSERT INTO `songlistwithsong` VALUES (172, 53, 6, '2019-06-02 22:21:09');
INSERT INTO `songlistwithsong` VALUES (173, 53, 4, '2019-06-02 22:21:12');

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `typeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '类型编号，主键',
  `typeName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型名称',
  PRIMARY KEY (`typeId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '歌曲分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (1, '摇滚');
INSERT INTO `type` VALUES (2, '青春');
INSERT INTO `type` VALUES (3, '乡村');
INSERT INTO `type` VALUES (4, '回忆');
INSERT INTO `type` VALUES (5, '流行');
INSERT INTO `type` VALUES (6, '民谣');
INSERT INTO `type` VALUES (7, '年代');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号，主键',
  `loginId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `userSex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户性别',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户手机',
  `userType` int(11) NOT NULL DEFAULT 0,
  `sign` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人签名',
  `headSculptureUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像链接',
  `registationDate` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userStateId` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'zjh2920779201', '1999420114', 'admin', '男', '2920779201@qq.com', '17860541931', 0, '世界是美好的，相信', '/userHeaderImgs/1_1559174627284_77434.jpg', '2019-04-21 00:00:00', 0);
INSERT INTO `user` VALUES (2, 'zjh17860541931', '1999420114', '郑甲会', '男', '2920779201@qq.co', '18300594762', 0, '永远相信美好的事情即将发生', '/userHeaderImgs/2_1559045768467_48790.jpg', '2019-04-21 10:32:47', 0);
INSERT INTO `user` VALUES (3, 'admin3', '1999420114', '第三个', '男', '17888@163.com', '18520365021', 0, '相信每一天', '/userHeaderImgs/3_1559045817537_67025.jpg', '2019-05-15 13:50:02', 0);
INSERT INTO `user` VALUES (4, 'admin4', '1999420114', '第四个', '男', '178886465@163.com', '18526520021', 0, '相信每一天', '/userHeaderImgs/4_1559045861862_75209.jpg', '2019-05-15 13:50:02', 0);
INSERT INTO `user` VALUES (5, 'admin5', '1999420114', '第五个', '男', '178865488@163.com', '17862065021', 0, '相信每一天', '/userHeaderImgs/5_1559045889650_27282.jpg', '2019-05-15 13:50:02', 1);
INSERT INTO `user` VALUES (6, 'admin6', '1999420114', '第六个', '男', '17886648@163.com', '18520362035', 0, '相信每一天', '/userHeaderImgs/6_1559045913766_29794.jpg', '2019-05-15 13:50:02', 0);
INSERT INTO `user` VALUES (7, 'admin7', '1999420114', '第七个', '男', '178649888@163.com', '18956365021', 0, '相信每一天', '/userHeaderImgs/7_1559045944093_17241.jpg', '2019-05-15 13:50:02', 1);
INSERT INTO `user` VALUES (8, 'admin8', '1999420114', '第八个', '男', '178523088@163.com', '18789665021', 0, '相信每一天', '/userHeaderImgs/8_1559045980263_39575.jpg', '2019-05-15 13:50:02', 0);
INSERT INTO `user` VALUES (9, 'admin9', '1999420114', '第九个', '男', '178563888@163.com', '18520365221', 0, '相信每一天', '无', '2019-05-15 13:50:02', 1);
INSERT INTO `user` VALUES (10, 'zjh456789', '1999420114', '王明', '男', NULL, NULL, 0, NULL, NULL, '2019-05-31 09:49:15', 0);
INSERT INTO `user` VALUES (11, 'zjh894615', '1999420114', '王明', '男', NULL, NULL, 0, NULL, NULL, '2019-05-31 09:49:15', 0);
INSERT INTO `user` VALUES (12, 'zjh879465', '1999420114', '王明', '男', NULL, NULL, 0, NULL, NULL, '2019-05-31 09:49:15', 0);
INSERT INTO `user` VALUES (13, 'zjh984651', '1999420114', '王明', '男', NULL, NULL, 0, NULL, NULL, '2019-05-31 09:49:15', 1);
INSERT INTO `user` VALUES (14, 'zjh78456', '1999420114', '王明', '男', NULL, NULL, 0, NULL, NULL, '2019-05-31 09:49:15', 0);
INSERT INTO `user` VALUES (15, 'zjh74561', '1999420114', '王明', '男', NULL, NULL, 0, NULL, NULL, '2019-05-31 09:49:15', 0);
INSERT INTO `user` VALUES (16, 'zjh321489', '1999420114', '王明', '男', NULL, NULL, 0, NULL, NULL, '2019-05-31 09:49:15', 0);
INSERT INTO `user` VALUES (17, 'zjh054516', '1999420114', '王明', '男', NULL, NULL, 0, NULL, NULL, '2019-05-31 09:49:15', 0);
INSERT INTO `user` VALUES (18, 'zjh894651', '1999420114', '王明', '男', NULL, NULL, 0, NULL, NULL, '2019-05-31 09:49:15', 0);
INSERT INTO `user` VALUES (19, 'zjh98589456', '1999420114', '王明', '男', NULL, NULL, 0, NULL, NULL, '2019-05-31 09:49:15', 1);
INSERT INTO `user` VALUES (20, 'zjh9566515', '1999420114', '王明', '男', NULL, NULL, 0, NULL, NULL, '2019-05-31 09:49:15', 0);
INSERT INTO `user` VALUES (21, 'zjh89415613', '1999420114', '王明', '男', NULL, NULL, 0, NULL, NULL, '2019-05-31 09:49:15', 0);
INSERT INTO `user` VALUES (22, 'zjh851313', '1999420114', '王明', '男', NULL, NULL, 0, NULL, NULL, '2019-05-31 09:49:15', 1);
INSERT INTO `user` VALUES (23, 'zjh841322532', '1999420114', '王明', '男', NULL, NULL, 0, NULL, NULL, '2019-05-31 09:49:15', 0);
INSERT INTO `user` VALUES (24, 'zjh156523265', '1999420114', '王明', '男', NULL, NULL, 0, NULL, NULL, '2019-05-31 09:49:15', 0);
INSERT INTO `user` VALUES (25, 'zjh13565265', '1999420114', '王明', '男', NULL, NULL, 0, NULL, NULL, '2019-05-31 09:49:15', 1);
INSERT INTO `user` VALUES (26, 'zjh56156864', '1999420114', '王明', '男', NULL, NULL, 0, NULL, NULL, '2019-05-31 09:49:15', 0);
INSERT INTO `user` VALUES (27, 'zjh26518525', '1999420114', '王明', '男', NULL, NULL, 0, NULL, NULL, '2019-05-31 09:49:15', 0);
INSERT INTO `user` VALUES (28, 'zjh55146656', '1999420114', '王明', '男', NULL, NULL, 0, NULL, NULL, '2019-05-31 09:49:15', 0);
INSERT INTO `user` VALUES (29, 'zjh4894623', '1999420114', '王明', '男', NULL, NULL, 0, NULL, NULL, '2019-05-31 09:49:15', 0);
INSERT INTO `user` VALUES (30, 'zjh54153625', '1999420114', '王明', '男', NULL, NULL, 0, NULL, NULL, '2019-05-31 09:49:15', 0);
INSERT INTO `user` VALUES (31, 'zjh984132', '1999420114', '王明', '男', NULL, NULL, 0, NULL, NULL, '2019-05-31 09:49:15', 0);
INSERT INTO `user` VALUES (32, 'zjh98461231', '1999420114', '王明', '男', NULL, NULL, 0, NULL, NULL, '2019-05-31 09:49:15', 0);

-- ----------------------------
-- Table structure for userlistensong
-- ----------------------------
DROP TABLE IF EXISTS `userlistensong`;
CREATE TABLE `userlistensong`  (
  `uListenSongId` int(11) NOT NULL AUTO_INCREMENT COMMENT '听歌记录编号，主键',
  `userId` int(11) NOT NULL COMMENT '用户编号，外键',
  `songId` int(11) NOT NULL COMMENT '歌曲编号，外键',
  `listenSongDate` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`uListenSongId`) USING BTREE,
  INDEX `userlistensong_fk_userId`(`userId`) USING BTREE,
  INDEX `userlistensong_fk_songId`(`songId`) USING BTREE,
  CONSTRAINT `userlistensong_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `userlistensong_ibfk_2` FOREIGN KEY (`songId`) REFERENCES `song` (`songId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 139 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户听歌记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userlistensong
-- ----------------------------
INSERT INTO `userlistensong` VALUES (1, 1, 1, '2019-05-21 09:57:39');
INSERT INTO `userlistensong` VALUES (2, 1, 9, '2019-05-21 12:34:11');
INSERT INTO `userlistensong` VALUES (3, 1, 8, '2019-05-21 12:34:16');
INSERT INTO `userlistensong` VALUES (4, 1, 9, '2019-05-21 12:34:17');
INSERT INTO `userlistensong` VALUES (5, 1, 9, '2019-05-21 12:34:21');
INSERT INTO `userlistensong` VALUES (6, 1, 2, '2019-05-21 12:34:26');
INSERT INTO `userlistensong` VALUES (7, 1, 3, '2019-05-21 12:34:27');
INSERT INTO `userlistensong` VALUES (8, 1, 1, '2019-05-23 16:28:32');
INSERT INTO `userlistensong` VALUES (9, 1, 9, '2019-05-23 16:33:33');
INSERT INTO `userlistensong` VALUES (10, 1, 9, '2019-05-23 16:33:46');
INSERT INTO `userlistensong` VALUES (11, 1, 1, '2019-05-23 16:33:51');
INSERT INTO `userlistensong` VALUES (12, 1, 3, '2019-05-23 16:33:52');
INSERT INTO `userlistensong` VALUES (13, 1, 2, '2019-05-23 16:33:53');
INSERT INTO `userlistensong` VALUES (14, 1, 10, '2019-05-23 16:33:54');
INSERT INTO `userlistensong` VALUES (15, 1, 11, '2019-05-23 16:33:55');
INSERT INTO `userlistensong` VALUES (16, 1, 11, '2019-05-23 16:33:57');
INSERT INTO `userlistensong` VALUES (17, 1, 15, '2019-05-23 16:33:58');
INSERT INTO `userlistensong` VALUES (18, 1, 9, '2019-05-23 16:42:06');
INSERT INTO `userlistensong` VALUES (19, 1, 5, '2019-05-23 16:43:40');
INSERT INTO `userlistensong` VALUES (20, 1, 35, '2019-05-23 16:43:47');
INSERT INTO `userlistensong` VALUES (21, 1, 2, '2019-05-23 16:44:04');
INSERT INTO `userlistensong` VALUES (22, 3, 9, '2019-05-24 00:11:56');
INSERT INTO `userlistensong` VALUES (23, 3, 1, '2019-05-24 00:11:57');
INSERT INTO `userlistensong` VALUES (24, 3, 2, '2019-05-24 00:11:58');
INSERT INTO `userlistensong` VALUES (25, 3, 3, '2019-05-24 00:11:59');
INSERT INTO `userlistensong` VALUES (26, 3, 5, '2019-05-24 00:12:00');
INSERT INTO `userlistensong` VALUES (27, 3, 8, '2019-05-24 00:12:00');
INSERT INTO `userlistensong` VALUES (28, 3, 10, '2019-05-24 00:12:01');
INSERT INTO `userlistensong` VALUES (29, 3, 15, '2019-05-24 00:12:02');
INSERT INTO `userlistensong` VALUES (30, 3, 35, '2019-05-24 00:12:02');
INSERT INTO `userlistensong` VALUES (31, 3, 3, '2019-05-24 00:12:03');
INSERT INTO `userlistensong` VALUES (32, 3, 1, '2019-05-24 00:12:04');
INSERT INTO `userlistensong` VALUES (33, 3, 9, '2019-05-24 00:12:05');
INSERT INTO `userlistensong` VALUES (34, 3, 1, '2019-05-24 00:12:06');
INSERT INTO `userlistensong` VALUES (35, 3, 1, '2019-05-24 00:12:06');
INSERT INTO `userlistensong` VALUES (36, 3, 1, '2019-05-24 00:12:06');
INSERT INTO `userlistensong` VALUES (37, 3, 1, '2019-05-24 00:12:06');
INSERT INTO `userlistensong` VALUES (38, 3, 1, '2019-05-24 00:12:07');
INSERT INTO `userlistensong` VALUES (39, 3, 1, '2019-05-24 00:12:07');
INSERT INTO `userlistensong` VALUES (40, 3, 2, '2019-05-24 00:12:07');
INSERT INTO `userlistensong` VALUES (41, 3, 2, '2019-05-24 00:12:08');
INSERT INTO `userlistensong` VALUES (42, 3, 3, '2019-05-24 00:12:08');
INSERT INTO `userlistensong` VALUES (43, 3, 3, '2019-05-24 00:12:08');
INSERT INTO `userlistensong` VALUES (44, 3, 3, '2019-05-24 00:12:09');
INSERT INTO `userlistensong` VALUES (45, 3, 3, '2019-05-24 00:12:09');
INSERT INTO `userlistensong` VALUES (46, 3, 3, '2019-05-24 00:12:09');
INSERT INTO `userlistensong` VALUES (47, 3, 3, '2019-05-24 00:12:09');
INSERT INTO `userlistensong` VALUES (48, 3, 4, '2019-05-24 00:12:19');
INSERT INTO `userlistensong` VALUES (49, 3, 4, '2019-05-24 00:12:19');
INSERT INTO `userlistensong` VALUES (50, 3, 4, '2019-05-24 00:12:20');
INSERT INTO `userlistensong` VALUES (51, 3, 6, '2019-05-24 00:12:20');
INSERT INTO `userlistensong` VALUES (52, 3, 6, '2019-05-24 00:12:20');
INSERT INTO `userlistensong` VALUES (53, 3, 11, '2019-05-24 00:12:21');
INSERT INTO `userlistensong` VALUES (54, 3, 3, '2019-05-24 00:12:22');
INSERT INTO `userlistensong` VALUES (55, 3, 1, '2019-05-24 00:12:23');
INSERT INTO `userlistensong` VALUES (56, 3, 1, '2019-05-24 00:12:23');
INSERT INTO `userlistensong` VALUES (57, 3, 15, '2019-05-24 00:12:24');
INSERT INTO `userlistensong` VALUES (58, 3, 10, '2019-05-24 00:12:25');
INSERT INTO `userlistensong` VALUES (59, 3, 21, '2019-05-24 00:12:25');
INSERT INTO `userlistensong` VALUES (60, 3, 26, '2019-05-24 00:12:26');
INSERT INTO `userlistensong` VALUES (61, 3, 4, '2019-05-24 00:12:26');
INSERT INTO `userlistensong` VALUES (62, 3, 3, '2019-05-24 00:12:27');
INSERT INTO `userlistensong` VALUES (63, 3, 1, '2019-05-24 00:12:28');
INSERT INTO `userlistensong` VALUES (64, 3, 11, '2019-05-24 00:12:42');
INSERT INTO `userlistensong` VALUES (65, 3, 11, '2019-05-24 00:12:43');
INSERT INTO `userlistensong` VALUES (66, 3, 15, '2019-05-24 00:12:43');
INSERT INTO `userlistensong` VALUES (67, 3, 44, '2019-05-24 00:13:10');
INSERT INTO `userlistensong` VALUES (68, 3, 43, '2019-05-24 00:13:10');
INSERT INTO `userlistensong` VALUES (69, 3, 42, '2019-05-24 00:13:10');
INSERT INTO `userlistensong` VALUES (70, 3, 41, '2019-05-24 00:13:11');
INSERT INTO `userlistensong` VALUES (71, 3, 40, '2019-05-24 00:13:11');
INSERT INTO `userlistensong` VALUES (72, 3, 39, '2019-05-24 00:13:12');
INSERT INTO `userlistensong` VALUES (73, 3, 38, '2019-05-24 00:13:12');
INSERT INTO `userlistensong` VALUES (74, 3, 45, '2019-05-24 00:13:13');
INSERT INTO `userlistensong` VALUES (75, 3, 46, '2019-05-24 00:13:14');
INSERT INTO `userlistensong` VALUES (76, 3, 3, '2019-05-24 00:14:16');
INSERT INTO `userlistensong` VALUES (77, 3, 2, '2019-05-24 00:14:21');
INSERT INTO `userlistensong` VALUES (78, 3, 1, '2019-05-24 00:15:00');
INSERT INTO `userlistensong` VALUES (79, 8, 1, '2019-05-29 09:53:34');
INSERT INTO `userlistensong` VALUES (80, 8, 2, '2019-05-29 09:53:35');
INSERT INTO `userlistensong` VALUES (81, 8, 9, '2019-05-29 09:53:42');
INSERT INTO `userlistensong` VALUES (82, 8, 10, '2019-05-29 09:53:43');
INSERT INTO `userlistensong` VALUES (83, 8, 11, '2019-05-29 09:53:44');
INSERT INTO `userlistensong` VALUES (84, 8, 12, '2019-05-29 09:53:44');
INSERT INTO `userlistensong` VALUES (85, 8, 13, '2019-05-29 09:53:44');
INSERT INTO `userlistensong` VALUES (86, 8, 14, '2019-05-29 09:53:46');
INSERT INTO `userlistensong` VALUES (87, 8, 15, '2019-05-29 09:53:46');
INSERT INTO `userlistensong` VALUES (88, 1, 2, '2019-05-29 10:13:24');
INSERT INTO `userlistensong` VALUES (89, 1, 10, '2019-05-29 23:47:09');
INSERT INTO `userlistensong` VALUES (90, 1, 11, '2019-05-29 23:47:11');
INSERT INTO `userlistensong` VALUES (91, 1, 21, '2019-05-29 23:48:44');
INSERT INTO `userlistensong` VALUES (92, 1, 21, '2019-05-29 23:48:45');
INSERT INTO `userlistensong` VALUES (93, 1, 21, '2019-05-29 23:48:46');
INSERT INTO `userlistensong` VALUES (94, 1, 21, '2019-05-29 23:48:46');
INSERT INTO `userlistensong` VALUES (95, 1, 21, '2019-05-29 23:48:46');
INSERT INTO `userlistensong` VALUES (96, 1, 21, '2019-05-29 23:48:47');
INSERT INTO `userlistensong` VALUES (97, 1, 21, '2019-05-29 23:48:47');
INSERT INTO `userlistensong` VALUES (98, 1, 21, '2019-05-29 23:48:47');
INSERT INTO `userlistensong` VALUES (99, 1, 21, '2019-05-29 23:48:47');
INSERT INTO `userlistensong` VALUES (100, 1, 21, '2019-05-29 23:48:47');
INSERT INTO `userlistensong` VALUES (101, 1, 21, '2019-05-29 23:48:47');
INSERT INTO `userlistensong` VALUES (102, 1, 21, '2019-05-29 23:48:48');
INSERT INTO `userlistensong` VALUES (103, 1, 21, '2019-05-29 23:48:48');
INSERT INTO `userlistensong` VALUES (104, 1, 21, '2019-05-29 23:48:48');
INSERT INTO `userlistensong` VALUES (105, 1, 21, '2019-05-29 23:48:48');
INSERT INTO `userlistensong` VALUES (106, 1, 3, '2019-05-29 23:49:04');
INSERT INTO `userlistensong` VALUES (107, 1, 3, '2019-05-29 23:49:05');
INSERT INTO `userlistensong` VALUES (108, 1, 4, '2019-06-02 16:54:01');
INSERT INTO `userlistensong` VALUES (109, 1, 1, '2019-06-02 16:54:10');
INSERT INTO `userlistensong` VALUES (110, 1, 1, '2019-06-02 16:54:12');
INSERT INTO `userlistensong` VALUES (111, 1, 9, '2019-06-02 16:54:16');
INSERT INTO `userlistensong` VALUES (112, 1, 4, '2019-06-02 16:55:14');
INSERT INTO `userlistensong` VALUES (113, 1, 1, '2019-06-02 16:55:19');
INSERT INTO `userlistensong` VALUES (114, 1, 9, '2019-06-02 16:59:53');
INSERT INTO `userlistensong` VALUES (115, 1, 2, '2019-06-02 16:59:56');
INSERT INTO `userlistensong` VALUES (116, 1, 4, '2019-06-02 16:59:57');
INSERT INTO `userlistensong` VALUES (117, 1, 5, '2019-06-02 16:59:58');
INSERT INTO `userlistensong` VALUES (118, 1, 4, '2019-06-02 16:59:59');
INSERT INTO `userlistensong` VALUES (119, 1, 5, '2019-06-02 17:00:00');
INSERT INTO `userlistensong` VALUES (120, 1, 6, '2019-06-02 17:00:00');
INSERT INTO `userlistensong` VALUES (121, 1, 7, '2019-06-02 17:00:02');
INSERT INTO `userlistensong` VALUES (122, 1, 4, '2019-06-02 17:00:04');
INSERT INTO `userlistensong` VALUES (123, 1, 4, '2019-06-02 17:12:08');
INSERT INTO `userlistensong` VALUES (124, 1, 3, '2019-06-02 19:15:24');
INSERT INTO `userlistensong` VALUES (125, 1, 9, '2019-06-02 19:15:28');
INSERT INTO `userlistensong` VALUES (126, 1, 10, '2019-06-02 19:17:08');
INSERT INTO `userlistensong` VALUES (127, 1, 10, '2019-06-02 19:17:09');
INSERT INTO `userlistensong` VALUES (128, 1, 4, '2019-06-02 20:14:16');
INSERT INTO `userlistensong` VALUES (129, 1, 4, '2019-06-02 20:14:44');
INSERT INTO `userlistensong` VALUES (130, 1, 4, '2019-06-02 20:16:42');
INSERT INTO `userlistensong` VALUES (131, 1, 1, '2019-06-02 20:19:39');
INSERT INTO `userlistensong` VALUES (132, 1, 4, '2019-06-02 20:39:36');
INSERT INTO `userlistensong` VALUES (133, 1, 4, '2019-06-02 20:45:20');
INSERT INTO `userlistensong` VALUES (134, 1, 2, '2019-06-02 20:51:52');
INSERT INTO `userlistensong` VALUES (135, 1, 3, '2019-06-02 20:51:55');
INSERT INTO `userlistensong` VALUES (136, 1, 1, '2019-06-02 20:52:05');
INSERT INTO `userlistensong` VALUES (137, 1, 4, '2019-06-02 20:52:19');
INSERT INTO `userlistensong` VALUES (138, 2, 1, '2019-06-02 22:19:14');

-- ----------------------------
-- Table structure for usersongcomment
-- ----------------------------
DROP TABLE IF EXISTS `usersongcomment`;
CREATE TABLE `usersongcomment`  (
  `uCommentId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户评论编号，主键',
  `userId` int(11) NOT NULL COMMENT '用户编号，外键',
  `songId` int(11) NOT NULL COMMENT '歌曲编号，外键',
  `commentText` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `commentDate` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`uCommentId`) USING BTREE,
  INDEX `usongcomment_fk_userId`(`userId`) USING BTREE,
  INDEX `usongcomment_fk_songId`(`songId`) USING BTREE,
  CONSTRAINT `usersongcomment_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `usersongcomment_ibfk_2` FOREIGN KEY (`songId`) REFERENCES `song` (`songId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '歌曲评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for usersonglistcomment
-- ----------------------------
DROP TABLE IF EXISTS `usersonglistcomment`;
CREATE TABLE `usersonglistcomment`  (
  `uSongListCommId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `songListId` int(11) NOT NULL,
  `songListCommText` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `songListCommDate` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`uSongListCommId`) USING BTREE,
  INDEX `userId`(`userId`) USING BTREE,
  INDEX `songListId`(`songListId`) USING BTREE,
  CONSTRAINT `userSongListComm_fk_songListId` FOREIGN KEY (`songListId`) REFERENCES `songlist` (`songListId`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `userSongListComm_fk_userId` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of usersonglistcomment
-- ----------------------------
INSERT INTO `usersonglistcomment` VALUES (36, 4, 3, '哈哈哈', '2019-06-01 21:30:39');
INSERT INTO `usersonglistcomment` VALUES (37, 4, 6, '呦呦呦', '2019-06-01 21:30:50');
INSERT INTO `usersonglistcomment` VALUES (38, 1, 12, '啦啦啦啦', '2019-06-01 21:31:08');
INSERT INTO `usersonglistcomment` VALUES (39, 1, 12, '为为为为为为我1', '2019-06-01 21:31:18');
INSERT INTO `usersonglistcomment` VALUES (40, 1, 12, '地方发帖', '2019-06-01 21:31:23');
INSERT INTO `usersonglistcomment` VALUES (41, 1, 3, '五二三天会员', '2019-06-01 21:31:28');
INSERT INTO `usersonglistcomment` VALUES (42, 1, 1, '阿萨德黄齑淡饭', '2019-06-01 21:31:32');
INSERT INTO `usersonglistcomment` VALUES (43, 6, 6, 'fddshdhgfhgfh', '2019-06-01 21:31:57');
INSERT INTO `usersonglistcomment` VALUES (44, 6, 3, 'fhdjgfhjhgfjhjgfj', '2019-06-01 21:32:17');
INSERT INTO `usersonglistcomment` VALUES (45, 6, 5, 'fhfdgnhgfjgfj', '2019-06-01 21:32:23');
INSERT INTO `usersonglistcomment` VALUES (46, 6, 6, 'dfghgfdjgfjgfjgfdjgf', '2019-06-01 21:32:35');
INSERT INTO `usersonglistcomment` VALUES (47, 6, 12, '符合国际化股份结构和飞机', '2019-06-01 21:32:48');
INSERT INTO `usersonglistcomment` VALUES (48, 6, 2, '申达股份方大化工负担和规范化', '2019-06-01 21:32:54');
INSERT INTO `usersonglistcomment` VALUES (49, 6, 4, '丰富的官方电话给房东和规范化股份', '2019-06-01 21:33:09');

-- ----------------------------
-- Table structure for userwithcd
-- ----------------------------
DROP TABLE IF EXISTS `userwithcd`;
CREATE TABLE `userwithcd`  (
  `uCDId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户专辑编号，主键',
  `userId` int(11) NOT NULL COMMENT '用户编号，外键',
  `CDId` int(11) NOT NULL COMMENT '专辑编号，外键',
  `collectionDate` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`uCDId`) USING BTREE,
  INDEX `userwithcd_fk_userId`(`userId`) USING BTREE,
  INDEX `userwithcd_fk_CDId`(`CDId`) USING BTREE,
  CONSTRAINT `userwithcd_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `userwithcd_ibfk_2` FOREIGN KEY (`CDId`) REFERENCES `cd` (`CDId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户专辑表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for userwithsinger
-- ----------------------------
DROP TABLE IF EXISTS `userwithsinger`;
CREATE TABLE `userwithsinger`  (
  `uSingerId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户歌手编号，主键',
  `userId` int(11) NOT NULL COMMENT '用户编号，外键',
  `singerId` int(11) NOT NULL COMMENT '歌手编号，外键',
  `collectionDate` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`uSingerId`) USING BTREE,
  INDEX `uwithsinger_fk_userId`(`userId`) USING BTREE,
  INDEX `uwithsinger_fk_singerId`(`singerId`) USING BTREE,
  CONSTRAINT `userwithsinger_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `userwithsinger_ibfk_2` FOREIGN KEY (`singerId`) REFERENCES `singer` (`singerId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户歌手表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for userwithsonglist
-- ----------------------------
DROP TABLE IF EXISTS `userwithsonglist`;
CREATE TABLE `userwithsonglist`  (
  `uSongListId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户歌单编号，主键',
  `userId` int(11) NOT NULL COMMENT '用户编号，外键',
  `songListId` int(11) NOT NULL COMMENT '歌单编号，外键',
  `collectionDate` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`uSongListId`) USING BTREE,
  INDEX `usonglist_fk_userId`(`userId`) USING BTREE,
  INDEX `usonglist_fk_songListId`(`songListId`) USING BTREE,
  CONSTRAINT `userwithsonglist_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `userwithsonglist_ibfk_2` FOREIGN KEY (`songListId`) REFERENCES `songlist` (`songListId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户歌单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userwithsonglist
-- ----------------------------
INSERT INTO `userwithsonglist` VALUES (14, 1, 60, '2019-05-21 09:41:36');
INSERT INTO `userwithsonglist` VALUES (15, 3, 48, '2019-05-24 00:11:15');
INSERT INTO `userwithsonglist` VALUES (16, 3, 5, '2019-05-24 00:11:17');
INSERT INTO `userwithsonglist` VALUES (17, 3, 4, '2019-05-24 00:11:19');
INSERT INTO `userwithsonglist` VALUES (18, 3, 8, '2019-05-24 00:11:22');
INSERT INTO `userwithsonglist` VALUES (19, 3, 26, '2019-05-24 00:11:33');
INSERT INTO `userwithsonglist` VALUES (20, 3, 29, '2019-05-24 00:11:35');
INSERT INTO `userwithsonglist` VALUES (21, 3, 32, '2019-05-24 00:11:38');
INSERT INTO `userwithsonglist` VALUES (22, 8, 62, '2019-05-29 09:53:19');
INSERT INTO `userwithsonglist` VALUES (23, 8, 60, '2019-05-29 09:53:22');
INSERT INTO `userwithsonglist` VALUES (24, 8, 8, '2019-05-29 09:53:24');
INSERT INTO `userwithsonglist` VALUES (25, 8, 5, '2019-05-29 09:53:26');
INSERT INTO `userwithsonglist` VALUES (26, 4, 3, '2019-06-01 21:22:25');

-- ----------------------------
-- View structure for userlistensongcount_view
-- ----------------------------
DROP VIEW IF EXISTS `userlistensongcount_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `userlistensongcount_view` AS select `userlistensong`.`userId` AS `userId`,`userlistensong`.`songId` AS `songId`,count(`userlistensong`.`songId`) AS `listenSongCount` from `userlistensong` group by `userlistensong`.`userId`,`userlistensong`.`songId`;

SET FOREIGN_KEY_CHECKS = 1;
