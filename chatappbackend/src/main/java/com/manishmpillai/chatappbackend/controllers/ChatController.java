package com.manishmpillai.chatappbackend.controllers;

import com.manishmpillai.chatappbackend.entities.Message;
import com.manishmpillai.chatappbackend.entities.Room;
import com.manishmpillai.chatappbackend.payload.MessageRequest;
import com.manishmpillai.chatappbackend.services.RoomService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;


@Controller
@CrossOrigin("http://localhost:3000")
public class ChatController {

    private RoomService roomService;

    public ChatController(RoomService roomService) {
        this.roomService = roomService;
    }

    @MessageMapping("/sendMessage/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public Message sendMessage(
            @DestinationVariable String roomId,
            @RequestBody MessageRequest request
    ){
        Room room = roomService.findByRoomId(request.getRoomId());
        Message message = new Message();
        message.setContent(request.getContent());
        message.setSender(request.getSender());
        message.setTimeStamp(LocalDateTime.now());

        if(room != null){
            room.getMessages().add(message);
            roomService.saveRoom(room);
        }
        else{
            throw new RuntimeException("Room not Found !!!");
        }

        return message;
    }
}
