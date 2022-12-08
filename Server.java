package simple_tcp_advanced;

// Importing IOException class and ServerSocket class
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public Server() throws IOException {
        ServerSocket serverSocket = new ServerSocket(2020); // Open new port *Port 2020 is a UDP port for datagram protocol
        System.out.println("Port 2020 is open");

        Socket socket = serverSocket.accept(); // accept() here is a blocking method where when Java is running through this code, when it comes to this line, it will waiting for incoming connection then only it will continue to the other lines
        System.out.println("Client: " + socket.getInetAddress() + " has connected."); // getInetAddress is to retrieve the client's IP address from the socket object

        // I/O Buffers - A place to store our data in and out of the socket
        BufferedReader in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));      // getInputStream() here is to get the stream of data from client to the server. InputStreamReader is for making the stream of data readable for human.
        PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        String msg;
        int randomNumber = (int)(Math.random()*10+1);
        System.out.println("The random number is " + randomNumber);

        do {
            out_socket.println("Guess the number [1-10]: ");
            msg = in_socket.readLine();
        }while (Integer.parseInt(msg) != randomNumber);

        out_socket.println("You guessed it right!");

        socket.close();
        System.out.println("The socket has closed");
    }

    public static void main(String[] args) {
        try {
            new Server();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
