package io.artemis.server.mappers;

import io.artemis.server.dto.QuoteResponse;
import io.artemis.server.entities.Quote;
import org.springframework.stereotype.Component;

/**
 * Quote mapper class for mapping quote with quote response
 */
@Component
public class QuoteMapper {
    /**
     * This function map quote to quote response
     * @param quote quote to be mapped
     * @return the quote's response
     */
    public QuoteResponse quoteToQuoteResponse(Quote quote){
        return QuoteResponse.builder().id(quote.getId()).content(quote.getContent()).length(quote.getQuoteContentLength()).authorName(quote.getAuthor().getName()).authorSlug(quote.getAuthor().getSlug()).build();
    }
}
