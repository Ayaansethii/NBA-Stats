CREATE DATABASE IF NOT EXISTS NBASW;
USE NBASW;

CREATE TABLE Manager (
    managerID INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(30) UNIQUE NOT NULL,
    password VARCHAR(30) NOT NULL
);

CREATE TABLE League (
    leagueID INT AUTO_INCREMENT PRIMARY KEY,
    leagueName VARCHAR(50) NOT NULL UNIQUE,
    managerID INT NOT NULL,  
    FOREIGN KEY (managerID) REFERENCES Manager(managerID) ON DELETE CASCADE
);

CREATE TABLE Team (
    teamID INT AUTO_INCREMENT PRIMARY KEY,
    teamName VARCHAR(75) NOT NULL,
    leagueID INT NOT NULL,
    games INT DEFAULT 0,
    wins INT DEFAULT 0,
    losses INT DEFAULT 0,
    points_scored INT DEFAULT 0,
    points_conceded INT DEFAULT 0,
    field_goal_percentage DECIMAL(5,2) DEFAULT 0.00,
    three_point_percentage DECIMAL(5,2) DEFAULT 0.00,
    FOREIGN KEY (leagueID) REFERENCES League(leagueID) ON DELETE CASCADE
);

CREATE TABLE Player (
    playerID INT AUTO_INCREMENT PRIMARY KEY,
    teamID INT NOT NULL,
    playerName VARCHAR(100) NOT NULL,
    games INT DEFAULT 0,
    total_points INT DEFAULT 0,
    total_assists INT DEFAULT 0,
    total_rebounds INT DEFAULT 0,
    total_steals INT DEFAULT 0,
    total_blocks INT DEFAULT 0,
    total_turnovers INT DEFAULT 0,
    field_goal_percent DECIMAL(5,2) DEFAULT 0.00,
    FOREIGN KEY (teamID) REFERENCES Team(teamID) ON DELETE CASCADE
);

