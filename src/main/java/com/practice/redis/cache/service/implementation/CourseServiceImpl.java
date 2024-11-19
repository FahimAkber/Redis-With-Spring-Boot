package com.practice.redis.cache.service.implementation;

import com.practice.redis.cache.domain.entity.Course;
import com.practice.redis.cache.domain.request.CourseRequest;
import com.practice.redis.cache.domain.response.CourseResponse;
import com.practice.redis.cache.repository.CourseRepository;
import com.practice.redis.cache.service.CourseService;
import com.practice.redis.common.exception.ApplicationException;
import com.practice.redis.database.domain.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    @Override
    public CourseResponse saveCourse(CourseRequest courseRequest) {
        Optional<Course> optionalCourse = courseRepository.findByName(courseRequest.getName());
        if(optionalCourse.isPresent()){
            throw new ApplicationException("Course already saved", HttpStatus.ALREADY_REPORTED);
        }

        Course course = modelMapper.map(courseRequest, Course.class);
        course = courseRepository.save(course);

        return modelMapper.map(course, CourseResponse.class);
    }

    @Override
    @CachePut(value = "CourseResponse", key = "#courseRequest.id")
    public CourseResponse editCourse(CourseRequest courseRequest) {
        Optional<Course> optionalCourse = courseRepository.findById(courseRequest.getId());
        if(optionalCourse.isEmpty()){
            throw new ApplicationException("Course not found", HttpStatus.NOT_FOUND);
        }

        Course course = modelMapper.map(courseRequest, Course.class);
        course = courseRepository.save(course);

        return modelMapper.map(course, CourseResponse.class);
    }

    @Override
    public List<CourseResponse> getCourses() {
        Iterable<Course> courses = courseRepository.findAll();
        if(!courses.iterator().hasNext()) {
            throw new ApplicationException("No course found", HttpStatus.NOT_FOUND);
        }

        List<CourseResponse> courseResponses = new ArrayList<>();
        courses.forEach(course -> courseResponses.add(modelMapper.map(course, CourseResponse.class)));

        return courseResponses;
    }

    @Override
    @Cacheable(value = "CourseResponse", key = "#id")
    public CourseResponse getCourseById(Integer id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        System.out.println("Data fetch from database");
        if(optionalCourse.isEmpty()){
            throw new ApplicationException("Course not found", HttpStatus.NOT_FOUND);
        }

        return modelMapper.map(optionalCourse.get(), CourseResponse.class);
    }

    @Override
    @CacheEvict(value = "CourseResponse", key = "#id")
    public MessageResponse deleteCourseById(Integer id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if(optionalCourse.isEmpty()){
            throw new ApplicationException("Course not found", HttpStatus.NOT_FOUND);
        }

        courseRepository.delete(optionalCourse.get());

        return new MessageResponse("Course deleted successfully");
    }
}
