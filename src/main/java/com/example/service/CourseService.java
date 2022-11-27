package com.example.service;

import com.example.dto.CourseDTO;
import com.example.dto.StudentDTO;
import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import com.example.exception.CreationException;
import com.example.exception.ItemnotFoundException;
import com.example.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public String createCourse(CourseDTO courseDTO){
        String checking=check(courseDTO);
        if(checking!=null){
            return checking;
        }
        CourseEntity entity=new CourseEntity();
        entity.setName(courseDTO.getName());
        entity.setPrice(courseDTO.getPrice());
        entity.setDuration(courseDTO.getDuration());
        entity.setCreatedDate(LocalDate.now());
        courseRepository.save(entity);
        return "Successefully saved";
    }

    public CourseDTO getCourseByID(Integer id){
        Optional<CourseEntity> entity= courseRepository.findById(id);
        if(entity.isEmpty()){
            throw new ItemnotFoundException("this id course not found");
        }
        return createDto(entity.get());
    }
    public CourseDTO createDto(CourseEntity entity){
        CourseDTO dto=new CourseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setDuration(entity.getDuration());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
    public List<CourseDTO> list(){
        Iterable<CourseEntity> entityList=  courseRepository.findAll();
        return createDtoLIst(entityList);

    }


    public List<CourseDTO> createDtoLIst(Iterable<CourseEntity> entityList){
        List<CourseDTO> dtoList=new ArrayList<>();
        for (CourseEntity entity : entityList) {
            CourseDTO dto=new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        return dtoList;
    }
    public String  updateCourse(CourseDTO courseDTO,Integer id){

        Optional<CourseEntity>entity=courseRepository.findById(id);
        CourseEntity course=new CourseEntity();
        course.setId(id);;
        course.setName(courseDTO.getName());
        course.setPrice(courseDTO.getPrice());
        course.setDuration(courseDTO.getDuration());
        course.setCreatedDate(LocalDate.now());

        courseRepository.save(course);
        return "Successefully updated";

    }
    public String delete(Integer id){
        if(id<0){
            throw new CreationException("id mus be positive");
        }
        if(courseRepository.findById(id)==null){
            throw new ItemnotFoundException("this id student doest not exists");
        }
        courseRepository.deleteById(id);
        return "Successefully deleted";

    }
    public List<CourseDTO>dtoList(String name){
         Iterable<CourseEntity> entities =courseRepository.findByName(name);
        return createDtoLIst(entities);
    }
    public List<CourseDTO>priceList(Double price){
       List<CourseEntity> entities =courseRepository.findByPrice(price);

        return createDtoLIst(entities);
    }
    public List<CourseDTO>durationList(Integer duration){
        List<CourseEntity> entities =courseRepository.findByDuration(duration);

        return createDtoLIst(entities);
    }
    public List<CourseDTO>dtoPricefromTO(Double price1,Double price2){
        List<CourseEntity> entities =courseRepository.findByPriceBetween(price1,price2);

        if(entities.isEmpty()){
            throw new ItemnotFoundException("this ptice does not exists");
        }

      return createDtoLIst(entities);
    }
    public List<CourseDTO>dtoDatefromTO(String date1,String date2){
        List<CourseEntity> entities =courseRepository.findByCreatedDateBetween(LocalDate.parse(date1),LocalDate.parse(date2));

        if(entities.isEmpty()){
            throw new ItemnotFoundException("this date does not exists");
        }

        return createDtoLIst(entities);
    }
    private String check(CourseDTO courseDTO) {

        if(courseDTO.getName().trim().length()<1){
            throw new CreationException("name length should be bigger than 1");
        }
        if(courseDTO.getPrice()<0){
            throw new CreationException("Price must be positove");
        }
        if(courseDTO.getDuration()<0){
            throw new CreationException("Duration must be positove");
        }

        return null;
    }

    public Page<CourseDTO> getBypage(Integer page, Integer size) {

        Pageable pageable=PageRequest.of(page,size);
        Page<CourseEntity>pageObj=courseRepository.findAll(pageable);

        List<CourseEntity>content=pageObj.getContent();

        long totalElementst=pageObj.getTotalElements();

       Page<CourseDTO> studentDTOS=new PageImpl<>(createDtoLIst(content),pageable,totalElementst);
       return studentDTOS;

    }

    public Page<CourseDTO> getBypageSort(Integer page, Integer size) {
        Sort sort=Sort.by(Sort.Direction.ASC,"createdDate");
        Pageable pageable=PageRequest.of(page,size,sort);
        Page<CourseEntity>pageObj1=courseRepository.findAll(pageable);

        List<CourseEntity>content=pageObj1.getContent();

        long totalElementst=pageObj1.getTotalElements();

        Page<CourseDTO> studentDTOS1=new PageImpl<>(createDtoLIst(content),pageable,totalElementst);
        return studentDTOS1;
    }

    public Page<CourseDTO> getBypageSortPrice(Integer page, Integer size, Double price) {
        Sort sort=Sort.by(Sort.Direction.ASC,"createdDate");
        Pageable pageable=PageRequest.of(page,size,sort);
        Page<CourseEntity>pageObj2=courseRepository.findAllPrice(price,pageable);

        List<CourseEntity>content=pageObj2.getContent();

        long totalElementst=pageObj2.getTotalElements();

        Page<CourseDTO> studentDTOS2=new PageImpl<>(createDtoLIst(content),pageable,totalElementst);
        return studentDTOS2;
    }
    public Page<CourseDTO> getByPriceBetween(Integer page, Integer size, Double from,Double to) {
        Sort sort=Sort.by(Sort.Direction.ASC,"createdDate");
        Pageable pageable=PageRequest.of(page,size,sort);
        Page<CourseEntity>pageObj2=courseRepository.findByPriceBetweenSort(from,to,pageable);

        List<CourseEntity>content=pageObj2.getContent();

        long totalElementst=pageObj2.getTotalElements();

        Page<CourseDTO> studentDTOS2=new PageImpl<>(createDtoLIst(content),pageable,totalElementst);
        return studentDTOS2;
    }
}
