package com.spring.trialproject.repository;

import com.spring.trialproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByPhoneNumber(Long phoneNumber);

    List<User> findByUserName(String userName);

    Boolean existsByPhoneNumber(Long phoneNumber);

    Boolean existsByUserName(String userName);
}
