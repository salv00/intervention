package com.vialis.intervention.model;

public class Intervention {
	
    private Long id;
    private String type;
    private String description;

    public Intervention(Long id, String type, String description) {
        this.id = id;
        this.type = type;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}