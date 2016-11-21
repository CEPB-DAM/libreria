package librarymanager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class EventClass implements ActionListener, MouseListener, DocumentListener{
    
    private TableView tableView;
    private FormView form;
    private LibraryManager manager;
    private controlConnection connection;

    public EventClass(){
        try {
            
            connection=new controlConnection();      
            manager=new LibraryManager(connection);
            form = new FormView(this);
            actualizarTabla();
            form.setVisible(true);
            
        } catch (SQLException ex) {
            Logger.getLogger(EventClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizarTabla(){
        manager.tableContent("select * from libros");
        form.getBooksTable().setModel(tableView=new TableView(manager.getRespondSQL()));
    }
    
    public void actualizarTabla1(String consul) {
        manager.tableContent2(consul);
        form.getBooksTable().setModel(tableView=new TableView(manager.getRespondSQL()));
    }
    
    //<editor-fold defaultstate="collapsed" desc="ACTION PERFORMED">
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==form.getBtnNew())
        {
            try {
        
               BookOdt book= new BookOdt();
               book.setTitle(form.getTxtTitle().getText());
               book.setAuthor(form.getTxtAuthor().getText());
               book.setPublisher(form.getTxtPublisher().getText());
               book.setSchoolSubjects(form.getTxtSchoolSubjects().getText());
               book.setState(form.getTxtState().getText());
            
               manager.newUser(book);
               
               actualizarTabla();

               //actualizarTabla();
               //form.table1();
                              
            }catch (SQLException ex) {
                Logger.getLogger(FormView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(ae.getSource()==form.getBtnModify())
        {
            try {
                BookOdt book= new BookOdt();
                book.setCode(form.getTxtCode().getText());
                book.setTitle(form.getTxtTitle().getText());
                book.setAuthor(form.getTxtAuthor().getText());
                book.setPublisher(form.getTxtPublisher().getText());
                book.setSchoolSubjects(form.getTxtSchoolSubjects().getText());
                book.setState(form.getTxtState().getText());
            
                manager.Modify(book);
                
                actualizarTabla();
                
            } catch (SQLException ex) {
                Logger.getLogger(FormView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(ae.getSource()==form.getBtnDelete())
        {
            try {
            
                BookOdt book= new BookOdt();
                book.setCode(form.getTxtCode().getText());
            
                manager.deleteUser(book);
                
                actualizarTabla();
                
            }catch (SQLException ex) {
                Logger.getLogger(FormView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(ae.getSource()==form.getBtnExit())
        {
            System.exit(EXIT_ON_CLOSE);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="MOUSE LISTENER">
    @Override
    public void mouseClicked(MouseEvent me) {
        try {
        BookOdt book=new BookOdt();
        
        String code="";
        int row=0;
       
        row=form.getBooksTable().getSelectedRow();
        code=String.valueOf(form.getBooksTable().getValueAt(row, 0));
       
        book=manager.showBook(code);
        
        form.getTxtCode().setText(book.getCode());
        form.getTxtTitle().setText(book.getTitle());
        form.getTxtAuthor().setText(book.getAuthor());
        form.getTxtPublisher().setText(book.getPublisher());
        form.getTxtSchoolSubjects().setText(book.getSchoolSubjects());
        form.getTxtState().setText(book.getState());
        
        } catch (SQLException ex) {
            Logger.getLogger(EventClass.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="DOCUMENT LISTENER">
    @Override
    public void insertUpdate(DocumentEvent e) {
        
        
        String txtfield="";
        
        txtfield=form.getTxtSearch().getText();
        if(txtfield.equals("")){
            actualizarTabla();
        }
        else{
            actualizarTabla1(txtfield);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        String txtfield="";
        
        txtfield=form.getTxtSearch().getText();
        
        if(txtfield.equals("")){
            actualizarTabla();
        }
        else{
            actualizarTabla1(txtfield);
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        String txtfield="";
        
        txtfield=form.getTxtSearch().getText();
        
        if(txtfield.equals("")){
            actualizarTabla();
        }
        else{
            actualizarTabla1(txtfield);
        }
    }
    //</editor-fold>
}
