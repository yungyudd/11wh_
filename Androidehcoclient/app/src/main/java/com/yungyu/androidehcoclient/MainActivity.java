package com.yungyu.androidehcoclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    Socket socket;
    OutputStream os;
    InputStream is;
    BufferedReader in;
    PrintWriter out;
    ConnectThread thread;
    Button button01, button02;
    EditText input01, input02;
    TextView text01;
    String msg;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input01 = (EditText) findViewById(R.id.input01);
        input02 = (EditText) findViewById(R.id.input02);
        text01 = (TextView) findViewById(R.id.text01);
        button01 = (Button) findViewById(R.id.button01);
        button02 = (Button) findViewById(R.id.button02);
        button02.setEnabled(false);

        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addr = input01.getText().toString().trim();
                thread = new ConnectThread(addr);
                thread.start();

                button01.setEnabled(false);
                button02.setEnabled(true);
            }
        });
        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg = input02.getText().toString().trim();

                new Thread(){
                    public void run(){
                        out.println(msg);
                        out.flush();
                        thread.readServer();
                    }
                }.start();
                if(msg.equals("bye")){
                    thread.setStop();
                    button01.setEnabled(true);
                    button02.setEnabled(false);
                }
                input02.setText("");
            }
        });
    }
    class ConnectThread extends Thread{
        String hostname;
        public ConnectThread(String addr){
            hostname = addr;
        }
        public void run(){
            try{
                int port = 9000;
                socket = new Socket(hostname, port);
                os = socket.getOutputStream();
                is = socket.getInputStream();
                in = new BufferedReader(new InputStreamReader(is));
                out = new PrintWriter(os);
             } catch (IOException e){
                e.printStackTrace();
                try {
                    socket.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        public void readServer() {
            try {
                String msg1 = in.readLine();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        text01.setText("서버에서 받은 내용: "+msg1);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void setStop(){
            if(socket.isConnected()){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}