package com.jpdev.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.jpdev.cursomc.domain.Categoria;
import com.jpdev.cursomc.repositories.CategoriaRepository;
import com.jpdev.cursomc.services.exceptions.DataIntegrityExcepition;
import com.jpdev.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id" + id 
				+ ", Tipo " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
	  }
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityExcepition("Não é possivel deletar uma categoria que possui produtos");
		}
	}
}
