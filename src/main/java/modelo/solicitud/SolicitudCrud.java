package modelo.solicitud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

public class SolicitudCrud extends modelo.Connection {
    private Solicitud constructSolicitud(ResultSet rs) throws SQLException {
        return new Solicitud(
                rs.getInt("cod"),
                rs.getInt("solicitante"),
                rs.getInt("cuotas"),
                rs.getInt("estado"),
                rs.getDouble("monto"),
                rs.getDouble("taza"),
                rs.getString("observaciones"),
                rs.getString("fecha")
        );
    }
    
    public Solicitud[] getAll(int dni) throws SQLException {
        String sql = "SELECT * FROM solicitud WHERE solicitante=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, dni);
        ResultSet rs = ps.executeQuery();
        
        ArrayList<Solicitud> result = new ArrayList<>();
        while(rs.next()) result.add(constructSolicitud(rs));
        
        return result.toArray(new Solicitud[0]);
    }
    
    public Solicitud getOne(int cod) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM solicitud WHERE cod=?");
        ps.setInt(1, cod);
        ResultSet rs = ps.executeQuery();
            
        if(!rs.next()) return null;
        return constructSolicitud(rs);
    }
    
    private Integer getLastSoli() throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT cod FROM solicitud ORDER BY cod desc LIMIT 1");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) return rs.getInt("cod");
        return null;
    }
    
    private boolean canCreate(int dni) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT 1 FROM solicitud WHERE solicitante=? AND estado!=2 HAVING count(*)<2;");
        ps.setInt(1, dni);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }
    
    public boolean create(Solicitud solicitud) throws SQLException {
        if (solicitud.cuotas < 1) return false;
        if (!canCreate(solicitud.solicitante)) return false;
        
        double taza = 3.2;
        
        PreparedStatement ps;
        
        ps = con.prepareStatement("INSERT INTO solicitud(solicitante, cuotas, monto, taza, observaciones, estado, fecha) VALUE(?, ?, ?, ?, ?, 1, curdate());");
            
        ps.setInt(1, solicitud.solicitante);
        ps.setInt(2, solicitud.cuotas);
        ps.setDouble(3, solicitud.monto);
        ps.setDouble(4, taza);
        ps.setString(5, solicitud.observaciones);
        
        ps.execute();
        
        Integer cod = getLastSoli();
        if (cod == null) return false;
        
        String sql = "INSERT INTO cuotas (codigo,ncuota,capital,interes,neto,estado,plazo) VALUES";
        double capital = solicitud.monto/solicitud.cuotas;
        double interes = capital*taza/100;
        double neto = capital+interes;
        
        for(int i = 1; i <= solicitud.cuotas; i++)
            sql += String.format(Locale.ROOT,"(%d, %d, %.2f, %.2f, %.2f, 1, DATE_ADD(curdate(), INTERVAL %d MONTH)),", cod, i, capital, interes, neto, i);
        
        sql = sql.substring(0, sql.length() - 1) + ';';
        
        System.out.println(sql);
        
        ps = con.prepareStatement(sql);
        ps.execute();
        return true;
    }
}
