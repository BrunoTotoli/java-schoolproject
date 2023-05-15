package com.bruno.schoolproject.entities;


import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class CourseRegistrationID implements Serializable {


    private Long studentId;
    private Long courseId;

}
