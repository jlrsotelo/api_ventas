package com.ventas.app.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ventas.app.business.entity.PedidoEntity;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long>{
	@Query(nativeQuery = true, value = "select * from pedido c where c.borrado='0' and c.idcliente=:id")
	List<PedidoEntity> findByCliente(@Param("id") Long id);
	
	@Query(nativeQuery = true, value = "select * from pedido c where c.borrado='0' and c.idpedido=:id")
	List<PedidoEntity> findByPedido(@Param("id") Long id);	
	
	@Query(value = "select c from PedidoEntity c where c.borrado='0'")
	List<PedidoEntity> findByAll();	
}
