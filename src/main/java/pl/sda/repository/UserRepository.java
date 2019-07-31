package pl.sda.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sda.model.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

    User findUserById(Long id);


//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query("update User u set u.city = city where u.username =  username ")
//    void updateData(@Param("username") String username , @Param("city") String city);

//    @Modifying
//    @Query("update User u set u.city = city where u.user_is =  user_id ")
//    User updateData1(@Param("city") String city, @Param("user_id") int user_id);


}
