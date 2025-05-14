package com.itheima.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message{
    private String sessionId;
    private String username;
    private String message;
    private String time;
    private String headId;
}
