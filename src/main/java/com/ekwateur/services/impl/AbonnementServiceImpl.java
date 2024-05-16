package com.ekwateur.services.impl;

import org.springframework.stereotype.Service;

import com.ekwateur.models.Abonnement;
import com.ekwateur.models.Client;
import com.ekwateur.models.Particulier;
import com.ekwateur.models.Professionnel;
import com.ekwateur.services.AbonnementService;
import com.ekwateur.utils.enums.Energie;

@Service
public class AbonnementServiceImpl implements AbonnementService {

	
	@Override
	public float getPrixKwh(Client client, Energie energie) {
		if (client instanceof Particulier p)
			return p.getAbonnements().stream().filter(a -> energie.equals(a.getType())).map(Abonnement::getPrixKwh)
					.findFirst().orElse(Float.valueOf(0));
		else
			return ((Professionnel) client).getAbonnements().stream().filter(a -> energie.equals(a.getType()))
					.map(Abonnement::getPrixKwh).findFirst().orElse(Float.valueOf(0));
	}

}
