# 数据库


```sql
-- 群组
create table `group_info` (
    `group_id` int primary key,
    `group_name` varchar(64) not null comment '群组名称',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间'
);

-- 用户
create table `user_info` (
    `user_id` int primary key,
    `user_name` varchar(64) not null comment '用户名称',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间'
);

-- 地点
create table `region_info` (
    `region_id` int primary key,
    `region_longi` varchar(64) not null comment '经度',
    `region_lati` varchar(64) not null comment '纬度',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间'
);

-- 活动
create table `event_info` (
    `event_id` int primary key,
    `region_id` int not null comment '活动地点ID',
    `event_time` int not null comment '活动时间',
    `event_name` varchar(64) not null comment '活动名称',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    constraint `fk_event_region_id` foreign key (`region_id`) references `region_info`(`region_id`)
);

-- 群组-用户
create table `group_user` (
    `id` int primary key auto_increment,
    `group_id` int not null,
    `user_id` int not null,
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    constraint `fk_group_group_id` foreign key (`group_id`) references `group_info`(`group_id`),
    constraint `fk_group_user_id` foreign key (`user_id`) references `user_info`(`user_id`)
);

-- 训练集
create table `train_set` (
    `id` int primary key auto_increment,
    `group_id` int not null,
    `event_id` int not null,
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    constraint `fk_train_group_id` foreign key (`group_id`) references `group_info`(`group_id`),
    constraint `fk_train_event_id` foreign key (`event_id`) references `event_info`(`event_id`)
);

-- 测试集
create table `test_set` (
    `id` int primary key auto_increment,
    `group_id` int not null,
    `event_id` int not null,
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    constraint `fk_test_group_id` foreign key (`group_id`) references `group_info`(`group_id`),
    constraint `fk_test_event_id` foreign key (`event_id`) references `event_info`(`event_id`)
);
-- 推荐表
create table `rec_res` (
    `id` int primary key auto_increment,
    `group_id` int not null,
    `event_id` int not null,
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    constraint `fk_rec_group_id` foreign key (`group_id`) references `group_info`(`group_id`),
    constraint `fk_rec_event_id` foreign key (`event_id`) references `event_info`(`event_id`)
);
```

