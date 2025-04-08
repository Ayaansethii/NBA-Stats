-- Insert Sample Leagues
INSERT INTO League (leagueName) VALUES 
('NBA East'),
('NBA West'),
('College League');

-- Insert Sample Teams
INSERT INTO Team (teamName) VALUES
('Toronto Raptors'),
('Miami Heat'),
('Golden State Warriors'),
('LA Lakers'),
('Duke Blue Devils');

-- Associate Teams with Leagues
INSERT INTO Team_League (leagueID, teamID) VALUES
-- NBA East Teams
(1, 1), -- Toronto Raptors
(1, 2), -- Miami Heat
-- NBA West Teams
(2, 3), -- Golden State Warriors
(2, 4), -- LA Lakers
-- College League Teams
(3, 5); -- Duke Blue Devils
