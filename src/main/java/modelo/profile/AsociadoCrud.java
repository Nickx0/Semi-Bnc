package modelo.profile;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AsociadoCrud extends modelo.Connection {
    
    private Asociado constructAsociado(ResultSet rs) throws SQLException {
        return new Asociado(
                rs.getInt("dni"),
                rs.getString("token"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("telefono"),
                rs.getString("contra"),
                rs.getDouble("salario"),
                rs.getDouble("saldo"),
                rs.getDouble("aporte")
        );
    }
    
    public boolean validateByToken(Asociado a) throws SQLException {
        return validateByToken(a.token);
    }
    
    public boolean validateByToken(String token) throws SQLException {
        String sql = "SELECT token from asociado WHERE token = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, token);
        ResultSet rs = st.executeQuery();
        
        return rs.next();
    }
    
    public Asociado getByToken(String token) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM asociado WHERE token=?");
        ps.setString(1, token);
        ResultSet rs = ps.executeQuery();
        if(!rs.next()) return null;
        return constructAsociado(rs);
    }
    
    public Asociado getAsociado(String username, String password) throws SQLException {
        String sql = "Select * from asociado where dni='"+username+"' and contra='"+password+"'";
        
        PreparedStatement st = con.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        
        if(!rs.next()) return null;
        return constructAsociado(rs);
    }
}
