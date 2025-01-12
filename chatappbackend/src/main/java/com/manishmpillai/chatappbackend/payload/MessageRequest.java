package com.manishmpillai.chatappbackend.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest {

    private String content;
    private String sender;
    private String roomId;

}
