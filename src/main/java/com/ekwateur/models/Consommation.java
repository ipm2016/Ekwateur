package com.ekwateur.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "consommation")
@Builder
public class Consommation {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private LocalDate date;
	
	private float qte;
	
	@ManyToOne
	@JoinColumn(name = "abonnement_id")
	private Abonnement abonnement;
	
	
	
}
