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
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


@WebServlet(value = "/updateNote")
public class updateNote extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        NoteService noteService = new NoteService();

        if(req.getParameter("deleteNote") != null){
            noteService.delete(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect(req.getContextPath()+"/userNotes");
            return;
        }

        if (req.getParameter("id") != null){

            Note actualNote = noteService.searchById(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("title", actualNote.getTitle());
            req.setAttribute("text", actualNote.getText());
            req.setAttribute("id", actualNote.getId());

            List<String> emails = noteService.getAllSharedUsersFromIdNote(actualNote.getId());
            System.out.println(Arrays.toString(emails.toArray()));

            req.setAttribute("users", emails.toArray());

            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/updateNote.jsp");
            dispatcher.forward(req, resp);
            return;
        }
        resp.sendRedirect(req.getContextPath()+"/userNotes");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        NoteService noteService = new NoteService();
        UserService userService = new UserService();

        String emailToShare = req.getParameter("emailToShare");
        int id_note = Integer.parseInt(req.getParameter("id"));

        if (emailToShare != null && userService.getUserFromEmail(emailToShare) != null){
            User userToShare = userService.getUserFromEmail(emailToShare);

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

        if (userService.userOwnsNote((int) session.getAttribute("user_id"),id_note) && emailToShare == null){
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
        req.setAttribute("error", true);
        req.setAttribute("csrfToken", req.getParameter("_csrftoken"));
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/updateNote.jsp");
        dispatcher.forward(req, resp);
    }
}
