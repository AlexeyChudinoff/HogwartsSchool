package ru.hogwarts.school.service;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

@Service
public class FacultyService {
//убрали мапу , завели репозиторий и в контроллере внедрили репозиторий
// private final HashMap<Long, Faculty> facultys = new HashMap<>();

  private final FacultyRepository facultyRepository;

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

  public List<Faculty> getFacultyByColor(String color) {
    return facultyRepository.findAll().stream()
        .filter(f -> f.getColor().equals(color))
        .collect(Collectors.toList());
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
