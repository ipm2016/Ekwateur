package com.ekwateur.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekwateur.dtos.FactureDto;
import com.ekwateur.models.Abonnement;
import com.ekwateur.models.Consommation;
import com.ekwateur.models.Particulier;
import com.ekwateur.repositories.ParticulierRepository;
import com.ekwateur.services.ConsommationService;
import com.ekwateur.services.FacturationService;
import com.ekwateur.utils.EkwateurException;
import com.ekwateur.utils.FacturationUtils;
import com.ekwateur.utils.enums.Energie;

@Service("FacturationParticulierService")
public class FacturationParticulierServiceImpl implements FacturationService {

	@Autowired
	private ParticulierRepository particulierRepository;

	@Autowired
	private ConsommationService consommationService;

	@Override
	public FactureDto getFacture(Energie energie, int mois, int annee, Long clientId) throws EkwateurException {

		Optional<Particulier> optParticulier = particulierRepository.findById(clientId);
		if (optParticulier.isEmpty()) {
			throw new EkwateurException("Utilisateur avec id {} introuvable ".formatted(clientId));
		}
		Particulier client = optParticulier.get();

		Abonnement abonnement = getAbonnementbyEnergyType(energie, client);
		List<Consommation> consommationMois = consommationService.getByMonth(annee, mois, abonnement.getId());
		return FacturationUtils.buildResponse(energie, mois, annee, client, abonnement, consommationService.getPrice(consommationMois, abonnement.getPrixKwh()) ,consommationService.getSumConsommation(consommationMois));
	}


	private Abonnement getAbonnementbyEnergyType(Energie energie, Particulier client) throws EkwateurException {
		Abonnement abonnement = client.getAbonnements().stream().filter(a -> energie.equals(a.getType())).findFirst()
				.orElse(null);

		if (null == abonnement) {
			throw new EkwateurException(
					"Utilisateur avec id {} n'a pas d'abonnement pour l'énergie {}".formatted(client.getId(), energie));
		}
		return abonnement;
	}
	
}
