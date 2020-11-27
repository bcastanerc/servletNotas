package com.liceu.notes.dao;

import com.liceu.notes.models.Note;

import java.util.List;

public interface NoteDAO {
    List<Note> getAllFromId(int id);
    void add(Note note);
    void delete(int id);
    void update(Note note);
    void share(Note note);
    Note searchById(int id);
    Note searchByTextString(String text);
    void shareNoteToUserById(int id_shared_user, int id_note);
    void deleteSharedNote(int id_shared_user, int id_note);
}
