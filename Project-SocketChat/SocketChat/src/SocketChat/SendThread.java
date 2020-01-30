/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketChat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextArea;

/**
 *
 * @author Admin
 */
public class SendThread extends Thread {

    ServerSocket srvSocket;
    Socket socket; // socket is join to the communication
    JTextArea txt; //text-area contains communicated message
    BufferedReader br;//input buffer of the socket
    String sender;
    String receiver;

    public SendThread(Socket socket, JTextArea txt, String sender, String receiver) {
        super();
        try {
            this.srvSocket = new ServerSocket(12340);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.socket = socket;
        this.txt = txt;
        this.sender = sender;
        this.receiver = receiver;
        try {
            //send message to socket
            DataOutputStream os = new DataOutputStream(socket.getOutputStream());
            Thread sendThread = new Thread() {
                @Override
                public void run() {
                    while (true) {
                        String text = txt.getText();
                        try {
                            //send some data to client
                            os.writeBytes(text);
                            os.write(13);
                            os.flush();
                        } catch (IOException ex) {
                        }
                    }
                }
            };
            sendThread.start();
        } catch (IOException ex) {

        }
    }

}
