package com.liceu.notes.services;
import com.liceu.notes.dao.UserDAO;
import com.liceu.notes.dao.UserDAOImplementation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidData {
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

}
