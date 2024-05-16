package com.ekwateur.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekwateur.models.Consommation;
import com.ekwateur.repositories.ConsommationRepository;
import com.ekwateur.services.ConsommationService;

@Service
public class ConsommationServiceImpl implements ConsommationService {

	@Autowired
	private ConsommationRepository consommationRepository;
	
	@Override
	public List<Consommation> getByMonth(int month, int year, long abonnementId) {
		return consommationRepository.findByMonth(month, year, abonnementId);
	}
	
	
	@Override
	public float getPrice(List<Consommation> consommations, float prixKwh) {
		return consommations.stream().map(c -> c.getQte() * prixKwh).reduce(0f, (sumGloabal,result) ->  sumGloabal + result );
	}
	
	
	@Override
	public float getSumConsommation(List<Consommation> consomations) {
		return consomations.stream().map(Consommation::getQte).reduce(Float::sum).get();
	}

}
