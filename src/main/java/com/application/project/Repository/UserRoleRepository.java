package com.application.project.Repository;


import com.application.project.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {


}
