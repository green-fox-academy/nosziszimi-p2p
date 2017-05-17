package com.greenfox.nori.peertopeerchatapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Nóra on 2017. 05. 17..
 */
@Controller
public class MainController {

  @RequestMapping("")
  public String main() {
    return "main";
  }
}
