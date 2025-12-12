package com.Student.Hub.Repository;

import com.Student.Hub.Entity.Student;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, ObjectId> {
    Student findByStudentName(String studentName);
}
