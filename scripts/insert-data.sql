INSERT INTO app.Categories VALUES
(1, 'Dewastacja lub uszkodzenie'),
(2, 'Odpady lub zanieczyszczenia'),
(3, 'Zanieczyszczenie powietrza'),
(4, 'Uszkodzenie drogi lub chodnika'),
(5, 'Transport publiczny'),
(6, 'Wodociągi i kanalizacja'),
(7, 'Zieleń gminna'),
(8, 'Nielegalne wysypiska śmieci'),
(9, 'Oświetlenie uliczne'),
(99, 'Inne');

INSERT INTO app.Statuses VALUES
(1, 'Oczekująca'),
(2, 'Rozpatrywana'),
(3, 'W realizacji'),
(4, 'Zakończona'),
(5, 'Anulowana');

INSERT INTO app.Roles VALUES
(1, 'zgłaszający'),
(2, 'pracownik');

INSERT INTO app.communes VALUES
(1, 'Wrocław'),
(2, 'Katowice');

INSERT INTO app.institutions VALUES
(1, 'Urząd Miejski Wrocław', 1),
(2, 'Urząd Miejski Katowice', 2);

INSERT INTO app.departments VALUES
(1, 'Zarząd dróg', 1),
(2, 'Dyrekcja ochrony środowiska', 1);

INSERT INTO app.users VALUES
(1, 'Jo', 'Ko', 'joko@test.pl', '1234', 1);

INSERT INTO app.issues VALUES
(1, NOW(), 'Piekielnie ważna sprawa', 51, 17, 'Głowackiego', '12', '52-240', 'Sępolno', false, 1, 1, 1),
(2, NOW(), 'Piekielnie ważna sprawa', 51, 17, 'Głowackiego', '12', '52-240', 'Sępolno', true, 3, 1, 1),
(3, NOW(), 'Piekielnie ważna sprawa', 51, 17, 'Głowackiego', '12', '52-240', 'Sępolno', false, 2, 1, 2);

INSERT INTO app.issuesstatuses VALUES
(1, NOW(), 1, 1, null),
(2, NOW(), 2, 1, null),
(3, NOW(), 3, 2, null);
