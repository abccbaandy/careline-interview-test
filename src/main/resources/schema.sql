set mode MySQL;

DROP TABLE IF EXISTS tmp;
CREATE TABLE tmp (
    key VARCHAR(50) NOT NULL, 
    value VARCHAR(200) DEFAULT NULL, 
    desc VARCHAR(200) DEFAULT NULL, 
    PRIMARY KEY (key)
);
create table member (
  id       char(16)     not null primary key,
  email    varchar(320) not null unique,
  password varchar(100) not null,
  username varchar(50)  not null unique
);
create table member_modify_info (
  id          char(16) not null primary key,
  modify_time datetime not null,
  modify_info text     not null,
  modify_user char(16) not null
)