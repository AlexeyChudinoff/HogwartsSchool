package ru.hogwarts.school.service;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

@Service
public class FacultyService {

  private final HashMap<Long, Faculty> facultys = new HashMap<>();
  private static Long lastId = 0L;

  public Faculty createFaculty(Faculty faculty) {
    faculty.setId(++lastId);
    facultys.put(lastId, faculty);
    return faculty;
  }

  public Faculty getFacultyById(Long id) {
    return facultys.get(id);
  }

  public List<Faculty> getFacultyByColor(String color) {
    return facultys.values().stream()
        .filter(f -> f.getColor().equals(color))
        .collect(Collectors.toList());
  }

  public Collection<Faculty> getAllFaculty() {
    return facultys.values();
  }

  public Faculty updateFaculty(Faculty faculty) {
    if (facultys.containsKey(faculty.getId())) {
      facultys.put(faculty.getId(), faculty);
      return faculty;
    }
    return null;
  }


  public Faculty deleteFaculty(Long id) {
    return facultys.remove(id);
  }

}
