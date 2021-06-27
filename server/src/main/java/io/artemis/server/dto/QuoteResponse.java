package io.artemis.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class QuoteResponse {
    private Long id;
    private String content;
    private String authorName;
    private String authorSlug;
    private int length;
    private LocalTime dateAdded;
    private LocalTime dateModified;
}
