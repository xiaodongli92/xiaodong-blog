create table article(
  id          bigint(20)    PRIMARY KEY auto_increment,
  title       varchar(200)  DEFAULT null,
  type_code    varchar(6)    DEFAULT NULL,
  author_id    bigint(20)       DEFAULT null,
  author_name varchar(50)    DEFAULT NULL,
  content     varchar(2000)      DEFAULT NULL,
  status      int(4)        DEFAULT 1,
  create_time  datetime      DEFAULT null
)charset utf8;