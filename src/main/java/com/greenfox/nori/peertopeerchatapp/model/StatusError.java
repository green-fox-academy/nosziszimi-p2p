package com.greenfox.nori.peertopeerchatapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Created by Nóra on 2017. 05. 22..
 */
@Component
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusError {
  String status;
  String message;
}
