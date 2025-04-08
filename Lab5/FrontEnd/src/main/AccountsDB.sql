CREATE DATABASE IF NOT EXISTS FrontendDB;
USE FrontendDB;

CREATE TABLE IF NOT EXISTS Manager (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Insert a sample manager (username: manager, password: password)
INSERT INTO Manager (username, password) VALUES ('manager', 'password');
