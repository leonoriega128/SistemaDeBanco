
package Modelo;

public class Cuenta {
    private int id_Cuenta;
    private int id_Cliente;
    private String tipo;
    private float saldo;
    private String fecha_Creacion;

    public Cuenta(int id_Cuenta, int id_Cliente, String tipo, float saldo, String fecha_Creacion) {
        this.id_Cuenta = id_Cuenta;
        this.id_Cliente = id_Cliente;
        this.tipo = tipo;
        this.saldo = saldo;
        this.fecha_Creacion = fecha_Creacion;
    }
    public Cuenta(int id_Cliente, String tipo, float saldo, String fecha_Creacion) {
        this.id_Cliente = id_Cliente;
        this.tipo = tipo;
        this.saldo = saldo;
        this.fecha_Creacion = fecha_Creacion;
    }

    public int getId_Cuenta() {
        return id_Cuenta;
    }

    public void setId_Cuenta(int id_Cu) {
        this.id_Cuenta = id_Cu;
    }

    public int getId_Cliente() {
        return id_Cliente;
    }

    public void setId_Cliente(int id_C) {
        this.id_Cliente = id_C;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public String getFecha_Creacion() {
        return fecha_Creacion;
    }

    public void setFecha_Creacion(String fecha) {
        this.fecha_Creacion = fecha;
    }
    
}
