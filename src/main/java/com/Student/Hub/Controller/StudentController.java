package com.Student.Hub.Controller;
import com.Student.Hub.Entity.Student;
import com.Student.Hub.Entity.User;
import com.Student.Hub.Repository.StudentRepository;
import com.Student.Hub.Services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final StudentRepository studentRepository;

    public StudentController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        return ResponseEntity.ok(studentService.getStudentById(new ObjectId(id)));
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        student.setStudentName(userName);  // Set logged-in user's name
        return ResponseEntity.ok(studentService.addStudent(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable String id, @RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateStudent(new ObjectId(id), student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(new ObjectId(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/checkAndGetData")
    public ResponseEntity<?> checkStudentExistsAndGetData() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            Student student = studentService.getStudentByUserName(userName);
            if (student != null) {
                System.out.println("student fetched");
                return ResponseEntity.ok(student);
            }
            return ResponseEntity.status(404).body("Student not found: " + userName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
