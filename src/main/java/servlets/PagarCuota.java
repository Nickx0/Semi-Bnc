/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lib.main.ProtectedGetTemplate;
import modelo.cuota.CuotasCrud;
import modelo.profile.AsociadoCrud;
import modelo.solicitud.Solicitud;
import modelo.solicitud.SolicitudCrud;

/**
 *
 * @author 2022
 */
@WebServlet(name = "PagarCuota", urlPatterns = {"/pagar-cuota"})
public class PagarCuota extends ProtectedGetTemplate {

    public PagarCuota() {
        super("assets/pagarCuota.jsp");
    }

    private SolicitudCrud solicitudCrud = new SolicitudCrud();
    private CuotasCrud cuotasCrud = new CuotasCrud();
    
    private Integer getNumCuota(HttpServletRequest request) throws SQLException{
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
            Integer cod = getNumCuota(request);
            if (cod == null) {
                response.sendRedirect(getURI("profile"));
                return;
            }
            
            Solicitud solicitud = new SolicitudCrud().getOne(cod);
            if (solicitud==null) { response.sendRedirect(getURI("profile")); return; }
            if (asociado.dni != solicitud.solicitante) { response.sendRedirect(getURI("profile")); return; }
            
            request.setAttribute("solicitud", solicitud);
            request.setAttribute("asociado", asociado);
            request.setAttribute("cuota", cuotasCrud.getLast(token, cod));
        } catch (Exception ex) { printError(ex); }
        
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean res = combrobe(request, response);
        if (!res) return;
        
        try {    
            Integer cod = getNumCuota(request);
            if (cod == null) { response.sendRedirect(getURI("profile")); return; }
                        
            boolean made = cuotasCrud.pagarCuota(token, cod);
            if(!made) { response.sendRedirect(getURI("profile")); return; }
            
            if (cuotasCrud.todoPagado(cod)) response.sendRedirect(getURI("profile"));
            else response.sendRedirect(getURI("cuotas?codigo="+cod.toString()));
        } catch(SQLException e) { printError(e); /*TODO make a error page*/  }
        catch(Exception e) { printError(e); }
    }
}
