package com.application.project.Repository;


import com.application.project.model.Getting;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

    @Repository
    public class GettingRepository {

        @PersistenceContext
    EntityManager entityManager;


    @Transactional
    public void saveGet(Getting getting){
        entityManager.persist(getting);
    }

        @Transactional
        public void removeGet(Getting getting){entityManager.remove(getting);}

        public Getting findByID(Long id){ return entityManager.find(Getting.class, id);}


        public List findAll(){
        String jpql = "select g from Getting g";
        Query query = entityManager.createQuery(jpql);
        List<Getting> gettingList = query.getResultList();
        return gettingList;
    }

    public List<Getting>findByPersonId(Long personid){
        String jpql="select g from Getting g where person_id= :personid";
        TypedQuery<Getting> query=entityManager.createQuery(jpql, Getting.class);
        query.setParameter("personid",personid);
        List<Getting> personGetID=query.getResultList();
        return personGetID;
    }


}
