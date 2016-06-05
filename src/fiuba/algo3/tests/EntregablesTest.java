package fiuba.algo3.tests;

import java.io.FileReader;

import fiuba.algo3.modelo.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

public class EntregablesTest {
	
	// Primera entrega Jueves 02/06/2016 - Lunes 06/06/2016
	
	@Test
	public void testAgregarAlgoformerHumanoideMoverYVerificarPosicion() throws FileNotFoundException, 
	 IOException, ParseException {

		JSONParser parser = new JSONParser();
		JSONObject jsonTablero = (JSONObject) parser.parse(new FileReader("mapas/1.json"));
		Tablero tablero = new Tablero(jsonTablero);
		Algoformer prime = new Optimus();

		Posicion nuevaPosicion = new Posicion(3, 1);
		Posicion ultimaPosicion = new Posicion(5, 1);
		
		try {prime.transformarHumanoide();}
		catch(Exception e){}
		
		tablero.agregarAlgoformerHumanoideSinEfectoDeSuperficie(prime, nuevaPosicion);
		tablero.moverAlgoformerHumanoidesinEfectoDeLaSuperficie(prime,ultimaPosicion);

		Posicion auxPosicion = prime.getPosicion();
		
		Assert.assertEquals(ultimaPosicion.obtenerPosicionX(), auxPosicion.obtenerPosicionX());
		Assert.assertEquals(ultimaPosicion.obtenerPosicionY(), auxPosicion.obtenerPosicionY());
		
	}
	
	@Test
	public void testAgregarAlgoformerAlternoMoverYVerificarPosicion() throws FileNotFoundException, 
	 IOException, ParseException {

		JSONParser parser = new JSONParser();
		JSONObject jsonTablero = (JSONObject) parser.parse(new FileReader("mapas/1.json"));
		Tablero tablero = new Tablero(jsonTablero);
		Algoformer prime = new Optimus();
		Posicion nuevaPosicion = new Posicion(3,1);
		Posicion ultimaPosicion = new Posicion(5,1);

		try {prime.transformarAlterno();}
		catch(Exception e){}
		
		tablero.agregarAlgoformerHumanoideSinEfectoDeSuperficie(prime,nuevaPosicion);
		tablero.moverAlgoformerHumanoidesinEfectoDeLaSuperficie(prime,ultimaPosicion);

		Posicion auxPosicion = prime.getPosicion();
		
		Assert.assertEquals(ultimaPosicion.obtenerPosicionX(), auxPosicion.obtenerPosicionX());
		Assert.assertEquals(ultimaPosicion.obtenerPosicionY(), auxPosicion.obtenerPosicionY());
		
	}
	
	@Test(expected=MovimientoInvalidoDistanciaNoValida.class)
	public void testAgregarAlgoformerYCambiarDeModoEnAmbasDirecciones() throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		JSONObject jsonTablero = (JSONObject) parser.parse(new FileReader("mapas/1.json"));
		Tablero tablero = new Tablero(jsonTablero);
		Algoformer prime = new Optimus();
		Posicion nuevaPosicion = new Posicion(0,1);
		Posicion ultimaPosicionAlterno = new Posicion(4,1);
		
		try {prime.transformarAlterno();} //Optimus alterno tiene velocidad 5
		catch(Exception e){}
		
		tablero.agregarAlgoformerHumanoideSinEfectoDeSuperficie(prime,nuevaPosicion);
		tablero.moverAlgoformerHumanoidesinEfectoDeLaSuperficie(prime, ultimaPosicionAlterno);
		Posicion auxPosicion = prime.getPosicion();
		Assert.assertEquals(ultimaPosicionAlterno.obtenerPosicionX(), auxPosicion.obtenerPosicionX());
		Assert.assertEquals(ultimaPosicionAlterno.obtenerPosicionY(), auxPosicion.obtenerPosicionY());
		
		try {prime.transformarHumanoide();}
		catch(Exception e){}
		
		Posicion ultimaPosicionHumanoide = new Posicion (6,1);
		tablero.moverAlgoformerHumanoidesinEfectoDeLaSuperficie(prime, ultimaPosicionHumanoide);
		Posicion auxPosicion2 = prime.getPosicion();
		Assert.assertEquals(ultimaPosicionHumanoide.obtenerPosicionX(), auxPosicion2.obtenerPosicionX());
		Assert.assertEquals(ultimaPosicionHumanoide.obtenerPosicionY(), auxPosicion2.obtenerPosicionY());
		Posicion posicionInvalidaHumanoide = new Posicion (9,1);
		tablero.moverAlgoformerHumanoidesinEfectoDeLaSuperficie(prime, posicionInvalidaHumanoide);
		
	}

	@Test(expected=TransformacionIncorresctaYaEsHumanoide.class)
	public void testCrearAlgoformerHumanoideYCambiarAlMismoModo(){
		Algoformer prime = new Optimus();
		prime.transformarHumanoide();
	}

	@Test(expected=TransformacionIncorresctaYaEsAlterno.class)
	public void testCrearAlgoformerTransformarAAlternoYCambiarAlMismoModo(){
		Algoformer prime = new Optimus();
		prime.transformarAlterno();
		prime.transformarAlterno();
	}

	@Test
	public void testAutobotAtacaDecepticonConDanos() throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		JSONObject jsonTablero = (JSONObject) parser.parse(new FileReader("mapas/1.json"));
		Tablero tablero = new Tablero(jsonTablero);

		Optimus optimus = new Optimus();
		Frenzy frenzy = new Frenzy();

		Posicion posicion1 = new Posicion(0, 0);
		Posicion posicion2 = new Posicion(2, 2);

		tablero.agregarAlgoformerHumanoide(optimus, posicion1);
		tablero.agregarAlgoformerHumanoide(frenzy, posicion2);

		int vidaFrenzyEsperadaLuegoDeAtaque = frenzy.getVida() - optimus.getAtaque();

		Ataque ataque = new Ataque(tablero, posicion1, posicion2);

		// El primer ataque est� dentro de la distancia y debe restarle vida
		Assert.assertEquals(vidaFrenzyEsperadaLuegoDeAtaque, frenzy.getVida());

		posicion2.setCoordenadas(2, 3);
		tablero.moverAlgoformerHumanoidesinEfectoDeLaSuperficie(frenzy, posicion2);
		vidaFrenzyEsperadaLuegoDeAtaque = frenzy.getVida();
		ataque = new Ataque(tablero, posicion1, posicion2);

		Assert.assertEquals(vidaFrenzyEsperadaLuegoDeAtaque, frenzy.getVida()); // Este est� fuera de la distancia y no debe restarle

		posicion2.setCoordenadas(7, 7);
		tablero.moverAlgoformerHumanoidesinEfectoDeLaSuperficie(frenzy, posicion2);
		vidaFrenzyEsperadaLuegoDeAtaque = frenzy.getVida();
		ataque = new Ataque(tablero, posicion1, posicion2);

		Assert.assertEquals(vidaFrenzyEsperadaLuegoDeAtaque, frenzy.getVida()); // Este est� fuera de la distancia y no debe restarle

		posicion2.setCoordenadas(1, 1);
		tablero.moverAlgoformerHumanoidesinEfectoDeLaSuperficie(frenzy, posicion2);
		vidaFrenzyEsperadaLuegoDeAtaque = frenzy.getVida() - optimus.getAtaque();;
		ataque = new Ataque(tablero, posicion1, posicion2);

		Assert.assertEquals(vidaFrenzyEsperadaLuegoDeAtaque, frenzy.getVida()); // Este est� dentro de la distancia y debe restarle

	}
}
