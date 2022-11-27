package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {

        private Integer id;

        private  String name;

        private  String surname;

        private  Integer level;

        private Integer age;

        private String gender;

        private LocalDate createdDate;

    }

