drop table if exists ser_login_client;
create table ser_login_client(
`id` bigint(20) not null auto_increment,
`login_id` bigint(20) null comment '登录id ',
`client_id` varchar(200) null comment '登录方式 ',
`client_secret` varchar(200) null comment '登录秘钥 ',
`status` varchar(200) null comment '状态 '
primary key (`id`)
) engine=innodb auto_increment=1  comment='';
