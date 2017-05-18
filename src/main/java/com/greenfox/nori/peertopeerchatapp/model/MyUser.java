package com.greenfox.nori.peertopeerchatapp.model;

import javax.persistence.Entity;
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
@NoArgsConstructor
@AllArgsConstructor
public class MyUser {
  @Id
  long id;
  String userName;


}
