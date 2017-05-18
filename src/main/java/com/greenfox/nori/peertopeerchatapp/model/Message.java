package com.greenfox.nori.peertopeerchatapp.model;

import com.greenfox.nori.peertopeerchatapp.service.Service;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Nóra on 2017. 05. 18..
 */
@Entity
@Getter @Setter
public class Message {

  private String username;
  private String text;
  private Timestamp timestamp;
  @Id
  long id;

  public Message() {
    //long randomId = service.getRandomId();
    //while (service.existingId(randomId)) {
      //randomId = service.getRandomId();
    //}
    id = 1000000 + (long)(Math.random()*8999999);
    timestamp = new Timestamp(System.currentTimeMillis());
  }

  public Message(String username, String text) {
    this();
    this.username = username;
    this.text = text;
  }

  public Message(String username, String text, long id) {
    this.username = username;
    this.text = text;
    this.id = id;
  }

}
