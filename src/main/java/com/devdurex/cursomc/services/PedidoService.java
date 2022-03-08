package com.devdurex.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devdurex.cursomc.domain.Pedido;
import com.devdurex.cursomc.repositories.PedidoRepository;
import com.devdurex.cursomc.services.exception.ObjectNotFoundException;



@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return  obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado, id: " + id +", tipo: " + Pedido.class.getName()));
	}

}
