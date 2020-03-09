package com.topjavatutorial.service;

import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.topjavatutorial.dao.MezzoDAO;
import com.topjavatutorial.entità.Mezzo;

public class MezzoService {
	private MezzoDAO mezzoDAO = new MezzoDAO();

	public List<Mezzo> getMezzi() {
		List<Mezzo> mezzi = mezzoDAO.getMezzi();
		return mezzi;
	}

	public Response createMezzo(Mezzo mez) {
		mezzoDAO.createMezzo(mez);
		return Response.ok().build();
	}

	// ATTENZIONE! XXXXXXXX DA qui a "Fino a qua" ho sviluppato i metodi ricerca
	// direttamente nel Front-end XXXXXXXX

	public List<Mezzo> findMezzi(@PathParam("marca") String marca, @PathParam("modello") String modello,
			@PathParam("carburante") String carburante) {
		List<Mezzo> mezziByMarca = mezzoDAO.findMezzi(marca, modello, carburante);
		return mezziByMarca;
	}

	public List<Mezzo> findMezziByIdMezzo(long idMezzo) {
		List<Mezzo> mezziByIdMezzo = mezzoDAO.findMezziByIdMezzo(idMezzo);
		return mezziByIdMezzo;

	}

	public List<Mezzo> findMezziByMarca(@PathParam("marca") String marca) {
		List<Mezzo> mezziByMarca = mezzoDAO.findMezziByMarca(marca);
		return mezziByMarca;
	}

	public List<Mezzo> findMezziByModello(@PathParam("modello") String modello) {
		List<Mezzo> mezziByModello = mezzoDAO.findMezziByModello(modello);
		return mezziByModello;
	}

	public List<Mezzo> findMezziByCarburante(@PathParam("carburante") String carburante) {
		List<Mezzo> mezziByCarburante = mezzoDAO.findMezziByCarburante(carburante);
		return mezziByCarburante;
	}

	// XXXXXXXX Fino a qua XXXXXXXX

	public Response updateMezzo(int idMezzo, Mezzo mez) {
		int count = mezzoDAO.updateMezzo(idMezzo, mez);
		if (count == 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().build();
	}

	public Response deleteMezzo(@PathParam("idMezzo") long idMezzo) {
		int count = mezzoDAO.deleteMezzo(idMezzo);
		if (count == 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().build();
	}
}
