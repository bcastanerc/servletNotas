package com.liceu.notes.controllers;

import com.liceu.notes.models.User;
import com.liceu.notes.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

        UserService userService = new UserService();

        if (password.equals(confirmPassword) && userService.isPasswordValid(password)
                && userService.isEmailValid(email) &&  userService.isUsernameValid(username)){
            try {
                String encryptedPassword = userService.encryptPassword(password);
                userService.add(new User(0, email, username, encryptedPassword));

                resp.sendRedirect(req.getContextPath() + "/login");

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }else{
            req.setAttribute("error", true);
            req.setAttribute("csrfToken", req.getParameter("_csrftoken"));
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/register.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
