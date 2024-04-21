CREATE TABLE  players (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) not null,
    lastname VARCHAR(255) not null
);

CREATE TABLE warehouses (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) not null,
    description text,
    FOREIGN KEY (player_id) REFERENCES players(id)
);

CREATE TABLE items (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) not null,
    description text,
    icon VARCHAR(255) not null,
    max_capacity SMALLINT not null
);

CREATE TABLE warehouse_items (
    id SERIAL PRIMARY KEY,
    FOREIGN KEY (warehouse_id) REFERENCES warehouses(id) not null,
    FOREIGN KEY (item_id) REFERENCES items(id) not null,
    item_count SMALLINT not null,
);