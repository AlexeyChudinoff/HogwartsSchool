package ru.hogwarts.school.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Objects;

@Entity(name = "student")
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(hidden = true)
  private Long id; // Изменили long → Long иначе
  // вылетала ошибка 500 long (примитивный тип),
  // что несовместимо с автоинкрементом в Hibernate
  private String name;
  private int age;
  private String gender;

  public Student() {}

  public Student(String name, int age, String gender) {
    this.name = name;
    this.age = age;
    this.gender = gender;
  }

// были проблемы с созданием студента с присвоением
// значения id, поэтому попробовал удалить из конструктора
// public Student(long id, String name, int age, String gender) {
//    this.id = id;
//    this.name = name;
//    this.age = age;
//    this.gender = gender;
//  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
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
    Student student = (Student) o;
    return id == student.id
        && age == student.age
        && Objects.equals(name, student.name)
        && Objects.equals(gender, student.gender);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, age, gender);
  }

  @Override
  public String toString() {
    return "Student{" +
        ", id= " + id +
        ", name= " + name +
        ", age= " + age +
        ", gender= " + gender +
         "}";
  }

  @ManyToOne
  @JoinColumn(name = "faculty_id")
  @JsonIdentityReference(alwaysAsId = true) // Сериализует Faculty только как ID
  @Schema(description = "ID факультета", type = "integer", example = "1")
  private Faculty faculty;

  public Faculty getFaculty() {
    return faculty;
  }

  public void setFaculty(Faculty faculty) {
    this.faculty = faculty;
  }


}