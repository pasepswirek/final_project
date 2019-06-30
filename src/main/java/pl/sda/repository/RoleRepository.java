package pl.sda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

}
