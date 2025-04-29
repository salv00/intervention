package com.vialis.intervention.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vialis.intervention.model.Intervention;

@Service
public class InterventionService {

	private final List<Intervention> interventions = new ArrayList<>();

	public InterventionService() {
		interventions.add(new Intervention(1L, "Gaz", "Fuite signalée au 23 rue des Lilas"));
		interventions.add(new Intervention(2L, "Electricité", "Panne générale dans le quartier Ouest"));
	}

	public List<Intervention> findAll() {
		return interventions;
	}

	public Intervention findById(Long id) {
		return interventions.stream().filter(i -> i.getId().equals(id)).findFirst().orElse(null);
	}
}
