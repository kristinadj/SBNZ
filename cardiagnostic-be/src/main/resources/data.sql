INSERT INTO USER (id, username, password) VALUES (1, 'admin', '$2y$12$Cg76kI/v91EAqOjRWSrr/utljtY604voeDEJ1ldYq46iWTo7Mo1wO');
INSERT INTO USER (id, username, password) VALUES (2, 'user', '$2y$12$Cg76kI/v91EAqOjRWSrr/utljtY604voeDEJ1ldYq46iWTo7Mo1wO');

INSERT INTO AUTHORITY (id, name) VALUES (1, 'EXPERT');

INSERT INTO AUTHORITY (id, name) VALUES (2, 'AMATEUR');

INSERT INTO USER_AUTHORITIES (user_id, authorities_id) VALUES (1, 1);
INSERT INTO USER_AUTHORITIES (user_id, authorities_id) VALUES (2, 2);