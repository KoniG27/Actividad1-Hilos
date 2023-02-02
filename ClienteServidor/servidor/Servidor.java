package ClienteServidor.servidor;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Servidor extends Thread{

    private Socket socket;
    private PrintWriter output;

    private PrintWriter output2;


    public Servidor(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run(){
        int val = 0;
        String pregunta1 = " si quieres saber cual es la capital de paris inserta 1";
        String respuesta1 = "La respuesta a la pregunta es: Paris";
        String pregunta2 = " si quieres saber quien escribio la novela 'To kill a mockingbird' inserta 2";
        String respuesta2 = "La respuesta a la pregunta es: Harper lee";
        String pregunta3 = " si quieres saber cual es el planeta mas grande de nuestro sistema solar inserta 3";
        String respuesta3 = "La respuesta a la pregunta es: Jupiter";
        String pregunta4 = " si quieres saber cuando termino la segunda guerra mundial inserta 4";
        String respuesta4 = "La respuesta a la pregunta es: 1945";
        String pregunta5 = " si quieres saber quien pinto la mona lista inserta 5";
        String respuesta5 = "La respuesta a la pregunta es: Leonardo da Vinci";
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            boolean exit = false;
            int clientnumber = 0;
            output = new PrintWriter(socket.getOutputStream(), true);


            output.println(pregunta1);
            output.println(pregunta2);
            output.println(pregunta3);
            output.println(pregunta4);
            output.println(pregunta5);

            String outputString;
            while (!exit) {
                outputString = input.readLine();

                if (outputString.equals("exit")) {
                    exit = true;
                    System.out.println("Exit - Se cerro la conexión con uno de los clientes");

                }
                else {

                    if(val != 0){

                        switch (outputString) {
                            case "1":
                                output.println(respuesta1);
                                break;
                            case "2":
                                output.println(respuesta2);

                                break;
                            case "3" :
                                output.println(respuesta3);

                                break;
                            case "4":
                                output.println(respuesta4);

                                break;
                            case "5":
                                output.println(respuesta5);

                                break;
                            default:
                                output.println("Esta no es una seleccion correcta: " + outputString);

                        }


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
