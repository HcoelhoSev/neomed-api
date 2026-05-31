package br.com.neomed.api.user.repository;

import br.com.neomed.api.user.entity.UserRole;
import br.com.neomed.api.user.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {

    List<UserRole> findByUserId(Long userId);
}