package pl.sda.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sda.model.Auction;

@Repository
public interface AuctionRepository extends CrudRepository<Auction, Long> {
}
