package com.ventas.app.business.controller.privates.gestion;

import java.util.HashMap;
import java.util.Map;
import static java.util.Objects.isNull;
import static com.ventas.app.business.controller.constants.ConstantController.MSG_INTERNAL_ERROR;
import static com.ventas.app.business.controller.constants.ConstantController.MSG_BAD_REQUEST;

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

import com.ventas.app.business.entity.ClienteEntity;
import com.ventas.app.business.services.ClienteService;
import com.ventas.app.business.services.ServiceException;

@RestController
@RequestMapping("/private/api/v1/cliente/gestion")
public class ClienteGestionController {
	private final ClienteService clienteService;
	private Map<String, String> map = new HashMap<>();

	public ClienteGestionController(ClienteService clienteService) {
		super();
		this.clienteService = clienteService;
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody ClienteEntity ClienteEntity){
		try {
			ClienteEntity oClienteEntity = this.clienteService.save(ClienteEntity);
			if (isNull(oClienteEntity)) {
				map.put("alerta", MSG_BAD_REQUEST);
				return ResponseEntity.badRequest().body(map);
			} else {
				return new ResponseEntity<>(oClienteEntity,HttpStatus.CREATED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable  Long id, @RequestBody ClienteEntity ClienteEntity){

		try {
			ClienteEntity oClienteEntity = this.clienteService.update(id,ClienteEntity);
			if (isNull(oClienteEntity)) {
				map.put("alerta", MSG_BAD_REQUEST);
				return ResponseEntity.badRequest().body(map);
			} else {
				return ResponseEntity.ok(oClienteEntity);
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
			this.clienteService.delete(id);
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
