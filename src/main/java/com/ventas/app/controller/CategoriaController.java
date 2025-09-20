package com.ventas.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.Objects.isNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ventas.app.entity.CategoriaEntity;
import com.ventas.app.services.CategoriaService;
import com.ventas.app.services.ServiceException;

@RestController
@RequestMapping("/api/v1/categoria")
public class CategoriaController {
	private final CategoriaService categoriaService;
	private final String MSG_INTERNAL_ERROR = "Se ha producido un error interno";
	private final String MSG_BAD_REQUEST = "Operación no valida";
	private Map<String, String> map = new HashMap<>();

	public CategoriaController(CategoriaService categoriaService) {
		super();
		this.categoriaService = categoriaService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAll() {
		try {
			List<CategoriaEntity> lstCategoriaEntity = this.categoriaService.findAll();
			if (lstCategoriaEntity.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(lstCategoriaEntity);
			}
		} catch (Exception e) {
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody CategoriaEntity categoriaEntity){
		try {
			CategoriaEntity oCategoriaEntity = this.categoriaService.save(categoriaEntity);
			if (isNull(oCategoriaEntity)) {
				map.put("alerta", MSG_BAD_REQUEST);
				return ResponseEntity.badRequest().body(map);
			} else {
				return new ResponseEntity<>(oCategoriaEntity,HttpStatus.CREATED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable  Long id, @RequestBody CategoriaEntity categoriaEntity){

		try {
			CategoriaEntity oCategoriaEntity = this.categoriaService.update(id,categoriaEntity);
			if (isNull(oCategoriaEntity)) {
				map.put("alerta", MSG_BAD_REQUEST);
				return ResponseEntity.badRequest().body(map);
			} else {
				return ResponseEntity.ok(oCategoriaEntity);
			}
		} catch (ServiceException e) {
			map.put("error", e.getMessage());
			return ResponseEntity.badRequest().body(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){

		try {
			this.categoriaService.delete(id);
			return ResponseEntity.ok().build();
		} catch (ServiceException e) {
			map.put("error", e.getMessage());
			return ResponseEntity.badRequest().body(map);
		} catch (Exception e) {
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}	
	
}
