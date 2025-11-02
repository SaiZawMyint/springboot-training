package com.example.demo.api.controllers.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.api.requests.Role.RoleCreateRequest;
import com.example.demo.api.response.BaseResponse;
import com.example.demo.api.response.Role.RoleResponse;
import com.example.demo.dtos.role.RoleDto;
import com.example.demo.services.role.RoleService;

@RestController
@RequestMapping("/api/role")
public class RoleApiController {
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/list")
	public ResponseEntity<BaseResponse<?>> getRoleList() {
		BaseResponse<List<RoleResponse>> response = new BaseResponse<List<RoleResponse>>();

		try {
			response.setSuccess(true);
			response.setStatusCode(1);
			response.setData(roleService.getAllRoleList().stream().map(t -> new RoleResponse().copyFormDTO(t))
					.toList());

			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			response.setSuccess(false);
			response.setStatusCode(-1);
			return ResponseEntity.internalServerError().body(response);
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<BaseResponse<?>> createRole(@RequestBody RoleCreateRequest request) {
		BaseResponse<RoleResponse> response = new BaseResponse<RoleResponse>();
		try {
			RoleDto saved = roleService.saveRole(request.convertToDto());

			response.setData(new RoleResponse().copyFormDTO(saved));
			response.setStatusCode(1);
			response.setSuccess(true);
			//response.setMessage("Create Role success!");
		} catch (Exception e) {
			response.setStatusCode(-1);
			response.setSuccess(false);
			//response.setMessage(e.getMessage());
			return ResponseEntity.internalServerError().body(response);
		}

		return ResponseEntity.ok(response);
	}
	
	//update
    @PutMapping("/role/{id}")
    public ResponseEntity<BaseResponse<?>> updateRole(@PathVariable Long id, @RequestBody RoleCreateRequest request) {
    	BaseResponse<RoleResponse> response = new BaseResponse<RoleResponse>();
    	
    	try {
    		RoleDto updtRole = roleService.getById(id);

			response.setData(new RoleResponse().copyFormDTO(updtRole));
			response.setStatusCode(1);
			response.setSuccess(true);
			//response.setMessage("Update Role success!");
		} catch (Exception e) {
			response.setStatusCode(-1);
			response.setSuccess(false);
			//response.setMessage(e.getMessage());
			return ResponseEntity.internalServerError().body(response);
		}
    	return ResponseEntity.ok(response);
    }
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<BaseResponse<?>> deleteRole(@PathVariable("id") Long pId){
		BaseResponse<Long> response = new BaseResponse<Long>();
		try {
			this.roleService.deleteRole(pId);
			response.setStatusCode(1);
			response.setSuccess(true);
			//response.setMessage("Delete Role success!");
			response.setData(pId);
		} catch (Exception e) {
			response.setStatusCode(-1);
			response.setSuccess(false);
			//response.setMessage(e.getMessage());
			return ResponseEntity.internalServerError().body(response);
		}
		return ResponseEntity.ok(response);
	}
}
