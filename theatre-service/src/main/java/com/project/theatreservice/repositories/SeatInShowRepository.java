package com.project.theatreservice.repositories;

import com.project.theatreservice.entity.SeatInShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatInShowRepository extends JpaRepository<SeatInShow,Long> {
}