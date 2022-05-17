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
import modelo.profile.Asociado;
import modelo.profile.AsociadoCrud;

/**
 *
 * @author 2022
 */
@WebServlet(name = "NewServlet", urlPatterns = {"/NewServlet"})
public class Login extends lib.main.GetTemplate {

    public Login() {
        super("assets/login.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = getCookie("token", request.getCookies());
        
        try{
            if (token.equals("")) { super.doGet(request, response); return; }
            
            AsociadoCrud crud = new AsociadoCrud();
            if(!crud.validateByToken(token)) {
                response.sendRedirect(getURI("profile"));
            }
            response.addCookie(new Cookie("token", ""));
            super.doGet(request, response);
        }catch(Exception e){ printError(e); }
    }
    
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        try{
            AsociadoCrud crud = new AsociadoCrud();
            Asociado asociado = crud.getAsociado(username, password);
            
            if (asociado == null) {
                request.setAttribute("failed", true);
                super.doGet(request, response);
            }
            
            response.addCookie(new Cookie("token", asociado.token));
            response.sendRedirect(getURI("profile"));
        }catch(Exception e){
            System.out.println("ERROR: "+(e.getMessage() + e.getClass().getName() ));
        }
    }
}
