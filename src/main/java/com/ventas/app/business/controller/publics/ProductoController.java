package com.ventas.app.business.controller.publics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.ventas.app.business.controller.constants.ConstantController.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ventas.app.business.entity.ProductoEntity;
import com.ventas.app.business.services.ProductoService;

@RestController
@RequestMapping("/public/api/v1/producto")
public class ProductoController {
	private final ProductoService productoService;
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
}
