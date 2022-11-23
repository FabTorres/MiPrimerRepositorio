package ar.edu.unlam.pb2.dominio;

public class PartidoEliminatoria extends Partido {
	
	public PartidoEliminatoria(){
		
	}
	
	public PartidoEliminatoria(Integer id, Equipo local, Equipo visitante, TipoPartido tipo){
		super(id, local, visitante, tipo);
		
	}

	public PartidoEliminatoria(Integer id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	
}
