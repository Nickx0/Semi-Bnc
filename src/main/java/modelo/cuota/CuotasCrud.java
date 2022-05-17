package modelo.cuota;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CuotasCrud extends modelo.Connection {
    private Cuota construct(ResultSet rs) throws SQLException {
        return new Cuota(
                rs.getInt("codigo"),
                rs.getInt("ncuota"),
                rs.getInt("estado"),
                rs.getDouble("capital"),
                rs.getDouble("interes"),
                rs.getDouble("neto"),
                rs.getString("plazo")
        );
    }
    
    public Cuota[] getAll(int codigo) throws SQLException {
        PreparedStatement ps = con.prepareStatement("Select * from cuotas where codigo=? ORDER BY estado asc");
        ps.setInt(1, codigo);
        ResultSet rs = ps.executeQuery();
        
        ArrayList<Cuota> result = new ArrayList<>();
        while(rs.next()) result.add(construct(rs));
        
        return result.toArray(new Cuota[0]);
    }
    
    public Cuota getLast(String token, int codigo) throws SQLException {
        PreparedStatement ps = con.prepareStatement("Select a.Nombre,a.Apellido, a.saldo,c.* from asociado a inner join solicitud s on s.solicitante=a.dni inner join cuotas c on c.codigo=s.cod where a.token=? and s.cod=? and c.estado!=2 order by c.estado asc LIMIT 1;");
        ps.setString(1, token);
        ps.setInt(2, codigo);
        
        ResultSet rs = ps.executeQuery();
        if(!rs.next()) return null;
        return construct(rs);
    }
    
    public boolean pagarCuota(String token, int codigo) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT ncuota FROM cuotas WHERE codigo=? AND estado!=2 ORDER by estado ASC LIMIT 1;");
        ps.setInt(1, codigo);
        
        ResultSet rs = ps.executeQuery();
        rs.next();
        int ncuota = rs.getInt("ncuota");
        
        PreparedStatement psUpdate = con.prepareStatement(
                                            "UPDATE asociado INNER JOIN solicitud s ON s.solicitante=asociado.dni INNER JOIN cuotas c ON s.cod=c.codigo SET asociado.saldo=asociado.saldo-c.neto, c.estado=2 " +
                                            "WHERE asociado.token=? AND c.codigo=? AND c.ncuota=? AND c.estado!=2 AND (asociado.saldo-c.neto)>-1;");
        
        psUpdate.setInt(2, codigo);
        psUpdate.setString(1, token);
        psUpdate.setInt(3, ncuota);
        int rows = psUpdate.executeUpdate();
        return rows >= 1;
    }
    
    public boolean todoPagado(int codigo) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT (AVG(estado)) as estado FROM cuotas WHERE codigo=?;");
        ps.setInt(1, codigo);
        ResultSet rs = ps.executeQuery();
        
        if(!rs.next()) return false;
        
        return rs.getInt("estado") == 2;
    }
}
