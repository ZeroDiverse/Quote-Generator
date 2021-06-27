package io.artemis.server.repositories;

import io.artemis.server.entities.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AuthorRepositoryTest {
    public static final String MOCK_AUTHOR_NAME = "MOCK AUTHOR NAME";

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testFindAuthorByName_WillReturnOneAuthor(){
        testEntityManager.persistAndFlush(Author.builder().name(MOCK_AUTHOR_NAME).build());
        Author author = authorRepository.findByName(MOCK_AUTHOR_NAME);

        assertThat(author).isNotNull();

        assertThat(author.getName()).isEqualTo(MOCK_AUTHOR_NAME);

    }
}
