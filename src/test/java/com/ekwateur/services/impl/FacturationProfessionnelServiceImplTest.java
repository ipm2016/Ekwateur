package com.ekwateur.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import com.ekwateur.models.Professionnel;
import com.ekwateur.repositories.ProfessionnelRepository;
import com.ekwateur.services.FacturationService;
import com.ekwateur.utils.EkwateurException;
import com.ekwateur.utils.enums.Energie;

import jakarta.transaction.Transactional;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@Transactional
@ActiveProfiles("test")
class FacturationProfessionnelServiceImplTest {

	
	@Autowired
	private ProfessionnelRepository professionnelRepository;
	
	@Qualifier("FacturationProfessionnelService")
	@Autowired
	private FacturationService facturationService;
	
	@BeforeAll
	void init() {
		Abonnement abonnementGaz1 = Abonnement.builder().type(Energie.GAZ).prixKwh(Float.valueOf("0.128")).build();
		Abonnement abonnementElec1 = Abonnement.builder().type(Energie.ELECTRICITE).prixKwh(Float.valueOf("0.110")).build();

		Abonnement abonnementGaz2 = Abonnement.builder().type(Energie.GAZ).prixKwh(Float.valueOf("0.112")).build();
		Abonnement abonnementElec2 = Abonnement.builder().type(Energie.ELECTRICITE).prixKwh(Float.valueOf("0.117")).build();

		
		Consommation consommation1 = Consommation.builder()
				.date(LocalDate.of(2024,5,9))
				.abonnement(abonnementGaz1)
				.qte(Float.valueOf(10)).build();
		
		Consommation consommation2 = Consommation.builder()
				.date(LocalDate.of(2024,5,10))
				.abonnement(abonnementGaz1)
				.qte(Float.valueOf(10)).build();
		
		Consommation consommation3 = Consommation.builder()
				.date(LocalDate.of(2024,5,11))
				.abonnement(abonnementGaz1)
				.qte(Float.valueOf(10)).build();
		
		Consommation consommation4 = Consommation.builder()
				.date(LocalDate.of(2024,5,9))
				.abonnement(abonnementElec2)
				.qte(Float.valueOf(22)).build();
		
		Consommation consommation5 = Consommation.builder()
				.date(LocalDate.of(2024,5,9))
				.abonnement(abonnementElec1)
				.qte(Float.valueOf(10)).build();
		
		
		Consommation consommation6 = Consommation.builder()
				.date(LocalDate.of(2024,4,9))
				.abonnement(abonnementElec1)
				.qte(Float.valueOf(56)).build();
		
		
		abonnementGaz1.setConsommations(List.of(consommation1,consommation2,consommation3));
		abonnementElec1.setConsommations(List.of(consommation5,consommation6));
		abonnementElec2.setConsommations(List.of(consommation4));
		
		Professionnel pro1 = Professionnel.builder().id(1L).abonnements(List.of(abonnementGaz1,abonnementElec1))
				.raisonSociale("pro1").ca(1100000).build();
		Professionnel pro2 = Professionnel.builder().id(2L).abonnements(List.of(abonnementElec2))
				.raisonSociale("pro2").ca(100000).build();

		
		professionnelRepository.saveAll(List.of(pro1,pro2));

		
	}
	
	@Test
	void testProChiffreAffaireSup1million() throws EkwateurException {
		FactureDto result = facturationService.getFacture(Energie.ELECTRICITE, 5, 2024, 1L);
		assertEquals(1.1f, result.getMontant());
	}
	
	
	@Test
	void testProChiffreAffaireInf1million() throws EkwateurException {
		assertThrows(EkwateurException.class,() ->  facturationService.getFacture(Energie.GAZ, 5, 2024, 2L),"Utilisateur avec id 2 n'a pas d'abonnement pour l'énergie GAZ");
		
		FactureDto result = facturationService.getFacture(Energie.ELECTRICITE, 5, 2024, 2L);
		assertEquals(2.574f, result.getMontant());
	}
	
	

}
