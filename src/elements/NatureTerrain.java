package elements;

import java.awt.Color;

public enum NatureTerrain {
	//Objets directement construits
	EAU("EAU", Color.BLUE, "images/elements/eau.jpg"),
    FORET("FORET", Color.GREEN, "images/elements/foret.jpg"),
    ROCHE("ROCHE", Color.GRAY, "images/elements/roche.jpg"),
    TERRAIN_LIBRE("TERRAIN_LIBRE", Color.YELLOW, "images/elements/terrainLibre.jpg"),
    HABITAT("HABITAT", Color.WHITE, "images/elements/habitat.jpg");
	
	private String nom;
	
	private Color terrainColor;
	
	private String fileName;
	// constructeur
	NatureTerrain(String nom, Color terrainColor, String fileName) {
		this.nom = nom;
		this.terrainColor = terrainColor;
		this.fileName = fileName;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public Color getTerrainColor() {
		return this.terrainColor;
	}
	
	public String getFileName() {
		return this.fileName;
	}
}
