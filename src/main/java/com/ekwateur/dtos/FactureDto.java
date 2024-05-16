package com.ekwateur.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FactureDto {

	
	private String refClient;
	
	private int mois;
	
	private int annee;
	
	private float qte;
	
	private float prixKwh;
	
	private float montant;
	
}
