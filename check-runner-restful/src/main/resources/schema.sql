create table products (
    id BIGSERIAL PRIMARY KEY ,
    name VARCHAR (32),
    price DOUBLE PRECISION
);

create table card(
    id BIGSERIAL PRIMARY KEY ,
    number VARCHAR (32) ,
    discount INTEGER
);

drop table products;
drop table card;