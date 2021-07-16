package com.example.designtopicselectionsystem.server;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

// 用户连接服务层
@ServerEndpoint("/api/webSocket/{userId}")
@Component
public class WebSocketServer {

    // hashtable是属于线程安全的
    private static Map<String, WebSocketServer> userList = new Hashtable<>();

    private static int onlineCount = 0; // 当前在线数

    private Session session; // 使用与用来与客户端连接对话

    private String userId; // 当前用户的id

    //连接打开时执行
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "userId") String userId) throws IOException{
        if(StringUtils.isNotBlank(userId) && !userList.containsKey(userId)){
            System.out.println("新客户端接入，用户ID：" + userId);
            this.userId = userId;
            this.session = session;
            userList.put(userId,this); // 加入set中
            addOnlineCount(); // 在线数加1
        }
        System.out.println("在线人数：" + WebSocketServer.onlineCount);
    }

    //连接关闭调用的方法
    @OnClose
    public void onClose() {
        System.out.println("客户端关闭连接："+this.userId);
        userList.remove(this.userId); // 从map中删除
        subOnlineCount(); // 在线数减1
        System.out.println("在线人数：" + WebSocketServer.onlineCount);
    }

    // 收到客户端消息后调用的方法
    @OnMessage
    public void onMessage(String message, Session session) {
        if(StringUtils.isNotBlank(this.userId)){
            // 将接收方和发送方获取下来
            String[] users = message.split("\\|");
            if(userList.containsKey(users[1])) {
                // 收到消息后可以去做一些具体的业务处理在推送，此处直接推送
                sendMessage(users[1], users[0]);
            } else {
                System.out.println("对方未上线：" + users[1]);
            }
        }else{
            System.out.println("当前客户未登陆：" + this.userId);
        }
    }

    //发生错误时调用
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessage(String userId, String message){
        try {
            if(!StringUtils.isNotBlank(userId)){
                System.out.println("客户ID不能为空");
                return ;
            }
            for(Map.Entry<String, WebSocketServer> entry : userList.entrySet()){
                if(entry.getKey().equals(userId)){
                    entry.getValue().getSession().getBasicRemote().sendText(message);
                    System.out.println("推送给用户【"+entry.getKey()+"】消息成功，消息为：" + message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 群发消息
    public void sendMessage(List<String> userIds, String message){
        try {
            if(userIds == null || userIds.size() == 0){
                System.out.println("客户ID不能为空");
                return ;
            }
            for(Map.Entry<String, WebSocketServer> entry : userList.entrySet()){
                if(userIds.contains(entry.getKey())){
                    entry.getValue().getSession().getBasicRemote().sendText(message);
                    System.out.println("推送给用户【"+entry.getKey()+"】消息成功，消息为：" + message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAll(String message){
        try {
            if(!StringUtils.isNotBlank(userId)){
                System.out.println("客户ID不能为空");
                return ;
            }
            for(Map.Entry<String, WebSocketServer> entry : userList.entrySet()){
                entry.getValue().getSession().getBasicRemote().sendText(message);
                System.out.println("推送给用户【"+entry.getKey()+"】消息成功，消息为：" + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取连接人数
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
    //连接人数加一
    public static synchronized void addOnlineCount() {
        onlineCount+=1;
    }
    //连接人数减一
    public static synchronized void subOnlineCount() {
        if(onlineCount > 0){
            onlineCount-=1;
        }
    }

    public Session getSession() {
        return session;
    }

}
