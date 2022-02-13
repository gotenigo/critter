/*

alter table customer_pet
drop
foreign key FK3dfdrnbrgq51ns7e2o9os1c5j;


alter table customer_pet
drop
foreign key FK9osi68tal5pfk2llv67oxxqqx;


alter table employee_days_available
drop
foreign key FK7ra3ha8r7rcikm5k4ow91hluk;


alter table employee_skills
drop
foreign key FKnepe51hewn4dd673e3qk1v2qx;


drop table if exists customer;


drop table if exists customer_pet;


drop table if exists employee;


drop table if exists employee_days_available;


drop table if exists employee_skills;


drop table if exists hibernate_sequence;


drop table if exists pet;


drop table if exists schedule;


create table customer (
                          id bigint not null,
                          name varchar(255),
                          notes varchar(255),
                          phone_number varchar(255),
                          primary key (id)
) engine=InnoDB;


create table customer_pet (
                              customer_id bigint not null,
                              pet_id bigint not null
) engine=InnoDB;


create table employee (
                          id bigint not null,
                          name varchar(255),
                          primary key (id)
) engine=InnoDB;


create table employee_days_available (
                                         employee_id bigint not null,
                                         days_available varchar(255)
) engine=InnoDB;


create table employee_skills (
                                 employee_id bigint not null,
                                 skills varchar(255)
) engine=InnoDB;


create table hibernate_sequence (
    next_val bigint
) engine=InnoDB;


insert into hibernate_sequence values ( 1 );


insert into hibernate_sequence values ( 1 );


insert into hibernate_sequence values ( 1 );


insert into hibernate_sequence values ( 1 );


create table pet (
                     id bigint not null,
                     birth_date date,
                     name varchar(255),
                     notes varchar(255),
                     type integer,
                     primary key (id)
) engine=InnoDB;


create table schedule (
                          id bigint not null,
                          date date,
                          primary key (id)
) engine=InnoDB;


alter table customer_pet
    add constraint FK3dfdrnbrgq51ns7e2o9os1c5j
        foreign key (pet_id)
            references pet (id);


alter table customer_pet
    add constraint FK9osi68tal5pfk2llv67oxxqqx
        foreign key (customer_id)
            references customer (id);


alter table employee_days_available
    add constraint FK7ra3ha8r7rcikm5k4ow91hluk
        foreign key (employee_id)
            references employee (id);


alter table employee_skills
    add constraint FKnepe51hewn4dd673e3qk1v2qx
        foreign key (employee_id)
            references employee (id);


*/