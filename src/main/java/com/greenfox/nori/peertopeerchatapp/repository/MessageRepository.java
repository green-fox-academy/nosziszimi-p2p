package com.greenfox.nori.peertopeerchatapp.repository;

import com.greenfox.nori.peertopeerchatapp.model.Message;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Nóra on 2017. 05. 18..
 */
public interface MessageRepository extends CrudRepository<Message, Long> {

  public List<Message> findAll();
}
