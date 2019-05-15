create table app_user_status(
    id bigint not null auto_increment primary key,
    app_user_status_name varchar(30)not null unique key,
    app_user_status_created_at timestamp not null default current_timestamp
) engine=InnoDb character set UTF8mb4 collate utf8mb4_bin;