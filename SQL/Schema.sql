drop schema if exists bookstore ;
create schema bookstore;
use bookstore;
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
    title               varchar(50) NOT NULL,
    publisher_id        int,
    publication_year    year,
    selling_price       float NOT NULL CHECK (selling_price >= 0),
    category            varchar(10),
    num_of_copies       int NOT NULL,
    threshold           int NOT NULL,
    foreign key (category) references categories (name)
 );
 
 create table publisher
(
  publisher_id           int auto_increment primary key,
  publisher_name         varchar(50),
  publisher_address      varchar(100),
  publisher_phone        varchar(20)
);

create table authors
(
  author_id      int auto_increment primary key,
  author_name    varchar(50),
  author_address varchar(100)
);

create table book_authors
(
  isbn     int,
  author_id int,
  foreign key (isbn) references book (isbn) on delete cascade,
  primary key (isbn, author_id)
);

create table book_order
(
  order_id      int auto_increment primary key,
  isbn          int,
  quantity      int,
  date          date,
  foreign key (isbn) references book (isbn)
);
/*************************** Part 1 : II.Operations ******************************/
-- Operations    --TODO

-- Add new books
-- Modify existing books
-- Place orders on books
-- Confirm orders
-- Search for books

/**************************** Part 2 : Schema ***********************************/
create table user_access
(
  user_id    int primary key auto_increment,
  privilege  boolean
);

create table user_info
(
  user_id    int primary key auto_increment,
  user_name  varchar(50),
  password   varchar(255),
  first_name varchar(50),
  last_name  varchar(50),
  email      varchar(255) UNIQUE,
  phone      varchar(20),
  shipping_address varchar(100),
  foreign key (user_id) references user_access (user_id)
);

create table credit_card
(
  user_id      int,
  card_number  bigint,
  cvv_number   int,
  expiry_date  Date,
  primary key (user_id, card_number),
  foreign key (user_id) references user_info (user_id)
);

-- TODO 
-- customer_orders
-- shopping_cart
-- edit the above book_order