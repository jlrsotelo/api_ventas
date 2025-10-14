package com.ventas.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ventas.app.entity.ProductoEntity;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long>{
	@Query(value = "select c from ProductoEntity c where c.borrado='0' and c.idproducto=:id")
	List<ProductoEntity> findByIdproducto(@Param("id") Long id);
	
	@Query(value = "select c from ProductoEntity c where c.borrado='0' and c.categoria.idcategoria=:id")
	List<ProductoEntity> findByIdCategoria(@Param("id") Long id);
	
	@Query(value = "select c from ProductoEntity c where c.borrado='0'")
	List<ProductoEntity> findAll();
	
	@Modifying
	@Query(value = "update producto set borrado='1' where idproducto=:id", nativeQuery = true )
	void eliminar(@Param("id") Long id);
}
