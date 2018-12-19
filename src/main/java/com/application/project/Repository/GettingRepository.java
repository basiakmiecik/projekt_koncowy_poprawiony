package com.application.project.Repository;


import com.application.project.model.Getting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

    @Repository
    public interface GettingRepository extends JpaRepository<Getting, Long> {



    @Transactional
    void deleteById(Long id);

    Getting findByid(Long id);

    List findAll();

    List<Getting>findByPersonId(Long personid);

}
