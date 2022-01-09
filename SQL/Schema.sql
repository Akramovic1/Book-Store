drop schema if exists bookstore ;
create schema bookstore;
use bookstore;
SET SQL_SAFE_UPDATES = 0;
/*************************** Part 1 : I.Database relations ******************************/
create table categories
(
  name varchar(10) primary key
);

insert into categories
values ('Science'),
       ('Art'),
       ('Religion'),
       ('History'),
       ('Geography');

create table book(
	isbn                int auto_increment primary key,
    title               varchar(50) NOT NULL UNIQUE,
    publisher_id        int NOT NULL,
    publication_year    year NOT NULL,
    selling_price       float NOT NULL CHECK (selling_price >= 0),
    category            varchar(10) NOT NULL,
    num_of_copies       int NOT NULL,
    threshold           int NOT NULL,
    foreign key (category) references categories (name)
 );
 
 create table publisher
(
  publisher_id           int auto_increment primary key,
  publisher_name         varchar(50) NOT NULL,
  publisher_address      varchar(100),
  publisher_phone        varchar(20)
);

create table authors
(
  author_id      int auto_increment primary key,
  author_name    varchar(50) NOT NULL
);

create table book_authors
(
  isbn     int,
  author_id int,
  foreign key (isbn) references book (isbn) ON UPDATE CASCADE ON DELETE CASCADE,
  primary key (isbn, author_id)
);

/**************************** Part 2 : Schema ***********************************/
create table user_info
(
  user_id    int primary key auto_increment,
  user_name  varchar(50) NOT NULL,
  password   varchar(255) NOT NULL,
  first_name varchar(50) NOT NULL,
  last_name  varchar(50) NOT NULL,
  email      varchar(255) NOT NULL UNIQUE,
  phone      varchar(20) NOT NULL,
  Shipping_address varchar(100) NOT NULL,
  privilege  boolean NOT NULL
);

create table book_order
(
  order_id      int auto_increment primary key,
  customer_id   int NOT NULL,
  isbn          int NOT NULL,
  quantity      int NOT NULL,
  Shipping_address varchar(100) NOT NULL,
  date          date ,
  foreign key (isbn) references book (isbn) ON UPDATE CASCADE ON DELETE CASCADE,
  foreign key (customer_id) references user_info (user_id) ON UPDATE CASCADE ON DELETE CASCADE
);

create table credit_card
(
  user_id      int,
  card_number  bigint,
  expiry_date  Date,
  primary key (user_id, card_number),
  foreign key (user_id) references user_info (user_id) ON UPDATE CASCADE ON DELETE CASCADE
);

alter table book
  add foreign key (publisher_id) references publisher (publisher_id) ON UPDATE CASCADE ON DELETE CASCADE;
