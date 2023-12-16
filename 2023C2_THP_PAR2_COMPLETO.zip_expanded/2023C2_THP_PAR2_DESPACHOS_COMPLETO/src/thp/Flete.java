package thp;

import java.util.ArrayList;

public class Flete {
	private double PORCENTAJE_UMBRAL_LLENADO = 80;

	private String patente;
	private double cargaMaxima;
	private double umbralEnPeso;
	private double cargaActual;
	private Sucursal destino;
	private ArrayList<Paquete> paquetes;

	/**
	 * Este constructor inicializa un flete.
	 * 
	 * @param patente
	 * @param cargaMaxima
	 */
	public Flete(String patente, double cargaMaxima) {
		this.patente = patente;
		this.cargaMaxima = cargaMaxima;
		this.umbralEnPeso = cargaMaxima * PORCENTAJE_UMBRAL_LLENADO / 100;
		this.cargaActual = 0;
		this.destino = null;
		this.paquetes = new ArrayList<>();
	}

	/**
	 * Devuelve verdadero si la patente coincide con la patente del flete.
	 * 
	 * @param patente
	 * @return
	 */
	public boolean mismaPatente(String patente) {
		return this.patente.equals(patente);
	}

	/**
	 * Devuelve verdadero si la carga del flete es superior al umbral (80%).
	 * 
	 * @return
	 */
	public boolean esDespachable() {
		return cargaActual >= umbralEnPeso;
	}

	/**
	 * Devuelve verdadero si se cumplen las siguientes condiciones: - el flete no
	 * tiene asignado ning�n destino o en caso de tenerlo, coincide con el paquete.
	 * - el peso del paquete no har� sobrepasar la carga m�xima del flete.
	 * 
	 * @param paquete
	 * @return
	 */
	private boolean puedeCargar(Paquete paquete) {
		return destinoValido(paquete) && !superaTope(paquete.getPeso());
	}

	/**
	 * Indica si el paquete se puede cargar en el flete
	 * 
	 * @return Devuelve verdadero si la carga del flete agregando la nueva carga
	 *         supera el umbral (80%).
	 */
	private boolean superaTope(double pesoNuevaCarga) {
		return (cargaActual + pesoNuevaCarga) > cargaMaxima;
	}

	/**
	 * Indica si el paquete es para este Flete
	 * 
	 * @return Devuelve verdadero si el destino del viaje no está definido o si el
	 *         paquete es para el mismo destino.
	 */
	private boolean destinoValido(Paquete paquete) {
		return this.destino == null || this.destino.compareTo(paquete.getDestino()) == 0;
	}

	/**
	 * Este m�todo debe verificar que el flete sea capaz de llevar este paquete. En
	 * caso afirmativo debe asegurarse que el flete tenga seteado ese destino y debe
	 * registrar el paquete actualizando la carga del flete. En caso que no lo pueda
	 * cargar, debe devolver false
	 * 
	 * @param paquete
	 * @return
	 */
	public boolean cargarPaquete(Paquete paquete) {
		boolean ok = false;
		if (puedeCargar(paquete)) {
			paquetes.add(paquete);
			cargaActual += paquete.getPeso();
			destino = paquete.getDestino();
			ok = true;
		}
		return ok;
	}

	public void mostrarConPaqueteMasPesado() {
		System.out.println("Patente:" + patente + " Destino=" + destino + " " + paqueteMasPesado());
	}

	private Paquete paqueteMasPesado() {
		Paquete paquete = null;
		for (Paquete actual : paquetes) {
			if (paquete == null || actual.getPeso() > paquete.getPeso()) {
				paquete = actual;
			}
		}
		return paquete;
	}
	
	

}
