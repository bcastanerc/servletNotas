package com.liceu.notes.services;

import com.liceu.notes.dao.NoteDAOImplementation;
import com.liceu.notes.models.Note;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NoteService {
    NoteDAOImplementation nd = new NoteDAOImplementation();

    public List<Note> getAllFromId(int user_id) {
       return  nd.getAllFromId(user_id);
    }

    public void add(Note note){
        nd.add(note);
    }

    /**
     * Cuts the note text and title to the maximum size of the note to a better display.
     * @param notes Notes of the user.
     * @return the same notes but with max of characters.
     */
    public List<Note> cutNotes(List<Note> notes){
        for(Note n : notes){
            if(n.getText().length() > 230) n.setText(n.getText().substring(0,230) + "...");
            if(n.getTitle().length() > 37) n.setTitle(n.getTitle().substring(0,37) + "...");
        }
        return notes;
    }

    public Note searchById(int id){
        return nd.searchById(id);
    }

    public void update(Note note) {
        nd.update(note);
    }

    public void delete(int id){
        nd.delete(id);
    }

    public void shareNoteToUserById(int id_shared_user, int id_note){
        nd.shareNoteToUserById(id_shared_user, id_note);
    }

    public void deleteSharedNote(int id_shared_user, int id_note){
        nd.deleteSharedNote(id_shared_user, id_note);
    }

    public List<String> getAllSharedUsersFromIdNote(int id_note) {
     return nd.getAllSharedUsersFromIdNote(id_note);
    }

    public boolean isNoteSharedToUser(int id_shared_user, int id_note) {
        return nd.isNoteSharedToUser(id_shared_user, id_note);
    }

    /**
     * This will filter the given notes by type matching the input.
     * @param notes All the notes of the user.
     * @param input search input (title, content...).
     * @param type the type of the search.
     * @return filtered list of notes.
     */
    public List<Note> filterNotes(List<Note> notes, String input, int type){
        List<Note> filteredNotes = new ArrayList<>();
        switch (type){
            case 1:
                // Busqueda titulo
                for (Note n : notes) if (n.getTitle().toLowerCase().contains(input.toLowerCase())) filteredNotes.add(n);
                break;
            case 2:
                // Busqueda texto
                for (Note n : notes) if (n.getText().toLowerCase().contains(input.toLowerCase())) filteredNotes.add(n);
                break;
            case 3:
                // Busqueda expresion regular
                Pattern pattern = Pattern.compile(input);
                for (Note n : notes) {
                    Matcher noteMatcher = pattern.matcher(n.getText());
                    if (noteMatcher.matches()) filteredNotes.add(n);
                }
                break;
            case 4:
                // Busqueda antes de fecha de creacion
                try {
                    for (Note n : notes) {
                        SimpleDateFormat formatter5 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date1 = formatter5.parse(input);
                        if (n.getCreation_date().after(date1)) filteredNotes.add(n);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case 5:
                // Busqueda antes de fecha de modificacion
                try {
                    for (Note n : notes) {
                        SimpleDateFormat formatter5 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date1 = formatter5.parse(input);
                        System.out.println("Date" + date1.toString());
                        if (n.getLast_modification().after(date1))filteredNotes.add(n);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;

        }
        return filteredNotes;
    }
}
