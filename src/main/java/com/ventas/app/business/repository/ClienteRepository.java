package com.ventas.app.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ventas.app.business.entity.ClienteEntity;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long>{
	@Query(value = "select c from ClienteEntity c where c.borrado='0' and idcliente=:id")
	List<ClienteEntity> findByIdcliente(@Param("id") Long id);
	
	@Modifying
	@Query(value = "update cliente set borrado='1' where idcliente=:id", nativeQuery = true)
	void eliminar(@Param("id") Long id);
}
