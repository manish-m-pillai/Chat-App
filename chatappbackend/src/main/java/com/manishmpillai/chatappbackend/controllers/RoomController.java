package com.manishmpillai.chatappbackend.controllers;

import com.manishmpillai.chatappbackend.entities.Message;
import com.manishmpillai.chatappbackend.entities.Room;
import com.manishmpillai.chatappbackend.services.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin("http://localhost:3000")
public class RoomController {

    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // Creating a Room
    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody String roomId){

        if(roomService.findByRoomId(roomId) != null){
            return ResponseEntity.badRequest().body("Room Already Exists !!!");
        }

        Room room = new Room();
        room.setRoomId(roomId);
        Room savedRoom = roomService.saveRoom(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRoom);
    }

    // Getting a Room
    @GetMapping("/{roomId}")
    public ResponseEntity<?> getRoom(@PathVariable String roomId){

        Room room = roomService.findByRoomId(roomId);
        if(room == null){
            return ResponseEntity.badRequest().body("Room not Found !!!");
        }

        return ResponseEntity.ok(room);
    }

    // Get Messages of Room
    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<Message>> getMessages(
            @PathVariable String roomId,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "20", required = false) int size
    ){

        Room room = roomService.findByRoomId(roomId);
        if(room == null){
            return ResponseEntity.badRequest().build();
        }

        // get messages
        List<Message> messages = room.getMessages();
        // pagination
        int start = Math.max(0, messages.size() - (page + 1) * size);
        int end = Math.min(messages.size(), start + size);
        List<Message> paginatedMessages = messages.subList(start, end);

        return ResponseEntity.ok(paginatedMessages);

    }
}
