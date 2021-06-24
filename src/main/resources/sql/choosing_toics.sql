/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : choosing_toics

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 24/06/2021 09:58:32
*/
CREATE DATABASE IF NOT EXISTS choosing_toics;
use choosing_toics;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chat_record
-- ----------------------------
DROP TABLE IF EXISTS `chat_record`;
CREATE TABLE `chat_record`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `teacher_id` int NOT NULL COMMENT '教师id',
  `student_id` int NOT NULL COMMENT '学生id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容',
  `create_time` bigint NOT NULL COMMENT '创建时间',
  `message_side` int NOT NULL COMMENT '留言方id',
  `flag` int NULL DEFAULT 1 COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of chat_record
-- ----------------------------
INSERT INTO `chat_record` VALUES (69, 20190121, 2019101044, '是打法是否', 1624474046012, 20190121, 1);
INSERT INTO `chat_record` VALUES (70, 20190121, 2019101044, '是对方付', 1624474939637, 20190121, 1);
INSERT INTO `chat_record` VALUES (71, 20190121, 2019101044, 'dafa', 1624474945659, 2019101044, 1);
INSERT INTO `chat_record` VALUES (72, 20190121, 2019101044, '你好呀\n', 1624475328802, 20190121, 1);
INSERT INTO `chat_record` VALUES (73, 20190121, 2019101044, '我叫a是\n', 1624499651983, 2019101044, 1);
INSERT INTO `chat_record` VALUES (74, 20190121, 2019101044, '自带声音\n', 1624499668989, 2019101044, 1);
INSERT INTO `chat_record` VALUES (75, 20190121, 2019101044, '您觉得呢\n', 1624499853636, 20190121, 1);

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `class_no` int NOT NULL AUTO_INCREMENT COMMENT '班级编号',
  `class_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '班级名称',
  `major_no` int NULL DEFAULT NULL COMMENT '专业编号',
  PRIMARY KEY (`class_no`) USING BTREE,
  INDEX `major_no`(`major_no`) USING BTREE,
  CONSTRAINT `class_ibfk_1` FOREIGN KEY (`major_no`) REFERENCES `major` (`major_no`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5005 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES (5000, '19软件2班', 1000);
INSERT INTO `class` VALUES (5001, '19电商1班', 1001);
INSERT INTO `class` VALUES (5002, '19电商2班', 1001);
INSERT INTO `class` VALUES (5003, '19学前2班', 1004);
INSERT INTO `class` VALUES (5004, '19学前3班', 1004);

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `file_id` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件id',
  `topic_id` int NOT NULL COMMENT '课题id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES (44, 'c7107c99-74ec-4767-8ae9-88f88dddc656_acwing测试编程题.md', 25);
INSERT INTO `file` VALUES (45, '9b4228fa-53d7-4690-aa34-1d518de4ad44_2020-2021 学年“优秀学生干部”推荐登记表-罗若飞.docx', 21);
INSERT INTO `file` VALUES (46, '8630589c-42dd-4c7b-8126-d6e60674bb83_2020-2021 学年“优秀学生干部”推荐登记表-罗若飞.docx', 24);
INSERT INTO `file` VALUES (47, 'fddfe148-5164-4ae6-9bec-7fed1e24f240_毕业论文-罗若飞.docx', 20);
INSERT INTO `file` VALUES (48, '2335fe9b-f69d-4212-b9a5-a45966fc46aa_毕业论文-罗若飞.docx', 28);

-- ----------------------------
-- Table structure for institute
-- ----------------------------
DROP TABLE IF EXISTS `institute`;
CREATE TABLE `institute`  (
  `institute_no` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学院编号',
  `institute_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学院名称',
  PRIMARY KEY (`institute_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of institute
-- ----------------------------
INSERT INTO `institute` VALUES ('100', '电子信息工程系');
INSERT INTO `institute` VALUES ('101', '文化与传媒系');
INSERT INTO `institute` VALUES ('102', '教育系');
INSERT INTO `institute` VALUES ('103', '旅游商贸系');
INSERT INTO `institute` VALUES ('104', '经济管理系');
INSERT INTO `institute` VALUES ('105', '机电工程系');

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major`  (
  `major_no` int NOT NULL AUTO_INCREMENT COMMENT '专业编号',
  `major_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '专业名称',
  `institute_no` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学院编号',
  PRIMARY KEY (`major_no`) USING BTREE,
  INDEX `institute_no`(`institute_no`) USING BTREE,
  CONSTRAINT `major_ibfk_1` FOREIGN KEY (`institute_no`) REFERENCES `institute` (`institute_no`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1011 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES (1000, '软件设计', '100');
INSERT INTO `major` VALUES (1001, '电子商务', '100');
INSERT INTO `major` VALUES (1002, '数字媒体', '100');
INSERT INTO `major` VALUES (1003, '广告设计', '101');
INSERT INTO `major` VALUES (1004, '学前教育', '102');
INSERT INTO `major` VALUES (1005, '小学教育', '102');
INSERT INTO `major` VALUES (1006, '食品安全', '103');
INSERT INTO `major` VALUES (1007, '物流管理', '104');
INSERT INTO `major` VALUES (1008, '一体化工程', '105');
INSERT INTO `major` VALUES (1010, '影视传媒', '100');

-- ----------------------------
-- Table structure for selecttopic
-- ----------------------------
DROP TABLE IF EXISTS `selecttopic`;
CREATE TABLE `selecttopic`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title_no` int NOT NULL COMMENT '课题编号',
  `student_no` int NOT NULL COMMENT '学号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `student_no`(`student_no`) USING BTREE,
  INDEX `title_no`(`title_no`) USING BTREE,
  CONSTRAINT `selecttopic_ibfk_1` FOREIGN KEY (`student_no`) REFERENCES `student` (`student_no`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `selecttopic_ibfk_2` FOREIGN KEY (`title_no`) REFERENCES `topic` (`title_no`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 103 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of selecttopic
-- ----------------------------

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `student_no` int NOT NULL AUTO_INCREMENT COMMENT '学号',
  `student_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `sex` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `birthday` date NULL DEFAULT NULL COMMENT '出生年月',
  `class_no` int NULL DEFAULT NULL COMMENT '班级编号',
  `topic_no` int NULL DEFAULT NULL COMMENT '课题编号',
  `file` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生毕设',
  PRIMARY KEY (`student_no`) USING BTREE,
  UNIQUE INDEX `student_name`(`student_name`) USING BTREE,
  INDEX `class_no`(`class_no`) USING BTREE,
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`class_no`) REFERENCES `class` (`class_no`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2019101052 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (2019101039, '李四', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `student` VALUES (2019101040, '张三', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `student` VALUES (2019101044, '张飞', '男', '2021-05-28', 5000, 24, '84915efb-433d-4b13-8d20-c3c09baafd5b_Diagram%201.jpg');
INSERT INTO `student` VALUES (2019101045, '李白', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `student` VALUES (2019101046, '阿珂', '男', '2021-05-14', 5001, NULL, NULL);
INSERT INTO `student` VALUES (2019101048, '大乔', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `student` VALUES (2019101051, '瑶', '男', '2021-06-15', 5000, NULL, NULL);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `teacher_no` int NOT NULL AUTO_INCREMENT COMMENT '教师编号',
  `teacher_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '教师姓名',
  `sex` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `degree` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学位',
  `institute_no` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学院编号',
  PRIMARY KEY (`teacher_no`) USING BTREE,
  INDEX `institute_no`(`institute_no`) USING BTREE,
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`institute_no`) REFERENCES `institute` (`institute_no`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 20190128 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (20190121, '阿肆', '男', '高级教师', '103');
INSERT INTO `teacher` VALUES (20190122, '韩信', NULL, '中级教师', '101');
INSERT INTO `teacher` VALUES (20190125, '白白', '男', '初级教师', '100');
INSERT INTO `teacher` VALUES (20190126, '韩跳跳', '男', '中级教师', '100');
INSERT INTO `teacher` VALUES (20190127, '拜拜老师', '男', '初级教师', '100');

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic`  (
  `title_no` int NOT NULL AUTO_INCREMENT COMMENT '课题编号',
  `title_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课题名称',
  `title_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课题描述',
  `teacher_no` int NOT NULL COMMENT '教师编号',
  `admission` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态',
  `state` int NULL DEFAULT 1,
  PRIMARY KEY (`title_no`) USING BTREE,
  INDEX `teacher_no`(`teacher_no`) USING BTREE,
  CONSTRAINT `topic_ibfk_1` FOREIGN KEY (`teacher_no`) REFERENCES `teacher` (`teacher_no`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES (20, '小程序开发', '1.良好的页面设计和交互，2.后台使用java或PHP语言', 20190122, '是', 1);
INSERT INTO `topic` VALUES (21, '个人网站制作', '1.良好的页面设计和交互，2.后台使用java或', 20190121, '是', 1);
INSERT INTO `topic` VALUES (24, '个人博客系统', '1.良好的页面设计和交互，2.后台使用java或PHP语言', 20190121, '是', 1);
INSERT INTO `topic` VALUES (25, '校园点餐系统', '1.良好的页面设计和交互，2.后台使用java或PHP语言', 20190121, '是', 1);
INSERT INTO `topic` VALUES (26, '微信小程序开发', '1.超级靓号的设计页面', 20190121, '是', 1);
INSERT INTO `topic` VALUES (28, '后端开发', '1.超级靓号的超级语言', 20190122, '是', 1);
INSERT INTO `topic` VALUES (29, '微信小程序开始教程', '开发开发开发', 20190122, '是', 1);
INSERT INTO `topic` VALUES (39, 'springboot', '开发开发开发', 20190122, NULL, 1);
INSERT INTO `topic` VALUES (40, '我是测试课题', '开发开发开发', 20190122, NULL, 1);
INSERT INTO `topic` VALUES (41, '我是测试课题2', '哈哈哈哈哈哈', 20190121, '是', 1);
INSERT INTO `topic` VALUES (42, '我是测试课题3', '哈哈哈哈哈', 20190121, '是', 1);
INSERT INTO `topic` VALUES (43, '我是测试课题4', '哈啊哈哈哈哈哈', 20190121, '否', 1);
INSERT INTO `topic` VALUES (44, '我是白白的新增课题1', '我是白白的新增课题1hahahhahah ', 20190125, '是', 1);
INSERT INTO `topic` VALUES (45, '我是白白的新增课题2', '我是白白的新增课题2sdfsdfdsfdsf', 20190125, '是', 1);
INSERT INTO `topic` VALUES (46, '我是白白的新增课题3', '我是白白的新增课题3hahhahahahh', 20190125, NULL, 1);
INSERT INTO `topic` VALUES (47, '我是白白的新增课题4', '我是白白的新增课题4hhahahahahaha', 20190125, NULL, 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_no` varchar(12) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '123456' COMMENT '密码',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户名称',
  `identity` varchar(5) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '身份：学生、教师、管理员、超级管理员',
  `user_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`user_no`) USING BTREE,
  UNIQUE INDEX `user_no`(`user_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('20190121', 'e10adc3949ba59abbe56e057f20f883e', '阿肆', '教师', '');
INSERT INTO `user` VALUES ('20190122', 'e10adc3949ba59abbe56e057f20f883e', '韩信', '教师', NULL);
INSERT INTO `user` VALUES ('20190125', 'e10adc3949ba59abbe56e057f20f883e', '白白', '教师', '');
INSERT INTO `user` VALUES ('20190126', 'e10adc3949ba59abbe56e057f20f883e', '韩跳跳', '教师', '');
INSERT INTO `user` VALUES ('20190127', 'e10adc3949ba59abbe56e057f20f883e', '拜拜老师', '教师', NULL);
INSERT INTO `user` VALUES ('2019101039', 'e10adc3949ba59abbe56e057f20f883e', '小李四', '学生', NULL);
INSERT INTO `user` VALUES ('2019101040', 'e10adc3949ba59abbe56e057f20f883e', '张三', '学生', NULL);
INSERT INTO `user` VALUES ('2019101044', 'e10adc3949ba59abbe56e057f20f883e', '张飞', '学生', '');
INSERT INTO `user` VALUES ('2019101045', 'e10adc3949ba59abbe56e057f20f883e', '李白', '学生', NULL);
INSERT INTO `user` VALUES ('2019101046', 'e10adc3949ba59abbe56e057f20f883e', '阿珂', '学生', NULL);
INSERT INTO `user` VALUES ('2019101048', 'e10adc3949ba59abbe56e057f20f883e', '大乔', '学生', NULL);
INSERT INTO `user` VALUES ('2019101051', 'e10adc3949ba59abbe56e057f20f883e', '瑶', '学生', NULL);

SET FOREIGN_KEY_CHECKS = 1;
