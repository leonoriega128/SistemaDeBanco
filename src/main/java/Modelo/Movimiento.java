
package Modelo;

public class Movimiento {
    private int id_Movimiento;
    private int id_Cuenta;
    private String tipo_Movimiento;
    private float importe;
    private int cuenta_Origen;
    private int cuenta_Destino;
    private String fecha;

    public Movimiento(int id_Movimiento, int id_Cuenta, String tipo_Movimiento, float importe, String fecha) {
        this.id_Movimiento = id_Movimiento;
        this.id_Cuenta = id_Cuenta;
        this.tipo_Movimiento = tipo_Movimiento;
        this.importe = importe;
        this.fecha = fecha;
    }
    
    public Movimiento(int id_Movimiento, int id_Cuenta, String tipo_Movimiento, float importe,int c_Origen,int c_Destino, String fecha) {
        this.id_Movimiento = id_Movimiento;
        this.id_Cuenta = id_Cuenta;
        this.tipo_Movimiento = tipo_Movimiento;
        this.importe = importe;
        this.cuenta_Origen=c_Origen;
        this.cuenta_Destino=c_Destino;
        this.fecha = fecha;
    }
    
    public Movimiento(int id_Cuenta, String tipo_Movimiento, float importe, String fecha) {
        
        this.id_Cuenta = id_Cuenta;
        this.tipo_Movimiento = tipo_Movimiento;
        this.importe = importe;
        this.fecha = fecha;
    }

    public int getId_Movimiento() {
        return id_Movimiento;
    }

    public void setId_Movimiento(int id_Mov) {
        this.id_Movimiento = id_Mov;
    }

    public int getId_Cuenta() {
        return id_Cuenta;
    }

    public void setId_Cuenta(int id_Cu) {
        this.id_Cuenta = id_Cu;
    }

    public String getTipo_Movimiento() {
        return tipo_Movimiento;
    }

    public void setTipo_Movimiento(String tipo) {
        this.tipo_Movimiento = tipo;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }
    
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCuenta_Destino() {
        return cuenta_Destino;
    }

    public void setCuenta_Destino(int cuenta_Destino) {
        this.cuenta_Destino = cuenta_Destino;
    }

    public int getCuenta_Origen() {
        return cuenta_Origen;
    }

    public void setCuenta_Origen(int cuenta_Origen) {
        this.cuenta_Origen = cuenta_Origen;
    }
    
}
