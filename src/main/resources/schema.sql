drop table category if exists;
drop table product if exists;
create table category (id bigint not null, name varchar(255), primary key (id));
create table product (id bigint not null, name varchar(255), category_id bigint, primary key (id));
alter table product add constraint FK1mtsbur82frn64de7balymq9s foreign key (category_id) references category; 
  