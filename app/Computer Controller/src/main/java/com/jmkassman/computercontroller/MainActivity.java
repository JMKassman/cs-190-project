package com.jmkassman.computercontroller;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "Main Activity";

    @BindView(R.id.edit_text)
    EditText editText;

    @BindView(R.id.ip_edit_text)
    EditText ipAddress;

    @BindView(R.id.ip_button)
    Button ipButton;

    private String host;
    private static final String port = "45342";

    private String oldInput = "";
    private MainActivity context;
    private boolean hostInitialized = false;

    public Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.context = this;
        this.toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, 1);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.INTERNET}, 2);
        }

        ipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hostInitialized = true;
                host = ipAddress.getText().toString();
                toast.setText("Host updated");
                toast.show();
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String toSend;
                if (s.toString().length() > oldInput.length()) {
                    //new character to process
                    toSend = s.toString().substring(s.toString().length()-1);
                    oldInput = s.toString();
                    Log.d(TAG, "afterTextChanged: new character \"" + toSend + "\"");
                }
                else {
                    //backspace pressed
                    toSend = "BACKSPACE";
                    oldInput = s.toString();
                    Log.d(TAG, "afterTextChanged: character deleted");
                }
                if (hostInitialized) {
                    new NetworkController(context).execute(host, port, toSend);
                }
                else {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context)
                            .setTitle("Network Error")
                            .setMessage("Please set an IP address first")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialogBuilder.create().show();
                    s.clear();
                }
            }
        });
    }
}
