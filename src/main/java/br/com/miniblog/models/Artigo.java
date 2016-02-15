package br.com.miniblog.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="artigo")
@NamedQueries({
	@NamedQuery(name="Artigo.findAll", query="SELECT a FROM Artigo a ORDER BY a.id"),
	@NamedQuery(name="Artigo.findById", query="SELECT a FROM Artigo a WHERE a.id = :id")
})
public class Artigo {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	@NotBlank
	private String titulo;
	
	@Lob
	@NotBlank
	private String texto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public String toString() {
		return "Artigo [id=" + id + ", titulo=" + titulo + ", texto=" + texto + "]";
	}
	
	
}
