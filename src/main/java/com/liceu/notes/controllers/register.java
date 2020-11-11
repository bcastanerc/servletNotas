package com.liceu.notes.controllers;

import com.liceu.notes.services.ValidData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;



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

        }

        pw.print("<html><body><p>"
                + "Email is valid? " + email + " " + validData.isEmailValid(email) +
                " | User is valid? " + username + " " + validData.isUsernameValid(username) +
                " | Password is valid? " + password + " " + validData.isPasswordValid(password) +
                " | Password is confirmed" + confirmPassword + " " + password.equals(confirmPassword) +
                "</p></body></html>");


    }
}
