package com.liceu.notes.controllers;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(value = "/register")
public class register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/register.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email =req.getParameter("email");
        String username =req.getParameter("username");
        String password =req.getParameter("password");
        String confirmPassword =req.getParameter("confirmPassword");

        if (password.equals(confirmPassword) && isPasswordValid(password)
                && isEmailValid(email) &&  isUsernameValid(username)){

        }

        PrintWriter pw = resp.getWriter();
        pw.print("<html><body><p>"+ email +  username + password + confirmPassword + "</p></body></html>");

    }

    boolean isPasswordValid(String pass){
        Pattern passPattern = Pattern.compile("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$");
        Matcher passMatcher = passPattern.matcher(pass);
        return passMatcher.find();
    }

    boolean isEmailValid(String email){
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$");
        Matcher emailMatcher = emailPattern.matcher(email);
        return emailMatcher.find();
    }

    boolean isUsernameValid(String username){
        Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9._-]{3,}$");
        Matcher usernameMatcher = usernamePattern.matcher(username);
        return usernameMatcher.find();
    }

}
