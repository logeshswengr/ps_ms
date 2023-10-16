package com.project.theatreservice.repositories;

import com.project.theatreservice.entity.Audi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudiRepository extends JpaRepository<Audi,Long> {
}
