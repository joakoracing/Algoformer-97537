package fiuba.algo3.modelos.superficies;

import fiuba.algo3.modelos.algoformers.Algoformer;
import fiuba.algo3.modelos.excepciones.MovimientoInvalidoIncapazDeAtravezarSuperficieExcepcion;

public class Pantano extends Tierra {
	public static final String NOMBRE_JSON = "Pantano";
	static final int TURNOS_ATRAPADO = 1;

	@Override
	public void afectarAlgoformerAlterno(Algoformer algoformer) {
		//if(!algoformer.movimientoDisminuido())
			algoformer.disminuirMovimientoEsteTurno();
	}

	@Override
	public void afectarAlgoformerHumanoide(Algoformer algoformer) {
		throw new MovimientoInvalidoIncapazDeAtravezarSuperficieExcepcion();
	} 


}