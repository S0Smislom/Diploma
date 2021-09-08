package com.market.marketplace.repositories;


import com.market.marketplace.models.ERole;
import com.market.marketplace.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
