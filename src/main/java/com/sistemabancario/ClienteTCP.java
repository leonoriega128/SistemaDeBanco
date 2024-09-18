
package com.sistemabancario;

//import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteTCP {

    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
                Socket sock = null;
                Scanner sn = new Scanner(System.in);
                sn.useDelimiter("\n");
                
		//PrintWriter sockOut = null;
		//BufferedReader sockIn = null;
		try {   
                        sock = new Socket("localhost", 7777); // the communication socket.
			//sockOut = new PrintWriter(sock.getOutputStream(), true); //force write
			//sockIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                        
                        //
                        DataInputStream in = new DataInputStream(sock.getInputStream());
                        DataOutputStream out = new DataOutputStream(sock.getOutputStream());
            
                        // Leer mensaje del servidor
                        String mensaje = in.readUTF();
                        System.out.println(mensaje);
            
                        // Escribe el nombre y se lo manda al servidor
                        String nombre = sn.next();
                        out.writeUTF(nombre);
            
                        // ejecutamos el hilo
                        ClienteHilo hilo = new ClienteHilo(in, out);
                        hilo.start();
                        hilo.join();
                        
		} catch (UnknownHostException e) {
		System.err.println("host unreachable: localhost");
		System.exit(1);
		} catch (IOException e) {
			System.err.println("cannot connect to: localhost");
			System.exit(1);
		} catch (InterruptedException ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
		System.out.println("press any key to close");
                System.out.println("prueba");
		//Scanner scan = new Scanner(System.in);
		//String message = scan.next().toLowerCase();
		//while (! message.equals("no")) {
			//sockOut.println(message);
			//String recu = sockIn.readLine();
			//System.out.println("server -> client:" + recu);
			//message = scan.next().toLowerCase();
		//}
		//sockOut.close();
		//sockIn.close();
		sock.close();
    }
    
}
