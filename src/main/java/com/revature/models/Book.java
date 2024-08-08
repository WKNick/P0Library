package com.revature.models;

public class Book {

    private int book_id;

    private String book_name;

    private String author_name;

    private int copies;

    public Book(){

    }
    public Book(int book_id, String book_name, String author_name, int copies) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.copies = copies;
        this.author_name = author_name;
    }
    public Book(String book_name, String author_name) {
        this.book_name = book_name;
        this.author_name = author_name;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + book_id +
                ", book_name='" + book_name + '\'' +
                ", copies=" + copies +
                '}';
    }

}
