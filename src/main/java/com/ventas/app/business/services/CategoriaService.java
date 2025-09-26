package com.ventas.app.business.services;

import java.util.List;

import com.ventas.app.business.entity.CategoriaEntity;


public interface CategoriaService {
	List<CategoriaEntity> findAll() throws ServiceException;
	CategoriaEntity save(CategoriaEntity categoriaEntity) throws ServiceException;
	CategoriaEntity update(Long id, CategoriaEntity categoriaEntity) throws ServiceException;
	void delete(Long id) throws ServiceException;
}
