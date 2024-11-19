package com.practice.redis.cache.repository;

import com.practice.redis.cache.domain.entity.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CourseRepository extends CrudRepository<Course, Integer> {
    Optional<Course> findByName(String name);
}
