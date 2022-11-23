package ar.edu.unlam.pb2.dominio;

import java.util.HashSet;
import java.util.Set;

public class Torneo {
	private Set<Equipo> equipos;
	private Set<Partido> partidos;
	private Set<Equipo> equiposEnEliminatorias;
	private String nombre;

	public Torneo(String nombre) {
		this.nombre = nombre;
		this.equipos = new HashSet<>();
		this.partidos = new HashSet<>();
		this.equiposEnEliminatorias = new HashSet<>();
	}

	public void agregarEquipo(Equipo equipo) throws EquipoExistenteException {
		if (this.equipos.contains(equipo)) {
			throw new EquipoExistenteException("No se puede agregar el equipo porque ya existe");
		}
		this.equipos.add(equipo);
	}

	public Set<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(Set<Equipo> equipos) {
		this.equipos = equipos;
	}

	public Set<Partido> getPartidos() {
		return partidos;
	}

	public void setPartidos(Set<Partido> partidos) {
		this.partidos = partidos;
	}

	public Set<Equipo> getEquiposEnEliminatorias() {
		return equiposEnEliminatorias;
	}

	public void setEquiposEnEliminatorias(Set<Equipo> equiposEnEliminatorias) {
		this.equiposEnEliminatorias = equiposEnEliminatorias;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void registrarPartidoGrupos(Integer idPartido, Equipo equipoLocal, Equipo equipoVisitante, Torneo torneo,
			TipoPartido tipoPartido)
			throws GrupoNoCoincidenteException, EquipoDuplicadoException, PartidoNoEncontradoException {
		if (equipoLocal.getGrupo() != equipoVisitante.getGrupo()) {
			throw new GrupoNoCoincidenteException("El grupo de los equipos no coincide");
		}
		crearPartidoFaseGrupos(idPartido, equipoLocal, equipoVisitante, torneo, tipoPartido);

	}

	public void registrarPartidoEliminatorias(Integer idPartido, Equipo equipoLocal, Equipo equipoVisitante,
			Torneo torneo, TipoPartido tipoPartido)
			throws GrupoNoCoincidenteException, EquipoDuplicadoException, PartidoNoEncontradoException {
		if (equipoLocal.getGrupo() != equipoVisitante.getGrupo()) {
			throw new GrupoNoCoincidenteException("El grupo de los equipos no coincide");
		}
		crearPartidoEliminatorias(idPartido, equipoLocal, equipoVisitante, torneo, tipoPartido);
		if (equipoLocal.getSeleccion().equals(equipoVisitante.getSeleccion())) {
			throw new EquipoDuplicadoException("No se creo el partido, los equipos son iguales");
		}
		Partido partido = new PartidoEliminatoria(idPartido, equipoLocal, equipoVisitante, tipoPartido);
		this.partidos.add(partido);
	}

	private void crearPartidoEliminatorias(Integer idPartido, Equipo equipoLocal, Equipo equipoVisitante, Torneo torneo,
			TipoPartido tipoPartido) {

	}

	private void crearPartidoFaseGrupos(Integer idPartido, Equipo equipoLocal, Equipo equipoVisitante, Torneo torneo,
			TipoPartido tipoPartido) throws EquipoDuplicadoException, PartidoNoEncontradoException {
		if (equipoLocal.getSeleccion().equals(equipoVisitante.getSeleccion())) {
			throw new EquipoDuplicadoException("No se creo el partido, los equipos son iguales");
		}
		Partido partido = new PartidoGrupos(idPartido, equipoLocal, equipoVisitante, tipoPartido);
		this.partidos.add(partido);

	}

	private void corroborarTipoDePartido(Integer idPartido, Torneo torneo, Integer golesLocal, Integer golesVisitante)
			throws PartidoNoEncontradoException {
		Partido partidoEncontrado = torneo.buscarPartido(idPartido);
		if (partidoEncontrado.getTipoPartido().equals(TipoPartido.FASE_DE_GRUPOS)) {
			torneo.registrarResultado(idPartido, golesLocal, golesVisitante, torneo);
		}
	}

	private Partido buscarPartido(Integer idPartido) throws PartidoNoEncontradoException {
		for (Partido partido : this.partidos) {
			if (partido.getId().equals(idPartido)) {
				return partido;
			}
		}
		throw new PartidoNoEncontradoException("No se encontro el partido");
	}

	public void crearPartido(Equipo argentina, Equipo arabiaSaudita) {

	}

	public void registrarResultado(Integer idPartido, Integer golesLocal, Integer golesVisitante, Torneo torneo)
			throws PartidoNoEncontradoException {
		Partido partidoEncontrado = torneo.buscarPartido(idPartido);
		partidoEncontrado.setGolesEquipoLocal(golesLocal);
		partidoEncontrado.setGolesEquipoRival(golesVisitante);
		// LO DE ARRIBA SON LOS GOLES HECHOS POR EL LOCAL Y EL VISITANTE EN EL
		// PARTIDO!!!

		// LO DE ABAJO SON LOS GOLES A FAVOR QUE TENDRIA EL LOCAL Y EL VISITANTE
		partidoEncontrado.sumarGolesAEquipoLocal(golesLocal, golesVisitante);
		partidoEncontrado.sumarGolesAEquipoRival(golesLocal, golesVisitante);

		if (partidoEncontrado.getEquipoLocal().getGana().equals(Gana.GANA_LOCAL)) {
			partidoEncontrado.getEquipoLocal().setPuntos(3);
		}

		if (partidoEncontrado.getEquipoVisitante().getGana().equals(Gana.GANA_VISITANTE)) {
			partidoEncontrado.getEquipoVisitante().setPuntos(3);
		}
		;

		if (partidoEncontrado.getEquipoVisitante().getGana().equals(Gana.EMPATE)
				&& partidoEncontrado.getEquipoLocal().getGana().equals(Gana.EMPATE)) {
			partidoEncontrado.getEquipoLocal().setPuntos(1);
			partidoEncontrado.getEquipoVisitante().setPuntos(1);
		}
	};

	private void obtenerResultado(Integer idPartido, Torneo torneo) throws PartidoNoEncontradoException {
		Partido partidoEncontrado = torneo.buscarPartido(idPartido);

		partidoEncontrado.obtenerResultado();
	}

	//Ya fue, nos vemos el otro cuatri profes, voy a practicar mas
}
