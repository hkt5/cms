create table app_user_password(
    id bigint not null auto_increment primary key,
    app_user_id bigint not null unique key,
    app_user_password_created_at timestamp not null default current_timestamp,
    app_user_password_updated_at timestamp not null default current_timestamp,
    foreign key(app_user_id) references app_user(id) on update cascade on delete cascade
) engine=InnoDb character set UTF8mb4 collate utf8mb4_bin;