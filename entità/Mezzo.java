package com.topjavatutorial.entità;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Mezzo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_mezzo")
	private long idMezzo;

	@Column(name = "targa")
	private String targa;

	@Column(name = "marca")
	private String marca;

	@Column(name = "modello")
	private String modello;

	@Column(name = "active")
	private boolean active;

	@OneToOne
	@JoinColumn(name = "tipo_alimentazione_id_tipo_alimentazione")
	private TipoAlimentazione tipoAlimentazione;

	public Mezzo() {
	}

	public Mezzo(String targa, String marca, String modello) {
		this.targa = targa;
		this.marca = marca;
		this.modello = modello;
	}

	public Mezzo(long idMezzo, String targa, String marca, String modello, TipoAlimentazione tipoAlimentazione) {
		this.idMezzo = idMezzo;
		this.targa = targa;
		this.marca = marca;
		this.modello = modello;
		this.tipoAlimentazione = tipoAlimentazione;
	}

	public Mezzo(String targa, String marca, String modello, TipoAlimentazione tipoAlimentazione) {
		this.targa = targa;
		this.marca = marca;
		this.modello = modello;
		this.tipoAlimentazione = tipoAlimentazione;
	}

	public long getIdMezzo() {
		return idMezzo;
	}

	public void setIdMezzo(long idMezzo) {
		this.idMezzo = idMezzo;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public TipoAlimentazione getTipoAlimentazione() {
		return tipoAlimentazione;
	}

	public void setTipoAlimentazione(TipoAlimentazione tipoAlimentazione) {
		this.tipoAlimentazione = tipoAlimentazione;
	}

	@Override
	public String toString() {
		return "Mezzo [idMezzo=" + idMezzo + ", targa=" + targa + ", marca=" + marca + ", modello=" + modello
				+ ", active=" + active + ", tipoAlimentazione=" + tipoAlimentazione + "]";
	}

}
