create sequence products_product_id_seq
    as integer;

--alter sequence products_product_id_seq owner to postgres;

alter sequence products_product_id_seq owned by products.product_id;
