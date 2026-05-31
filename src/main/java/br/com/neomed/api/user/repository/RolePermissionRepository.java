package br.com.neomed.api.user.repository;

import br.com.neomed.api.user.entity.RolePermission;
import br.com.neomed.api.user.entity.RolePermissionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolePermissionRepository extends JpaRepository<RolePermission, RolePermissionId> {

    List<RolePermission> findByRoleId(Long roleId);
}