package com.ekwateur.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.ekwateur.dtos.FactureDto;
import com.ekwateur.models.Abonnement;
import com.ekwateur.models.Consommation;
import com.ekwateur.models.Particulier;
import com.ekwateur.repositories.ParticulierRepository;
import com.ekwateur.services.FacturationService;
import com.ekwateur.utils.EkwateurException;
import com.ekwateur.utils.enums.CIVILITE;
import com.ekwateur.utils.enums.Energie;

import jakarta.transaction.Transactional;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@Transactional
@ActiveProfiles("test")
class FacturationParticulierServiceImplTest {

	@Autowired
	private ParticulierRepository particulierRepository;
	
	@Qualifier("FacturationParticulierService")
	@Autowired
	private FacturationService facturationService;
	
	@BeforeAll
	void init() {

		Abonnement abonnement = Abonnement.builder().type(Energie.GAZ).prixKwh(Float.valueOf("0.108")).build();

		Abonnement abonnementElec = Abonnement.builder().type(Energie.ELECTRICITE).prixKwh(Float.valueOf("0.133"))
				.build();

		Consommation consommation1 = Consommation.builder().date(LocalDate.of(2024, 5, 9)).abonnement(abonnement)
				.qte(Float.valueOf(10)).build();

		Consommation consommation2 = Consommation.builder().date(LocalDate.of(2024, 5, 9)).abonnement(abonnement)
				.qte(Float.valueOf(10)).build();

		Consommation consommation3 = Consommation.builder().date(LocalDate.of(2024, 5, 9)).abonnement(abonnement)
				.qte(Float.valueOf(10)).build();

		Consommation consommation4 = Consommation.builder().date(LocalDate.of(2024, 5, 9)).abonnement(abonnement)
				.qte(Float.valueOf(10)).build();

		Consommation consommation5 = Consommation.builder().date(LocalDate.of(2024, 5, 9)).abonnement(abonnement)
				.qte(Float.valueOf(10)).build();

		Consommation consommation6 = Consommation.builder().date(LocalDate.of(2024, 4, 9)).abonnement(abonnementElec)
				.qte(Float.valueOf(56)).build();

		List<Consommation> consommations = List.of(consommation1, consommation2, consommation3, consommation4,
				consommation5);
		abonnement.setConsommations(consommations);
		abonnementElec.setConsommations(List.of(consommation6));
		Particulier particulier = Particulier.builder().nom("user1").prenom("prenom1")
				.civilite(CIVILITE.MONSIEUR)
				.abonnements(List.of(abonnement, abonnementElec))
				.build();

		particulierRepository.save(particulier);

	}

	
	@Test
	void testgetfactureGAZClientParticulier() throws EkwateurException {
		FactureDto result = facturationService.getFacture(Energie.GAZ,5,2024,1L);
		 assertEquals(5.4f, result.getMontant());
	}
	
	@Test
	void testgetfactureElectriciteClientParticulier() throws EkwateurException {
		 FactureDto result2 = facturationService.getFacture(Energie.ELECTRICITE,4,2024,1L);
		 assertEquals(7.448f, result2.getMontant());
	}
	
	
	
}
