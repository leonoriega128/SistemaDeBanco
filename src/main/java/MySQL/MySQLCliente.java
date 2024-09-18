
package MySQL;

import DAO.clienteDAO;
import Modelo.Cliente;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLCliente implements clienteDAO{
    final String INSERT = "INSERT INTO clientes(id_cliente,nombre,alias,direccion) VALUES(?,?,?,?)";
    final String UPDATE = "UPDATE clientes SET id_cliente=?,nombre=?, alias=?, direccion=? WHERE id_cliente=?" ;
    final String DELETE = "DELETE FROM clientes WHERE id_cliente=?";
    final String GETALL = "SELECT id_cliente, nombre, alias, direccion FROM clientes";
    final String GETONE = "SELECT id_cliente, nombre, alias, direccion FROM clientes WHERE id_cliente=?";
    
    private Connection conn;
    public MySQLCliente (Connection c){
        this.conn=c;
    }
    
    private Cliente convertir(ResultSet rs)throws SQLException{
        int id=rs.getInt("id_cliente");
        String nom=rs.getString("nombre");
        String al=rs.getString("alias");
        String dir=rs.getString("direccion");
        return new Cliente(id, nom, al, dir);
    }

    @Override
    public void insertar(Cliente a) {
        PreparedStatement stat=null;
        try{
            stat=conn.prepareStatement(INSERT);
            stat.setInt(1, a.getId_Cliente());
            stat.setString(2, a.getNombre_Cliente());
            stat.setString(3, a.getAlias());
            stat.setString(4, a.getDireccion());
            stat.execute();
            if(stat.executeUpdate() ==0){
                System.out.println("Tal vez no se guardó correctamente.");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MySQLCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(stat!=null){
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MySQLCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }

    @Override
    public void modificar(Cliente a) {
        PreparedStatement stat=null;
        try{
            stat=conn.prepareStatement(UPDATE);
            stat.setInt(1, a.getId_Cliente());
            stat.setString(2, a.getNombre_Cliente());
            stat.setString(3, a.getAlias());
            stat.setString(4, a.getDireccion());
            stat.execute();
            if(stat.executeUpdate() ==0){
                System.out.println("Tal vez no se guardó correctamente.");
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(MySQLCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(stat!=null){
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MySQLCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void eliminar(Cliente a) {
        PreparedStatement stat=null;
        try{
            stat=conn.prepareStatement(DELETE);
            stat.setInt(1, a.getId_Cliente());
            stat.execute();
            
            if(stat.executeUpdate()==0){
                System.out.println("Tal vez no se eliminó correctamente.");
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(MySQLCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(stat!=null){
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MySQLCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<Cliente> obtenerTodos() {
        PreparedStatement stat=null;
        ResultSet rs=null;
        List<Cliente> clientes= new ArrayList<>();
        try{
            stat=conn.prepareStatement(GETALL);
            rs=stat.executeQuery();
            
            while(rs.next()){
                clientes.add(convertir(rs));
            }
        }
        catch (SQLException ex) {        
            Logger.getLogger(MySQLCliente.class.getName()).log(Level.SEVERE, null, ex);
        }        
        finally{
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MySQLCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(stat!=null){
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MySQLCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return clientes;
    }

    @Override
    public Cliente obtener(Integer id) {
        PreparedStatement stat=null;
        ResultSet rs=null;
        Cliente a=null;
        
        try{
            stat=conn.prepareStatement(GETONE);
            stat.setInt(1, id);
            rs=stat.executeQuery();
            if(rs.next()){
                a=convertir(rs);
            }
            else{
                System.out.println("No se pudo encontrar el registro.");
            }
        }
        catch (SQLException ex) {        
            Logger.getLogger(MySQLCliente.class.getName()).log(Level.SEVERE, null, ex);
        }        
        finally{
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MySQLCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(stat!=null){
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MySQLCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return a;
    }
//    public static void main(String[]args) throws SQLException{
//    String driver = "com.mysql.jdbc.Driver";
//    String database = "banco";
//    String hostname = "localhost";
//    String port = "3307";
//    String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";
//    Connection con=null;
//    con=DriverManager.getConnection(url,"root","");
//    MySQLCliente dao=new MySQLCliente(con);
//  
//     Cliente a=new Cliente(7,"Iguro Ubanai","serpiente","Salinas");
//     dao.eliminar(a);
//     List<Cliente> clientes=dao.obtenerTodos(); 
//     for(int i=0;i<clientes.size();i++){
//          Cliente elemento = clientes.get(i);
//          System.out.print(elemento.getId_Cliente());
//          System.out.print(" ");
//          System.out.print(elemento.getNombre_Cliente());
//          System.out.print(" ");
//          System.out.print(elemento.getAlias());
//          System.out.print(" ");
//          System.out.println(elemento.getDireccion());}       
//    }
}