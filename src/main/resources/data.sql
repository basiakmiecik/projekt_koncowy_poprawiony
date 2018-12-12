insert into user (first_name, last_name, username, password, enabled, saldo, image_url)
values ('Anna',	'Nowak',	'admin2',	'{noop}admin2', true, 0,'https://i.vimeocdn.com/portrait/7854848_120x120.jpg'),
('Ala',	'Pol',	'admin',	'{noop}admin',true, 0, 'https://tandemhr.com/wp-content/uploads/2017/01/carrie-storozynski-200x200-cartoon.gif'),
('Adam',	'Kowalski',	'akowalski','{noop}abcdef22',true, 0, 'https://thumbs.dreamstime.com/thumblarge_9836/98363599.jpg');

insert into user_role (username, role) values
('admin2', 'admin'),
('admin', 'user'),
('akowalski', 'user');



insert into person(first_name, last_name, to_give, to_get, user_id, information_Give,information_Get, age, about, image)
values ('Tomasz', 'Lok',	23.45,	54.89, 2, 'kolacja we wtorek', 'basen z Asią', 18, 'Trzeba czasem przypomnieć, bo zapomina', 'http://www.mallvarna.com/files/rte/article33/batux-tux-g2-hd-200x200.png'),
('Alicja','Flet',	43.98,	87.90, 2,'bilard w srode z Karolem','ksiazki z empiku',21, 'Oddaje w terminie' ,'http://3.bp.blogspot.com/-xw6ydLVK3iI/T2YRz22SWQI/AAAAAAAABMI/sDe9QqqSj5Q/s200/fleur-coquelicot.png' ),
('Karol',	'Tomis', 89.90,	75.90, 1,'tenis w srodę', 'pozyczka',55,'nie do odzyskania' , 'http://www.elistmania.com/wp-content/uploads/2010/03/irritating_cartoon_characters.png'),
('Marta',	'Wojt',	9.0,	0, 1, 'obiad w czawartek', ' ', 15, 'po urodzinach odda wszytskie długi',  'https://samequizy.pl/wp-content/uploads/2016/02/filing_images_846df94125f7.jpeg');
