package com.exampl.employee.service;

import com.exampl.employee.model.Course;
import com.exampl.employee.repository.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public List<Course> getAll(){
        return courseRepository.findAll();
    }

    public Course getById(Long id){
        Course data = courseRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return data;
    }

    public String save(Course course){
        courseRepository.save(course);
        return "success";
    }

    public String delete(Long id){
        courseRepository.deleteById(id);
        return "success";
    }

    public Page<Course> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        return this.courseRepository.findAll(pageable);
    }
}
