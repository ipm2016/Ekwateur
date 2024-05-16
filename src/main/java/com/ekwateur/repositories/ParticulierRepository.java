package com.ekwateur.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ekwateur.models.Particulier;

public interface ParticulierRepository extends JpaRepository<Particulier, Long> {

}
