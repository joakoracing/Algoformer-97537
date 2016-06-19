package fiuba.algo3.modelo.algoformers;


import fiuba.algo3.modelo.Posicion;
import fiuba.algo3.modelo.Tablero;
import fiuba.algo3.modelo.movimientos.Movimiento;
import fiuba.algo3.modelo.movimientos.MovimientoHumanoide;


public abstract class Modo {
    protected final Tablero tablero;
    protected Algoformer algoformer;
    protected Movimiento movimiento;
    protected int ataque;

    public Modo(Algoformer algoformer, Tablero tablero) {
        this.algoformer = algoformer;
        this.tablero = tablero;
        this.movimiento = new MovimientoHumanoide(this.tablero);
        this.ataque = this.getAtaqueInicial();
    }

    public boolean esHumanoide() {
        return false;
    }

    public boolean esAlterno() {
        return false;
    }

    public abstract int getAtaqueInicial();
    public abstract int getDistanciaAtaque();
    public abstract int getVelocidad();

    public void mover(Posicion posicion) {
        this.movimiento.mover(this.algoformer, posicion);
    }

	public int getAtaque() {
		return this.ataque;
	}

	public void afectarAtaque(int nuevoAtaque) {
		this.ataque = nuevoAtaque;
	}
}
