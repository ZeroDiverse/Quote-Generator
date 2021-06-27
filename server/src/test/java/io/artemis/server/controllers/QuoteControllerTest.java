package io.artemis.server.controllers;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import io.artemis.server.dto.QuoteResponse;
import io.artemis.server.entities.Quote;
import io.artemis.server.exceptions.QuoteNotFoundException;
import io.artemis.server.services.QuoteService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = QuoteController.class)
public class QuoteControllerTest {
    public static final String MOCK_AUTHOR_NAME = "MOCK AUTHOR NAME";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuoteService quoteService;

    private List<QuoteResponse> quotes = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        quotes.add(QuoteResponse.builder().id(1L).content("Mock Quote content 1").build());
        quotes.add(QuoteResponse.builder().id(2L).content("Mock Quote content 2").build());
        quotes.add(QuoteResponse.builder().id(3L).content("Mock Quote content 3").build());
    }

    @AfterEach
    public void tearDown() {
        quotes = new ArrayList<>();
    }

    @Test
    public void testGetAllQuotes() throws Exception {

        given(quoteService.findAllQuotes()).willReturn(quotes);

        MvcResult result = mockMvc.perform(get("/api/v1/quotes"))
                .andExpect(status().isOk())
                .andReturn();

        String resultBody = result.getResponse().getContentAsString();
        Type listType = new TypeToken<ArrayList<QuoteResponse>>() {
        }.getType();

        List<QuoteResponse> responseDto
                = new Gson().fromJson(resultBody, listType);
        assertThat(responseDto).isEqualTo(quotes);
    }

    @Test
    public void testGetRandomQuote() throws Exception {

        given(quoteService.findRandomQuote()).willReturn(quotes.get(0));

        MvcResult result = mockMvc.perform(get("/api/v1/quotes/random"))
                .andExpect(status().isOk())
                .andReturn();

        String resultBody = result.getResponse().getContentAsString();

        QuoteResponse responseDto
                = new Gson().fromJson(resultBody, QuoteResponse.class);
        assertThat(responseDto).isEqualTo(quotes.get(0));
    }

    @Test
    public void testGetRandomQuoteByAuthorName() throws Exception {

        given(quoteService.findRandomQuoteFromAuthorName(MOCK_AUTHOR_NAME)).willReturn(quotes.get(0));

        MvcResult result = mockMvc.perform(get("/api/v1/quotes/random?author="+MOCK_AUTHOR_NAME))
                .andExpect(status().isOk())
                .andReturn();

        String resultBody = result.getResponse().getContentAsString();

        QuoteResponse responseDto
                = new Gson().fromJson(resultBody, QuoteResponse.class);
        assertThat(responseDto).isEqualTo(quotes.get(0));
    }

    @Test
    public void testGetRandomQuoteByAuthorName_WillReturnStatusOf404_IfQuoteNotFound() throws Exception {
        given(quoteService.findRandomQuoteFromAuthorName(MOCK_AUTHOR_NAME)).willThrow(QuoteNotFoundException.class);

        mockMvc.perform(get("/api/v1/quotes/random?author="+MOCK_AUTHOR_NAME))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }
}
