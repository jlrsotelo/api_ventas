package com.ventas.app.business.controller.privates.gestion;

import java.util.HashMap;
import java.util.Map;
import static java.util.Objects.isNull;
import static com.ventas.app.business.controller.constants.ConstantController.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ventas.app.business.entity.ProductoEntity;
import com.ventas.app.business.services.ProductoService;
import com.ventas.app.business.services.ServiceException;

@RestController
@RequestMapping("/private/api/v1/producto/gestion")
public class ProductoGestionController {
	private final ProductoService productoService;
	private Map<String, String> map = new HashMap<>();
	
	public ProductoGestionController(ProductoService productoService) {
		super();
		this.productoService = productoService;
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
	
	@PreAuthorize("hasRole('ADMIN')")
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
	
	@PreAuthorize("hasRole('ADMIN')")
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
