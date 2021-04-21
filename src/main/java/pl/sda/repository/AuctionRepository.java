package pl.sda.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sda.model.Auction;

import java.util.List;

@Repository
public interface AuctionRepository extends CrudRepository<Auction, Long> {

    @Query("select a from Auction a join a.user u where u.username = :username")
    List<Auction> findByUsername(@Param("username") String username);

    List<Auction> findAll();

}
