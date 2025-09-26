package com.ventas.app.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ventas.app.business.entity.CategoriaEntity;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long>{
	@Query(value = "select c from CategoriaEntity c where c.borrado='0' and idcategoria=:id")
	List<CategoriaEntity> findByIdcategoria(@Param("id") Long id);
	
	@Modifying
	@Query(value = "update categoria set borrado='1' where idcategoria=:id", nativeQuery = true)
	void eliminar(@Param("id") Long id);
}
