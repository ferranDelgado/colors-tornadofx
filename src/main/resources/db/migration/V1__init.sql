CREATE TABLE color
(
    id   INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(255),
    hex  VARCHAR(255)
);

INSERT INTO color (name, hex)
VALUES ('Antique white', '#FAEBD7')
     , ('Beau blue', '#BCD4E6')
