package com.yash.jsw.service.role.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yash.jsw.dao.RoleDAO;
import com.yash.jsw.model.Role;
import com.yash.jsw.service.role.RoleService;
import com.yash.jsw.utility.GlobalConstant;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    
    @Autowired
    private RoleDAO roleDAO;

    @Override
    public Integer addRole(Role role) throws Exception {
    	Role roleCheck = this.getRole(role.getRolename());
    	if(roleCheck!=null){
			logger.debug( "The role [" + roleCheck.getRolename() + "] already exists");
			return GlobalConstant.OBJECT_DUPLICATE;
		}else{
			roleDAO.addRole(role);
			return GlobalConstant.ACTION_SUCCESSFUL;
		}
    }

    @Override
    public Role getRole(int id) throws Exception {
    	return roleDAO.getRole(id);
    }

    @Override
    public Role getRole(String rolename) throws Exception {
        return roleDAO.getRole(rolename);
    }

    @Override
    public Integer updateRole(Role role) throws Exception {
    	return roleDAO.updateRole(role);
    }

    @Override
    public void deleteRole(int id) throws Exception {
        roleDAO.deleteRole(id);
    }

    @Override
    public List<Role> getRoles() throws Exception {
        return roleDAO.getRoles();
    }
    @Override
    public List<Role> getAlllRole(List<Integer> roleIds) throws Exception {
        return roleDAO.getAlllRole(roleIds);
    }
}
