package ClienteServidor.tallercliente;

import java.io.BufferedReader;
import java.io.IOException;
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

            response = input.readLine();
            System.out.println(response);

            do {
                if (clienteName.equals("empty")) {
                    System.out.println("Enter your name");
                    userInput = scanner.nextLine();
                    clienteName = userInput;
                    output.println(userInput);
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
                        output.println(message + " " + userInput);
                    }
                }
            }while (!userInput.equals("exit"));

        } catch (Exception e) {
            System.out.println("Exception occured in client main: " + e.getStackTrace());
        }
    }
}

