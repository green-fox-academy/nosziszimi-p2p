package com.greenfox.nori.peertopeerchatapp.service;

import com.greenfox.nori.peertopeerchatapp.model.MyUser;
import com.greenfox.nori.peertopeerchatapp.repository.UserRepository;
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
}
