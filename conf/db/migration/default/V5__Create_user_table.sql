create table app_user(
    id bigint not null auto_increment primary key,
    app_user_name varchar(255) not null unique key,
    app_user_email varchar(255) not null unique key,
    app_user_phone varchar(255) not null,
    app_user_login_password varchar(255) not null,
    app_user_status_id bigint not null,
    app_user_role_id bigint not null,
    app_user_created_at timestamp not null default current_timestamp,
    app_user_updated_at timestamp not null default current_timestamp on update current_timestamp,
    foreign key(app_user_status_id) references app_user_status(id) on update cascade,
    foreign key (app_user_role_id) references app_user_role(id) on update cascade
) engine=InnoDb character set UTF8mb4 collate utf8mb4_bin;