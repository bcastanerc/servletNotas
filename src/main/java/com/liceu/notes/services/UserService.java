package com.liceu.notes.services;

import com.liceu.notes.dao.UserDAO;
import com.liceu.notes.dao.UserDAOImplementation;
import com.liceu.notes.models.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    UserDAO userDAO = new UserDAOImplementation();

    public boolean isPasswordValid(String password){
        Pattern passPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
        Matcher passMatcher = passPattern.matcher(password);
        return passMatcher.find();
    }

    public boolean isEmailValid(String email){
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher emailMatcher = emailPattern.matcher(email);
        UserDAO ud = new UserDAOImplementation();

        return emailMatcher.find() && ud.isEmailNotUsed(email);
    }

    public boolean isUsernameValid(String username){
        Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9._-]{3,}$");
        Matcher usernameMatcher = usernamePattern.matcher(username);
        return usernameMatcher.find();
    }

    public String encryptPassword(String password) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(password.getBytes());
        byte[] digest = md.digest();

        StringBuilder hexString = new StringBuilder();
        for (byte b : digest) {
            hexString.append(Integer.toHexString(0xFF & b));
        }
        return hexString.toString();
    }


    public void add(User user){
        userDAO.add(user);
    }

    public User getFromLogin(String email, String password){
       return userDAO.getFromLogin(email,password);
    }

    public List<User> getAll() throws SQLException {
       return userDAO.getAll();
    }

    public void update(User user) throws SQLException {
    userDAO.update(user);
    }

    public void delete(User user){

    }

    public User getUserFromId(int id){
        return userDAO.getUserFromId(id);
    }
}
