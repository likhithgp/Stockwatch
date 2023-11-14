create sequence stock_details_seq start with 1 increment by 50;
create table stock_details (current_price float(53), high_of52weeks float(53), id integer not null, low_of52weeks float(53), currency_type varchar(255), stock_name varchar(255) not null, stock_symbol varchar(255), primary key (id));
create sequence stock_details_seq start with 1 increment by 50;
create table stock_details (current_price float(53), high_of52weeks float(53), id integer not null, low_of52weeks float(53), currency_type varchar(255), stock_name varchar(255) not null, stock_symbol varchar(255), primary key (id));
