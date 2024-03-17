package dev.magadiflo.app.dao.impl;

import dev.magadiflo.app.dao.StudentDAO;
import dev.magadiflo.app.model.Student;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentDAOImpl implements StudentDAO {

    private final JdbcClient jdbcClient;

    public StudentDAOImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Student> findAllStudents() {
        return this.jdbcClient.sql("""
                        SELECT s.id, s.name, s.email, s.gender, s.age
                        FROM students AS s
                        """)
                .query(Student.class)
                .list();
    }

    @Override
    public List<Student> findStudentByAgeAndGender(Integer age, String gender) {
        return this.jdbcClient.sql("""
                        SELECT s.id, s.name, s.email, s.gender, s.age
                        FROM students AS s
                        WHERE s.age = :age AND s.gender = :gender
                        """)
                .param("age", age)
                .param("gender", gender)
                .query(new StudentRowMapper())
                .list();
    }

    @Override
    public List<Student> findStudentByGenderAndAgeGreaterThan(Integer age, String gender) {
        return this.jdbcClient.sql("""
                        SELECT s.id, s.name, s.email, s.gender, s.age
                        FROM students AS s
                        WHERE s.age > :age AND s.gender = :gender
                        """)
                .param("age", age)
                .param("gender", gender)
                .query(new StudentRowMapper())
                .list();
    }

    @Override
    public Optional<Student> findStudentById(Long id) {
        return this.jdbcClient.sql("""
                        SELECT s.id, s.name, s.email, s.gender, s.age
                        FROM students AS s
                        WHERE s.id = :id
                        """)
                .param("id", id)
                .query(Student.class)
                .optional();
    }

    @Override
    public Integer insertStudent(Student student) {
        return this.jdbcClient.sql("""
                        INSERT INTO students(name, email, gender, age)
                        VALUES(:name, :email, :gender, :age)
                        """)
                .param("name", student.name(), Types.VARCHAR)
                .param("email", student.email(), Types.VARCHAR)
                .param("gender", student.gender(), Types.VARCHAR)
                .param("age", student.age(), Types.INTEGER)
                .update();
    }

    @Override
    public Integer updateStudent(Student student) {
        return this.jdbcClient.sql("""
                        UPDATE students
                        SET name = :name, email = :email, gender = :gender, age = :age
                        WHERE id = :id
                        """)
                .param("name", student.name())
                .param("email", student.email())
                .param("gender", student.gender())
                .param("age", student.age())
                .param("id", student.id())
                .update();
    }

    @Override
    public Integer deleteStudent(Long id) {
        return this.jdbcClient.sql("DELETE FROM students WHERE id = :id")
                .param("id", id)
                .update();
    }

    static class StudentRowMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Student(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("gender"),
                    rs.getInt("age"));
        }
    }
}
