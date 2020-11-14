package com.liceu.notes.dao;

import com.liceu.notes.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<User> getAll() throws SQLException;
    void add(User user);
    void delete(User user);
    void update(User user);
    User getFromLogin(String email, String password);
    boolean isEmailNotUsed(String email);
    User getUserFromId(int id);

}
