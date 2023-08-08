package com.toy4.global.security;

import static com.toy4.domain.status.type.StatusType.RETIRED;

import com.toy4.domain.employee.domain.Employee;
import com.toy4.domain.employee.type.EmployeeRole;
import com.toy4.domain.status.domain.Status;

import java.util.Collection;
import java.util.Collections;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final Employee employee;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        EmployeeRole role = employee.getRole();
        return Collections.singleton(new SimpleGrantedAuthority(role.getRole()));
    }

    @Override
    public String getPassword() {
        return employee.getPassword();
    }

    @Override
    public String getUsername() {
        return employee.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        Status status = employee.getStatus();
        return status.getType() != RETIRED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
