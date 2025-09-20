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

import com.ventas.app.entity.ProductoEntity;
import com.ventas.app.services.ProductoService;
import com.ventas.app.services.ServiceException;

@RestController
@RequestMapping("/api/v1/producto")
public class ProductoController {
	private final ProductoService productoService;
	private final String MSG_INTERNAL_ERROR = "Se ha producido un error interno";
	private final String MSG_BAD_REQUEST = "Operación no valida";
	private Map<String, String> map = new HashMap<>();
	
	public ProductoController(ProductoService productoService) {
		super();
		this.productoService = productoService;
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAll() {
		try {
			List<ProductoEntity> lstProductoEntity = this.productoService.findAll();
			if (lstProductoEntity.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(lstProductoEntity);
			}
		} catch (Exception e) {
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody ProductoEntity productoEntity){
		try {
			ProductoEntity oProductoEntity = this.productoService.save(productoEntity);
			if (isNull(oProductoEntity)) {
				map.put("alerta", MSG_BAD_REQUEST);
				return ResponseEntity.badRequest().body(map);
			} else {
				return new ResponseEntity<>(oProductoEntity,HttpStatus.CREATED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable  Long id, @RequestBody ProductoEntity productoEntity){

		try {
			ProductoEntity oProductoEntity = this.productoService.update(id,productoEntity);
			if (isNull(oProductoEntity)) {
				map.put("alerta", MSG_BAD_REQUEST);
				return ResponseEntity.badRequest().body(map);
			} else {
				return ResponseEntity.ok(oProductoEntity);
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
			this.productoService.delete(id);
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
