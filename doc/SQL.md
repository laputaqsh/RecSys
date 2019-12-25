# 数据库

```sql
-- user
create table `user` (
    `id` int(11) primary key,
    `uid` varchar(63) not null,
    `name` varchar(127) not null,
    `type` varchar(31) not null,
    `avatar` varchar(255),
    `large_avatar` varchar(255),
    `alt` varchar(255)
);

-- event
create table `event` (
    `id` int(11) primary key,
    `owner_id` int(11) not null comment 'user_id',
    `title` varchar(255) not null,
    `content` longtext not null,
    `category` varchar(31) not null,
    `category_name` varchar(63) not null,
    `begin_time` datetime not null,
    `end_time` datetime not null
);

-- event_user
create table `event_user` (
    `event_id` int(11) not null,
    `user_id` int(11) not null,
    `user_type` varchar(31) not null comment 'participant/owner/wisher'
);

-- relation(following: user_id follows related_user, follower: user_id is related_user's follower)
create table `relation` (
    
);

```

```sql
-- 地点
create table `region_info` (
    `region_id` int primary key auto_increment,
    `region_name` varchar(64) not null comment '地点名称',
    `region_longi` varchar(64) not null comment '经度',
    `region_lati` varchar(64) not null comment '纬度',
    `create_time` timestamp not null default current_timestamp comment '创建时间'
);

-- 类别
create table `type_info` (
    `type_id` int primary key auto_increment,
    `type_name` varchar(64) not null comment '类别名称',
    `type_detail` text not null comment '类别详情',
    `create_time` timestamp not null default current_timestamp comment '创建时间'
);

-- 用户
create table `user_info` (
    `user_id` int primary key auto_increment,
    `user_pw` varchar(64) not null comment '群组密码',
    `user_name` varchar(64) not null comment '群组名称',
    `user_detail` text not null comment '群组详情',
    `user_region_id` int not null comment '所处地点ID',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    constraint `fk_user_region_id` foreign key (`user_region_id`) references `region_info`(`region_id`)
);

-- 群组
create table `group_info` (
    `group_id` int primary key auto_increment,
    `group_name` varchar(64) not null comment '群组名称',
    `group_detail` text not null comment '群组详情',
    `group_user_id` int not null comment '主办用户ID',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    constraint `fk_group_user_id` foreign key (`group_user_id`) references `user_info`(`user_id`)
);

-- 活动
create table `event_info` (
    `event_id` int primary key auto_increment,
    `event_name` varchar(64) not null comment '活动名称',
    `event_notice` text not null comment '活动须知',
    `event_detail` text not null comment '活动详情',
    `event_type_id` int not null comment '活动类别ID',
    `event_group_id` int not null comment '主办群组ID',
    `event_region_id` int not null comment '举办地点ID',
    `start_time` timestamp not null default current_timestamp comment '开始时间',
    `end_time` timestamp not null default current_timestamp comment '结束时间',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    constraint `fk_event_type_id` foreign key (`event_type_id`) references `type_info`(`type_id`),
    constraint `fk_event_group_id` foreign key (`event_group_id`) references `group_info`(`group_id`),
    constraint `fk_event_region_id` foreign key (`event_region_id`) references `region_info`(`region_id`)
);

-- 加入记录
create table `user_group` (
    `id` int primary key auto_increment,
    `user_id` int not null comment '用户ID',
    `group_id` int not null comment '群组ID',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    constraint `fk_gu_user_id` foreign key (`user_id`) references `user_info`(`user_id`),
    constraint `fk_gu_group_id` foreign key (`group_id`) references `group_info`(`group_id`)
);

-- 参与记录
create table `user_event` (
    `id` int primary key auto_increment,
    `user_id` int not null comment '用户ID',
    `event_id` int not null comment '活动ID',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    constraint `fk_ge_user_id` foreign key (`user_id`) references `user_info`(`user_id`),
    constraint `fk_ge_event_id` foreign key (`event_id`) references `event_info`(`event_id`)
);

-- 候选列表
create table `cand_group_user` (
    `id` int primary key auto_increment,
    `group_id` int not null comment '群组ID',
    `user_id` int not null comment '用户ID',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    constraint `fk_gu_group_id` foreign key (`group_id`) references `group_info`(`group_id`),
    constraint `fk_gu_user_id` foreign key (`user_id`) references `user_info`(`user_id`)
);

-- 推荐表
create table `rec_info` (
    `id` int primary key auto_increment,
    `group_id` int not null,
    `event_id` int not null,
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    constraint `fk_rec_group_id` foreign key (`group_id`) references `group_info`(`group_id`),
    constraint `fk_rec_event_id` foreign key (`event_id`) references `event_info`(`event_id`)
);
```

