package com.application.project.Repository;


import com.application.project.model.Person;
import com.application.project.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class PersonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void savePerson(Person person){
        entityManager.persist(person);
    }

    @Transactional
    public void removePerson(Person person){
        entityManager.remove(person);
    }

    public List<Person> findAll() {
        String jpql = "select p from Person p";
        Query query = entityManager.createQuery(jpql);
        List<Person> personList = query.getResultList();
        return personList;
    }

    public List<Person>findByUserId(Long userid){
       // String jpql="select p from Person p";//where p.user_id =:user_id"
        String jpql="select p from Person p where user_id= :userid";
        TypedQuery<Person> query=entityManager.createQuery(jpql, Person.class);
        query.setParameter("userid",userid);
        List<Person> personID=query.getResultList();
        return personID;
    }

    public Person findById(Long id)
    {
        return entityManager.find(Person.class, id);
    }
}


