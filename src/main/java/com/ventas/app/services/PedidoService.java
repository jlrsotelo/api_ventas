package com.ventas.app.services;

import java.util.List;

import com.ventas.app.entity.PedidoEntity;

public interface PedidoService {
	List<PedidoEntity> findAll() throws ServiceException;
	List<PedidoEntity> findByCliente(Long id) throws ServiceException;
	PedidoEntity save(PedidoEntity pedidoEntity) throws ServiceException;
	PedidoEntity update(Long id, PedidoEntity pedidoEntity) throws ServiceException;
}
