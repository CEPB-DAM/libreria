
package librarymanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class controlConnection {
    
    private Connection varConnection = null;
    private ResultSet varRespuesta = null;

    public controlConnection() {
        openConnection();
    }
    
    private void openConnection() {
        try {
            String sUrl = "jdbc:mysql://localhost:3306/biblioteca";
            varConnection = DriverManager.getConnection(sUrl, "root", "root");
            JOptionPane.showMessageDialog(null, "Conectado!!!!");
        }catch(SQLException ex) {
            varConnection = null;
        throw new RuntimeException("Error con la conexión!!!");
        }
    }
    
    public void ejecutar_consulta(String sql) throws SQLException {
            Statement stmt = varConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            varRespuesta = stmt.executeQuery(sql);
    }

    public int Ejecutar_Sql_Actualizacion(String sql) throws SQLException {
        int i=0;
            Statement stmt = varConnection.createStatement();
            i = stmt.executeUpdate(sql);
        return i;
    }

    public Connection getVarConnection() {
        return varConnection;
    }

    public ResultSet getVarRespuesta() {
        return varRespuesta;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
