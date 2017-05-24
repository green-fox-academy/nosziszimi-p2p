package com.greenfox.nori.peertopeerchatapp.controller;

import com.greenfox.nori.peertopeerchatapp.model.Client;
import com.greenfox.nori.peertopeerchatapp.model.ErrorMessage;
import com.greenfox.nori.peertopeerchatapp.model.IncomingJSON;
import com.greenfox.nori.peertopeerchatapp.model.LogMessage;
import com.greenfox.nori.peertopeerchatapp.model.Message;
import com.greenfox.nori.peertopeerchatapp.model.MyUser;
import com.greenfox.nori.peertopeerchatapp.model.StatusOk;
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
import org.springframework.web.client.RestTemplate;
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
  public String main(Model model,
          @RequestParam(value = "errorMessage", required = false) String errorMessage){
    LogMessage logMessage = new LogMessage
            ("/", "GET", "INFO", "errorMessage=" + errorMessage);
    System.out.println(logMessage);

    if(service.isAnyUser()) {
      ErrorMessage error = new ErrorMessage(errorMessage);
      model.addAttribute("error", error);
      MyUser user = service.findUser();
      model.addAttribute("user", user);


      model.addAttribute("messageList", service.findAllMessageDesc());
      return "index";
    } else {
      return "redirect:/enter";
    }
  }

  @PostMapping("/")
  public String updateName(@RequestParam(value = "userName", required = false) String userName) {
    System.out.println(new LogMessage
            ("/","POST", "INFO", "userName=" + userName));
    if (userName.length() == 0) {
      return "redirect:/?errorMessage=The username field was empty!";
    } else {
      service.updateUserName(service.findUser().getUserName(), userName);
      return "redirect:/";
    }
  }

  @GetMapping("/enter")
  public String getEnter(Model model,
          @RequestParam(value = "errorMessage", required = false) String errorMessage) {
    System.out.println(new LogMessage("/enter", "GET", "INFO"));
    ErrorMessage error = new ErrorMessage(errorMessage);
    model.addAttribute("error", error);
    return "enter";
  }

  @PostMapping("/enter")
  public String postEnter(@RequestParam ("userName") String userName) {
    System.out.println(new LogMessage
            ("/enter","POST", "INFO", "userName=" + userName));
    if (userName.length() == 0) {
      return "redirect:/enter?errorMessage=The username field was empty!";
    } else {
      MyUser u = new MyUser(userName);
      service.save(u);
      return "redirect:/";
    }
  }

  @PostMapping("/sendnew")
  public String newMessage(@RequestParam("username") String username,
          @RequestParam("text") String text) {
    Message message = service.newMessage(username, text);
    RestTemplate restTemplate = new RestTemplate();
    IncomingJSON incomingJSON = new IncomingJSON();
    incomingJSON.setMessage(message);
    Client client = new Client();
    client.setId(System.getenv("CHAT_APP_UNIQUE_ID"));
    incomingJSON.setClient(client);
    //restTemplate.postForObject("https://peertopeerchatapp.herokuapp.com/api/message/receive"
    //       , incomingJSON, StatusOk.class);
    restTemplate.postForObject("http://dorinagychatapp.herokuapp.com/api/message/receive"
            , incomingJSON, StatusOk.class);
    service.saveMessage(message);
    return "redirect:";
  }
}
