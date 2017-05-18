package com.greenfox.nori.peertopeerchatapp.controller;

import com.greenfox.nori.peertopeerchatapp.model.LogMessage;
import com.greenfox.nori.peertopeerchatapp.model.MyUser;
import com.greenfox.nori.peertopeerchatapp.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Created by Nóra on 2017. 05. 17..
 */
@Controller
public class MainController {

  @Autowired
  Service service;

  @ExceptionHandler(NoHandlerFoundException.class)
  public String exception(NoHandlerFoundException e) {
    System.out.println("localizedMessage: " + e.getLocalizedMessage());
    e.printStackTrace();
    return "main";
  }

  @GetMapping("/")
  public String main(Model model){
    LogMessage logMessage = new LogMessage("/", "GET", "INFO");
    System.out.println(logMessage);
    if(service.isAnyUser()) {
      MyUser user = service.findUser();
      model.addAttribute("user", user);
      return "main";
    } else {
     return "redirect:/enter";
    }
  }

  @PostMapping("/")
  public String updateName(@RequestParam("userName") String userName) {
    System.out.println(new LogMessage
            ("/","POST", "INFO", "userName=" + userName));
    service.updateUserName(service.findUser().getUserName(), userName );
    return "redirect:/";
  }

  @GetMapping("/enter")
  public String getEnter() {
    System.out.println(new LogMessage("/enter", "GET", "INFO"));
    return "enter";
  }

  @PostMapping("/enter")
  public String postEnter(@RequestParam ("userName") String userName) {
    System.out.println(new LogMessage("/enter","POST", "INFO", "userName=" + userName));
    MyUser u = new MyUser(userName);
    service.save(u);
    return "redirect:/";
  }
}
