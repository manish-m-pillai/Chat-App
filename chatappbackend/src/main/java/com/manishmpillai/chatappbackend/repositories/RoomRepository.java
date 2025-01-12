package com.manishmpillai.chatappbackend.repositories;

import com.manishmpillai.chatappbackend.entities.Room;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, ObjectId> {

    Room findByRoomId(String roomId);

}
