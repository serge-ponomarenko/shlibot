alter table lists
    drop constraint lists_tg_users_fkey;

alter table lists
    add constraint lists_tg_users_fkey
        foreign key (user_id) references tg_user
            on delete cascade;

alter table products
    drop constraint products_lists_fkey;

alter table products
    add constraint products_lists_fkey
        foreign key (list_id) references lists
            on delete cascade;

alter table users_lists
    drop constraint users_lists_tg_users_fkey;

alter table users_lists
    add constraint users_lists_tg_users_fkey
        foreign key (user_id) references tg_user
            on delete cascade;

alter table users_lists
    drop constraint users_lists_lists_fkey;

alter table users_lists
    add constraint users_lists_lists_fkey
        foreign key (list_id) references lists
            on delete cascade;
