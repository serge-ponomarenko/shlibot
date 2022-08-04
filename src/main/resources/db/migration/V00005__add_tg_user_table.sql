alter table products
    alter column creation_date type timestamp using creation_date::timestamp;