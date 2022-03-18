INSERT INTO city (id, name, photo)
SELECT *
FROM CSVREAD('classpath:cities.csv');