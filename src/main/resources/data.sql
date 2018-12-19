insert into user (first_name, last_name, username, password, enabled, saldo, image_url)
values ('Anna',	'Nowak',	'admin2@wp.pl',	'{noop}admin2', true, 0,'https://i.vimeocdn.com/portrait/7854848_120x120.jpg'),
('Ala',	'Pol',	'admin@wp.pl',	'{noop}admin',true, 0, 'https://tandemhr.com/wp-content/uploads/2017/01/carrie-storozynski-200x200-cartoon.gif'),
('Adam',	'Kowalski',	'akowalski@wp.pl','{noop}abcdef22',true, 0, 'https://thumbs.dreamstime.com/thumblarge_9836/98363599.jpg');

insert into user_role (username, role) values
('admin2@wp.pl', 'admin'),
('admin@wp.pl', 'user'),
('akowalski@wp.pl', 'user');



insert into person(first_name, last_name, user_id, age, about, image) values
('Tomasz', 'Lok', 2, 18, 'Trzeba czasem przypomnieć, bo zapomina', 'http://www.mallvarna.com/files/rte/article33/batux-tux-g2-hd-200x200.png'),
('Alicja','Flet',	2, 21, 'Oddaje w terminie'                     ,'http://3.bp.blogspot.com/-xw6ydLVK3iI/T2YRz22SWQI/AAAAAAAABMI/sDe9QqqSj5Q/s200/fleur-coquelicot.png' ),
('Karol',	'Tomis',1, 55,'nie do odzyskania'                      , 'http://www.elistmania.com/wp-content/uploads/2010/03/irritating_cartoon_characters.png'),
('Marta',	'Wojt',	1, 15, 'po urodzinach odda wszytskie długi'    ,'https://samequizy.pl/wp-content/uploads/2016/02/filing_images_846df94125f7.jpeg');



insert into getting (debt, information_get, person_id) values
(24.65, 'kolacja z Asią i Majką',      1),
(51.00, 'zoo z Tomkiem i resztą',      2),
(33.87, 'zakupy Tesco',                2),
(10.99, 'lunch z Bogusiem w PizzaHut', 1),
(98.88, 'kręgle',                      3),
(21.05, 'squash w środę',              4);

insert into giving (debt, information_give, person_id) values
(4.65, 'obiad w piątek',    3),
(19.80, 'zakupy Biedronka', 4),
(13.79, 'Bułki',            2),
(1.21, 'lunch',             1),
(88.76, 'kręgle i bilard',  3),
(178.00, 'fryzjer w środę', 4);
