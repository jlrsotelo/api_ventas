package com.ventas.app.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ventas.app.business.entity.ProductoEntity;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long>{
	@Query(value = "select c from ProductoEntity c where c.borrado='0' and idproducto=:id")
	List<ProductoEntity> findByIdproducto(@Param("id") Long id);
	
	@Modifying
	@Query(value = "update producto set borrado='1' where idproducto=:id", nativeQuery = true )
	void eliminar(@Param("id") Long id);
}
