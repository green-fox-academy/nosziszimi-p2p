package com.greenfox.nori.peertopeerchatapp.repository;

import com.greenfox.nori.peertopeerchatapp.model.MyUser;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Nóra on 2017. 05. 18..
 */

@Repository
public interface UserRepository extends CrudRepository<MyUser, Long>{
  List<MyUser> findAll();

  MyUser findByUserName(String userName);
}
