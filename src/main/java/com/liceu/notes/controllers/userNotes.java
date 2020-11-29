package com.liceu.notes.controllers;

import com.liceu.notes.models.Note;
import com.liceu.notes.services.NoteService;
import com.liceu.notes.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/userNotes")
public class userNotes extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        NoteService noteService = new NoteService();
        HttpSession session = req.getSession();
        if (req.getParameter("notes") == null) req.setAttribute("notes", noteService.cutNotes(noteService.getAllFromId((Integer) session.getAttribute("user_id"))));
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/userNotes.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        NoteService noteService = new NoteService();

        if(req.getParameter("inputType") != null && req.getParameter("searchInput") != null && !req.getParameter("searchInput").equals("")) {

            int type = Integer.parseInt(req.getParameter("inputType"));
            String input = req.getParameter("searchInput");
            List<Note> notes = noteService.cutNotes(noteService.getAllFromId((Integer) session.getAttribute("user_id")));

            req.setAttribute("csrfToken", req.getParameter("_csrftoken"));
            req.setAttribute("notes", noteService.filterNotes(notes, input, type));
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/userNotes.jsp");
            dispatcher.forward(req, resp);
        }

        String[] ids = req.getParameterValues("notesToDelete[]");
        if (ids != null){
            UserService userService = new UserService();
            int user_id = (int) session.getAttribute("user_id");
            for (String id : ids) {
                if (userService.userOwnsNote(user_id, Integer.parseInt(id))) noteService.delete(Integer.parseInt(id));
                else noteService.deleteSharedNote(user_id, Integer.parseInt(id));
            }
        }
        resp.sendRedirect(req.getContextPath() + "/userNotes");
    }
}
