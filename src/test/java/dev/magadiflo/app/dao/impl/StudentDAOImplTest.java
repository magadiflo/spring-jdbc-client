package dev.magadiflo.app.dao.impl;

import dev.magadiflo.app.model.Student;
import dev.magadiflo.app.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Testcontainers
@Sql(scripts = {"/test-student-data.sql"})
@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentDAOImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> POSTGRES_CONTAINER = new PostgreSQLContainer<>("postgres:15.2-alpine");

    @Autowired
    private JdbcClient jdbcClient;
    private StudentDAOImpl studentDAO;

    @BeforeEach
    void setUp() {
        this.studentDAO = new StudentDAOImpl(this.jdbcClient);
    }

    @Test
    void shouldSaveTheStudentAndReturnsTheInteger() {
        // Given
        Student student = new Student(null, "Martín", "martin@gmail.com", "Masculino", 35);

        // When
        Integer affectedRows = this.studentDAO.insertStudent(student);

        // Then
        assertThat(affectedRows).isEqualTo(1);
    }

    @Test
    void shouldUpdateTheStudentAndReturnsTheInteger() {
        // Given
        Long studentId = 1L;
        Student student = new Student(studentId, "Martín", "martin@gmail.com", "Masculino", 35);

        Optional<Student> optionalStudentBeforeUpdating = this.studentDAO.findStudentById(studentId);
        Student studentBefore = optionalStudentBeforeUpdating.get();

        assertThat(studentBefore).isNotNull();
        LOG.info("beforeUpdating: {}", studentBefore);

        // When
        Integer affectedRows = this.studentDAO.updateStudent(student);

        // Then
        assertThat(affectedRows).isEqualTo(1);

        Optional<Student> optionalStudentAfterUpdating = this.studentDAO.findStudentById(studentId);
        Student studentAfter = optionalStudentAfterUpdating.get();

        assertThat(studentAfter).isNotNull();
        assertThat(studentAfter.name()).isEqualTo(student.name());
        assertThat(studentAfter.email()).isEqualTo(student.email());
        assertThat(studentAfter.gender()).isEqualTo(student.gender());
        assertThat(studentAfter.age()).isEqualTo(student.age());
        LOG.info("afterUpdating: {}", studentAfter);
    }

    @Test
    void shouldDeleteStudentAndReturnsTheInteger() {
        // Given
        Long studentId = 1L;

        // When
        Integer affectedRows = this.studentDAO.deleteStudent(studentId);

        // Then
        assertThat(affectedRows).isEqualTo(1);
        Optional<Student> optionalStudent = this.studentDAO.findStudentById(studentId);
        assertThat(optionalStudent.isEmpty()).isTrue();
    }

    @Test
    void shouldFindStudentByIdAndReturnsTheStudent() {
        // Given
        Long studentId = 1L;

        // When
        Student studentDB = this.studentDAO.findStudentById(studentId).get();

        // Then
        assertThat(studentDB).isNotNull();
        assertThat(studentDB.id()).isEqualTo(studentId);
        assertThat(studentDB.name()).isEqualTo("Juan Pérez");
        assertThat(studentDB.email()).isEqualTo("juan@gmail.com");
        assertThat(studentDB.age()).isEqualTo(30);
        assertThat(studentDB.gender()).isEqualTo("Masculino");
    }

    @Test
    void shouldFindAllStudents() {
        // Given

        // When
        List<Student> students = this.studentDAO.findAllStudents();

        // Then
        assertThat(students.size()).isEqualTo(14);
    }

    @Test
    void shouldFindStudentByAgeAndGenderAndReturnsTheListStudents() {
        // Given

        // When
        List<Student> students = this.studentDAO.findStudentByAgeAndGender(32, "Masculino");

        // Then
        List<Long> ids = students.stream()
                .map(Student::id)
                .toList();

        assertThat(ids.size()).isEqualTo(3);
        assertThat(ids).isEqualTo(List.of(3L, 5L, 9L));

        assertThat(students.getFirst().id()).isEqualTo(3);
        assertThat(students.getFirst().name()).isEqualTo("Carlos López");
        assertThat(students.getFirst().email()).isEqualTo("carlos@gmail.com");
        assertThat(students.getFirst().age()).isEqualTo(32);
        assertThat(students.getFirst().gender()).isEqualTo("Masculino");
    }

    @Test
    void shouldFindStudentByGenderAndAgeGreaterThanAndReturnsTheListStudents() {
        // Given

        // When
        List<Student> students = this.studentDAO.findStudentByGenderAndAgeGreaterThan(30, "Femenino");

        // Then
        List<Long> ids = students.stream()
                .map(Student::id)
                .toList();

        assertThat(ids.size()).isEqualTo(2);
        assertThat(ids).isEqualTo(List.of(11L, 14L));
    }
}