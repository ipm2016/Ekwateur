package com.ekwateur.services;

import java.util.List;

import com.ekwateur.models.Consommation;

public interface ConsommationService {

	List<Consommation> getByMonth(int month, int year, long abonnementId);
	
	float getPrice(List<Consommation> consommations, float prixKwh);

	float getSumConsommation(List<Consommation> consomations);
	
}
