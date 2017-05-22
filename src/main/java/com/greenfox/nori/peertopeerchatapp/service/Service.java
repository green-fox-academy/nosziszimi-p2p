package com.greenfox.nori.peertopeerchatapp.service;

import com.greenfox.nori.peertopeerchatapp.model.Message;
import com.greenfox.nori.peertopeerchatapp.model.MyUser;
import com.greenfox.nori.peertopeerchatapp.repository.MessageRepository;
import com.greenfox.nori.peertopeerchatapp.repository.UserRepository;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Nóra on 2017. 05. 17..
 */
@Component
public class Service {

  String envVarLogLevel = System.getenv("CHAT_APP_LOGLEVEL");

  @Autowired
  UserRepository userRepo;

  @Autowired
  MessageRepository messageRepo;

  public List<MyUser> findAll() {
    return userRepo.findAll();
  }

  public boolean isAnyUser() {
    if(findAll().size() != 0) {
      return true;
    } else {
      return false;
    }
  }

  public void save(MyUser user) {
    userRepo.save(user);
  }

  public void updateUserName(String userName, String newUserName) {
    if (userRepo.findByUserName(newUserName) != null) {

    } else {
      MyUser user = userRepo.findByUserName(userName);
      user.setUserName(newUserName);
      userRepo.save(user);
    }
  }

  public MyUser findUser() {
    return userRepo.findOne(1L);
  }

  public long getRandomId() {
    long random = 1000000 + (long)(Math.random()*8999999);
    return random;
  }

  public boolean existingId(Long randomId) {
    if(messageRepo.exists(randomId)) {
      return true;
    } else
      return false;
  }

  public List<Message> findAllMessage() {
    return messageRepo.findAll();
  }

  public List<Message> findAllMessageDesc() {
    return messageRepo.findAllByOrderByTimestampDesc();
  }

  public void saveMessage(Message message) {
    messageRepo.save(message);
  }

  public Message newMessage(String username, String text) {
    long randomId = getRandomId();
    while (existingId(randomId)) {
    randomId = getRandomId();
    }
    return new Message(username, text, randomId, new Timestamp(System.currentTimeMillis()));
  }
}
