package com.ll.domain;

public class SetNumber3 {
    private final int id;
    private String content;
    private String author;

    public SetNumber3(int id, String content, String authorName) {
        this.id = id;
        this.content = content;
        this.author = authorName;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}