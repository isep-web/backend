package com.isep.project.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isep.project.entity.Role;
import com.isep.project.entity.User;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Implement {@link UserDetails}
 *
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/5/31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails
{

    private static final long serialVersionUID = -8685111208992959493L;

    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    private String phone;

    private String email;

    /**
     * 0=banned/1=normal
     */
    private Integer status;

    private List<String> roles;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl create(User user, List<Role> roles)
    {
        List<String> roleNames = roles.stream().map(Role::getName).collect(Collectors.toList());

        return new UserDetailsImpl(user.getId(), user.getUsername(), user.getPassword(),
                user.getPhone(), user.getEmail(), user.getStatus(), roleNames, null);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return authorities;
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public String getUsername()
    {
        return username;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return Objects.equals(this.status, 1);
    }
}
