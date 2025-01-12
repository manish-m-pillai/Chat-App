package com.manishmpillai.chatappbackend.repositories;

import com.manishmpillai.chatappbackend.entities.Message;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, ObjectId> {

}
