package com.example.service;

import com.example.dto.CourseDTO;
import com.example.dto.StudentCourseDTO;
import com.example.dto.StudentDTO;
import com.example.entity.CourseEntity;
import com.example.entity.StudentCourseMarkEntity;
import com.example.entity.StudentEntity;
import com.example.exception.CreationException;
import com.example.exception.ItemnotFoundException;
import com.example.repository.CourseRepository;
import com.example.repository.StudentMarkRepository;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StudentMarkService {
    @Autowired
    private StudentMarkRepository repository;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseRepository courseRepository;

    public StudentCourseDTO create(StudentCourseDTO dto) {
        try {
            Optional<StudentEntity> student = studentRepository.findById(dto.getStudentID());
            Optional<CourseEntity> course = courseRepository.findById(dto.getCourseID());

            StudentCourseMarkEntity entity = new StudentCourseMarkEntity();
            entity.setStudent(student.get());
            entity.setCourse(course.get());
            entity.setMark(dto.getMark());
            entity.setCreatedDate(dto.getCreatedDate());
            entity.setCreatedDate(LocalDate.now());
            repository.save(entity);
            dto.setId(entity.getId());
            return dto;
        }catch (NoSuchElementException e){
            throw new ItemnotFoundException("this student or course is not found");
        }
    }
    public String update(StudentCourseDTO studentCourseDTO,Integer m){

        Optional<StudentCourseMarkEntity> byId2 = repository.findById(m);
        if (byId2.isEmpty()) {
            throw new ItemnotFoundException("this id student course mark id not found");
        }

        Optional<StudentEntity> byId = studentRepository.findById(studentCourseDTO.getStudentID());
        if (byId.isEmpty()) {
            throw new ItemnotFoundException("this is student is not found");
        }
        Optional<CourseEntity> byId1 = courseRepository.findById(studentCourseDTO.getCourseID());
        if (byId1.isEmpty()) {
            throw new ItemnotFoundException("this is course is not found");
        }
        if (studentCourseDTO.getMark() < 1) {
            throw new CreationException("the mark is wrong");
        }
        Integer n = repository.update( studentCourseDTO.getStudentID(), studentCourseDTO.getCourseID(),
                studentCourseDTO.getMark(),m);
        if (n != 0) {
            return "successefull updated";
            }
            return "something is wrong";
    }

    public StudentCourseDTO getById(Integer id){
        Optional<StudentCourseMarkEntity> byId2 = repository.findById(id);
        if(byId2.isEmpty()){
            throw new ItemnotFoundException("is empty!!!");
        }
        return createDTO(byId2);
    }

    public StudentCourseDTO getByDetailInteger(Integer id){
        Optional<StudentCourseMarkEntity> byId2 = repository.findById(id);
        if(byId2.isEmpty()){
            throw new ItemnotFoundException("is empty!!!");
        }

        StudentDTO studentDTO = studentService.getByID(byId2.get().getStudent().getId());
        CourseDTO courseDTO=courseService.getCourseByID(byId2.get().getCourse().getId());

        StudentCourseDTO dto=new StudentCourseDTO();
        dto.setId(id);
        dto.setStudentDTO(studentDTO);
        dto.setCourseDTO(courseDTO);
        dto.setMark(byId2.get().getMark());
        dto.setCreatedDate(byId2.get().getCreatedDate());

        return dto;

    }
    public String delete(Integer id){
        Optional<StudentCourseMarkEntity> byId2 = repository.findById(id);
        if(byId2.isEmpty()){
            throw new ItemnotFoundException("this id is not found!!!");
        }
       repository.deleteById(id);
        return "successefully deleted";

    }
    public List<StudentCourseDTO> getAll(){

        Iterable<StudentCourseMarkEntity> all = repository.findAll();
        return createDTOList(all);

    }
    public List<StudentCourseDTO> getMark(String date,Integer id){


        List<StudentCourseMarkEntity> allByMark = repository.findAllByMark(LocalDate.parse(date),id);
        List<StudentCourseDTO>dtos=new LinkedList<>();
        for (StudentCourseMarkEntity entity : allByMark) {
           StudentCourseDTO dto=new StudentCourseDTO();
           dto.setId(entity.getId());
           dto.setStudentID(entity.getStudent().getId());
           dto.setMark(entity.getMark());
           dto.setCreatedDate(entity.getCreatedDate());
           dtos.add(dto);
        }
        return dtos;
    }
    public List<StudentCourseDTO> getMarkBetween(String date1,String date2,Integer id){


        List<StudentCourseMarkEntity> allByMark = repository.findAllByMarkBetween(LocalDate.parse(date1),LocalDate.parse(date2),id);
        List<StudentCourseDTO>dtos=new LinkedList<>();
        for (StudentCourseMarkEntity entity : allByMark) {
            StudentCourseDTO dto=new StudentCourseDTO();
            dto.setId(entity.getId());
            dto.setStudentID(entity.getStudent().getId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            dtos.add(dto);
        }
        return dtos;
    }
    public List<StudentCourseDTO> getMarkAll(Integer id){


        List<StudentCourseMarkEntity> allByMark = repository.findAllByMarkDesc(id);
        List<StudentCourseDTO>dtos=new LinkedList<>();
        for (StudentCourseMarkEntity entity : allByMark) {
            StudentCourseDTO dto=new StudentCourseDTO();
            dto.setId(entity.getId());
            dto.setStudentID(entity.getStudent().getId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            dtos.add(dto);
        }
        return dtos;
    }
    public List<StudentCourseDTO> getMarkcourse(Integer id,Integer courseId){
        List<StudentCourseMarkEntity> allByMark = repository.findAllByMarkcourse(id,courseId);
        List<StudentCourseDTO>dtos=new LinkedList<>();
        for (StudentCourseMarkEntity entity : allByMark) {
            StudentCourseDTO dto=new StudentCourseDTO();
            dto.setId(entity.getId());
            dto.setStudentID(entity.getStudent().getId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            dtos.add(dto);
        }
        return dtos;
    }
    public  List<StudentCourseDTO> createDTOList(Iterable<StudentCourseMarkEntity>entities){
        List<StudentCourseDTO> list=new LinkedList<>();
        for (StudentCourseMarkEntity entity : entities) {

        StudentCourseDTO studentCourseDTO=new StudentCourseDTO();
        studentCourseDTO.setId(entity.getId());
        studentCourseDTO.setStudentID(entity.getStudent().getId());
        studentCourseDTO.setCourseID(entity.getCourse().getId());
        studentCourseDTO.setMark(entity.getMark());
        studentCourseDTO.setCreatedDate(entity.getCreatedDate());
        list.add(studentCourseDTO);
        }
        return list;
    }
    public  StudentCourseDTO createDTO(Optional<StudentCourseMarkEntity> entity){
        StudentCourseDTO studentCourseDTO=new StudentCourseDTO();
        studentCourseDTO.setId(entity.get().getId());
        studentCourseDTO.setStudentID(entity.get().getStudent().getId());
        studentCourseDTO.setCourseID(entity.get().getCourse().getId());
        studentCourseDTO.setMark(entity.get().getMark());
        studentCourseDTO.setCreatedDate(entity.get().getCreatedDate());

        return studentCourseDTO;
    }
}
