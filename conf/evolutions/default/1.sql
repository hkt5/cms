# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

-- init script create procs
-- Inital script to create stored procedures etc for mysql platform
DROP PROCEDURE IF EXISTS usp_ebean_drop_foreign_keys;

delimiter $$
--
-- PROCEDURE: usp_ebean_drop_foreign_keys TABLE, COLUMN
-- deletes all constraints and foreign keys referring to TABLE.COLUMN
--
CREATE PROCEDURE usp_ebean_drop_foreign_keys(IN p_table_name VARCHAR(255), IN p_column_name VARCHAR(255))
BEGIN
  DECLARE done INT DEFAULT FALSE;
  DECLARE c_fk_name CHAR(255);
  DECLARE curs CURSOR FOR SELECT CONSTRAINT_NAME from information_schema.KEY_COLUMN_USAGE
    WHERE TABLE_SCHEMA = DATABASE() and TABLE_NAME = p_table_name and COLUMN_NAME = p_column_name
      AND REFERENCED_TABLE_NAME IS NOT NULL;
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

  OPEN curs;

  read_loop: LOOP
    FETCH curs INTO c_fk_name;
    IF done THEN
      LEAVE read_loop;
    END IF;
    SET @sql = CONCAT('ALTER TABLE ', p_table_name, ' DROP FOREIGN KEY ', c_fk_name);
    PREPARE stmt FROM @sql;
    EXECUTE stmt;
  END LOOP;

  CLOSE curs;
END
$$

DROP PROCEDURE IF EXISTS usp_ebean_drop_column;

delimiter $$
--
-- PROCEDURE: usp_ebean_drop_column TABLE, COLUMN
-- deletes the column and ensures that all indices and constraints are dropped first
--
CREATE PROCEDURE usp_ebean_drop_column(IN p_table_name VARCHAR(255), IN p_column_name VARCHAR(255))
BEGIN
  CALL usp_ebean_drop_foreign_keys(p_table_name, p_column_name);
  SET @sql = CONCAT('ALTER TABLE ', p_table_name, ' DROP COLUMN ', p_column_name);
  PREPARE stmt FROM @sql;
  EXECUTE stmt;
END
$$
create table app_user (
  id                            bigint auto_increment not null,
  app_user_name                 varchar(255) not null,
  app_user_email                varchar(255) not null,
  app_user_phone                varchar(255),
  app_user_login_password       varchar(255),
  app_user_created_at           datetime(6),
  app_user_updated_at           datetime(6),
  app_user_status_id            bigint,
  app_user_role_id              bigint,
  constraint uq_app_user_app_user_name unique (app_user_name),
  constraint uq_app_user_app_user_email unique (app_user_email),
  constraint pk_app_user primary key (id)
);

create table app_user_password (
  id                            bigint auto_increment not null,
  app_user_id                   bigint,
  app_user_password_created_at  datetime(6),
  app_user_password_updated_at  datetime(6),
  constraint uq_app_user_password_app_user_id unique (app_user_id),
  constraint pk_app_user_password primary key (id)
);

create table app_user_role (
  id                            bigint auto_increment not null,
  app_user_role_name            varchar(255) not null,
  app_user_role_created_at      datetime(6),
  constraint uq_app_user_role_app_user_role_name unique (app_user_role_name),
  constraint pk_app_user_role primary key (id)
);

create table app_user_status (
  id                            bigint auto_increment not null,
  app_user_status_name          varchar(255) not null,
  app_user_status_created_at    datetime(6) not null,
  constraint uq_app_user_status_app_user_status_name unique (app_user_status_name),
  constraint pk_app_user_status primary key (id)
);

create index ix_app_user_app_user_status_id on app_user (app_user_status_id);
alter table app_user add constraint fk_app_user_app_user_status_id foreign key (app_user_status_id) references app_user_status (id) on delete restrict on update restrict;

create index ix_app_user_app_user_role_id on app_user (app_user_role_id);
alter table app_user add constraint fk_app_user_app_user_role_id foreign key (app_user_role_id) references app_user_role (id) on delete restrict on update restrict;

alter table app_user_password add constraint fk_app_user_password_app_user_id foreign key (app_user_id) references app_user (id) on delete restrict on update restrict;


# --- !Downs

alter table app_user drop foreign key fk_app_user_app_user_status_id;
drop index ix_app_user_app_user_status_id on app_user;

alter table app_user drop foreign key fk_app_user_app_user_role_id;
drop index ix_app_user_app_user_role_id on app_user;

alter table app_user_password drop foreign key fk_app_user_password_app_user_id;

drop table if exists app_user;

drop table if exists app_user_password;

drop table if exists app_user_role;

drop table if exists app_user_status;

