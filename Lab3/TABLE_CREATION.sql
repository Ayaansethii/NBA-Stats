CREATE DATABASE IF NOT EXISTS NBASW;
USE NBASW;


CREATE TABLE IF NOT EXISTS Manager (
    managerID INT PRIMARY KEY,
    username VARCHAR(30) UNIQUE NOT NULL,
    password VARCHAR(30) NOT NULL
);


CREATE TABLE IF NOT EXISTS League (
    leagueID INT PRIMARY KEY,
    leagueName VARCHAR(50) NOT NULL,
    managerID INT,  
    FOREIGN KEY (managerID) REFERENCES Manager(managerID) ON DELETE SET NULL
);


CREATE TABLE IF NOT EXISTS Team (
    teamID INT PRIMARY KEY,
    teamName VARCHAR(75) NOT NULL,
    leagueID INT,
    games INT DEFAULT 0,
    wins INT DEFAULT 0,
    losses INT DEFAULT 0,
    points_scored INT DEFAULT 0,
    points_conceded INT DEFAULT 0,
    field_goal_percentage DECIMAL(5,2) DEFAULT 0.00,
    three_point_percentage DECIMAL(5,2) DEFAULT 0.00,
    FOREIGN KEY (leagueID) REFERENCES League(leagueID) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS Player (
    playerID INT PRIMARY KEY,
    teamID INT,
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
