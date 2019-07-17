package pl.sda.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sda.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {


    Category getOne(Long categoryId);
}
