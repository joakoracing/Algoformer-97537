package fiuba.algo3.modelo.superficies;

import fiuba.algo3.modelo.algoformers.Algoformer;

public class NebulosaDeAndromeda extends Aire{
	public static final int TURNOS_ATRAPADO = 3;
	
	public NebulosaDeAndromeda(){
		this.superficie = NEBULOSA_DE_ANDROMEDA;
	}

	@Override
	public void afectarAlgoformer(Algoformer algoformer) {
		algoformer.establecerTurnosAtrapado(TURNOS_ATRAPADO);
		
	}
}
