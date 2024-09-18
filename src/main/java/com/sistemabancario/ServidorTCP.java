package com.sistemabancario;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {

    public static void main(String[] args) {
        // TODO code application logic here
        ServerSocket server = null;
        try {
            server = new ServerSocket(7777); // connection socket
            while (true) {
                Socket sock = server.accept(); // waiting
                System.out.println("connected");
                //PrintWriter sockOut = new PrintWriter(sock.getOutputStream(), true);
                //BufferedReader sockIn = new BufferedReader(new
                //InputStreamReader(sock.getInputStream()));				
                DataInputStream in = new DataInputStream(sock.getInputStream());
                DataOutputStream out = new DataOutputStream(sock.getOutputStream());

                // Pido al cliente el nombre al cliente
                out.writeUTF("Indica tu nombre");
                String nombreCliente = in.readUTF();

                out.writeUTF("\nHola " + nombreCliente + ", elige una opción:\n"
                        + "1. Retiro\n"
                        + "2. Depósito\n"
                        + "3. Transferencia\n"
                        + "4. Salir\n"
                        + "Ingrese el número de la opción:");

                // Leer la opción seleccionada por el cliente
                String opcion = in.readUTF();

                switch (opcion) {
                    case "1":
                        out.writeUTF("Has seleccionado: Retiro\nIngrese el monto a retirar:");
                        String montoRetiro = in.readUTF();
                        // Lógica para el retiro
                        out.writeUTF("Has retirado: " + montoRetiro);
                        break;

                    case "2":
                        out.writeUTF("Has seleccionado: Depósito\nIngrese el monto a depositar:");
                        String montoDeposito = in.readUTF();
                        // Lógica para el depósito
                        out.writeUTF("Has depositado: " + montoDeposito);
                        break;

                    case "3":
                        out.writeUTF("Has seleccionado: Transferencia\nIngrese el monto a transferir:");
                        String montoTransferencia = in.readUTF();
                        out.writeUTF("Ingrese el destinatario:");
                        String destinatario = in.readUTF();
                        // Lógica para la transferencia
                        out.writeUTF("Has transferido " + montoTransferencia + " a " + destinatario);
                        break;

                    case "4":
                        out.writeUTF("Gracias por usar el servicio, hasta luego.");
                        return; // Terminar la comunicación con el cliente

                    default:
                        out.writeUTF("Opción no válida, por favor ingrese una opción válida.");
                        break;
                }

                //crear hilo para cliente
                ServidorHilo hilo = new ServidorHilo(in, out, nombreCliente);
                hilo.start();

                System.out.println("Creada la conexion con el cliente " + nombreCliente);
                //

                sock.close();
            } //end while (true)
        } catch (IOException e) {
            try {
                server.close();
            } catch (IOException e2) {
            }
        } // end first catch
    }

}
