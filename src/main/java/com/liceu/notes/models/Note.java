package com.liceu.notes.models;

public class Note {
    private int id;
    private String title;
    private String text;
    private String creation_date;
    private String last_modification;
    private int user_id;

    public Note(int id, String title, String text, String creation_date, String last_modification, int user_id) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.creation_date = creation_date;
        this.last_modification = last_modification;
        this.user_id = user_id;
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

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getLast_modification() {
        return last_modification;
    }

    public void setLast_modification(String last_modification) {
        this.last_modification = last_modification;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

}