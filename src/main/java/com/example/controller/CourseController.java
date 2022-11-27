package com.example.controller;

import com.example.dto.CourseDTO;
import com.example.dto.StudentDTO;
import com.example.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/createCourse")
    public ResponseEntity<?> createCourse(@RequestBody CourseDTO courseDTO){
        String str=courseService.createCourse(courseDTO);
        return ResponseEntity.ok(str);
    }
    @GetMapping("/getCourseByID/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
       CourseDTO dtoList= courseService.getCourseByID(id);
        return   ResponseEntity.ok(dtoList);

    }
    @GetMapping("/getCourseAll")
    public ResponseEntity<?> create() {
        List<CourseDTO> dtoList= courseService.list();
        return   ResponseEntity.ok(dtoList);

    }
    @PutMapping("/updatedBycourse/{id}")
    public ResponseEntity<?> getById(@RequestBody CourseDTO dto,@PathVariable ("id") Integer id) {
        String result= courseService.updateCourse(dto,id);
        return   ResponseEntity.ok(result);
    }
    @DeleteMapping("/deleteBycourse/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ("id") Integer id) {
        String result= courseService.delete(id);
        return   ResponseEntity.ok(result);
    }
    @GetMapping("/getBycourseName/{name}")
    public ResponseEntity<?> getByName(@PathVariable ("name")  String name) {
        List<CourseDTO> dtoList= courseService.dtoList(name);
        return   ResponseEntity.ok(dtoList);
    }
    @GetMapping("/getBycoursePrice/{price}")
    public ResponseEntity<?> getByPrice(@PathVariable ("price")  Double price) {
        List<CourseDTO> dtoList= courseService.priceList(price);
        return   ResponseEntity.ok(dtoList);
    }
    @GetMapping("/getBycourseDuration/{duration}")
    public ResponseEntity<?> getByPrice(@PathVariable ("duration")  Integer duration) {
        List<CourseDTO> dtoList= courseService.durationList(duration);
        return   ResponseEntity.ok(dtoList);
    }

    @GetMapping("/getByPriceBetween")
    public ResponseEntity<?> getByPriceBetween(@RequestParam ("from") Double from,@RequestParam("to") Double to ){
        List<CourseDTO> dtoList= courseService.dtoPricefromTO(from,to);
        return   ResponseEntity.ok(dtoList);
    }
    @GetMapping("/getByCreatedDateBetweenCourse")
    public ResponseEntity<?> getByDateFromDate(@RequestParam ("from") String from,@RequestParam("to") String to ){
        List<CourseDTO> dtoList= courseService.dtoDatefromTO(from,to);
        return   ResponseEntity.ok(dtoList);
    }

    @GetMapping("/getByPageCourse")
    public ResponseEntity<?> getByPage(@RequestParam ("page")  Integer page,@RequestParam("size") Integer size ){
        Page<CourseDTO> dtoList= courseService.getBypage(page,size);
        return   ResponseEntity.ok(dtoList);
    }
    @GetMapping("/getByPageCourseSort")
    public ResponseEntity<?> getByPageSort(@RequestParam ("page")  Integer page,@RequestParam("size") Integer size ){
        Page<CourseDTO> dtoList= courseService.getBypageSort(page,size);
        return   ResponseEntity.ok(dtoList);
    }
    @GetMapping("/getByPageCourseSortPrice")
    public ResponseEntity<?> getByPageSortPrice(@RequestParam ("page")  Integer page,@RequestParam("size") Integer size,@RequestParam("price") Double price ){
        Page<CourseDTO> dtoList= courseService.getBypageSortPrice(page,size,price);
        return   ResponseEntity.ok(dtoList);
    }
    @GetMapping("/Price/between")
    public ResponseEntity<?> getByPriceBetween(@RequestParam ("page")  Integer page,@RequestParam("size") Integer size,@RequestParam("from") Double from, @RequestParam("to") Double to){
        Page<CourseDTO> dtoList= courseService.getByPriceBetween(page,size,from,to);
        return   ResponseEntity.ok(dtoList);
    }

}
