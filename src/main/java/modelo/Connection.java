package modelo;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    
    static protected java.sql.Connection con;
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/javadb?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false","root","");
        } catch (SQLException ex) { lib.main.Common.printError(ex); }
        catch (Exception e) { lib.main.Common.printError(e); }
    }
}
