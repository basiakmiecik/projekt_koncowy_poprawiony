package com.application.project.Repository;


import com.application.project.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserRepository {


    @PersistenceContext
    private EntityManager entityManager;

@Transactional
    public void saveUser(User user){
    entityManager.persist(user);
}

public List<User> findAll(){
    String jpql="select u from User u";
    Query query=entityManager.createQuery(jpql);
    List<User> userList=query.getResultList();
    return userList;
}


public User findByLogin(String login){
   return entityManager.find(User.class, login);
}

public User findByID(Long id){
    return entityManager.find(User.class, id);
}
}
