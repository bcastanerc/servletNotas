package com.liceu.notes.dao;

import com.liceu.notes.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImplementation implements UserDAO{
    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            Connection c = Database.getConnection();
            PreparedStatement ps = c.prepareStatement("select * from users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String username = rs.getString("username");
                String password = rs.getString("password");

                users.add(new User(id, email, username, password));
            }
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void add(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public User getFromId(int id) {
        return null;
    }
}
