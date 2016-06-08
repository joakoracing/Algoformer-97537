package fiuba.algo3.modelo.algoformers;

import fiuba.algo3.modelo.algoformers.Autobot;
import fiuba.algo3.modelo.movimientos.MovimientoHumanoide;
import fiuba.algo3.modelo.movimientos.MovimientoAlternoTerrestre;
import fiuba.algo3.modelo.excepciones.TransformacionIncorresctaYaEsHumanoideExcepcion;
import fiuba.algo3.modelo.excepciones.TransformacionIncorresctaYaEsAlternoExcepcion;

public class Optimus extends Autobot {
	private static final int ATAQUE_HUMANOIDE = 50;
	private static final int DISTANCIA_ATAQUE_HUMANOIDE = 2;
	private static final int VELOCIDAD_HUMANOIDE = 2;
	private static final int ATAQUE_ALTERNO = 15;
	private static final int DISTANCIA_ATAQUE_ALTERNO = 4;
	private static final int VELOCIDAD_ALTERNO = 5;
	private static final int VIDA = 500;
	
	public Optimus() {
		this.vida = VIDA;
		this.ataque = ATAQUE_HUMANOIDE;
		this.distanciaDeAtaque = DISTANCIA_ATAQUE_HUMANOIDE;
		this.velocidad = VELOCIDAD_HUMANOIDE;
		this.movimiento = new MovimientoHumanoide();
		this.modo = "humanoide";
	}
	
	public void transformarHumanoide() {
		this.validarQueNoSoyHumanoide();
		
		this.movimiento = new MovimientoHumanoide();
		this.ataque = ATAQUE_HUMANOIDE;
		this.distanciaDeAtaque = DISTANCIA_ATAQUE_HUMANOIDE;
		this.velocidad = VELOCIDAD_HUMANOIDE;
		this.modo = "humanoide";
	}
	
	public void transformarAlterno(){
		this.validarQueNoSoyAlterno();
		
		this.movimiento = new MovimientoAlternoTerrestre();
		this.ataque = ATAQUE_ALTERNO;
		this.distanciaDeAtaque = DISTANCIA_ATAQUE_ALTERNO;
		this.velocidad = VELOCIDAD_ALTERNO;
		this.modo = "alterno";
	}

}
