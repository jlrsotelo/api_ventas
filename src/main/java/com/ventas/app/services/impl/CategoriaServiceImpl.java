package com.ventas.app.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ventas.app.entity.CategoriaEntity;
import com.ventas.app.repository.CategoriaRepository;
import com.ventas.app.services.CategoriaService;
import com.ventas.app.services.ServiceException;

import jakarta.transaction.Transactional;

@Service
public class CategoriaServiceImpl implements CategoriaService{
	private final CategoriaRepository categoriaRepository;

	public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
		super();
		this.categoriaRepository = categoriaRepository;
	}

	@Override
	public List<CategoriaEntity> findAll() throws ServiceException {
		try {
			return this.categoriaRepository.findAll();
		}catch(Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public CategoriaEntity save(CategoriaEntity categoriaEntity) throws ServiceException {
		try {
			return this.categoriaRepository.save(categoriaEntity);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public CategoriaEntity update(Long id, CategoriaEntity categoriaEntity) throws ServiceException {
		try {

			List<CategoriaEntity> optCategoriaEntity = categoriaRepository.findByIdcategoria(id);

			if (optCategoriaEntity.isEmpty()) {
				throw new ServiceException(String.format("No existe categoria con el id %s", id));
			}
			CategoriaEntity oCategoriaEntity = optCategoriaEntity.get(0);
			oCategoriaEntity.setIdcategoria(id);
			oCategoriaEntity.setNombre(categoriaEntity.getNombre());
			oCategoriaEntity.setDescripcion(categoriaEntity.getDescripcion());
			oCategoriaEntity.setEstado(categoriaEntity.getEstado());
			oCategoriaEntity.setBorrado(categoriaEntity.getBorrado());
			return this.categoriaRepository.save(oCategoriaEntity);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public void delete(Long id) throws ServiceException {
		try {
			List<CategoriaEntity> optCategoriaEntity = categoriaRepository.findByIdcategoria(id);

			if (optCategoriaEntity.isEmpty()) {
				throw new ServiceException(String.format("No existe categoria con el id %s", id));
			}
			categoriaRepository.eliminar(id);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
