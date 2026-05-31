package br.com.neomed.api.domain.auth.service;

import br.com.neomed.api.security.AuthenticatedUser;
import br.com.neomed.api.user.entity.RolePermission;
import br.com.neomed.api.user.repository.RolePermissionRepository;
import br.com.neomed.api.user.repository.UserRepository;
import br.com.neomed.api.user.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RolePermissionRepository rolePermissionRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        var user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        var authorities = userRoleRepository.findByUserId(user.getId())
                .stream()
                .flatMap(userRole ->
                        rolePermissionRepository.findByRoleId(userRole.getRole().getId())
                                .stream()
                )
                .map(RolePermission::getPermission)
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .distinct()
                .toList();

        return new AuthenticatedUser(user, authorities);
    }

}