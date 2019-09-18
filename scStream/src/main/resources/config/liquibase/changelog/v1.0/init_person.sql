drop table if exists person;
create table person(
`id` bigint(20) not null auto_increment,
`name` varchar(200) null comment '姓名',
`sex` varchar(200) null comment '性别',
`age` int(20) null comment '年龄',
`remark` varchar(255) null comment '简介',
primary key (`id`)
) engine=innodb auto_increment=1  comment='';
