package com.example.dto;

import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentCourseDTO {

        private Integer id;

        private StudentDTO studentDTO;
        private Integer studentID;

        private CourseDTO courseDTO;
        private Integer courseID;

        private  Integer mark;

        private LocalDate createdDate;
    }

