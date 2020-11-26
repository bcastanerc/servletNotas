package com.liceu.notes.controllers;

import com.liceu.notes.models.User;
import com.liceu.notes.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


@WebServlet(value = "/userInfo")
public class userInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserService userService = new UserService();
        HttpSession session = req.getSession();
        User actualUser = userService.getUserFromId((int) session.getAttribute("user_id"));

        if(req.getParameter("deleteAccount") != null){
            userService.delete(actualUser.getId());
            session.invalidate();
            resp.sendRedirect(req.getContextPath()+"/login");
            return;
        }

        req.setAttribute("username", actualUser.getUsername());
        req.setAttribute("email", actualUser.getEmail());

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/userInfo.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email =req.getParameter("email");
        String username =req.getParameter("username");
        String password =req.getParameter("password");
        String confirmPassword =req.getParameter("confirmPassword");

        UserService userService = new UserService();

        HttpSession session = req.getSession();
        User actualUser = userService.getUserFromId((int) session.getAttribute("user_id"));

        if (password.equals(confirmPassword) && userService.isPasswordValid(password)
                && (userService.isEmailValid(email) || email.equals(actualUser.getEmail()))
                && userService.isUsernameValid(username)){
            try {
                userService.update(new User(actualUser.getId(),email,username,userService.encryptPassword(password)));
                session.invalidate();
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/login.jsp");
                dispatcher.forward(req, resp);
            } catch (NoSuchAlgorithmException | SQLException e) {
                e.printStackTrace();
            }
        }else req.setAttribute("error", true);
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/userInfo.jsp");
        dispatcher.forward(req, resp);
    }

}
