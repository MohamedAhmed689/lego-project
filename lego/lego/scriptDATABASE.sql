CREATE DATABASE lego;

use lego;

CREATE TABLE lego (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) DEFAULT ''
);
INSERT INTO lego(name) values ('LEGONAME');
CREATE TABLE legosetting (
    id INT AUTO_INCREMENT PRIMARY KEY,
    speed INT,
    direction INT, -- 0=forward, 1=backward
    time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    legoid INT,
    run INT,
    FOREIGN KEY (legoid) REFERENCES lego(id)
);