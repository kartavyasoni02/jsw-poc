package com.yash.jsw.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;

@Entity
@Table(name = "ROLES")
public class Role extends BaseEntity implements Serializable, GrantedAuthority  {

    private static final long serialVersionUID = 6874667425302308430L;
    static Logger logger = LoggerFactory.getLogger(Role.class);
    
    @NotNull(message = "{error.roles.role.null}")
    @NotEmpty(message = "{error.roles.role.empty}")
    @Size(max = 50, message = "{error.roles.role.max}")
    @Column(name = "rolename", length = 50)
    private String rolename;
    
    //@OneToMany(cascade = CascadeType.ALL)  
    @OneToMany(fetch = FetchType.LAZY)  
    @JoinTable(name = "user_roles",   
        joinColumns        = {@JoinColumn(name = "role_id", referencedColumnName = "id")},  
        inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}  
    )  
    @JsonIgnore
    private Set<User> userRoles;
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_permissions",
        joinColumns        = { @JoinColumn(name = "role_id",       referencedColumnName = "id") },
        inverseJoinColumns = { @JoinColumn(name = "permission_id", referencedColumnName = "id") }
    )    

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Set<User> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<User> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        return String.format("%s(id=%d, rolename='%s')", 
                this.getClass().getSimpleName(), 
                this.getId(), this.getRolename());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (o instanceof Role) {
            final Role other = (Role) o;
            return Objects.equal(getId(), other.getId())
                    && Objects.equal(getRolename(), other.getRolename());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getRolename());
    }

    @Override
    public String getAuthority() {
        return getRolename();
    }
}
