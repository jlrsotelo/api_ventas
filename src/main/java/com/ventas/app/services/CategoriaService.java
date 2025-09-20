package com.ventas.app.services;

import java.util.List;
import com.ventas.app.entity.CategoriaEntity;


public interface CategoriaService {
	List<CategoriaEntity> findAll() throws ServiceException;
	CategoriaEntity save(CategoriaEntity categoriaEntity) throws ServiceException;
	CategoriaEntity update(Long id, CategoriaEntity categoriaEntity) throws ServiceException;
	void delete(Long id) throws ServiceException;
}
