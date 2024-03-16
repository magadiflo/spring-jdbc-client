package dev.magadiflo.app.model;

public record Student(
        Long id,
        String name,
        String email,
        String gender,
        Integer age) {
}
