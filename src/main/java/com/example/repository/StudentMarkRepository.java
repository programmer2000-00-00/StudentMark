package com.example.repository;

import com.example.entity.StudentCourseMarkEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface StudentMarkRepository extends PagingAndSortingRepository<StudentCourseMarkEntity,Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE StudentCourseMarkEntity set student.id=:student,course.id=:course,mark=:mark where id=:id")
    Integer update(@Param("student") Integer student,@Param("course") Integer course,@Param("mark") Integer mark,@Param("id") Integer id);

    @Query("SELECT s From StudentCourseMarkEntity s where s.student.id =:id and s.createdDate between  :date1 and :date2")
    List<StudentCourseMarkEntity>   findAllByMarkBetween( @Param("date1") LocalDate date1, @Param("date2") LocalDate date2,@Param("id") Integer id);

    @Query("SELECT s From StudentCourseMarkEntity s where s.student.id =:id and  s.createdDate=:date ")
    List<StudentCourseMarkEntity>   findAllByMark( @Param("date") LocalDate date,@Param("id") Integer id);
    @Query("SELECT s From StudentCourseMarkEntity s where s.student.id =:id  order by s.createdDate desc")
    List<StudentCourseMarkEntity>   findAllByMarkDesc(@Param("id") Integer id);

    @Query("SELECT s From StudentCourseMarkEntity s where s.student.id =:id and s.course.id=:courseId  order by s.createdDate desc")
    List<StudentCourseMarkEntity>findAllByMarkcourse(@Param("id") Integer id,@Param("courseId") Integer courseId);
}
