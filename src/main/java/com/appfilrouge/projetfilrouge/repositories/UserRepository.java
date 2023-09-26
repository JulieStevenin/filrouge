package com.appfilrouge.projetfilrouge.repositories;

import com.appfilrouge.projetfilrouge.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByMail(String mail);

    User findUserById(Long id);
}
