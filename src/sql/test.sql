begin;
CREATE TABLE drinks
(
    drink_id BIGINT primary key AUTO_INCREMENT ,
    name varchar(100) not null ,
    bar_code varchar(100),
    alc DECIMAL(5, 2) not null default 0
);
commit;