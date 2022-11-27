package com.example.repository;

import com.example.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface StudentRepository extends PagingAndSortingRepository<StudentEntity,Integer> {

List<StudentEntity> findByName(String name);

List<StudentEntity> findBySurname(String surname);

List<StudentEntity> findByLevel(Integer level);

List<StudentEntity> findByAge(Integer age);

List<StudentEntity> findByGender(String gender);
List<StudentEntity> findByCreatedDate(LocalDate date);

List<StudentEntity> findByCreatedDateBetween(LocalDate from,LocalDate to);


    Page<StudentEntity> findByLevelOrderById(Integer level, Pageable pageable);


    Page<StudentEntity> findByGenderOrderByCreatedDate(String gender, Pageable pageable);
}
