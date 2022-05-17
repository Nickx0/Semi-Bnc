/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lib.main.ProtectedGetTemplate;
import modelo.cuota.CuotasCrud;

import modelo.solicitud.Solicitud;
import modelo.solicitud.SolicitudCrud;

/**
 *
 * @author 2022
 */
@WebServlet(name = "Cuotas", urlPatterns = {"/cuotas"})
public class Cuotas extends ProtectedGetTemplate {

    public Cuotas() {
        super("assets/cuotas.jsp");
    }

    private CuotasCrud cuotasCrud = new CuotasCrud();
    private SolicitudCrud solicitudCrud = new SolicitudCrud();
    
    private Integer getSolicCod(HttpServletRequest request) throws SQLException {
        try {
            int cod = Integer.parseInt(request.getParameter("codigo"));
            Solicitud soli = solicitudCrud.getOne(cod);
            if (soli == null) return null;
            
            return soli.cod;
        } catch(NumberFormatException e) { return null; } 
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean res = combrobe(request, response);
        if (!res) return;
        
        try {
            Integer cod = getSolicCod(request);
            if (cod == null) {
                response.sendRedirect(getURI("profile"));
            }
            
            request.setAttribute("cuotas", cuotasCrud.getAll(cod));
        } catch (Exception ex) { printError(ex); }
        
        super.doGet(request, response);
    }
    
    
    
}
