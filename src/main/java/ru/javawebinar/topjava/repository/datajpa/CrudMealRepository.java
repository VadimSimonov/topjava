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
    @Modifying
    int deleteByIdAndUserId(int id,int userId);
/*
    @Modifying
    @Transactional
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);
*/
    List<Meal>findAllByUserIdOrderByDateTimeDesc(int userId);
/*
    @Query("SELECT m from Meal m WHERE m.user.id=:userId and m.dateTime between :startDate and :endDate")
    List<Meal> getBetween(@Param("userId") int userId,@Param("startDate") LocalDateTime startDate,
                          @Param("endDate") LocalDateTime endDate);
    */
    List<Meal> findAllByUserIdAndDateTimeBetweenOrderByDateTimeDesc(int userId, LocalDateTime startDate, LocalDateTime endDate);

    @Transactional
    @Modifying
    @Query("UPDATE Meal m set m=:meal where m.user.id=:userId")
    Meal update(@Param("meal") Meal meal,@Param("userId") int userId);


}
