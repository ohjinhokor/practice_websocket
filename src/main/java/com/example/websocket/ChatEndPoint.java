package com.example.websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/chat/{username}")
public class ChatEndPoint {

    private Session session;
    private static Set<ChatEndPoint> chatEndPoints = new CopyOnWriteArraySet<>();
    private static HashMap<String, String> users = new HashMap<>();

    @OnOpen
    public void onOpen(Session session) throws IOException{

    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException{

    }

    @OnClose
    public void onClose(Session session) throws IOException{

    }

    @OnError
    public void onError(Session session, Throwable throwable){

    }
}
