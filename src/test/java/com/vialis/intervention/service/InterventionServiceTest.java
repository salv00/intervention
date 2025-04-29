package com.vialis.intervention.service;

import com.vialis.intervention.model.Intervention;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InterventionServiceTest {

    @Test
    void shouldReturnAllInterventions() {
        InterventionService service = new InterventionService();
        assertThat(service.findAll()).hasSize(2);
    }

    @Test
    void shouldReturnInterventionById() {
        InterventionService service = new InterventionService();
        Intervention intervention = service.findById(1L);
        assertThat(intervention).isNotNull();
        assertThat(intervention.getType()).isEqualTo("Gaz");
    }
}