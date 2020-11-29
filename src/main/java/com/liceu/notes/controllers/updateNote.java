package com.liceu.notes.controllers;

import com.liceu.notes.models.Note;
import com.liceu.notes.models.User;
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


@WebServlet(value = "/updateNote")
public class updateNote extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        NoteService noteService = new NoteService();
        UserService userService = new UserService();
        HttpSession session = req.getSession();

        // If id is not null, to prevent null pointer if the user force /updateNote whith no params.
        if (req.getParameter("id") != null){
            // If the user is the owner of the note.
            if (userService.userOwnsNote((Integer) session.getAttribute("user_id"),Integer.parseInt(req.getParameter("id")))) {
                // Delete the note.
                if(req.getParameter("deleteNote") != null){
                    noteService.delete(Integer.parseInt(req.getParameter("id")));
                    resp.sendRedirect(req.getContextPath()+"/userNotes");
                    return;
                }
                // Show the note to edit.
                Note actualNote = noteService.searchById(Integer.parseInt(req.getParameter("id")));
                req.setAttribute("note", actualNote);


                List<User> usersShared = userService.getAllSharedUsersFromIdNote(actualNote.getId());
                req.setAttribute("usersShared", usersShared.toArray());

                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/updateNote.jsp");
                dispatcher.forward(req, resp);
                return;
            }
        }
        resp.sendRedirect(req.getContextPath()+"/userNotes");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        NoteService noteService = new NoteService();
        UserService userService = new UserService();

        String emailToShare = req.getParameter("emailToShare");
        int id_note = Integer.parseInt(req.getParameter("id"));

        // Checks that the user owns the note.
        if (userService.userOwnsNote((int) session.getAttribute("user_id"),id_note)){
            // If there is an email to share and it exist.
            if (emailToShare != null && userService.getUserFromEmail(emailToShare) != null){
                User userToShare = userService.getUserFromEmail(emailToShare);

                // Prevent html modification at values with developer console.
                if (req.getParameter("actionType") != null){
                    if(req.getParameter("actionType").equals("share")){
                        noteService.shareNoteToUserById(userToShare.getId(), id_note);
                    }
                    if (req.getParameter("actionType").equals("delete")){
                        noteService.deleteSharedNote(userToShare.getId(), id_note);
                    }
                }
                resp.sendRedirect(req.getContextPath()+"/userNotes");
                return;
            }

            // Update the note if the user is the owner of it and there is no input at the share email, title and text is not empty.
            if (emailToShare == null && !req.getParameter("title").equals("") &&
                    !req.getParameter("text").equals("") &&  req.getParameter("id") != null){
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
            }
        }
        resp.sendRedirect(req.getContextPath()+"/userNotes");
    }
}
