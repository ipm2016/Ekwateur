package com.ekwateur.models;

import java.util.List;

import com.ekwateur.utils.enums.CIVILITE;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "particulier")
public class Particulier extends Client {

	private String nom;
	
	private String prenom;
	
	@Enumerated(EnumType.STRING)
	private CIVILITE civilite;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Abonnement> abonnements;

	@Builder
	public Particulier(Long id, String referenceCLient, String nom, String prenom, CIVILITE civilite,
			List<Abonnement> abonnements) {
		super(id, referenceCLient);
		this.nom = nom;
		this.prenom = prenom;
		this.civilite = civilite;
		this.abonnements = abonnements;
	}

	
}
