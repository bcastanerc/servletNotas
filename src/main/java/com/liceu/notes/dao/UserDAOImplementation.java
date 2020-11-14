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
            assert c != null;
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
        try {
            Connection c = Database.getConnection();
            assert c != null;
            PreparedStatement ps = c.prepareStatement("insert into users (email,username,password) values(?,?,?)");
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.execute();

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error insert user");
        }
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public User getFromLogin(String email, String password) {
        User user = null;
        try {
            Connection c = Database.getConnection();
            assert c != null;

            PreparedStatement ps = c.prepareStatement("select * from users where email = ? and password = ?");
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            user = new User( rs.getInt("id"),rs.getString("email"), rs.getString("username"), rs.getString("password"));
            ps.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean isEmailNotUsed(String email) {
        try {
            Connection c = Database.getConnection();
            assert c != null;
            PreparedStatement ps = c.prepareStatement("select email from users where email = ?");
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            return !rs.next();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUserFromId(int id) {
        try {
            Connection c = Database.getConnection();
            assert c != null;
            PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            String email = rs.getString("email");
            String username = rs.getString("username");
            String password = rs.getString("password");

            ps.close();
            return new User(id, email, username, password);
            
            }catch (Exception e){
                e.printStackTrace();
            }
        return null;
    }
}
