package com.example.repository;

import com.example.entity.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CourseRepository extends PagingAndSortingRepository<CourseEntity,Integer> {

    @Query("FROM CourseEntity where name=:n")
    List<CourseEntity> findByName(@Param("n") String name);
    @Query("FROM CourseEntity where price=:n")
    List<CourseEntity> findByPrice(@Param("n") Double price);
    List<CourseEntity>findByDuration(Integer duration);

    @Query("FROM CourseEntity where price between :n and :m ")
    List<CourseEntity> findByPriceBetween(@Param("n") Double price1,@Param("m") Double price2);
    @Query("FROM CourseEntity where createdDate between :n and :m ")
    List<CourseEntity> findByCreatedDateBetween(@Param("n") LocalDate date1,@Param("m") LocalDate date2);

    @Query("select e FROM CourseEntity as e where e.price=:n")
    Page<CourseEntity> findAllPrice( @Param("n") Double price,Pageable pageable);

    @Query("select c FROM CourseEntity as c where c.price between :from and :to ")
    Page<CourseEntity> findByPriceBetweenSort(@Param("from") Double from ,@Param("to") Double to,Pageable pageable);
//    Page<CourseEntity> findAllByPrice(Pageable pageable, Double price);
//
//    List<CourseEntity> findByPriceBetween(Double price1, Double price2);

}
