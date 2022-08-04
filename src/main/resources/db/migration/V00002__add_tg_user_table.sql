alter table tg_user
    add tg_name varchar(255);

alter table tg_user
    add mainlist_id integer;

create table lists
(
    list_id    serial,
    name       varchar(50),
    user_id    integer,
    share_token varchar(20),
    deleted    boolean
);

alter table lists
    add constraint lists_pkey
        primary key (list_id);

alter table lists
    add constraint lists_tg_users_fkey
        foreign key (user_id) references tg_user (user_id);

create table users_lists
(
    user_id integer
        constraint users_lists_tg_users_fkey
            references tg_user (user_id),
    list_id integer
        constraint users_lists_lists_fkey
            references lists (list_id)
);

create table products
(
    product_id       integer
        constraint products_pkey
            primary key,
    name          varchar(255),
    list_id       integer
        constraint products_lists_fkey
            references lists (list_id),
    position      integer,
    checked       boolean,
    deleted       boolean,
    creation_date date
);