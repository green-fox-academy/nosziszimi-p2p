package com.greenfox.nori.peertopeerchatapp.service;

import com.greenfox.nori.peertopeerchatapp.model.MyUser;
import com.greenfox.nori.peertopeerchatapp.repository.UserRepository;
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

  public Iterable<MyUser> findAll() {
    return userRepo.findAll();
  }

  public boolean isAnyUser() {
    int sum = 0;
    while(findAll().iterator().hasNext()) {
      findAll().iterator().next();
      sum++;
    }
    if (sum == 0) {
      return false;
    } else {
      return true;
    }
  }

  public void save(MyUser user) {
    userRepo.save(user);
  }
}
