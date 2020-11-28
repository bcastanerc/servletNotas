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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet(value = "/userNotes")
public class userNotes extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        NoteService noteService = new NoteService();
        HttpSession session = req.getSession();
        System.out.println(req.getParameter("notes"));
        if (req.getParameter("notes") == null) req.setAttribute("notes", noteService.cutNotes(noteService.getAllFromId((Integer) session.getAttribute("user_id"))));

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/userNotes.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HttpSession session = req.getSession();
        NoteService noteService = new NoteService();



        if(req.getParameter("inputType") != null && req.getParameter("searchInput") != null) {

            int type = Integer.parseInt(req.getParameter("inputType"));
            String input = req.getParameter("searchInput");
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
                    // Busqueda expresion regular
                    Pattern pattern = Pattern.compile(input);
                    for (Note n : notes) {
                        Matcher noteMatcher = pattern.matcher(n.getText());
                        System.out.println("noteMatcher: " + noteMatcher + " pattern: " + pattern);
                        if (noteMatcher.matches()) filteredNotes.add(n);
                    }
                    break;
                case 4:
                    // Busqueda antes de fecha de creacion
                    try {
                        for (Note n : notes) {
                            SimpleDateFormat formatter5 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date1 = formatter5.parse(input);
                            if (n.getCreation_date().after(date1)){
                                filteredNotes.add(n);
                            }
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    // Busqueda antes de fecha de modificacion
                    try {
                        for (Note n : notes) {
                            SimpleDateFormat formatter5 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date1 = formatter5.parse(input);
                            System.out.println("Date" + date1.toString());
                            if (n.getLast_modification().after(date1)){
                                filteredNotes.add(n);
                            }
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;

            }
            req.setAttribute("csrfToken", req.getParameter("_csrftoken"));
            req.setAttribute("notes", filteredNotes);
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
