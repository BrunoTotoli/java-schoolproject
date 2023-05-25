package com.bruno.schoolproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String courseName;

    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    private Set<CourseRegistration> students;

    @OneToOne
    @JsonIgnore
    private Teacher teacher;
}
