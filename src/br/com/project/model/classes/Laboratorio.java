package br.com.project.model.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "laboratorio")
@SequenceGenerator(name = "laboratorio_seq", sequenceName = "laboratorio_seq", initialValue = 1, allocationSize = 1)
public class Laboratorio implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "laboratorio_seq")
	private Long id_laboratorio;

	private String laboratoriodb;

	private String antecedencia_min;

	private String antecedencia_max;

	private String vigencia_inicial;

	private String vigencia_final;

	public String getVigencia_inicial() {
		return vigencia_inicial;
	}

	public void setVigencia_inicial(String vigencia_inicial) {
		this.vigencia_inicial = vigencia_inicial;
	}

	public String getVigencia_final() {
		return vigencia_final;
	}

	public void setVigencia_final(String vigencia_final) {
		this.vigencia_final = vigencia_final;
	}

	public String getAntecedencia_min() {
		return antecedencia_min;
	}

	public void setAntecedencia_min(String antecedencia_min) {
		this.antecedencia_min = antecedencia_min;
	}

	public String getAntecedencia_max() {
		return antecedencia_max;
	}

	public void setAntecedencia_max(String antecedencia_max) {
		this.antecedencia_max = antecedencia_max;
	}

	public Long getId_laboratorio() {
		return id_laboratorio;
	}

	public void setId_laboratorio(Long id_laboratorio) {
		this.id_laboratorio = id_laboratorio;
	}

	public void setLaboratoriodb(String laboratoriodb) {
		this.laboratoriodb = laboratoriodb;
	}

	public String getLaboratoriodb() {
		return laboratoriodb;
	}

	@Version
	@Column(name = "versionNum")
	private int versionNum;

	protected int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id_laboratorio == null) ? 0 : id_laboratorio.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Laboratorio other = (Laboratorio) obj;
		if (id_laboratorio == null) {
			if (other.id_laboratorio != null)
				return false;
		} else if (!id_laboratorio.equals(other.id_laboratorio))
			return false;
		return true;
	}

}
