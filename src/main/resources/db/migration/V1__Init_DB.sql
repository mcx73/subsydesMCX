create sequence hibernate_sequence start 1 increment 1;
create table docum (
                       id int8 not null,
                       text varchar(2048),
                       user_id int8,
                       primary key (id)
);
create table user_role (
                           id int8 not null,
                           user_id int8 not null,
                           roles varchar(255),
                           primary key (id)
);
create table usr (
                     id int8 not null,
                     username varchar(255) not null,
                     password varchar(255) not null,
                     email varchar(255),
                     activation_code varchar(255),
                     active boolean not null,
                     primary key (id)
);
alter table if exists docum add
    constraint docum_user_fk
        foreign key (user_id) references usr;
alter table if exists user_role add
    constraint user_role_user_fk
        foreign key (user_id) references usr;




