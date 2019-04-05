package com.example.demo.repository;

import com.example.demo.domain.StarWarsSpecies;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface StarWarsSpeciesRepository extends JpaRepository<StarWarsSpecies, UUID>  {

    @Query("select p from StarWarsSpecies p where p.name = :nameToSearch")
    StarWarsSpecies byName(@Param("nameToSearch") String nameToSearch);

    @Query("from StarWarsSpecies order by name")
    List<StarWarsSpecies> allPageable (Pageable pageable);
}
