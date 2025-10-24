package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Proyectil {
	double x,y,velocidad,escala;
	Image imagen;
	Entorno e;
	boolean activo;

	public Proyectil (double x, double y, Entorno e) {
		this.x=x;
		this.y=y;
		this.e=e;
		this.velocidad=4.0; //velocidad en la que se mueve el proyectil (pixeles)
		this.escala=0.05;
		this.imagen = Herramientas.cargarImagen("ataque.png");
		this.activo=true; //el proyectil esta en pantalla
	}
		
	public void mover () {
		this.x += velocidad;
		if (this.x > e.ancho()) {
			this.activo=false; //si sale de pantalla desaparece
		}
		
	}
		
	public void dibujar () {
		if (activo) {
			e.dibujarImagen(imagen, x, y, 0, escala);
		}
	}
	
}
