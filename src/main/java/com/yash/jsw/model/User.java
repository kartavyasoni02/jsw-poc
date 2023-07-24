package com.yash.jsw.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;

@Entity  
@Table(name="USERS")
public class User extends BaseEntity implements UserDetails {
    
    private static final long serialVersionUID = 6311364761937265306L;
    static Logger logger = LoggerFactory.getLogger(User.class);
    
    @NotNull(message = "{error.user.username.null}")
    @NotEmpty(message = "{error.user.username.empty}")
    @Size(max = 50, message = "{error.user.username.max}")
    @Column(name = "username", length = 50)
    private String username;

    @NotNull(message = "{error.user.password.null}")
    @NotEmpty(message = "{error.user.password.empty}")
    @Size(max = 100, message = "{error.user.password.max}")
    @Column(name = "password", length = 50)
    private String password;
    
    @Column(name = "enabled")
    private boolean enabled;
    
    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)  
    @JoinTable(name = "user_roles",  
        joinColumns        = {@JoinColumn(name = "user_id", referencedColumnName = "id")},  
        inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}  
    )  
    
    private Role role;
    
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("%s(id=%d, username=%s, password=%s, enabled=%b)", 
                this.getClass().getSimpleName(), 
                this.getId(), 
                this.getUsername(), 
                this.getPassword(), 
                this.getEnabled());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (o instanceof User) {
            final User other = (User) o;
            return Objects.equal(getId(), other.getId())
                    && Objects.equal(getUsername(), other.getUsername())
                    && Objects.equal(getPassword(), other.getPassword())
                    && Objects.equal(getEnabled(), other.getEnabled());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getUsername(), getPassword(), getEnabled());
    }

   /* @Transient
    public Set<Permission> getPermissions() {
        Set<Permission> perms = new HashSet<Permission>();
        perms.addAll(role.getPermissions()); 
        return perms;
    }

    @Override
    @Transient
    public Collection<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(getRole());
        authorities.addAll(getPermissions());
        return authorities;
    }*/

    @Override
    public boolean isAccountNonExpired() {
        //return true = account is valid / not expired
        return true; 
    }

    @Override
    public boolean isAccountNonLocked() {
        //return true = account is not locked
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //return true = password is valid / not expired
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.getEnabled();
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
    
}
