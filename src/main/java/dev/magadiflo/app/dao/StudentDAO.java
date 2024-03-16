package dev.magadiflo.app.dao;

import dev.magadiflo.app.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDAO {
    List<Student> findAllStudents();

    List<Student> findStudentByAgeAndGender(Integer age, String gender);

    List<Student> findStudentByGenderAndAgeGreaterThan(Integer age, String gender);

    Optional<Student> findStudentById(Long id);

    Integer insertStudent(Student student);

    Integer updateStudent(Student student);

    Integer deleteStudent(Long id);
}
