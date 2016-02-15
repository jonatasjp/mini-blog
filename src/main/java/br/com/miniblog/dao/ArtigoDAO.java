package br.com.miniblog.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.miniblog.models.Artigo;

@Repository
public class ArtigoDAO {

	@PersistenceContext
	private EntityManager manager;
	
	
	public void save(Artigo artigo) {
		manager.persist(artigo);
	}
	
	public List<Artigo> findAll() {
		
		return manager.createNamedQuery("Artigo.findAll", Artigo.class).getResultList();
		
	}
	
	public Artigo findById(Long id) {
		
		return manager.createNamedQuery("Artigo.findById", Artigo.class).setParameter("id", id).getSingleResult();
	
	}
	
	
	public void atualizar(Artigo artigo) {
		manager.merge(artigo);
	}

	public void excluir(Artigo artigo) {
		artigo = manager.find(Artigo.class, artigo.getId());
		manager.remove(artigo);
	}
	
}
