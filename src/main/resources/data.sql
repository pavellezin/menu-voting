INSERT INTO USERS (EMAIL, FIRST_NAME, LAST_NAME, PASSWORD)
VALUES ('user@gmail.com', 'User_First', 'User_Last', '{noop}password'),
       ('admin@javaops.ru', 'Admin_First', 'Admin_Last', '{noop}admin');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO RESTAURANT (NAME)
VALUES ('Basilio'),
       ('Takatak');

INSERT INTO MENU(MENU_DATE, RESTAURANT_ID)
VALUES (NOW(), 1),
       (NOW(), 2);

INSERT INTO LUNCH (NAME, PRICE, MENU_ID)
VALUES ('Salat', 500, 1),
       ('Pasta', 1200, 1),
       ('Salat', 600, 2),
       ('Pizza', 1500, 2);