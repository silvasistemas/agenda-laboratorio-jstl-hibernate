package br.com.project.model.classes;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.envers.Audited;
import org.primefaces.json.JSONObject;

@Audited
@Entity
@Table(name = "entidade")
@SequenceGenerator(name = "entidade_seq", sequenceName = "entidade_seq", initialValue = 1, allocationSize = 1)
public class Entidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entidade_seq")
	private Long ent_codigo;

	@Column(length = 100)
	private String nomedb;

	@Column(length = 100)
	private String emaildb;

	@Column(length = 100)
	private String senhadb;

	private boolean ent_inativo = false;

	@Column(length = 100)
	private String tipo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date ent_ultimoacesso;

	@Version
	@Column(name = "versionNum")
	private int versionNum;

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNomedb() {
		return nomedb;
	}

	public void setNomedb(String nomedb) {
		this.nomedb = nomedb;
	}

	public String getEmaildb() {
		return emaildb;
	}

	public void setEmaildb(String emaildb) {
		this.emaildb = emaildb;
	}

	public String getSenhadb() {
		return senhadb;
	}

	public void setSenhadb(String senhadb) {
		this.senhadb = senhadb;
	}

	public String getTipo() {
		return tipo;
	}

	public void setEnt_ultimoacesso(Date ent_ultimoacesso) {
		this.ent_ultimoacesso = ent_ultimoacesso;
	}

	public Date getEnt_ultimoacesso() {
		return ent_ultimoacesso;
	}


	public boolean isEnt_inativo() {
		return ent_inativo;
	}

	public void setEnt_inativo(boolean ent_inativo) {
		this.ent_inativo = ent_inativo;
	}

	public void setEnt_codigo(Long ent_codigo) {
		this.ent_codigo = ent_codigo;
	}

	public Long getEnt_codigo() {
		return ent_codigo;
	}

	protected int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("ent_codigo", ent_codigo);
		return new JSONObject(map);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ent_codigo == null) ? 0 : ent_codigo.hashCode());
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
		Entidade other = (Entidade) obj;
		if (ent_codigo == null) {
			if (other.ent_codigo != null)
				return false;
		} else if (!ent_codigo.equals(other.ent_codigo))
			return false;
		return true;
	}

}
