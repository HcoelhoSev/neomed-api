package br.com.neomed.api.domain.auth.service;

import br.com.neomed.api.domain.auth.*;
import br.com.neomed.api.user.entity.Role;
import br.com.neomed.api.user.entity.User;
import br.com.neomed.api.user.entity.UserRole;
import br.com.neomed.api.user.entity.UserRoleId;
import br.com.neomed.api.user.repository.RoleRepository;
import br.com.neomed.api.user.repository.UserRepository;
import br.com.neomed.api.user.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.neomed.api.security.AuthenticatedUser;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MeResponseDTO me() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof AuthenticatedUser authenticatedUser)) {
            throw new IllegalStateException("Authenticated user not found");
        }

        var user = authenticatedUser.user();

        var authorities = authentication.getAuthorities()
                .stream()
                .map(authority -> authority.getAuthority())
                .toList();

        return new MeResponseDTO(
                user.getId(),
                user.getLogin(),
                authorities
        );
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {

        var authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        request.login(),
                        request.password()
                );

        authenticationManager.authenticate(authenticationToken);

        String token = tokenService.generateToken(
                request.login(),
                Map.of()
        );

        return new LoginResponseDTO(token);
    }

    @Override
    public void forgotPassword(ForgotPasswordRequestDTO request) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void resetPassword(ResetPasswordRequestDTO request) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public LoginResponseDTO refreshToken(RefreshTokenRequestDTO request) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public UserAuthResponseDTO register(RegisterRequestDTO request) {
        if (userRepository.existsByLogin(request.login())) {
            throw new IllegalArgumentException("Login already exists");
        }

        String roleName = request.role() == null || request.role().isBlank()
                ? "ADMIN"
                : request.role().toUpperCase();

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName));

        User user = User.builder()
                .login(request.login())
                .passwordHash(passwordEncoder.encode(request.password()))
                .active(true)
                .loginAttempts(0)
                .build();

        User savedUser = userRepository.save(user);

        UserRole userRole = UserRole.builder()
                .id(new UserRoleId(savedUser.getId(), role.getId()))
                .user(savedUser)
                .role(role)
                .build();

        userRoleRepository.save(userRole);

        String token = tokenService.generateToken(
                savedUser.getLogin(),
                Map.of("role", role.getName())
        );

        return new UserAuthResponseDTO(
                savedUser.getId(),
                savedUser.getLogin(),
                token
        );
    }
}