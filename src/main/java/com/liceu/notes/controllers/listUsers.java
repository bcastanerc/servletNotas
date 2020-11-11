package com.liceu.notes.controllers;
import com.liceu.notes.dao.UserDAO;
import com.liceu.notes.dao.UserDAOImplementation;
import com.liceu.notes.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/listUsers")
public class listUsers extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Hay que poner  UserDAO en capa de servicio
        UserDAO ud = new UserDAOImplementation();

        try {
            List<User> users = ud.getAll();
            req.setAttribute("users", users);
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/listUsers.jsp");
        dispatcher.forward(req, resp);
    }
}
