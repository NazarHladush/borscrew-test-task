package com.botscrew.university.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Lector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private Double salary;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "degree_id")
    private Degree degree;
}
