INSERT INTO person (id, email, full_name, password, phone, role, username, pass_id) VALUES
(1, 'admin@admin.ru', 'Admin Adminovich Admin', '$2a$10$MN3w9GysLoJAYDPo8LJVNOeW0Qp/pCcBSP6t6WqrEMrdaee7.OgPK',79787777777,'ROLE_ADMIN','admin', 23),
(2, 'user@user.ru', 'User Userovich User', '$2a$10$MN3w9GysLoJAYDPo8LJVNOeW0Qp/pCcBSP6t6WqrEMrdaee7.OgPK',79781111111,'ROLE_USER','user', 21);

INSERT INTO car VALUES
(1,'Купе', 'Porsche', '1', 'Красный', 'Porsche germany marka very good', '2.0 Twin turbo', 333, '911', 35000, true),
(2,'Купе', 'Porsche', '1', 'Красный', 'Porsche germany marka very good', '2.0 Twin turbo', 333, '912', 35000, true),
(3,'Купе', 'Porsche', '1', 'Красный', 'Porsche germany marka very good', '2.0 Twin turbo', 333, '913', 35000, true),
(4,'Купе', 'Porsche', '1', 'Красный', 'Porsche germany marka very good', '2.0 Twin turbo', 333, '914', 35000, true);

INSERT INTO booking VALUES
(1,'2022-06-08 16:00:00.000000','2022-06-01 16:00:00.000000',false,228,1,1);
