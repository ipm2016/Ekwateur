package com.ekwateur.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ekwateur.models.Consommation;

public interface ConsommationRepository extends JpaRepository<Consommation, Long> {

	
    @Query("SELECT c FROM Consommation c WHERE  c.abonnement.id = :abonnement_id AND  YEAR(c.date) = :year AND MONTH(c.date) = :month")
    List<Consommation> findByMonth(@Param("year") int year, @Param("month") int month, @Param("abonnement_id") long abonnement_id);

	
}
