CREATE TABLE users (
	id INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 100, INCREMENT BY 1) PRIMARY KEY,
	name VARCHAR(30),
	age INTEGER,
	country VARCHAR(10),
	city VARCHAR(20)
);