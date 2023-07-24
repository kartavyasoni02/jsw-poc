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

import com.yash.jsw.domain.UserDTO;
import com.yash.jsw.model.Role;
import com.yash.jsw.model.User;
import com.yash.jsw.service.role.RoleService;
import com.yash.jsw.service.user.UserService;
import com.yash.jsw.utility.GlobalConstant;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	static Logger logger = LoggerFactory.getLogger(UserController.class);
	static String businessObject = "user"; //used in RedirectAttributes messages 

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	@Autowired
	private MessageSource messageSource;


	@RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
	public UserDTO listUsers() throws Exception {
		logger.debug("IN: User/list-GET");
		UserDTO retrunUsers = new UserDTO();
		List<User> users = userService.getUsers();
		if(users!=null && !users.isEmpty()){
			List<UserDTO> userList = this.getUsers(users);
			retrunUsers.setUsers(userList);
			retrunUsers.setResponseCode(GlobalConstant.OBJECT_FOUND);
		}else{
			retrunUsers.setResponseCode(GlobalConstant.OBJECT_NOT_FOUND);
		}
		return retrunUsers;
	}


	private List<UserDTO> getUsers(List<User> users){
		List<UserDTO> userList = new ArrayList<>();
		for (User user : users) {
			UserDTO dto = new UserDTO();
			dto.setId(user.getId());
			dto.setUsername(user.getUsername());
			dto.setEnabled(user.getEnabled());
			if(user.getRole()!=null){
				dto.setRoleId(user.getRole().getId());
				dto.setRoleName(user.getRole().getRolename());
			}
			userList.add(dto);
		}
		return userList;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public UserDTO addUser(@Valid @RequestBody UserDTO userDTO) throws Exception {
		UserDTO dto = new UserDTO();
		logger.debug("IN: User/add-POST");
		User user = new User();

		user = getUser(userDTO);
		Integer response = userService.addUser(user);
		dto.setResponseCode(response);
		return dto;
	}
	public User getUser(UserDTO userDTO) throws Exception {
		User user = new User();
		user.setId(userDTO.getId());

		Role role = new Role();

		role = roleService.getRole(userDTO.getRoleId());
		if(role==null){
			role = setNullRole();	
		}

		user.setRole(role);
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		user.setEnabled(userDTO.getEnabled());
		return user;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public UserDTO editUserPage(@RequestParam(value = "id", required = true)Integer id) throws Exception {
		UserDTO userDTO = null;
		logger.debug("IN: User/edit-GET:  ID to query = " + id);
		logger.debug("Adding userDTO object to model");
		User user = userService.getUser(id);
		if(user!=null){
			userDTO = getUserDTO(user);
			userDTO.setResponseCode(GlobalConstant.OBJECT_FOUND); 
		}else{
			userDTO = new UserDTO();
			userDTO.setResponseCode(GlobalConstant.OBJECT_FOUND);
		}
		return userDTO;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public UserDTO editUser(@Valid @RequestBody UserDTO userDTO
			) throws Exception {
		User user = getUser(userDTO);
		userDTO.setResponseCode(userService.updateUser(user));
		return userDTO;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public UserDTO deleteUser(
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "phase", required = true) String phase) throws Exception {
		UserDTO userDTO = null;
		User user;
		logger.debug("IN: User/delete-GET | id = " + id + " | phase = " + phase );

		if (phase.equals(messageSource.getMessage("button.action.stage", null, Locale.US))) {
			user = userService.getUser(id);
			if(user==null){
				userDTO = new UserDTO();
				userDTO.setResponseCode(GlobalConstant.OBJECT_NOT_FOUND);
			}else{
				userDTO=this.getUserDTO(user);
				userDTO.setResponseCode(GlobalConstant.ACTION_SUCCESSFUL);
			}
			return userDTO;
		} else if (phase.equals(messageSource.getMessage("button.action.delete", null, Locale.US))) {

			userService.deleteUser(id);
			userDTO = new UserDTO();
			userDTO.setResponseCode(GlobalConstant.ACTION_SUCCESSFUL);
			return userDTO;
		}
		return userDTO;
	}

	public UserDTO getUserDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setUsername(user.getUsername());
		userDTO.setPassword(user.getPassword());
		userDTO.setEnabled(user.getEnabled());
		Role role = new Role();
		if (user.getRole() == null || user.getRole().getId() == 0) {
			role = setNullRole();
		} else {
			role = user.getRole();
		}
		userDTO.setRoleId(role.getId());
		return userDTO;
	}


	public Role setNullRole() {
		Role role = new Role();
		role.setId(0);
		role.setRolename("ROLE_NOT_SET");
		return role;
	}

}