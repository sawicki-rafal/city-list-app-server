INSERT INTO city (id, name, photo)
SELECT *
FROM CSVREAD('classpath:cities.csv');

INSERT INTO users (username, password, enabled)
VALUES ('reader', '$2a$10$qispuA8E.Bp31Ue2z6LEke3wGc54xNXRNGIjAae4MA6b/3BTegtjS', true),
       ('editor', '$2a$10$CXLifrMc52G5fnTrptHxBe8rI6VSayTYXnwl8hK8XftBIG5y6wqJq', true);

INSERT INTO authorities (username, authority)
VALUES ('reader', 'ROLE_ALLOW_READ'),
       ('editor', 'ROLE_ALLOW_READ'),
       ('editor', 'ROLE_ALLOW_EDIT');
