package ru.hogwarts.school.controller;


import java.util.Collection;
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
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

  private final FacultyService facultyService;
  private final StudentService studentService;

  public FacultyController(FacultyService facultyService,
      StudentService studentService) {
    this.facultyService = facultyService;
    this.studentService = studentService;
  }

  @PostMapping("/add")
  public Faculty createFaculty(@RequestBody Faculty faculty) {
    System.out.println("Получен факультет: " + faculty);
    return facultyService.createFaculty(faculty);
  }

  @GetMapping("/getall")
  public Collection<Faculty> getAllFaculties() {
    return facultyService.getAllFaculty();
  }

  //http://localhost:8080/faculty/getbycolor/22
//  @GetMapping("/getByColor/{color}")
//  public Collection<Faculty> getAllFacultyByColor(@PathVariable String color) {
//    return facultyService.getFacultyByColor(color);
//  }

  @GetMapping("/{id}")
  public Faculty getFacultyById(@PathVariable Long id) {
    return facultyService.getFacultyById(id);
  }

  @PutMapping("/change")
  public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
    Faculty updateFaculty = facultyService.updateFaculty(faculty);
    if (updateFaculty != null) {
      return ResponseEntity.ok(updateFaculty);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/deleteById")
  public ResponseEntity<Faculty> deleteFaculty(@RequestParam Long id) {
    facultyService.deleteFaculty(id);
    return ResponseEntity.ok().build();
  }

  //@RequestParam(required = false) позволяет опускать параметры.
  @GetMapping("/getByNameOrColor")
  public ResponseEntity<Collection<Faculty>> getFacultyByNameOrColor(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String color) {

    if (name != null && !name.isBlank()) {
      return ResponseEntity.ok(facultyService.getFacultyByName(name));
    }
    if (color != null && !color.isBlank()) {
      return ResponseEntity.ok(facultyService.getFacultyByColor(color));
    }
    return ResponseEntity.ok(facultyService.getAllFaculty());
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
