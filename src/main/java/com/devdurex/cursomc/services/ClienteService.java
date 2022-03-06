package com.devdurex.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devdurex.cursomc.domain.Categoria;
import com.devdurex.cursomc.domain.Cliente;
import com.devdurex.cursomc.repositories.ClienteRepository;
import com.devdurex.cursomc.services.exception.ObjectNotFoundException;



@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return  obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado, id: " + id +", tipo: " + Cliente.class.getName()));
	}

}
