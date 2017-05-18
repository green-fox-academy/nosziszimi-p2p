package com.greenfox.nori.peertopeerchatapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Nóra on 2017. 05. 17..
 */
@Entity
@Getter @Setter
public class MyUser {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  long id;
  String userName;

  public MyUser() {

  }

  public MyUser(String userName) {
    this.userName = userName;
  }


}
