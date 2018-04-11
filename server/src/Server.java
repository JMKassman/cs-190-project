import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        //just adding something to start off with
        ServerSocket serverSocket = new ServerSocket(1234);
        try {
            Socket socket = serverSocket.accept();
            ObjectInputStream OIS = new ObjectInputStream(socket.getInputStream());
            while (true) {
                String readable = "Hello World";
                readable = (String) OIS.readObject();
                System.out.println(readable);
            }
        } catch (IOException e){
            
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}
