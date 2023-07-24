package com.yash.jsw.service.role;

import java.util.List;

import com.yash.jsw.model.Role;

public interface RoleService {

    public Integer addRole(Role role) throws Exception;

    public Role getRole(int id) throws Exception;
    
    public Role getRole(String rolename) throws Exception;

    public Integer updateRole(Role role) throws  Exception;

    public void deleteRole(int id) throws Exception;

    public List<Role> getRoles() throws Exception;

    public List<Role> getAlllRole(List<Integer> roleIds) throws Exception;

}
