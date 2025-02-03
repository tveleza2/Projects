USE library_db;

DROP TABLE IF EXISTS lent_books;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users(
    id INT PRIMARY KEY AUTO_INCREMENT,
    email varchar(255) UNIQUE NOT NULL,
    name varchar(255) NOT NULL,
    surename varchar(255) NOT NULL,
    birthdate DATE NOT NULL,
    password varchar(255) NOT NULL,
    isAdmin BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS authors(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(255) UNIQUE NOT NULL,
    nationality varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS genres(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS books(
    id INT PRIMARY KEY AUTO_INCREMENT,
    title varchar(255) NOT NULL,
    author_id INT NOT NULL,
    genre_id INT NOT NULL,
    lent BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (author_id)  REFERENCES authors(id),
    FOREIGN KEY (genre_id) REFERENCES genres(id)
);

CREATE TABLE IF NOT EXISTS lent_books(
    id INT PRIMARY KEY AUTO_INCREMENT,
    book_id INT NOT NULL,
    user_id INT NOT NULL,
    lent_date DATE NOT NULL,
    expected_return_date DATE DEFAULT (DATE_ADD(lent_date,INTERVAL 15 DAY)),
    return_date DATE DEFAULT NULL,
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO authors(name,nationality) VALUES
('J.K. Rowling','British'),
('J.R.R. Tolkien','British'),
('George R.R. Martin','American'),
('Stephen King','American'),
('Agatha Christie','British'),
('Dan Brown','American'),
('Paulo Coelho','Brazilian'),
('Jules Verne','French'),
('Arthur Conan Doyle','British'),
('William Shakespeare','British'),
('Gabriel Garcia Marquez','Colombian');


SELECT * FROM authors;

INSERT INTO genres(name) VALUES
('Fantasy'),
('Mystery'),
('Horror'),
('Adventure'),
('Science Fiction'),
('Romance'),
('Historical Fiction'),
('Thriller'),
('Drama'),
('Poetry');

INSERT INTO books (title, author_id, genre_id, lent) VALUES ('Cien años de soledad',(SELECT a.id from authors a WHERE a.name = "Gabriel Garcia Marquez"),(SELECT g.id from genres g WHERE g.name = "Fantasy"),FALSE);

SELECT * FROM books;

SELECT b.title Titulo,a.name Autor,g.name Genero,b.lent Prestado FROM books b JOIN authors a ON b.author_id=a.id JOIN genres g ON b.genre_id=g.id;

UPDATE books SET lent = FALSE WHERE title = 'Cien años de soledad' AND id = 1;


SELECT * FROM users;

DELETE FROM users;

ALTER TABLE users AUTO_INCREMENT = 1;

SELECT concat(name, ' ' ,surename) AS Nombre, email AS Correo, isAdmin AS Administrador FROM users WHERE id = 1;

INSERT INTO users (email, name, surename, birthdate, password, isAdmin) VALUES ("tvelezacosta5@gmail.com","Tomas","Velez",DATE("2002-02-25"),"abc123",TRUE);

SELECT concat(name, ' ' ,surename) AS Nombre, email AS Correo, isAdmin AS Administrador FROM users WHERE id = 1;