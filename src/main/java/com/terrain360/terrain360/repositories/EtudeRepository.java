package com.terrain360.terrain360.repositories;

import com.terrain360.terrain360.entities.Etude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EtudeRepository extends JpaRepository<Etude, Long> {

    List<Etude> findBySuperviseurId(Long superviseurId);

    @Query("SELECT e FROM Etude e JOIN e.enqueteurs enq WHERE enq.id = :employeId")
    List<Etude> findByEnqueteursId(@Param("employeId") Long employeId);
}