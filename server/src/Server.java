import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        //just adding something to start off with
        ServerSocket serverSocket = new ServerSocket(45342);
        System.out.println("created server socket " + serverSocket.toString());
        try {
            Socket socket = serverSocket.accept();
            ObjectInputStream OIS = new ObjectInputStream(socket.getInputStream());
            System.out.println("created socket and ois");
            while (true) {
                String readable = "Hello World";
                System.out.println("waiting for object on " + socket.getInetAddress().toString() + " on port " + socket.getPort());
                readable = (String) OIS.readObject();
                System.out.println(readable);
            }
        } catch (IOException e){
            
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}
