package io.artemis.server.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String slug;

    private String name;

    @ElementCollection
    private List<String> aka = new ArrayList<>();

    private String link;

    private String bio;

    private String description;

    @Column(name = "date_added", columnDefinition = "DATE")
    private LocalDate dateAdded = LocalDate.now();

    @Column(name = "date_modified", columnDefinition = "DATE")
    private LocalDate dateModified = LocalDate.now();
}
