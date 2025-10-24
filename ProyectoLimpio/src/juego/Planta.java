package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Planta {
	double x,y,escala,ancho,alto;
	Image imagen,imagenSeleccionada;
	Entorno e;
	boolean seleccionada;
	boolean plantada;
	int contadorDisparo;
	int tiempoDisparo=100; //ticks
	
	public Planta(double x, double y, Entorno e) {
		this.x = x;
		this.y = y;
		this.e = e;
		this.escala = 0.35;
		this.imagen = Herramientas.cargarImagen("rosa.png");
		this.imagenSeleccionada = Herramientas.cargarImagen("planta seleccionada.png");
		this.seleccionada = false;
		this.plantada = false;
	}
	
	public void dibujar() {
		if(seleccionada) {
			e.dibujarImagen(imagenSeleccionada, x, y, 0,escala);
		}
		e.dibujarImagen(imagen, x, y, 0, escala);
	}
	
	public double distancia(double x1, double y1, double x2, double y2) {
		return Math.sqrt( (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) );
	}
	
	public boolean encima(double xM, double yM) {
		return distancia(xM,yM,this.x,this.y) < 20;
	}
	
	public void arrastrar(double xM, double yM) {
		this.x = xM;
		this.y = yM;
	}
	
	public Proyectil atacar () {
		if (plantada) {
			contadorDisparo++;
			if (contadorDisparo >= tiempoDisparo) {  
				contadorDisparo=0;
				return new Proyectil(x+40,y,e); //general proyectil frente a la planta
				
			}
		}
		return null;
	}
	
	
}
