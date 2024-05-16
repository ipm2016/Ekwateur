package com.ekwateur.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "professionnel")
public class Professionnel extends Client {

	private String numSiret;
	
	private String raisonSociale;
	
	private long ca;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Abonnement> abonnements;

	@Builder
	public Professionnel(Long id, String referenceCLient, String numSiret, String raisonSociale, long ca,
			List<Abonnement> abonnements) {
		super(id, referenceCLient);
		this.numSiret = numSiret;
		this.raisonSociale = raisonSociale;
		this.ca = ca;
		this.abonnements = abonnements;
	}

	
	
}
