package com.Foodcourt.fc.Repository;

import com.Foodcourt.fc.Entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles,Integer> {
}
