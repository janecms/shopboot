/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/12/24 12:46:47                          */
/*==============================================================*/


drop table if exists d_attribute;

drop table if exists d_attribute_option;

drop table if exists d_brand;

drop table if exists d_member_rank;

drop table if exists d_parameter;

drop table if exists d_parameter_group;

drop table if exists d_tag;

drop table if exists product;

drop table if exists product_attribute;

drop table if exists product_category;

drop table if exists product_member_price;

drop table if exists product_parameter_value;

drop table if exists product_product_image;

drop table if exists product_specification_value;

drop table if exists product_tag;

drop table if exists d_specification;

drop table if exists d_specification_value;

/*==============================================================*/
/* User: shopxx_3                                               */
/*==============================================================*/
create user shopxx_3;

/*==============================================================*/
/* User: shopxxboot                                             */
/*==============================================================*/
create user shopxxboot;

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
  product_category     varchar(32) not null,
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

alter table d_attribute_option comment '属性项';

/*==============================================================*/
/* Table: d_brand                                               */
/*==============================================================*/
create table d_brand
(
  id                    varchar(32) not null,
  create_Date          datetime,
  modify_Date          datetime,
  introduction          text,
  logo                  varchar(255),
  name                  varchar(255) not null,
  orders               int(11) not null,
  url                   varchar(255),
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

/*==============================================================*/
/* Table: d_parameter                                           */
/*==============================================================*/
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

/*==============================================================*/
/* Table: product                                               */
/*==============================================================*/
create table product
(
  product_id            bigint(20) not null,
  create_Date          datetime comment '创建日期',
  modify_Date          datetime comment '修改日期',
  description           text comment '描述',
  freeze_Store         int(11) not null comment '被占用库存数',
  html_File_Path        varchar(255) not null comment 'HTML静态文件路径',
  isBest               bit(1) not null comment '是否精品推荐',
  isHot                bit(1) not null comment '是否热销推荐',
  is_Marketable        bit(1) not null comment '是否上架',
  is_New               bit(1) not null comment '是否新品推荐',
  market_Price         decimal(15,5) not null comment '市场售价',
  meta_Description      text comment '页面描述',
  meta_Keywords         text comment '页面关键词',
  name                  varchar(255) not null comment '商品名称',
  point                int(11) not null comment '积分',
  price                decimal(15,5) not null comment '本店售价',
  Image_List_Store      text comment '商品图片路径存储',
  product_sn            varchar(255) not null comment '货号',
  store                int(11) comment '商品库存数量',
  weight               double not null comment '商品重量',
  weight_Unit          int(11) not null comment '重量单位',
  brand_id              varchar(32) comment '商品品牌',
  category_id           varchar(32) not null comment '产品分类',
  key AK_pk_product_id (product_id)
);

/*==============================================================*/
/* Table: product_attribute                                     */
/*==============================================================*/
create table product_attribute
(
  attribute_id         bigint(20),
  product_id           bigint(20),
  options             varchar(255)
);

alter table product_attribute comment '产品属性';

/*==============================================================*/
/* Table: product_category                                      */
/*==============================================================*/
create table product_category
(
  id                    varchar(32) not null,
  create_Date          datetime,
  modify_Date          datetime,
  meta_Description      text comment '页面描述',
  meta_Keywords         text comment '页面关键词',
  name                  varchar(255) not null,
  orders               int(11) not null comment '排序',
  path                  text comment '树路径',
  parent_id             varchar(32) comment '上级分类',
  primary key (id)
);

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

/*==============================================================*/
/* Table: product_specification_value                           */
/*==============================================================*/
create table product_specification_value
(
  product_id           bigint(20) not null,
  specifications       bigint(20),
  specification_values bigint(20) not null,
  primary key (product_id, specification_values)
);
alter table d_specification_value add constraint FK_specification_value_specID foreign key (specification)
references d_specification (id);

alter table product_specification_value add constraint product_specification_value_ibfk_2 foreign key
  (specification_values)
references d_specification_value (id);

alter table product_specification_value add constraint FK_Reference_15 foreign key (product_id)
references product (product_id) on delete restrict on update restrict;

alter table product_specification_value add constraint FK_Reference_16 foreign key (specifications)
references d_specification (id) on delete restrict on update restrict;

alter table d_attribute add constraint FK_attribute_category_id foreign key (product_category)
references product_category (id);

alter table d_attribute_option add constraint FK96E026D75E1B95F4 foreign key (attribute)
references d_attribute (id);

alter table d_parameter add constraint FK8238FD2A818BF383 foreign key (parameter_group)
references d_parameter_group (id);

alter table product add constraint FK50C664CF59CF1676 foreign key (category_id)
references product_category (id);

alter table product add constraint FK50C664CFF378EF16 foreign key (brand_id)
references d_brand (id);

alter table product_attribute add constraint FK_Reference_17 foreign key (product_id)
references product (product_id) on delete restrict on update restrict;

alter table product_attribute add constraint FK_Reference_18 foreign key (attribute_id)
references d_attribute (id) on delete restrict on update restrict;

alter table product_category add constraint FKD05546EDB2990399 foreign key (parent_id)
references product_category (id);

alter table product_member_price add constraint FKDCCD88935CCD83AE foreign key (member_rank_id)
references d_member_rank (id);

alter table product_member_price add constraint FK_Reference_12 foreign key (product_id)
references product (product_id) on delete restrict on update restrict;

alter table product_parameter_value add constraint FK_Reference_19 foreign key (product_id)
references product (product_id) on delete restrict on update restrict;

alter table product_parameter_value add constraint FK_Reference_22 foreign key (parameter_value_key)
references d_parameter (id) on delete restrict on update restrict;

alter table product_product_image add constraint FK_Reference_10 foreign key (product_id)
references product (product_id) on delete restrict on update restrict;

alter table product_tag add constraint FK2F6A998BC842716F foreign key (tag_id)
references d_tag (id);

alter table product_tag add constraint FK_Reference_9 foreign key (product_id)
references product (product_id) on delete restrict on update restrict;




DROP TABLE IF EXISTS `admin`;
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

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_system` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `admins` bigint(20) NOT NULL,
  `roles` bigint(20) NOT NULL,
  PRIMARY KEY (`admins`,`roles`),
  KEY `FKD291D6053FF548F7` (`roles`),
  KEY `FKD291D605A022690F` (`admins`),
  CONSTRAINT `FKD291D6053FF548F7` FOREIGN KEY (`roles`) REFERENCES `role` (`id`),
  CONSTRAINT `FKD291D605A022690F` FOREIGN KEY (`admins`) REFERENCES `admin` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `role_authority`;
CREATE TABLE `role_authority` (
  `role` bigint(20) NOT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  KEY `FKE06165D939B03AB0` (`role`),
  CONSTRAINT `FKE06165D939B03AB0` FOREIGN KEY (`role`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `member`;
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



DROP TABLE IF EXISTS `log`;
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