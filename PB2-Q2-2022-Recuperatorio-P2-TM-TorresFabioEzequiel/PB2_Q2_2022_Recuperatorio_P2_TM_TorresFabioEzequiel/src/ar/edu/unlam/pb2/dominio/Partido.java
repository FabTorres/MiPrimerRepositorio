package ar.edu.unlam.pb2.dominio;

public class Partido {
	private Integer id;
	private Equipo equipoLocal;
	private Equipo equipoVisitante;
	private TipoPartido tipoPartido;
	private Integer golesAFavor;
	private Integer golesEnContra;
	
	public Partido(){
		
	}
	
	public Partido(Integer id){
		this.id = id;
	}
	
	public Partido(Integer id, Equipo local, Equipo visitante, TipoPartido tipo){
		this.id = id;
		this.equipoLocal = local;
		this.equipoVisitante = visitante;
		this.tipoPartido = tipo;
		this.golesAFavor = 0;
		this.golesEnContra = 0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Equipo getEquipoLocal() {
		return equipoLocal;
	}

	public void setEquipoLocal(Equipo equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	public Equipo getEquipoVisitante() {
		return equipoVisitante;
	}

	public void setEquipoVisitante(Equipo equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}

	public TipoPartido getTipoPartido() {
		return tipoPartido;
	}

	public void setTipoPartido(TipoPartido tipoPartido) {
		this.tipoPartido = tipoPartido;
	}
	

	public void setGolesEquipoLocal(Integer golesAFavor) {
		this.golesAFavor = golesAFavor;
		
	}

	public void setGolesEquipoRival(Integer golesEnContra) {
		this.golesEnContra = golesEnContra;
		
	}


	public void sumarGolesAEquipo() {
		
	}

	public void sumarGolesAEquipoLocal(Integer golesLocal, Integer golesVisitante) {
		this.equipoLocal.setGolesAFavor(golesLocal);
		this.equipoLocal.setGolesEnContra(golesVisitante);
	}
	
	public void sumarGolesAEquipoRival(Integer golesLocal, Integer golesVisitante) {
		this.equipoLocal.setGolesAFavor(golesVisitante);
		this.equipoLocal.setGolesEnContra(golesLocal);
	}

	public void obtenerResultado() {
		if(this.golesAFavor > this.golesEnContra){
			this.equipoLocal.setGana(Gana.GANA_LOCAL);
		}
		
		if(this.golesEnContra > this.golesAFavor){
			this.equipoVisitante.setGana(Gana.GANA_VISITANTE);
		}
		
		if(this.golesAFavor.equals(golesEnContra)){
			this.equipoLocal.setGana(Gana.EMPATE);
			this.equipoVisitante.setGana(Gana.EMPATE);
		}
	}
	
	
	}
	

