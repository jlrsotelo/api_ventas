package com.ventas.app.business.services;

import java.util.List;
import com.ventas.app.business.entity.ClienteEntity;


public interface ClienteService {
	List<ClienteEntity> findAll() throws ServiceException;
	ClienteEntity save(ClienteEntity clienteEntity) throws ServiceException;
	ClienteEntity update(Long id, ClienteEntity clienteEntity) throws ServiceException;
	void delete(Long id) throws ServiceException;
}
