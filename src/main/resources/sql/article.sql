create table article(
  id          int(64)       PRIMARY KEY auto_increment,
  title       varchar(200)  DEFAULT null,
  typeCode    varchar(6)    DEFAULT NULL,
  authorId    int(64)       DEFAULT null,
  content     varchar(2000) DEFAULT NULL,
  status      int(4)        DEFAULT 1,
  createTime  datetime      DEFAULT null
)charset utf8;