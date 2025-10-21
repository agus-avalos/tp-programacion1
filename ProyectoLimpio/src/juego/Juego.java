package juego;


import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	Cuadricula cua;
	Regalo[] regalos;
	Planta[]plantas;
	private int tiempoplanta=0;
	private int tiempoplantafinal=180;
	Image fondoplantas;
	// Variables y métodos propios de cada grupo
	// ...

	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		
		this.fondoplantas = Herramientas.cargarImagen("fondo.png");

		
		cua=new Cuadricula(50,150,entorno);
		regalos=new Regalo[5];
		for(int i=0; i < 5; i++) {
			regalos[i]=new Regalo(50, 150+i*100,entorno);
		}
		plantas=new Planta[15];
		plantas[0]=new Planta(50,50,entorno);		
		// Inicializar lo que haga falta para el juego
		// ...

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		// Procesamiento de un instante de tiempo
		// ...
		entorno.dibujarImagen(fondoplantas, 400, 35,0);
		 
		cua.dibujar();
		for(Regalo r:this.regalos) {
			r.dibujar();

		}

		for(int ite=0;ite < plantas.length;ite ++ ) {
			if(plantas[ite] != null) {
				plantas[ite].dibujar();
			}
		}

		if(entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
			if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
			    boolean seleccionoAlguien = false;
			    for (int i = 0; i < plantas.length; i++) {
			        if (plantas[i] != null && plantas[i].encima(entorno.mouseX(), entorno.mouseY())) {
			            for (int j = 0; j < plantas.length; j++) {
			                if (plantas[j] != null)
			                    plantas[j].seleccionada = false;
			            }
			            plantas[i].seleccionada = true;
			            if (plantas[i].plantada) {
			                int ix = cua.cercano(plantas[i].x, plantas[i].y).x;
			                int iy = cua.cercano(plantas[i].x, plantas[i].y).y;
			                cua.ocupado[ix][iy] = false;
			                plantas[i].plantada = false;
			            }
			            seleccionoAlguien = true;
			            break;
			        }
			    }
			    if (!seleccionoAlguien) {
			        for (int i = 0; i < plantas.length; i++) {
			            if (plantas[i] != null) {
			                plantas[i].seleccionada = false;
			            }
			        }
			    }
			}
		}

		if (entorno.estaPresionado(entorno.BOTON_IZQUIERDO)) {
		    for (int i = 0; i < plantas.length; i++) {
		        if (plantas[i] != null && plantas[i].seleccionada) {
		            plantas[i].arrastrar(entorno.mouseX(), entorno.mouseY());
		        }
		    }
		}

		if (entorno.seLevantoBoton(entorno.BOTON_IZQUIERDO)) {
		    for (int i = 0; i < plantas.length; i++) {
		        if (plantas[i] != null && plantas[i].seleccionada) {
		            if (entorno.mouseY() < 70) {
		                plantas[i].arrastrar(50, 50);
		                plantas[i].plantada = false;
		            } else {
		                int ix = cua.cercano(entorno.mouseX(), entorno.mouseY()).x;
		                int iy = cua.cercano(entorno.mouseX(), entorno.mouseY()).y;
		                if (!cua.ocupado[ix][iy]) {
		                    plantas[i].arrastrar(cua.corX[ix], cua.corY[iy]);
		                    cua.ocupado[ix][iy] = true;
		                    plantas[i].plantada = true;
		                } else {
		                    plantas[i].arrastrar(50, 50);
		                    plantas[i].plantada = false;
		                }
		            }
		        }
		    }
		}
		for(int ite=0; ite < plantas.length;ite++) {
			if(plantas[ite] != null) {
				if(entorno.sePresiono(entorno.TECLA_ARRIBA)){

					if(this.plantas[ite].seleccionada && this.plantas[ite].plantada) {
						int indiceX = cua.cercano(plantas[ite].x, plantas[ite].y).x;
						int indiceY = cua.cercano(plantas[ite].x, plantas[ite].y).y;
						if(indiceY >= 1 && !this.cua.ocupado[indiceX][indiceY-1]) {

							this.cua.ocupado[indiceX][indiceY] = false;
							this.cua.ocupado[indiceX][indiceY-1] = true;
							this.plantas[ite].y -= 100 ;
						}
					}
				}

				if(entorno.sePresiono(entorno.TECLA_ABAJO)){
					if(this.plantas[ite].seleccionada && this.plantas[ite].plantada ) {
						int indiceX = cua.cercano(plantas[ite].x, plantas[ite].y).x;
						int indiceY = cua.cercano(plantas[ite].x, plantas[ite].y).y;
						if(indiceY <= 3 && !this.cua.ocupado[indiceX][indiceY+1]) {

							this.cua.ocupado[indiceX][indiceY] = false;
							this.cua.ocupado[indiceX][indiceY+1] = true;
							this.plantas[ite].y += 100 ;
						}
					}	
				}

				if(entorno.sePresiono(entorno.TECLA_DERECHA)) {
					if(this.plantas[ite].seleccionada && this.plantas[ite].plantada) {
						int indiceX = cua.cercano(plantas[ite].x, plantas[ite].y).x;
						int indiceY = cua.cercano(plantas[ite].x, plantas[ite].y).y;
						if(indiceX <= 6 && !this.cua.ocupado[indiceX+1][indiceY]) {

							this.cua.ocupado[indiceX][indiceY] = false;
							this.cua.ocupado[indiceX+1][indiceY] = true;
							this.plantas[ite].x += 100 ;
						}

					}
				}

				if(entorno.sePresiono(entorno.TECLA_IZQUIERDA)) {
					if(this.plantas[ite].seleccionada && this.plantas[ite].plantada) {
						int indiceX = cua.cercano(plantas[ite].x, plantas[ite].y).x;
						int indiceY = cua.cercano(plantas[ite].x, plantas[ite].y).y;
						if(indiceX > 1 && !this.cua.ocupado[indiceX-1][indiceY]) {

							this.cua.ocupado[indiceX][indiceY] = false;
							this.cua.ocupado[indiceX-1][indiceY] = true;
							this.plantas[ite].x -= 100 ;
						}
					}	
				}
			}
		}

		if(!plantasNoPlantadas(this.plantas)) {
			tiempoplanta++;
			if(tiempoplanta >= tiempoplantafinal) {
			crearPlanta(this.plantas);
			tiempoplanta=0;
			}
		}
	}

	public boolean plantasNoPlantadas(Planta[] pl) {
		for(Planta p:pl) {
			if(p != null && !p.plantada) {
				return true;
			}
		}
		return false;
	}

	private void crearPlanta(Planta[] pl) {

		for(int x=0; x < pl.length;x++) {
			if(pl[x] == null) {
				pl[x] = new Planta(50,50,entorno);
				return;
			}
		}
	}
	


	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
