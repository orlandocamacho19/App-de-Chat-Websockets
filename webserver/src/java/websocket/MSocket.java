package websocket;

import Entities.Message;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;
import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author orlandocamacho
 */
@ServerEndpoint("/chat/{name}")
public class MSocket {
    
    static HashMap<String, Session> users = new HashMap<String, Session>();
    
    @OnOpen
    public void onOpen(Session session, @PathParam("name") String name) throws IOException {
        synchronized (users) {
            users.put(name, session);
            for (Session openSession : session.getOpenSessions()) {
                openSession.getBasicRemote().sendText("Usuarios activos: " + users.keySet().toString());
            }
            session.getBasicRemote().sendText("Usuarios activos: " + users.keySet().toString());
        }
    }
        
    @OnMessage
    public void onMessage(String message, Session client) throws IOException, EncodeException {
        Gson gson = new Gson();
        Message msg = gson.fromJson(message, Message.class);
        if (msg.getAddressee().equalsIgnoreCase("") || msg.getAddressee().equalsIgnoreCase("All")){
           for (Session openSession : client.getOpenSessions()){
                if (!client.equals(openSession)) {
                    openSession.getBasicRemote().sendText(message);
                }  
            } 
        } else if (users.containsKey(msg.getAddressee())) {
            users.get(msg.getAddressee()).getBasicRemote().sendText(message);
        } else {
            users.get(msg.getSender()).getBasicRemote().sendText("Error: Usuario no encontrado");
        }
    }  
}
