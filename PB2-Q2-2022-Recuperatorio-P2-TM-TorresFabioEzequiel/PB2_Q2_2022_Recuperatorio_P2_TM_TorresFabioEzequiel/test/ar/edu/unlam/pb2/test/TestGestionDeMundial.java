package ar.edu.unlam.pb2.test;
import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.unlam.pb2.dominio.Equipo;
import ar.edu.unlam.pb2.dominio.EquipoDuplicadoException;
import ar.edu.unlam.pb2.dominio.EquipoExistenteException;
import ar.edu.unlam.pb2.dominio.Grupo;
import ar.edu.unlam.pb2.dominio.GrupoNoCoincidenteException;
import ar.edu.unlam.pb2.dominio.Partido;
import ar.edu.unlam.pb2.dominio.PartidoNoEncontradoException;
import ar.edu.unlam.pb2.dominio.Seleccion;
import ar.edu.unlam.pb2.dominio.TipoPartido;
import ar.edu.unlam.pb2.dominio.Torneo;

public class TestGestionDeMundial {

	@Test
	public void queSePuedaCrearUnTorneoCon32Equipos() {
		Torneo torneo = new Torneo("Qatar 2022");
		Equipo Argentina = new Equipo(Seleccion.Argentina, Grupo.A);
		Equipo Polonia = new Equipo(Seleccion.Polonia, Grupo.A);
		try {
			torneo.agregarEquipo(Argentina);
		} catch (EquipoExistenteException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			torneo.agregarEquipo(Polonia);
		} catch (EquipoExistenteException e) {
			System.out.println(e.getMessage());
		}
		
		Integer valorEsperado = 2;
		Integer valorObtenido = torneo.getEquipos().size();
		
		assertEquals(valorEsperado, valorObtenido);
	}
	
	@Test
	public void queSePuedaCrearUnPartidoDeGruposConDosEquiposDelMismoGrupo(){
		Torneo torneo = new Torneo("Qatar 2022");
		Equipo argentina = new Equipo(Seleccion.Argentina, Grupo.A);
		Equipo arabiaSaudita = new Equipo(Seleccion.Arabia_Saudita, Grupo.A);
		//Partido partido = new Partido(1, argentina, arabiaSaudita, TipoPartido.FASE_DE_GRUPOS);
				
		try {
			torneo.agregarEquipo(argentina);
		} catch (EquipoExistenteException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			torneo.agregarEquipo(arabiaSaudita);
		} catch (EquipoExistenteException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			torneo.registrarPartidoGrupos(1, argentina, arabiaSaudita, torneo, TipoPartido.FASE_DE_GRUPOS);
		} catch (GrupoNoCoincidenteException e) {
			System.out.println(e.getMessage());
		} catch (EquipoDuplicadoException e) {
			System.out.println(e.getMessage());
		} catch (PartidoNoEncontradoException e) {
			System.out.println(e.getMessage());
		}
		
		Integer valorEsperado = 1;
		Integer valorObtenido = torneo.getPartidos().size();
		
		assertEquals(valorEsperado, valorObtenido);	
		
	}
	
	@Test
	public void queSePuedaCrearUnPartidoDeEliminatoriasConDosEquiposPertenecientesALaListaDeEquiposEnEliminatorias(){
		Torneo torneo = new Torneo("Qatar 2022");
		Equipo argentina = new Equipo(Seleccion.Argentina, Grupo.A);
		Equipo arabiaSaudita = new Equipo(Seleccion.Arabia_Saudita, Grupo.A);
		Equipo brasil = new Equipo(Seleccion.Brasil, Grupo.B);
		Equipo serbia = new Equipo(Seleccion.Serbia, Grupo.B);
		Partido partido = new Partido(1, argentina, arabiaSaudita, TipoPartido.FASE_DE_GRUPOS);
				
		try {
			torneo.agregarEquipo(argentina);
		} catch (EquipoExistenteException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			torneo.agregarEquipo(arabiaSaudita);
		} catch (EquipoExistenteException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			torneo.agregarEquipo(brasil);
		} catch (EquipoExistenteException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			torneo.agregarEquipo(serbia);
		} catch (EquipoExistenteException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			torneo.registrarPartidoGrupos(1, argentina, arabiaSaudita, torneo, TipoPartido.FASE_DE_GRUPOS);
		} catch (GrupoNoCoincidenteException e) {
			System.out.println(e.getMessage());
		} catch (EquipoDuplicadoException e) {
			System.out.println(e.getMessage());
		} catch (PartidoNoEncontradoException e) {
			System.out.println(e.getMessage());
		}
		
		
		try {
			torneo.registrarResultado(1, 1, 2, torneo);
		} catch (PartidoNoEncontradoException e1) {
			System.out.println(e1.getMessage());
		}
		
		
		
		try {
			torneo.registrarPartidoGrupos(2, brasil, serbia, torneo, TipoPartido.FASE_DE_GRUPOS);
		} catch (GrupoNoCoincidenteException e) {
			System.out.println(e.getMessage());
		} catch (EquipoDuplicadoException e) {
			System.out.println(e.getMessage());
		} catch (PartidoNoEncontradoException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			torneo.registrarResultado(2, 3, 1, torneo);
		} catch (PartidoNoEncontradoException e1) {
			System.out.println(e1.getMessage());
		}
	}


}
