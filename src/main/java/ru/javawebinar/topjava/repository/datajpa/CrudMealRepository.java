package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    Meal getByIdAndUserId(int id,int userId);

    @Transactional
    int deleteByIdAndUserId(int id,int userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);
/*
    @Query("select Meal FROM Meal m WHERE m.user.id=:userId")
    List<Meal> getAll(@Param("userId") int userId);
*/
    List<Meal>findAllByUserIdOrderByDateTimeDesc(int userId);


   // List getAllByDateIsBetweenAndUserId(LocalDateTime startDate, LocalDateTime endDate, int userId);

}
