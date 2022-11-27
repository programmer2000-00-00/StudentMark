package com.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
@Getter
@Setter
@Entity
@Table(name="studentCourseMark")
public class StudentCourseMarkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentEntity student;



    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private CourseEntity course;


    @Column
    private  Integer mark;

    @Column
    private LocalDate createdDate;
}
