package simple_tcp_advanced;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public Client() throws Exception{
        Socket socket = new Socket("localhost", 2020);
        System.out.println("Successfully connected to the server!");

        // I/O Buffers
        BufferedReader in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        Scanner s = new Scanner(System.in);
        String number;

        while (in_socket.readLine().startsWith("Guess")){
            System.out.println("Guess the number [1-10]");
            number = s.nextLine();
            out_socket.println(number);
        }

        System.out.println("You guessed it right!");

        socket.close();
        System.out.println("Socket close!");
    }

    public static void main(String[] args) {
        try {
            new Client();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
