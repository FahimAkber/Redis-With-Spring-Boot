package com.practice.redis.cache.domain.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequest {
    private Integer id;
    @NotBlank(message = "Course name can't be null/empty")
    private String name;
    private Integer credit;
}
