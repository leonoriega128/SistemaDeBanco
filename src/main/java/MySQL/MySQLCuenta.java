
package MySQL;

import DAO.cuentaDAO;
import Modelo.Cuenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLCuenta implements cuentaDAO{
    final String INSERT = "INSERT INTO cuentas(id_cuenta,id_cliente,tipo, slado,fecha_creacion) VALUES(?,?,?,?,?)";
    final String UPDATE = "UPDATE cuentas SET id_cuenta=?,id_cliente=?, tipo=?, saldo=?, fecha_creacion=? WHERE id_cuenta=?" ;
    final String DELETE = "DELETE FROM cuentas WHERE id_cuenta=?";
    final String GETALL = "SELECT id_cuenta, id_cliente, tipo, saldo, fecha_creacion FROM cuentas";
    final String GETONE = "SELECT id_cuenta, id_cliente, tipo, saldo, fecha_creacion FROM cuentas WHERE id_cuenta=?";
    
    private Connection conn;
    public MySQLCuenta (Connection c){
        this.conn=c;
    }
    
    private Cuenta convertir(ResultSet rs)throws SQLException{
        int cuenta=rs.getInt("id_cuenta");
        int clie=rs.getInt("id_cliente");
        String tipo=rs.getString("tipo");
        Float sal=rs.getFloat("saldo");
        String date=rs.getString("fecha_creacion");
        return new Cuenta(cuenta,clie,tipo,sal,date);
    }

    @Override
    public void insertar(Cuenta a) {
        PreparedStatement stat=null;
        try{
            stat=conn.prepareStatement(INSERT);
            stat.setInt(1, a.getId_Cuenta());
            stat.setInt(2, a.getId_Cliente());
            stat.setString(3, a.getTipo());
            stat.setFloat(4, a.getSaldo());
            stat.setString(5,a.getFecha_Creacion());
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
    public void modificar(Cuenta a) {
        PreparedStatement stat=null;
        try{
            stat=conn.prepareStatement(UPDATE);
            stat.setInt(1, a.getId_Cuenta());
            stat.setInt(2, a.getId_Cliente());
            stat.setString(3, a.getTipo());
            stat.setFloat(4, a.getSaldo());
            stat.setString(5,a.getFecha_Creacion());
            
            stat.setInt(6, a.getId_Cuenta());
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
    public void eliminar(Cuenta a) {
        PreparedStatement stat=null;
        try{
            stat=conn.prepareStatement(DELETE);
            stat.setInt(1, a.getId_Cuenta());
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
    public List<Cuenta> obtenerTodos() {
        PreparedStatement stat=null;
        ResultSet rs=null;
        List<Cuenta> cuentas= new ArrayList<>();
        try{
            stat=conn.prepareStatement(GETALL);
            rs=stat.executeQuery();
            
            while(rs.next()){
                cuentas.add(convertir(rs));
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
        
        return cuentas;
    }

    @Override
    public Cuenta obtener(Integer id) {
        PreparedStatement stat=null;
        ResultSet rs=null;
        Cuenta a=null;
        
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
}
