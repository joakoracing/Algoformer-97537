package fiuba.algo3.modelo;

public class MovimientoHumanoide extends Movimiento{
	
	@Override
	public void moverPosicionAlgoformer(Algoformer algoformer, Posicion nuevaPosicion) {
		this.tablero.moverAlgoformerHumanoide(algoformer,nuevaPosicion);
	}

}
