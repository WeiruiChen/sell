-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: sell
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'admin','aa7b043b818888f5868936482a6f08be','328863397@qq.com','15084978667');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_detail` (
  `detail_id` varchar(32) NOT NULL,
  `order_id` varchar(32) NOT NULL,
  `product_id` varchar(32) NOT NULL,
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '当前价格,单位分',
  `product_quantity` int(11) NOT NULL COMMENT '数量',
  `product_icon` varchar(512) DEFAULT NULL COMMENT '小图',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`detail_id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES ('1234567810','11111112','11111112','皮蛋粥',2.20,3,'http://xxxx.jpg','2018-03-26 14:28:55','2018-03-26 14:28:55'),('1522116542045446295','1522116541912200230','123456','宫爆鸡丁',8.50,1,'http://xxxxx.png','2018-03-26 16:29:31','2018-03-26 16:29:31'),('1522116542096535083','1522116541912200230','123457','宫保鸡丁',10.50,2,'http://xxxxx.png','2018-03-26 16:29:32','2018-03-26 16:29:32'),('1522137214721746779','1522137214701824967','123456','宫爆鸡丁',8.50,2,'http://xxxxx.png','2018-03-27 07:53:35','2018-03-27 07:53:35'),('1522338438857105328','1522338438774326478','123456','宫爆鸡丁',0.01,1,'http://xxxxx.png','2018-03-29 15:47:20','2018-03-29 15:47:20'),('1522338578459116802','1522338578457698651','123456','宫爆鸡丁',0.01,1,'http://xxxxx.png','2018-03-29 15:49:39','2018-03-29 15:49:39'),('1522338695659722122','1522338695657176868','123456','宫爆鸡丁',0.01,1,'http://xxxxx.png','2018-03-29 15:51:37','2018-03-29 15:51:37'),('1522338784691995988','1522338784689879893','123456','宫爆鸡丁',0.01,1,'http://xxxxx.png','2018-03-29 15:53:06','2018-03-29 15:53:06'),('1522338919756123586','1522338919754793286','123456','宫爆鸡丁',0.01,2,'http://xxxxx.png','2018-03-29 15:55:21','2018-03-29 15:55:21'),('1522339341021176945','1522339341020476958','123456','宫爆鸡丁',0.01,1,'http://xxxxx.png','2018-03-29 16:02:22','2018-03-29 16:02:22'),('1522339608793378340','1522339608791354729','123456','宫爆鸡丁',0.01,1,'http://xxxxx.png','2018-03-29 16:06:50','2018-03-29 16:06:50'),('1522339899264473139','1522339899262284277','123456','宫爆鸡丁',0.01,1,'http://xxxxx.png','2018-03-29 16:11:40','2018-03-29 16:11:40'),('1522339970086393966','1522339969686923623','123456','宫爆鸡丁',0.01,1,'http://xxxxx.png','2018-03-29 16:12:51','2018-03-29 16:12:51'),('1522340201650561798','1522340201629236297','123456','宫爆鸡丁',0.01,1,'http://xxxxx.png','2018-03-29 16:16:43','2018-03-29 16:16:43'),('1522404870680396026','1522404870629148367','123456','宫爆鸡丁',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/e5f0c51c-7e47-4848-8cfb-ad1436975c9f','2018-03-30 10:14:32','2018-03-30 10:14:32'),('1522552204392580782','1522552204358139175','123456','宫爆鸡丁',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/e5f0c51c-7e47-4848-8cfb-ad1436975c9f','2018-04-01 03:10:06','2018-04-01 03:10:06'),('1522552376079715318','1522552376076737058','123456','宫爆鸡丁',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/e5f0c51c-7e47-4848-8cfb-ad1436975c9f','2018-04-01 03:12:58','2018-04-01 03:12:58'),('1522552547534730357','1522552547532620636','1522396891254534218','小米米',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/d5b3e440-9d21-4a70-ad6d-8b7770346827','2018-04-01 03:15:49','2018-04-01 03:15:49'),('1522744194063798657','1522744193996551049','123456','宫爆鸡丁',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/e5f0c51c-7e47-4848-8cfb-ad1436975c9f','2018-04-03 08:29:54','2018-04-03 08:29:54'),('1522744389871359355','1522744389870677097','1522396891254534218','小米米',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/d5b3e440-9d21-4a70-ad6d-8b7770346827','2018-04-03 08:33:09','2018-04-03 08:33:09'),('1522746140274948244','1522746140252454147','123456','宫爆鸡丁',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/e5f0c51c-7e47-4848-8cfb-ad1436975c9f','2018-04-03 09:02:20','2018-04-03 09:02:20'),('1522819688925575196','1522819688900334457','123456','宫爆鸡丁',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/984ec64f-2b03-41e3-b472-21b897e57724','2018-04-04 05:28:08','2018-04-04 05:28:08'),('1522828347275147959','1522828347256757133','123456','宫爆鸡丁',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/984ec64f-2b03-41e3-b472-21b897e57724','2018-04-04 07:52:27','2018-04-04 07:52:27'),('1522828486213820362','1522828486211914813','123456','宫爆鸡丁',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/984ec64f-2b03-41e3-b472-21b897e57724','2018-04-04 07:54:46','2018-04-04 07:54:46'),('1522829954687689153','1522829954685691881','1522829874802640589','北京烤鸭',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/e72e58e7-c082-46c8-8f59-19a17ca92ad5','2018-04-04 08:19:14','2018-04-04 08:19:14'),('1522853591185397270','1522853591150603087','123456','宫爆鸡丁',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/984ec64f-2b03-41e3-b472-21b897e57724','2018-04-04 14:53:11','2018-04-04 14:53:11'),('1522853591226619361','1522853591150603087','1522396891254534218','小米米',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/d5b3e440-9d21-4a70-ad6d-8b7770346827','2018-04-04 14:53:11','2018-04-04 14:53:11'),('1522901573184706065','1522901573125976475','123456','宫爆鸡丁',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/984ec64f-2b03-41e3-b472-21b897e57724','2018-04-05 04:12:53','2018-04-05 04:12:53'),('1522901573223151607','1522901573125976475','1522396891254534218','小米米',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/d5b3e440-9d21-4a70-ad6d-8b7770346827','2018-04-05 04:12:53','2018-04-05 04:12:53'),('1522901684647573410','1522901684646441873','1522396891254534218','小米米',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/d5b3e440-9d21-4a70-ad6d-8b7770346827','2018-04-05 04:14:44','2018-04-05 04:14:44'),('1522902828953125204','1522902828952821662','1522396891254534218','小米米',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/d5b3e440-9d21-4a70-ad6d-8b7770346827','2018-04-05 04:33:48','2018-04-05 04:33:48'),('1522902828956873376','1522902828952821662','1522802818333379866','豌杂',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/c28fe215-e138-4197-a5ee-8f3e5b29563f','2018-04-05 04:33:49','2018-04-05 04:33:49'),('1523199852424282066','1523199852369690765','123456','宫爆鸡丁',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/984ec64f-2b03-41e3-b472-21b897e57724','2018-04-08 15:04:12','2018-04-08 15:04:12'),('1523199852486746835','1523199852369690765','1522396891254534218','小米米',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/d5b3e440-9d21-4a70-ad6d-8b7770346827','2018-04-08 15:04:12','2018-04-08 15:04:12'),('1523201133440681540','1523201133437290521','123456','宫爆鸡丁',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/984ec64f-2b03-41e3-b472-21b897e57724','2018-04-08 15:25:33','2018-04-08 15:25:33'),('1523201133447285108','1523201133437290521','1522396891254534218','小米米',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/d5b3e440-9d21-4a70-ad6d-8b7770346827','2018-04-08 15:25:33','2018-04-08 15:25:33'),('1523201564414262530','1523201564410247831','1522802818333379866','豌杂',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/c28fe215-e138-4197-a5ee-8f3e5b29563f','2018-04-08 15:32:44','2018-04-08 15:32:44'),('1523279617566163488','1523279617564849988','123456','宫爆鸡丁',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/984ec64f-2b03-41e3-b472-21b897e57724','2018-04-09 13:13:37','2018-04-09 13:13:37'),('1523457317006559724','1523457316936939548','123456','宫爆鸡丁',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/984ec64f-2b03-41e3-b472-21b897e57724','2018-04-11 14:35:17','2018-04-11 14:35:17'),('1523457510827479771','1523457510825230220','123456','宫爆鸡丁',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/984ec64f-2b03-41e3-b472-21b897e57724','2018-04-11 14:38:30','2018-04-11 14:38:30'),('1523457510831787310','1523457510825230220','1522396891254534218','小米米',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/d5b3e440-9d21-4a70-ad6d-8b7770346827','2018-04-11 14:38:30','2018-04-11 14:38:30'),('1523457510840884551','1523457510825230220','1522802818333379866','豌杂',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/c28fe215-e138-4197-a5ee-8f3e5b29563f','2018-04-11 14:38:30','2018-04-11 14:38:30'),('1523459289299900508','1523459289294297510','123456','宫爆鸡丁',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/984ec64f-2b03-41e3-b472-21b897e57724','2018-04-11 15:08:09','2018-04-11 15:08:09'),('1523459384471878491','1523459384470388346','123456','宫爆鸡丁',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/984ec64f-2b03-41e3-b472-21b897e57724','2018-04-11 15:09:44','2018-04-11 15:09:44'),('1523459450237171333','1523459450235585678','123456','宫爆鸡丁',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/984ec64f-2b03-41e3-b472-21b897e57724','2018-04-11 15:10:50','2018-04-11 15:10:50'),('1523459450242381557','1523459450235585678','123457','宫保鸡丁',10.50,1,'http://p4qg9bqm6.bkt.clouddn.com/e5f0c51c-7e47-4848-8cfb-ad1436975c9f','2018-04-11 15:10:50','2018-04-11 15:10:50'),('1523619706143788285','1523619706065217538','123456','宫爆鸡丁',0.01,2,'http://p4qg9bqm6.bkt.clouddn.com/984ec64f-2b03-41e3-b472-21b897e57724','2018-04-13 11:41:46','2018-04-13 11:41:46'),('1524280648781808551','1524280648777118885','1522802818333379866','豌杂',0.01,2,'http://p4qg9bqm6.bkt.clouddn.com/c28fe215-e138-4197-a5ee-8f3e5b29563f','2018-04-21 03:17:28','2018-04-21 03:17:28'),('1524282769189826934','1524282769186276658','1522396891254534218','小米米',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/d5b3e440-9d21-4a70-ad6d-8b7770346827','2018-04-21 03:52:49','2018-04-21 03:52:49'),('1524282898287421631','1524282898285907520','1522396891254534218','小米米',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/d5b3e440-9d21-4a70-ad6d-8b7770346827','2018-04-21 03:54:58','2018-04-21 03:54:58'),('1524283040298442439','1524283040296661037','1522396891254534218','小米米',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/d5b3e440-9d21-4a70-ad6d-8b7770346827','2018-04-21 03:57:20','2018-04-21 03:57:20'),('1524283192465141144','1524283192463707512','1522802818333379866','豌杂',0.01,1,'http://p4qg9bqm6.bkt.clouddn.com/c28fe215-e138-4197-a5ee-8f3e5b29563f','2018-04-21 03:59:52','2018-04-21 03:59:52');
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_master`
--

DROP TABLE IF EXISTS `order_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_master` (
  `order_id` varchar(32) NOT NULL,
  `buyer_name` varchar(32) DEFAULT NULL COMMENT '买家名字',
  `buyer_phone` varchar(32) DEFAULT NULL COMMENT '买家电话',
  `buyer_address` varchar(128) NOT NULL COMMENT '买家地址',
  `buyer_openid` varchar(64) NOT NULL COMMENT '买家微信openid',
  `order_amount` decimal(8,2) NOT NULL COMMENT '订单总金额',
  `order_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '订单状态, 默认为新下单',
  `pay_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '支付状态, 默认未支付',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `seller_id` varchar(32) DEFAULT NULL,
  `is_end` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`order_id`),
  KEY `idx_buyer_openid` (`buyer_openid`),
  KEY `fk_seller_idx` (`seller_id`),
  CONSTRAINT `fk_seller` FOREIGN KEY (`seller_id`) REFERENCES `seller_info` (`seller_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_master`
--

LOCK TABLES `order_master` WRITE;
/*!40000 ALTER TABLE `order_master` DISABLE KEYS */;
INSERT INTO `order_master` VALUES ('12345678','CYH','15084978667','湖南','11011110',12.50,0,0,'2018-03-26 14:25:16','2018-03-26 14:25:16',NULL,0),('1522116541912200230','CYH','15084978667','湖南大学','253644582',29.50,0,1,'2018-03-26 16:29:32','2018-03-27 05:21:35',NULL,0),('1522137214701824967','张三','15084978667','湖南大学','454564545fsdfsd',17.00,0,0,'2018-03-27 07:53:35','2018-03-27 07:53:35',NULL,0),('1522338438774326478','陈裕豪','15084978667','湖南大学','olgUa07TgzfQs_wK6etGjjtAHGTk',0.01,0,0,'2018-03-29 15:47:20','2018-03-29 15:47:20',NULL,0),('1522338578457698651','陈裕豪','15084978667','湖南大学','olgUa07TgzfQs_wK6etGjjtAHGTk',0.01,0,0,'2018-03-29 15:49:40','2018-03-29 15:49:40',NULL,0),('1522338695657176868','陈裕豪','15084978667','湖大','olgUa07TgzfQs_wK6etGjjtAHGTk',0.01,2,0,'2018-03-29 15:51:37','2018-03-30 07:07:48',NULL,0),('1522338784689879893','陈裕豪','15084978667','湖大','olgUa07TgzfQs_wK6etGjjtAHGTk',0.01,2,0,'2018-03-29 15:53:06','2018-03-30 03:14:48',NULL,0),('1522338919754793286','陈裕豪','15084978667','湖大','olgUa07TgzfQs_wK6etGjjtAHGTk',0.02,1,0,'2018-03-29 15:55:21','2018-03-30 07:07:38',NULL,0),('1522339341020476958','陈裕豪','15084978667','155','olgUa07TgzfQs_wK6etGjjtAHGTk',0.01,2,0,'2018-03-29 16:02:22','2018-03-30 03:04:35',NULL,0),('1522339608791354729','湖南大学','15084978667','55','olgUa07TgzfQs_wK6etGjjtAHGTk',0.01,2,0,'2018-03-29 16:06:50','2018-03-30 03:08:36',NULL,0),('1522339899262284277','陈裕豪','15084978667','哈哈','olgUa07TgzfQs_wK6etGjjtAHGTk',0.01,0,0,'2018-03-29 16:11:40','2018-03-29 16:11:40',NULL,0),('1522339969686923623','湖南大学','15084978667','555','olgUa07TgzfQs_wK6etGjjtAHGTk',0.01,0,0,'2018-03-29 16:12:51','2018-03-29 16:12:51',NULL,0),('1522340201629236297','chu','15084978667','555','olgUa07TgzfQs_wK6etGjjtAHGTk',0.01,0,0,'2018-03-29 16:16:43','2018-03-29 16:16:43',NULL,0),('1522404870629148367','陈裕豪','15084978667','555','olgUa07TgzfQs_wK6etGjjtAHGTk',0.01,2,1,'2018-03-30 10:14:32','2018-03-30 10:15:01',NULL,0),('1522552204358139175','莫小江','15084978664','555','olgUa07TgzfQs_wK6etGjjtAHGTk',0.01,1,1,'2018-04-01 03:10:06','2018-04-02 07:32:15','abc',0),('1522552376076737058','MM','15084978667','555','olgUa07TgzfQs_wK6etGjjtAHGTk',0.01,2,1,'2018-04-01 03:12:58','2018-04-02 07:32:15','abc',0),('1522552547532620636','hhh','15084978667','555','olgUa07TgzfQs_wK6etGjjtAHGTk',0.01,1,1,'2018-04-01 03:15:49','2018-04-02 07:32:15','abc',0),('1522744193996551049','hh','15084978667','555','olgUa07TgzfQs_wK6etGjjtAHGTk',0.01,2,1,'2018-04-03 08:29:54','2018-04-03 08:30:32','abc',0),('1522744389870677097','mo','15577545754','122','olgUa07TgzfQs_wK6etGjjtAHGTk',0.01,2,1,'2018-04-03 08:33:09','2018-04-03 08:33:44','abc',0),('1522746140252454147','好好','158','55','olgUa07TgzfQs_wK6etGjjtAHGTk',0.01,2,1,'2018-04-03 09:02:20','2018-04-03 09:03:10','abc',0),('1522819688900334457','陈裕豪','15084978667','5','olgUa07TgzfQs_wK6etGjjtAHGTk',0.01,2,1,'2018-04-04 05:28:09','2018-04-04 05:28:53','abc',0),('1522828347256757133','车','15084978667','9','olgUa07TgzfQs_wK6etGjjtAHGTk',0.01,2,1,'2018-04-04 07:52:27','2018-04-04 07:52:54','abc',0),('1522828486211914813','gg','15084978667','4','olgUa07TgzfQs_wK6etGjjtAHGTk',0.01,1,1,'2018-04-04 07:54:46','2018-04-04 07:55:12','abc',0),('1522829954685691881','chenyuhao','15084978667','6','olgUa07TgzfQs_wK6etGjjtAHGTk',0.01,1,1,'2018-04-04 08:19:14','2018-04-04 08:19:41','add',0),('1522853591150603087','陈','15084978667','6','olgUa07TgzfQs_wK6etGjjtAHGTk',0.02,1,1,'2018-04-04 14:53:11','2018-04-04 14:54:03','abc',0),('1522901573125976475','宣章洋','13168778188','4','olgUa07LaAn7v7N3TeOeeGD2wYMA',0.02,1,1,'2018-04-05 04:12:53','2018-04-05 04:15:15','abc',0),('1522901684646441873','王思林','17673161048','1','olgUa0zsmahXVt_5GrCoe3Z8P59w',0.01,1,1,'2018-04-05 04:14:44','2018-04-05 04:15:24','abc',0),('1522902828952821662','宣','13166666','3','olgUa07LaAn7v7N3TeOeeGD2wYMA',0.02,1,1,'2018-04-05 04:33:49','2018-04-05 04:35:05','abc',0),('1523199852369690765','陈','15084978667','2','olgUa07TgzfQs_wK6etGjjtAHGTk',0.02,1,1,'2018-04-08 15:04:12','2018-04-09 14:57:37','abc',0),('1523201133437290521','表','15084978667','2','olgUa07TgzfQs_wK6etGjjtAHGTk',0.02,1,1,'2018-04-08 15:25:33','2018-04-09 15:10:26','abc',0),('1523201564410247831','等等','150849786','2','olgUa07TgzfQs_wK6etGjjtAHGTk',0.01,1,1,'2018-04-08 15:32:44','2018-04-13 11:55:10','abc',0),('1523279617564849988','陈','158','5','olgUa07TgzfQs_wK6etGjjtAHGTk',0.01,2,1,'2018-04-09 13:13:37','2018-04-09 14:59:31','abc',0),('1523457316936939548','纳尼','15084978667','5','olgUa07TgzfQs_wK6etGjjtAHGTk',0.01,2,1,'2018-04-11 14:35:17','2018-04-11 14:36:50','abc',0),('1523457510825230220','宝宝','15084978667','3','olgUa07TgzfQs_wK6etGjjtAHGTk',0.03,1,0,'2018-04-11 14:38:30','2018-04-20 15:49:40','abc',0),('1523459289294297510','哈哈','150849786441','2','olgUa07TgzfQs_wK6etGjjtAHGTk',0.01,0,1,'2018-04-11 15:08:09','2018-04-11 15:08:19','abc',0),('1523459384470388346','陈','1588','3','olgUa07TgzfQs_wK6etGjjtAHGTk',0.01,2,0,'2018-04-11 15:09:44','2018-04-11 15:10:05','abc',0),('1523459450235585678','测绘','jj','8','olgUa07TgzfQs_wK6etGjjtAHGTk',10.51,0,1,'2018-04-11 15:10:50','2018-04-11 15:10:58','abc',0),('1523619706065217538','王思林','17673161048','1','olgUa0zsmahXVt_5GrCoe3Z8P59w',0.02,2,1,'2018-04-13 11:41:46','2018-04-13 11:42:39','abc',0),('1524280648777118885','','','5','olgUa07TgzfQs_wK6etGjjtAHGTk',0.02,0,1,'2018-04-21 03:17:28','2018-04-21 03:17:38','abc',0),('1524282769186276658','','','5','olgUa07TgzfQs_wK6etGjjtAHGTk',0.01,0,1,'2018-04-21 03:52:49','2018-04-21 03:53:02','abc',0),('1524282898285907520','','','5','olgUa07TgzfQs_wK6etGjjtAHGTk',0.01,0,1,'2018-04-21 03:54:58','2018-04-21 03:55:08','abc',0),('1524283040296661037','','','5','olgUa07TgzfQs_wK6etGjjtAHGTk',0.01,0,1,'2018-04-21 03:57:20','2018-04-21 03:57:27','abc',0),('1524283192463707512','','','5','olgUa07TgzfQs_wK6etGjjtAHGTk',0.01,0,1,'2018-04-21 03:59:52','2018-04-21 04:00:01','abc',0);
/*!40000 ALTER TABLE `order_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(64) NOT NULL COMMENT '类目名字',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `seller_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `category_type_UNIQUE` (`category_type`),
  KEY `fk_seller2_idx` (`seller_id`),
  CONSTRAINT `fk_seller2` FOREIGN KEY (`seller_id`) REFERENCES `seller_info` (`seller_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` VALUES (1,'热销的',2,'2018-03-25 14:10:38','2018-04-02 07:44:49','abc'),(2,'你最爱',10,'2018-03-25 14:21:50','2018-04-02 07:44:49','abc'),(3,'女生最爱',5,'2018-03-30 09:04:26','2018-04-02 07:44:49','abc'),(4,'我不喜欢',15,'2018-04-02 07:49:16','2018-04-02 07:49:43','abc'),(5,'今日特卖',18,'2018-04-04 08:16:56','2018-04-04 08:22:11','add'),(6,'今日特价',55,'2018-04-08 04:30:03','2018-04-08 07:37:32','abc');
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_info`
--

DROP TABLE IF EXISTS `product_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_info` (
  `product_id` varchar(32) NOT NULL,
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '单价',
  `product_stock` int(11) NOT NULL COMMENT '库存',
  `product_description` varchar(64) DEFAULT NULL COMMENT '描述',
  `product_icon` varchar(512) DEFAULT NULL COMMENT '小图',
  `product_status` tinyint(3) DEFAULT '0' COMMENT '商品状态,0正常1下架',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `seller_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `fk_sell1_idx` (`seller_id`),
  CONSTRAINT `fk_sell1` FOREIGN KEY (`seller_id`) REFERENCES `seller_info` (`seller_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_info`
--

LOCK TABLES `product_info` WRITE;
/*!40000 ALTER TABLE `product_info` DISABLE KEYS */;
INSERT INTO `product_info` VALUES ('123456','宫爆鸡丁',0.01,0,'好吃','http://p4qg9bqm6.bkt.clouddn.com/21efeb45-81c4-42e0-9c97-86da8882667e',0,2,'2018-03-26 10:30:53','2018-04-21 02:47:57','abc'),('123457','宫保鸡丁',10.50,199,'好吃','http://p4qg9bqm6.bkt.clouddn.com/e5f0c51c-7e47-4848-8cfb-ad1436975c9f',0,10,'2018-03-26 11:01:31','2018-04-11 15:10:50','abc'),('1522396891254534218','小米米',0.01,289,'很好吃','http://p4qg9bqm6.bkt.clouddn.com/d5b3e440-9d21-4a70-ad6d-8b7770346827',0,2,'2018-03-30 08:01:33','2018-04-21 03:57:20','abc'),('1522655793747755285','鸡腿饭',0.01,500,'很好吃','http://p4qg9bqm6.bkt.clouddn.com/6b6c1714-4cfc-435c-8a8e-1d44601c3d3c',0,15,'2018-04-02 07:56:33','2018-04-04 00:47:17','abc'),('1522802818333379866','豌杂',0.01,194,'加麻不加辣','http://p4qg9bqm6.bkt.clouddn.com/c28fe215-e138-4197-a5ee-8f3e5b29563f',0,10,'2018-04-04 00:46:58','2018-04-21 03:59:52','abc'),('1522829874802640589','北京烤鸭',0.01,19,'贼好吃','http://p4qg9bqm6.bkt.clouddn.com/c2743fc7-101d-4f6a-989c-f0ec3555b85e',0,18,'2018-04-04 08:17:54','2018-04-04 08:23:04','add'),('1523157499298504132','测试食品',0.01,20,'测试',NULL,0,10,'2018-04-08 03:18:19','2018-04-08 03:18:19','abc');
/*!40000 ALTER TABLE `product_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seller_info`
--

DROP TABLE IF EXISTS `seller_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seller_info` (
  `seller_id` varchar(32) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `openid` varchar(64) DEFAULT NULL COMMENT '微信openid',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `merchant_name` varchar(255) NOT NULL,
  `name` varchar(64) NOT NULL,
  `id_card` varchar(255) NOT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `business_license` varchar(255) DEFAULT NULL,
  `phone` varchar(64) NOT NULL,
  `audit` tinyint(3) NOT NULL DEFAULT '0',
  `key` varchar(255) NOT NULL COMMENT '商户密钥',
  `trial` int(11) DEFAULT NULL,
  `trial_time` timestamp NULL DEFAULT NULL,
  `visit_count` int(11) DEFAULT '0',
  PRIMARY KEY (`seller_id`),
  UNIQUE KEY `phone_UNIQUE` (`phone`),
  UNIQUE KEY `key_UNIQUE` (`key`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='卖家信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seller_info`
--

LOCK TABLES `seller_info` WRITE;
/*!40000 ALTER TABLE `seller_info` DISABLE KEYS */;
INSERT INTO `seller_info` VALUES ('1523407078672271270','yuhh','03e96e44dca7b2a8c5d5635289511b07',NULL,'2018-04-11 00:37:58','2018-04-23 05:11:48','微信小店','陈裕豪','6217907500001304911',NULL,NULL,'15577545754',0,'1523407078672949241',10,'2018-04-30 15:46:01',0),('1524459889093780344','cyh333','e54ac76893d8ed6f5d1533546a13c216',NULL,'2018-04-23 05:04:49','2018-04-23 05:10:20','yuhao测试','陈裕豪','6217907500001304911',NULL,NULL,'15575123151',0,'28c344cc-4051-4cdd-ab1b-7f243e72fd43',0,NULL,NULL),('abc','admin','0dcd7eb16f1f11ca8e5664edc9b183f0','aaaa','2018-03-31 14:08:50','2018-04-25 13:37:09','admin','admin','6217907500001304911','328863397@qq.com','http://p4qg9bqm6.bkt.clouddn.com/e5f0c51c-7e47-4848-8cfb-ad1436975c9f','15084978667',2,'aaaaa',20,'2018-05-13 05:02:38',9),('add','yuhao','1f715e10a22b4e2f6937083017ecf25a','bbbb','2018-04-04 08:15:46','2018-04-23 05:11:48','ddd','aaa','6217907500001304911','328863397@qq.com','http://p4qg9bqm6.bkt.clouddn.com/e5f0c51c-7e47-4848-8cfb-ad1436975c9f','15084978668',2,'bbbbb',0,'2018-04-19 15:48:44',0);
/*!40000 ALTER TABLE `seller_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-14  9:32:44
