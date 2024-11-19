package com.practice.redis.cache.controller;

import com.practice.redis.cache.domain.request.CourseRequest;
import com.practice.redis.cache.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("course/")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    
    @PostMapping("save")
    public ResponseEntity<Object> saveCourse(@Valid @RequestBody CourseRequest courseRequest) {
        return new ResponseEntity<>(courseService.saveCourse(courseRequest), HttpStatus.OK);
    }

    @PutMapping("edit")
    public ResponseEntity<Object> editCourse(@Valid @RequestBody CourseRequest courseRequest) {
        return new ResponseEntity<>(courseService.editCourse(courseRequest), HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<Object> getCourses(){
        return new ResponseEntity<>(courseService.getCourses(), HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{courseId}")
    public ResponseEntity<Object> getCourseById(@PathVariable("courseId") Integer courseId) {
        return new ResponseEntity(courseService.getCourseById(courseId), HttpStatus.OK);
    }

    @DeleteMapping("/delete-by-id/{courseId}")
    public ResponseEntity<Object> deleteCourseById(@PathVariable("courseId") Integer courseId) {
        return new ResponseEntity<>(courseService.deleteCourseById(courseId), HttpStatus.OK);
    }
}
