package com.psjw.kakaoshareprototype.repository;

import com.psjw.kakaoshareprototype.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName : com.psjw.kakaoshareprototype.repository
 * fileName : UserRepository
 * author : psjw
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email); //JPA QueryMethod
}
