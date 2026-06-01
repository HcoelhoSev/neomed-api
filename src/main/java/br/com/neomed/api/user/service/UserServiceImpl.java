package br.com.neomed.api.user.service;

import br.com.neomed.api.user.dto.*;
import br.com.neomed.api.user.entity.*;
import br.com.neomed.api.user.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public java.util.List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public UserResponse findById(Long id) {
        return userRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public UserResponse create(CreateUserRequest request) {
        if (userRepository.existsByLogin(request.login())) {
            throw new IllegalArgumentException("Login already exists");
        }

        User user = User.builder()
                .personId(request.personId())
                .login(request.login())
                .passwordHash(passwordEncoder.encode(request.password()))
                .active(true)
                .loginAttempts(0)
                .build();

        User savedUser = userRepository.save(user);

        String roleName = request.role() == null || request.role().isBlank()
                ? "ADMIN"
                : request.role().toUpperCase();

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        UserRole userRole = UserRole.builder()
                .id(new UserRoleId(savedUser.getId(), role.getId()))
                .user(savedUser)
                .role(role)
                .build();

        userRoleRepository.save(userRole);

        return toResponse(savedUser);
    }

    @Override
    public UserResponse update(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setPersonId(request.personId());
        user.setActive(request.active());

        return toResponse(userRepository.save(user));
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        userRepository.delete(user);
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getPersonId(),
                user.getLogin(),
                user.getActive(),
                user.getLoginAttempts(),
                user.getLastLogin(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}