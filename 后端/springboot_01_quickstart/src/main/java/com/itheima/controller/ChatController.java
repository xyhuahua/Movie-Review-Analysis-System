package com.itheima.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.dao.ChatDao;
import com.itheima.dao.UserDao;
import com.itheima.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class ChatController {
    @Autowired
    private ChatDao chatDao;

//    @Autowired
//    HttpServletRequest request;

    @GetMapping(value = "/chat/get")
    public String getAllChats(@RequestParam Map<String, Object> params) throws IOException {

        String roomid = (String) params.get("roomId");
        String type = (String)params.get("type");
        String size = (String) params.get("size");

        System.out.println("fav"+roomid);

        List<Chat> chats = chatDao.findAll(roomid,type,Integer.valueOf(size));

        System.out.println(chats);

        ObjectMapper objectMapper = new ObjectMapper();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        JsonFactory jsonFactory = objectMapper.getFactory();
        JsonGenerator generator = jsonFactory.createGenerator(bout);
        generator.writeStartObject();
        generator.writeNumberField("code", 0);
        generator.writeStringField("msg", "success");
        generator.writeObjectField("chats", chats);
        generator.writeNumberField("count", chats.size());
        generator.writeEndObject();
        generator.flush();
        generator.close();

        String s = bout.toString();
        System.out.println(s);

        return s;
    }


    @GetMapping(value = "/chat/getLen")
    public String getChatLen(@RequestParam Map<String, Object> params) throws IOException {

        String roomid = (String) params.get("roomId");
        String type = (String)params.get("type");

        System.out.println("fav"+roomid);

        long count = chatDao.getLen(roomid,type);

        ObjectMapper objectMapper = new ObjectMapper();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        JsonFactory jsonFactory = objectMapper.getFactory();
        JsonGenerator generator = jsonFactory.createGenerator(bout);
        generator.writeStartObject();
        generator.writeNumberField("code", 0);
        generator.writeStringField("msg", "success");
        generator.writeNumberField("count", count);
        generator.writeEndObject();
        generator.flush();
        generator.close();

        String s = bout.toString();
        System.out.println(s);

        return s;
    }

    @GetMapping(value = "/chat/add")
    public String AddChat(@RequestParam Map<String, Object> params) throws IOException {
        String roomid = (String) params.get("roomId");
        String username = (String) params.get("username");
        String content = (String) params.get("content");
//        String time = (String) params.get("time");
        String headId = (String) params.get("headId");
        String type = (String) params.get("type");

        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        System.out.println(dateFormat.format(calendar.getTime()));
        String time = dateFormat.format(calendar.getTime());

        Chat chat = new Chat(roomid,username,content,time,headId,type);
        System.out.println(chat);
        chatDao.addChat(chat);
        String msg = "success";

        ObjectMapper objectMapper = new ObjectMapper();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        JsonFactory jsonFactory = objectMapper.getFactory();
        JsonGenerator generator = jsonFactory.createGenerator(bout);
        generator.writeStartObject();
        generator.writeNumberField("code", 200);
        generator.writeStringField("msg", msg);
        generator.writeEndObject();
        generator.flush();
        generator.close();

        String s = bout.toString();
        System.out.println(s);

        return s;
    }


}
