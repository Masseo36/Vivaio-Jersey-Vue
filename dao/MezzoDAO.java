package com.topjavatutorial.dao;

import java.util.List;

import javax.ws.rs.core.Response;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.topjavatutorial.entità.Employee;
import com.topjavatutorial.entità.Mezzo;
import com.topjavatutorial.entità.TipoAlimentazione;

public class MezzoDAO {

	public void addMezzo(Mezzo bean) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		addMezzo(session, bean);
		tx.commit();
		session.close();

	}

	private void addMezzo(Session session, Mezzo bean) {
		Mezzo mezzo = new Mezzo();

		mezzo.setTarga(bean.getTarga());
		mezzo.setMarca(bean.getMarca());
		mezzo.setModello(bean.getModello());
		mezzo.setTipoAlimentazione(bean.getTipoAlimentazione());

		session.save(mezzo);
	}

	public Response createMezzo(Mezzo mez) {
		mez.setTarga(mez.getTarga());
		mez.setMarca(mez.getMarca());
		mez.setModello(mez.getModello());
		Session session = SessionUtil.getSession();
		Query query = session.createQuery("from TipoAlimentazione tipo where tipo.carburante =:carburante");
		query.setParameter("carburante", mez.getTipoAlimentazione().getCarburante());
		List<TipoAlimentazione> tipoAl = query.list();
		session.close();
		mez.setTipoAlimentazione(tipoAl.get(0));
		MezzoDAO dao = new MezzoDAO();
		dao.addMezzo(mez);
		return Response.ok().build();
	}

	public List<Mezzo> getMezzi() {
		Session session = SessionUtil.getSession();
		Query query = session.createQuery("from Mezzo");
		List<Mezzo> mezzi = query.list();
		session.close();
		return mezzi;
	}

	public int deleteMezzo(long idMezzo) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from Mezzo where idMezzo = :idMezzo";
		Query query = session.createQuery(hql);
		query.setInteger("idMezzo", (int) idMezzo);
		int rowCount = query.executeUpdate();
		System.out.println("Rows affected: " + rowCount);
		tx.commit();
		session.close();
		return rowCount;
	}

	// ATTENZIONE! XXXXXXXX DA qui a "Fino a qua" ho sviluppato i metodi ricerca
	// direttamente nel Front-end XXXXXXXX
	public List<Mezzo> findMezzi(String marca, String modello, String carburante) {
		Session session = SessionUtil.getSession();
		Query query = session.createQuery(
				"from Mezzo mez where mez.marca =:marca and mez.modello =:modello and mez.tipoAlimentazione.carburante =:carburante");
		query.setParameter("marca", marca);
		query.setParameter("modello", modello);
		query.setParameter("carburante", carburante);
		List<Mezzo> mezziByMarca = query.list();
		session.close();

		return mezziByMarca;
	}

	public List<Mezzo> findMezziByMarca(String marca) {
		Session session = SessionUtil.getSession();
		Query query = session.createQuery("from Mezzo mez where mez.marca =:marca");
		query.setParameter("marca", marca);
		List<Mezzo> mezziByMarca = query.list();
		session.close();

		return mezziByMarca;
	}

	public List<Mezzo> findMezziByModello(String modello) {
		Session session = SessionUtil.getSession();
		Query query = session.createQuery("from Mezzo mez where mez.modello =:modello");
		query.setParameter("modello", modello);
		List<Mezzo> mezziByMarca = query.list();
		session.close();

		return mezziByMarca;
	}

	public List<Mezzo> findMezziByCarburante(String carburante) {
		Session session = SessionUtil.getSession();
		Query query = session.createQuery("from Mezzo mez where mez.tipoAlimentazione.carburante =:carburante");
		query.setParameter("carburante", carburante);
		List<Mezzo> mezziByCarburante = query.list();
		session.close();

		return mezziByCarburante;
	}
	// XXXXXXXX Fino a qua XXXXXXXX

	public List<Mezzo> findMezziByIdMezzo(long idMezzo) {
		Session session = SessionUtil.getSession();
		Query query = session.createQuery("from Mezzo mez where mez.idMezzo =:idMezzo");
		query.setParameter("idMezzo", idMezzo);
		List<Mezzo> mezziByCarburante = query.list();
		session.close();

		return mezziByCarburante;
	}

	public int updateMezzo(int idMezzo, Mezzo mez) {
		TipoAlimentazioneDAO tipoAl = new TipoAlimentazioneDAO();
		if (idMezzo <= 0)
			return 0;
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		String hql = "update Mezzo set targa =:targa, marca =:marca, modello =:modello, tipoAlimentazione.idTipoAlimentazione =:idTipoAlimentazione  where idMezzo =:idMezzo";

		Query query = session.createQuery(hql);
		query.setInteger("idMezzo", idMezzo);
		query.setString("targa", mez.getTarga());
		query.setString("marca", mez.getMarca());
		query.setString("modello", mez.getModello());
		query.setInteger("idTipoAlimentazione",
				tipoAl.findIdAlimentazioneByCarburante(mez.getTipoAlimentazione().getCarburante()));
		int rowCount = query.executeUpdate();
		System.out.println("Rows affected: " + rowCount);
		// System.out.println(mez.getTipoAlimentazione().getIdTipoAlimentazione());
		// System.out.println(tipoAl.findIdAlimentazioneByCarburante(mez.getTipoAlimentazione().getCarburante()));
		tx.commit();
		session.close();
		return rowCount;
	}
}
