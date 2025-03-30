package ru.hogwarts.school.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
  //save() сохраняет изменения в БД;
  //findOne() находит первый элемент, который удовлетворяет определенным условиям;
  //findById() используется, если необходимо совершить поиск по идентификатору;
  //findAll() возвращает все записи из таблицы;
  //count() возвращает число int — количество записей в таблице;
  //deleteById() удаляет сущность из БД по ее идентификатору;

  Collection<Faculty> findByNameIgnoreCase(String name);
  Collection<Faculty> findByColorIgnoreCase(String color);

}
