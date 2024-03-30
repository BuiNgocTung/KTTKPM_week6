package com.example.sevices;

import com.example.entities.Student;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentRedisService {
    private HashOperations hashOperations;
    private RedisTemplate redisTemplate;

    public StudentRedisService(RedisTemplate redisTemplate) {
        super();
        this.hashOperations = redisTemplate.opsForHash();
        this.redisTemplate = redisTemplate;
    }


    public void save(Student student) {
        hashOperations.put("Student", student.getStudentId(), student);
    }

    public Student findById(long id) {
        return (Student) hashOperations.get("Student", id);
    }

    public List<Student> findAll() {
        return hashOperations.values("Student");
    }

    public void update(Student Student) {
        save(Student);
    }

    public void delete(int id) {
        hashOperations.delete("Student", id);
    }
}
