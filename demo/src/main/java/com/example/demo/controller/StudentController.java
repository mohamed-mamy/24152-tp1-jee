package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @PostMapping
    public Student create(@RequestBody @Valid Student student) {
        return studentRepository.save(student);
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

        studentRepository.deleteById(id);
    }
    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student student) {

        Student s = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        s.setName(student.getName());
        s.setEmail(student.getEmail());

        return studentRepository.save(s);
    }
    @GetMapping("/page")
    public Page<Student> getStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @GetMapping("/search")
    public List<Student> search(@RequestParam String name) {
        return studentRepository.findByName(name);
    }

}
