package com.liceu.notes.services;

import com.liceu.notes.dao.NoteDAOImplementation;
import com.liceu.notes.models.Note;

import java.sql.SQLException;
import java.util.List;
import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

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
            if(n.getText().length() > 160) n.setText(n.getText().substring(0,160) + "...");
            if(n.getTitle().length() > 37) n.setTitle(n.getTitle().substring(0,37) + "...");
        }
        return notes;
    }

    public Note searchById(int id){
        return nd.searchById(id);
    }

    public void update(Note note) throws SQLException {
        nd.update(note);
    }

    public void delete(int id){
        nd.delete(id);
    }
}
