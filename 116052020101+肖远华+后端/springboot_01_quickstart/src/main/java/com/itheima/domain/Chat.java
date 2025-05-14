package com.itheima.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    private String _id;
    private String roomid;
    private String username;
    private String content;
    private String time;
    private String headId;
    private String type;

    public Chat(String roomid, String username, String content, String time, String headId, String type) {
        this.roomid = roomid;
        this.username = username;
        this.content = content;
        this.time = time;
        this.headId = headId;
        this.type = type;
    }
}
