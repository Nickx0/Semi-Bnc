/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lib.main.ProtectedGetTemplate;
import modelo.solicitud.Solicitud;
import modelo.solicitud.SolicitudCrud;

/**
 *
 * @author 2022
 */
public class Prestamo extends ProtectedGetTemplate {
    
    private SolicitudCrud solicitudCrud = new SolicitudCrud();
    
    public Prestamo() { super("assets/prestamo.jsp"); }

    private Solicitud comprobeParameters(HttpServletRequest request) {
        try {
            int cuotas = Integer.parseInt(request.getParameter("cuotas"));
            double monto = Double.parseDouble(request.getParameter("monto"));
            String obs = request.getParameter("observaciones"); 
            
            return new Solicitud(-1, asociado.dni, cuotas, -1, monto, -1, obs, "");
        } catch(NumberFormatException e) { return null; }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!combrobe(request, response)) return;
        
        Solicitud solicitud = comprobeParameters(request);
        if (solicitud == null) {
            response.sendRedirect(getURI("profile?state=fail"));
            return;
        }
        
        try {
            boolean res = solicitudCrud.create(solicitud);
            if (!res) response.sendRedirect(getURI("profile?state=fail"));
            else response.sendRedirect(getURI("profile?state=created"));
        } catch (SQLException ex) { printError(ex); }
    }   
}
