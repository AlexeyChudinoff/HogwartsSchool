package ru.hogwarts.school.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

@Service
public class StudentService {

  private final Map<Long, Student> students = new HashMap<>();
  private Long idCounter = 0L;

  public Student create(Student student) {
    student.setId(++idCounter);
    students.put(idCounter, student);
    return student;
  }

  public Student getById(Long id) {
    return students.get(id);
  }

  public Collection<Student> getByAge(int age) {
    return students.values().stream()
        .filter(s -> s.getAge() == age)
        .collect(Collectors.toList());
  }

  public Collection<Student> getByGender(String gender) {
    return students.values().stream()
        .filter(s -> s.getGender().equals(gender))
        .collect(Collectors.toList());
  }

  public Collection<Student> getAll() {
    return students.values();
  }

  public Student update(Student student) {
    if (students.containsKey(student.getId())) {
      students.put(student.getId(), student);
      return student;
    }
    return null;
  }

  public Student deleteById(Long id) {
    return students.remove(id);
  }
}