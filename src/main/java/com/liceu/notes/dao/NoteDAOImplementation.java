package com.liceu.notes.dao;

import com.liceu.notes.models.Note;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NoteDAOImplementation implements NoteDAO{
    @Override
    public List<Note> getAllFromId(int user_id) {
        List<Note> notes = new ArrayList<>();
        try {
            Connection c = Database.getConnection();
            assert c != null;
            PreparedStatement ps = c.prepareStatement("select * from notes where user_id = ?");
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String text = rs.getString("text");
                String creation_date = rs.getString("creation_date");
                String last_modification = rs.getString("last_modification");

                notes.add(new Note(id, title, text, creation_date, last_modification, user_id));
            }
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return notes;
    }

    @Override
    public void add(Note note) {
        try {
            Connection c = Database.getConnection();
            assert c != null;
            PreparedStatement ps = c.prepareStatement("insert into notes (title, text, creation_date, last_modification, user_id) " +
                    "values(?,?,strftime('%Y-%m-%d %H:%M:%S','now'),strftime('%Y-%m-%d %H:%M:%S','now'),?)");
            ps.setString(1, note.getTitle());
            ps.setString(2, note.getText());
            ps.setInt(3, note.getUser_id());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error insert note");
        }
    }

    @Override
    public void delete(Note note) {

    }

    @Override
    public void share(Note note) {

    }

    @Override
    public Note searchById(int id) {
        return null;
    }

    @Override
    public Note searchByTextString(String text) {
        return null;
    }
}
