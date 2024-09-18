
package com.sistemabancario;

//import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
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
                                
                                //crear hilo para cliente
                                ServidorHilo hilo = new ServidorHilo(in, out,nombreCliente);
                                hilo.start();
                                
                                System.out.println("Creada la conexion con el cliente " + nombreCliente);
                                //
                                
				sock.close();
			} //end while (true)
		} catch (IOException e) {
		try {server.close();}
		catch (IOException e2) {}
		} // end first catch
    }
    
}
