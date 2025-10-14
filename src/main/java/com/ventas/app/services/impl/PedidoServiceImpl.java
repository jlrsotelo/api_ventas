package com.ventas.app.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ventas.app.entity.ClienteEntity;
import com.ventas.app.entity.DetallePedidoEntity;
import com.ventas.app.entity.PedidoEntity;
import com.ventas.app.entity.ProductoEntity;
import com.ventas.app.repository.ClienteRepository;
import com.ventas.app.repository.PedidoRepository;
import com.ventas.app.repository.ProductoRepository;
import com.ventas.app.services.PedidoService;
import com.ventas.app.services.ServiceException;

import jakarta.transaction.Transactional;

@Service
public class PedidoServiceImpl implements PedidoService {
	private final PedidoRepository pedidoRepository;
	private final ClienteRepository clienteRepository;
	private final ProductoRepository productoRepository;

	public PedidoServiceImpl(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, ProductoRepository productoRepository) {
		super();
		this.pedidoRepository = pedidoRepository;
		this.clienteRepository = clienteRepository;
		this.productoRepository = productoRepository;
	}

	@Override
	@Transactional
	public PedidoEntity save(PedidoEntity pedidoEntity) throws ServiceException {
		try {
	        ClienteEntity cliente = clienteRepository.findById(pedidoEntity.getCliente().getIdcliente())
	        		.orElseThrow(() -> new ServiceException(
	        				String.format("No existe cliente con el id %s", pedidoEntity.getCliente().getIdcliente()))
	                );
			
			pedidoEntity.setCliente(cliente);
			
	        if (pedidoEntity.getDetalles() != null) {
	            pedidoEntity.getDetalles().forEach(d -> d.setPedido(pedidoEntity));
	        }			
			
	        return pedidoRepository.save(pedidoEntity);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<PedidoEntity> findAll() throws ServiceException {
		try {
			return this.pedidoRepository.findAll();
		}catch(Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<PedidoEntity> findByCliente(Long id) throws ServiceException {
		try {
			return this.pedidoRepository.findByCliente(id);
		}catch(Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public PedidoEntity update(Long id, PedidoEntity pedidoEntity) throws ServiceException {
		try {

			List<PedidoEntity> optPedidoEntity = pedidoRepository.findByPedido(id);

			if (optPedidoEntity.isEmpty()) {
				throw new ServiceException(String.format("No existe pedido con el id %s", id));
			}
			
			PedidoEntity oPedidoEntity = optPedidoEntity.get(0);
			oPedidoEntity.setIdpedido(id);
			
			List<ClienteEntity> optClienteEntity = clienteRepository.findByIdcliente(pedidoEntity.getCliente().getIdcliente());

			if (optClienteEntity.isEmpty()) {
				throw new ServiceException(String.format("No existe cliente con el id %s", pedidoEntity.getCliente().getIdcliente()));
			}
			
			oPedidoEntity.setCliente(optClienteEntity.get(0));
			oPedidoEntity.setFecha(pedidoEntity.getFecha());
			oPedidoEntity.setTotal(pedidoEntity.getTotal());
			oPedidoEntity.setEstado(pedidoEntity.getEstado());
			oPedidoEntity.setBorrado(pedidoEntity.getBorrado());
			
	        if (pedidoEntity.getDetalles() != null) {
	            oPedidoEntity.getDetalles().clear();

	            for (DetallePedidoEntity detalle: pedidoEntity.getDetalles()) {
	                ProductoEntity productoExistente = productoRepository.findById(detalle.getProducto().getIdproducto())
	                		.orElseThrow(() -> new ServiceException("No existe producto con id " + detalle.getProducto().getIdproducto()));
	                
	            	detalle.setPedido(oPedidoEntity);
	            	detalle.setProducto(productoExistente);
	                oPedidoEntity.getDetalles().add(detalle);
	            }
	        }
	        
			return this.pedidoRepository.save(oPedidoEntity);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}
