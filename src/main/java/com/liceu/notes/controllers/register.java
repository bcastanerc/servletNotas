package com.liceu.notes.controllers;

import com.liceu.notes.dao.UserDAO;
import com.liceu.notes.dao.UserDAOImplementation;
import com.liceu.notes.models.User;
import com.liceu.notes.services.ValidData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;


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

        ValidData validData = new ValidData();
        PrintWriter pw = resp.getWriter();

        if (password.equals(confirmPassword) && validData.isPasswordValid(password)
                && validData.isEmailValid(email) &&  validData.isUsernameValid(username)){
            try {
                String encryptedPassword = validData.encryptPassword(password);
                UserDAO ud = new UserDAOImplementation();
                ud.add(new User(0, email, username, encryptedPassword));

                resp.sendRedirect(req.getContextPath() + "/login");

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }else{
            req.setAttribute("error", true);
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/register.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
