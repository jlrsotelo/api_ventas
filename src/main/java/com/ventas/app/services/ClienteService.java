package com.ventas.app.services;

import java.util.List;
import com.ventas.app.entity.ClienteEntity;


public interface ClienteService {
	List<ClienteEntity> findAll() throws ServiceException;
	ClienteEntity save(ClienteEntity clienteEntity) throws ServiceException;
	ClienteEntity update(Long id, ClienteEntity clienteEntity) throws ServiceException;
	void delete(Long id) throws ServiceException;
}
