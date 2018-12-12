package com.application.project.Repository;

import com.application.project.model.Login;
import com.application.project.model.Person;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class LoginRepository {

    @PersistenceContext
    EntityManager entityManager;


    @Transactional
    public void saveLogin(Login login){
        entityManager.persist(login);
    }

    @Transactional
    public void removeLogin(Login login){
        entityManager.remove(login);
    }

    public List<Login> loginList(){
        String jpql = "select l from Login l";
        Query query = entityManager.createQuery(jpql);
        List<Login> logins = query.getResultList();
        return logins;
    }
}
