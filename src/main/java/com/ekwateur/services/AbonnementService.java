package com.ekwateur.services;

import com.ekwateur.models.Client;
import com.ekwateur.utils.enums.Energie;

public interface AbonnementService {

	public float getPrixKwh(Client client, Energie energie);
	
}
