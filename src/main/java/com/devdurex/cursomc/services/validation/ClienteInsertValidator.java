package com.devdurex.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.devdurex.cursomc.domain.Cliente;
import com.devdurex.cursomc.domain.enums.TipoCliente;
import com.devdurex.cursomc.dto.ClienteNewDTO;
import com.devdurex.cursomc.repositories.ClienteRepository;
import com.devdurex.cursomc.resources.exception.FieldMessage;
import com.devdurex.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator  implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteInsert ann) {
	
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
				
		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("CpfOuCnpj", "CPF inválido"));			
		}
		
		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("CpfOuCnpj", "CNPJ inválido"));			
		}
		
		Cliente clienteAuxiliar = clienteRepository.findByEmail(objDto.getEmail());
		if (clienteAuxiliar != null) {
			list.add(new FieldMessage("email", "e-Mail já cadastrado"));	
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}