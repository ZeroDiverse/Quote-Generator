package io.artemis.server.mappers;

import io.artemis.server.dto.QuoteResponse;
import io.artemis.server.entities.Author;
import io.artemis.server.entities.Quote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuoteMapperTest {
    public static final String MOCK_QUOTE_CONTENT = "Mock quote content";
    public static final String MOCK_AUTHOR_NAME = "mock author name";
    public static final long ID = 1L;
    public static final String MOCK_AUTHOR_SLUG = "Mock Author Slug";
    private QuoteResponse quoteResponse;
    private Quote quote;
    private Author author;
    private QuoteMapper quoteMapper;

    @BeforeEach
    public void setUp(){
        quoteResponse = new QuoteResponse();
        author = Author.builder().id(1L).name(MOCK_AUTHOR_NAME).slug(MOCK_AUTHOR_SLUG).build();
        quote = Quote.builder().author(author).content(MOCK_QUOTE_CONTENT).id(ID).build();
        quoteMapper = new QuoteMapper();
    }

    @Test
    public void testQuoteMapperFromQuoteToQuoteResponse(){
        quoteResponse = quoteMapper.quoteToQuoteResponse(quote);
        assertThat(quoteResponse.getId()).isEqualTo(ID);
        assertThat(quoteResponse.getContent()).isEqualTo(MOCK_QUOTE_CONTENT);
        assertThat(quoteResponse.getAuthorName()).isEqualTo(MOCK_AUTHOR_NAME);
        assertThat(quoteResponse.getAuthorSlug()).isEqualTo(MOCK_AUTHOR_SLUG);
        assertThat(quoteResponse.getLength()).isEqualTo(MOCK_QUOTE_CONTENT.length());
    }
}
