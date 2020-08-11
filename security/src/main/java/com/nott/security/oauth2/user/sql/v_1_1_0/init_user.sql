drop table if exists ser_user;
create table ser_user(
`id` bigint(20) not null auto_increment,
`login_id` bigint(20) null comment '登录id ',
`person_id` bigint(20) null comment '用户id ',
`name` varchar(200) null comment '用户名 ',
`mobile` varchar(200) null comment '用户手机号 ',
`status` varchar(200) null comment '状态 '
primary key (`id`)
) engine=innodb auto_increment=1  comment='';
