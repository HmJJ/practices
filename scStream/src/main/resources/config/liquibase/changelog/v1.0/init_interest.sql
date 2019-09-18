drop table if exists interest;
create table interest(
`id` bigint(20) not null auto_increment,
`name` varchar(200) null comment '名称',
`person_id` bigint(20) not null null comment 'personId',
`remark` varchar(255) null comment '简介',
primary key (`id`)
) engine=innodb auto_increment=1  comment='';
