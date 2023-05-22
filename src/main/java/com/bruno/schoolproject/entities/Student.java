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
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String studentName;
    private Integer age;

    @JsonIgnore
    @OneToMany(mappedBy = "student",cascade = CascadeType.REMOVE)
    private Set<CourseRegistration> courses;
}
