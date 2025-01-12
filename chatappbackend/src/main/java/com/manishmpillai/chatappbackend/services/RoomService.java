package com.manishmpillai.chatappbackend.services;

import com.manishmpillai.chatappbackend.entities.Room;
import com.manishmpillai.chatappbackend.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room findByRoomId(String roomId){
        return roomRepository.findByRoomId(roomId);
    }

    public Room saveRoom(Room room){
        return roomRepository.save(room);
    }

}
