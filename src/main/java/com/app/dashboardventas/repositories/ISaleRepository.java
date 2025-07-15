package com.app.dashboardventas.repositories;

import com.app.dashboardventas.models.entities.Sale;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT s FROM Sale s WHERE MONTH(s.date) = :month AND YEAR(s.date) = :year")
    List<Sale> findAllByMonthAndYear(@Param("month") Integer month, @Param("year") Integer year);
}
