INSERT INTO app_user (name, email, password) VALUES 
('Alice Smith', 'alice.smith@example.com', 'password123'),
('Bob Johnson', 'bob.johnson@example.com', 'password456'),
('Charlie Brown', 'charlie.brown@example.com', 'password789'),
('David Williams', 'david.williams@example.com', 'password101'),
('Eve Davis', 'eve.davis@example.com', 'password102'),
('Frank Miller', 'frank.miller@example.com', 'password103'),
('Grace Wilson', 'grace.wilson@example.com', 'password104'),
('Hannah Moore', 'hannah.moore@example.com', 'password105'),
('Ian Taylor', 'ian.taylor@example.com', 'password106'),
('Jackie Anderson', 'jackie.anderson@example.com', 'password107'),
('Kurt Thomas', 'kurt.thomas@example.com', 'password108'),
('Laura Harris', 'laura.harris@example.com', 'password109'),
('Mike Martin', 'mike.martin@example.com', 'password110'),
('Nancy Lee', 'nancy.lee@example.com', 'password111'),
('Oscar Walker', 'oscar.walker@example.com', 'password112'),
('Paula Scott', 'paula.scott@example.com', 'password113'),
('Quincy Young', 'quincy.young@example.com', 'password114'),
('Rachel King', 'rachel.king@example.com', 'password115'),
('Steve Lewis', 'steve.lewis@example.com', 'password116'),
('Tina Clark', 'tina.clark@example.com', 'password117');

INSERT INTO user_roles (user_id, roles) VALUES
(1, 'RIDER'),
(2, 'DRIVER'),
(3, 'DRIVER'),
(4, 'DRIVER'),
(5, 'DRIVER'),
(6, 'DRIVER'),
(7, 'DRIVER'),
(8, 'DRIVER'),
(9, 'DRIVER'),
(10, 'DRIVER'),
(11, 'DRIVER'),
(12, 'DRIVER'),
(13, 'DRIVER'),
(14, 'DRIVER'),
(15, 'DRIVER'),
(16, 'DRIVER'),
(17, 'DRIVER'),
(18, 'DRIVER'),
(19, 'DRIVER'),
(20, 'DRIVER');

INSERT INTO rider (id, user_id, rating) VALUES
(1,1,4.9);

INSERT INTO driver (id, user_id, rating, available, current_location) VALUES
(1, 2, 4.7, true, ST_GeomFromText('POINT(77.1050 28.4326)', 4326)),
(2, 3, 4.8, true, ST_GeomFromText('POINT(77.1080 28.7041)', 4326)),
(3, 4, 4.6, true, ST_GeomFromText('POINT(77.1110 28.7045)', 4326)),
(4, 5, 4.9, true, ST_GeomFromText('POINT(77.1140 28.7043)', 4326)),
(5, 6, 4.0, true, ST_GeomFromText('POINT(77.1170 28.7042)', 4326)),
(6, 7, 4.2, true, ST_GeomFromText('POINT(77.1200 28.7044)', 4326)),
(7, 8, 3.5, true, ST_GeomFromText('POINT(77.1230 28.7040)', 4326)),
(8, 9, 4.1, true, ST_GeomFromText('POINT(77.1260 28.7046)', 4326)),
(9, 10, 4.6, true, ST_GeomFromText('POINT(77.1290 28.7042)', 4326)),
(10, 11, 4.3, true, ST_GeomFromText('POINT(77.1320 28.7047)', 4326)),
(11, 12, 4.7, true, ST_GeomFromText('POINT(77.1350 28.7045)', 4326)),
(12, 13, 3.9, true, ST_GeomFromText('POINT(77.1380 28.7041)', 4326)),
(13, 14, 4.4, true, ST_GeomFromText('POINT(77.1410 28.7043)', 4326)),
(14, 15, 4.8, true, ST_GeomFromText('POINT(77.1440 28.7048)', 4326)),
(15, 16, 3.7, true, ST_GeomFromText('POINT(77.1470 28.7042)', 4326)),
(16, 17, 4.2, true, ST_GeomFromText('POINT(77.1500 28.7049)', 4326)),
(17, 18, 4.6, true, ST_GeomFromText('POINT(77.1530 28.7040)', 4326)),
(18, 19, 4.3, true, ST_GeomFromText('POINT(77.1560 28.7050)', 4326)),
(19, 20, 4.5, true, ST_GeomFromText('POINT(77.1590 28.7041)', 4326));

INSERT INTO wallet (id, user_id, balance) VALUES
(1, 1, 100),
(2, 2, 500);
