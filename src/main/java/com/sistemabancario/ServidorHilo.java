package com.sistemabancario;

import Controlador.Conexion;
import Modelo.Cliente;
import Modelo.Cuenta;
import MySQL.MySQLCliente;
import MySQL.MySQLCuenta;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServidorHilo extends Thread {

    private DataInputStream in;
    private DataOutputStream out;
    private String nombreCliente;
    private Socket socket;

    public ServidorHilo(Socket socket, DataInputStream in, DataOutputStream out, String nombreCliente) {
        this.socket = socket;
        this.in = in;
        this.out = out;
        this.nombreCliente = nombreCliente;
    }

    @Override
    public void run() {
        try {
            out.writeUTF("Ingrese numero de cuenta:");
            nombreCliente = in.readUTF();
            Cliente cliente = traerCuenta(Integer.parseInt(nombreCliente));
            // Obtener el saldo inicial de la cuenta
            float fondosCuenta = consultarCuenta(cliente.getId_Cliente());

            int opcion = 0;
            do {
                // Enviar opciones al cliente
                out.writeUTF("\nHola " + cliente.getNombre_Cliente() + ", su saldo actual es de: " + fondosCuenta + ", elige una opción:\n"
                        + "1. Retiro\n"
                        + "2. Depósito\n"
                        + "3. Transferencia\n"
                        + "4. Salir\n"
                        + "Ingrese el número de la opción:");

                // Leer la opción seleccionada por el cliente
                String opcionStr = in.readUTF();
                try {
                    opcion = Integer.parseInt(opcionStr);
                } catch (NumberFormatException e) {
                    out.writeUTF("Opción no válida, por favor ingrese un número.");
                    continue; // Volver al inicio del bucle
                }

                switch (opcion) {
                    case 1:
                        out.writeUTF("Has seleccionado: Retiro\nIngrese el monto a retirar:");
                        float montoRetiro = Float.parseFloat(in.readUTF());

                        if (fondosCuenta >= montoRetiro) {
                            fondosCuenta -= montoRetiro; // Actualiza el saldo
                            out.writeUTF("Has retirado: " + montoRetiro + ". Saldo actual: " + fondosCuenta);

                            actualizarSaldo(cliente, fondosCuenta);
                        } else {
                            out.writeUTF("Saldo insuficiente. Saldo actual: " + fondosCuenta);
                        }
                        break;

                    case 2:
                        out.writeUTF("Has seleccionado: Depósito\nIngrese el monto a depositar:");
                        float montoDeposito = Float.parseFloat(in.readUTF());
                        fondosCuenta += montoDeposito; // Actualiza el saldo
                        out.writeUTF("Has depositado: " + montoDeposito + ". Saldo actual: " + fondosCuenta);

                        actualizarSaldo(cliente, fondosCuenta);
                        break;

                    case 3:
                        out.writeUTF("Has seleccionado: Transferencia\nIngrese el monto a transferir:");
                        float montoTransferencia = Float.parseFloat(in.readUTF());
                        out.writeUTF("Ingrese el destinatario:");
                        String destinatario = in.readUTF();
                        if (fondosCuenta >= montoTransferencia) {
                            fondosCuenta -= montoTransferencia; // Actualiza saldo
                            out.writeUTF("Has transferido " + montoTransferencia + " a " + destinatario + ". Saldo actual: " + fondosCuenta);

                            actualizarSaldo(cliente, fondosCuenta);
                        } else {
                            out.writeUTF("Saldo insuficiente para la transferencia. Saldo actual: " + fondosCuenta);
                        }
                        break;

                    case 4:
                        out.writeUTF("Gracias por usar el servicio, hasta luego.");
                        break;

                    default:
                        out.writeUTF("Opción no válida, por favor ingrese una opción válida.");
                        break;
                }

            } while (opcion != 4);

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error en el hilo: " + e.getMessage());
        } finally {
            try {
                socket.close(); // Aseguramos el cierre del socket
            } catch (IOException e) {
                System.out.println("Error cerrando el socket: " + e.getMessage());
            }
        }
    }

    public float consultarCuenta(int id) {
        Conexion c = new Conexion();
        MySQLCuenta msq = new MySQLCuenta(c.conectar());
        Cuenta cuenta = msq.obtener(id);
        System.out.println("Saldo actual de la cuenta: " + cuenta.getSaldo());
        return cuenta.getSaldo();
    }

    public void actualizarSaldo(Cliente cliente, float retiro) {
        Conexion c = new Conexion();
        MySQLCuenta msq = new MySQLCuenta(c.conectar());
        Cuenta cuenta = msq.obtener(cliente.getId_Cliente());
        cuenta.setId_Cuenta(cuenta.getId_Cuenta());
        cuenta.setId_Cliente(cuenta.getId_Cliente());
        cuenta.setTipo(cuenta.getTipo()); 
        cuenta.setFecha_Creacion("123");
        cuenta.setSaldo(retiro);
        System.out.println(retiro);
        System.out.println(cuenta.getId_Cliente() + cuenta.getSaldo() + cuenta.getId_Cliente());
        msq.modificar(cuenta);
    }

    public Cliente traerCuenta(int id) {
        Conexion c = new Conexion();
        MySQLCliente msq = new MySQLCliente(c.conectar());
        Cliente cliente = msq.obtener(id);
        if (cliente == null) {
            return null;
        }
        return cliente;
    }
}
