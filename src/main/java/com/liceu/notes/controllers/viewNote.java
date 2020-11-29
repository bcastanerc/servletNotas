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

        NoteService noteService = new NoteService();
        UserService userService = new UserService();

        HttpSession session = req.getSession();
        // If id is not null, to prevent null pointer if the user force /viewNote whith no params.
        if (req.getParameter("id") != null){
            int userID = (int) session.getAttribute("user_id");
            int noteID = Integer.parseInt(req.getParameter("id"));

            // The user can see the note if is the owner or is shared to him, can't force by url.
            if (req.getParameter("id") != null && (userService.userOwnsNote(userID,noteID) || noteService.isNoteSharedToUser(userID,noteID))){
                Note actualNote = noteService.searchById(noteID);
                req.setAttribute("note", actualNote);
                req.setAttribute("ownerEmail", userService.getUserFromId(actualNote.getUser_id()).getEmail());
                req.setAttribute("owner", userService.userOwnsNote(userID, actualNote.getId()));

                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/viewNote.jsp");
                dispatcher.forward(req, resp);
                return;
            }
        }
        resp.sendRedirect(req.getContextPath()+"/userNotes");
    }
}
