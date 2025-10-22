package juego;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Zombie { 
	double x,y,escala,ancho,alto; 
	Image imagen; 
	Entorno e; 
	
	public Zombie(Entorno e) { 
		this.e = e; 
		this.escala = 0.8;
		this.imagen =Herramientas.cargarImagen("zombie.png");
		this.x = e.ancho() ;//e.ancho usa todo el ancho de la cuadricula (en este caso 800) para representar la cordenada x
		int fila = (int) (Math.random() * 5); // con este random se decide la cordenada y usando valores entre 0 y 4
		 if (fila == 0) {
		   this.y = 150; } 
		 else if (fila == 1) {
		   this.y = 250; } 
		 else if (fila == 2) { 
		   this.y = 350; } 
		 else if (fila == 3) { 
		   this.y = 450; } 
		 else if (fila == 4) { 
		   this.y = 550; }
		
	}
	public void dibujar() { 
		e.dibujarImagen(imagen, x, y, 0,escala);
	}
}