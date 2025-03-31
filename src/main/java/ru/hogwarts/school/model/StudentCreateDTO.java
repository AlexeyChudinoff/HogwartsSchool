package ru.hogwarts.school.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Student Create DTO", description = "Данные для создания студента")
public class StudentCreateDTO {

  @Schema(description = "Имя студента", example = "Гарри Поттер")
  private String name;

  @Schema(description = "Возраст студента", example = "17")
  private int age;

  @Schema(description = "Пол студента", example = "male")
  private String gender;

  @Schema(description = "ID факультета", example = "1")
  private Long facultyId;


  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public Long getFacultyId() {
    return facultyId;
  }

  public void setFacultyId(Long facultyId) {
    this.facultyId = facultyId;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}//
