package io.artemis.server.repositories;

import io.artemis.server.entities.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
    @Query(value = "SELECT * FROM Quote ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    public Quote findRandomQuote();

    @Query(value = "SELECT * FROM Quote Q JOIN Author A ON Q.author_id = A.id WHERE A.name=:authorName ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    public Quote findRandomQuoteFromAuthorName(@Param("authorName") String authorName);
}
