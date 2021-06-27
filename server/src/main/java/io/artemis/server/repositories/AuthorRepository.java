package io.artemis.server.repositories;

import io.artemis.server.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "SELECT * FROM Author A WHERE A.name=:authorName  ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    public Author findByName(@Param("authorName") String authorName);
}
