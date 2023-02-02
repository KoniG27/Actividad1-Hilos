package ClienteServidor.servidor;

import java.net.ServerSocket;
import java.net.Socket;


public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciamos...");

        try(ServerSocket serversocket = new ServerSocket(5500)){
            System.out.println("--Servidor Iniciado--");
            System.out.println("Esperando conexion de cliente...");

            while (true) {
                Socket socket = serversocket.accept();
                System.out.println("Nueva conexion con cliente iniciada"+ " " + socket.getPort());
                Servidor serverThread = new Servidor(socket);
                serverThread.start();
            }
        }
        catch (Exception e) {
            System.out.println("Error occured in main: " + e.getStackTrace());
        }
    }
}