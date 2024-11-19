package com.practice.redis.cache.service;

import com.practice.redis.cache.domain.request.CourseRequest;
import com.practice.redis.cache.domain.response.CourseResponse;
import com.practice.redis.database.domain.response.MessageResponse;

import java.util.List;

public interface CourseService {
    CourseResponse saveCourse(CourseRequest courseRequest);
    CourseResponse editCourse(CourseRequest courseRequest);
    List<CourseResponse> getCourses();
    CourseResponse getCourseById(Integer id);
    MessageResponse deleteCourseById(Integer id);
}
