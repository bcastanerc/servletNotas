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
import java.util.ArrayList;
import java.util.List;


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

        int type = Integer.parseInt(req.getParameter("inputType"));
        String input = req.getParameter("searchInput");
        System.out.println(type);
        HttpSession session = req.getSession();
        NoteService noteService = new NoteService();

        List<Note> notes = noteService.cutNotes(noteService.getAllFromId((Integer) session.getAttribute("user_id")));
        List<Note> filteredNotes = new ArrayList<>();

        switch (type){
            case 1:
                // Busqueda titulo
                for (Note n : notes) if (n.getTitle().contains(input)) filteredNotes.add(n);
                break;
            case 2:
                // Busqueda texto
                for (Note n : notes) if (n.getText().contains(input)) filteredNotes.add(n);
                break;
            case 3:
                // Busqueda expresion
                break;
            case 4:
                // Busqueda titulo
                break;
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/userNotes.jsp");
        dispatcher.forward(req, resp);
    }
}
