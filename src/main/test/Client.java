/**
 * @author : iron
 * @version : 1.0.0
 * @date : 15:40 2019-05-28
 */

import jdk.nashorn.internal.objects.Global;

import javax.websocket.*;

@ClientEndpoint()
public class Client {
    @OnOpen
    public void onOpen(Session session) {}

    @OnMessage
    public void onMessage(String message) {
        System.out.println("Client: " + message);
        CL.GlobRes = message;
    }

    @OnClose
    public void onClose() {}
}