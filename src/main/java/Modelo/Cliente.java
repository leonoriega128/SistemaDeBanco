
package Modelo;

public class Cliente {
    private int id_Cliente;
    private String nombre_Cliente;
    private String alias;
    private String direccion;

    public Cliente(int id_Cliente,String nombre_Cliente, String alias, String direccion) {
        this.id_Cliente=id_Cliente;
        this.nombre_Cliente = nombre_Cliente;
        this.alias = alias;
        this.direccion = direccion;
    }
    
    public Cliente(String nombre_Cliente, String alias, String direccion) {
        this.nombre_Cliente = nombre_Cliente;
        this.alias = alias;
        this.direccion = direccion;
    }

    public int getId_Cliente() {
        return id_Cliente;
    }

    public void setId_Cliente(int id) {
        this.id_Cliente = id;
    }

    public String getNombre_Cliente() {
        return nombre_Cliente;
    }

    public void setNombre_Cliente(String nom) {
        this.nombre_Cliente = nom;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String a) {
        this.alias = a;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String dir) {
        this.direccion = dir;
    }
}
