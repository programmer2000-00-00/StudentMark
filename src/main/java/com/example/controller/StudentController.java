package com.example.controller;

import com.example.dto.StudentDTO;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService service;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody StudentDTO studentDTO) {
        String result = service.create(studentDTO);
         return   ResponseEntity.ok(result);

    }


    @GetMapping("/getAll")
    public ResponseEntity<?> create() {
      List<StudentDTO> dtoList= service.list();
        return   ResponseEntity.ok(dtoList);

    }

    @GetMapping("/getByID/{id}")
    public ResponseEntity<?> getById(@PathVariable ("id") Integer id) {
        StudentDTO dtoList= service.getByID(id);
        return   ResponseEntity.ok(dtoList);

    }
    @PutMapping("/updatedBystudent/{id}")
    public ResponseEntity<?> getById(@RequestBody StudentDTO dto,@PathVariable ("id") Integer id) {
       String result= service.updateStudent(dto,id);
        return   ResponseEntity.ok(result);
    }
    @DeleteMapping("/deleteBystudent/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ("id") Integer id) {
        String result= service.delete(id);
        return   ResponseEntity.ok(result);
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<?> getByName(@PathVariable ("name")  String name) {
        List<StudentDTO> dtoList= service.dtoList(name);
        return   ResponseEntity.ok(dtoList);
    }

    @GetMapping("/getBySurname/{surname}")
    public ResponseEntity<?> getBySurName(@PathVariable ("surname")  String surname) {
        List<StudentDTO> dtoList= service.dtoSurname(surname);
        return   ResponseEntity.ok(dtoList);
    }

    @GetMapping("/getByLevel/{level}")
    public ResponseEntity<?> getByLevel(@PathVariable ("level")  Integer level) {
        List<StudentDTO> dtoList= service.dtoLevel(level);
        return   ResponseEntity.ok(dtoList);
    }
    @GetMapping("/getByAge/{age}")
    public ResponseEntity<?> getByAge(@PathVariable ("age")  Integer age) {
        List<StudentDTO> dtoList= service.dtoAge(age);
        return   ResponseEntity.ok(dtoList);
    }
    @GetMapping("/getByGender/{gender}")
    public ResponseEntity<?> getByGender(@PathVariable ("gender")  String gender) {
        List<StudentDTO> dtoList= service.dtoGender(gender);
        return   ResponseEntity.ok(dtoList);
    }
    @GetMapping("/getByDate/{date}")
    public ResponseEntity<?> getByDate(@PathVariable ("date") String date){
        List<StudentDTO> dtoList= service.dtoDate(date);
        return   ResponseEntity.ok(dtoList);
    }
    @GetMapping("/getByCreatedDateBetween")
    public ResponseEntity<?> getByDateFromDate(@RequestParam ("from") String from,@RequestParam("to") String to ){
        List<StudentDTO> dtoList= service.dtoDatefromTO(from,to);
        return   ResponseEntity.ok(dtoList);
    }

    @GetMapping("/getByPage")
    public ResponseEntity<?> getByPage(@RequestParam ("page") Integer page,@RequestParam("size") Integer size ){
        Page<StudentDTO> dtoList= service.getStudentPage(page,size);
        return   ResponseEntity.ok(dtoList);
    }
    @GetMapping("/getByPageLevel")
    public ResponseEntity<?> getByPage(@RequestParam ("page") Integer page,@RequestParam("size") Integer size,@RequestParam("level") Integer level ){
        Page<StudentDTO> dtoList= service.getStudentPageByLevel(page,size,level);
        return   ResponseEntity.ok(dtoList);
    }
    @GetMapping("/getByPageGender")
    public ResponseEntity<?> getByPage(@RequestParam ("page") Integer page,@RequestParam("size") Integer size,@RequestParam("gender") String gender ){
        Page<StudentDTO> dtoList= service.getStudentPageByGender(page,size,gender);
        return   ResponseEntity.ok(dtoList);
    }




}
