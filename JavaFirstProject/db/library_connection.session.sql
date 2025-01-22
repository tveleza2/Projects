USE library_db;

CREATE TABLE IF NOT EXISTS users(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(63) NOT NULL,
    surename varchar(63) NOT NULL,
    birthdate DATE NOT NULL,
    email varchar(63) NOT NULL,
    password varchar(63) NOT NULL,
    isAdmin BOOLEAN NOT NULL DEFAULT FALSE
)

CREATE TABLE IF NOT EXISTS authors(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(63) NOT NULL,
    nationality varchar(63) NOT NULL
)

CREATE TABLE IF NOT EXISTS genres(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(63) NOT NULL
)

CREATE TABLE IF NOT EXISTS books(
    id INT PRIMARY KEY AUTO_INCREMENT,
    title varchar(63) NOT NULL,
    author_id INT NOT NULL,
    genre_id INT NOT NULL,
    lent BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (author_id)  REFERENCES authors(id),
    FOREIGN KEY (genre_id) REFERENCES genre(id)
)

CREATE TABLE IF NOT EXISTS lent_books(
    id INT PRIMARY KEY AUTO_INCREMENT,
    book_id INT NOT NULL,
    user_id INT NOT NULL,
    lent_date DATE NOT NULL,
    return_date DATE DEFAULT NULL,
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
)

-- INSERT INTO authors(name,nationality) VALUES
-- ('J.K. Rowling','British'),
-- ('J.R.R. Tolkien','British'),
-- ('George R.R. Martin','American'),
-- ('Stephen King','American'),
-- ('Agatha Christie','British'),
-- ('Dan Brown','American'),
-- ('Paulo Coelho','Brazilian'),
-- ('Jules Verne','French'),
-- ('Arthur Conan Doyle','British'),
-- ('William Shakespeare','British'),
-- ('Gabriel Garcia Marquez','Colombian');



INSERT INTO books (title, author_id, genre_id, lent) VALUES ('Cien años de soledad',(SELECT a.id from authors a WHERE a.name = "Gabriel Garcia Marquez"),(SELECT g.id from genre g WHERE g.name = "Fantasy"),FALSE);

SELECT b.title Titulo,a.name Autor,g.name Genero,b.lent Prestado FROM books b JOIN authors a ON b.author_id=a.id JOIN genre g ON b.genre_id=g.id;