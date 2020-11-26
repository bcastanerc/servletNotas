package com.liceu.notes.dao;

import com.liceu.notes.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<User> getAll() throws SQLException;
    void add(User user);
    void delete(int id);
    void update(User user) throws SQLException;
    User getFromLogin(String email, String password);
    boolean isEmailNotUsed(String email);
    User getUserFromId(int id);
    boolean userOwnsNote(int user_id, int id_note);
    User getUserFromEmail(String email);
}
