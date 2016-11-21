
package librarymanager;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.AbstractTableModel;

public class TableView extends  AbstractTableModel{
   
    ResultSet varResoultSet;
    ResultSetMetaData varSetMetaData; //contiene información sobre la estructura de un ResulSet,especialmente sobre sus nom campos
    int numColumns;
    int numRows;
    
    public TableView(ResultSet rs){
        this.varResoultSet=rs;
        try{
            varSetMetaData=rs.getMetaData();
            varResoultSet.last();
            numRows=varResoultSet.getRow();
            numColumns=varSetMetaData.getColumnCount();  
        }catch( SQLException ex){
        
        }
    }
    
    @Override
    public int getRowCount(){
        return numRows;   
    }

    @Override
    public int getColumnCount(){
        return numColumns;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            varResoultSet.absolute(rowIndex+1);
            Object o=varResoultSet.getObject(columnIndex +1);
            return o;
        }catch (SQLException ex){
            return ex.toString();
            }   
    }  
}

