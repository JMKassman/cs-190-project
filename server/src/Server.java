import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import static java.awt.event.KeyEvent.*;

public class Server {
    private static Socket socket;
    private static ObjectInputStream OIS;

    public static void main(String[] args) throws IOException, AWTException {
        //just adding something to start off with
        ServerSocket serverSocket = new ServerSocket(45342);
        System.out.println("created server socket " + serverSocket.toString());
        Robot robot = new Robot();
        Map<String, Integer> keyCodes = new HashMap<>();
        keyCodes.put(" ", VK_SPACE);
        keyCodes.put("BACKSPACE", VK_BACK_SPACE);
        keyCodes.put("0",VK_0);
        keyCodes.put("1",VK_1);
        keyCodes.put("2",VK_2);
        keyCodes.put("3",VK_3);
        keyCodes.put("4",VK_4);
        keyCodes.put("5",VK_5);
        keyCodes.put("6",VK_6);
        keyCodes.put("7",VK_7);
        keyCodes.put("8",VK_8);
        keyCodes.put("9",VK_9);
        keyCodes.put("A",VK_A);
        keyCodes.put("B",VK_B);
        keyCodes.put("C",VK_C);
        keyCodes.put("D",VK_D);
        keyCodes.put("E",VK_E);
        keyCodes.put("F",VK_F);
        keyCodes.put("G",VK_G);
        keyCodes.put("H",VK_H);
        keyCodes.put("I",VK_I);
        keyCodes.put("J",VK_J);
        keyCodes.put("K",VK_K);
        keyCodes.put("L",VK_L);
        keyCodes.put("M",VK_M);
        keyCodes.put("N",VK_N);
        keyCodes.put("O",VK_O);
        keyCodes.put("P",VK_P);
        keyCodes.put("Q",VK_Q);
        keyCodes.put("R",VK_R);
        keyCodes.put("S",VK_S);
        keyCodes.put("T",VK_T);
        keyCodes.put("U",VK_U);
        keyCodes.put("V",VK_V);
        keyCodes.put("X",VK_X);
        keyCodes.put("Y",VK_Y);
        keyCodes.put("Z",VK_Z);
        try {
            System.out.println("created socket and ois");
            while (true) {
                socket = serverSocket.accept();
                OIS = new ObjectInputStream(socket.getInputStream());
                String readable;
                System.out.println("waiting for object on " + socket.getInetAddress().toString() + " on port " + socket.getPort());
                readable = (String) OIS.readObject();
                System.out.println(readable);
                if (keyCodes.containsKey(readable.toUpperCase())){
                    System.out.println("pressing key " + keyCodes.get(readable.toUpperCase()));
                    robot.keyPress(keyCodes.get(readable.toUpperCase()));
                    Thread.sleep(100);
                    robot.keyRelease(keyCodes.get(readable.toUpperCase()));
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
