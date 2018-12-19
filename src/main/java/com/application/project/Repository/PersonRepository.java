package com.application.project.Repository;


import com.application.project.model.Giving;
import com.application.project.model.Person;
import com.application.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Transactional
    void deleteById(Long id);

    List<Person> findAll();

    List<Person>findByUserId(Long userid);

    Person findByid(Long id);

    /*@Modifying
    @Query()
    void update(Person person);*/


}


