package com.ekwateur.services;

import com.ekwateur.dtos.FactureDto;
import com.ekwateur.utils.EkwateurException;
import com.ekwateur.utils.enums.Energie;

public interface FacturationService {

	FactureDto getFacture(Energie energie,int mois, int annee, Long clientId) throws EkwateurException;
	
	
}
