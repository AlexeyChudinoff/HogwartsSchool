package ru.hogwarts.school.service;


import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

@Service
public class FacultyService {
//убрали мапу , завели репозиторий и в контроллере внедрили репозиторий
// private final HashMap<Long, Faculty> facultys = new HashMap<>();

  private final FacultyRepository facultyRepository;

  @Autowired
  public FacultyService(FacultyRepository facultyRepository) {
    this.facultyRepository = facultyRepository;
  }
//убрали айдишник так как теперь БД
  // присваивает сама БД
  // private static Long lastId = 0L;

  public Faculty createFaculty(Faculty faculty) {
    return facultyRepository.save(faculty);
  }

  public Faculty getFacultyById(Long id) {
    return facultyRepository.findById(id).orElse(null);
  }

  public Collection<Faculty> getFacultyByName(String name) {
    return facultyRepository.findByNameIgnoreCase(name);
  }

  public Collection<Faculty> getFacultyByColor(String color) {
    return facultyRepository.findByColorIgnoreCase(color);
  }

  public Collection<Faculty> getAllFaculty() {
    return facultyRepository.findAll();
  }

  public Faculty updateFaculty(Faculty faculty) {
    if (facultyRepository.existsById(faculty.getId())) {
      facultyRepository.save(faculty);
      return faculty;
    }
    return null;
  }

  public void deleteFaculty(Long id) {
    facultyRepository.deleteById(id);
  }

}
