package com.liceu.notes.dao;

import com.liceu.notes.models.Note;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteDAOImplementation implements NoteDAO{
    @Override
    public List<Note> getAllFromId(int user_id) {
        List<Note> notes = new ArrayList<>();
        try {
            Connection c = Database.getConnection();
            assert c != null;
            PreparedStatement ps = c.prepareStatement("select * from notes where user_id = ? union select * from notes where id in (select id_note from shared_note where id_shared_user = ?)");
            ps.setInt(1, user_id);
            ps.setInt(2, user_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String text = rs.getString("text");
                String creation_date = rs.getString("creation_date");
                String last_modification = rs.getString("last_modification");

                notes.add(new Note(id, title, text, creation_date, last_modification, user_id));
            }
            rs.close();
            ps.close();
            Database.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error getAllFromId");
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

            ps.close();
            Database.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error insert note");
        }
    }

    @Override
    public void delete(int id) {
        try {
            Connection c = Database.getConnection();
            assert c != null;
            PreparedStatement ps = c.prepareStatement("delete from notes where id = ?");
            ps.setInt(1, id);

            ps.execute();
            ps.close();
            Database.closeConnection();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Note note) {
        try {
            Connection c = Database.getConnection();
            assert c != null;
            PreparedStatement ps = c.prepareStatement("update notes set title =(?), text = (?), last_modification = strftime('%Y-%m-%d %H:%M:%S','now') where id = (?)");
            ps.setString(1, note.getTitle());
            ps.setString(2, note.getText());
            ps.setInt(3, note.getId());
            ps.executeUpdate();

            ps.close();
            Database.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Error update Notes");
        }
    }

    @Override
    public void share(Note note) {

    }

    @Override
    public Note searchById(int id) {
        try {
            Connection c = Database.getConnection();
            assert c != null;
            PreparedStatement ps = c.prepareStatement("select * from notes where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            String title = rs.getString("title");
            String text = rs.getString("text");
            String creation_date = rs.getString("creation_date");
            String last_modification = rs.getString("last_modification");
            int user_id = rs.getInt("user_id");

            Note noteByID = new Note(id, title, text, creation_date, last_modification, user_id);

            rs.close();
            ps.close();
            Database.closeConnection();
            return noteByID;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

        @Override
    public Note searchByTextString(String text) {
        return null;
    }

    @Override
    public void shareNoteToUserById(int id_shared_user, int id_note) {
        try {
            Connection c = Database.getConnection();
            assert c != null;
            PreparedStatement ps = c.prepareStatement("insert into shared_note (id_shared_user, id_note) values(?,?)");
            ps.setInt(1, id_shared_user);
            ps.setInt(2, id_note);
            ps.execute();

            ps.close();
            Database.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error share note");
        }
    }

    @Override
    public void deleteSharedNote(int id_shared_user, int id_note) {
        try {
            Connection c = Database.getConnection();
            assert c != null;
            PreparedStatement ps = c.prepareStatement("delete from shared_note where id_shared_user = ? and id_note = ?");
            ps.setInt(1, id_shared_user);
            ps.setInt(2, id_note);
            ps.execute();
            ps.close();
            Database.closeConnection();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
