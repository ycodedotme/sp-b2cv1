-- ----------------------------
-- Table structure for `shop_html`
-- ----------------------------
DROP TABLE IF EXISTS `shop_html`;
CREATE TABLE `shop_html` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


alter table SHOP_TabProduct add(createDate date);
update SHOP_TabProduct set createDate = CURRENT_DATE();