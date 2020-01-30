/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketChat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Admin
 */
public class ReceiveThread extends Thread {

    Socket socket; // socket is join to the communication
    JTextArea txt; //text-area contains communicated message
    BufferedReader br;//input buffer of the socket
    String sender;
    String receiver;

    public ReceiveThread(Socket socket, JTextArea txt, String sender, String receiver) {
        super();
        this.socket = socket;
        this.txt = txt;
        this.sender = sender;
        this.receiver = receiver;
        try {
            br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Network Error");
            System.exit(0);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (this.socket != null) {
                    String msg = "";//get data from input Stream
                    if ((msg = br.readLine()) != null && msg.length() > 0) {
                        txt.append("\n" + receiver + ":" + msg);
                    }
                    sleep(1000);
                }

            } catch (Exception ex) {

            }
        }
    }

}
