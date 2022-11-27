package com.example.controller;

import com.example.dto.CourseDTO;
import com.example.dto.StudentCourseDTO;
import com.example.entity.StudentCourseMarkEntity;
import com.example.service.StudentMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("studentCourse/")
public class StudentCourseMarkController {
    @Autowired
    private StudentMarkService service;


    @PostMapping("/create")
    public ResponseEntity<?> createCourse(@RequestBody StudentCourseDTO dto){
        StudentCourseDTO res=service.create(dto);
        return ResponseEntity.ok(res);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateCourse(@RequestBody StudentCourseDTO dto,@RequestParam("id") Integer id){
        String res=service.update(dto,id);
        return ResponseEntity.ok(res);
    }
    @GetMapping("/get")
    public ResponseEntity<?> getCourse(@RequestParam("id") Integer id){
        StudentCourseDTO studentCourseDTO =service.getById(id);
        return ResponseEntity.ok(studentCourseDTO);
    }
    @GetMapping("/getDetail")
    public ResponseEntity<?> getDetail(@RequestParam("id") Integer id){
        StudentCourseDTO studentCourseDTO =service.getByDetailInteger(id);
        return ResponseEntity.ok(studentCourseDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id") Integer id){
        String res =service.delete(id);
        return ResponseEntity.ok(res);
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
       List<StudentCourseDTO> getList =service.getAll();
        return ResponseEntity.ok(getList);
    }
    @GetMapping("/get/Mark")
    public ResponseEntity<?> getMark(@RequestParam("id") Integer id,@RequestParam("date") String date){
        List<StudentCourseDTO> getList =service.getMark(date, id);
        return ResponseEntity.ok(getList);
    }
    @GetMapping("/get/between")
    public ResponseEntity<?> getMark(@RequestParam("id") Integer id,@RequestParam("date1") String date1,@RequestParam("date2") String date2){
        List<StudentCourseDTO> getList =service.getMarkBetween(date1,date2,id);
        return ResponseEntity.ok(getList);
    }
    @GetMapping("/get/all/desc")
    public ResponseEntity<?> getAlldesc(@RequestParam("id") Integer id){
        List<StudentCourseDTO> studentCourseDTO =service.getMarkAll(id);
        return ResponseEntity.ok(studentCourseDTO);
    }

    @GetMapping("/get/all/course")
    public ResponseEntity<?> getAllcoursedesc(@RequestParam("id") Integer id,@RequestParam("courseId") Integer courseId){
        List<StudentCourseDTO> studentCourseDTO =service.getMarkcourse(id,courseId);
        return ResponseEntity.ok(studentCourseDTO);
    }
}
