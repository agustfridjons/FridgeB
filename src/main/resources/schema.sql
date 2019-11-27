DROP TABLE IF EXISTS Recipies;

CREATE TABLE Recipies
(
  id BIGINT  PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(250) NOT NULL,
  rating double DEFAULT 0.0
);