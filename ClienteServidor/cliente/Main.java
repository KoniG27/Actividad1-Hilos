package ClienteServidor.cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void  main(String[] args) {

        try (Socket socket = new Socket("localhost", 5500)) {
            BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String userInput;
            String response;
            String clienteName = "empty";



            do {
                if (clienteName.equals("empty")) {
                    System.out.println("-- Bienvenido al chat --");
                    System.out.println("Primero porfavor introduce tu nombre:");
                    userInput = scanner.nextLine();
                    clienteName = userInput;
                    output.println(userInput);
                    for (int i = 0; i < 5; i++) {
                        response = input.readLine();
                        System.out.println(response);
                    }

                    if (userInput.equals("exit")) {
                        output.println(userInput);
                        input.close();
                        scanner.close();
                        System.out.println("Exit");
                        break;
                    }
                }
                else {



                    String message  = ( "(" + clienteName + ")" + "message : ");
                    System.out.println(message);

                    userInput = scanner.nextLine();

                    if (userInput.equals("exit")){
                        output.println(userInput);
                        input.close();
                        scanner.close();
                        System.out.println("Exit");
                        break;
                    }
                    else {

                        output.println(userInput);
                        String response2 = input.readLine();
                        System.out.println(response2);


                    }
                }
            }while (!userInput.equals("exit"));

        } catch (Exception e) {
            System.out.println("Exception occured in client main: " + e.getStackTrace());
        }
    }
}

