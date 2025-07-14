begin;
CREATE TABLE drink_brothers
(
    drink_brother_id BIGINT primary key AUTO_INCREMENT not null,
    name             varchar(100)                      not null,
    created_at       timestamp                         not null default current_timestamp,
    updated_at       timestamp                         not null default current_timestamp
);

CREATE TABLE drinks
(
    drink_id         BIGINT primary key AUTO_INCREMENT,
    drink_brother_id BIGINT        not null,
    name             varchar(100)  not null,
    bar_code         varchar(100),
    alc              DECIMAL(5, 2) not null default 0,
    FOREIGN KEY (drink_brother_id) REFERENCES drink_brothers (drink_brother_id)
);

CREATE TABLE users
(
    user_id          BIGINT primary key AUTO_INCREMENT not null,
    full_name        varchar(100)                      not null,
    email            varchar(200)                      not null,
    password         varchar(200)                      not null,
    created_at       timestamp                         not null default current_timestamp,
    updated_at       timestamp                         not null default current_timestamp,
    drink_brother_id BIGINT                            not null,
    FOREIGN KEY (drink_brother_id) REFERENCES drink_brothers (drink_brother_id)
);
CREATE TABLE warehouses
(
    warehouse_id     BIGINT primary key AUTO_INCREMENT not null,
    drink_brother_id BIGINT                            not null,
    name             varchar(100)                      not null,
    address          varchar(200),
    city             varchar(200),
    created_at       timestamp                         not null default current_timestamp,
    FOREIGN KEY (drink_brother_id) REFERENCES drink_brothers (drink_brother_id)
);

CREATE TABLE product_stock
(
    drink_id     BIGINT         NOT NULL
        REFERENCES drinks (drink_id),
    warehouse_id BIGINT         NOT NULL
        REFERENCES warehouses (warehouse_id),
    quantity     DECIMAL(22, 8) NOT NULL,
    updated_at   timestamp      not null default current_timestamp,

    PRIMARY KEY (drink_id, warehouse_id)
);

insert into drink_brothers (name)
values ('DRINKBROS');
commit;