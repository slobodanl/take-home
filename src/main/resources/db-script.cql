CREATE KEYSPACE thcKeyspace WITH REPLICATION = {'class' : 'SimpleStrategy', 'replication_factor' : 1 };

USE thcKeyspace;


DROP TABLE IF EXISTS user;

CREATE TABLE user (
    user_id UUID,
    user_type text,
    first_name text,
    last_name text,
    address text,
    phone text,
    PRIMARY KEY ((user_id) , user_type)
) WITH CLUSTERING ORDER BY(user_type DESC);


DROP TABLE IF EXISTS user_by_type;

CREATE TABLE user_by_type (
    user_type text,
    user_id UUID,
    first_name text,
    last_name text,
    address text,
    phone text,
    PRIMARY KEY ((user_type) , user_id)
) WITH CLUSTERING ORDER BY(user_id DESC);


DROP TABLE IF EXISTS orders;

CREATE TABLE orders (
    order_id UUID,
    user_id UUID,
    order_date TIMESTAMP,
    delivery_date TIMESTAMP,
    delivery_address text,
    delivery_phone text,
    PRIMARY KEY ((order_id) , order_date)
) WITH CLUSTERING ORDER BY(order_date DESC);

DROP TABLE IF EXISTS orders_by_user;

CREATE TABLE orders_by_user (
    user_id UUID,
    order_id UUID,
    order_date TIMESTAMP,
    PRIMARY KEY ((user_id) , order_date , order_id)
) WITH CLUSTERING ORDER BY(order_date DESC , order_id DESC);

DROP TABLE IF EXISTS orders_by_cook;

CREATE TABLE orders_by_cook (
    cook_id UUID,
    order_id UUID,
    item_id UUID,
    item_name TEXT,
    order_date TIMESTAMP,
    delivery_date TIMESTAMP,
    recurring_interval INT,
    next_delivery_date TIMESTAMP,
    PRIMARY KEY ((cook_id , order_id) ,next_delivery_date , item_id )
) WITH CLUSTERING ORDER BY(next_delivery_date DESC);

DROP TABLE IF EXISTS order_items;

CREATE TABLE order_items (
    order_id UUID,
    user_id UUID,
    order_date TIMESTAMP,
    cook_id UUID,
    cook_name TEXT,
    cook_location_id UUID,
    cook_location TEXT,
    item_ordered UUID,
    item_name TEXT,
    PRIMARY KEY ((order_id ) , user_id , order_date , item_ordered)
) WITH CLUSTERING ORDER BY(user_id DESC , order_date DESC ,  item_ordered ASC);

DROP TABLE IF EXISTS menu_items;

CREATE TABLE menu_items (
    item_id UUID,
    user_id UUID,
    item_picture TEXT,
    item_name TEXT,
    item_description TEXT,
    item_added TIMESTAMP,
    available boolean,
    categories set<TEXT>,
    remarks set<TEXT>,
    tags set<TEXT>,
    PRIMARY KEY ((item_id) , user_id , item_added)
) WITH CLUSTERING ORDER BY(user_id ASC , item_added DESC);

DROP TABLE IF EXISTS menu_items_by_user;

CREATE TABLE menu_items_by_user (
    user_id UUID,
    item_id UUID,
    item_picture TEXT,
    item_name TEXT,
    item_description TEXT,
    item_added TIMESTAMP,
    available boolean,
    categories set<TEXT>,
    remarks set<TEXT>,
    tags set<TEXT>,
    PRIMARY KEY ((user_id) , item_id , item_added)
) WITH CLUSTERING ORDER BY(item_id ASC , item_added DESC);

DROP TABLE IF EXISTS menu_items_by_category;

CREATE TABLE menu_items_by_category (
    category TEXT,
    user_id UUID,
    item_id UUID,
    item_picture TEXT,
    item_name TEXT,
    item_description TEXT,
    item_added TIMESTAMP,
    available boolean,
    categories set<TEXT>,
    remarks set<TEXT>,
    tags set<TEXT>,
    PRIMARY KEY ((category) , user_id , item_id , item_added)
) WITH CLUSTERING ORDER BY(user_id ASC, item_id ASC , item_added DESC);