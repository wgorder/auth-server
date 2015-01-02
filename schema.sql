
    create table ACME_USER (
        user_id int8 not null,
        activated boolean not null,
        activation_key varchar(20),
        created_by varchar(50) not null,
        created_date timestamp not null,
        email varchar(100),
        first_name varchar(50),
        last_modified_by varchar(50),
        last_modified_date timestamp,
        last_name varchar(50),
        password varchar(100),
        primary key (user_id)
    );

    create table AUTHORITY (
        name varchar(50) not null,
        primary key (name)
    );

    create table USER_AUTHORITY (
        user_id int8 not null,
        name varchar(50) not null,
        primary key (user_id, name)
    );

    alter table ACME_USER 
        add constraint UK_a9jp07niwk9vomyuryka23sbg  unique (email);

    alter table USER_AUTHORITY 
        add constraint FK_ooi8fbk7patlpqemev2716eyx 
        foreign key (name) 
        references AUTHORITY;

    alter table USER_AUTHORITY 
        add constraint FK_or22hakky2a42qhu8rw1vn9ku 
        foreign key (user_id) 
        references ACME_USER;

    create table hibernate_sequences (
         sequence_name varchar(255),
         sequence_next_hi_value int4 
    );
