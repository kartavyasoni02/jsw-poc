package com.yash.jsw.dao.impl;

import java.util.List;

import javax.management.relation.RoleNotFoundException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yash.jsw.dao.RoleDAO;
import com.yash.jsw.model.Role;
import com.yash.jsw.utility.GlobalConstant;

@Repository
public class RoleDAOImpl implements RoleDAO {
	static Logger logger = LoggerFactory.getLogger(RoleDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addRole(Role role) throws Exception {
		logger.debug("RoleDAOImpl.addRole() - [" + role.getRolename() + "]");
		getCurrentSession().save(role);
	}

	@Override
	public Role getRole(int role_id) throws Exception {
		return (Role) getCurrentSession().get(Role.class, role_id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Role getRole(String usersRole) throws RoleNotFoundException {
		logger.debug("RoleDAOImpl.getRole() - [" + usersRole + "]");
		Query query = getCurrentSession().createQuery("from Role where rolename = :usersRole ");
		query.setString("usersRole", usersRole);

		logger.debug(query.toString());
		List<Role> list = (List<Role>)query.list();
		if (list!=null && !list.isEmpty() ) {
			Role roleObject = (Role) list.get(0);
			return roleObject;
		} 
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean checkRole(String usersRole,Integer id) throws Exception {
		logger.debug("RoleDAOImpl.getRole() - [" + usersRole + "]");
		Query query = getCurrentSession().createQuery("from Role where rolename = :usersRole and id!=:id ");
		query.setString("usersRole", usersRole);
		query.setInteger("id", id);

		logger.debug(query.toString());
		List<Role> list = (List<Role>)query.list();
		if (list!=null && !list.isEmpty() ) {
			return true;
		}
		return false;
	}
	@Override
	public Integer updateRole(Role role) throws Exception {
		if(!checkRole(role.getRolename(),role.getId())){
			Role roleToUpdate = getRole(role.getId());
			roleToUpdate.setId(role.getId());
			roleToUpdate.setRolename(role.getRolename());
			getCurrentSession().update(roleToUpdate);	
			return GlobalConstant.ACTION_SUCCESSFUL;
		}else{
			return GlobalConstant.OBJECT_DUPLICATE;
		}
	}

	@Override
	public void deleteRole(int role_id) throws Exception {
		Role role = getRole(role_id);
		if (role != null) {
			getCurrentSession().delete(role);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Role> getRoles() {
		String hql = "FROM Role r ORDER BY r.id";
		return getCurrentSession().createQuery(hql).list();
	}

	@Override
	public List<Role> getAlllRole(List<Integer> roleIds) throws Exception {
		logger.debug("RoleDAOImpl.getAlllRole() - [" + roleIds + "]");
		Query query = getCurrentSession().createQuery("from Role where id in (:roleIds) ");
		query.setParameterList("roleIds", roleIds);
		@SuppressWarnings("unchecked")
		List<Role> listOfRole =(List<Role>) query.list();
		if (listOfRole!=null && !listOfRole.isEmpty()) {
			return listOfRole;  
		}
		return null; 
	}
}