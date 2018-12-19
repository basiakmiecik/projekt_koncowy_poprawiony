package com.application.project.Repository;

import com.application.project.model.Giving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface GivingRepository extends JpaRepository<Giving, Long> {


    @Transactional
    @Query("delete from Giving g")
    void deleteById(Long id);

    List findAll();

    Giving findByid(Long id);

    List<Giving>findByPersonId(Long personid);


}
