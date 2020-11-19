package com.liceu.notes.controllers;

import com.liceu.notes.services.NoteService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(value = "/userNotes")
public class userNotes extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        NoteService noteService = new NoteService();
        HttpSession session = req.getSession();
        req.setAttribute("notes", noteService.cutNotes(noteService.getAllFromId((Integer) session.getAttribute("user_id"))));
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/userNotes.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/userNotes.jsp");
        dispatcher.forward(req, resp);
    }
}
