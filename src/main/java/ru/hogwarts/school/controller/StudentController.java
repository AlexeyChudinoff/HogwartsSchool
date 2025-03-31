package ru.hogwarts.school.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

  private final StudentService studentService;
  private final FacultyService facultyService;

  @Autowired
  public StudentController(StudentService studentService, FacultyService facultyService) {
    this.studentService = studentService;
    this.facultyService = facultyService;
  }


  @PostMapping("/add")
  public ResponseEntity<Student> addStudent(@RequestBody StudentCreateDTO studentDTO) {
    Faculty faculty = facultyService.getFacultyById(studentDTO.getFacultyId());
    if (faculty == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Факультет не найден");
    }

    Student student = new Student();
    student.setName(studentDTO.getName());
    student.setAge(studentDTO.getAge());
    student.setGender(studentDTO.getGender());
    student.setFaculty(faculty);

    Student createdStudent = studentService.create(student);
    return ResponseEntity.ok(createdStudent);
  }

//  @PostMapping("/add")
//  public Student addStudent(@RequestBody Student student) {
//    System.out.println("Получен студент: " + student);
//    return studentService.create(student);
//  }

  @GetMapping("/getid/{id}")
  public Student getById(@PathVariable Long id) {
    return studentService.getById(id);
  }

  @GetMapping("/getage/{age}")
  public Collection<Student> getByAge(@PathVariable int age) {
    return studentService.getByAge(age);
  }

  //127.0.0.1:8080/student/getgender?gender=m
  @GetMapping("/getgender")
  public Collection<Student> getByGender(@RequestParam String gender) {
    return studentService.getByGender(gender);
  }

  @GetMapping("/getall")
  public Collection<Student> getAllStudents() {
    return studentService.getAll();
  }

  @PutMapping("/change")
  public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
    Student updatedStudent = studentService.update(student);
    if (updatedStudent != null) {
      return ResponseEntity.ok(updatedStudent);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/delite")
  public ResponseEntity<Student> deleteStudent(@RequestParam Long id) {
    studentService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/getByAgeBetween")
  public ResponseEntity<Collection<Student>> getByAgeBetween(
      @RequestParam("ageFrom") int ageFrom,
      @RequestParam("ageTo") int ageTo) {
    Collection<Student> students = studentService.getByAgeBetween(ageFrom, ageTo);
    return ResponseEntity.ok(students);
  }

  @GetMapping("/getByName")
  public ResponseEntity<Collection<Student>> getByName(
      @RequestParam String name) {
    Collection<Student> students = studentService.getByName(name);
    if (!students.isEmpty()) {
      return ResponseEntity.ok(students);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/{studentId}/faculty")
  public ResponseEntity<Faculty> getStudentFaculty(@PathVariable Long studentId) {
    Student student = studentService.getById(studentId);
    if (student == null) {
      return ResponseEntity.notFound().build();
    }
    Faculty faculty = student.getFaculty();
    return ResponseEntity.ok(faculty);
  }

}