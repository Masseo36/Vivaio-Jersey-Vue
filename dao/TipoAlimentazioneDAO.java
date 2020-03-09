package com.topjavatutorial.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.topjavatutorial.entità.TipoAlimentazione;

public class TipoAlimentazioneDAO {
	
	public int findIdAlimentazioneByCarburante(String carburante) {
		Session session = SessionUtil.getSession();
		Query query = session.createQuery("from TipoAlimentazione tipo where tipo.carburante =:carburante");
		query.setParameter("carburante", carburante);
		List<TipoAlimentazione> tipoAl = query.list();
		session.close();
		int tipo = tipoAl.get(0).getIdTipoAlimentazione();	
		return tipo;
		
	}

}
