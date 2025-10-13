package com.ventas.app.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ventas.app.entity.CategoriaEntity;
import com.ventas.app.entity.ProductoEntity;
import com.ventas.app.repository.CategoriaRepository;
import com.ventas.app.repository.ProductoRepository;
import com.ventas.app.services.ProductoService;
import com.ventas.app.services.ServiceException;

import jakarta.transaction.Transactional;

@Service
public class ProductoServiceImpl implements ProductoService{
	private final ProductoRepository productoRepository;
	private final CategoriaRepository categoriaRepository;

	public ProductoServiceImpl(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
		super();
		this.productoRepository = productoRepository;
		this.categoriaRepository = categoriaRepository;
	}

	@Override
	public List<ProductoEntity> findAll() throws ServiceException {
		try {
			return this.productoRepository.findAll();
		}catch(Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public List<ProductoEntity> findByIdCategoria(Long id) throws ServiceException {
		try {
			return this.productoRepository.findByIdCategoria(id);
		}catch(Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/*/*@Override
	public List<ProductoEntity> findByAll() throws ServiceException {
		try {
			return this.productoRepository.findByAll();
		}catch(Exception e) {
			throw new ServiceException(e);
		}
	}	*/

	@Override
	public ProductoEntity save(ProductoEntity productoEntity) throws ServiceException {
		try {
			List<CategoriaEntity> optCategoriaEntity = categoriaRepository.findByIdcategoria(productoEntity.getCategoria().getIdcategoria());

			if (optCategoriaEntity.isEmpty()) {
				throw new ServiceException(String.format("No existe categoria con el id %s", productoEntity.getCategoria().getIdcategoria()));
			}
			
			return this.productoRepository.save(productoEntity);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public ProductoEntity update(Long id, ProductoEntity productoEntity) throws ServiceException {
		try {

			List<ProductoEntity> optProductoEntity = productoRepository.findByIdproducto(id);

			if (optProductoEntity.isEmpty()) {
				throw new ServiceException(String.format("No existe producto con el id %s", id));
			}
			
			ProductoEntity oProductoEntity = optProductoEntity.get(0);
			oProductoEntity.setIdproducto(id);
			
			List<CategoriaEntity> optCategoriaEntity = categoriaRepository.findByIdcategoria(productoEntity.getCategoria().getIdcategoria());

			if (optCategoriaEntity.isEmpty()) {
				throw new ServiceException(String.format("No existe categoria con el id %s", productoEntity.getCategoria().getIdcategoria()));
			}
			
			oProductoEntity.setCategoria(optCategoriaEntity.get(0));
			oProductoEntity.setNombre(productoEntity.getNombre());
			oProductoEntity.setDescripcion(productoEntity.getDescripcion());
			oProductoEntity.setFoto(productoEntity.getFoto());
			oProductoEntity.setTalla(productoEntity.getTalla());
			oProductoEntity.setMaterial(productoEntity.getMaterial());
			oProductoEntity.setTaco(productoEntity.getTaco());
			oProductoEntity.setPrecio(productoEntity.getPrecio());
			oProductoEntity.setEstado(productoEntity.getEstado());
			oProductoEntity.setBorrado(productoEntity.getBorrado());
			return this.productoRepository.save(oProductoEntity);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public void delete(Long id) throws ServiceException {
		try {
			List<ProductoEntity> optProductoEntity = productoRepository.findByIdproducto(id);

			if (optProductoEntity.isEmpty()) {
				throw new ServiceException(String.format("No existe producto con el id %s", id));
			}
			productoRepository.eliminar(id);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
