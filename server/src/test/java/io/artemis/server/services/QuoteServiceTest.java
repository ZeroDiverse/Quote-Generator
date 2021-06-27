package io.artemis.server.services;

import io.artemis.server.dto.QuoteResponse;
import io.artemis.server.entities.Quote;
import io.artemis.server.exceptions.QuoteNotFoundException;
import io.artemis.server.mappers.QuoteMapper;
import io.artemis.server.repositories.QuoteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class QuoteServiceTest {
    public static final String MOCK_AUTHOR = "Mock author";
    @Mock
    private QuoteRepository quoteRepository;

    @Mock
    private QuoteMapper quoteMapper;

    private QuoteService quoteService;

    private List<Quote> mockQuotes = new ArrayList<>();

    @BeforeEach
    public void setUp() throws Exception {
        quoteService = new QuoteService(quoteRepository, quoteMapper);
        mockQuotes.add(Quote.builder().id(1L).content("Mock quote 1").build());
        mockQuotes.add(Quote.builder().id(2L).content("Mock quote 3").build());
        mockQuotes.add(Quote.builder().id(3L).content("Mock quote 3").build());
    }

    @AfterEach
    public void tearDown(){
        mockQuotes = new ArrayList<>();
    }

    @Test
    public void testQuoteServiceGetAllWillReturnAllTheServices(){
        given(quoteRepository.findAll()).willReturn(mockQuotes);

        List<QuoteResponse> resultQuotes = quoteService.findAllQuotes();

        assertThat(resultQuotes).isEqualTo(mockQuotes.stream().map(quoteMapper::quoteToQuoteResponse).collect(Collectors.toList()));
    }

    @Test
    public void testQuoteServiceFindRandomQuote_ReturnRandomQuote(){
        given(quoteRepository.findRandomQuote()).willReturn(mockQuotes.get(0));

        QuoteResponse resultQuote = quoteService.findRandomQuote();

        assertThat(resultQuote).isEqualTo(quoteMapper.quoteToQuoteResponse(mockQuotes.get(0)));
    }

    @Test
    public void testQuoteServiceFindRandomQuoteFromAuthorName_ReturnRandomQuoteFromAuthorName_IfThereIsOneInDatabase(){
        given(quoteRepository.findRandomQuoteFromAuthorName(MOCK_AUTHOR)).willReturn(mockQuotes.get(0));

        QuoteResponse resultQuote = quoteService.findRandomQuoteFromAuthorName(MOCK_AUTHOR);

        assertThat(resultQuote).isEqualTo(quoteMapper.quoteToQuoteResponse(mockQuotes.get(0)));
    }

    @Test
    public void testQuoteServiceFindRandomQuoteFromAuthorName_ReturnAnException_IfThereIsNoKindOfResource(){
        given(quoteRepository.findRandomQuoteFromAuthorName(MOCK_AUTHOR)).willReturn(null);

        try {
            quoteService.findRandomQuoteFromAuthorName(MOCK_AUTHOR);
            fail("It should throw QuoteNotFoundException");
        } catch (QuoteNotFoundException e) {
            assertThat(e)
                    .isInstanceOf(QuoteNotFoundException.class)
                    .hasMessage("Can't find the corresponding quote");
        }
    }

}
