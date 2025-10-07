package com.ventas.app.business.controller.privates.consulta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.ventas.app.business.controller.constants.ConstantController.MSG_INTERNAL_ERROR;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ventas.app.business.entity.CategoriaEntity;
import com.ventas.app.business.services.CategoriaService;

@RestController
@RequestMapping("/private/api/v1/categoria/consulta")
public class CategoriaConsultaController {
	private final CategoriaService categoriaService;
	private Map<String, String> map = new HashMap<>();

	public CategoriaConsultaController(CategoriaService categoriaService) {
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
}
