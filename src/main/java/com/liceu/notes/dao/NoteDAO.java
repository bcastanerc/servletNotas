package com.liceu.notes.dao;

import com.liceu.notes.models.Note;

import java.sql.SQLException;
import java.util.List;

public interface NoteDAO {
    List<Note> getAllFromId(int id);
    void add(Note note);
    void delete(int id);
    void update(Note note);
    void share(Note note);
    Note searchById(int id);
    Note searchByTextString(String text);
}
