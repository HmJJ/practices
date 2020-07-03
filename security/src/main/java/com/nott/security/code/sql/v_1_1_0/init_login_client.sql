drop table if exists ser_login_client;
create table ser_login_client(
`id` bigint(20) not null auto_increment,
`login_id` bigint(20) null comment '登录id ',
`client_id` varchar(200) null comment '登录方式 ',
`client_secret` varchar(200) null comment '登录秘钥 ',
`status` varchar(200) null comment '状态 ',
`owner_id` bigint(20) null comment '拥有者ID',
`owner_group_id` bigint(20) null comment '拥有部门ID',
`object_state` varchar(10) null comment '对象状态：normal-有效 disabled-失效',
`created_by`  bigint(20) null,
`created_time`  datetime null,
`last_updated_by`  bigint(20) null,
`last_updated_time`  datetime null,
primary key (`id`)
) engine=innodb auto_increment=1  comment='';
