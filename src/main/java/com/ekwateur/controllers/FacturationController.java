package com.ekwateur.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ekwateur.dtos.FacturationRequest;
import com.ekwateur.dtos.FactureDto;
import com.ekwateur.services.FacturationService;
import com.ekwateur.utils.EkwateurException;

@RestController
@RequestMapping("/billing")
public class FacturationController {

	@Qualifier("FacturationParticulierService")
	@Autowired
	private FacturationService facturationParticulierService;
	
	@Qualifier("FacturationProfessionnelService")
	@Autowired
	private FacturationService facturationProService;
	
	
	@GetMapping("/particulier/{id}")
	public ResponseEntity<FactureDto> getFactureParticulier(@PathVariable(required = true) long id,
			@RequestBody(required = true) FacturationRequest request) {
		try {
			FactureDto facture = facturationParticulierService.getFacture(request.getEnergy(), request.getMonth(), request.getYear(), id);
			return ResponseEntity.ok(facture);
		} catch (EkwateurException e) {
			return new ResponseEntity<FactureDto>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/professionnel/{id}")
	public ResponseEntity<FactureDto> getFactureProfessionnel(@PathVariable(required = true) long id,
			@RequestBody(required = true) FacturationRequest request) {
		try {
			FactureDto facture = facturationProService.getFacture(request.getEnergy(), request.getMonth(), request.getYear(), id);
			return ResponseEntity.ok(facture);
		} catch (EkwateurException e) {
			return new ResponseEntity<FactureDto>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
