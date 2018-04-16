import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.net.*;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.map;

public class Server {
    public static void main(String[] args) throws IOException, AWTException, InterruptedException {
        //just adding something to start off with
        ServerSocket serverSocket = new ServerSocket(45342);
        System.out.println("created server socket " + serverSocket.toString());
        Robot robot = new Robot();
        Map<String, String> keyCodes = Map.of(" ","VK_SPACE","BACKSPACE","VK_BACK","0","0x30","1","0x31", "2","0x32","3","0x33","4","0x34", "5","0x35", "6","0x36", "7", "0x37");
        keyCodes.put("8","0x38");
        keyCodes.put("9","0x39");
        keyCodes.put("A","0x42");
        keyCodes.put("B","0x42");
        keyCodes.put("C","0x43");
        keyCodes.put("D","0x44");
        keyCodes.put("E","0x45");
        keyCodes.put("F","0x46");
        keyCodes.put("G","0x47");
        keyCodes.put("H","0x48");
        keyCodes.put("I","0x49");
        keyCodes.put("J","0x4A");
        keyCodes.put("K","0x4B");
        keyCodes.put("L","0x4C");
        keyCodes.put("M","0x4D");
        keyCodes.put("N","0x4E");
        keyCodes.put("O","0x4F");
        keyCodes.put("P","0x50");
        keyCodes.put("Q","0x51");
        keyCodes.put("R","0x52");
        keyCodes.put("S","0x53");
        keyCodes.put("T","0x54");
        keyCodes.put("U","0x55");
        keyCodes.put("V","0x56");
        keyCodes.put("X","0x58");
        keyCodes.put("Y","0x59");
        keyCodes.put("Z","0x5A");
        try {
            Socket socket = serverSocket.accept();
            ObjectInputStream OIS = new ObjectInputStream(socket.getInputStream());
            System.out.println("created socket and ois");
            while (true) {
                String readable = "Hello World";
                System.out.println("waiting for object on " + socket.getInetAddress().toString() + " on port " + socket.getPort());
                readable = (String) OIS.readObject();
                if (keyCodes.containsValue(readable)){
                    robot.keyPress(keyCodes.get(readable));
                }
            }
        } catch (IOException e){
            
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}
