package io.artemis.server.controllers;

import io.artemis.server.dto.QuoteResponse;
import io.artemis.server.services.QuoteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quotes")
@Slf4j
@AllArgsConstructor
public class QuoteController {

    private final QuoteService quoteService;

    @GetMapping(value = {"/", ""})
    public List<QuoteResponse> findAllQuotes() {
        return quoteService.findAllQuotes();
    }

    @GetMapping(value = {"/random"})
    public QuoteResponse findRandomQuote(@RequestParam(name = "author", required = false) String author) {
        if(author != null && !author.equals("")){
            return quoteService.findRandomQuoteFromAuthorName(author);
        }
        return quoteService.findRandomQuote();
    }
}
