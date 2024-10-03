package com.sistemabancario;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(7777)) {
            while (true) {
                Socket sock = server.accept();
                System.out.println("Cliente conectado");

                // Crear los streams
                DataInputStream in = new DataInputStream(sock.getInputStream());
                DataOutputStream out = new DataOutputStream(sock.getOutputStream());
                 String nombreCliente = in.readUTF();
                System.out.println("Nombre del cliente: " + nombreCliente);
                // Crear y empezar el hilo, pasando el socket
                ServidorHilo hilo = new ServidorHilo(sock, in, out,nombreCliente); // Inicialmente con nombre vacío
                hilo.start();
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}
