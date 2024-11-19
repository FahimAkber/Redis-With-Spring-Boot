package com.practice.redis.cache.domain.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse implements Serializable {
    private Integer id;
    private String name;
    private Integer credit;
}
