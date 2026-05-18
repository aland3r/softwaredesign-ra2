package com.flashbrix.context;

import com.flashbrix.language.Language;
import com.flashbrix.language.Level;
public class Book extends Context {

    private String isbn;
    private String fileUrl;

    public Book(String title, Language language, Level level, String isbn, String fileUrl) {
        super(title, language, level, Source.BOOK);
        this.isbn = isbn;
        this.fileUrl = fileUrl;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    public String toString() {
        return "Book{" +
                super.toString() +
                ", isbn='" + isbn + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                '}';
    }
}