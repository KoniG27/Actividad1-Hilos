package ClienteServidor.tallerservidor;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Servidor extends Thread{

    private Socket socket;
    private PrintWriter output;

    public Servidor(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run(){
        int val = 0;
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            boolean exit = false;
            output = new PrintWriter(socket.getOutputStream(), true);
            output.println("-- Bienvenido al servidor que publica todo lo que usted escribia, si quiere finalizar escriba <<exit>>");
            String outputString;
            while (!exit) {
                outputString = input.readLine();
                if (outputString.equals("exit")) {
                    exit = true;
                    System.out.println("Exit - Se cerro la conexión con uno de los clientes");

                }
                else {

                    if(val != 0){
                        System.out.println(outputString);

                    }
                    if(val == 0)
                    {
                        System.out.println(outputString + " entro al chat");
                        ++val;

                    }
                }
            }
        }
        catch (Exception e){
            System.out.println("Error occured " + e.getStackTrace());
        }
    }
}
