package com.ekwateur.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekwateur.dtos.FactureDto;
import com.ekwateur.models.Abonnement;
import com.ekwateur.models.Consommation;
import com.ekwateur.models.Professionnel;
import com.ekwateur.repositories.ProfessionnelRepository;
import com.ekwateur.services.ConsommationService;
import com.ekwateur.services.FacturationService;
import com.ekwateur.utils.EkwateurException;
import com.ekwateur.utils.FacturationUtils;
import com.ekwateur.utils.enums.Energie;

@Service("FacturationProfessionnelService")
public class FacturationProfessionnelServiceImpl implements FacturationService {

	@Autowired
	private ProfessionnelRepository proRepository;
	
	@Autowired
	private ConsommationService consommationService;

	
	@Override
	public FactureDto getFacture(Energie energie, int mois, int annee, Long clientId) throws EkwateurException {

		Optional<Professionnel> optPro = proRepository.findById(clientId);
		if (optPro.isEmpty()) {
			throw new EkwateurException("Utilisateur Pro avec id {} introuvable ".formatted(clientId));
		}
		Professionnel client = optPro.get();

		Abonnement abonnement = getAbonnementbyEnergyType(energie, client);
		
		List<Consommation> consommationMois = consommationService.getByMonth(annee, mois, abonnement.getId());
		return FacturationUtils.buildResponse(energie, mois, annee, client, abonnement, consommationService.getPrice(consommationMois, abonnement.getPrixKwh()) ,consommationService.getSumConsommation(consommationMois));
	}


	private Abonnement getAbonnementbyEnergyType(Energie energie, Professionnel client)
			throws EkwateurException {
		Abonnement abonnement = client.getAbonnements().stream().filter(a -> energie.equals(a.getType()))
				.findFirst().orElse(null);

		if(null == abonnement) {
			throw new EkwateurException("Utilisateur avec id {} n'a pas d'abonnement pour l'énergie {}".formatted(client.getId(),energie));
		}
		return abonnement;
	}

}
