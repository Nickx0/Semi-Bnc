/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lib.main.ProtectedGetTemplate;

/**
 *
 * @author 2022
 */
@WebServlet(name = "Logout", urlPatterns = {"/logout"})
public class Logout extends ProtectedGetTemplate {

    public Logout() {
        super(null);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addCookie(new Cookie("token", ""));
        response.sendRedirect(getURI("login"));
    }
    
}
