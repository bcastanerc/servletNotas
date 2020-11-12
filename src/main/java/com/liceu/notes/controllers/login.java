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

@WebServlet(value = "/login")
public class login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email =req.getParameter("email");
        String password =req.getParameter("password");

        ValidData validData = new ValidData();

        try {
          String encryptedPassword = validData.encryptPassword(password);
          UserDAO us = new UserDAOImplementation();
          User logedUSer = us.getFromLogin(email, encryptedPassword);
          if (logedUSer !=null){
              resp.sendRedirect(req.getContextPath() + "/createNotes");
          }else{
              req.setAttribute("error", true);
              RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/login.jsp");
              dispatcher.forward(req, resp);
          }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
}
