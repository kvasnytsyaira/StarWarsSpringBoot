package com.example.demo.repository;

import com.example.demo.domain.StarWarsPerson;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface StarWarsPersonRepository extends JpaRepository<StarWarsPerson, UUID> {

    @Query("select p from StarWarsPerson p where p.name = :nameToSearch")
    StarWarsPerson byName(@Param("nameToSearch") String nameToSearch);

    @Query("from StarWarsPerson order by  name")
    List<StarWarsPerson> allPageable (Pageable pageable);

}
