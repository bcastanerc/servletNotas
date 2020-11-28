package com.liceu.notes.controllers;
import com.liceu.notes.models.User;
import com.liceu.notes.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserService userService = new UserService();

        try {
          String encryptedPassword = userService.encryptPassword(password);
          User logedUSer = userService.getFromLogin(email, encryptedPassword);
          if (logedUSer != null){
              HttpSession session = req.getSession();
              session.setAttribute("user_id", logedUSer.getId());
              resp.sendRedirect(req.getContextPath() + "/createNotes");
          }else{
              req.setAttribute("error", true);
              RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/login.jsp");
              dispatcher.forward(req, resp);
          }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("Error login");
        }

    }
}
