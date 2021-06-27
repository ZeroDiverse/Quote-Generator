package io.artemis.server.services;

import io.artemis.server.dto.QuoteResponse;
import io.artemis.server.entities.Quote;
import io.artemis.server.exceptions.QuoteNotFoundException;
import io.artemis.server.mappers.QuoteMapper;
import io.artemis.server.repositories.QuoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuoteService {
    private final QuoteRepository quoteRepository;
    private final QuoteMapper quoteMapper;

    public List<QuoteResponse> findAllQuotes(){
        return quoteRepository.findAll().stream().map(quoteMapper::quoteToQuoteResponse).collect(Collectors.toList());
    }

    public QuoteResponse findRandomQuote(){
        return quoteMapper.quoteToQuoteResponse(quoteRepository.findRandomQuote());
    }

    public QuoteResponse findRandomQuoteFromAuthorName(String authorName){
        Quote resultQuote = quoteRepository.findRandomQuoteFromAuthorName(authorName);
        if(resultQuote == null){
            throw new QuoteNotFoundException("Can't find the corresponding quote");
        }
        return quoteMapper.quoteToQuoteResponse(quoteRepository.findRandomQuoteFromAuthorName(authorName));
    }
}
