/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/12/24 12:46:47                          */
/*==============================================================*/
DROP TABLE IF EXISTS `log`;

drop table if exists product_attribute_value;
drop table if exists product_member_price;
drop table if exists product_parameter_value;
drop table if exists product_product_image;
drop table if exists product_specification_value;
DROP TABLE IF EXISTS `product_specification`;
drop table if exists product_tag;
DROP TABLE IF EXISTS `product`;

drop table if exists d_attribute_option;
drop table if exists d_attribute;


drop table if exists d_parameter;
drop table if exists d_parameter_group;
drop table if exists d_tag;
drop table if exists d_specification_value;
drop table if exists d_specification;

DROP TABLE IF EXISTS `admin_role`;
DROP TABLE IF EXISTS `admin`;
DROP TABLE IF EXISTS `role_authority`;
DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `member`;
drop table if exists d_member_rank;
drop table if exists product_category_brand;
drop table if exists d_brand;
drop table if exists product_category;
drop table if exists plugin_config_attribute;
drop table if exists plugin_config;

CREATE TABLE `product_category` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `create_date` datetime NOT NULL,
 `modify_date` datetime NOT NULL,
 `orders` int(11) DEFAULT NULL,
 `grade` int(11) NOT NULL,
 `name` varchar(255) NOT NULL,
 `seo_description` varchar(255) DEFAULT NULL,
 `seo_keywords` varchar(255) DEFAULT NULL,
 `seo_title` varchar(255) DEFAULT NULL,
 `tree_path` varchar(255) NOT NULL,
 `parent_id` bigint(20) DEFAULT NULL,
 PRIMARY KEY (`id`),
 KEY `FK1B7971ADFBDD5B73` (`parent_id`)
);
alter table product_category add constraint FKD05546EDB2990399 foreign key (parent_id)
references product_category (id);

/*==============================================================*/
/* Table: d_attribute                                           */
/*==============================================================*/
create table d_attribute
(
  id                   bigint(20) not null auto_increment,
  create_date          datetime not null,
  modify_date          datetime not null,
  orders               int(11),
  name                  varchar(255) not null,
  property_index       int(11) not null,
  product_category     BIGINT(20) not null,
  primary key (id)
);

/*==============================================================*/
/* Table: d_attribute_option                                    */
/*==============================================================*/
create table d_attribute_option
(
  attribute            bigint(20) not null,
  options               varchar(255)
);
alter table d_attribute_option add constraint FK96E026D75E1B95F4 foreign key (attribute)
references d_attribute (id);
alter table d_attribute add constraint FK_attribute_category_id foreign key (product_category)
references product_category (id);
alter table d_attribute_option comment '属性项';

/*==============================================================*/
/* Table: d_brand                                               */
/*==============================================================*/
create table d_brand
(
  id                    BIGINT(20) not null auto_increment,
  create_Date          datetime,
  modify_Date          datetime,
  introduction          text,
  logo                  varchar(255),
  name                  varchar(255) not null,
  orders               int(11) not null,
  url                   varchar(255),
  type                  int(11),
  primary key (id)
);

alter table d_brand comment '品牌';

/*==============================================================*/
/* Table: d_member_rank                                         */
/*==============================================================*/
create table d_member_rank
(
  id                   bigint(20) not null auto_increment,
  create_date          datetime not null,
  modify_date          datetime not null,
  amount               decimal(21,6),
  is_default           bit(1) not null,
  is_special           bit(1) not null,
  name                  varchar(255) not null,
  scale                double not null,
  primary key (id),
  key amount (amount),
  key name (name)
);

create table d_parameter
(
  id                   bigint(20) not null auto_increment,
  create_date          datetime not null,
  modify_date          datetime not null,
  orders               int(11),
  name                  varchar(255) not null,
  parameter_group      bigint(20) not null,
  primary key (id)
);

alter table d_parameter comment '参数';

/*==============================================================*/
/* Table: d_parameter_group                                     */
/*==============================================================*/
create table d_parameter_group
(
  id                   bigint(20) not null auto_increment,
  create_date          datetime not null,
  modify_date          datetime not null,
  orders               int(11),
  name                  varchar(255) not null,
  category_id          bigint(20) not null,
  primary key (id)
);

alter table d_parameter add constraint FK8238FD2A818BF383 foreign key (parameter_group)
references d_parameter_group (id);
alter table d_parameter_group comment '参数组';

/*==============================================================*/
/* Table: d_tag                                                 */
/*==============================================================*/
create table d_tag
(
  id                   bigint(20) not null auto_increment,
  create_date          datetime not null,
  modify_date          datetime not null,
  orders               int(11),
  icon                  varchar(255),
  memo                  varchar(255),
  name                  varchar(255) not null,
  type                 int(11) not null,
  primary key (id)
);

alter table d_tag comment '标签';



CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `allocated_stock` int(11) NOT NULL,
  `attribute_value0` varchar(255) DEFAULT NULL,
  `attribute_value1` varchar(255) DEFAULT NULL,
  `attribute_value10` varchar(255) DEFAULT NULL,
  `attribute_value11` varchar(255) DEFAULT NULL,
  `attribute_value12` varchar(255) DEFAULT NULL,
  `attribute_value13` varchar(255) DEFAULT NULL,
  `attribute_value14` varchar(255) DEFAULT NULL,
  `attribute_value15` varchar(255) DEFAULT NULL,
  `attribute_value16` varchar(255) DEFAULT NULL,
  `attribute_value17` varchar(255) DEFAULT NULL,
  `attribute_value18` varchar(255) DEFAULT NULL,
  `attribute_value19` varchar(255) DEFAULT NULL,
  `attribute_value2` varchar(255) DEFAULT NULL,
  `attribute_value3` varchar(255) DEFAULT NULL,
  `attribute_value4` varchar(255) DEFAULT NULL,
  `attribute_value5` varchar(255) DEFAULT NULL,
  `attribute_value6` varchar(255) DEFAULT NULL,
  `attribute_value7` varchar(255) DEFAULT NULL,
  `attribute_value8` varchar(255) DEFAULT NULL,
  `attribute_value9` varchar(255) DEFAULT NULL,
  `cost` decimal(21,6) DEFAULT NULL,
  `full_name` varchar(255) NOT NULL,
  `hits` bigint(20) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `introduction` longtext,
  `is_gift` bit(1) NOT NULL,
  `is_list` bit(1) NOT NULL,
  `is_marketable` bit(1) NOT NULL,
  `is_top` bit(1) NOT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `market_price` decimal(21,6) NOT NULL,
  `memo` varchar(255) DEFAULT NULL,
  `month_hits` bigint(20) NOT NULL,
  `month_hits_date` datetime NOT NULL,
  `month_sales` bigint(20) NOT NULL,
  `month_sales_date` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  `point` bigint(20) NOT NULL,
  `price` decimal(21,6) NOT NULL,
  `sales` bigint(20) NOT NULL,
  `score` float NOT NULL,
  `score_count` bigint(20) NOT NULL,
  `seo_description` varchar(255) DEFAULT NULL,
  `seo_keywords` varchar(255) DEFAULT NULL,
  `seo_title` varchar(255) DEFAULT NULL,
  `sn` varchar(255) NOT NULL,
  `stock` int(11) DEFAULT NULL,
  `stock_memo` varchar(255) DEFAULT NULL,
  `total_score` bigint(20) NOT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `week_hits` bigint(20) NOT NULL,
  `week_hits_date` datetime NOT NULL,
  `week_sales` bigint(20) NOT NULL,
  `week_sales_date` datetime NOT NULL,
  `weight` int(11) DEFAULT NULL,
  `brand` bigint(20) DEFAULT NULL,
  `goods` bigint(20) NOT NULL,
  `product_category` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sn` (`sn`),
  KEY `FK7C9E82B0D7629117` (`product_category`),
  KEY `FK7C9E82B0FA9695CA` (`brand`),
  CONSTRAINT `FK7C9E82B0D7629117` FOREIGN KEY (`product_category`) REFERENCES `product_category` (`id`),
  CONSTRAINT `FK7C9E82B0FA9695CA` FOREIGN KEY (`brand`) REFERENCES `d_brand` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=301 DEFAULT CHARSET=utf8;



/*==============================================================*/
/* Table: product_attribute                                     */
/*==============================================================*/
create table product_attribute_value
(
  product_id           bigint(20),
  attribute_id         bigint(20),
  options             varchar(255)
);
alter table product_attribute_value add constraint FK_Reference_17 foreign key (product_id)
references product (id) on delete restrict on update restrict;

alter table product_attribute_value add constraint FK_Reference_18 foreign key (attribute_id)
references d_attribute (id) on delete restrict on update restrict;


alter table product_attribute comment '产品属性';

/*==============================================================*/
/* Table: product_member_price                                  */
/*==============================================================*/
create table product_member_price
(
  product_id           bigint(20) not null,
  member_price         decimal(19,2),
  member_rank_id       bigint(20) not null,
  primary key (product_id, member_rank_id)
);
alter table product_member_price add constraint FKDCCD88935CCD83AE foreign key (member_rank_id)
references d_member_rank (id);

alter table product_member_price add constraint FK_Reference_12 foreign key (product_id)
references product (id) on delete restrict on update restrict;
alter table product_member_price comment '会员价格';

/*==============================================================*/
/* Table: product_parameter_value                               */
/*==============================================================*/
create table product_parameter_value
(
  product_id           bigint(20) not null,
  parameter_value       varchar(255),
  parameter_value_key  bigint(20) not null,
  primary key (product_id, parameter_value_key)
);
alter table product_parameter_value add constraint FK_Reference_19 foreign key (product_id)
references product (id) on delete restrict on update restrict;

alter table product_parameter_value add constraint FK_Reference_22 foreign key (parameter_value_key)
references d_parameter (id) on delete restrict on update restrict;


alter table product_parameter_value comment '产品参数';

/*==============================================================*/
/* Table: product_product_image                                 */
/*==============================================================*/
create table product_product_image
(
  product_id           bigint(20),
  large                 varchar(255),
  medium                varchar(255),
  orders               int(11),
  source                varchar(255),
  thumbnail             varchar(255),
  title                 varchar(255)
);
alter table product_product_image add constraint FK_Reference_10 foreign key (product_id)
references product (id) on delete restrict on update restrict;
alter table product_product_image comment '产品图片';

/*==============================================================*/
/* Table: product_tag                                           */
/*==============================================================*/
create table product_tag
(
  product_id           bigint(20) not null,
  tag_id               bigint(20) not null,
  primary key (product_id, tag_id)
);

alter table product_tag add constraint FK2F6A998BC842716F foreign key (tag_id)
references d_tag (id);

alter table product_tag add constraint FK_Reference_9 foreign key (product_id)
references product (id) on delete restrict on update restrict;
alter table product_tag comment '标签';

/*==============================================================*/
/* Table: specification                                         */
/*==============================================================*/
create table d_specification
(
  id                   bigint(20) not null auto_increment,
  create_date          datetime not null,
  modify_date          datetime not null,
  orders               int(11),
  memo                  varchar(255),
  name                  varchar(255) not null,
  type                 int(11) not null,
  primary key (id)
);

/*==============================================================*/
/* Table: specification_value                                   */
/*==============================================================*/
create table d_specification_value
(
  id                   bigint(20) not null auto_increment,
  create_date          datetime not null,
  modify_date          datetime not null,
  orders               int(11),
  image                 varchar(255),
  name                  varchar(255) not null,
  specification        bigint(20) not null,
  primary key (id)
);


CREATE TABLE `product_specification` (
  `products` bigint(20) NOT NULL,
  `specifications` bigint(20) NOT NULL,
  PRIMARY KEY (`products`,`specifications`),
  KEY `FK622421B45096DE0F` (`products`),
  KEY `FK622421B4840DA38F` (`specifications`),
  CONSTRAINT `FK622421B45096DE0F` FOREIGN KEY (`products`) REFERENCES `product` (`id`),
  CONSTRAINT `FK622421B4840DA38F` FOREIGN KEY (`specifications`) REFERENCES `d_specification` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table product_specification_value
(
  product_id           bigint(20) not null,
  specification_values bigint(20) not null,
  primary key (product_id, specification_values)
);
alter table product_specification_value add constraint product_specification_value_ibfk_2 foreign key
  (specification_values)
references d_specification_value (id);

alter table product_specification_value add constraint FK_Reference_15 foreign key (product_id)
references product (id) on delete restrict on update restrict;

alter table d_specification_value add constraint FK_specification_value_specID foreign key (specification)
references d_specification (id);

CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `department` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `is_enabled` bit(1) NOT NULL,
  `is_locked` bit(1) NOT NULL,
  `locked_date` datetime DEFAULT NULL,
  `login_date` datetime DEFAULT NULL,
  `login_failure_count` int(11) NOT NULL,
  `login_ip` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_system` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `admin_role` (
  `admins` bigint(20) NOT NULL,
  `roles` bigint(20) NOT NULL,
  PRIMARY KEY (`admins`,`roles`),
  KEY `FKD291D6053FF548F7` (`roles`),
  KEY `FKD291D605A022690F` (`admins`),
  CONSTRAINT `FKD291D6053FF548F7` FOREIGN KEY (`roles`) REFERENCES `role` (`id`),
  CONSTRAINT `FKD291D605A022690F` FOREIGN KEY (`admins`) REFERENCES `admin` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `role_authority` (
  `role` bigint(20) NOT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  KEY `FKE06165D939B03AB0` (`role`),
  CONSTRAINT `FKE06165D939B03AB0` FOREIGN KEY (`role`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `amount` decimal(27,12) NOT NULL,
  `balance` decimal(27,12) NOT NULL,
  `birth` datetime DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `gender` int(11) DEFAULT NULL,
  `is_enabled` bit(1) NOT NULL,
  `is_locked` bit(1) NOT NULL,
  `locked_date` datetime DEFAULT NULL,
  `login_date` datetime DEFAULT NULL,
  `login_failure_count` int(11) NOT NULL,
  `login_ip` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `point` bigint(20) NOT NULL,
  `register_ip` varchar(255) NOT NULL,
  `safe_key_expire` datetime DEFAULT NULL,
  `safe_key_value` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  `area` bigint(20) DEFAULT NULL,
  `member_rank` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `FK92D398B937884F5B` (`member_rank`),
  CONSTRAINT `FK92D398B937884F5B` FOREIGN KEY (`member_rank`) REFERENCES `d_member_rank` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `content` longtext,
  `ip` varchar(255) NOT NULL,
  `operation` varchar(255) NOT NULL,
  `operator` varchar(255) DEFAULT NULL,
  `parameter` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;