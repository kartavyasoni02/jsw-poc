package com.yash.jsw.domain;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.common.base.Objects;

@JsonInclude(Include.NON_NULL)
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -1631797874369735181L;
    static Logger logger = LoggerFactory.getLogger(UserDTO.class);
    
    private Integer id;
    
    @NotNull(message = "{error.user.username.null}")
    @NotEmpty(message = "{error.user.username.empty}")
    @Size(max = 50, message = "{error.user.username.max}")
    private String username;

    @NotNull(message = "{error.user.password.null}")
    @NotEmpty(message = "{error.user.password.empty}")
    @Size(max = 100, message = "{error.user.password.max}")
    private String password;
    
    private boolean enabled;
    private Integer roleId;
    private String roleName;
    
    private List<UserDTO> users;
    private Integer responseCode;
   	private String message;
    
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean getEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public List<UserDTO> getUsers() {
		return users;
	}
	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}
	
	public Integer getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Override
    public String toString() {
        return String.format("%s(id=%d, username=%s, password=%s, enabled=%b, roleID=%d)", 
                this.getClass().getSimpleName(), 
                this.getId(), 
                this.getUsername(), 
                this.getPassword(), 
                this.getEnabled(),
                this.getRoleId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (o instanceof UserDTO) {
            final UserDTO other = (UserDTO) o;
            return Objects.equal(getId(), other.getId())
                    && Objects.equal(getUsername(), other.getUsername())
                    && Objects.equal(getPassword(), other.getPassword())
                    && Objects.equal(getEnabled(), other.getEnabled())
                    && Objects.equal(getRoleId(), other.getRoleId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getUsername(), getPassword(), getEnabled(), getRoleId());
    }


}
