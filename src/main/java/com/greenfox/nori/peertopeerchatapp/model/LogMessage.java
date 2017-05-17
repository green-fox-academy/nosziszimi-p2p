package com.greenfox.nori.peertopeerchatapp.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Nóra on 2017. 05. 17..
 */
public class LogMessage {

  String path;
  String method;
  LocalDateTime dateTime;
  String logLevel;
  String requestData;

  public LogMessage() {

  }

  public LogMessage(String path, String method, String logLevel) {
    this.path = path;
    this.method = method;
    dateTime = LocalDateTime.now();
    this.logLevel = logLevel;
    //this.requestData = requestData;
  }

  public LogMessage(String path, String method, String logLevel, String param) {
    this.path = path;
    this.method = method;
    dateTime = LocalDateTime.now();
    this.logLevel = logLevel;
    requestData = param;
  }

  @Override
  public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.SSS");
      return dateTime.format(formatter)
              + " " + logLevel
              + " Request " + path
              + " " + method
              + " " + requestData;
  }
}
