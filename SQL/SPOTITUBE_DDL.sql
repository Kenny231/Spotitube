-- Database aanmaken
DROP DATABASE IF EXISTS Spotitube;
CREATE DATABASE Spotitube;

USE Spotitube;
-- Table: Track
DROP TABLE IF EXISTS Track;
CREATE TABLE Track(
	id INT NOT NULL AUTO_INCREMENT,
	performer VARCHAR(100) NOT NULL,
    title VARCHAR(100) NOT NULL,
    url VARCHAR(200) NOT NULL,
    duration INT NOT NULL,
	-- Table constraints
	PRIMARY KEY(id)
);
	
-- Table: Songs
DROP TABLE IF EXISTS Songs;
CREATE TABLE Songs (
	id INT NOT NULL,
    album VARCHAR(100) NOT NULL,
	-- Table constraints
	PRIMARY KEY(id),
	FOREIGN KEY (id) REFERENCES Track(id)
		ON UPDATE CASCADE 
		ON DELETE CASCADE
);

-- Table: Videos
DROP TABLE IF EXISTS Videos;
CREATE TABLE Videos (
	id INT NOT NULL,
    playcount INT NOT NULL DEFAULT 0,
    publication_date VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    -- Table constraints
    PRIMARY KEY(id),
	FOREIGN KEY (id) REFERENCES Track(id)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);

-- Table: Playlist
DROP TABLE IF EXISTS Playlist;
CREATE TABLE Playlist (
	id INT NOT NULL AUTO_INCREMENT,
	owner VARCHAR(100) NOT NULL,
	name VARCHAR(100) NOT NULL,
	-- Table constraints
	PRIMARY KEY(id)
);

-- Table: Availability
DROP TABLE IF EXISTS Availability;
CREATE TABLE Availability (
	playlist_id INT NOT NULL,
	track_id INT NOT NULL,
	available BIT NOT NULL,
	-- Table constraints
	PRIMARY KEY(playlist_id, track_id),
	FOREIGN KEY (playlist_id) REFERENCES Playlist(id)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	FOREIGN KEY (track_id) REFERENCES Track(id)
		ON UPDATE CASCADE
		ON DELETE CASCADE		
);

/*
	Stored procedures, die alles in één keer goed invoeren.
*/
DROP PROCEDURE IF EXISTS sp_insert_song;
DROP PROCEDURE IF EXISTS sp_insert_video;

DELIMITER $

CREATE PROCEDURE sp_insert_song(
	IN i_performer VARCHAR(100),
	IN i_title VARCHAR(100),
	IN i_url VARCHAR(200),
	IN i_duration INT,
	IN i_album VARCHAR(100)
)
BEGIN
	DECLARE i_id INT;
    
	INSERT INTO Track VALUES (0, i_performer, i_title, i_url, i_duration);
	-- Id ophalen die gegenereerd is.
	SELECT id INTO i_id FROM Track WHERE performer = i_performer AND title = i_title AND url = i_url AND duration = i_duration;
	-- Song data inserten.
	INSERT INTO songs VALUES (i_id, i_album);
END $

CREATE PROCEDURE sp_insert_video(
	IN i_performer VARCHAR(100),
	IN i_title VARCHAR(100),
	IN i_url VARCHAR(200),
	IN i_duration INT,
	IN i_playcount INT,
	IN i_publicaton_date VARCHAR(100),
	IN i_description VARCHAR(500)
)
BEGIN
	DECLARE i_id INT;
    
	INSERT INTO Track VALUES (0, i_performer, i_title, i_url, i_duration);
	-- Id ophalen die gegenereerd is.
	SELECT id INTO i_id FROM Track WHERE performer = i_performer AND title = i_title AND url = i_url AND duration = i_duration;
	-- Song data inserten.
	INSERT INTO videos VALUES (i_id, i_playcount, i_publicaton_date, i_description);
END $

DELIMITER ;

/*
	Views die het gemakkelijker maken om dingen te selecteren.
*/
DROP VIEW IF EXISTS song_view;
CREATE VIEW song_view AS
SELECT T.id, performer, title, url, duration, album
FROM Track T, Songs S
WHERE S.id = T.id;

DROP VIEW IF EXISTS video_view;
CREATE VIEW video_view AS
SELECT T.id, performer, title, url, duration, playcount, publication_date, description
FROM Track T, Videos V
WHERE V.id = T.id;