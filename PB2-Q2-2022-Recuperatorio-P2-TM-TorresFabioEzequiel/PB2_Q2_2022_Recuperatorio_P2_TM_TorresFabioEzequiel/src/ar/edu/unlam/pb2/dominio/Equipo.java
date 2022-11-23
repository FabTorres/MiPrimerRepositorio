package ar.edu.unlam.pb2.dominio;

public class Equipo {
	private String nombre;
	private Seleccion seleccion;
	private Grupo grupo;
	private Integer golesAFavor;
	private Integer golesEnContra;
	private Integer puntos;
	private Gana gana;
	
	public Equipo(){
		
	}

	public Equipo(Seleccion seleccion, Grupo grupo) {
		super();
		this.seleccion = seleccion;
		this.golesAFavor = 0;
		this.golesEnContra = 0;
		this.puntos = 0;
		this.grupo = grupo;
		this.gana = null;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Seleccion getSeleccion() {
		return seleccion;
	}

	public void setSeleccion(Seleccion seleccion) {
		this.seleccion = seleccion;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Integer getGolesAFavor() {
		return golesAFavor;
	}

	public void setGolesAFavor(Integer golesAFavor) {
		this.golesAFavor += golesAFavor;
	}

	public Integer getGolesEnContra() {
		return golesEnContra;
	}

	public void setGolesEnContra(Integer golesEnContra) {
		this.golesEnContra += golesEnContra;
	}

	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos += puntos;
	}

	public Gana getGana() {
		return gana;
	}

	public void setGana(Gana gana) {
		this.gana = gana;
	}
	
	
}
