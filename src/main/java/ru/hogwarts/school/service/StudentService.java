package ru.hogwarts.school.service;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

@Service
public class StudentService {

  private final StudentRepository studentRepository;

  @Autowired
  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public Student create(Student student) {
    return studentRepository.save(student);
  }

  //Если же мы хотим получить данные модели по определенному id,
// то можно использовать метод findById() или getById().
// Разница между ними лишь в том, что findById возвращает Opltional,
// а getById (устарел)— сам объект или null, если такой отсутствует.
  public Student getById(Long id) {
    return studentRepository.findById(id).orElse(null);
  }
  //2. Вы вызываете метод `findById` репозитория `studentRepository` и передаете ему параметр `id`.
  //3. Метод `findById` возвращает объект `Optional<Student>`, который может содержать студента или быть пустым.
  //4. Вы вызываете метод `orElse` объекта `Optional<Student>` и передаете ему значение `null`.
  //5. Метод `orElse` возвращает студента, если он существует, или `null`, если он не существует.
  //Таким образом, метод `getById` возвращает студента по идентификатору или `null`, если студент не существует.

  public Collection<Student> getByAge(int age) {
    return studentRepository.findAll().stream()
        .filter(s -> s.getAge() == age)
        .collect(Collectors.toList());
  }

  public Collection<Student> getByGender(String gender) {
    return studentRepository.findAll().stream()
        .filter(s -> s.getGender().equals(gender))
        .collect(Collectors.toList());
  }

  public Collection<Student> getAll() {
    return studentRepository.findAll();
  }

  public Student update(Student student) {
    return studentRepository.save(student);
  }

  public void deleteById(Long id) {
    studentRepository.deleteById(id);
  }

  public Collection<Student> getByAgeBetween(int ageFrom, int ageTo) {
    return studentRepository.findByAgeBetween(ageFrom, ageTo);
  }

  public Collection<Student> getByName(String name) {
    if (name != null) {
      return studentRepository.findByName(name);
    } else {
      return studentRepository.findAll();
    }
  }





}