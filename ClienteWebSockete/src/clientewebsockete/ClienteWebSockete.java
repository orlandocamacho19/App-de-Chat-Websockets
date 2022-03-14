/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientewebsockete;

import Entities.Message;
import java.io.IOException;
import java.net.URI;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/**
 *
 * @author lv1013
 */
public class ClienteWebSockete {
    private static Object waitLock = new Object();

        
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingresa tu nombre: ");
        String user = sc.nextLine();
        WebSocketContainer container=null;//
        Session session=null;
        try {                        
            //Tyrus is plugged via ServiceLoader API. See notes above
            container = ContainerProvider.getWebSocketContainer();
            //WS1 is the context-root of my web.app
            //ratesrv is the  path given in the ServerEndPoint annotation on server implementation
            session=container.connectToServer(MiEndpoint.class, URI.create("ws://localhost:8080/webserver/chat/" + user));            
            RemoteEndpoint.Basic basicRemote = session.getBasicRemote();
            String addressee=null;
            String msj=null;
            do{
                System.out.print("Para: ");
                addressee = sc.nextLine();
                System.out.print("Mensaje: ");
                msj = sc.nextLine();
                
                Message msg = new Message(user, addressee, msj);
                
                basicRemote.sendText(msg.getJson());
            }while(!msj.equals("out"));            
        } catch (DeploymentException | IOException ex) {
            ex.printStackTrace();
        }
        finally{
            if(session!=null){
                try {
                    session.close();
                } catch (IOException e) {     
                    e.printStackTrace();
                }
            }     
        }
    }
}
