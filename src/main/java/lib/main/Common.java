/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lib.main;

import javax.servlet.http.Cookie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 *
 * @author 2022
 */
public class Common extends javax.servlet.http.HttpServlet {
    static protected final String URI = "/Entregable";
    
    static protected String getURI(String path, String[][] args) {
        path = getURI(path) + "?";
        
        for(String[] arg: args)
            path += arg[0] + "=" + arg[1] + "&";
        
        return path.substring(0, path.length()-1);
    }
    
    static protected String getURI(String path) {
        if (path.charAt(0) != '/') path = '/' + path;
        
        return URI+path;
    }
    
    static protected String getCookie(String name, Cookie[] cookies) {
        if (cookies == null) return "";
        
        String value = "";
        for(Cookie c: cookies) 
            if (c != null & c.getName().equals(name)) value = c.getValue();
        
        
        return value;
    }
    
    static public void printError(Exception e) {
        System.out.println("---------------------------------------------------------------");
        System.err.println("MY ERROR:: "+e.getClass().toString() + ": " + e.getMessage());
        e.printStackTrace();
        System.out.println("---------------------------------------------------------------");
    }
    
    public Connection con;
    
    public Common() {
        try {
          Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
          con = DriverManager.getConnection("jdbc:mysql://localhost:3307/javadb?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false","root","");
        } catch (Exception e) { printError(e); }
    }
    
    private void clean() {
        try {
            con.close();
        } catch (Exception e) { printError(e); }
    }
}
