package dev.magadiflo.app.service;

import dev.magadiflo.app.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAllStudents();

    List<Student> findStudentByAgeAndGender(Integer age, String gender);

    List<Student> findStudentByGenderAndAgeGreaterThan(Integer age, String gender);

    Optional<Student> findStudentById(Long id);

    void insertStudent(Student student);

    void updateStudent(Student student, Long id);

    void deleteStudent(Long id);
}
