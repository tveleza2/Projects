package com.tva.entities;

public class Book {
    private int id;
    private String title;
    private int authorId;
    private int genreId;
    private boolean lent;

    public Book(int id, String title, int authorId, int genreId, boolean lent) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.genreId = genreId;
        this.lent = lent;
    }

    public Book(String title, int authorId, int genreId, boolean lent){
        this.title = title;
        this.authorId = authorId;
        this.genreId = genreId;
        this.lent = lent;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getGenreId() {
        return genreId;
    }

    public boolean isLent() {
        return lent;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthorId(int author) {
        this.authorId = author;
    }

    public void setGenreId(int genre) {
        this.genreId = genre;
    }

    public void setLent(boolean lent) {
        this.lent = lent;
    }

    @Override
    public String toString() {
        return "[Title=" + title +  ", ID=" + id + ", Author_ID=" + authorId + ", Genre_Id=" + genreId + ", Lent=" + lent + "]";
    }
}
