package com.lins4tech.locaimob.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lins4tech.locaimob.entities.Usuario;

@Repository
public class UsuarioRepository {

	@PersistenceContext
	private EntityManager entityManager;

//	@Inject
//	private PasswordEncoder passwordEncoder;

	@Transactional(readOnly = false)
	public Usuario save(Usuario user) {
		// user.setPassword(passwordEncoder.encode(user.getPassword()));
		return entityManager.merge(user);
	}

	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		try {
			return entityManager.createNamedQuery(Usuario.FIND_BY_USERNAME, Usuario.class)
					.setParameter("username", username).getSingleResult();
		} catch (PersistenceException exp) {
			exp.printStackTrace();
			return null;
		}
	}
	
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		try {
			return entityManager.createNamedQuery(Usuario.FIND_ALL, Usuario.class).getResultList();
		} catch (PersistenceException exp) {
			exp.printStackTrace();
			return null;
		}
	}
}
