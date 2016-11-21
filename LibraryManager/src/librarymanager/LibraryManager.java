package librarymanager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LibraryManager {
    
    private ResultSet respondSQL;
    private controlConnection clconnection;
    
    public LibraryManager(controlConnection connection) throws SQLException {
            clconnection=connection;
    }
    
    public void Modify(BookOdt book) throws SQLException {
            String sql = "update libros set titulo='" + book.getTitle()
                                    + "', autor='" + book.getAuthor()
                                    + "', editorial='" + book.getPublisher()
                                    + "', asignatura='" + book.getSchoolSubjects()
                                    + "', estado='" + book.getState() + "' "
            +"where codigo=" + book.getCode();
            if(clconnection.Ejecutar_Sql_Actualizacion(sql) > 0) {
                JOptionPane.showMessageDialog(null, "Modificación Correcta");
            }else{
                JOptionPane.showMessageDialog(null, "Ha Habido un Error");
            }
    }
    
    public void newUser(BookOdt book) throws SQLException{
        String sql="INSERT INTO libros(titulo, autor, editorial, asignatura, estado) VALUES ('"
                                               +book.getTitle()
                                           +"','"+book.getAuthor()
                                           +"','"+book.getPublisher()
                                           +"','"+book.getSchoolSubjects()
                                           +"','"+book.getState()+"');";
        
        if(clconnection.Ejecutar_Sql_Actualizacion(sql) > 0) {
            JOptionPane.showMessageDialog(null, "Alta Correcta");
        }else{
            JOptionPane.showMessageDialog(null, "Ha Habido un Error");
        }
    }
  
    public void deleteUser(BookOdt book) throws SQLException{
        String sql="delete from libros where codigo=" + book.getCode();
     
     if (clconnection.Ejecutar_Sql_Actualizacion(sql) > 0) {
            JOptionPane.showMessageDialog(null, "Baja Correcta");
        } else {
            JOptionPane.showMessageDialog(null, "Ha Habido un Error");
        }
    }
    
    public BookOdt showBook(String code) throws SQLException{
        
        BookOdt book=new BookOdt();
        
        String sql="select * from libros where codigo="+code;
   
        clconnection.ejecutar_consulta(sql);
        
        respondSQL=clconnection.getVarRespuesta();
        
        //ResultSetMetaData rsmd = respondSQL.getMetaData();
        //int columns=rsmd.getColumnCount();
        respondSQL.next();
        book.setCode(code);
        book.setTitle(respondSQL.getString("titulo"));
        book.setAuthor(respondSQL.getString("autor"));
        book.setPublisher(respondSQL.getString("editorial"));
        book.setSchoolSubjects(respondSQL.getString("asignatura"));
        book.setState(respondSQL.getString("estado"));
        
        return book;
    }
    
    public void tableContent(String consul){
        try {
            clconnection.ejecutar_consulta(consul);
            respondSQL=clconnection.getVarRespuesta();
        } catch (SQLException ex) {
            Logger.getLogger(LibraryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tableContent2(String consul){
        try {
            if(isNumeric(consul)){
                clconnection.ejecutar_consulta("SELECT * from libros where codigo="+consul);
            respondSQL=clconnection.getVarRespuesta();
            }
            else{
            clconnection.ejecutar_consulta("SELECT * from libros where titulo like '"+consul
                                                                +"%' or autor like'"+consul
                                                                +"%' or editorial like'"+consul
                                                                +"%' or asignatura like'"+consul
                                                                +"%' or estado like'"+consul+"%'");
            respondSQL=clconnection.getVarRespuesta();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibraryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet getRespondSQL() {
        return respondSQL;
    }
    
    private boolean isNumeric(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        }catch (NumberFormatException nfe){
            return false;
        }
    }
    
    
}
