create database bookstore;
use bookstore;
create table users (username char(16) primary key,password char(40) not null,name char(60) not null,address char(80)not null, city char(30) not null,authority tinyint not null, check(authority in (0,1)));
create table categories(catid int unsigned auto_increment primary key, catname char(100) not null);
create table books(isbn char(13) primary key,title char(100),author char(100),price float(6,2) not null,catid int unsigned not null references categories(catid), description varchar(255));
create table orders(orderid int unsigned auto_increment primary key,username char(16) not null references users(username),datetime date not null );
create table order_items(orderid int unsigned not null references orders(orderid),isbn char(13) not null references books(isbn),quantity tinyint unsigned not null,primary key(orderid,isbn));
insert into users values('thunderboy','1234','ChenJun','SJTU','ShangHai',0);
insert into users values('123','123','ChenJun2','SJTU','ShangHai',1);
insert into categories values(1,'novel');
insert into categories values(2,'programming');
insert into categories values(3,'biography');
insert into categories values(4,'poem');
insert into categories values(5,'others');
insert into books values('1','C++','Paul',30.50,2,null);
insert into books values('2','Harry Potter','JK',40.00,1,null);
insert into books values('3','Steve Jobs','Peter',30.20,3,null);
insert into books values('4','Stray Birds','Tagore',35.00,4,null);
insert into orders(username,datetime) values('thunderboy','2015-09-12');
insert into order_items values(1,'4',1);
insert into orders(username,datetime) values('123','2016-05-13');
insert into order_items values(2,'4',1);
insert into orders(username,datetime) values('123','2016-04-13');
insert into order_items values(3,'4',1);


delimiter //    
create trigger tri_delete_category before delete on categories 
for each row  
begin
     declare other_id int;
     set other_id = (select catid from categories where catname = "others");
     update books set catid = other_id where catid = old.catid;  
end // 

create function getAmount(quantity int,price float(6,2))
     returns float(8,2)
     begin
     declare amount float(8,2);
     set amount = price*quantity;
     return amount;
     end//

create function user_quantity(User char(16))
     returns integer
     begin
     declare u_quantity integer;
     select IFNULL(sum(quantity),0) from order_items natural join orders where username = User into u_quantity;
     return u_quantity;
     end//

create function user_amount(User char(16))
     returns float(8,2)
     begin
     declare u_amount float(8,2);
     select IFNULL(sum(getAmount(quantity,price)),0) from order_items natural join books natural join orders where username = User into u_amount;
     return u_amount;
     end//

create function date_quantity(d date)
    returns integer
    begin
    declare d_quantity integer;
    select IFNULL(sum(quantity),0) from order_items natural join orders where datetime = d into d_quantity;
    return d_quantity;
    end//

create function date_amount(d date)
    returns float(8,2)
    begin
    declare d_amount float(8,2);
    select IFNULL(sum(getAmount(quantity,price)),0) from order_items natural join books natural join orders where datetime = d into d_amount;
    return d_amount;
    end//

create function book_quantity(book char(13))
    returns integer
    begin
    declare b_quantity integer;
    select IFNULL(sum(quantity),0) from order_items natural join orders where isbn = book into b_quantity;
    return b_quantity;
    end//

create function book_amount(book char(13))
    returns float(8,2)
    begin
    declare b_amount float(8,2);
    select IFNULL(sum(getAmount(quantity,price)),0) from order_items natural join orders natural join books where isbn = book into b_amount;
    return b_amount;
    end//

create function category_quantity(c int)
    returns integer
    begin
    declare c_quantity integer;
    select IFNULL(sum(quantity),0) from order_items natural join orders natural join books where catid = c into c_quantity;
    return c_quantity;
    end//

create function category_amount(c int)
    returns float(8,2)
    begin
    declare c_amount float(8,2);
    select IFNULL(sum(getAmount(quantity,price)),0) from order_items natural join orders natural join books where catid = c into c_amount;
    return c_amount;
    end//
create procedure allUserStatistic()
     begin
     select username as keyword,user_quantity(username) as quantity,user_amount(username) as amount from users;
     end//

create procedure userStatistic(User char(16))
     begin
     select username as keyword,user_quantity(username) as quantity,user_amount(username) as amount from users where username like  concat('%',User,'%');
     end//

create procedure allDateStatistic()
     begin
     select distinct datetime as keyword, date_quantity(datetime) as quantity,date_amount(datetime) as amount from orders;
     end//

create procedure dateStatistic(datefrom date, dateto date)
     begin
     select distinct datetime as keyword, date_quantity(datetime) as quantity,date_amount(datetime) as amount from orders where datetime between datefrom and dateto;
     end//

create procedure dateAfter(datefrom date)
     begin
     select distinct datetime as keyword, date_quantity(datetime) as quantity,date_amount(datetime) as amount from orders where datetime >= datefrom;
     end//

create procedure dateBefore(dateto date)
     begin
     select distinct datetime as keyword, date_quantity(datetime) as quantity,date_amount(datetime) as amount from orders where datetime <= datefrom;
     end//

create procedure allBookStatistic()
     begin
     select isbn as keyword,book_quantity(isbn) as quantity,book_amount(isbn) as amount from books order by isbn;
     end//

create procedure bookStatistic(book char(13))
     begin
     select isbn as keyword,book_quantity(isbn) as quantity,book_amount(isbn) as amount from books where isbn = book or title like concat('%',book,'%') order by isbn;
     end//

create procedure allCategoryStatistic()
     begin
     select catname as keyword,category_quantity(catid) as quantity, category_amount(catid) as amount from categories;
     end//

create procedure categoryStatistic(cat char(100))
     begin
     select catname as keyword,category_quantity(catid) as quantity, category_amount(catid) as amount from categories where catname like concat('%',cat,'%') order by catname;
     end//


delimiter ;
