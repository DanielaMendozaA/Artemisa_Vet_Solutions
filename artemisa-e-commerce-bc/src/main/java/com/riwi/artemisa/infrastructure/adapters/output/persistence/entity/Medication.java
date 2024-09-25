package com.riwi.artemisa.infrastructure.adapters.output.persistence.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "medications")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "medication_id")
    private List<Media> media;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "deleted")
    private Boolean deleted = false;

}



