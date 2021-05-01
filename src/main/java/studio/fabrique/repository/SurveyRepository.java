package studio.fabrique.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import studio.fabrique.model.Survey;

import java.util.List;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {

    /**
     * Метод получения списка всех активных опросов
     * @param pageable объект {@link Pageable} для постраничного вывода
     * @return список с активными опросами
     */
    @Query("from Survey s where s.endDate >= now()")
    List<Survey> finAllActiveSurveys(Pageable pageable);
}
