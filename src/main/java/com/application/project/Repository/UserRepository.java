package com.application.project.Repository;


import com.application.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


  /*  @Transactional
    void saveUser(User user);*/

    List<User> findAll();

    User findByid(Long id);
}
