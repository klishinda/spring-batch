insert into authors(id, surname, name) values (1, 'КРИСТИ', 'АГАТА');
insert into authors(id, surname, name) values (2, 'ШЕКЛИ', 'РОБЕРТ');
insert into authors(id, surname) values (3, 'КОНФУЦИЙ');
insert into authors(id, surname, name) values (4, 'РОУЛИНГ', 'ДЖОАН');
insert into authors(id, surname, name) values (5, 'ЛОНДОН', 'ДЖЕК');
insert into authors(id, surname, name) values (6, 'ПИЗ', 'АЛЛАН');
insert into authors(id, surname, name) values (7, 'ПИЗ', 'БАРБАРА');

insert into genres(id, name) values (1, 'ДЕТЕКТИВ');
insert into genres(id, name) values (2, 'РОМАН');
insert into genres(id, name) values (3, 'ПРИКЛЮЧЕНИЯ');
insert into genres(id, name) values (4, 'ФАНТАСТИКА');
insert into genres(id, name) values (5, 'ФЭНТЕЗИ');
insert into genres(id, name) values (6, 'СКАЗКИ');
insert into genres(id, name) values (7, 'АНЕКДОТЫ');
insert into genres(id, name) values (8, 'СТИХИ');
insert into genres(id, name) values (9, 'БИОГРАФИЯ');
insert into genres(id, name) values (10,'ПСИХОЛОГИЯ');
insert into genres(id, name) values (11, 'ФИЛОСОФИЯ');

insert into books(id, name, pages) values (1, 'Восточный экспресс', 150);
insert into books(id, name, pages) values (2, 'Гарри Поттер и Философский Камень', 700);
insert into books(id, name, pages) values (3, 'Координаты чудес света', 200);
insert into books(id, name, pages) values (4, 'Сердца трёх', 350);
insert into books(id, name, pages) values (5, 'Язык телодвижений', 300);
insert into books(id, name, pages) values (6, '10 негритят', 180);
insert into books(id, name, pages) values (7, 'Народные сказки', 100);

insert into book_authors(id_book, id_author) values (1, 1);
insert into book_authors(id_book, id_author) values (2, 4);
insert into book_authors(id_book, id_author) values (3, 2);
insert into book_authors(id_book, id_author) values (4, 5);
insert into book_authors(id_book, id_author) values (5, 6);
insert into book_authors(id_book, id_author) values (5, 7);
insert into book_authors(id_book, id_author) values (6, 1);

insert into genres_of_books(id_book, id_genre) values (1, 1);
insert into genres_of_books(id_book, id_genre) values (2, 5);
insert into genres_of_books(id_book, id_genre) values (3, 4);
insert into genres_of_books(id_book, id_genre) values (4, 2);
insert into genres_of_books(id_book, id_genre) values (4, 3);
insert into genres_of_books(id_book, id_genre) values (5, 10);
insert into genres_of_books(id_book, id_genre) values (6, 1);
insert into genres_of_books(id_book, id_genre) values (7, 6);

insert into book_comments values(1, 1, 10, 'Alex', 'Очень интересный детектив!');
insert into book_comments values(2, 1, 1, 'Sergey', 'Очень скучный детектив!');
insert into book_comments values(3, 2, 8, '-==NAGIBATOR2000==-', null);
insert into book_comments values(4, 4, 5, 'Test1', 'Test1');
insert into book_comments values(5, 4, 6, 'Test2', 'Test2');
insert into book_comments values(6, 4, 7, 'Test3', 'Test3');