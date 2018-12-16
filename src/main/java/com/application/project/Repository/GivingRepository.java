package com.application.project.Repository;

import com.application.project.model.Giving;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class GivingRepository {

    @PersistenceContext
    EntityManager entityManager;



    @Transactional
    public void saveGive(Giving giving){
        entityManager.persist(giving);
    }

    @Transactional
    public void removeGive(Giving giving){entityManager.remove(giving);}

    public List findAll(){
        String jpql = "select g from Giving g";
        Query query = entityManager.createQuery(jpql);
        List<Giving> givingList = query.getResultList();
        return givingList;
    }

    public Giving findByID(Long id){ return entityManager.find(Giving.class, id);}

    public List<Giving>findByPersonId(Long personid){
        String jpql="select g from Giving g where person_id= :personid";
        TypedQuery <Giving> query=entityManager.createQuery(jpql, Giving.class);
        query.setParameter("personid",personid);
        List<Giving> personGiveID=query.getResultList();
        return personGiveID;
    }


}
