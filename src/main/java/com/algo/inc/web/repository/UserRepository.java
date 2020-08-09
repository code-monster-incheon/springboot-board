package com.algo.inc.web.repository;

import com.algo.inc.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByAccount(String account);

}
