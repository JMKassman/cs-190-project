package com.jmkassman.computercontroller;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NetworkController extends AsyncTask<String, Void, Integer> {
    private MainActivity context;
    private static final String TAG = "Network Controller";

    private Socket socket;
    private ObjectOutputStream oos;

    public NetworkController(MainActivity context) {
        this.context = context;
    }

    /**
     * @param strings ip, port, string to send
     * @return Integer -1 if error, 0 if success
     */
    @Override
    protected Integer doInBackground(String... strings) {
        for (String s : strings) {
            Log.d(TAG, "doInBackground: " + s);
        }
        if (socket == null) {
            Log.d(TAG, "doInBackground: creating socket");
            try {
                socket = new Socket(strings[0], Integer.parseInt(strings[1]));
                Log.d(TAG, "doInBackground: socket created");
            } catch (IOException e) {
                e.printStackTrace();
                return -1;
            }
        }
        if (oos == null) {
            Log.d(TAG, "doInBackground: creating oos");
            try {
                oos = new ObjectOutputStream(socket.getOutputStream());
                Log.d(TAG, "doInBackground: created oos");
            } catch (IOException e) {
                e.printStackTrace();
                return -1;
            }
        }

        try {
            Log.d(TAG, "doInBackground: sending object");
            oos.writeObject(strings[2]);
            oos.flush();
            Log.d(TAG, "doInBackground: sent object");
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    protected void onPostExecute(Integer result) {
        if (result == -1) {
            context.toast.setText("Network Connection Error");
            context.toast.show();
        }
        else if (result == 0) {
            context.toast.setText("Success");
            context.toast.show();
        }
        context = null;
    }
}
