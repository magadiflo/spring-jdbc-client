package dev.magadiflo.app.controller;

import dev.magadiflo.app.model.Student;
import dev.magadiflo.app.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(this.studentService.findAllStudents());
    }

    @GetMapping(path = "/with-age-and-gender")
    public ResponseEntity<List<Student>> getStudentsByAgeAndGender(@RequestParam Integer age, @RequestParam String gender) {
        return ResponseEntity.ok(this.studentService.findStudentByAgeAndGender(age, gender));
    }

    @GetMapping(path = "/with-gender-and-age-greater-than")
    public ResponseEntity<List<Student>> getStudentsByGenderAndAgeGreaterThan(@RequestParam Integer age, @RequestParam String gender) {
        return ResponseEntity.ok(this.studentService.findStudentByGenderAndAgeGreaterThan(age, gender));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return this.studentService.findStudentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> saveStudent(@RequestBody Student student) {
        this.studentService.insertStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> updateStudent(@RequestBody Student student, @PathVariable Long id) {
        this.studentService.updateStudent(student, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        this.studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
