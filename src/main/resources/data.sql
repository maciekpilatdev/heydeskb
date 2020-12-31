INSERT INTO BUILDING_DB VALUES (1,'budynek 1');
INSERT INTO BUILDING_DB VALUES (2,'budynek 2');

INSERT INTO FLOOR_DB VALUES (1,'piętro 1', 1);
INSERT INTO FLOOR_DB VALUES (2,'piętro 2', 1);
INSERT INTO FLOOR_DB VALUES (3,'piętro 1', 2);
INSERT INTO FLOOR_DB VALUES (4,'piętro 2', 2);
INSERT INTO FLOOR_DB VALUES (5,'piętro 3', 2);

INSERT INTO ROOM_DB VALUES (1,'pokój 1', 1);
INSERT INTO ROOM_DB VALUES (2,'pokój 2', 1);
INSERT INTO ROOM_DB VALUES (3,'pokój 1', 2);
INSERT INTO ROOM_DB VALUES (4,'pokój 1', 3);
INSERT INTO ROOM_DB VALUES (5,'pokój 1', 4);
INSERT INTO ROOM_DB VALUES (6,'pokój 1', 5);
INSERT INTO ROOM_DB VALUES (7,'pokój 2', 3);
INSERT INTO ROOM_DB VALUES (8,'pokój 3', 3);
INSERT INTO ROOM_DB VALUES (9,'pokój 4', 3);

INSERT INTO DESK_DB VALUES(1, TRUE, 1, FALSE, 'Biurko w rogu pokoju.', null, 1, FALSE, 1);
INSERT INTO DESK_DB VALUES(2, TRUE, 1, FALSE, 'Biurko na środku.', null, 1, TRUE, 2);
INSERT INTO DESK_DB VALUES(3, TRUE, 1, FALSE, 'Biurko na środku pokoju.', null, 2, TRUE, 3);
INSERT INTO DESK_DB VALUES(4, TRUE, 1, FALSE, 'Biurko z lewej strony', null, 2, FALSE, 4);
INSERT INTO DESK_DB VALUES(5, TRUE, 1, FALSE, 'Biurko z prawej strony', null, 2, FALSE, 5);
INSERT INTO DESK_DB VALUES(6, TRUE, 1, FALSE, 'Biurko z lewej strony', null, 1, FALSE, 6);
INSERT INTO DESK_DB VALUES(7, TRUE, 2, FALSE, 'Biurko z prawej strony', null, 1, FALSE, 7);
INSERT INTO DESK_DB VALUES(8, TRUE, 2, FALSE, 'Biurko przy drzwiach', null, 2, FALSE, 8);
INSERT INTO DESK_DB VALUES(9, TRUE, 2, FALSE, 'Biurko przy drzwiach', null, 3, TRUE, 9);
INSERT INTO DESK_DB VALUES(10, TRUE, 2, FALSE, 'Biurko przy ścianie', null, 4, TRUE, 1);
INSERT INTO DESK_DB VALUES(11, TRUE, 2, FALSE, 'Biurko w kanciapie', null, 4, TRUE, 1);
-- INSERT INTO USERS VALUES (1, 'true','USER, ROLE_USER','$2y$12$7Iw7fWqw.QHcY6cSHgMKPuAKf1k8wYy9jfGu0NEu.D/mFsewzh2TS','user',0);

-- INSERT INTO USERS VALUES (2,'true','ADMIN, ROLE_ADMIN','$2y$12$7Iw7fWqw.QHcY6cSHgMKPuAKf1k8wYy9jfGu0NEu.D/mFsewzh2TS','admin',0);

-- INSERT INTO USERS VALUES (1,1,1,1,1,true);

