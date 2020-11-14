package com.liceu.notes.services;

import com.liceu.notes.dao.NoteDAOImplementation;
import com.liceu.notes.models.Note;

import java.util.List;

public class NoteService {
    NoteDAOImplementation nd = new NoteDAOImplementation();

    public List<Note> getAllFromId(int user_id) {
       return  nd.getAllFromId(user_id);
    }

    public void add(Note note){
        nd.add(note);
    }

    public List<Note> cutNotes(List<Note> notes){
        for(Note n : notes){
            if(n.getText().length() > 120) n.setText(n.getText().substring(0,120) + "...");
            if(n.getTitle().length() > 37) n.setTitle(n.getTitle().substring(0,37) + "...");
        }
        return notes;
    }
}
