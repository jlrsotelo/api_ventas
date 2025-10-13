package com.ventas.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ventas.app.entity.ProductoEntity;
import com.ventas.app.services.ProductoService;

@RestController
@RequestMapping("/api/v1/catalogo")
public class CatalogoController {
	private final ProductoService productoService;
	private final String MSG_INTERNAL_ERROR = "Se ha producido un error interno";
	private Map<String, String> map = new HashMap<>();

	public CatalogoController(ProductoService productoService) {
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
	
	@GetMapping("/by-categoria")
	public ResponseEntity<?> findByCategoria(@RequestParam Long id) {
		try {
			List<ProductoEntity> optProducto = this.productoService.findByIdCategoria(id);
			if (optProducto.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(optProducto);
			}
		} catch (Exception e) {
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
}
