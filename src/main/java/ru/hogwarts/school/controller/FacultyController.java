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
import ru.hogwarts.school.service.FacultyService;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

  private final FacultyService facultyService;

  public FacultyController(FacultyService facultyService) {
    this.facultyService = facultyService;
  }

  @PostMapping("/add")
  public Faculty createFaculty(@RequestBody Faculty faculty) {
    return facultyService.createFaculty(faculty);
  }

  @GetMapping("/getall")
  public Collection<Faculty> getAllFaculties() {
    return facultyService.getAllFaculty();
  }

  //http://localhost:8080/faculty/getbycolor/22
  @GetMapping("/getbycolor/{color}")
  public Collection<Faculty> getAllFacultyByColor(@PathVariable String color) {
    return facultyService.getFacultyByColor(color);
  }

  @GetMapping("/{id}")
  public Faculty getFacultyById(@PathVariable Long id) {
    return facultyService.getFacultyById(id);
  }

  @PutMapping("/put")
  public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
    Faculty updateFaculty = facultyService.updateFaculty(faculty);
    if (updateFaculty != null) {
      return ResponseEntity.ok(updateFaculty);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/deleteById")
  public Faculty deleteFaculty(@RequestParam Long id) {
    return facultyService.deleteFaculty(id);
  }


}
