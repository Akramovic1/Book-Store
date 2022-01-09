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
  first_name varchar(50),
  last_name  varchar(50),
  email      varchar(255) NOT NULL UNIQUE,
  phone      varchar(20),
  Shipping_address varchar(100),
  privilege  boolean NOT NULL
);

create table book_order
(
  order_id      int auto_increment primary key,
  user_id   int NOT NULL,
  isbn          int NOT NULL,
  quantity      int NOT NULL,
  -- Shipping_address varchar(100),
  date          date ,
  foreign key (isbn) references book (isbn) ON UPDATE CASCADE ON DELETE CASCADE,
  foreign key (user_id) references user_info (user_id) ON UPDATE CASCADE ON DELETE CASCADE
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

/*************************** Triggers ******************************/
delimiter $$
create trigger modify_existing_books before update  on book
    for each row
begin
    if  new.num_of_copies<0 then
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Cannot add or update book row with number of copies < 0';
end if;
end;$$

-- Test modify_existing_books :

-- insert into publisher (publisher_id,publisher_name) values(1,"publisher name");
-- insert into book (isbn,title,publisher_id,publication_year,selling_price,category,num_of_copies,threshold)values("1","title","1",YEAR(CURDATE()),"10","Science","1","5");
-- update book set num_of_copies = num_of_copies-2 where isbn = 1 ;

create trigger place_orders_books after update on book 
for each row
begin
    if old.num_of_copies>new.threshold and new.num_of_copies<=new.threshold then
            insert into book_order(user_id,isbn,quantity,date) values("1",new.isbn,new.threshold*2,CURDATE());
    end if;
end;

-- Test place_orders_books :

-- insert into user_info (user_id,user_name,password,email,privilege)values("1","manager","******","manager@gmail.com",true);
-- insert into publisher (publisher_id,publisher_name) values(1,"publisher name");
-- insert into book (isbn,title,publisher_id,publication_year,selling_price,category,num_of_copies,threshold)values("1","title","1",YEAR(CURDATE()),"10","Science","6","5");
-- update book set num_of_copies = num_of_copies-2 where isbn = 1 ;

create trigger confirm_orders before  delete on book_order
    for each row
begin
    update book set num_of_copies = num_of_copies + old.quantity where isbn = old.isbn ;
end;

-- Test confirm_orders :

-- insert into user_info (user_id,user_name,password,email,privilege)values("1","manager","******","manager@gmail.com",true);
-- insert into publisher (publisher_id,publisher_name) values(1,"publisher name");
-- insert into book (isbn,title,publisher_id,publication_year,selling_price,category,num_of_copies,threshold)values("1","title","1",YEAR(CURDATE()),"10","Science","6","5");
-- update book set num_of_copies = num_of_copies-1 where isbn = 1 ;
-- delete from book_order where order_id="1";