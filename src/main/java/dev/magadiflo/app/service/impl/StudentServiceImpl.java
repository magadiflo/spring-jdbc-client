package dev.magadiflo.app.service.impl;

import dev.magadiflo.app.dao.StudentDAO;
import dev.magadiflo.app.model.Student;
import dev.magadiflo.app.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger LOG = LoggerFactory.getLogger(StudentServiceImpl.class);
    private final StudentDAO studentDAO;

    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> findAllStudents() {
        return this.studentDAO.findAllStudents();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> findStudentByAgeAndGender(Integer age, String gender) {
        return this.studentDAO.findStudentByAgeAndGender(age, gender);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> findStudentByGenderAndAgeGreaterThan(Integer age, String gender) {
        return this.studentDAO.findStudentByGenderAndAgeGreaterThan(age, gender);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Student> findStudentById(Long id) {
        return this.studentDAO.findStudentById(id);
    }

    @Override
    @Transactional
    public void insertStudent(Student student) {
        Integer affectedRows = this.studentDAO.insertStudent(student);
        LOG.info("Affected rows after insert: {}", affectedRows);
    }

    @Override
    @Transactional
    public void updateStudent(Student student, Long id) {
        this.studentDAO.findStudentById(id)
                .map(studentDB -> {
                    Student studentToUpdate = new Student(id, student.name(), student.email(), student.gender(), student.age());
                    Integer affectedRows = this.studentDAO.updateStudent(studentToUpdate);
                    LOG.info("Affected rows after update: {}", affectedRows);
                    return affectedRows;
                }).orElseThrow(() -> new NoSuchElementException("No existe el id del estudiante a actualizar"));
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        this.studentDAO.findStudentById(id)
                .map(studentDB -> {
                    Integer affectedRows = this.studentDAO.deleteStudent(id);
                    LOG.info("Affected rows after deletion: {}", affectedRows);
                    return affectedRows;
                }).orElseThrow(() -> new NoSuchElementException("No existe el id del estudiante a eliminar"));
    }
}
