package ru.hogwarts.school.controller;


import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

  FacultyService facultyService;

  public FacultyController(FacultyService facultyService) {
    this.facultyService = facultyService;
  }

  @PostMapping("/add")
  public Faculty createFaculty(Faculty faculty) {
    return facultyService.createFaculty(faculty);
  }

  @GetMapping("/getall")
  public Collection<Faculty> getAllFaculties() {
    return facultyService.getAllFaculty();
  }

  @GetMapping("/getbycolor")
  public Collection<Faculty> getAllFacultyByColor(String color) {
    return facultyService.getFacultyByColor(color);
  }

  @GetMapping("{id}")
  public Faculty getFacultyById(@PathVariable Long id) {
    return facultyService.getFacultyById(id);
  }

  @GetMapping("/put")
  public Faculty updateFaculty(@RequestBody Faculty faculty) {
    return facultyService.updateFaculty(faculty);
  }

  @GetMapping("/delete")
  public Faculty deleteFaculty(Long id) {
    return facultyService.deleteFaculty(id);
  }







}
