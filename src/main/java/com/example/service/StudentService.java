package com.example.service;

import com.example.dto.StudentDTO;
import com.example.entity.StudentEntity;
import com.example.exception.CreationException;
import com.example.exception.ItemnotFoundException;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String create(StudentDTO studentDTO){
       String checking=check(studentDTO);
       if(checking!=null){
           return checking;
       }
        StudentEntity entity=new StudentEntity();
        entity.setName(studentDTO.getName());
        entity.setSurname(studentDTO.getSurname());
        entity.setLevel(studentDTO.getLevel());
        entity.setAge(studentDTO.getAge());
        entity.setGender(studentDTO.getGender());
        entity.setCreatedDate(LocalDate.now());
        studentRepository.save(entity);
        return "successefully saved";

    }
    public List<StudentDTO> list(){
        Iterable<StudentEntity> entityList= (List<StudentEntity>) studentRepository.findAll();
        return createDtoLIst(entityList);

    }
    public StudentDTO getByID(Integer id){
       Optional<StudentEntity> entity= studentRepository.findById(id);
       if(entity.isEmpty()){
           throw new ItemnotFoundException("this id student not found");
       }
       return createDto(entity.get());
    }

    public String  updateStudent(StudentDTO studentDTO,Integer id){

        Optional<StudentEntity>entity=studentRepository.findById(id);
        StudentEntity student=new StudentEntity();
        student.setId(id);
        student.setId(entity.get().getId());
        student.setName(studentDTO.getName());
        student.setSurname(studentDTO.getSurname());
        student.setLevel(studentDTO.getLevel());
        student.setAge(studentDTO.getAge());
        student.setGender(studentDTO.getGender());
        student.setCreatedDate(entity.get().getCreatedDate());
        studentRepository.save(student);
        return "Successefully updated";

    }
    public String delete(Integer id){
        if(id<0){
            throw new CreationException("id mus be positive");
        }
        if(studentRepository.findById(id)==null){
            throw new ItemnotFoundException("this id student doest not exists");
        }
        studentRepository.deleteById(id);
        return "Successefully deleted";

    }

    public List<StudentDTO>dtoList(String name){
        Iterable<StudentEntity> entities =studentRepository.findByName(name);
        return createDtoLIst(entities);
    }
    public List<StudentDTO>dtoSurname(String name){
        Iterable<StudentEntity> entities =studentRepository.findBySurname(name);
        return createDtoLIst(entities);
    }
    public List<StudentDTO>dtoLevel(Integer level){
        Iterable<StudentEntity> entities =studentRepository.findByLevel(level);

        return createDtoLIst(entities);
    }
    public List<StudentDTO>dtoAge(Integer age){
        Iterable<StudentEntity> entities =studentRepository.findByAge(age);

        return createDtoLIst(entities);
    }
    public List<StudentDTO>dtoGender(String gender){
        Iterable<StudentEntity> entities =studentRepository.findByGender(gender);

        return createDtoLIst(entities);
    }
    public List<StudentDTO>dtoDate(String date){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(date, formatter);

        Iterable<StudentEntity> entities =studentRepository.findByCreatedDate(localDate);

        if(entities.iterator().hasNext()){
            throw new ItemnotFoundException("this date does not exists");
        }

        List<StudentDTO> dtoList=new ArrayList<>();
        for (StudentEntity entity : entities) {
            StudentDTO dto=new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setAge(entity.getAge());
            dto.setGender(entity.getGender());
            dto.setCreatedDate(localDate);
            dtoList.add(dto);
        }
        return dtoList;
    }
    public List<StudentDTO>dtoDatefromTO(String date1,String date2){
        List<StudentEntity> entities =studentRepository.findByCreatedDateBetween(LocalDate.parse(date1),LocalDate.parse(date2));

        if(entities.isEmpty()){
            throw new ItemnotFoundException("this date does not exists");
        }

        List<StudentDTO> dtoList=new ArrayList<>();
        for (StudentEntity entity : entities) {
            StudentDTO dto=new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setAge(entity.getAge());
            dto.setGender(entity.getGender());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public Page<StudentDTO> getStudentPage(Integer page,Integer size){

        Pageable pageable= PageRequest.of(page,size);

        Page<StudentEntity> pageObj=studentRepository.findAll(pageable);

        List<StudentEntity> content=pageObj.getContent();

        Long totalElement=pageObj.getTotalElements();

        List<StudentDTO> studentDTOList=new LinkedList<>();

        for (StudentEntity student:content) {
            StudentDTO studentDTO=createDto(student);
            studentDTOList.add(studentDTO);
        }
        Page<StudentDTO> studentDTOS=new PageImpl<>(studentDTOList,pageable,totalElement);
        return studentDTOS;
    }
    public Page<StudentDTO> getStudentPageByLevel(Integer page,Integer size,Integer level){

        Pageable pageable= PageRequest.of(page,size);

        Page<StudentEntity> pageObj=studentRepository.findByLevelOrderById(level,pageable);

        List<StudentEntity> content=pageObj.getContent();

        Long totalElement=pageObj.getTotalElements();

        List<StudentDTO> studentDTOList=new LinkedList<>();

        for (StudentEntity student:content) {
            StudentDTO studentDTO=createDto(student);
            studentDTOList.add(studentDTO);
        }
        Page<StudentDTO> studentDTOS=new PageImpl<>(studentDTOList,pageable,totalElement);
        return studentDTOS;
    }
    public Page<StudentDTO> getStudentPageByGender(Integer page,Integer size,String gender){

        Pageable pageable= PageRequest.of(page,size);

        Page<StudentEntity> pageObj=studentRepository.findByGenderOrderByCreatedDate(gender,pageable);

        List<StudentEntity> content=pageObj.getContent();

        Long totalElement=pageObj.getTotalElements();

        List<StudentDTO> studentDTOList=new LinkedList<>();

        for (StudentEntity student:content) {
            StudentDTO studentDTO=createDto(student);
            studentDTOList.add(studentDTO);
        }
        Page<StudentDTO> studentDTOS=new PageImpl<>(studentDTOList,pageable,totalElement);
        return studentDTOS;
    }
    public StudentDTO createDto(StudentEntity entity){
        StudentDTO dto=new StudentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setLevel(entity.getLevel());
        dto.setAge(entity.getAge());
        dto.setGender(entity.getGender());
        dto.setCreatedDate(LocalDate.now());
        return dto;
    }
    public List<StudentDTO> createDtoLIst(Iterable<StudentEntity> entityList){
        List<StudentDTO> dtoList=new ArrayList<>();
        for (StudentEntity entity : entityList) {
            StudentDTO dto=new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setAge(entity.getAge());
            dto.setGender(entity.getGender());
            dto.setCreatedDate(LocalDate.now());
            dtoList.add(dto);
        }
        return dtoList;
    }
    private String check(StudentDTO studentDTO) {

        if(studentDTO.getName().trim().length()<3){
            throw new CreationException("name length should be bigger than 3");
        }
        if(studentDTO.getSurname().trim().length()<3){
            throw new CreationException("surname length should be bigger than 3");
        }
        if(studentDTO.getLevel()>4&&studentDTO.getLevel()<0){
            throw new CreationException("level will be betwen 0 and 4");
        }
        return null;
    }
}
