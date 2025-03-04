package com.nt.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.Model.User;

public interface UserRepository extends JpaRepository<User,Integer>{

 Optional<User>	findUserByEmail(String email);
}
