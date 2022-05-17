package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.solicitud.SolicitudCrud;


/**
 *
 * @author 2022
 */
@WebServlet(name = "Profile", urlPatterns = {"/profile"})
public class Profile extends lib.main.ProtectedGetTemplate {

    public Profile() {
        super("assets/profile.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!combrobe(request, response)) return;
        
        try {
            
            request.setAttribute("user", asociado.nombre + " " + asociado.apellido);
            request.setAttribute("solicitudes", new SolicitudCrud().getAll(asociado.dni));
            
        } catch (Exception ex) { printError(ex); }
        
        super.doGet(request, response);
    }
    
    
}
