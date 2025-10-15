package com.ventas.app.business.controller.privates.consulta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.ventas.app.business.controller.constants.ConstantController.MSG_INTERNAL_ERROR;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ventas.app.business.entity.ClienteEntity;
import com.ventas.app.business.services.ClienteService;

@RestController
@RequestMapping("/private/api/v1/cliente/consulta")
public class ClienteConsultaController {
	private final ClienteService clienteService;
	private Map<String, String> map = new HashMap<>();

	public ClienteConsultaController(ClienteService clienteService) {
		super();
		this.clienteService = clienteService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAll() {
		try {
			List<ClienteEntity> lstClienteEntity = this.clienteService.findAll();
			if (lstClienteEntity.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(lstClienteEntity);
			}
		} catch (Exception e) {
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
}
