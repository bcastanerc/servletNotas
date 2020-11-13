package com.liceu.notes.controllers;
import com.liceu.notes.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/listUsers")
public class listUsers extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Hay que poner  UserDAO en capa de servicio
        UserService userService = new UserService();

        try {
            req.setAttribute("users", userService.getAll());
        } catch (Exception e) {
            e.printStackTrace();
            PrintWriter pw = resp.getWriter();
            pw.print(e);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/listUsers.jsp");
        dispatcher.forward(req, resp);
    }
}
