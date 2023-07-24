package com.yash.jsw.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yash.jsw.domain.RoleDTO;
import com.yash.jsw.model.Role;
import com.yash.jsw.service.role.RoleService;
import com.yash.jsw.utility.GlobalConstant;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

	static Logger logger = LoggerFactory.getLogger(RoleController.class);
	static String businessObject = "role"; //used in RedirectAttributes messages 

	@Autowired
	private RoleService roleService;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
	public RoleDTO listOfRoles() throws Exception{
		RoleDTO retrunRoles = new RoleDTO();
		logger.debug("IN: Role/list-GET");
		List<Role> roles = roleService.getRoles();
		if(roles!=null && !roles.isEmpty()){
			List<RoleDTO> roleList = this.getRoles(roles);
			retrunRoles.setRoles(roleList);
			retrunRoles.setResponseCode(GlobalConstant.OBJECT_FOUND);
		}else{
			retrunRoles.setResponseCode(GlobalConstant.OBJECT_NOT_FOUND);
		}
		return retrunRoles;
	}              
	private List<RoleDTO> getRoles(List<Role> roles){
		List<RoleDTO> roleList = new ArrayList<RoleDTO>();
		for (Role role : roles) {
			RoleDTO dto = new RoleDTO();
			dto.setRoleId(role.getId());
			dto.setRolename(role.getRolename());
			roleList.add(dto);
		}
		return roleList;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public RoleDTO editRolePage(@RequestParam(value = "id", required = true) Integer id) throws Exception{

		logger.debug("IN: Role/edit-GET:  ID to query = " + id);
		RoleDTO retrunRoles = new RoleDTO();
		logger.debug("IN: Role/list-GET");
		Role role = roleService.getRole(id);
		if(role!=null){
			retrunRoles.setRoleId(role.getId());
			retrunRoles.setRolename(role.getRolename());
			retrunRoles.setResponseCode(GlobalConstant.OBJECT_FOUND);
			return retrunRoles;
		}else{
			retrunRoles.setResponseCode(GlobalConstant.OBJECT_NOT_FOUND);
			return retrunRoles;
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public RoleDTO deleteRolePage(
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "phase", required = true) String phase) throws Exception {
		RoleDTO retrunRoles = new RoleDTO();
		Role role;
		logger.debug("IN: Role/delete-GET ");

		if (phase.equals(messageSource.getMessage("button.action.stage", null, Locale.US))) {
			role = roleService.getRole(id);
			if(role!=null){
				retrunRoles.setRoleId(role.getId());
				retrunRoles.setRolename(role.getRolename());
				retrunRoles.setResponseCode(GlobalConstant.OBJECT_FOUND);
				return retrunRoles;
			}else{
				retrunRoles.setResponseCode(GlobalConstant.OBJECT_NOT_FOUND);
				return retrunRoles;
			}

		} else if (phase.equals(messageSource.getMessage("button.action.delete", null, Locale.US))) {
			roleService.deleteRole(id);
			retrunRoles.setResponseCode(GlobalConstant.OBJECT_FOUND);
			return retrunRoles;
		}
		return retrunRoles;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public RoleDTO addRole(@Valid @RequestBody Role role) throws Exception {
		logger.debug("IN: Role/add-POST");
		RoleDTO retrunRoles = new RoleDTO();
		Integer response = roleService.addRole(role);
		retrunRoles.setResponseCode(response);
		return retrunRoles;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public RoleDTO editRole(@Valid @RequestBody Role role) throws Exception{

		RoleDTO retrunRoles = new RoleDTO();
		logger.debug("Role/edit-POST:  " + role.toString());
		Integer respose = roleService.updateRole(role);
		retrunRoles.setResponseCode(respose);
		return retrunRoles;
	}
}