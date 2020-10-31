package com.botscrew.university.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id", "lectors", "headOfDepartment"})
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "department_lector",
        joinColumns = @JoinColumn(name = "lector_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "department_id", referencedColumnName = "id"))
    private Set<Lector> lectors;

    @OneToOne
    @JoinColumn(name = "lector_id", referencedColumnName = "id")
    private Lector headOfDepartment;
}
