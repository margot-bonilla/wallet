package org.mbonilla.wallet.repository;

import org.mbonilla.wallet.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
