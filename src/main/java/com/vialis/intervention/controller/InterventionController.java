package com.vialis.intervention.controller;

import com.vialis.intervention.model.Intervention;
import com.vialis.intervention.service.InterventionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interventions")
public class InterventionController {

	private final InterventionService interventionService;

	public InterventionController(InterventionService interventionService) {
		this.interventionService = interventionService;
	}

	@GetMapping
	public List<Intervention> getAllInterventions() {
		return interventionService.findAll();
	}

	@GetMapping("/{id}")
	public Intervention getInterventionById(@PathVariable Long id) {
		return interventionService.findById(id);
	}
}
