package com.devdurex.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devdurex.cursomc.domain.Categoria;
import com.devdurex.cursomc.repositories.CategoriaRepository;
import com.devdurex.cursomc.services.exception.ObjectNotFoundException;



@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return  obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado, id: " + id +", tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		
		find(obj.getId());
		return repo.save(obj);
	}	

}
