drop table if exists animals CASCADE;
    CREATE TABLE animals (
    id BIGINT AUTO_INCREMENT,
    animal_group VARCHAR(255),
    diet VARCHAR(255),
    name VARCHAR(255),
    no_of_legs INTEGER,
    size_cm DOUBLE,
    weight_kg DOUBLE,
    PRIMARY KEY (id)
);
