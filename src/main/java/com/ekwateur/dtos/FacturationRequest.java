package com.ekwateur.dtos;

import com.ekwateur.utils.enums.Energie;

import lombok.Data;

@Data
public class FacturationRequest {

	private Energie energy;
	
	private int month;
	
	private int year;
}
