package com.greenfox.nori.peertopeerchatapp.controller;

import com.greenfox.nori.peertopeerchatapp.model.IncomingJSON;
import com.greenfox.nori.peertopeerchatapp.model.Message;
import com.greenfox.nori.peertopeerchatapp.model.StatusError;
import com.greenfox.nori.peertopeerchatapp.model.StatusOk;
import com.greenfox.nori.peertopeerchatapp.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by Nóra on 2017. 05. 22..
 */
@org.springframework.web.bind.annotation.RestController
@CrossOrigin("*")
public class RestController {

  @Autowired
  Service service;

  @ExceptionHandler()
  public StatusError exception(){
    return new StatusError("error", "");
  }

  @PostMapping("/api/message/receive")
  public ResponseEntity<?> receiveMessage(@RequestBody IncomingJSON incoming) {
    String missingField = "";
    Message message = incoming.getMessage();
    if (message.getId() == 0) {
      missingField += "message.id, ";
    }
    if (message.getUsername() == null) {
      missingField += "message.username, ";
    }
    if(message.getText() == null) {
      missingField += "message.text, ";
    }
    if(message.getTimestamp() == null) {
      missingField += "message.timestamp, ";
    }
    if(incoming.getClient().getId() == null) {
    missingField += "client.id";
    }
    if(missingField.length() != 0) {
      StatusError error = new StatusError("error", "Missing field(s): " + missingField);
      return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    } else {
      service.saveMessage(incoming.getMessage());
      StatusOk statusOk = new StatusOk("ok");
      return new ResponseEntity<>(statusOk, HttpStatus.OK);
    }
  }
}
