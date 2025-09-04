package com.terrain360.terrain360.repositories;

import com.terrain360.terrain360.entities.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TacheRepository extends JpaRepository<Tache, Long> {
    List<Tache> findByEtudeId(Long etudeId);
}
