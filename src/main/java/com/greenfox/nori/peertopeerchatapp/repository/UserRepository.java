package com.greenfox.nori.peertopeerchatapp.repository;

import com.greenfox.nori.peertopeerchatapp.model.MyUser;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Nóra on 2017. 05. 18..
 */
public interface UserRepository extends CrudRepository<MyUser, Long>{

}
