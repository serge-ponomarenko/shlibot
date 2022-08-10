alter table tg_user
    add constraint tg_user_lists_fkey
        foreign key (mainlist_id) references lists (list_id)
            on delete set null;