package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @PostMapping("/add")
  public Student addStudent(@RequestBody Student student) {
    return studentService.create(student);
  }

  @GetMapping("/getid/{id}")
  public Student getById(@PathVariable Long id) {
    return studentService.getById(id);
  }

  @GetMapping("/getage/{age}")
  public Collection<Student> getByAge(@PathVariable int age) {
    return studentService.getByAge(age);
  }
  @GetMapping("/getgender/{gender}")
  public Collection<Student> getByGender(@PathVariable String gender)  {
    return studentService.getByGender(gender);
  }

  @GetMapping("/getall")
  public Collection<Student> getAllStudents() {
    return studentService.getAll();
  }

  @PutMapping("/change")
  public Student updateStudent(@RequestBody Student student) {
    return studentService.update(student);
  }

  @DeleteMapping("/{id}")
  public Student deleteStudent(@PathVariable Long id) {
    return studentService.deleteById(id);
  }
}