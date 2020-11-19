package com.liceu.notes.controllers;


import com.liceu.notes.models.Note;
import com.liceu.notes.services.NoteService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(value = "/viewNote")
public class viewNote extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null){
            NoteService noteService = new NoteService();

            Note actualNote = noteService.searchById(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("title", actualNote.getTitle());
            req.setAttribute("text", actualNote.getText());
            req.setAttribute("id", actualNote.getId());
            System.out.println("ID= " + actualNote.getId());

            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/viewNote.jsp");
            dispatcher.forward(req, resp);
            return;
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/userNotes.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        NoteService noteService = new NoteService();

        try {
            noteService.update(
                    new Note(Integer.parseInt(
                            req.getParameter("id")),
                            req.getParameter("title"),
                            req.getParameter("text"),
                            null,null,
                            (Integer) session.getAttribute("user_id"))
            );
           resp.sendRedirect(req.getContextPath()+"/userNotes");
            return;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("error en POST viewNotes");
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/createNote.jsp");
        dispatcher.forward(req, resp);
    }
}
