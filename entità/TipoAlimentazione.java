package com.topjavatutorial.entità;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_alimentazione")
public class TipoAlimentazione {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_alimentazione")
	private int idTipoAlimentazione;

	@Column(name = "carburante")
	private String carburante;

	public TipoAlimentazione() {
	}

	public TipoAlimentazione(int idTipoAlimentazione, String carburante) {
		this.idTipoAlimentazione = idTipoAlimentazione;
		this.carburante = carburante;
	}

	public TipoAlimentazione(String carburante) {
		this.carburante = carburante;
	}
	public int getIdTipoAlimentazione() {
		return idTipoAlimentazione;
	}

	public void setIdTipoAlimentazione(int idTipoAlimentazione) {
		this.idTipoAlimentazione = idTipoAlimentazione;
	}

	public String getCarburante() {
		return carburante;
	}

	public void setCarburante(String carburante) {
		this.carburante = carburante;
	}

	@Override
	public String toString() {
		return "TipoAlimentazione [idTipoAlimentazione=" + idTipoAlimentazione + ", carburante=" + carburante + "]";
	}

}
