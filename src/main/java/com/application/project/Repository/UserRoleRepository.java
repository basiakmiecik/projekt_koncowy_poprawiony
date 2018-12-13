package com.application.project.Repository;


import com.application.project.model.UserRole;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class UserRoleRepository {
    @PersistenceContext
    EntityManager entityManager;


    @Transactional
    public void saveUserRole(UserRole userRole){
        entityManager.persist(userRole);
    }
}
