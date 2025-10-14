package com.ventas.app.business.services;

import java.util.List;
import com.ventas.app.business.entity.DetallePedidoEntity;

public interface DetallePedidoService {
	List<DetallePedidoEntity> findAll() throws ServiceException;
	List<DetallePedidoEntity> findByCategoria() throws ServiceException;
	DetallePedidoEntity save(DetallePedidoEntity detallePedidoEntity) throws ServiceException;
}
