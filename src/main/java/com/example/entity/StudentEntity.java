package com.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private  String name;
    @Column
    private  String surname;
    @Column
    private  Integer level;
    @Column
    private Integer age;
    @Column
    private String gender;
    @Column
    private LocalDate createdDate;

}
