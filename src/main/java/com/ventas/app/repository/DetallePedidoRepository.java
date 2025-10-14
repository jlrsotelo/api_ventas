package com.ventas.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ventas.app.entity.DetallePedidoEntity;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedidoEntity, Long>{
	
}
