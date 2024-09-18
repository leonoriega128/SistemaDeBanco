
package MySQL;

import DAO.movimientoDAO;
import Modelo.Movimiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLMovimiento implements movimientoDAO{
    final String INSERT = "INSERT INTO movimientos(id_movimiento,id_cuenta,tipo_movimiento, importe,cuenta_origen,cuenta_destino, fecha) VALUES(?,?,?,?,?,?,?)";
    final String UPDATE = "UPDATE movimientos SET id_movimiento=?,id_cuenta=?,tipo_movimiento=?, importe=?,cuenta_origen=?,cuenta_destino=?, fecha=? WHERE id_movimiento=?" ;
    final String DELETE = "DELETE FROM movimientos WHERE id_movimiento=?";
    final String GETALL = "SELECT id_movimiento, id_cuenta, tipo_movimiento, importe,cuenta_origen,cuenta_destino, fecha FROM movimientos";
    final String GETONE = "SELECT id_movimiento, id_cuenta, tipo_movimiento, importe,cuenta_origen,cuenta_destino, fecha FROM movimientos WHERE id_movimiento=?";
    
    private Connection conn;
    public MySQLMovimiento (Connection c){
        this.conn=c;
    }
    
    private Movimiento convertir(ResultSet rs)throws SQLException{
        int move=rs.getInt("id_movimiento");
        int cuenta=rs.getInt("id_cuenta");
        String tipo=rs.getString("tipo_movimiento");
        Float imp=rs.getFloat("importe");
        int cO=rs.getInt("cuenta_origen");
        int cD=rs.getInt("cuenta_destino");
        String date=rs.getString("fecha");
        
        return new Movimiento(move,cuenta, tipo, imp,cO,cD, date);
    }

    @Override
    public void insertar(Movimiento a) {
        PreparedStatement stat=null;
        try{
            stat=conn.prepareStatement(INSERT);
            stat.setInt(1, a.getId_Movimiento());
            stat.setInt(2, a.getId_Cuenta());
            stat.setString(3, a.getTipo_Movimiento());
            stat.setFloat(4, a.getImporte());
            stat.setInt(5, a.getCuenta_Origen());
            stat.setInt(6, a.getCuenta_Destino());
            stat.setString(7, a.getFecha());
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
    public void modificar(Movimiento a) {
        PreparedStatement stat=null;
        try{
            stat=conn.prepareStatement(UPDATE);
            stat.setInt(1, a.getId_Movimiento());
            stat.setInt(2, a.getId_Cuenta());
            stat.setString(3, a.getTipo_Movimiento());
            stat.setFloat(4, a.getImporte());
            stat.setInt(5, a.getCuenta_Origen());
            stat.setInt(6, a.getCuenta_Destino());
            stat.setString(7, a.getFecha());
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
    public void eliminar(Movimiento a) {
        PreparedStatement stat=null;
        try{
            stat=conn.prepareStatement(DELETE);
            stat.setInt(1, a.getId_Movimiento());
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
    public List<Movimiento> obtenerTodos() {
        PreparedStatement stat=null;
        ResultSet rs=null;
        List<Movimiento> moves= new ArrayList<>();
        try{
            stat=conn.prepareStatement(GETALL);
            rs=stat.executeQuery();
            
            while(rs.next()){
                moves.add(convertir(rs));
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
        
        return moves;
    }

    @Override
    public Movimiento obtener(Integer id) {
        PreparedStatement stat=null;
        ResultSet rs=null;
        Movimiento a=null;
        
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
