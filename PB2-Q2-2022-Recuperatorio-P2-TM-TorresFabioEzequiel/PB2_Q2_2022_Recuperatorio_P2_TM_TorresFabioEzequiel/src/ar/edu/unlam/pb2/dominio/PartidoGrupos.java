package ar.edu.unlam.pb2.dominio;

public class PartidoGrupos extends Partido {
	
	private Integer id;
	private Equipo equipoLocal;
	private Equipo equipoVisitante;
	private TipoPartido tipoPartido;
	private Integer golesAFavor;
	private Integer golesEnContra;
	
	public PartidoGrupos(Integer id, Equipo local, Equipo visitante, TipoPartido tipo){
		super(id, local, visitante, tipo);
		
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
