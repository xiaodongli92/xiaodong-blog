create table code_set(
  id    int   PRIMARY KEY auto_increment,
  code_set_name   varchar(200)  DEFAULT null,
  code_set_value  varchar(30)   DEFAULT NULL,
  remark          longtext      DEFAULT null,
  seq             int           DEFAULT 1,
  status          int(4)        DEFAULT 1
)charset utf8;