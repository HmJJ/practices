drop table if exists ser_login;
create table ser_login(
`id` bigint(20) not null auto_increment,
`username` varchar(200) null comment '用户名 ',
`password` varchar(200) null comment '密码 ',
`email` varchar(200) null comment '邮箱 ',
`mobile` varchar(200) null comment '手机号 ',
`status` varchar(200) null comment '状态 '
primary key (`id`)
) engine=innodb auto_increment=1  comment='';
