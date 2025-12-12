package com.Student.Hub.Services;

import com.Student.Hub.Entity.Student;
import com.Student.Hub.Repository.StudentRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get student by ID
    public Student getStudentById(ObjectId id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }
    public Student getStudentByUserName(String studentName){
        return studentRepository.findByStudentName(studentName);
    }

    // Add new student
    public Student addStudent(Student student) {
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());
        return studentRepository.save(student);
    }

    // Update existing student
    public Student updateStudent(ObjectId id, Student updatedStudent) {
        return studentRepository.findById(id).map(student -> {

            if(updatedStudent.getStudentName() != null) student.setStudentName(updatedStudent.getStudentName());
            if (updatedStudent.getFatherName() != null) student.setFatherName(updatedStudent.getFatherName());
            if (updatedStudent.getCnic() != null) student.setCnic(updatedStudent.getCnic());
            if (updatedStudent.getDateOfBirth() != null) student.setDateOfBirth(updatedStudent.getDateOfBirth());
            if (updatedStudent.getGender() != null) student.setGender(updatedStudent.getGender());
            if (updatedStudent.getEmail() != null) student.setEmail(updatedStudent.getEmail());
            if (updatedStudent.getContactNumber() != null) student.setContactNumber(updatedStudent.getContactNumber());
            if (updatedStudent.getDepartment() != null) student.setDepartment(updatedStudent.getDepartment());
            if (updatedStudent.getProgram() != null) student.setProgram(updatedStudent.getProgram());
            if (updatedStudent.getSemester() != 0) student.setSemester(updatedStudent.getSemester());
            if (updatedStudent.getBatch() != null) student.setBatch(updatedStudent.getBatch());
            if (updatedStudent.getSection() != null) student.setSection(updatedStudent.getSection());
            if (updatedStudent.getCurrentSubjects() != null) student.setCurrentSubjects(updatedStudent.getCurrentSubjects());
            if (updatedStudent.getCompletedCredits() != 0) student.setCompletedCredits(updatedStudent.getCompletedCredits());
            if (updatedStudent.getCgpa() != 0) student.setCgpa(updatedStudent.getCgpa());
            if (updatedStudent.getGpa() != 0) student.setGpa(updatedStudent.getGpa());
            if (updatedStudent.getAddress() != null) student.setAddress(updatedStudent.getAddress());
            if (updatedStudent.getCity() != null) student.setCity(updatedStudent.getCity());
            if (updatedStudent.getCountry() != null) student.setCountry(updatedStudent.getCountry());
            if (updatedStudent.getPostalCode() != null) student.setPostalCode(updatedStudent.getPostalCode());
            if (updatedStudent.getGuardianName() != null) student.setGuardianName(updatedStudent.getGuardianName());
            if (updatedStudent.getGuardianContact() != null) student.setGuardianContact(updatedStudent.getGuardianContact());
            if (updatedStudent.getGuardianRelation() != null) student.setGuardianRelation(updatedStudent.getGuardianRelation());
            if (updatedStudent.getImage() != null) student.setImage(updatedStudent.getImage());
            if (updatedStudent.getStatus() != null) student.setStatus(updatedStudent.getStatus());

            student.setUpdatedAt(LocalDateTime.now());
            return studentRepository.save(student);
        }).orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    // Delete student by ID
    public void deleteStudent(ObjectId id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }
}
