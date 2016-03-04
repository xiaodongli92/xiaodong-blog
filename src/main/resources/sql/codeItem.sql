create table code_set(
  id    int   PRIMARY KEY auto_increment,
  code_set_name   varchar(200)  DEFAULT null,
  code_set_value  varchar(30) unique DEFAULT NULL,
  remark          longtext      DEFAULT null,
  seq             int           DEFAULT 1,
  status          int(4)        DEFAULT 1
)charset utf8;
create table code_item(
  id    int   PRIMARY KEY auto_increment,
  code_set    varchar(30)   DEFAULT null,
  parent_code varchar(30)   DEFAULT NULL,
  code_name   varchar(200)  DEFAULT null,
  code_name2  varchar(200)  DEFAULT null,
  code_value  varchar(30)   DEFAULT null,
  seq         int           DEFAULT 1,
  remark      varchar(2000) DEFAULT null,
  status      int(4)        DEFAULT 1
)charset utf8;