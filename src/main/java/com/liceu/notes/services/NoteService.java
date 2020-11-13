package com.liceu.notes.services;

import com.liceu.notes.dao.NoteDAOImplementation;
import com.liceu.notes.models.Note;

import java.util.List;

public class NoteService {
    NoteDAOImplementation nd = new NoteDAOImplementation();

    public List<Note> getAllFromId(int user_id) {
       return nd.getAllFromId(user_id);
    }

    public void add(Note note){
        nd.add(note);
    }
}
