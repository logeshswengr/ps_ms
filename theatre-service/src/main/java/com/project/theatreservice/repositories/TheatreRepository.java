package com.project.theatreservice.repositories;

import com.project.theatreservice.dtos.ShowResponse;
import com.project.theatreservice.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre,Long> {
    @Query("select new com.project.theatreservice.dtos.ShowsResponse( s.startTime, t.name, a.number)  from Show s JOIN s.audi a JOIN a.theatre t JOIN t.city c  where c.id = :cityId  and s.movieId= :movieId Order by s.startTime")
    List<ShowResponse> findTheatreByCityandMovie(@Param("cityId") String cityId, @Param("movieId") String movieId);
}
