package com.ventas.app.business.services;

import java.util.List;

import com.ventas.app.business.entity.ProductoEntity;


public interface ProductoService {
	List<ProductoEntity> findAll() throws ServiceException;
	ProductoEntity save(ProductoEntity productoEntity) throws ServiceException;
	ProductoEntity update(Long id, ProductoEntity productoEntity) throws ServiceException;
	void delete(Long id) throws ServiceException;
}
