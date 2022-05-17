/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lib.main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.profile.Asociado;
import modelo.profile.AsociadoCrud;

/**
 *
 * @author 2022
 */
public class ProtectedGetTemplate extends GetTemplate {
    
    protected Asociado asociado;
    protected AsociadoCrud crud = new AsociadoCrud();
    protected String token;
    
    public ProtectedGetTemplate(String path) {
        super(path);
    }
    
    protected boolean combrobe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        token = getCookie("token", request.getCookies());
        
        if (token.equals("")) return fail(response);
        
        try{
            asociado = crud.getByToken(token);
            if (asociado == null) return fail(response);
            
            return true;
        }catch(Exception e){ printError(e); return false; }
    }
    
    private boolean fail(HttpServletResponse response) throws IOException {
        response.sendRedirect(getURI("login"));
        return false;
    }
}
