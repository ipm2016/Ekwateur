package com.ekwateur.utils;

import com.ekwateur.dtos.FactureDto;
import com.ekwateur.models.Abonnement;
import com.ekwateur.models.Client;
import com.ekwateur.utils.enums.Energie;

public final class FacturationUtils {
	
	
	private FacturationUtils() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");

	}
	
	public static FactureDto buildResponse(Energie energie, int mois, int annee, Client client, Abonnement abonnement,
			float montant, float consommation) {
		return FactureDto.builder().refClient(client.getReferenceCLient()).montant(montant).mois(mois).annee(annee)
				.qte(consommation).prixKwh(abonnement.getPrixKwh()).build();
	}

}
