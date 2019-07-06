package pl.sda.repository;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sda.model.Role;

import javax.transaction.Transactional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByName(String name);

//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query(value ="select r.name from role r  join user u join user_role ur on u.user_id = ur.user_id where  " +
//            "u.username ='pasepswirek@wp.pl' and r.role_id = ur.role_id", nativeQuery = true)
//    Role  findByUserName(@Param("username")String username);

}
