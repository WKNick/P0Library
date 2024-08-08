

drop table members;
drop table library;

CREATE TABLE members (
    member_id serial PRIMARY KEY,
    first_name TEXT NOT null,
    last_name TEXT NOT null
);

INSERT INTO members (first_name, last_name)
VALUES ('Nicholas', 'Mahoney');

SELECT * FROM members;


CREATE TABLE library (
    book_id serial PRIMARY KEY,
    book_name TEXT NOT null,
    author TEXT NOT null,
    copies int not null
);

SELECT * FROM library;

SELECT * FROM book_1;
drop table members;
drop table library;
drop table book_1;
drop table book_3;

-- create table book_{book_id}(member_id_fk, FOREIGN KEY (member_id_fk) REFERENCES members(member_id))