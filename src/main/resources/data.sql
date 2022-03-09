
--position drone LMDR-1
INSERT INTO positions (id, x, y) Values (1, 5, 10);
INSERT INTO drones (drone_id, drone_position_id, autonomy) Values ('LMDR-1',1,100);

--position drone LMDR-2
INSERT INTO positions (id, x, y) Values (2, 2, 7);
INSERT INTO drones (drone_id, drone_position_id, autonomy) Values ('LMDR-2',2,100);

--position drone LMDR-3
INSERT INTO positions (id, x, y) Values (3, 6, 20);
INSERT INTO drones (drone_id, drone_position_id, autonomy) Values ('LMDR-3',3,100);

--position drone LMDR-4
INSERT INTO positions (id, x, y) Values (4, 4, 43);
INSERT INTO drones (drone_id, drone_position_id, autonomy) Values ('LMDR-4',4,100);

--position drone LMDR-5
INSERT INTO positions (id, x, y) Values (5, 9, 9);
INSERT INTO drones (drone_id, drone_position_id, autonomy) Values ('LMDR-5',5,100);


-- stores's positions 
INSERT INTO positions (id, x, y) Values (6, 3, 3);
INSERT INTO stores (store_id, store_position_id) Values ('LMFR-VA', 6);
INSERT INTO positions (id, x, y) Values (7, 23, 45);
INSERT INTO stores (store_id, store_position_id) Values ('LMFR-RO', 7);
INSERT INTO positions (id, x, y) Values (8, 10, 14);
INSERT INTO stores (store_id, store_position_id) Values ('LMFR-LE', 8);

-- products
INSERT INTO products (product_id, product_name) Values ('LMFRPRD-1', 'Shovel');
INSERT INTO products (product_id, product_name) Values ('LMFRPRD-2', 'Pickaxe');
INSERT INTO products (product_id, product_name) Values ('LMFRPRD-3', 'Rake');

-- stock's products in stores

INSERT INTO stores_products (store_id, product_id, quantity) Values ('LMFR-VA', 'LMFRPRD-1', 10);
INSERT INTO stores_products (store_id, product_id, quantity) Values ('LMFR-RO', 'LMFRPRD-1', 1);
INSERT INTO stores_products (store_id, product_id, quantity) Values ('LMFR-RO', 'LMFRPRD-2', 3);
INSERT INTO stores_products (store_id, product_id, quantity) Values ('LMFR-LE', 'LMFRPRD-2', 2);
INSERT INTO stores_products (store_id, product_id, quantity) Values ('LMFR-LE', 'LMFRPRD-3', 1);
INSERT INTO stores_products (store_id, product_id, quantity) Values ('LMFR-VA', 'LMFRPRD-3', 2);

-- customers's positions 
INSERT INTO positions (id, x, y) Values (9, 5, 8);
INSERT INTO customers (id, customer_id, customer_position_id) Values (1, 'LMFR-VA', 9);
INSERT INTO positions (id, x, y) Values (10, 20, 20);
INSERT INTO customers (id, customer_id, customer_position_id) Values (2, 'LMFR-RO', 10);
INSERT INTO positions (id, x, y) Values (11, 12, 3);
INSERT INTO customers (id, customer_id, customer_position_id) Values (3, 'LMFR-LE', 11);




