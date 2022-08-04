alter table products
    alter column product_id set default nextval('products_product_id_seq'::regclass);