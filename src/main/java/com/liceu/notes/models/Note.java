package com.liceu.notes.models;

import java.sql.Date;

public class Note {
    private int id;
    private String title;
    private String text;
    private Date creationDate;
    private Date modificationDate;

    Note(int id, String title, String text, Date creationDate, Date modificationDate){
        this.id = id;
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }
}
