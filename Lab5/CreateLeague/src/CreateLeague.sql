CREATE DATABASE IF NOT EXISTS CreateLeagueDB;
USE CreateLeagueDB;


-- Remove database creation/selection (Docker already creates LEAGUES_DB from ENV)

DROP TABLE IF EXISTS League;

CREATE TABLE League (
    leagueID INT AUTO_INCREMENT PRIMARY KEY,
    leagueName VARCHAR(50) NOT NULL,
    managerID INT NOT NULL  
);

INSERT INTO League (leagueName, managerID) VALUES ("testjason", 1);

