package com.ventas.app.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ventas.app.entity.ClienteEntity;
import com.ventas.app.repository.ClienteRepository;
import com.ventas.app.services.ClienteService;
import com.ventas.app.services.ServiceException;

import jakarta.transaction.Transactional;

@Service
public class ClienteServiceImpl implements ClienteService{
	private final ClienteRepository clienteRepository;
	
	public ClienteServiceImpl(ClienteRepository clienteRepository) {
		super();
		this.clienteRepository = clienteRepository;
	}

	@Override
	public List<ClienteEntity> findAll() throws ServiceException {
		try {
			return this.clienteRepository.findAll();
		}catch(Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public ClienteEntity save(ClienteEntity clienteEntity) throws ServiceException {
		try {
			return this.clienteRepository.save(clienteEntity);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public void delete(Long id) throws ServiceException {
		try {
			List<ClienteEntity> optCategoriaEntity = clienteRepository.findByIdcliente(id);

			if (optCategoriaEntity.isEmpty()) {
				throw new ServiceException(String.format("No existe cliente con el id %s", id));
			}
			clienteRepository.eliminar(id);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public ClienteEntity update(Long id, ClienteEntity clienteEntity) throws ServiceException {
		try {

			List<ClienteEntity> optClienteEntity = clienteRepository.findByIdcliente(id);

			if (optClienteEntity.isEmpty()) {
				throw new ServiceException(String.format("No existe cliente con el id %s", id));
			}
			ClienteEntity oClienteEntity = optClienteEntity.get(0);
			oClienteEntity.setIdcliente(id);
			oClienteEntity.setNombre(clienteEntity.getNombre());
			oClienteEntity.setPaterno(clienteEntity.getPaterno());
			oClienteEntity.setMaterno(clienteEntity.getMaterno());
			oClienteEntity.setDireccion(clienteEntity.getDireccion());
			oClienteEntity.setBorrado(clienteEntity.getBorrado());
			return this.clienteRepository.save(oClienteEntity);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
