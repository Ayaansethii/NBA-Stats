-- Create the SearchLeagueDB
CREATE DATABASE IF NOT EXISTS SearchLeagueDB;
USE SearchLeagueDB;

-- League Table
CREATE TABLE League (
    leagueID INT PRIMARY KEY AUTO_INCREMENT,
    leagueName VARCHAR(50) NOT NULL UNIQUE
);

-- Team Table
CREATE TABLE Team (
    teamID INT PRIMARY KEY AUTO_INCREMENT,
    teamName VARCHAR(75) NOT NULL UNIQUE
);

-- Team_League Table (Many-to-Many Relationship)
CREATE TABLE Team_League (
    leagueID INT NOT NULL,
    teamID INT NOT NULL,
    PRIMARY KEY (leagueID, teamID),
    FOREIGN KEY (leagueID) REFERENCES League(leagueID) ON DELETE CASCADE,
    FOREIGN KEY (teamID) REFERENCES Team(teamID) ON DELETE CASCADE
);

-- Indexes for Faster Search
CREATE INDEX idx_league_name ON League(leagueName);
CREATE INDEX idx_team_name ON Team(teamName);
