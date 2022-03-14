/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientewebsockete;

import Entities.Message;
import com.google.gson.Gson;
import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

/**
 *
 * @author lv1013
 */
@ClientEndpoint
public class MiEndpoint {
    
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    
    @OnOpen
    public void open(Session s){
    }
    

    @OnMessage
    public void onMessage(String message) {
        if (message.charAt(0) == '{') {
            Gson gson = new Gson();
            Message msg = gson.fromJson(message, Message.class);

            if (msg.getAddressee().equalsIgnoreCase("") || msg.getAddressee().equalsIgnoreCase("All")) {
                System.out.println(ANSI_CYAN + "\n\n--- De: " + msg.getSender() + " --- Para: Todos ---" + ANSI_CYAN );
                System.out.println(ANSI_CYAN + msg.getText() + ANSI_CYAN);
                System.out.println(ANSI_CYAN + "--- A: " + msg.getDate() + " ---" + ANSI_CYAN);
            } else {
                System.out.println(ANSI_GREEN + "\n\n--- De: " + msg.getSender() + " --- Para: Ti ---" + ANSI_GREEN);
                System.out.println(ANSI_GREEN + msg.getText() + ANSI_GREEN);
                System.out.println(ANSI_GREEN + "--- A: " + msg.getDate() + " ---" + ANSI_GREEN);
            }  
        } else {
            System.out.println(ANSI_RESET + message);
        }  
        System.out.print(ANSI_RESET + "Para: ");
    }    
}
