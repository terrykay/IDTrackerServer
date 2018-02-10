/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import UtilityClasses.Hash;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.UserFacade;
import userIdEntity.Session;
import userIdEntity.User;

/**
 *
 * @author tezk
 */
public class WebServlet extends HttpServlet {

    @EJB
    UserFacade userFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //System.out.println("WebServlet : " + username + ", " + password);
        User aSession = (User) request.getSession().getAttribute("session");
        String url = "WEB-INF/";

        if (username != null && password != null) {
            // Logging in
            System.out.println("processing login");
            User aUser = userFacade.findByUsername(username);
            System.out.println("user = " + aUser);
            if (aUser != null) {
                password = Hash.sha256(password);
                if (aUser.getPassword().equals(password)) {
                    request.getSession().setAttribute("session", aUser);
                    url += "main.jsp";
                } else {
                    url += "login.jsp";
                }
            } else {
                url += "login.jsp";
            }
        } else if (aSession == null) {
            // User not logged in
            System.out.println("No session");
            url += "login.jsp";
        } else {
            // User has valid session
            System.out.println("valid user");
            if (request.getParameter("logout") != null) {
                System.err.println("logout");
                request.removeAttribute("session");
                url += "login.jsp";
                // Links -----------------------------------------------------
            } else if (request.getParameter("changemypassword") != null) {
                //Change users password
                url += "changepassword.jsp";
            } else if (request.getParameter("changeuserspassword") != null) {
                // Change another's password
                if (!aSession.getPermissions().contains("admin")) {
                    request.setAttribute("message", "You are not authorised to access that");
                    url += "main.jsp";
                } else {
                    request.setAttribute("changeforother", "1");
                    url += "changepassword.jsp";
                }
            } else if (request.getParameter("adduser") != null) {
                // Add user
                if (!aSession.getPermissions().contains("admin")) {
                    request.setAttribute("message", "You are not authorised to access that");
                    url += "main.jsp";
                } else {
                    url += "adduser.jsp";
                }
            } else if (request.getParameter("dochangepassword") != null) {
                // Do something -------------------------------------------------------
                if (request.getParameter("usersname") != null) {
                    // Change other user
                    if (!aSession.getPermissions().contains("admin")) {
                        request.setAttribute("message", "You are not authorised to access that");
                        url += "main.jsp";
                    } else {
                        String newPassword = request.getParameter("newpassword");
                        if (newPassword == null || !newPassword.equals(request.getParameter("newpassword2"))) {
                            request.setAttribute("message", "Passwords were different");
                        } else {
                            newPassword = Hash.sha256(newPassword);
                            String usersname = request.getParameter("usersname");
                            User aUser = userFacade.findByUsername(usersname);
                            if (aUser != null) {
                                aUser.setPassword(newPassword);
                                userFacade.edit(aUser);
                                request.setAttribute("message", "Users password changed");
                            } else {
                                request.setAttribute("message", "invalid user");
                            }
                        }
                    }
                } else if (request.getParameter("newusername") != null) {
                    // Add new user
                    if (!aSession.getPermissions().contains("admin")) {
                        request.setAttribute("message", "You are not authorised to access that");
                    } else {
                        User aUser = new User();
                        aUser.setCreated(new Date());
                        aUser.setUsername(request.getParameter("newusername"));
                        aUser.setPassword(Hash.sha256(request.getParameter("newpassword")));
                        try {
                            userFacade.create(aUser);
                            request.setAttribute("message", "User added");
                        } catch (Exception e) {
                            request.setAttribute("message", "Problem creating new user");
                        }
                    }
                } else {
                    // Change our password
                    String newPassword = request.getParameter("newpassword");
                    if (newPassword != null) {
                        if (!newPassword.equals(request.getParameter("newpassword2"))) {
                            request.setAttribute("message", "passwords were different");
                        } else {
                            User aUser = userFacade.findByUserId(aSession.getIdusers());
                            aUser.setPassword(Hash.sha256(newPassword));
                            userFacade.edit(aUser);
                            request.setAttribute("message", "password changed");
                        }
                    } else {
                        request.setAttribute("message", "problem changing password");
                    }
                }
                url += "main.jsp";
            }
        }
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
