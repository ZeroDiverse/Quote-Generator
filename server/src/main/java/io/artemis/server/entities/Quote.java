package io.artemis.server.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Content of the quote, type: TEXT
    @Column(columnDefinition="TEXT")
    private String content;

    //Author of the quote
    @ManyToOne
    private Author author;


    //Date first added the quote
    @Column(name = "date_added", columnDefinition = "DATE")
    private LocalDate dateAdded = LocalDate.now();

    //Date last modified the quote
    @Column(name = "date_modified", columnDefinition = "DATE")
    private LocalDate dateModified = LocalDate.now();

    @Transient
    public Integer getQuoteContentLength(){
        return content.length();
    }
}
