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


@WebServlet(value = "/createNotes")
public class createNotes extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("logout") != null){
            HttpSession session = req.getSession();
            session.invalidate();
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/login.jsp");
            dispatcher.forward(req, resp);
            return;
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/createNotes.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String text = req.getParameter("text");
        try{
            HttpSession session = req.getSession();

            NoteService noteService = new NoteService();
            noteService.add(new Note(0,title,text,null,null, (Integer) session.getAttribute("user_id")));
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/createNotes.jsp");
            dispatcher.forward(req, resp);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("error en createNotes");
        }

    }
}
