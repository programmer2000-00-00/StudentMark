package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDTO {

        private Integer id;
        private  String name;
        private  Double price;
        private  Integer duration;
        private LocalDate createdDate;


}
