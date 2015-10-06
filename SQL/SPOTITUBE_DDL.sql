-- Database aanmaken
DROP DATABASE IF EXISTS Spotitube;
CREATE DATABASE Spotitube;

USE Spotitube;
-- Table: Songs
DROP TABLE IF EXISTS Songs;
CREATE TABLE Songs (
	id INT NOT NULL AUTO_INCREMENT,
	performer VARCHAR(100) NOT NULL,
    title VARCHAR(100) NOT NULL,
    url VARCHAR(200) NOT NULL,
    duration INT NOT NULL,
    album VARCHAR(100) NOT NULL,
    
    PRIMARY KEY(id)
);

-- Table: Videos
DROP TABLE IF EXISTS Videos;
CREATE TABLE Videos (
	id INT NOT NULL AUTO_INCREMENT,
	performer VARCHAR(100) NOT NULL,
    title VARCHAR(100) NOT NULL,
    url VARCHAR(200) NOT NULL,
    duration INT NOT NULL,
    playcount INT NOT NULL DEFAULT 0,
    publication_data DATE NOT NULL,
    description VARCHAR(500),
    
    PRIMARY KEY(id)
);