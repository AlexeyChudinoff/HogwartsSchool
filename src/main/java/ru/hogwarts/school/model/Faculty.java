package ru.hogwarts.school.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

@Entity(name = "faculty")//чтобы модель отвечала за какую-либо
// определенную таблицу
@JsonIgnoreProperties({"students"})
// Игнорируем поля при сериализации/десериализации

public class Faculty {

  public Faculty() {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)//strategy обозначает,
// что будет использоваться автоинкриментация идентификатора на уровне
// БД. Т.е. идентификатор будет генерировать база, а не приложение.
  @Schema(hidden = true) // Скрываем id в Swagger
  private Long id;
  private String name;
  private String color;

  public Faculty(Long id, String name, String color) {
    // this.id = id;
    this.name = name;
    this.color = color;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Faculty faculty = (Faculty) o;
    return Objects.equals(id, faculty.id) && Objects.equals(name, faculty.name)
        && Objects.equals(color, faculty.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, color);
  }

  @Override
  public String toString() {
    return "Faculty{" +
        " id= " + id +
        ", color= " + color +
        ", name= " + name +
        "}";
  }

  @OneToMany(mappedBy = "faculty")
  @Schema(hidden = true) // Скрываем students в Swagger
  private List<Student> students;

  public List<Student> getStudents() {
    return students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }

  public Faculty orElseThrow(Object факультетНеНайден) {
    return null;
  }


}//
