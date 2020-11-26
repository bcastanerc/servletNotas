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

@WebServlet(value = "/viewNote")
public class viewNote extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null){
            NoteService noteService = new NoteService();
            UserService userService = new UserService();

            HttpSession session = req.getSession();
            int userID = (int) session.getAttribute("user_id");

            Note actualNote = noteService.searchById(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("title", actualNote.getTitle());
            req.setAttribute("text", actualNote.getText());
            req.setAttribute("id", actualNote.getId());
            req.setAttribute("owner", userService.userOwnsNote(userID, actualNote.getId()));

            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/viewNote.jsp");
            dispatcher.forward(req, resp);
            return;
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/userNotes.jsp");
        dispatcher.forward(req, resp);
    }
}
