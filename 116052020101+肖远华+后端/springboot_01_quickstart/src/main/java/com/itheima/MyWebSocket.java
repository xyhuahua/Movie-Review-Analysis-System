package com.itheima;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.domain.Message;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Auther: morou
 * @Description: websocket的具体实现类
 * 使用springboot的唯一区别是要@Component声明下，而使用独立容器是由容器自己管理websocket的，
 * 但在springboot中连容器都是spring管理的。
虽然@Component默认是单例模式的，但springboot还是会为每个websocket连接初始化一个bean，
所以可以用一个静态set保存起来。
 */
@ServerEndpoint(value = "/websocket/chat/{roomName}/{username}")
@Component
public class MyWebSocket {
    //用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();
    private static final Map<String, Set<Session>> rooms = new ConcurrentHashMap();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private String nickname;
    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session,@PathParam("roomName") String roomName,@PathParam("username") String username) throws UnsupportedEncodingException {
        if (!rooms.containsKey(roomName)) {
            // 创建房间不存在时，创建房间
            Set<Session> room = new HashSet<>();
            // 添加用户
            room.add(session);
            rooms.put(roomName, room);
        } else {
            // 房间已存在，直接添加用户到相应的房间
            rooms.get(roomName).add(session);
        }
        System.out.println("有新连接加入:"+roomName+",当前在线人数为" + rooms.get(roomName).size());
//        session.getAsyncRemote().sendText("恭喜您成功连接上WebSocket-->当前房间"+roomName+"在线人数为："+rooms.get(roomName).size());
    }


    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("roomName") String roomName, Session session) {
        rooms.get(roomName).remove(session);  //从set中删除
        System.out.println("有一连接关闭！当前在线人数为" + webSocketSet.size());
    }
    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session,@PathParam("roomName") String roomName,@PathParam("username") String username) throws Exception {
        System.out.println("来自客户端的消息-->"+nickname+": " + message);

        JSONObject json = JSONObject.parseObject(message);

        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat dateFormat= new SimpleDateFormat("HH:mm");
        System.out.println(dateFormat.format(calendar.getTime()));
        String time = dateFormat.format(calendar.getTime());

        String headId1 = "http://localhost:8080/file/download/"+ json.getString("headId");

        //群发消息
        Message message1 = new Message(session.getId(),username,json.getString("content"),time,headId1);

        ObjectMapper objectMapper = new ObjectMapper();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        JsonFactory jsonFactory = objectMapper.getFactory();
        JsonGenerator generator = jsonFactory.createGenerator(bout);
        generator.writeStartObject();
        generator.writeNumberField("code", 200);
        generator.writeObjectField("msg", message1);
        generator.writeEndObject();
        generator.flush();
        generator.close();

        String s = bout.toString();
        System.out.println(s);

        broadcast(roomName,s);
    }

    public static void broadcast(String roomName, String msg) throws Exception {
        for (Session item : rooms.get(roomName)) {
            item.getAsyncRemote().sendText(msg);
        }
    }


    /**
     * 发生错误时调用
     *
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

}